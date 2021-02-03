package com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Exam;

public class AdapterExamPhysicsOneStudent extends FirebaseRecyclerAdapter<Exam, AdapterExamPhysicsOneStudent.ExamViewHolder> {

    /*private FirebaseUser
            student;
    private DatabaseReference
            reference,
            referenceStudent;
    private DatabaseReference
            listPhysicsOne,
            answerExamReset,
            answerPhysicsOne;
    private ValueEventListener
            referenceExam;*/

    public AdapterExamPhysicsOneStudent(@NonNull FirebaseRecyclerOptions<Exam> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExamViewHolder holder, int position, @NonNull final Exam exam) {
        holder.btnNumberExamPhysicsOneStudent.setText("Đề số " + exam.getExamCode().charAt(exam.getExamCode().length() - 2) + exam.getExamCode().charAt(exam.getExamCode().length() - 1));
        holder.txtNameExamPhysicsOneStudent.setText(exam.getExamName());
        holder.txtTeacherExamPhysicsOneStudent.setText("Giáo viên: " + exam.getExamTeacher());
        holder.txtDeadlineExamPhysicsOneStudent.setText("Thời hạn: " + exam.getExamDeadline());
        holder.btnNumberExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExamPhysicsOneStudent(exam, view);
            }
        });
    }

    private void startExamPhysicsOneStudent(final Exam exam, View view) {
        final AppCompatActivity activity = (AppCompatActivity) view.getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Hệ thống sẽ bắt đầu tính thời gian thi.\nBạn đã sẵn sàng ?")
                .setCancelable(false)
                .setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(activity, ExamPhysicsOneStudent.class);
                        intent.putExtra("NAME_EXAM_PHYSICS_ONE_STUDENT", exam.getExamName());
                        intent.putExtra("CODE_EXAM_PHYSICS_ONE_STUDENT", exam.getExamCode());
                        activity.startActivity(intent);
                    }
                })
                .setNegativeButton("THOÁT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_exam_student, parent, false);
        return new ExamViewHolder(view);
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {

        Button
                btnNumberExamPhysicsOneStudent;
        TextView
                txtNameExamPhysicsOneStudent,
                txtTeacherExamPhysicsOneStudent,
                txtDeadlineExamPhysicsOneStudent;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            btnNumberExamPhysicsOneStudent = itemView.findViewById(R.id.btnNumberExamStudent);
            txtNameExamPhysicsOneStudent = itemView.findViewById(R.id.txtNameExamStudent);
            txtTeacherExamPhysicsOneStudent = itemView.findViewById(R.id.txtTeacherExamStudent);
            txtDeadlineExamPhysicsOneStudent = itemView.findViewById(R.id.txtDeadlineExamStudent);
        }
    }
}
