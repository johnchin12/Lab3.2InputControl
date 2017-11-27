package my.edu.tarc.lab32inputcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerAge;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;
    private double basicPremium, extraMale, total, extraSmoker;
    private RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        spinnerAge.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.age,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapter);

        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);
        radioGroupGender = (RadioGroup)findViewById(R.id.radiogroupGender);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TODO: Calculate basic premium according to age group
        switch(position){
            case 0:
                basicPremium = 50;
                break;
            case 1:
                basicPremium = 55;
                break;
            case 2:
                basicPremium = 60;
                break;
            case 3:
                basicPremium = 70;
                break;
            case 4:
                basicPremium = 120;
                break;
            case 5:
                basicPremium = 160;
                break;
            case 6:
                basicPremium = 200;
                break;
            case 7:
                basicPremium = 250;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calPremium(View v){
        int gender = radioGroupGender.getCheckedRadioButtonId();
        boolean smoker = checkBoxSmoker.isChecked();

        if(gender == R.id.radioButtonMale){
            if(basicPremium==60 || basicPremium==160)
                extraMale = 50;
            else if(basicPremium==70 || basicPremium==120)
                extraMale = 100;
            else
                extraMale = 0;
        }

        else if(smoker){
            if(basicPremium == 70)
                extraSmoker = 100;
            else if (basicPremium == 120 || basicPremium==160)
                extraSmoker = 150;
            else if(basicPremium == 200 || basicPremium == 250)
                extraSmoker = 250;
            else
                extraSmoker = 0;
        }

        else{
            extraMale = 0;
            extraSmoker = 0;
        }

        total = basicPremium + extraMale + extraSmoker;

        textViewPremium.setText("Premium: " + total);
    }
}
