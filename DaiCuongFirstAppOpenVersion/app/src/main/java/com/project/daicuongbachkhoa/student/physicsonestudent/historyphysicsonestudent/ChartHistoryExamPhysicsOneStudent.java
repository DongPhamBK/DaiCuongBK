package com.project.daicuongbachkhoa.student.physicsonestudent.historyphysicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;

import java.util.ArrayList;

public class ChartHistoryExamPhysicsOneStudent extends AppCompatActivity {

    private BarChart
            layoutChartHistoryPhysicsOneStudent;
    private FirebaseUser
            student;
    private String
            studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_history_exam_physics_one_student);
        layoutChartHistoryPhysicsOneStudent = findViewById(R.id.layoutChartHistoryPhysicsOneStudent);
        student = FirebaseAuth.getInstance().getCurrentUser();
        studentID = student.getUid();

        showChartHistoryPhysicsOneStudent();
    }

    private void showChartHistoryPhysicsOneStudent() {
        FirebaseDatabase.getInstance().getReference("Students").child(studentID).child("PhysicsOne").
                child("Storage").limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<BarEntry> visitor = new ArrayList<>();
                int i = 1;
                for (DataSnapshot sp : snapshot.getChildren()) {
                    visitor.add(new BarEntry(i, Integer.parseInt(String.valueOf(sp.child("pointStorage").getValue()))));
                    i++;
                }
                BarDataSet barDataSet = new BarDataSet(visitor, "Điểm");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(16f);
                BarData barData = new BarData(barDataSet);
                layoutChartHistoryPhysicsOneStudent.setFitBars(true);
                layoutChartHistoryPhysicsOneStudent.setData(barData);
                layoutChartHistoryPhysicsOneStudent.getDescription().setText("Biểu đồ điểm số mười lần thi Vật lý 1 gần nhất");
                layoutChartHistoryPhysicsOneStudent.animateY(1000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChartHistoryExamPhysicsOneStudent.this, "Hmmm ! Có lỗi xảy ra ! Vui lòng liên hệ nhóm phát triển để khắc phục !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

