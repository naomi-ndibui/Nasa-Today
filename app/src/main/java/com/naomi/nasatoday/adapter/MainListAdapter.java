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
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import com.naomi.nasatoday.Activity.MainDetailActivity;
import com.naomi.nasatoday.Activity.Space;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder> implements View.OnClickListener{
    private ArrayList<Space> mSpace = new ArrayList<>();
    private Context mContext;
    private Space mspace;
    private View itemView;

    public  void MainListAdapter( View itemView) {
        super(itemView);
    }

    public MainListAdapter(Context context, ArrayList<Space> Space) {
        super(context);
        mContext = context;
        mSpace = Space;
    }

    @Override
    public MainListAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainListAdapter.MainViewHolder holder, int position) {
        holder.bindMain(mSpace.get(position));
    }

    @Override
    public int getItemCount() {
        return mSpace.size();
    }


    @Override
    public void onClick(View v) {
        itemView.setOnClickListener(this);

        int itemPosition = getLayoutPosition();
        Intent intent = new Intent(mContext, MainDetailActivity.class);
        intent.putExtra("position", itemPosition);
        intent.putExtra("space", Parcels.wrap(mspace));
        mContext.startActivity(intent);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_eventsTextView) TextView mdateTextView;
        @BindView(R.id.spaceImageView) ImageView mspaceImageView;

        private Context mContext;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMain(Space Space) {
            Picasso.get().load(Space.getmImage()).into(mspaceImageView);
            mdateTextView.setText(Space.getmTitle());
        }
    }
}
