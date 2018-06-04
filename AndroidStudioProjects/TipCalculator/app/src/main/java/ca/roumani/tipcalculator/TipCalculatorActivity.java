package ca.roumani.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * @author Arian Seyedi
 */
public class TipCalculatorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
    }

    public void buttonClicked(View view) {
        // get the amount
        View amountView = findViewById(R.id.checkAmount);
        EditText amountEdit = (EditText) amountView;
        String amount = amountEdit.getText().toString();

        // get the number of people
        View numOfPeopleView = findViewById(R.id.numberOfPeople);
        EditText numOfPeopleEdit = (EditText) numOfPeopleView;
        String numOfPeople = numOfPeopleEdit.getText().toString();

        // get the tip
        RadioButton tenTipBtn;
        RadioButton twentyTipBtn;
        RadioButton fifteenTipBtn;
        String tip = "15";

        tenTipBtn = (RadioButton) findViewById(R.id.tenTipRadBtn);
        twentyTipBtn = (RadioButton) findViewById(R.id.twentyTipRadBtn);
        fifteenTipBtn = (RadioButton) findViewById(R.id.fifteenTipRadBtn);

        if (tenTipBtn.isChecked()) {
            tip = "10";
        } else if (fifteenTipBtn.isChecked()) {
            tip = "15";
        } else if (twentyTipBtn.isChecked()) {
            tip = "20";
        }

        // create a model
        TipCalculatorModel model = new TipCalculatorModel(amount, tip, numOfPeople);

        // get the index from the model
        String amountText = model.getTotal();
        String total = "Total =  " + amountText;

        // set the amount to display
        View totalView = findViewById(R.id.total);
        TextView totalTextView = (TextView) totalView;
        totalTextView.setText(total);

        String perPersonText = model.getPerPerson();
        String perPerson = "Per Person =  " + perPersonText;

        // set per person amount to display
        View perPersonView = findViewById(R.id.perPerson);
        TextView perPersonTextView = (TextView) perPersonView;
        perPersonTextView.setText(perPerson);
    }
}


