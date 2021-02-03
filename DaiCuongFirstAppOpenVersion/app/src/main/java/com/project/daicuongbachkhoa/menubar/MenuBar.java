package com.project.daicuongbachkhoa.menubar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.project.daicuongbachkhoa.MainActivity;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.news.listnews.ListNews;
import com.project.daicuongbachkhoa.student.StudentInfo;
import com.project.daicuongbachkhoa.student.StudentNotification;
import com.project.daicuongbachkhoa.student.Subject;
import com.project.daicuongbachkhoa.student.algebrastudent.OptionAlgebraStudent;
import com.project.daicuongbachkhoa.student.lawstudent.OptionLawStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.OptionPhysicsOneStudent;

public class MenuBar extends AppCompatActivity {

    public DrawerLayout
            drawerLayout;
    private LinearLayout
            btnGoLogOut,
            btnGoAuthor,
            btnGoTarget,
            btnGoFeedback,
            btnGoHome;
    private ImageView
            imgGoMenu,
            imgGoStudentNotification,
            imgGoLearn,
            imgGoNews,
            imgGoCompetition,
            imgGoGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        btnGoHome = findViewById(R.id.btnGoHome);
        btnGoAuthor = findViewById(R.id.btnGoAuthor);
        btnGoFeedback = findViewById(R.id.btnGoFeedback);
        btnGoTarget = findViewById(R.id.btnGoTarget);
        btnGoLogOut = findViewById(R.id.btnGoLogOut);
        imgGoMenu = findViewById(R.id.imgGoMenu);
        imgGoStudentNotification = findViewById(R.id.imgStudentNotification);
        imgGoLearn = findViewById(R.id.imgGoLearn);
        imgGoNews = findViewById(R.id.imgGoNews);

        imgGoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMenu();
            }
        });
        imgGoStudentNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goStudentNotification();
            }
        });
        btnGoLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutAccount();
            }
        });
        btnGoAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAuthor();
            }
        });
        btnGoTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTarget();
            }
        });
        btnGoFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFeedback();
            }
        });
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goInformationUser();
            }
        });
        imgGoLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLearn();
            }
        });
        imgGoNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNews();
            }
        });
    }

    private void goStudentNotification() {
        startActivity(new Intent(MenuBar.this, StudentNotification.class));
    }

    private void goNews() {
        startActivity(new Intent(MenuBar.this, ListNews.class));
    }

    private void goLearn() {
        startActivity(new Intent(MenuBar.this, Subject.class));
    }

    private void goInformationUser() {
        startActivity(new Intent(this, StudentInfo.class));
    }

    private void goMenu() {
        openDrawer(drawerLayout);
    }

    private void goFeedback() {
        startActivity(new Intent(MenuBar.this, Feedback.class));
    }

    private void goTarget() {
        startActivity(new Intent(MenuBar.this, Target.class));
    }

    private void goAuthor() {
        startActivity(new Intent(this, Author.class));
    }

    private void logOutAccount() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MenuBar.this, MainActivity.class));
        finish();
    }

    // open - close navigation bar
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}