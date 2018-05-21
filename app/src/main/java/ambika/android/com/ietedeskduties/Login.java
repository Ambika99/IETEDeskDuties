package ambika.android.com.ietedeskduties;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends MainActivity {
    Button btnlogin;
    EditText etemail;
    EditText etpass;
    FirebaseAuth login;
    ProgressDialog progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        etemail = (EditText) findViewById(R.id.etemail);
        etpass = (EditText) findViewById(R.id.etpass);
        progress = new ProgressDialog(this);
        login = FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = etemail.getText().toString().trim();
                String password = etpass.getText().toString().trim();
                if(TextUtils.isEmpty(emailid)|| TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Invalid details", Toast.LENGTH_LONG).show();
                    return;

                }
                progress.setMessage("Logging In User....");
                progress.show();
                login.signInWithEmailAndPassword(emailid, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.dismiss();
                        if(task.isSuccessful()){
                            Intent intent1 = new Intent();
                            intent1.setClass(Login.this, first_page.class);
                            startActivity(intent1);
                        }
                        else{
                            Toast.makeText(Login.this , "Not Successful - Recheck Login Credentials" , Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });

    }
}