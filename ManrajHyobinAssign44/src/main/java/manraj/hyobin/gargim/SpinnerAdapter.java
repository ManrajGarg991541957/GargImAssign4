package manraj.hyobin.gargim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import manraj.hyobin.gargim.ui.home.DownloadFrag;

public class SpinnerAdapter extends ArrayAdapter<SpinnerItems> {

    public SpinnerAdapter(Context context, ArrayList<SpinnerItems> spinnerList)
    {
        super(context, 0, spinnerList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.team_spinner_row, parent, false
            );
        }

        ImageView imageViewTeam = convertView.findViewById(R.id.gargImImage);
        TextView textViewName = convertView.findViewById(R.id.gargImTvName);

        SpinnerItems currentItem = getItem(position);

        if(currentItem != null)
        {
            imageViewTeam.setImageResource(currentItem.getLogoImage());
            textViewName.setText(currentItem.getImageName());
        }

        return convertView;
    }
}
