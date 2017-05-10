package wizut.bukmacher;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class API_Connect extends AppCompatActivity{
    ProgressBar progressBar;
    TextView responseView;
    EditText emailText;
    static final String API_KEY = "API_KEY";
    static final String API_URL = "https://api.trololololo";


/*class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    private Exception exception;

    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        responseView.setText("");
    }

    protected String doInBackground(Void... urls) {
        String email = emailText.getText().toString();

        try {
            URL url = new URL(API_URL + "email=" + email + "&apiKey=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "ERROR";
        }
        progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        responseView.setText(response);


//            try {
//                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
//                JSONArray photos = object.getJSONArray("photos");
//                .
//                .
//                .
//                .
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
    }
}
*/
    protected String getTeams(String API_Key,int league_id){
        //lista druzyn w danej lidze
        String teams="";
        return teams;
    }
    protected String discipline_getResults(String API_Key,int discipline_id){
        //wyniki meczow w danej dyscyplinie
        String disc_results="";
        return disc_results;
    }
    protected String league_getResults(String API_Key,int league_id){
        //wyniki meczow w danej lidze
        String league_results="";
        return league_results;
    }
    protected String team_getResults(String API_Key,int team_id){
        //wyniki meczow danej druzyny
        String team_results="";
        return team_results;
    }
    protected String getEvents(String API_Key){
        //mecze do obstawienia
        String events="";
        return events;
    }
    protected String getMarkets(String API_Key,int event_id){
        //stawki
        String markets="";
        return markets;
    }
}