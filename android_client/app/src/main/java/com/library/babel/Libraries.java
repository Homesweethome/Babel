package com.library.babel;

public class Libraries {
    private String nameInstitution;
    private String adressInstitution;

    public Libraries(String nameInstitution, String adressInstitution) {
        this.nameInstitution = nameInstitution;
        this.adressInstitution = adressInstitution;
    }

    public String getNameInstitution() {
        return nameInstitution;
    }

    public String getAdressInstitution() {
        return adressInstitution;
    }
}
