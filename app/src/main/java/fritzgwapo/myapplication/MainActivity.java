package fritzgwapo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public class LogIn implements View.OnClickListener{
        public void onClick(View v){
            Intent i = new Intent(MainActivity.this, SignIn.class);
            startActivity(i);
        }
    }
    public class Register implements View.OnClickListener{
        public void onClick(View v){
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
    }

    private Button btnUp, btnIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIn = (Button)findViewById(R.id.btnIn);
        btnUp = (Button)findViewById(R.id.btnUp);
        btnIn.setOnClickListener(new LogIn());
        btnUp.setOnClickListener(new Register());

    }
}
