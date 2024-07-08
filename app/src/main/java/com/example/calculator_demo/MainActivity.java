package com.example.calculator_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button on,off,c,div,multi,sub,sum,equal,del,point;
    Button num1,num2,num3,num4,num5,num6,num7,num8,num9,num0;

    double firstNumber=0.0,secondNumber=0.0;
    String operation;
    TextView screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen=findViewById(R.id.screen);

        //references for operator's buttons
        on=findViewById(R.id.on);
        off=findViewById(R.id.off);
        c=findViewById(R.id.c);
        point=findViewById(R.id.point);


        sub=findViewById(R.id.sub);
        sum=findViewById(R.id.sum);
        equal=findViewById(R.id.equal);
        del=findViewById(R.id.del);

        //all references for number's buttons
        num0=findViewById(R.id.num0);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        num3=findViewById(R.id.num3);
        num4=findViewById(R.id.num4);
        num5=findViewById(R.id.num5);
        num6=findViewById(R.id.num6);
        num7=findViewById(R.id.num7);
        num8=findViewById(R.id.num8);
        num9=findViewById(R.id.num9);


        ArrayList<Button> nums=new ArrayList<>();

        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for(Button b : nums){

           b.setOnClickListener(View->{


               if(!screen.getText().toString().equals("0")){
                   screen.setText(screen.getText().toString()+ b.getText().toString());
               }else{
                   screen.setText(b.getText().toString());
               }
           });
        }

        ArrayList<Button> operators=new ArrayList<>();
        sub=findViewById(R.id.sub);
        sum=findViewById(R.id.sum);
        div=findViewById(R.id.div);
        multi=findViewById(R.id.times);

        operators.add(sub);
        operators.add(sum);
        operators.add(div);
        operators.add(multi);

        for(Button b:operators){
            b.setOnClickListener(View->{
                if(operation ==null) {
                    firstNumber = Double.parseDouble(screen.getText().toString());
                    operation = b.getText().toString();
                    screen.setText("");
                }
            });
        }



        off.setOnClickListener(View->screen.setVisibility(View.GONE));
        on.setOnClickListener(View-> screen.setVisibility(View.VISIBLE));

        del.setOnClickListener(View->{
            String num=screen.getText().toString();
            if(num.length()>=1){
                screen.setText(num.substring(0,num.length()-1));
                if(num.length()==1 && !num.equals("0")){
                    screen.setText("0");
                }
            }

        });
        
        point.setOnClickListener(View->{

            if(!screen.getText().toString().contains(".")){
                screen.setText(screen.getText().toString() +".");
            }
        });

        //equality
        equal.setOnClickListener(View->{
             secondNumber=0.0;
            if (!screen.getText().toString().equals(null)) {
                secondNumber = Double.parseDouble(screen.getText().toString().trim());

                if( operation != null) {
                    double result = 0.0;
                    switch (operation) {

                        case "÷":
                            result = division(firstNumber, secondNumber);
                            break;
                        case "×":
                            result = multiplication(firstNumber, secondNumber);
                            break;
                        case "-":
                            result = subtraction(firstNumber, secondNumber);
                            break;
                        case "+":
                            result = addition(firstNumber, secondNumber);
                            break;
                        default: result= firstNumber+secondNumber;
                    }
                    screen.setText(String.valueOf(result));
                    firstNumber=result;
                    secondNumber=0.0;
                    operation=null;
                }
            }


        });

        c.setOnClickListener(View->{

            firstNumber=0.0;
            secondNumber=0.0;
            screen.setText("0");

        });

    }



    //method for division of numbers and i handled the exception if it occurs
    private double division(double firstNumber,double secondNumber){
        double result=0.0;
        try {
             result =firstNumber/secondNumber;

        }catch (ArithmeticException e){

            screen.setText("error");
        }
        return result;
    }

    private double addition(double firstNumber,double secondNumber){
       return firstNumber+secondNumber;
    }

    private double subtraction(double firstNumber,double secondNumber){
        return firstNumber-secondNumber;
    }

    private double multiplication(double firstNumber,double secondNumber){
        return firstNumber*secondNumber;
    }

}