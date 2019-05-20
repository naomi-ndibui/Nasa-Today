package com.naomi.nasatoday.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.naomi.nasatoday.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import com.naomi.nasatoday.adapter.MainPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainDetailActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private MainPagerAdapter adapterViewPager;
    ArrayList<Space> mspace = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        ButterKnife.bind(this);

        mspace = Parcels.unwrap(getIntent().getParcelableExtra("space"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new MainPagerAdapter(getSupportFragmentManager(), mspace);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}