package com.naomi.nasatoday.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.naomi.nasatoday.R;
import com.naomi.nasatoday.activity.Constants;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import com.naomi.nasatoday.activity.Space;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SpaceDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.spaceImageView) ImageView mImageLabel;
    @BindView(R.id.title_eventsTextView) TextView mTitleLabel;
    @BindView(R.id.creditsTextView) TextView mCreditsLabel;
    @BindView(R.id.ExplanationTextView) TextView mexplanationLabel;
    @BindView(R.id.dateTextView) TextView mdateLabel;
    @BindView(R.id.button) Button saveButton;

    private Space mspace;


    public static SpaceDetailFragment newInstance(Space space) {
        SpaceDetailFragment mainDetailFragment = new SpaceDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("Space", Parcels.wrap(space));
        mainDetailFragment.setArguments(args);
        return mainDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mspace = Parcels.unwrap(getArguments().getParcelable("Space"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_space_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get()
                .load(mspace.getmImage())
                .into(mImageLabel);

        mTitleLabel.setText(mspace.getmTitle());
        mexplanationLabel.setText(mspace.getmExplanation());
        mCreditsLabel.setText(mspace.getmCredits());
        mdateLabel.setText(mspace.getmdate());

        mTitleLabel.setOnClickListener(this);
        mdateLabel.setOnClickListener(this);

        saveButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == saveButton) {
            DatabaseReference spaceRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_Date);
            spaceRef.push().setValue(mdateLabel);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}