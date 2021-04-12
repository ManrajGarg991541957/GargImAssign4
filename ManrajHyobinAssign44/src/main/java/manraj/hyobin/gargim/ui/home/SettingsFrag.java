/*
        Manraj Garg s991541957
        Hyobin Im s991526068
        This is assignment 4 completed via pair programming - displays the use of multiple fragments in a nav drawer */

package manraj.hyobin.gargim.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import manraj.hyobin.gargim.R;

import static android.content.Context.MODE_PRIVATE;


public class SettingsFrag extends Fragment {

    RadioButton selectedColor, selectedFormat;
    Switch portrait;
    SharedPreferences settings;
    Button button;
    RadioGroup color, clockFormat;
    boolean b_portrait;
    public SettingsFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        portrait = (Switch)view.findViewById(R.id.manrajHyobin_portrait_switch);
        button = (Button)view.findViewById(R.id.manrajHyobin_savePrefs_button);
        color = (RadioGroup)view.findViewById(R.id.manrajHyobin_rg_color);
        clockFormat = (RadioGroup)view.findViewById(R.id.manrajHyobin_rg_clockFormat);

        settings = getContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        b_portrait = settings.getBoolean("PORTRAIT", true);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                portrait.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            b_portrait = true;
                            saveSettingsPortrait("PORTRAIT", b_portrait);
                        } else {
                            b_portrait = false;
                            saveSettingsPortrait("PORTRAIT", b_portrait);
                        }
                    }
                });
                int selectedColorId = color.getCheckedRadioButtonId();
                selectedColor = view.findViewById(selectedColorId);
                saveSettingsColor("COLOR", selectedColor.getText().toString());
                int selectedFormatId = clockFormat.getCheckedRadioButtonId();
                selectedFormat = view.findViewById(selectedFormatId);
                saveSettingsClock("FORMAT", selectedFormat.getText().toString());
            }
        });




        return view;
    }

    public void saveSettingsColor(String s, String value){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(s, value);
        editor.apply();
    }

    public void saveSettingsClock(String s, String value){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(s, value);
        editor.apply();
    }

    public void saveSettingsPortrait(String s, boolean b){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(s,b);
        editor.apply();
    }
}