package Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.naomi.nasatoday.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import Activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainDetailFragment extends Fragment {
    @BindView(R.id.spaceImageView) ImageView mImageLabel;
    @BindView(R.id.title_eventsTextView) TextView mTitleLabel;
    @BindView(R.id.creditsTextView) TextView mCreditsLabel;
    @BindView(R.id.ExplanationTextView) TextView mexplanationLabel;
    @BindView(R.id.dateTextView) TextView mdateLabel;

    private MainActivity mMain;

    public static MainDetailFragment newInstance(Space space) {
        MainDetailFragment mainDetailFragment = new MainDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("Space", Parcels.wrap(space));
        MainDetailFragment.setArguments(args);
        return mainDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMain = Parcels.unwrap(getArguments().getParcelable("Space"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mMain.getmImage()).into(mImageLabel);

        mTitleLabel.setText(mMain.getmTitle());
        mexplanationLabel.setText(mMain.getmExplanation());
        mCreditsLabel.setText(mMain.getmCredits());
        mdateLabel.setText(mMain.getmdate());

        return view;
    }
}