package wizut.bukmacher;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpService extends IntentService {
    public static final String RETURN = "Return";
    public static final String RESPONSE = "Response";

    //Konstruktor
    public HttpService() {
        super("HTTP calls handler");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {

            //Tworzenie obiektu url ze stringa
            URL url = new URL("http://api.football-data.org/v1/competitions");

            //Przygotowanie polaczenia
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            //wysłanie żądania
            conn.connect();

            //Dostanie odpowiedzi HTTP
            int responseCode = conn.getResponseCode();


            //Odebranie odpowiedzi tylko kiedy polaczenie jest OK
            String response = "";
            if(responseCode==200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //Convert response to single string
                String line;
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
                reader.close();
            }

            //Zamkniecie polaczenia
            conn.disconnect();

            //Dodanie odpowiedzi
            Intent returns = new Intent();
            returns.putExtra(HttpService.RESPONSE, response);
            PendingIntent reply = intent.getParcelableExtra(HttpService.RETURN);
            reply.send(getApplicationContext(), responseCode, returns);

        }catch (Exception ex){

            Log.d("CONNERROR", ex.toString());
        }
    }
}
