package com.library.babel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "bable";

    /*types_of_ premises*/
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME_OF_PREMISES = "name_of_premises";
    public static final String KEY_NUMBER_OF_PREMISES = "number_of_premises";
    public static final String KEY_ID_INST_CULT = "id_instcult";
    public static final String KEY_PATH_TO_SCHEMA = "path_to_schema";

    /*city*/
    public static final String TABLE_CITY = "city";
    public static final String KEY_ID_CITY = "_id";
    public static final String KEY_NAME_CITY = "name_city";
    public static final String KEY_NL = "nl";
    public static final String KEY_SL = "sl";

    /*institut_of_culture*/
    public static final String TABLE_INSTITUT_OF_CULTURE = "institut_of_culture";
    public static final String KEY_ID_INSTCULT = "_id";
    public static final String KEY_name_of_institute = "name_of_institute";
    public static final String KEY_address_inst_cultur = "address_inst_cultur";
    public static final String KEY_path_to_image = "path_to_image";
    public static final String KEY_idcity = "idcity";
    public static final String KEY_idtype_of_institution = "idtype_of_institution";

    /*point_perm_detect_wifi*/
    public static final String KEY_ID_point_perm_detect_wifi = "_id";
    public static final String KEY_id_pint_in_mg = "id_pint_in_mg";
    public static final String KEY_ssid = "ssid";
    public static final String KEY_level = "level";

    /*points_in_premises*/
    public static final String KEY_ID_points_in_premises = "_id";
    public static final String KEY_id_permisses = "id_permissese";
    public static final String KEY_img_point_coordinates_x = "img_point_coordinates_x";
    public static final String KEY_img_point_coordinates_y = "img_point_coordinates_y";

    /*type_of_institution*/
    public static final String KEY_ID_type_of_institution = "_id";
    public static final String KEY_name_of_type = "name_of_type";

    /*types_of_rescult*/
    public static final String KEY_ID_types_of_rescult = "_id";
    public static final String KEY_name_type_of_resculture = "name_type_of_resculture";
    public static final String KEY_id_instcult = "id_instcult";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE city (\n" +
                "    _id       INTEGER       PRIMARY KEY AUTOINCREMENT\n" +
                "                            NOT NULL ON CONFLICT ROLLBACK\n" +
                "                            UNIQUE,\n" +
                "    name_city VARCHAR (250),\n" +
                "    nl        DECIMAL       DEFAULT (0),\n" +
                "    sl        DECIMAL       DEFAULT (0) \n" +
                ");\n" +
                "\n" +
                "CREATE TABLE type_of_institution (\n" +
                "    _id          INTEGER       PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT\n" +
                "                               UNIQUE,\n" +
                "    name_of_type VARCHAR (250) \n" +
                ");\n" +
                "\n" +
                "CREATE TABLE institut_of_culture (\n" +
                "    _id                   INTEGER       PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT\n" +
                "                                        UNIQUE ON CONFLICT ROLLBACK\n" +
                "                                        NOT NULL,\n" +
                "    name_of_institute     VARCHAR (250) NOT NULL ON CONFLICT ROLLBACK,\n" +
                "    address_inst_cultur     VARCHAR (250) NOT NULL ON CONFLICT ROLLBACK,\n" +
                "    path_to_image         VARCHAR (250) DEFAULT [not img],\n" +
                "    idcity                INTEGER       REFERENCES city (_id) ON DELETE SET NULL\n" +
                "                                                              ON UPDATE CASCADE,\n" +
                "    idtype_of_institution INTEGER       REFERENCES type_of_institution (_id) ON DELETE SET NULL\n" +
                "                                                                             ON UPDATE CASCADE\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE types_of_rescult (\n" +
                "    _id                     INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT\n" +
                "                                    NOT NULL ON CONFLICT ROLLBACK\n" +
                "                                    UNIQUE ON CONFLICT ROLLBACK,\n" +
                "    name_type_of_resculture VARCHAR,\n" +
                "    id_instcult             INTEGER REFERENCES institut_of_culture (_id) ON DELETE SET NULL\n" +
                "                                                                         ON UPDATE CASCADE\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE types_of_ premises (\n" +
                "    _id                INTEGER       PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT\n" +
                "                                     UNIQUE ON CONFLICT ROLLBACK\n" +
                "                                     NOT NULL ON CONFLICT ROLLBACK,\n" +
                "    name_of_premises   VARCHAR (250),\n" +
                "    number_of_premises VARCHAR (10),\n" +
                "    id_instcult        INTEGER       REFERENCES institut_of_culture (_id) ON DELETE SET NULL\n" +
                "                                                                          ON UPDATE CASCADE,\n" +
                "    path_to_schema     VARCHAR (500) \n" +
                ");\n" +
                "\n" +
                "CREATE TABLE points_in_premises (\n" +
                "    _id                     INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT\n" +
                "                                    UNIQUE ON CONFLICT ROLLBACK\n" +
                "                                    NOT NULL ON CONFLICT ROLLBACK,\n" +
                "    id_permisses            INTEGER REFERENCES [\n" +
                "types_of_ premises] (_id) ON DELETE SET NULL\n" +
                "                                                                           ON UPDATE CASCADE,\n" +
                "    img_point_coordinates_x DECIMAL DEFAULT (0),\n" +
                "    img_point_coordinates_y DECIMAL DEFAULT (0) \n" +
                ");\n" +
                "\n" +
                "CREATE TABLE point_perm_detect_wifi (\n" +
                "    _id           INTEGER       PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT\n" +
                "                                UNIQUE ON CONFLICT ROLLBACK\n" +
                "                                NOT NULL ON CONFLICT ROLLBACK,\n" +
                "    id_pint_in_mg INTEGER       REFERENCES points_in_premises (_id) ON DELETE SET NULL\n" +
                "                                                                    ON UPDATE CASCADE,\n" +
                "    ssid          VARCHAR (250) DEFAULT [not ssid],\n" +
                "    level         DECIMAL       DEFAULT (0) \n" +
                ");\n");

        db.execSQL("INSERT INTO city (\n" +
                "                     name_city\n" +
                "                 )\n" +
                "                 VALUES (\n" +
                "                     'Алтайский край, город Рубцовск'\n" +
                "                 );\n");
        db.execSQL("INSERT INTO city (\n" +
                "                     name_city\n" +
                "                 )\n" +
                "                 VALUES (\n" +
                "                     'город Новосибирск'\n" +
                "                 );\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exits institut_of_culture");

        sqLiteDatabase.execSQL("CREATE TABLE institut_of_culture _id  INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT UNIQUE ON CONFLICT ROLLBACK  NOT NULL, " +
                " name_of_institute VARCHAR (250) NOT NULL ON CONFLICT ROLLBACK, address_inst_cultur VARCHAR (250) NOT NULL ON CONFLICT ROLLBACK, path_to_image VARCHAR (250) DEFAULT [not img], " +
                " idcity  INTEGER REFERENCES city (_id) ON DELETE SET NULL  ON UPDATE CASCADE, idtype_of_institution INTEGER  REFERENCES type_of_institution (_id) ON DELETE SET NULL ON UPDATE CASCADE");


        sqLiteDatabase.execSQL("NSERT INTO institut_of_culture (\n" +
                "                                    name_of_institute,\n" +
                "                                    idcity,\n" +
                "                                    address_inst_cultur\n" +
                "                                )\n" +
                "                                VALUES (\n" +
                "                                    'Центральная городская библиотека',\n" +
                "                                    '1',\n" +
                "                                    'Алтайский край, город Рубцовск, пр. Ленина, 137-А,Б'\n" +
                "                                );");
        sqLiteDatabase.execSQL("NSERT INTO institut_of_culture (\n" +
                "                                    name_of_institute,\n" +
                "                                    idcity,\n" +
                "                                    address_inst_cultur\n" +
                "                                )\n" +
                "                                VALUES (\n" +
                "                                    'Центральная детская библиотека',\n" +
                "                                    '1',\n" +
                "                                    'Алтайский край, город Рубцовск, пр. Ленина 53 «а»'\n" +
                "                                );");
        sqLiteDatabase.execSQL("NSERT INTO institut_of_culture (\n" +
                "                                    name_of_institute,\n" +
                "                                    idcity,\n" +
                "                                    address_inst_cultur\n" +
                "                                )\n" +
                "                                VALUES (\n" +
                "                                    'Библиотека семейного чтения «Лад»',\n" +
                "                                    '1',\n" +
                "                                    'Алтайский край, город Рубцовск, ул. Федоренко, 17в'\n" +
                "                                );");


    }
}
