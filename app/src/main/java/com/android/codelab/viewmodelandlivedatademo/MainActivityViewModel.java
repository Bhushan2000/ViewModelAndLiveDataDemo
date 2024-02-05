package com.android.codelab.viewmodelandlivedatademo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;






// Generate random using viewModel

public class MainActivityViewModel extends ViewModel {

    // Creating the liveData and use with it is very easy:

    // step 1: create instance of the liveData

    // for demo application We generate random no instead of fetch it from any network
    // When speaking of live we have another class mutableLiveData

    // step 2: Create instance of mutableLiveData

    //MutableLiveData extends liveData scenes live data has no public method to update the stored data we will use MutableLiveData
    // This MutableLiveData class provides us two functions postValue(),setValue() which is used to update the data.
    // so in viewModel we use liveData






    private String TAG = "MainActivityDataGenerator";
    private MutableLiveData<String> myRandomNumber;

    public MutableLiveData<String> getNumber() {
        Log.i(TAG, "getNumber: get number");
        if (myRandomNumber == null) {
            myRandomNumber = new MutableLiveData<>();
            createdNumber();
        }
        return myRandomNumber;
    }

    public void createdNumber() {
        Log.i(TAG, "createdNumber: created new randomNumber");
        Random random = new Random();
//        myRandomNumber = "Number: " + (random.nextInt(10 - 1) + 1);
        // give 1 to 10 random no
        // scenes myRandomNumber is of MutableLiveData so instead of initializing this ("Number: " + (random.nextInt(10 - 1) + 1))
        // string value we will use function setValue()

        myRandomNumber.setValue("Number: " + (random.nextInt(10 - 1) + 1));
        // Now we all set with live data in ViewModel
        // now we need to observe livedata inside our mainActivity and this is usually done in onCreate() function
        // Doing it in onCreate() function ensure that the system does not make redundant calls for observing the liveData

    }

    // We have seen viewModel is destroyed in onCleared method
    // It is destroyed when the created activity is completely destroyed


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ViewModel Destroyed");
    }
}