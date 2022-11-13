package com.future.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.Serializable;
import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity{


    public static final String EXTRA_CRIME_ID = "crime_id";
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra("crime_id");
        return CrimeFragment.newInstance(crimeId);
    }
    public static Intent newIntent(Context packageContext,UUID crimeId){
        Intent intent = new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);

        return intent;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        UUID crimeId = (UUID) getIntent().getSerializableExtra("crimeid");
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        if (fragment == null) {
//            fragment = CrimeFragment.newInstance(crimeId);
////            fragment = CrimeListFragment.newInstance("p1","p2");
//            fm.beginTransaction()
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//
//        }
//
//    }
}