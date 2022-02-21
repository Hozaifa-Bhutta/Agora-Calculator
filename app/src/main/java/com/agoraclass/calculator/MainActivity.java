package com.agoraclass.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Used to check if you have already inputted an expression
        final Boolean[] isDone = {false};

        //Assign each button to a variable by finding its ID
        Button zero = findViewById(R.id.zero);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);

        Button add = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button divide = findViewById(R.id.divide);
        Button multiply = findViewById(R.id.multiply);

        Button equal = findViewById(R.id.equal);

        //Assign TextView for display
        TextView answer = findViewById(R.id.answer);

        //Put all numbers and operators into a list
        Button[] operations = {add, minus, divide, multiply};
        Button[] numbers = {zero, one, two, three, four, five, six, seven, eight, nine};

        //Sets listener for each number button
        //When a number is pressed, it adds text to "display" and sets the TextView accordingly
        final String[] display = {""};
        for (int i = 0; i < numbers.length; i++) {
            int finalI = i;
            numbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isDone[0] == true) {
                        //Resets display if you've already computed an expression
                        display[0] = "";
                        isDone[0] = false;
                    }
                    //Adds number to display
                    display[0] = display[0] + numbers[finalI].getText();
                    answer.setText(display[0]);
                }
            });
        }

        //Sets listenor for each operator button
        //When an operator is pressed, it is placed onto the screen
        for (int i = 0; i < operations.length; i++) {
            int finalI = i;
            operations[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isDone[0] == true) {
                        //Resets display if you've already computed an expression
                        display[0] = "";
                        isDone[0] = false;
                    }
                    //adds operator to display
                    display[0] = display[0] + " " + operations[finalI].getText() + " ";
                    answer.setText(display[0]);
                }
            });
        }

        //Sets listener for equals
        //Computes expression and sets display accordingly
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Computes results
                if (isDone[0] == false) {
                    //Resets display if isDone == true
                    answer.setText(String.valueOf(compute(display[0])));
                    isDone[0] = true;
                }


            }

        });



    }
    public int compute(String expression) {
        //Parse string to generate answer
        //Note: Only works with one operator and 2 numbers. Try to mess around and expand on it
        String[] expressionList = expression.split(" ");
        int output = 0;
        int i = 0;
        while (i < (expressionList.length - 1)) {
            if (i == 0) {
                output = Integer.parseInt(expressionList[0]);
                i++;
            } else if (expressionList[i].equals("-")) {
                output = output - Integer.parseInt(expressionList[i + 1]);
                i++;
                break;
            }
            else if (expressionList[i].equals("x")) {
                output = output * Integer.parseInt(expressionList[i + 1]);
                i++;
                break;
            }
            else if (expressionList[i].equals("+")) {
                output = output + Integer.parseInt(expressionList[i + 1]);
                i++;
                break;
            }
            else if (expressionList[i].equals("/")) {
                output = output / Integer.parseInt(expressionList[i + 1]);
                i++;
                break;
            }


        }
        return output;
    }
}