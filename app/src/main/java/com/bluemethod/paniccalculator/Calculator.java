package com.bluemethod.paniccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Activity for the main calculator view
 * Contains all code for the calculator and
 * emergency SOS initiation
 *
 * @author Dan Bliss
 * @author Sam Wyss-Duhammel
 */
public class Calculator extends AppCompatActivity {

    //TODO: Modify the UI in activity_calculator.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Begin collecting scene information and init-ing variables

        //The text display at the top of the calculator
        TextView equationDisplay = (TextView) findViewById(R.id.equationDisplay);

        //The number buttons
        Button oneButton = (Button) findViewById(R.id.bOneButton);
        Button twoButton = (Button) findViewById(R.id.bTwoButton);
        Button threeButton = (Button) findViewById(R.id.bThreeButton);
        Button fourButton = (Button) findViewById(R.id.bFourButton);
        Button fiveButton = (Button) findViewById(R.id.bFiveButton);
        Button sixButton = (Button) findViewById(R.id.bSixButton);
        Button sevenButton = (Button) findViewById(R.id.bSevenButton);
        Button eightButton = (Button) findViewById(R.id.bEightButton);
        Button nineButton = (Button) findViewById(R.id.bNineButton);
        Button zeroButton = (Button) findViewById(R.id.bZeroButton);

        //Operation Buttons
        Button plusButton = (Button) findViewById(R.id.bPlusButton);
        Button minusButton = (Button) findViewById(R.id.bMinusButton);
        Button timesButton = (Button) findViewById(R.id.bTimesButton);
        Button divideButton = (Button) findViewById(R.id.bDivideButton);
        Button equalsButton = (Button) findViewById(R.id.bEqualsButton);

        //Clear Button
        Button button = (Button) findViewById(R.id.bClearButton);

        //TODO: Set on click listeners
    }
}