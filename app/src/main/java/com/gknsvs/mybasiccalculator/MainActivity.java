package com.gknsvs.mybasiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText num1,num2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 =findViewById(R.id.TextNum1);
        num2 =findViewById(R.id.TextNum2);
        result=findViewById(R.id.textResult);
    }
    
    private boolean control() {
        if(num1.getText().toString().matches("")||num2.getText().toString().matches(""))
        {
            result.setText("Enter the number");
            return false;
        }
        return true;
    }
    public void sum(View view)
    {
        if(control())
            result.setText(num1.getText().toString()+" + "+num2.getText().toString()+" = "+String.valueOf(Integer.parseInt(num1.getText().toString()) + Integer.parseInt(num2.getText().toString())));
    }
    public void sub(View view)
    {
        if(control())
            result.setText(num1.getText().toString()+" - "+num2.getText().toString()+" = "+String.valueOf(Integer.parseInt(num1.getText().toString()) - Integer.parseInt(num2.getText().toString())));
    }
    public void mul(View view)
    {
        if(control())
            result.setText(num1.getText().toString()+" * "+num2.getText().toString()+" = "+String.valueOf(Integer.parseInt(num1.getText().toString()) * Integer.parseInt(num2.getText().toString())));

    }
    public void div(View view)
    {
        if(control())
            result.setText(num1.getText().toString()+" / "+num2.getText().toString()+" = "+String.valueOf(Integer.parseInt(num1.getText().toString()) / Integer.parseInt(num2.getText().toString())));
    }
}