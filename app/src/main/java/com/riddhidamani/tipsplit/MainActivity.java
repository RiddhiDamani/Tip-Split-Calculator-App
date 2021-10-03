package com.riddhidamani.tipsplit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText billTotalValue;
    private EditText numOfPplTxtValue;

    private TextView totAmtPerPersonTxtValue;
    private TextView tipAmtTxtValue;
    private TextView totWithTipTxtValue;
    private TextView overageTxtValue;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotalValue = findViewById(R.id.billTotal);
        radioGroup = findViewById(R.id.tipPercentRG);
        tipAmtTxtValue = findViewById(R.id.tipAmtTxt);
        totWithTipTxtValue = findViewById(R.id.totWithTipTxt);
        numOfPplTxtValue = findViewById(R.id.numOfPplTxt);
        totAmtPerPersonTxtValue = findViewById(R.id.totAmtPerPersonTxt);
        overageTxtValue = findViewById(R.id.overageTxt);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString("billTotalValue", billTotalValue.getText().toString());
        outState.putString("tipAmtTxtValue", tipAmtTxtValue.getText().toString());
        outState.putString("totWithTipTxtValue", totWithTipTxtValue.getText().toString());
        outState.putString("numOfPplTxtValue", numOfPplTxtValue.getText().toString());
        outState.putString("totAmtPerPersonTxtValue", totAmtPerPersonTxtValue.getText().toString());
        outState.putString("overageTxtValue", overageTxtValue.getText().toString());

        // Call super last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        billTotalValue.setText(savedInstanceState.getString("billTotalValue"));
        tipAmtTxtValue.setText(savedInstanceState.getString("tipAmtTxtValue"));
        totWithTipTxtValue.setText(savedInstanceState.getString("totWithTipTxtValue"));
        numOfPplTxtValue.setText(savedInstanceState.getString("numOfPplTxtValue"));
        totAmtPerPersonTxtValue.setText(savedInstanceState.getString("totAmtPerPersonTxtValue"));
        overageTxtValue.setText(savedInstanceState.getString("overageTxtValue"));
    }

    public void calculateTipTotal(View v) {

        NumberFormat currFormat = NumberFormat.getCurrencyInstance();
        String billTot = billTotalValue.getText().toString();
        DecimalFormat f = new DecimalFormat("##.00");
        double tipAmount = 0.0;
        double totalAmount = 0.0;

        if(v.getId() == R.id.rb1) {
            tipAmount = Double.parseDouble(f.format((Double.parseDouble(billTot)) * 0.12));
        }
        else if(v.getId() == R.id.rb2) {
            tipAmount = Double.parseDouble(f.format((Double.parseDouble(billTot)) * 0.15));
        }
        else if(v.getId() == R.id.rb3) {
            tipAmount = Double.parseDouble(f.format((Double.parseDouble(billTot)) * 0.18));
        }
        else if(v.getId() == R.id.rb4) {
            tipAmount = Double.parseDouble(f.format((Double.parseDouble(billTot)) * 0.20));
        }
        //((RadioButton)v).setChecked(false);

        totalAmount = (Double.parseDouble(billTot)) + tipAmount;

        // Setting the screen values
        tipAmtTxtValue.setText(currFormat.format(tipAmount));
        totWithTipTxtValue.setText(currFormat.format(totalAmount));

    }

    public void goBtn(View v) {
        // Format values as currency
        NumberFormat currFormat = NumberFormat.getCurrencyInstance();
        String billTotal = billTotalValue.getText().toString();
        String numOfPpl = numOfPplTxtValue.getText().toString();
        String totWithTip = totWithTipTxtValue.getText().toString();
        String dollar = "$";

        if (billTotal.isEmpty() || numOfPpl.isEmpty()) {
            return;
        }

        if (billTotal.equals("0")) {
            numOfPplTxtValue.setText("1");
            numOfPpl = numOfPplTxtValue.getText().toString();
        }

        // Pull total with tip from the screen without the $
        totWithTip = totWithTip.substring(1, totWithTip.length());
        double totalDouble = Double.parseDouble(totWithTip)*100;
        int numOfPeople = Integer.parseInt(numOfPpl);


        double retd = Math.ceil(totalDouble/numOfPeople);
        double retO = (totalDouble/numOfPeople);
        double overage = ((retd-retO) * numOfPeople);

        // updates total per person on screen
        totAmtPerPersonTxtValue.setText(currFormat.format(retd/100));

        // updates overage on screen
        overageTxtValue.setText(currFormat.format(overage/100));

    }

    public void clearAllFields(View v) {
        billTotalValue.setText("");
        tipAmtTxtValue.setText("");
        totWithTipTxtValue.setText("");
        numOfPplTxtValue.setText("");
        totAmtPerPersonTxtValue.setText("");
        overageTxtValue.setText("");
        radioGroup.clearCheck();
    }
}