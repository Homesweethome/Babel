package com.library.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CardCultureActivity extends AppCompatActivity {

    private Floors[] fl;
    private Button btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_culture);

        btnSetting = (Button)findViewById(R.id.settingsWiFi);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FloorActivity.class);
                startActivity(intent);
            }
        });


        SetDataOnList();
    }

    private void SetDataOnList(){
        fl = new Floors[3];

        fl[0] = new Floors(1, "Этаж 1");
        fl[1] = new Floors(2, "Этаж 2");
        fl[2] = new Floors(3, "Этаж 3");

        AdapterFloors adapterLibraries = new AdapterFloors(this);
        ListView libList = (ListView) findViewById(R.id.floorItems);
        libList.setAdapter(adapterLibraries);

        libList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SingleFloorActivity.class);
                startActivity(intent);
            }
        });
    }


    class AdapterFloors extends ArrayAdapter<Object> {
        Activity context;

        public AdapterFloors(Activity context){
            super(context, R.layout.floor_list, fl);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.floor_list, null);

            TextView tvNameFloor = (TextView) item.findViewById(R.id.tvNameFloor);
            tvNameFloor.setText(fl[position].getNameFloor());
            return  item;
        }
    }
}