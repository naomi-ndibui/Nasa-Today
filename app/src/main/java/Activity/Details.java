package Activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naomi.nasatoday.R;

public class Details extends Fragment {
    TextView name,location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container, false);
        name = (TextView) view.findViewById(R.id.Name);
        location = (TextView) view.findViewById(R.id.Location);
        return view;
    }

    public void change(String uname, String ulocation) {
        name.setText(uname);
        location.setText(ulocation);
    }
}