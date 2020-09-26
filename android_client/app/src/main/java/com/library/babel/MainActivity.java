package com.library.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CityItemsModel [] city_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetDataLibraryToList();
    }

    public void SetDataLibraryToList(){
        this.city_items = new CityItemsModel[2];

        String name = "Алтайский край, город Рубцовск";
        city_items[0] = new CityItemsModel(name);
        name = "Нововсибирск";
        city_items[1] = new CityItemsModel(name);

        AdapterCityes adapterCityes = new AdapterCityes(this);
        ListView libList = (ListView) findViewById(R.id.cityItems);
        libList.setAdapter(adapterCityes);

    }


    class AdapterCityes extends ArrayAdapter<Object> {
        Activity context;

        public AdapterCityes(Activity context){
            super(context, R.layout.items_city, city_items);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.items_city, null);

            TextView tvNameInstitution = (TextView) item.findViewById(R.id.tvCityName);
            tvNameInstitution.setText(city_items[position].getCityName());

            return  item;
        }
    }
}