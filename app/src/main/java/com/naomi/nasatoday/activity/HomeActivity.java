package com.naomi.nasatoday.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naomi.nasatoday.R;
import com.naomi.nasatoday.adapter.HomeListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = HomeActivity.class.getSimpleName();

    private DatabaseReference SearchedDateReference;
    private ValueEventListener SearchedDateReferenceListener;


    @BindView(R.id.button) Button Button;
    @BindView(R.id.dateTextView) EditText DateEditText;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private HomeListAdapter mAdapter;
    private ArrayList<Space> mspace = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SearchedDateReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_DATE);

        SearchedDateReferenceListener =  SearchedDateReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                    String date = dateSnapshot.getValue().toString();
                    Log.d("Dates updated", "date: " + date);
            }
        }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Button.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v == Button) {
            String date = DateEditText.getText().toString();

            saveDateToFirebase(date);

            Intent intent = getIntent();
            intent.putExtra("date", date);
            startActivity(intent);
        }
    }

    public void saveDateToFirebase(String date) {
        SearchedDateReference.push().setValue(date);
    }

    private void getSpace(String date) {
        final NasaService NasaService = new NasaService();
        NasaService.findSpace(date, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mspace = NasaService.processResults(response);

                HomeActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new HomeListAdapter(getApplicationContext(), mspace);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(HomeActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SearchedDateReference.removeEventListener(SearchedDateReferenceListener);
    }

}

