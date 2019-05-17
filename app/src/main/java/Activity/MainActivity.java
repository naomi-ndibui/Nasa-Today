package Activity;


import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.naomi.nasatoday.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout drawer;
    private String date;

    @BindView(R.id.dateTextView) TextView mDateTextView;
    @BindView(R.id.listView) ListView mListView;
    private ArrayList<Space> mspace = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        mDateTextView.setText("Here are all the space event titles on: " + date);
        getSpace(date);

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

        private void getSpace(String date) {
            final NasaService NasaService = new NasaService();
            NasaService.findSpace(date, new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) {
                    mspace = NasaService.processResults(response);

                    MainActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            String[] spaceDate = new String[mspace.size()];
                            for (int i = 0; i < spaceDate.length; i++) {
                                spaceDate[i] = mspace.get(i).getmTitle();
                            }
                            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, spaceDate);
                            mListView.setAdapter(adapter);
                            for (Space Space : mspace) {
                                Log.d(TAG, "Image: " + Space.getmImage());
                                Log.d(TAG, "Credits: " + Space.getmCredits());
                                Log.d(TAG, "Title: " + Space.getmTitle());
                                Log.d(TAG, "Explanation: " + Space.getmExplanation());
                                Log.d(TAG, "Date: " + Space.getmdate());
                            }
                        }

                    });
                }
            });
        }
}
