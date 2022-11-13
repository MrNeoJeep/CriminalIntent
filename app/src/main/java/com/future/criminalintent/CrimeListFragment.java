package com.future.criminalintent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrimeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrimeListFragment extends Fragment {



    private CrimeLab mCrimeLab;
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CrimeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrimeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrimeListFragment newInstance(String param1, String param2) {
        CrimeListFragment fragment = new CrimeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = v.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        mCrimeLab = CrimeLab.get(getActivity());
        mCrimeAdapter = new CrimeAdapter(mCrimeLab.getCrimes());
        mCrimeRecyclerView.setAdapter(mCrimeAdapter);

        return v;
    }
    public void onResume(){
        super.onResume();
        updateUI();
    }
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mCrimeAdapter == null){
            mCrimeAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        }else{
            mCrimeAdapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder{

        private TextView mTitle;
        private TextView mDate;
        private Crime mCrime;
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            mTitle = itemView.findViewById(R.id.list_item_crime_title);
            mDate = itemView.findViewById(R.id.list_item_crime_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent intent = new Intent(getActivity(),CrimeActivity.class);
                    Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getmId());

//                    intent.putExtra("crime_id",mCrime.getmId());
                    startActivity(intent);
                    mCrimeAdapter.notifyItemChanged(2);
                }
            });

        }
        public void bind(Crime crime){
            mCrime = crime;
            mTitle.setText(mCrime.getmTitle());
            mDate.setText(mCrime.getmDate().toString());
        }
//        public void onClick(View view){
//            Toast.makeText(getActivity(),
//                    mCrime.getmTitle()+"clicked!",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getActivity(),CrimeActivity.class);
//            startActivity(intent);
//        }

    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> mCrimes) {
            this.mCrimes = mCrimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}