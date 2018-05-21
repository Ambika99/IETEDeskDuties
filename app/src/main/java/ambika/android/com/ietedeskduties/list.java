package ambika.android.com.ietedeskduties;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class list extends MainActivity {
   Button btnslot;
   RadioGroup radioGroup;
   RadioButton rmonday;
   RadioButton rtuesday;
   RadioButton rwednesday;
   RadioButton rthursday;
   RadioButton rfriday;
   String day;
   String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rmonday = (RadioButton) findViewById(R.id.rmonday);
        rtuesday = (RadioButton) findViewById(R.id.rtuesday);
        rwednesday = (RadioButton) findViewById(R.id.rwednesday);
        rthursday = (RadioButton) findViewById(R.id.rthursday);
        rfriday = (RadioButton) findViewById(R.id.rfriday);
        radioGroup = (RadioGroup) findViewById(R.id.rgroupdays);
        btnslot = (Button) findViewById(R.id.btnslot);
        Bundle b  = getIntent().getExtras();
        name = b.getString("day");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rmonday:
                        day = "Monday";
                        break;
                    case R.id.rtuesday:
                        day = "Tuesday";
                        break;
                    case R.id.rwednesday:
                        day = "Wednesday";
                        break;
                    case R.id.rthursday:
                        day = "Thursday";
                        break;
                    case R.id.rfriday:
                        day = "Friday";
                        break;
                }
            }
        });
        btnslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("name",name);
                intent1.putExtra("day", day);
                intent1.setClass(list.this , slots.class);
                startActivity(intent1);


            }
        });




    }

}
