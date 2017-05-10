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

public class Login extends AppCompatActivity {

    EditText name, surName, login;
    Button btn;
    String PREFS_NAME = "MyPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        name = (EditText) findViewById(R.id.et_name);
        surName = (EditText) findViewById(R.id.et_surname);
        login = (EditText) findViewById(R.id.et_login);
        btn = (Button) findViewById(R.id.btn_login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                if(name.getText().toString().length() == 0) {
                    name.setError("Enter your first name");
                } else {
                    name.setError(null);
                    count++;
                }
                if(surName.getText().toString().length() == 0) {
                    surName.setError("Enter your surname");
                } else {
                    surName.setError(null);
                    count++;
                }
                if(login.getText().toString().length() == 0) {
                    login.setError("Enter your login");
                } else {
                    login.setError(null);
                    count++;
                }

                if(count > 2) {
                    settings.edit().putString("name", name.getText().toString()).commit();
                    settings.edit().putString("surname", surName.getText().toString()).commit();
                    settings.edit().putString("login", login.getText().toString()).commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
