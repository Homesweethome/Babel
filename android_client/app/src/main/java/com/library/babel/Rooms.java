package com.library.babel;

public class Rooms {
    private int id;
    private String nameRoom;
    private String numberRoom;
    private int id_inst_cult;
    private String schema;

    public int getId() {
        return id;
    }
    public String getNameRoom() {
        return nameRoom;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public int getId_inst_cult() {
        return id_inst_cult;
    }

    public String getSchema() {
        return schema;
    }

    public Rooms(int id, String nameRoom, String numberRoom, int id_inst_cult, String schema) {
        this.id = id;
        this.nameRoom = nameRoom;
        this.numberRoom = numberRoom;
        this.id_inst_cult = id_inst_cult;
        this.schema = schema;
    }
}
