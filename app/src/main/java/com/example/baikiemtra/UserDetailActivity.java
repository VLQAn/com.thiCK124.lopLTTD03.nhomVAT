package com.example.baikiemtra;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetailActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone;
    private Button btnSave, btnDelete;

    private DatabaseReference databaseReference;
    private String userId; // Dùng để xác định user trong Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        databaseReference = FirebaseDatabase.getInstance().getReference("users1");

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
            etName.setText(intent.getStringExtra("name"));
            etEmail.setText(intent.getStringExtra("email"));
            etPhone.setText(intent.getStringExtra("phone"));
        }

        // Lưu thông tin
        btnSave.setOnClickListener(v -> saveUserInfo());

        // Xóa thông tin
        btnDelete.setOnClickListener(v -> deleteUser());
    }

    private void saveUserInfo() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(name, email, phone);

        // Cập nhật hoặc thêm mới
        if (userId == null) {
            // Thêm mới
            userId = databaseReference.push().getKey();
        }
        databaseReference.child(userId).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Thông tin đã được lưu", Toast.LENGTH_SHORT).show();
                        finish(); // Quay về màn hình trước đó
                    } else {
                        Toast.makeText(this, "Lỗi khi lưu thông tin", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteUser() {
        if (userId != null) {
            databaseReference.child(userId).removeValue()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Thông tin đã được xóa", Toast.LENGTH_SHORT).show();
                            finish(); // Quay về màn hình trước đó
                        } else {
                            Toast.makeText(this, "Lỗi khi xóa thông tin", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
