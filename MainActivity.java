package com.example.user.tipcalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText amount, per;
    RadioButton fif, twn, other;
    Button cal;
    TextView tip, total;
    float num_amount, num_per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = (EditText)findViewById(R.id.amount);
        per = (EditText)findViewById(R.id.per);
        fif = (RadioButton)findViewById(R.id.fif);
        twn = (RadioButton)findViewById(R.id.twn);
        other = (RadioButton)findViewById(R.id.other);
        cal = (Button)findViewById(R.id.cal);
        tip = (TextView)findViewById(R.id.tip);
        total = (TextView)findViewById(R.id.total);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tip.setText("");
                total.setText("");

                if(amount.getText().toString().isEmpty()) // amount is empty exceptional condition
                {
                    String text = "Non_value";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(Float.valueOf(amount.getText().toString()) < 0) //  amount is negative value exceptional condition
                {
                    String text = "Can't input negative";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    num_amount = Float.valueOf(amount.getText().toString());
                    if(fif.isChecked())
                    {
                        tip.setText("Tip: " + String.valueOf(calculate(num_amount, 15)));
                        total.setText("Total: " + String.valueOf(calculate(num_amount, 15) + num_amount));
                    }
                    else if(twn.isChecked())
                    {
                        tip.setText("Tip: " + String.valueOf(calculate(num_amount, 20)));
                        total.setText("Total: " + String.valueOf(calculate(num_amount, 20) + num_amount));
                    }
                    else if(other.isChecked())
                    {
                        if(per.getText().toString().isEmpty()) // Tip is empty exceptional condition
                        {
                            String text = "Non_value";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else if(Float.valueOf(per.getText().toString()) < 0) // Tip is negative value exceptional condition
                        {
                            String text = "Can't input negative";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else if(Float.valueOf(per.getText().toString()) == 0)
                        {
                            tip.setText("Tip: 0");
                            total.setText("Total: " + amount.getText().toString());
                        }
                        else {
                            num_per = Float.valueOf(per.getText().toString());
                            tip.setText("Tip: " + String.valueOf(calculate(num_amount, num_per)));
                            total.setText("Total: " + String.valueOf(calculate(num_amount, num_per) + num_amount));
                        }
                    }
                }
            }
        });
    }

    public float calculate(float amount, float percent) // Tip and Total pay calculate function
    {
        float result=0;

        result = amount * percent / 100;

        return result;
    }
}
