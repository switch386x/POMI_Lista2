package pl.applover.piotrekzadanie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int clickCounter = 0;
    int wznowienia;
    static final String RESUME_COUNTER_KEY = "WZNOWIENIA_KLUCZ";
    static final ArrayList<Integer> al = new ArrayList<>();
    Uri uriUrl = Uri.parse("http://stackoverflow.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPrimeNumbers(75);
        if (savedInstanceState != null)
            wznowienia = savedInstanceState.getInt(RESUME_COUNTER_KEY, 0);
        else
            wznowienia = 0;
        Log.d("test", String.valueOf(wznowienia));
        display10Numbers(9);
        final Button mySecondBtn = findViewById(R.id.mySecondBtn);
        final Button myFirstBtn = findViewById(R.id.myFirstBtn);
        final RadioGroup myRadioGroup = findViewById(R.id.myRadioGroup);
        final RadioButton redRadial = findViewById(R.id.redRadial);
        final RadioButton greenRadial = findViewById(R.id.greenRadial);
        final TextView linkTextView = findViewById(R.id.linkContainerTextView);
        linkTextView.setOnClickListener(new View.OnClickListener() {  // ista 3 zad 3
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
//lista 3 zad 3 koniec
        mySecondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(redRadial.isChecked()){
                    Intent thirdActivityIntent = new Intent(getApplicationContext(), ThirdScreenActivity.class);
                    startActivityForResult(thirdActivityIntent,0);
                }
                else if(greenRadial.isChecked()){
                    Intent secondActivityIntent = new Intent(getApplicationContext(), SecondScreenActivity.class);
                    startActivityForResult(secondActivityIntent,0);
                }
            }
        });
        myFirstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCounter += 1;
                myFirstBtn.setText(String.valueOf(clickCounter));

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void display10Numbers(int x) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= x; i++) {
            if (i < al.size())
                sb.append(al.get(i)).append("\n");
        }
        TextView tv = findViewById(R.id.myFirstTextView);
        tv.setText(sb);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        wznowienia += 1;
        outState.putInt(RESUME_COUNTER_KEY, wznowienia);
    }

    public void displayPrimeNumbers(int x) {       //75 for 21
        al.clear();
        int primeNumbers = 0;
        int num;
        for (int i = 2; i < x; i++) {
            int counter = 0;
            int lastNumber = 0;
            for (num = i; num >= 1; num--) {
                if (i % num == 0) {
                    counter = counter + 1;
                    lastNumber = i;
                }
            }
            if (counter == 2) {
                Log.d("primeNumbers", String.valueOf(lastNumber));
                al.add(lastNumber);
                primeNumbers = primeNumbers + i;
            }
        }
    }

}
