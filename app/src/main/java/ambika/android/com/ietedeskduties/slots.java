package ambika.android.com.ietedeskduties;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class slots extends AppCompatActivity {
    Button btnsave;
    RadioGroup slots;
    RadioButton nine;
    RadioButton ten;
    RadioButton eleven;
    RadioButton two;
    RadioButton three;
    RadioButton four;
    RadioButton five;
    String slot;
    FirebaseDatabase database;
    String id;
    FirebaseUser user;
    String name;
    String day;
    DatabaseReference myref;
    DatabaseReference ref;
    int value=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle b = getIntent().getExtras();
        day = b.getString("day");
        name = b.getString("name");
        slots = (RadioGroup) findViewById(R.id.slots);
        nine = (RadioButton) findViewById(R.id.nine);
        ten = (RadioButton) findViewById(R.id.ten);
        eleven = (RadioButton) findViewById(R.id.eleven);
        two = (RadioButton) findViewById(R.id.two);
        three = (RadioButton) findViewById(R.id.three);
         four = (RadioButton) findViewById(R.id.four);
        five = (RadioButton) findViewById(R.id.five);
        user = FirebaseAuth.getInstance().getCurrentUser();
        id = user.getUid();
        database = FirebaseDatabase.getInstance();
        myref = database.getReferenceFromUrl("https://iete-desk-duties-771f2.firebaseio.com/").child(id);
        ref = database.getReferenceFromUrl("https://iete-desk-duties-771f2.firebaseio.com/");

        slots.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.nine:
                        slot = "Nine";
                        break;
                    case R.id.ten:
                        slot = "Ten";
                    break;
                    case R.id.eleven:
                        slot = "Eleven";
                        break;
                    case R.id.two:
                        slot = "Two";
                        break;
                    case R.id.three:
                        slot = "Three";
                        break;
                    case R.id.four:
                        slot = "Four";
                        break;
                    case R.id.five:
                        slot = "Five";
                        break;

                }
                ref.child(day).child(slot).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        value = dataSnapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        btnsave = (Button) findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(value<2) {

                      myref.child(day).setValue(slot);
                      ref.child(day).child(slot).setValue(++value);

                      Intent intent = new Intent();
                      intent.setClass(slots.this, save.class);
                      startActivity(intent);
                      Toast.makeText(slots.this, "Slot Saved", Toast.LENGTH_LONG).show();
                  }
                  else{
                      Toast.makeText(slots.this,"This Slot is Full",Toast.LENGTH_LONG).show();
                  }
            }
        });


    }

}