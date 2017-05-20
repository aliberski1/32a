package wizut.bukmacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityMeczu extends AppCompatActivity {

    EditText data, druzyna, stawka;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        data = (EditText) findViewById(R.id.id_data);
        druzyna = (EditText) findViewById(R.id.id_druzyny);
        stawka = (EditText) findViewById(R.id.id_stawka);
        btn = (Button) findViewById(R.id.btn_bet);
        Mecz mecz = new Mecz();
        data.setText(mecz.getData());
        Druzyna team = new Druzyna();
        druzyna.setText(mecz.getId_gospodarzy() + " VS " + mecz.getId_gosci());
        //Stawka staweczka = new Stawka;
        //stawka.setText(stawka.getStawka());
        //btn.callOnClick()
    }


}
