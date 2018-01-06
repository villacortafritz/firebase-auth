package fritzgwapo.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SignIn extends Activity implements View.OnClickListener {

    private Button btnIn;
    private EditText txtEmail, txtPass;
    private FirebaseAuth a;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPassword);
        btnIn = (Button) findViewById(R.id.btnSignIn);
        btnIn.setOnClickListener(this);

        a = FirebaseAuth.getInstance();
        load = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        LogInUser();
    }

    public void LogInUser() {
        String hello = txtEmail.getText( ).toString().trim();
        String world = txtPass.getText().toString().trim();
        boolean flag = false;

        if (TextUtils.isEmpty(hello)) {
            Toast.makeText(this, "Please enter valid email.", Toast.LENGTH_SHORT).show();
            txtEmail.setText("");
            txtPass.setText("");
            return;
        } else {
            if (TextUtils.isEmpty(world)) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                txtEmail.setText("");
                txtPass.setText("");
                return;
            } else {
                flag = true;
            }
        }

        //if the email and password are not empty
        //displaying a progress dialog
        if (flag == true) {
            load.setMessage("Loggin in. Please Wait...");
            load.show();

            a.signInWithEmailAndPassword(hello, world)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            load.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(SignIn.this, "Successfully registered", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(SignIn.this, LandingPage.class);
                                startActivity(i);
                            }
                            else{
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                Toast.makeText(SignIn.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
        }

    }
}
