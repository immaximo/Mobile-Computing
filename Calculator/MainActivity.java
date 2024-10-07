package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char currentSymbol;
    private double firstValue = Double.NaN;
    private double secondValue;

    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;

    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide, buttonClear, buttonEqual, buttonBackspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");

        // Initialize TextViews
        inputDisplay = findViewById(R.id.input);
        outputDisplay = findViewById(R.id.output);

        // Initialize buttons
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);

        buttonAdd = findViewById(R.id.add);
        buttonSub = findViewById(R.id.subtract);
        buttonMultiply = findViewById(R.id.multiply);
        buttonDivide = findViewById(R.id.division);
        buttonDot = findViewById(R.id.btnPoint);
        buttonClear = findViewById(R.id.clear);
        buttonEqual = findViewById(R.id.equal);
        buttonBackspace = findViewById(R.id.backspace); // Initialize backspace button

        // Number buttons
        button0.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "0"));
        button1.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "1"));
        button2.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "2"));
        button3.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "3"));
        button4.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "4"));
        button5.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "5"));
        button6.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "6"));
        button7.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "7"));
        button8.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "8"));
        button9.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "9"));

        // Arithmetic operations
        buttonAdd.setOnClickListener(view -> setOperation(ADDITION, "+"));
        buttonSub.setOnClickListener(view -> setOperation(SUBTRACTION, "-"));
        buttonMultiply.setOnClickListener(view -> setOperation(MULTIPLICATION, "x"));
        buttonDivide.setOnClickListener(view -> setOperation(DIVISION, "÷"));

        // Equal button
        buttonEqual.setOnClickListener(view -> {
            allCalculations();
            outputDisplay.setText(decimalFormat.format(firstValue));
            currentSymbol = '0'; // Resetting the currentSymbol
        });

        // Clear button functionality
        buttonClear.setOnClickListener(view -> {
            firstValue = Double.NaN;
            secondValue = Double.NaN;
            inputDisplay.setText("");
            outputDisplay.setText("");
        });

        // Backspace functionality
        buttonBackspace.setOnClickListener(view -> {
            String currentText = inputDisplay.getText().toString();
            if (currentText.length() > 0) {
                inputDisplay.setText(currentText.substring(0, currentText.length() - 1));
            }
        });
    }

    private void setOperation(char operation, String symbol) {
        if (!Double.isNaN(firstValue)) {
            allCalculations(); // Calculate the previous operation
        } else {
            // If firstValue is NaN, parse the current input
            firstValue = Double.parseDouble(inputDisplay.getText().toString());
        }
        currentSymbol = operation;
        outputDisplay.setText(decimalFormat.format(firstValue) + symbol);
        inputDisplay.setText(null); // Clear the input for next value
    }

    private void allCalculations() {
        if (inputDisplay.getText().length() == 0) return; // Prevent crash if input is empty

        secondValue = Double.parseDouble(inputDisplay.getText().toString());

        switch (currentSymbol) {
            case ADDITION:
                firstValue += secondValue;
                break;
            case SUBTRACTION:
                firstValue -= secondValue;
                break;
            case MULTIPLICATION:
                firstValue *= secondValue;
                break;
            case DIVISION:
                firstValue /= secondValue;
                break;
        }

        inputDisplay.setText(null); // Clear input after calculations
    }
}
