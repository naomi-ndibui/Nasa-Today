package com.naomi.nasatoday.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naomi.nasatoday.R;
import com.naomi.nasatoday.activity.HomeDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import com.naomi.nasatoday.activity.Space;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.MainViewHolder> {
    private ArrayList<Space> mspace = new ArrayList<>();
    private Context mContext;
    private ArrayList<Space> space;

    public HomeListAdapter(Context context, ArrayList<Space> Space) {
        mContext = context;
        space = Space;
    }

    @Override
    public HomeListAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeListAdapter.MainViewHolder holder, int position) {
        holder.bindMain(space.get(position));
    }

    @Override
    public int getItemCount() {
        return space.size();
    }




    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.title_eventsTextView) TextView mdateTextView;
        @BindView(R.id.spaceImageView) ImageView mspaceImageView;

        private Context mContext;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();


        }

        public void bindMain(Space Space) {
            Picasso.get().load(Space.getmImage()).into(mspaceImageView);
            mdateTextView.setText(Space.getmTitle());
        }

        @Override
        public void onClick(View v) {


            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, HomeDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("space", Parcels.wrap(mspace));
            mContext.startActivity(intent);
        }
    }
}
