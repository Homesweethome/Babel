package com.library.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.library.babel.ui.dashboard.DashboardFragment;

import java.util.List;

public class WifiSettings extends AppCompatActivity {

    private Button btnStartSkan;
    private Button btnSave;
    private WifiManager wifiManager;
    private WifiScanReceiver wifiReceiver;
    private Element [] nets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_settings);

        wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiScanReceiver();

        btnStartSkan = (Button) findViewById(R.id.btnSkan);
        btnStartSkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] permiss = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_WIFI_STATE,
                                                Manifest.permission.CHANGE_WIFI_STATE,
                                                Manifest.permission.ACCESS_NETWORK_STATE};

                Permissions perms = new Permissions(permiss, view, view.getContext(), wifiManager);
                if (perms.hasPermissions()){
                   wifiManager.startScan();
                }else {
                    perms.requestPermissionWithRationale();
                }
            }
        });

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onResume();
                }catch (Exception e){
                    Log.d("TAG", "Ошибка! " + e.getMessage());
                }
            }
        });
    }

    protected void onResume() {
        registerReceiver(
                wifiReceiver,
                new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        );
        super.onResume();
    }

    protected void onPause() {
        unregisterReceiver(wifiReceiver);
        super.onPause();
    }

    private class WifiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = wifiManager.getScanResults();

            nets = new Element[wifiScanList.size()];

            for(int i = 0; i < wifiScanList.size(); i++){

                String item = ((wifiScanList.get(i)).toString());
                String[] vector_item = item.split(",");
                String item_essid = vector_item[0];

                String item_level = vector_item[3];
                String ssid = item_essid.split(": ")[1];
                String level = item_level.split(": ")[1];
                nets[i] = new Element(ssid, level);

            }

            AdapterElements adapterElements = new AdapterElements((Activity)c);
            ListView netList = (ListView) findViewById(R.id.listItem);
            netList.setAdapter(adapterElements);
        }
    }

    class AdapterElements extends ArrayAdapter<Object> {
        Activity context;

        public AdapterElements(Activity context) {
            super(context, R.layout.items_wifi, nets);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            try {
                LayoutInflater inflater = context.getLayoutInflater();
                View item = inflater.inflate(R.layout.items_wifi, null);

                TextView tvSsid = (TextView) item.findViewById(R.id.tvSSID);
                tvSsid.setText(nets[position].getTitle());


                TextView tvLevel = (TextView)item.findViewById(R.id.tvLevel);
                tvLevel.setText(nets[position].getLevel());
                return item;
            }catch (Exception e){

                Log.d("TAG", "Ошибка! " + e.getMessage());
                return new View(context);

            }
        }
    }

}