package ambika.android.com.ietedeskduties;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends MainActivity{
    FirebaseAuth newuser;
    EditText etemail;
    EditText etpass;
    Button btnsignup;
    ProgressDialog progress;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etemail = (EditText) findViewById(R.id.etemail);
        etpass = (EditText) findViewById(R.id.etpass);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        progress = new ProgressDialog(this);
        newuser = FirebaseAuth.getInstance();
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = etemail.getText().toString().trim();
                String password = etpass.getText().toString().trim();
                if(TextUtils.isEmpty(emailid)|| TextUtils.isEmpty(password)){
                    Toast.makeText(SecondActivity.this, "Invalid details", Toast.LENGTH_LONG).show();
                    return;

                }
                progress.setMessage("Registering User....");
                progress.show();
                newuser.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(SecondActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(SecondActivity.this , "Successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(SecondActivity.this , "Not Successfull - Check Validity of Email and Password " , Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent3 = new Intent();
            intent3.setClass(this , MainActivity.class);
            startActivity(intent3);

        }

        return super.onOptionsItemSelected(item);
    }
}
