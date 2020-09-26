package com.library.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CityItemsModel [] city_items;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        SetDataLibraryToList();
    }

    public void SetDataLibraryToList(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CITY, null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            this.city_items = new CityItemsModel[cursor.getCount()];
            int i = 0;
            do {
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME_CITY);
                String city_name = cursor.getString(nameIndex);
                this.city_items[i] = new CityItemsModel(city_name);
                i++;
            } while(cursor.moveToNext());
        } else {
            Log.d("nLog", "Нет записей!");
        }
        cursor.close();
        dbHelper.close();

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