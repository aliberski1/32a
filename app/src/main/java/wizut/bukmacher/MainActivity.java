package wizut.bukmacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_main;
    List arrayDisciplines;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayDisciplines = new ArrayList<>();
        arrayDisciplines.add("Login");
        arrayDisciplines.add("Pulpit");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_element, R.id.lv_element, arrayDisciplines);

        lv_main = (ListView) findViewById(R.id.lv_main);
        lv_main.setAdapter(arrayAdapter);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        intent = new Intent(getApplicationContext(), WDLogin.class);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), WDNavigationDrawer.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
