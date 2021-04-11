package manraj.hyobin.gargim.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;

import manraj.hyobin.gargim.R;
import manraj.hyobin.gargim.SpinnerAdapter;
import manraj.hyobin.gargim.SpinnerItems;


public class DownloadFrag extends Fragment {

    ArrayList<SpinnerItems> mSpinnerList;
    SpinnerAdapter mAdapter;
    View root;
    int img[] = {0, R.drawable.raptors, R.drawable.bucks, R.drawable.lakers};

    public DownloadFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_download, container, false);

        Spinner spinnerTeams = root.findViewById(R.id.gargImSpinner);

        mAdapter = new SpinnerAdapter(this.getActivity(), mSpinnerList);
        spinnerTeams.setAdapter(mAdapter);

        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItems clickedItem = (SpinnerItems)parent.getItemAtPosition(position);
                String clickedTeamName = clickedItem.getImageName();
                // Toast.makeText(this.getActivity(), clickedTeamName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }


    private void initList()
    {
        mSpinnerList = new ArrayList<>();
        mSpinnerList.add(new SpinnerItems("Raptors", R.drawable.raptors));
        mSpinnerList.add(new SpinnerItems("Bucks", R.drawable.bucks));
        mSpinnerList.add(new SpinnerItems("Lakers", R.drawable.lakers));
    }
}