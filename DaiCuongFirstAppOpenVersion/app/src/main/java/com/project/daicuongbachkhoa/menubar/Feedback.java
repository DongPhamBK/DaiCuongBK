package com.project.daicuongbachkhoa.menubar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.daicuongbachkhoa.R;

public class Feedback extends AppCompatActivity {

    private Button
            btnFacebook,
            btnEmail;
    private ImageView
            imgFeedbackGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        imgFeedbackGoHome = findViewById(R.id.imgFeedbackGoHome);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnEmail = findViewById(R.id.btnEmail);

        imgFeedbackGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbackGoHome();
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrlLink("https://www.facebook.com/profile.php?id=100012086771102");
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrlLink("https://mail.google.com/mail/u/0/?tab=rm#inbox");
            }
        });
    }

    //return
    private void feedbackGoHome() {
        finish();
    }

    //go to link
    public void openUrlLink(String url) {
        Uri uri = Uri.parse(url);
        Intent goFace = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(goFace);
    }
}