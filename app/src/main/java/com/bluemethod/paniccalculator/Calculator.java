package com.bluemethod.paniccalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Activity for the main calculator view
 * Contains all code for the calculator and
 * emergency SOS initiation
 *
 * @author Dan Bliss
 * @author Sam Wyss-Duhammel
 */
public class Calculator extends AppCompatActivity {

    //Used for triggering the SOS, is set to false after .25 seconds of being hit
    private boolean equalsTapped = false;
    Timer equalsTimer;

    //SOS Class
    private SOS sos;

    //Permissions Required
    private final String[] permissions = {"android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.INTERNET",
            "android.permission.SEND_SMS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        boolean firstBoot = false;

        //Confirm that we have proper permissions so we don't crash
        while (missingPermission())
        {
            firstBoot = true;
            this.requestPermissions(permissions, 44);
        }

        if (firstBoot)
            startSettingsActivity();

        sos = new SOS(this.getApplicationContext());

        CalculatorFunctionality calculator = new CalculatorFunctionality();

        //Begin collecting scene information and init-ing variables

        //The text display at the top of the calculator
        TextView equationDisplay = findViewById(R.id.equationDisplay);

        //The number buttons
        Button oneButton = findViewById(R.id.bOneButton);
        Button twoButton = findViewById(R.id.bTwoButton);
        Button threeButton = findViewById(R.id.bThreeButton);
        Button fourButton = findViewById(R.id.bFourButton);
        Button fiveButton = findViewById(R.id.bFiveButton);
        Button sixButton = findViewById(R.id.bSixButton);
        Button sevenButton = findViewById(R.id.bSevenButton);
        Button eightButton = findViewById(R.id.bEightButton);
        Button nineButton = findViewById(R.id.bNineButton);
        Button zeroButton = findViewById(R.id.bZeroButton);

        //Operation Buttons
        Button plusButton = findViewById(R.id.bPlusButton);
        Button minusButton = findViewById(R.id.bMinusButton);
        Button timesButton = findViewById(R.id.bTimesButton);
        Button divideButton = findViewById(R.id.bDivideButton);
        Button equalsButton = findViewById(R.id.bEqualsButton);

        //Other buttons
        Button negativeButton = findViewById(R.id.bNegativeButton);
        Button sqrtButton = findViewById(R.id.nSquareRootButton);
        Button percentButton = findViewById(R.id.bPercentButton);
        Button decimalButton = findViewById(R.id.nDecimalButton);

        //Clear Button
        Button clearButton = findViewById(R.id.bClearButton);

        //---- ON CLICK LISTENERS ----//

        oneButton.setOnClickListener(view -> {
            calculator.addtoString(1);
            updateDisplay(equationDisplay, calculator);
        });

        twoButton.setOnClickListener(view -> {
            calculator.addtoString(2);
            updateDisplay(equationDisplay, calculator);
        });

        threeButton.setOnClickListener(view -> {
            calculator.addtoString(3);
            updateDisplay(equationDisplay, calculator);
        });

        fourButton.setOnClickListener(view -> {
            calculator.addtoString(4);
            updateDisplay(equationDisplay, calculator);
        });

        fiveButton.setOnClickListener(view -> {
            calculator.addtoString(5);
            updateDisplay(equationDisplay, calculator);
        });

        sixButton.setOnClickListener(view -> {
            calculator.addtoString(6);
            updateDisplay(equationDisplay, calculator);
        });

        sevenButton.setOnClickListener(view -> {
            calculator.addtoString(7);
            updateDisplay(equationDisplay, calculator);
        });

        eightButton.setOnClickListener(view -> {
            calculator.addtoString(8);
            updateDisplay(equationDisplay, calculator);
        });

        nineButton.setOnClickListener(view -> {
            calculator.addtoString(9);
            updateDisplay(equationDisplay, calculator);
        });

        zeroButton.setOnClickListener(view -> {
            calculator.addtoString(0);
            updateDisplay(equationDisplay, calculator);
        });

        plusButton.setOnClickListener(view -> {
            if (calculator.getDisplay().equals(sos.getSettingsCode())) startSettingsActivity();

            calculator.addSign("+");
            updateDisplay(equationDisplay, calculator);
        });

        minusButton.setOnClickListener(view -> {
            calculator.addSign("-");
            updateDisplay(equationDisplay, calculator);
        });

        timesButton.setOnClickListener(view -> {
            calculator.addSign("*");
            updateDisplay(equationDisplay, calculator);
        });

        divideButton.setOnClickListener(view -> {
            calculator.addSign("/");
            updateDisplay(equationDisplay, calculator);
        });

        negativeButton.setOnClickListener(view -> {
            calculator.setNumNeg(true);
            updateDisplay(equationDisplay, calculator);
        });

        decimalButton.setOnClickListener(view -> {
            calculator.addtoString(".");
            updateDisplay(equationDisplay, calculator);
        });

        sqrtButton.setOnClickListener(view -> {
            calculator.addSign("√");
            updateDisplay(equationDisplay, calculator);
        });

        percentButton.setOnClickListener(view -> {
            calculator.addSign("%");
            updateDisplay(equationDisplay, calculator);
        });

        equalsButton.setOnClickListener(view -> {
            //Not only should this solve, but we should also check for double presses
            //and dispatch the SOS

            if (equalsTapped)
                sendSOS(calculator.getDisplay());

            equalsTapped = true;

            //Deactivates the equals primer after .25 seconds
            equalsTimer = new Timer();
            equalsTimer.schedule(new EqualsToggle(), 250);

            if (!calculator.getDisplay().equals(sos.getSOSCode()))
            {
                calculator.addSign("=");
                updateDisplay(equationDisplay, calculator);
            }
        });

        clearButton.setOnClickListener(view -> {
            calculator.clearButton();
            updateDisplay(equationDisplay, calculator);
        });
    }

    private boolean missingPermission()
    {
        return this.getApplicationContext().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != PackageManager.PERMISSION_GRANTED
                || this.getApplicationContext().checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != PackageManager.PERMISSION_GRANTED
                || this.getApplicationContext().checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED;
    }

    private void startSettingsActivity()
    {
        Intent intent = new Intent(this, MainSettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Class used to manage the double tapping for the equals button
     */
    class EqualsToggle extends TimerTask
    {
        public void run()
        {
            equalsTapped = false;
        }
    }

    /**
     * Updates the calculator's equation display
     *
     * @param view the text view to update
     * @param calculator the calculator class
     */
    private void updateDisplay(TextView view, CalculatorFunctionality calculator)
    {
        view.setText(calculator.getDisplay());
    }

    /**
     * Sends an SOS Signal
     *
     * @param code the current entered code
     */
    private void sendSOS(String code)
    {
        sos.sendSMS(code);
    }
}