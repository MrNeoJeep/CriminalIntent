package com.future.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return CrimeListFragment.newInstance("p1","p2");
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        if(fragment == null){
//            //fragment = CrimeFragment.newInstance("p1","p2");
//            fragment = CrimeListFragment.newInstance("p1","p2");
//            fm.beginTransaction()
//                    .add(R.id.fragment_container,fragment)
//                    .commit();
//
//        }
//
//    }
}