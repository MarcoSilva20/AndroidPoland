package com.example.marco.hw;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class SecondActivity extends AppCompatActivity {
    private int selected_color = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);
        final RadioGroup choise = (RadioGroup) findViewById(R.id.myRadioGroup);

        choise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioBtnID = group.getCheckedRadioButtonId();
                View radioB = group.findViewById(radioBtnID);
                selected_color = group.indexOfChild(radioB);
                switch (selected_color){
                    case 0: selected_color = Color.RED;break;
                    case 1: selected_color = Color.BLUE;break;
                    case 2: selected_color = Color.GREEN;break;
                    case 3: selected_color = Color.CYAN;break;
                    case 4: selected_color = Color.MAGENTA;break;
                    case 5: selected_color = Color.BLACK;break;
                }
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.color,selected_color);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choise.clearCheck();
                selected_color = -1;
            }
        });
    }
}
