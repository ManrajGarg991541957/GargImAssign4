package manraj.hyobin.gargim.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import manraj.hyobin.gargim.R;


public class WebServiceFrag extends Fragment implements View.OnClickListener {

    private Button webServiceButton;
    private View view;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 111;
    private TextView tvLong;
    private TextView tvLat;
    private TextView tvTemp;
    private TextView tvName;
    private TextView tvHumidity;
    private TextView tvZip;

    public WebServiceFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tvLong = view.findViewById(R.id.manrajHyobinLong);
        tvLat = view.findViewById(R.id.manrajHyobinLat);
        tvTemp = view.findViewById(R.id.manrajHyobinTemp);
        tvName = view.findViewById(R.id.manrajHyobinName);
        tvHumidity = view.findViewById(R.id.manrajHyobinHumidity);
        tvZip = view.findViewById(R.id.manrajHyobinZipcode);

        webServiceButton = view.findViewById(R.id.manrajHyobinWebServiceBtn);
        webServiceButton.setOnClickListener((View.OnClickListener) this);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_service, container, false);
    }

    @Override
    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(
                getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            getCurrentLocation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       // LocationServices.getFusedLocationProviderClient(this.getActivity()).requestLocationUpdates(locationRequest, new LocationCallBack(){})
    }
}