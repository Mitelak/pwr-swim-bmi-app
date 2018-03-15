package mitelski_238006.bmi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DisplayResultActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        Toolbar toolbar = findViewById(R.id.result_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        analyzeResult();

    }

    public void analyzeResult() {
        Intent intent = getIntent();
        Double bmi_value = intent.getDoubleExtra(MainActivity.EXTRA_RESULT, 0);

        AnalyzeBMI analyze = new AnalyzeBMI(bmi_value);
        AnalyzeBMI.BMICategory category = analyze.getCategory();

        ConstraintLayout layout = findViewById(R.id.result_activity);

        if (category == AnalyzeBMI.BMICategory.BAD)
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.bmiBad));

        if (category == AnalyzeBMI.BMICategory.TILT)
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.bmiTilt));

        if (category == AnalyzeBMI.BMICategory.GOOD)
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.bmiGood));

        TextView result_output = findViewById(R.id.bmi_result);
        result_output.setText(String.format("%.2f", bmi_value));
    }
}
