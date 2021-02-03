package com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Storage;
import com.project.daicuongbachkhoa.teacher.physicsoneteacher.tasksphysicsoneteacher.CreateTasksPhysicsOneTeacher;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultExamPhysicsOneStudent extends AppCompatActivity {

    private TextView
            txtPointResultExamPhysicsOneStudent,
            txtDateExamPhysicsOneStudent,
            txtShowResultExamPhysicsOneStudent,
            txtNameResultExamPhysicsOneStudent;
    private Button
            btnDetailResultExamPhysicsOneStudent;
    private FirebaseUser
            firebaseStudent;
    private DatabaseReference
            referenceRightAnswer;
    private DatabaseReference
            referenceStudent,
            referenceExam;
    private String
            studentID,
            showResultExamPhysicsOneStudent,
            saveContentResultExamPhysicsOneStudent;
    private int
            savePointResultExamPhysicsOneStudent,
            pointResultExamPhysicsOneStudent;
    private String
            examCodeResultExam,
            examNameResultExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exam_physics_one_student);
        txtNameResultExamPhysicsOneStudent = findViewById(R.id.txtNameResultExamPhysicsOneStudent);
        txtPointResultExamPhysicsOneStudent = findViewById(R.id.txtPointResultExamPhysicsOneStudent);
        txtDateExamPhysicsOneStudent = findViewById(R.id.txtDateExamPhysicsOneStudent);
        txtShowResultExamPhysicsOneStudent = findViewById(R.id.txtShowResultExamPhysicsOneStudent);
        btnDetailResultExamPhysicsOneStudent = findViewById(R.id.btnDetailResultExamPhysicsOneStudent);
        referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
        firebaseStudent = FirebaseAuth.getInstance().getCurrentUser();
        studentID = firebaseStudent.getUid();

        setTitleExamPhysicsOneStudent();
        saveExamPhysicsOneStudent();
        showResult();
        btnDetailResultExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResult();
            }
        });
    }

    private void showResult() {
        String status = btnDetailResultExamPhysicsOneStudent.getText().toString();
        if (status.equals("Chi tiết")) {
            detailResultExamPhysicsOneStudent();
            btnDetailResultExamPhysicsOneStudent.setText("Thu gọn");
        } else {
            resultExamPhysicsOneStudent();
            btnDetailResultExamPhysicsOneStudent.setText("Chi tiết");
        }
    }

    private void setTitleExamPhysicsOneStudent() {
        examCodeResultExam = getIntent().getStringExtra("CODE_RESULT_EXAM_PHYSICS_ONE_STUDENT");
        examNameResultExam = getIntent().getStringExtra("NAME_RESULT_EXAM_PHYSICS_ONE_STUDENT");
        txtNameResultExamPhysicsOneStudent.setText(examNameResultExam);
        String date = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date);
        txtDateExamPhysicsOneStudent.setText("Ngày thi: " + simpleDateFormat.format(new Date()));
    }

    private void saveExamPhysicsOneStudent() {
        saveContentResultExamPhysicsOneStudent = "";
        savePointResultExamPhysicsOneStudent = 0;
        referenceStudent.child(studentID).child("PhysicsOne").child(examCodeResultExam).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int questionCount = 1; questionCount <= 10; questionCount++) {
                    final String yourAnswer = String.valueOf(snapshot.child("Ques" + questionCount).getValue());
                    referenceRightAnswer = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
                    referenceExam = referenceRightAnswer.child(examCodeResultExam);
                    final int finalQuestionCount = questionCount;
                    referenceExam.child("Ques" + questionCount).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String contentQuestion = String.valueOf(snapshot.child("Content").getValue());
                            String optionA = String.valueOf(snapshot.child("OptionA").getValue());
                            String optionB = String.valueOf(snapshot.child("OptionB").getValue());
                            String optionC = String.valueOf(snapshot.child("OptionC").getValue());
                            String optionD = String.valueOf(snapshot.child("OptionD").getValue());
                            String rightAnswer = String.valueOf(snapshot.child("RightAns").getValue());
                            if (yourAnswer.equals(rightAnswer)) {
                                savePointResultExamPhysicsOneStudent++;
                            }
                            saveContentResultExamPhysicsOneStudent = saveContentResultExamPhysicsOneStudent + contentQuestion + "\nA: " + optionA + "\nB: " + optionB + "\nC: " + optionC + "\nD: " + optionD + "\nBạn chọn: " + yourAnswer + "     Đáp án đúng: " + rightAnswer + "\n\n\n\n";
                            Storage storage = new Storage(txtNameResultExamPhysicsOneStudent.getText().toString(), savePointResultExamPhysicsOneStudent, txtDateExamPhysicsOneStudent.getText().toString(), txtNameResultExamPhysicsOneStudent.getText().toString() + "\n" + saveContentResultExamPhysicsOneStudent);
                            String date = "ddMMyyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date);
                            referenceStudent.child(studentID).child("PhysicsOne").child("Storage").child(simpleDateFormat.format(new Date()) + examCodeResultExam).setValue(storage);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ResultExamPhysicsOneStudent.this, "Hmmmm ! Có vẻ có lỗi xảy ra !\n Liên hệ nhóm phát triển để khắc phục kịp thời !", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ResultExamPhysicsOneStudent.this, "Hmmmm ! Có vẻ có lỗi xảy ra !\n Liên hệ nhóm phát triển để khắc phục kịp thời !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void detailResultExamPhysicsOneStudent() {
        showResultExamPhysicsOneStudent = "";
        pointResultExamPhysicsOneStudent = 0;
        referenceStudent.child(studentID).child("PhysicsOne")
                .child(examCodeResultExam).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int questionCount = 1; questionCount <= 10; questionCount++) {
                    final String yourAnswer = String.valueOf(snapshot.child("Ques" + questionCount).getValue());
                    referenceRightAnswer = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
                    referenceExam = referenceRightAnswer.child(examCodeResultExam);
                    final int finalQuestionCount = questionCount;
                    referenceExam.child("Ques" + questionCount).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String contentQuestion = String.valueOf(snapshot.child("Content").getValue());
                            String optionA = String.valueOf(snapshot.child("OptionA").getValue());
                            String optionB = String.valueOf(snapshot.child("OptionB").getValue());
                            String optionC = String.valueOf(snapshot.child("OptionC").getValue());
                            String optionD = String.valueOf(snapshot.child("OptionD").getValue());
                            String rightAnswer = String.valueOf(snapshot.child("RightAns").getValue());
                            showResultExamPhysicsOneStudent = showResultExamPhysicsOneStudent + contentQuestion + "\nA: " + optionA + "\nB: " + optionB + "\nC: " + optionC + "\nD: " + optionD + "\n\uD835\uDC01\uD835\uDC1Ạ\uD835\uDC27 \uD835\uDC1C\uD835\uDC21\uD835\uDC28̣\uD835\uDC27: " + yourAnswer + "     Đ\uD835\uDC1Á\uD835\uDC29 \uD835\uDC1Á\uD835\uDC27 đ\uD835\uDC2É\uD835\uDC27\uD835\uDC20: " + rightAnswer + "\n\n\n\n";
                            txtShowResultExamPhysicsOneStudent.setText(showResultExamPhysicsOneStudent + "Xem đáp án và cải thiện kỹ năng của bạn !");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ResultExamPhysicsOneStudent.this, "Hmmmm ! Có vẻ có lỗi xảy ra !\n Liên hệ nhóm phát triển để khắc phục kịp thời !", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ResultExamPhysicsOneStudent.this, "Hmmmm ! Có vẻ có lỗi xảy ra !\n Liên hệ nhóm phát triển để khắc phục kịp thời !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void resultExamPhysicsOneStudent() {
        showResultExamPhysicsOneStudent = "Bài làm của bạn:\n";
        pointResultExamPhysicsOneStudent = 0;
        referenceStudent.child(studentID).child("PhysicsOne").child(examCodeResultExam).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int questionCount = 1; questionCount <= 10; questionCount++) {
                    final String yourAnswer = String.valueOf(snapshot.child("Ques" + questionCount).getValue());
                    referenceRightAnswer = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
                    referenceExam = referenceRightAnswer.child(examCodeResultExam);
                    final int finalQuestionCount = questionCount;
                    referenceExam.child("Ques" + questionCount).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String rightAnswer = String.valueOf(snapshot.child("RightAns").getValue());
                            if (yourAnswer.equals(rightAnswer)) {
                                pointResultExamPhysicsOneStudent++;
                            }

                            showResultExamPhysicsOneStudent = showResultExamPhysicsOneStudent + "\nCâu " + finalQuestionCount + ":     Bạn chọn: " + yourAnswer + "     Đáp án đúng: " + rightAnswer + "\n_____________________________________\n";
                            txtShowResultExamPhysicsOneStudent.setText(showResultExamPhysicsOneStudent + "\nHoàn thành !");
                            txtPointResultExamPhysicsOneStudent.setText("Điểm của bạn: " + pointResultExamPhysicsOneStudent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ResultExamPhysicsOneStudent.this, "Hmmmm ! Có vẻ có lỗi xảy ra !\n Liên hệ nhóm phát triển để khắc phục kịp thời !", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ResultExamPhysicsOneStudent.this, "Hmmmm ! Có vẻ có lỗi xảy ra !\n Liên hệ nhóm phát triển để khắc phục kịp thời !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}