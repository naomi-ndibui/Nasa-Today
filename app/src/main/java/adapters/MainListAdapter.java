package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naomi.nasatoday.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Activity.Space;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder> {
    private ArrayList<Space> mSpace = new ArrayList<>();
    private Context mContext;

    public MainListAdapter(Context context, ArrayList<Space> Spaces) {
        mContext = context;
        mSpace = Spaces;
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
