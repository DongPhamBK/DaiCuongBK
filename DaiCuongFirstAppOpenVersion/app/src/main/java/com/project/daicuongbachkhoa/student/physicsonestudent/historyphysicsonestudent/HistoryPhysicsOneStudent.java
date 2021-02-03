package com.project.daicuongbachkhoa.student.physicsonestudent.historyphysicsonestudent;

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Storage;

public class HistoryPhysicsOneStudent extends AppCompatActivity {

    private String
            studentID;
    private RecyclerView
            revListStoragePhysicsOneStudent;
    private Button
            btnChartHistoryPhysicsOneStudent;
    private DatabaseReference
            referenceRightAnswer,
            referenceStudent,
            referenceStorage,
            answerPhysicsOne,
            answerExam1;
    private ValueEventListener
            referenceQuestion,
            referenceAnswer;
    private FirebaseRecyclerOptions<Storage>
            recyclerOptions;
    private FirebaseRecyclerAdapter<Storage, AdapterHistoryPhysicsOneStudent>
            adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_physics_one_student);
        btnChartHistoryPhysicsOneStudent = findViewById(R.id.btnChartHistoryPhysicsOneStudent);
        revListStoragePhysicsOneStudent = findViewById(R.id.revListStoragePhysicsOneStudent);
        FirebaseUser student = FirebaseAuth.getInstance().getCurrentUser();
        studentID = student.getUid();
        referenceStorage = FirebaseDatabase.getInstance().getReference("Students").child(studentID).child("PhysicsOne").child("Storage");

        showListStoragePhysicsOneStudent();
        btnChartHistoryPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryPhysicsOneStudent.this, ChartHistoryExamPhysicsOneStudent.class));
            }
        });

    }

    private void showListStoragePhysicsOneStudent() {
        revListStoragePhysicsOneStudent.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        revListStoragePhysicsOneStudent.setLayoutManager(mLayoutManager);
        recyclerOptions = new FirebaseRecyclerOptions.Builder<Storage>().setQuery(referenceStorage, Storage.class).build();//khá dài
        adapter = new FirebaseRecyclerAdapter<Storage, AdapterHistoryPhysicsOneStudent>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull AdapterHistoryPhysicsOneStudent holder, int i, @NonNull final Storage model) {
                holder.txtNameStoragePhysicsOneStudent.setText(model.getNameStorage());
                holder.txtDateStoragePhysicsOneStudent.setText(model.getDateStorage());
                holder.txtPointStoragePhysicsOneStudent.setText("Điểm: " + (model.getPointStorage()));
                holder.btnFrameStorage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        Intent intent = new Intent(activity, ShowHistoryExamPhysicsOneStudent.class);
                        intent.putExtra("CODE_HISTORY_EXAM_PHYSICS_ONE_STUDENT", model.getContentStorage());
                        activity.startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public AdapterHistoryPhysicsOneStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_storage, parent, false);
                //tạo lập view
                AdapterHistoryPhysicsOneStudent viewHolder = new AdapterHistoryPhysicsOneStudent(view);
                return viewHolder;

            }
        };
        revListStoragePhysicsOneStudent.setAdapter(adapter);//ok
    }

    @Override
    protected void onStart() {
        super.onStart();//change
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}