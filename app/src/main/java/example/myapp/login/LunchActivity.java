package example.myapp.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.myapp.R;

public class LunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.WelcomeTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent i = new Intent(LunchActivity.this, LoginActivity.class);
               startActivity(i);
               finish();
           }
       }, 2000);

    }
}
