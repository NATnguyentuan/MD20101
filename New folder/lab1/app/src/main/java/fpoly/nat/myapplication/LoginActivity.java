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

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText email, password;
    private Button btnLogin;
    private TextView txtSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo các view
        email = findViewById(R.id.edemailLg);
        password = findViewById(R.id.edpasswordLg);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignup = findViewById(R.id.txtSignup);

        // Khởi tạo Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Sự kiện nhấn nút đăng nhập
        btnLogin.setOnClickListener(v -> {
            // Lấy giá trị email và password
            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            // Kiểm tra xem người dùng đã nhập đầy đủ chưa
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
                return; // Dừng việc đăng nhập nếu thông tin không đầy đủ
            }

            // Đăng nhập với email và password
            mAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Đăng nhập thành công
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                // Chuyển hướng đến màn hình chính
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish(); // Kết thúc màn hình đăng nhập để không quay lại được
                            } else {
                                // Đăng nhập thất bại
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        // Sự kiện nhấn vào txtSignup để chuyển đến màn hình đăng ký
        txtSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }
}
