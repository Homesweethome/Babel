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

public class LibrariesActivityList extends AppCompatActivity {

    private Libraries [] libraries;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraries_list);

        dbHelper = new DBHelper(this);

        SetDataLibraryToList();
    }

    public void SetDataLibraryToList(){

       /* SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_INSTITUT_OF_CULTURE, null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            this.libraries = new Libraries[cursor.getCount()];
            int i = 0;
            do {
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_name_of_institute);
                int addressIndex = cursor.getColumnIndex(DBHelper.KEY_address_inst_cultur);
                String lib_name = cursor.getString(nameIndex);
                String lib_address = cursor.getString(nameIndex);
                this.libraries[i] = new Libraries(lib_name, lib_address);
                i++;
            } while(cursor.moveToNext());
        } else {
            Log.d("nLog", "Нет записей!");
        }
        cursor.close();
        dbHelper.close();*/


        this.libraries = new Libraries[3];

        String name = "Центральная городская библиотека";
        String adress = "Алтайский край, город Рубцовск, пр. Ленина, 137-А,Б";
        libraries[0] = new Libraries(name, adress);

        name = "Центральная детская библиотека";
        adress = "Алтайский край, город Рубцовск, пр. Ленина 53 «а»";
        libraries[1] = new Libraries(name, adress);

        name = "Библиотека семейного чтения «Лад»";
        adress = "Алтайский край, город Рубцовск, ул. Федоренко, 17в";
        libraries[2] = new Libraries(name, adress);

        AdapterLibraries adapterLibraries = new AdapterLibraries(this);
        ListView libList = (ListView) findViewById(R.id.libraryItems);
        libList.setAdapter(adapterLibraries);

    }

    class AdapterLibraries extends ArrayAdapter<Object> {
        Activity context;

        public AdapterLibraries(Activity context){
            super(context, R.layout.items_institution, libraries);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.items_institution, null);

            TextView tvNameInstitution = (TextView) item.findViewById(R.id.tvNameInstitution);
            tvNameInstitution.setText(libraries[position].getNameInstitution());
            TextView tvAdressInstitution = (TextView) item.findViewById(R.id.tvAdress);
            tvAdressInstitution.setText(libraries[position].getAdressInstitution());

            return  item;
        }
    }
}