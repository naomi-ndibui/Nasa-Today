package com.naomi.nasatoday.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.naomi.nasatoday.R;
import com.naomi.nasatoday.activity.FirebaseSpaceViewHolder;
import com.naomi.nasatoday.activity.Space;
import com.naomi.nasatoday.activity.Util.ItemTouchHelperAdapter;
import com.naomi.nasatoday.activity.Util.OnStartDragListener;

public class FirebaseHomeListAdapter extends FirebaseRecyclerAdapter<Space, FirebaseSpaceViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseHomeListAdapter(FirebaseRecyclerOptions<Space> options,
                                         DatabaseReference ref,
                                         OnStartDragListener onStartDragListener,
                                         Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
    @Override
    protected void onBindViewHolder( FirebaseSpaceViewHolder firebaseSpaceViewHolder, int position, Space space) {
        firebaseSpaceViewHolder.bindSpace(space);
    }

    @Override
    public FirebaseSpaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        return new FirebaseSpaceViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position){

    }
}

