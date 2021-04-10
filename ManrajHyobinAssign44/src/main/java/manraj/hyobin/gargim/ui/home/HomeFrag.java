package manraj.hyobin.gargim.ui.home;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.os.Looper.getMainLooper;

public class HomeFrag extends Fragment {

    private TextView dateTimeDisplay;
    private TextView timeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateTimeDisplay = view.findViewById(R.id.manrajHyobinDateDisplay);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        timeDisplay = view.findViewById(R.id.manrajHyobinTimeDisplay);

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timeDisplay.setText(new SimpleDateFormat("hh:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RadialGradient radGrad = new RadialGradient(0, 0, 25, 25, Color.RED, Color.BLUE, Shader.TileMode.MIRROR);
        circlePaint.setShader(radGrad);
        canvas.drawCircle(100, 100, 100, circlePaint);
    }
}