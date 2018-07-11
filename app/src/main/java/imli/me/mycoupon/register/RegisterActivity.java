package imli.me.mycoupon.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import imli.me.mycoupon.R;
import imli.me.mycoupon.data.Student;
import imli.me.mycoupon.db.StudentDAO;
import imli.me.mycoupon.login.LoginActivity;


public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnRegister;
    private Button btnGoLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册");

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        btnGoLogin = findViewById(R.id.btn_go_login);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        });

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    /**
     * 注册
     */
    private void register() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
        }

        // 先判断改用户信息是否已经被注册
        StudentDAO studentDAO = new StudentDAO(this);
        Student student = studentDAO.find(username);
        if (student != null) {
            Toast.makeText(this, "该用户名已经被注册！", Toast.LENGTH_SHORT).show();
        } else {
            student = new Student(username, password, (short) 1);
            studentDAO.add(student);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

            // 注册成功后，跳转登录页面让用户进行登录
            goLogin();
        }
    }

    /**
     * 去登录
     */
    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
