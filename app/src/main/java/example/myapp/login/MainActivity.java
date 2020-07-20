package example.myapp.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import example.myapp.R;
import example.myapp.dialog.AlertDialog;
import example.myapp.navigationBar.DefaultNavigation;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setContentView(R.layout.activity_login)
//                .show();

        DefaultNavigation defaultNavigation = new DefaultNavigation.Builder(MainActivity.this, (ViewGroup) findViewById(R.id.main_a))
                .setTitle("2222")
                .setRightText("123")
                .setRightClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                    }
                })
                .builder();
    }

    public int a (String s) {
       if(s.equals(""))  {
           return 0;
       }

       int sum = 0;
       for(int i = 0; i< s.length(); i++) {
           switch (s.charAt(i)) {
               case 'I' :
                   sum = sum + 1;
                   break;
               case 'V' :
                   sum = sum + 5;
                   break;
               case 'X' :
                   sum = sum + 10;
                   break;
               case 'L' :
                   sum = sum + 50;
                   break;
               case 'C' :
                   sum = sum + 100;
                   break;
               case 'D' :
                   sum = sum + 500;
                   break;
               case 'M' :
                   sum = sum + 1000;
                   break;
           }

           if(i != 0) {
               if((s.charAt(i) == 'V' || s.charAt(i) == 'X') && s.charAt(i-1) == 'I') {
                   sum = sum - 2*1;
               }
               if((s.charAt(i) == 'L' || s.charAt(i) == 'C') && s.charAt(i-1) == 'X') {
                   sum = sum - 2*10;
               }
               if((s.charAt(i) == 'D' || s.charAt(i) == 'M') && s.charAt(i-1) == 'C') {
                   sum = sum - 2*100;
               }
           }
       }
       return sum;
    }
}
