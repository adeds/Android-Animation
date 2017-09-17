package com.noes.adeds.androanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView done;
    String word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        done = (TextView) findViewById(R.id.done);
        word = getIntent().getExtras().getString("word");
        done.setText(word);
    }
}
