package com.project.daicuongbachkhoa.news.listnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.News;
import com.project.daicuongbachkhoa.news.ReadNews;
import com.project.daicuongbachkhoa.student.physicsonestudent.historyphysicsonestudent.ShowHistoryExamPhysicsOneStudent;
import com.squareup.picasso.Picasso;

public class ListNews extends AppCompatActivity {

    private RecyclerView
            revListNews;
    private FirebaseRecyclerOptions<News>
            recyclerOptions;
    private FirebaseRecyclerAdapter<News, AdapterListNews>
            adapter;
    private FirebaseDatabase
            database;
    private DatabaseReference
            referenceNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        revListNews = findViewById(R.id.revListNews);
        database = FirebaseDatabase.getInstance();
        referenceNews = database.getReference("News");

        showListNews();
    }

    private void showListNews() {
        revListNews.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        revListNews.setLayoutManager(mLayoutManager);

        recyclerOptions = new FirebaseRecyclerOptions.Builder<News>().setQuery(referenceNews, News.class).build();
        adapter = new FirebaseRecyclerAdapter<News, AdapterListNews>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull AdapterListNews holder, int i, @NonNull final News model) {
                holder.txtTitleNews.setText(model.getTitleNews());
                holder.txtDateNews.setText(model.getDateNews());
                holder.txtContentNews.setText(model.getContentNews() + "...\n(click để xem chi tiết)");
                String urlImageNews = model.getImgNews();
                Picasso.with(ListNews.this).load(urlImageNews).into(holder.imgNews);
           holder.txtContentNews.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   final AppCompatActivity activity = (AppCompatActivity) view.getContext();
                   Intent intent = new Intent(activity, ReadNews.class);
                   intent.putExtra("LINK_NEWS", model.getLinkNews());
                   activity.startActivity(intent);
               }
           });
            }

            @NonNull
            @Override
            public AdapterListNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news_school, parent, false);
                AdapterListNews listNews = new AdapterListNews(view);
                return listNews;
            }
        };
        revListNews.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}