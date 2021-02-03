package com.project.daicuongbachkhoa.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.menubar.MenuBar;
import com.project.daicuongbachkhoa.student.algebrastudent.OptionAlgebraStudent;
import com.project.daicuongbachkhoa.student.lawstudent.OptionLawStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.OptionPhysicsOneStudent;

public class Subject extends AppCompatActivity {

    private ImageView
            imgPhysicsOne,
            imgAlgebra,
            imgLaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        imgPhysicsOne = findViewById(R.id.imgPhysicsOne);
        imgAlgebra = findViewById(R.id.imgAlgebra);
        imgLaw = findViewById(R.id.imgLaw);

        imgPhysicsOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOptionPhysic();
            }
        });
        imgLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOptionLaw();
            }
        });
        imgAlgebra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOptionAlgebra();
            }
        });
    }

    private void goOptionAlgebra() {
        startActivity(new Intent(Subject.this, OptionAlgebraStudent.class));
    }

    private void goOptionLaw() {
        startActivity(new Intent(Subject.this, OptionLawStudent.class));
    }

    private void goOptionPhysic() {
        startActivity(new Intent(Subject.this, OptionPhysicsOneStudent.class));
    }
}