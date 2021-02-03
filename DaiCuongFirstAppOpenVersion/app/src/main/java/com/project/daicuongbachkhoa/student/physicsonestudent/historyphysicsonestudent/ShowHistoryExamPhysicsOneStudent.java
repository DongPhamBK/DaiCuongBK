package com.project.daicuongbachkhoa.student.physicsonestudent.historyphysicsonestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.project.daicuongbachkhoa.R;

public class ShowHistoryExamPhysicsOneStudent extends AppCompatActivity {

    private TextView
            txtShowHistoryExamPhysicOneStudent;
    private String
            showHistoryExamPhysicOneStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history_exam_physics_one_student);
        txtShowHistoryExamPhysicOneStudent = findViewById(R.id.txtShowHistoryExamPhysicOneStudent);
        showHistoryExamPhysicOneStudent();
    }

    private void showHistoryExamPhysicOneStudent() {
        showHistoryExamPhysicOneStudent = getIntent().getStringExtra("CODE_HISTORY_EXAM_PHYSICS_ONE_STUDENT");
        txtShowHistoryExamPhysicOneStudent.setText(showHistoryExamPhysicOneStudent);
    }
}