package com.example.calculatorappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bC,bE,bA,bS,bM,bD,bDe;
    TextView tx,tx2;
    String s="";
    String error = "error";
    ArrayList<String> list;
    int ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0=findViewById(R.id.button0);
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        b5=findViewById(R.id.button5);
        b6=findViewById(R.id.button6);
        b7=findViewById(R.id.button7);
        b8=findViewById(R.id.button8);
        b9=findViewById(R.id.button9);
        bC=findViewById(R.id.buttonC);
        bE=findViewById(R.id.buttonE);
        bA=findViewById(R.id.buttonA);
        bS=findViewById(R.id.buttonS);
        bM=findViewById(R.id.buttonM);
        bD=findViewById(R.id.buttonD);
        bDe=findViewById(R.id.buttonDe);

        tx=findViewById(R.id.textView);
        tx2=findViewById(R.id.textView2);

        list = new ArrayList<>();

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bA.setOnClickListener(this);
        bS.setOnClickListener(this);
        bM.setOnClickListener(this);
        bD.setOnClickListener(this);
        bE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    StringTokenizer st = new StringTokenizer(s, "*/+-", true);

                    while (st.hasMoreTokens()) {
                        list.add((st.nextToken()));
                    }

                    if(list.get(0).equals("-")) {
                        list.set(0, list.get(0)+list.get(1));
                        list.remove(1); }

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("*")) {
                            double x = Double.parseDouble(list.get(i + 1));
                            double y = Double.parseDouble(list.get(i - 1));
                            if((y*x)%1 == 0) {
                                ans = (int)(y*x);
                                list.set(i, String.valueOf(ans)); }
                            else {
                                list.set(i, String.valueOf(y*x));
                            }
                            list.remove(i + 1);
                            list.remove(i - 1);
                            i -= 2;
                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("/")) {
                            double x = Double.parseDouble(list.get(i + 1));
                            double y = Double.parseDouble(list.get(i - 1));
                            try {
                                if((y/x)%1 == 0) {
                                    ans = (int)(y/x);
                                    list.set(i, String.valueOf(ans)); }
                                else {
                                    list.set(i, String.valueOf(y/x));
                                }
                                list.remove(i + 1);
                                list.remove(i - 1);
                                i -= 2;
                            } catch (ArithmeticException e) {
                                list.clear();
                                list.add(error);
                            }
                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("+")) {
                            double x = Double.parseDouble(list.get(i + 1));
                            double y = Double.parseDouble(list.get(i - 1));
                            if((y+x)%1 == 0) {
                                ans = (int)(y+x);
                                list.set(i, String.valueOf(ans)); }
                            else {
                                list.set(i, String.valueOf(y+x));
                            }
                            list.remove(i + 1);
                            list.remove(i - 1);
                            i -= 2;
                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals("-")) {
                            double x = Double.parseDouble(list.get(i + 1));
                            double y = Double.parseDouble(list.get(i - 1));
                            if((y-x)%1 == 0) {
                                ans = (int)(y-x);
                                list.set(i, String.valueOf(ans)); }
                            else {
                                list.set(i, String.valueOf(y-x));
                            }
                            list.remove(i + 1);
                            list.remove(i - 1);
                            i -= 2;
                        }
                    }

                    tx.setText(list.get(0));
                    s = list.get(0);
                    if (s.equals(error))
                        s = "";
                    list.clear();

                } catch (NumberFormatException ne){
                    tx.setText(error);
                } catch (ArrayIndexOutOfBoundsException ae){
                    tx.setText(error);
                }
            }
        });

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = "";
                tx.setText("");
                list.clear();
            }
        });

        bDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = s.substring(0, s.length()-1);
                tx.setText(s);
            }
        });
        }

    @Override
    public void onClick(View view) {
        s += ((Button)view).getText().toString();
        tx.setText(s);
    }

}