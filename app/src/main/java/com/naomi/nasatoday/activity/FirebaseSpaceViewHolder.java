package com.naomi.nasatoday.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naomi.nasatoday.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;


public class FirebaseSpaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseSpaceViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRestaurant(Space space) {
        ImageView spaceImageView = (ImageView) mView.findViewById(R.id.spaceImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.spaceTitleTextView);
        TextView dateTextView = (TextView) mView.findViewById(R.id.dateTextView);

        Picasso.get().load(space.getmImage()).into(spaceImageView);

        titleTextView.setText(space.getmTitle());
        dateTextView.setText(space.getmTitle());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Space> spaces = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_Date);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    spaces.add(snapshot.getValue(Space.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, SpaceDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("spaces", Parcels.wrap(spaces));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
