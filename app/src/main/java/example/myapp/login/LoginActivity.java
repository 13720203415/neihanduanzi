package example.myapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import example.myapp.R;
import example.myapp.xutils.CheckNet;
import example.myapp.xutils.OnClick;
import example.myapp.xutils.ViewById;
import example.myapp.xutils.XUtils;

public class LoginActivity extends AppCompatActivity {

    @ViewById(R.id.name)
    EditText name;
    @ViewById(R.id.ps)
    private EditText password;
    @ViewById(R.id.login)
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        XUtils.inject(this);
    }

    private void initView() {
//        name = findViewById(R.id.name);
//        password = findViewById(R.id.ps);
//        login = findViewById(R.id.login);

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(name.getText().toString().equals("123") && password.getText().toString().equals("1")) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
    }
    @OnClick(R.id.login)
    private void login() {
        if(name.getText().toString().equals("123") && password.getText().toString().equals("1")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
