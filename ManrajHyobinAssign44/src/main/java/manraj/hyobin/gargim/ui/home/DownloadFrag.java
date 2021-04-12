/*
        Manraj Garg s991541957
        Hyobin Im s991526068
        This is assignment 4 completed via pair programming - displays the use of multiple fragments in a nav drawer */

package manraj.hyobin.gargim.ui.home;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import manraj.hyobin.gargim.R;
import manraj.hyobin.gargim.SpinnerAdapter;
import manraj.hyobin.gargim.SpinnerItems;


public class DownloadFrag extends Fragment {

    ArrayList<SpinnerItems> mSpinnerList;
    SpinnerAdapter mAdapter;
    View root;
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView= null;
    ProgressDialog p;

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
        initList();
        mAdapter = new SpinnerAdapter(this.getContext(), mSpinnerList);
        spinnerTeams.setAdapter(mAdapter);

        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItems clickedItem = (SpinnerItems)parent.getItemAtPosition(position);
                String clickedTeamName = clickedItem.getImageName();
                 Toast.makeText(getContext(), clickedTeamName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button=root.findViewById(R.id.manrajHyobinDownload);
        imageView= root.findViewById(R.id.manrajHyobinImageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask=new AsyncTaskExample();
                TextView spinnerText = root.findViewById(R.id.gargImTvName);
                if(spinnerText.getText().toString().equals(getString(R.string.buck)))
                {
                    asyncTask.execute(getString(R.string.bucksUrl));
                }
                else if (spinnerText.getText().toString().equals(getString(R.string.rap)))
                {
                    asyncTask.execute(getString(R.string.raptorsUrl));
                }
                else if (spinnerText.getText().toString().equals(getString(R.string.lake)))
                {
                    asyncTask.execute(getString(R.string.lakersUrl));
                }
            }
        });

        return root;
    }


    private void initList()
    {
        mSpinnerList = new ArrayList<>();
        mSpinnerList.add(new SpinnerItems(getString(R.string.raps), R.drawable.raptors));
        mSpinnerList.add(new SpinnerItems(getString(R.string.bucs), R.drawable.bucks));
        mSpinnerList.add(new SpinnerItems(getString(R.string.la), R.drawable.lakers));
    }

    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            TextView spinnerText = root.findViewById(R.id.gargImTvName);
            String teamText = spinnerText.getText().toString();
            super.onPreExecute();
            p=new ProgressDialog(getContext());
            p.setTitle(getString(R.string.title) + teamText);
            p.setMessage(getString(R.string.message));
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {

                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl
                        .openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return bmImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(imageView!=null) {
                p.hide();
                imageView.setImageBitmap(bitmap);
            }else {
                p.show();
            }
        }
    }
}