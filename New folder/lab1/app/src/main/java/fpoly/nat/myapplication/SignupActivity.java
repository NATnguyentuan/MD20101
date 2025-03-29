package fpoly.nat.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText email, password;
    private Button btnsignup;
    private TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Khởi tạo các view
        email = findViewById(R.id.edemail);
        password = findViewById(R.id.edpassword);
        btnsignup = findViewById(R.id.btnsignup);
        txtLogin = findViewById(R.id.txtLogin);

        // Khởi tạo Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Sự kiện nhấn nút đăng ký
        btnsignup.setOnClickListener(v -> {
            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            // Kiểm tra xem người dùng đã nhập đầy đủ chưa
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
                return; // Dừng việc đăng ký nếu thông tin không đầy đủ
            }

            // Đăng ký với email và mật khẩu
            mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Đăng ký thành công
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                // Chuyển hướng đến màn hình đăng nhập
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish(); // Kết thúc màn hình đăng ký để không quay lại được
                            } else {
                                // Đăng ký thất bại
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignupActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        // Sự kiện nhấn vào txtLogin để chuyển đến màn hình đăng nhập
        txtLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        });
    }
}
