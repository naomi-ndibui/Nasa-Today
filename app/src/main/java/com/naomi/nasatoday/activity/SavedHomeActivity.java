package com.naomi.nasatoday.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.naomi.nasatoday.R;

public class SavedHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SpaceReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_Date);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Space> options =
                new FirebaseRecyclerOptions.Builder<Space>()
                        .setQuery(SpaceReference, Sapce.class)
                        .build();

        FirebaseAdapter = new FirebaseRecyclerAdapter<Space, FirebaseSpaceViewHolder>(options) {
            @Override
            protected void onBindViewHolder( FirebaseSpaceViewHolder firebaseSpaceViewHolder, int position, Space space) {
                firebaseSpaceViewHolder.bindRestaurant(space);
            }

            @Override
            public FirebaseSpaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
                return new FirebaseSpaceViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(FirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(FirebaseAdapter!= null) {
            FirebaseAdapter.stopListening();
        }

    }
}
