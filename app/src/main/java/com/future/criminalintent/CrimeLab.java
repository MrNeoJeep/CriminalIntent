package com.future.criminalintent;

import android.content.Context;
import android.hardware.lights.LightState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    //s -- 静态
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;
    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        for(int i = 0;i < 100;i++){
            Crime crime = new Crime();
            crime.setmTitle("crime# "+i);
            crime.setmSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public static CrimeLab get(Context context){
        if(null == sCrimeLab){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime : mCrimes) {
            if(crime.getmId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
