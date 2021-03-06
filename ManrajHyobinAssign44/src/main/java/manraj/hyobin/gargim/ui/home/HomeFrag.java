/*
        Manraj Garg s991541957
        Hyobin Im s991526068
        This is assignment 4 completed via pair programming - displays the use of multiple fragments in a nav drawer */

package manraj.hyobin.gargim.ui.home;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import manraj.hyobin.gargim.R;

import static android.content.Context.MODE_PRIVATE;
import static android.os.Looper.getMainLooper;

public class HomeFrag extends Fragment {

    private TextView dateTimeDisplay;
    private TextView timeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date, color, clockFormat;

    boolean b_portrait;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dateTimeDisplay = view.findViewById(R.id.manrajHyobinDateDisplay);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat(getString(R.string.dateFormat));
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        timeDisplay = view.findViewById(R.id.manrajHyobinTimeDisplay);

        SharedPreferences settings = getContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        color = settings.getString("COLOR", "");
        clockFormat = settings.getString( "FORMAT", "");
        b_portrait = settings.getBoolean("PORTRAIT", false);

        if (color.equals("Blue")) {
            view.getRootView().setBackgroundColor(Color.BLUE);
        }
        if (color.equals("Brown")) {
            view.getRootView().setBackgroundColor(Color.parseColor("#A52A2A"));
        }
        if (color.equals("Purple")) {
            view.getRootView().setBackgroundColor(Color.parseColor("#800080"));
        }

        if (clockFormat.equals("24hr")){
            final Handler someHandler = new Handler(getMainLooper());
            someHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timeDisplay.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
                    someHandler.postDelayed(this, 1000);
                }
            }, 10);
        }else if (clockFormat.equals("12hr")){
            final Handler someHandler = new Handler(getMainLooper());
            someHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timeDisplay.setText(new SimpleDateFormat("hh:mm:ss", Locale.US).format(new Date()));
                    someHandler.postDelayed(this, 1000);
                }
            }, 10);
        }else {
            final Handler someHandler = new Handler(getMainLooper());
            someHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timeDisplay.setText(new SimpleDateFormat("hh:mm:ss", Locale.US).format(new Date()));
                    someHandler.postDelayed(this, 1000);
                }
            }, 10);
        }

//        if (b_portrait == true){
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        } else {
//            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
//        }

        return view;
    }
}
