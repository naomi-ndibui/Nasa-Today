package Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naomi.nasatoday.R;
import com.squareup.picasso.Picasso;

import Activity.Space;

public class SpaceDetailFragment extends Fragment {
    @BindView(R.id.SpaceImageView) ImageView mImageLabel;
    @BindView(R.id.title_eventsTextView) TextView mTitleLabel;
    @BindView(R.id.creditsTextView) TextView mCreditsLabel;
    @BindView(R.id.ExplanationTextView) TextView mexplanationLabel;
    @BindView(R.id.dateTextView) TextView mdateLabel;

    private Space mspace;

    public static SpaceDetailFragment newInstance(Space space) {
        SpaceDetailFragment spaceDetailFragment = new spaceDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("space", Parcels.wrap(space));
        spaceDetailFragment.setArguments(args);
        return spaceDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mspace = Parcels.unwrap(getArguments().getParcelable("space"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_space_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mspace.getImageUrl()).into(mImageLabel);

        mTitleLabel.setText(mspace.getmTitle());
        mexplanationLabel.setText(mspace.getmExplanation());
        mCreditsLabel.setText(mspace.getmCredits());
        mdateLabel.setText(mspace.getmdate());

        return view;
    }
}