package com.riddhidamani.tipsplit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText billTotalValue;
    private EditText numOfPplTxtValue;


    private TextView totAmtPerPersonTxtValue;
    private TextView tipAmtTxtValue;
    private TextView totWithTipTxtValue;
    private TextView overageTxtValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotalValue = findViewById(R.id.billTotal);
        tipAmtTxtValue = findViewById(R.id.tipAmtTxt);
        totWithTipTxtValue = findViewById(R.id.totWithTipTxt);
        numOfPplTxtValue = findViewById(R.id.numOfPplTxt);
        totAmtPerPersonTxtValue = findViewById(R.id.totAmtPerPersonTxt);
        overageTxtValue = findViewById(R.id.overageTxt);

    }

    public void getValue(View v) {
        String value = billTotalValue.getText().toString();
        totAmtPerPersonTxtValue.setText(value);
    }

    public void clearAllFields(View v) {
        billTotalValue.setText("");
        tipAmtTxtValue.setText("");
        totWithTipTxtValue.setText("");
        numOfPplTxtValue.setText("");
        totAmtPerPersonTxtValue.setText("");
        overageTxtValue.setText("");
    }

}