package com.project.daicuongbachkhoa.news.listnews;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.daicuongbachkhoa.R;

public class AdapterListNews extends RecyclerView.ViewHolder {
    public ImageView
            imgNews;
    public TextView
            txtTitleNews,
            txtDateNews,
            txtContentNews;
    public LinearLayout
            btnNews;

    public AdapterListNews(@NonNull View itemView) {
        super(itemView);
        imgNews = itemView.findViewById(R.id.imgNews);
        txtTitleNews = itemView.findViewById(R.id.txtTitleNews);
        txtDateNews = itemView.findViewById(R.id.txtDateNews);
        txtContentNews = itemView.findViewById(R.id.txtContentNews);
        btnNews = itemView.findViewById(R.id.btnNews);
    }


}
