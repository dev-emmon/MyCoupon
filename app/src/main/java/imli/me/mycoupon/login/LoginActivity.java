package imli.me.mycoupon.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import imli.me.mycoupon.App;
import imli.me.mycoupon.data.Student;
import imli.me.mycoupon.db.StudentDAO;
import imli.me.mycoupon.main.MainActivity;
import imli.me.mycoupon.R;
import imli.me.mycoupon.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnGoRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnGoRegister = findViewById(R.id.btn_go_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goRegister();
            }
        });



    }

    /**
     * 登录
     */
    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        StudentDAO studentDAO = new StudentDAO(this);
        Student student = studentDAO.find(username);
        if (student == null) {
            Toast.makeText(this, "不存在该用户！", Toast.LENGTH_SHORT).show();
        } else if (!student.password.equals(password)) {
            Toast.makeText(this, "密码错误，请输入正确的密码！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            App app = (App) getApplication();
            app.setStudent(student);

        }
    }

    /**
     * 去注册
     */
    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
