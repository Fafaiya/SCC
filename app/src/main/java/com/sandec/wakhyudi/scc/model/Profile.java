package com.sandec.wakhyudi.scc.model;

/**
 * Created by wakhyudi on 22/01/18.
 */

public class Profile {
    private String namaMenu;
    private int iconMenu;

    public Profile(String namaMenu, int iconMenu) {
        this.namaMenu = namaMenu;
        this.iconMenu = iconMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public int getIconMenu() {
        return iconMenu;
    }
}
