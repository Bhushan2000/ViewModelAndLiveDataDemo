package com.android.codelab.viewmodelandlivedatademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView randomNoTextView = findViewById(R.id.tvNumber);
        Button buttonFetch = findViewById(R.id.button_fetch);


        // using viewModel
        MainActivityViewModel modelProvider = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);
        // while we create object of MainActivityDataGenerator class we will do this using ViewModelProvider

//        String myRandomNumber = modelProvider.getNumber();
//        randomNoTextView.setText(myRandomNumber);
        //  here myRandomNumber is of MutableLiveData so declare it bu mutableLiveData

        MutableLiveData<String> myRandomNumber = modelProvider.getNumber();

        // here we need to observe LiveData i.e., myRandomNumber which is getting from MainActivityViewModel getNumber() function
        // attach observer observer object to the liveData using observe method
        myRandomNumber.observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // this onChanged method will control what happens  when liveData object is change
                // here we set textview
                randomNoTextView.setText(s);
                Log.d(TAG, "onChanged: Data updated in UI");

            }
        });

        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on button click we want to trigger createNumber() function inside MainActivityViewModel
                // which will lead to update liveData in our viewModel 
                
                modelProvider.createdNumber();
            }
        });


        Log.i(TAG, "onCreate: random no set");
    }
}