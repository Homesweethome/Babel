package com.library.babel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FloorActivity extends AppCompatActivity {

    private Rooms[] items_rooms;
    private DBHelper dbHelper;

    private int  IdInstCult = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBHelper(this);
        SetDataInRoomsList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WifiSettings.class);
                startActivity(intent);
            }
        });
    }

    public void SetDataInRoomsList(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = database.query(DBHelper.TABLE_TYPES_OF_PERMISES, null, null, null, null, null, null);
        }catch (Exception e){
            Log.d("nLog", "Ошибка! " + e.getMessage());
            return;
        }

        if (cursor.moveToFirst()){
            this.items_rooms = new Rooms[cursor.getCount()];
            int i = 0;
            do {
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME_OF_PREMISES);
                int numberIndex = cursor.getColumnIndex(DBHelper.KEY_NUMBER_OF_PREMISES);
                int schemaIndex = cursor.getColumnIndex(DBHelper.KEY_PATH_TO_SCHEMA);

                String lib_id = cursor.getString(idIndex);
                String lib_name = cursor.getString(nameIndex);
                String lib_number = cursor.getString(numberIndex);
                String lib_schema = cursor.getString(schemaIndex);
                this.items_rooms[i] = new Rooms(Integer.parseInt(lib_id), lib_name, lib_number, 1, lib_schema);
                i++;
            } while(cursor.moveToNext());
        } else {
            Log.d("nLog", "Нет записей!");
            return;
        }
        cursor.close();
        dbHelper.close();

        AdapterFloorList adapterRooms = new AdapterFloorList(this);
        ListView roomList = (ListView) findViewById(R.id.roomItems);
        roomList.setAdapter(adapterRooms);
    }

    class AdapterFloorList extends ArrayAdapter<Object> {
        Activity context;

        public AdapterFloorList(Activity context){
            super(context, R.layout.floor_list, items_rooms);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.content_floor, null);

            TextView tvNameRoom = (TextView) item.findViewById(R.id.tvNameRoom);
            tvNameRoom.setText(items_rooms[position].getNameRoom());

            TextView tvNumberRoom = (TextView) item.findViewById(R.id.tvNumberRoom);
            tvNumberRoom.setText(items_rooms[position].getNumberRoom());


            return  item;
        }
    }
}