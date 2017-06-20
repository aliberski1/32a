package wizut.bukmacher;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfMatchActivity extends AppCompatActivity {

    String id;
    List arrayMain;
    ArrayAdapter arrayAdapter;
    ListView lv_list;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_match);

        id = getIntent().getStringExtra(HttpService.ID);
        arrayMain = new ArrayList<String>();

        Intent intent = new Intent(this, HttpService.class);
        PendingIntent pendingResult = createPendingResult(0, new Intent(),0);
        intent.putExtra(HttpService.RETURN, pendingResult);
        intent.putExtra(HttpService.ID, id);
        startService(intent);

        arrayAdapter = new ArrayAdapter(this, R.layout.list_element, R.id.lv_element, arrayMain);
        lv_list = (ListView) findViewById(R.id.lv_list_of_match);
        lv_list.setAdapter(arrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            JSONObject jsonObject = new JSONObject(data.getStringExtra(HttpService.RESPONSE));
            JSONArray jsonArray = jsonObject.getJSONArray("teams");
            for(int i=0; i < jsonArray.length();i++) {
                arrayMain.add(jsonArray.getJSONObject(i).getString("name"));
            }

            arrayAdapter.notifyDataSetChanged();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
