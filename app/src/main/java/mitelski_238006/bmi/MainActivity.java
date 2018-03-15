package mitelski_238006.bmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_RESULT = "238006.RESULT";
    public static final String SHARED_BMI = "238006.BMI";
    private boolean isGBChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        restoreInputs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.save_button:
                saveInputs();
                return true;
            case R.id.about_author:
                startActivity(new Intent(this, AboutAuthorActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void countBMI(View view) {
        Switch switchGB = findViewById(R.id.switch_gb);

        EditText mass = findViewById(R.id.mass);
        EditText height = findViewById(R.id.height);

        String mass_text = mass.getText().toString();
        String height_text = height.getText().toString();

        if (mass_text.length() == 0) {
            mass.setError(getText(R.string.LENGTH_ERR));
        }
        if (height_text.length() == 0) {
            height.setError(getText(R.string.LENGTH_ERR));
        }

        if (mass.getError() == null && height.getError() == null) {
            BMI bmi;

            if(switchGB.isChecked())
                bmi = new BmiForLbIn(Double.valueOf(mass_text), Double.valueOf(height_text));
            else
                bmi = new BmiForKgM(Double.valueOf(mass_text), Double.valueOf(height_text));

            Double result = bmi.calculateBmi();

            System.out.println(result);

            Intent intent = new Intent(MainActivity.this, DisplayResultActivity.class);
            intent.putExtra(EXTRA_RESULT, result);
            startActivity(intent);
        }
    }

    private void restoreInputs()
    {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(SHARED_BMI, 0);
        String mass_text = settings.getString("mass", null);
        String height_text = settings.getString("height", null);
        isGBChecked = settings.getBoolean("isGBChecked", false);

        if (mass_text != null) {
            EditText mass = findViewById(R.id.mass);
            mass.setText(mass_text);
        }
        if (height_text != null) {
            EditText height = findViewById(R.id.height);
            height.setText(height_text);
        }
    }

    public void handleSwitch (View view) {
        Switch switchGB = findViewById(R.id.switch_gb);

        TextView mass = findViewById(R.id.mass_label);
        TextView height = findViewById(R.id.height_label);

        if(switchGB.isChecked()) {
            mass.setText(R.string.mass_lb);
            height.setText(R.string.height_in);
            isGBChecked = true;
        } else {
            mass.setText(R.string.mass_kg);
            height.setText(R.string.height_m);
            isGBChecked = false;
        }
    }

    private void saveInputs()
    {
        EditText mass = findViewById(R.id.mass);
        EditText height = findViewById(R.id.height);

        SharedPreferences settings = getApplicationContext().getSharedPreferences(SHARED_BMI,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("mass", mass.getText().toString());
        editor.putString("height", height.getText().toString());
        editor.putBoolean("isGBChecked", isGBChecked);

        Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_SHORT).show();
        editor.apply();
    }

}


