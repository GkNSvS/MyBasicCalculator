package com.gknsvs.mybasiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1,num2;
    TextView result;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 =findViewById(R.id.txtNum1);
        num2 =findViewById(R.id.txtNum2);
        result=findViewById(R.id.txtResult);

        sharedPreferences=this.getSharedPreferences("com.gknsvs.basiccalculater", Context.MODE_PRIVATE);

        String storeRes = sharedPreferences.getString("result",null);
        if (storeRes!=null)
        {
            result.setText(storeRes);
        }
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
    public void delete(View view)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("DELETE");
        alert.setMessage("Are You Sure");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                if ((sharedPreferences.getString("result",null))!=null)
                    sharedPreferences.edit().remove("result").apply();
                result.setText("Result");
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Not Delete",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
    public void save(View view)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Save");
        alert.setMessage("Are You Sure?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Not Save",Toast.LENGTH_SHORT).show();
            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putString("result",result.getText().toString()).apply();
            }
        });

        alert.show();
    }
}