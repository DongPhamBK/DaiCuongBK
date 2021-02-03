package com.project.daicuongbachkhoa.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Student;


public class RegisterUser extends AppCompatActivity {

    private TextView
            txtSignIn,
            txtRegisterTeacher;
    private EditText
            txtNameRegister,
            txtIdRegister,
            txtEmailRegister,
            txtPasswordRegister,
            txtPasswordConfirm;
    private Button
            btnRegister;
    private ProgressBar
            progressBarRegister;
    private FirebaseAuth
            mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        txtSignIn = findViewById(R.id.txtSignIn);
        txtNameRegister = findViewById(R.id.txtNameRegister);
        txtIdRegister = findViewById(R.id.txtIdRegister);
        txtEmailRegister = findViewById(R.id.txtEmailRegister);
        txtPasswordRegister = findViewById(R.id.txtPasswordRegister);
        txtPasswordConfirm = findViewById(R.id.txtPasswordConfirm);
        btnRegister = findViewById(R.id.btnRegister);
        txtRegisterTeacher = findViewById(R.id.txtRegisterTeacher);
        progressBarRegister = findViewById(R.id.progressBarRegister);
        mAuth = FirebaseAuth.getInstance();

        txtRegisterTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerTeacherAccount();
            }
        });
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAccount();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
    }

    //register if you are teacher
    private void registerTeacherAccount() {
        startActivity(new Intent(RegisterUser.this, RegisterTeacher.class));
    }

    //sign in
    private void signInAccount() {
        Intent intent = new Intent(RegisterUser.this, LoginUser.class);
        startActivity(intent);
        finish();
    }

    //register
    private void registerAccount() {
        final String userNameRegister = txtNameRegister.getText().toString().trim();// chấm trim() để định dạng dữ liệu
        final String idUserRegister = txtIdRegister.getText().toString().trim();
        final String emailRegister = txtEmailRegister.getText().toString().trim();
        final String passwordRegister = txtPasswordRegister.getText().toString().trim();
        String passwordVerification = txtPasswordConfirm.getText().toString().trim();

        //start process register
        btnRegister.setText("ĐĂNG KÝ");
        btnRegister.setTextColor(Color.WHITE);
        progressBarRegister.setVisibility(View.VISIBLE);
        btnRegister.setEnabled(false);

        // treat exception
        if (userNameRegister.isEmpty()) {
            txtNameRegister.setError("Tên không được để trống !");
            txtNameRegister.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;

        }
        if (idUserRegister.isEmpty()) {
            txtIdRegister.setError("Mã số sinh viên không để trống !");
            txtIdRegister.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }
        if (emailRegister.isEmpty()) {
            txtEmailRegister.setError("Gmail không được để trống !");
            txtEmailRegister.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailRegister).matches()) {
            // check mail
            txtEmailRegister.setError("Gmail không hợp lệ !");
            txtEmailRegister.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }
        if (!emailRegister.contains("@gmail.com")) {
            txtEmailRegister.setError("Gmail không hợp lệ !");
            txtEmailRegister.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }
        if (passwordRegister.isEmpty()) {
            txtPasswordRegister.setError("Mật khẩu không được để trống !");
            txtPasswordRegister.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }
        if (passwordVerification.isEmpty()) {
            txtPasswordConfirm.setError("Vui lòng xác thực mật khẩu !");
            txtPasswordConfirm.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }
        if (passwordRegister.length() < 6) {
            txtPasswordRegister.setError("Mật khẩu không nhỏ hơn 6 kí tự !");
            txtPasswordRegister.requestFocus();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }
        if (!passwordRegister.equals(passwordVerification)) {
            Toast.makeText(RegisterUser.this, "Mật khẩu không khớp nhau !", Toast.LENGTH_SHORT).show();
            progressBarRegister.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            return;
        }

        //create account
        mAuth.createUserWithEmailAndPassword(emailRegister, passwordRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Student student = new Student(userNameRegister, idUserRegister, emailRegister, passwordRegister);
                            FirebaseDatabase.getInstance().getReference("Students")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "Đăng ký thành công !Vui lòng \nchọn đăng nhập để tiếp tục !", Toast.LENGTH_LONG).show();
                                        progressBarRegister.setVisibility(View.GONE);
                                        btnRegister.setText("Thành Công");
                                        btnRegister.setTextColor(Color.CYAN);
                                        FirebaseAuth.getInstance().signOut();
                                    } else {
                                        Toast.makeText(RegisterUser.this, "Chưa được, thử lại đi !", Toast.LENGTH_LONG).show();
                                        progressBarRegister.setVisibility(View.GONE);
                                        btnRegister.setText("Thất bại");
                                        txtNameRegister.setText("");
                                        txtIdRegister.setText("");
                                        txtEmailRegister.setText("");
                                        btnRegister.setEnabled(true);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterUser.this, "Ôi hỏng, có vẻ email sai hoặc đã được dùng !", Toast.LENGTH_LONG).show();
                            progressBarRegister.setVisibility(View.GONE);
                            btnRegister.setText("ĐĂNG KÝ LẠI");
                            btnRegister.setEnabled(true);
                            txtEmailRegister.setText("");
                        }
                    }
                });
    }
}