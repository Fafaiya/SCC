package com.sandec.wakhyudi.scc.model;

/**
 * Created by wakhyudi on 22/01/18.
 */

public class Perusahaan {
    private String namaPerusahaan;
    private String linkIcon;
    private String description;

    public Perusahaan(String namaPerusahaan, String linkIcon, String description) {
        this.namaPerusahaan = namaPerusahaan;
        this.linkIcon = linkIcon;
        this.description = description;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public String getLinkIcon() {
        return linkIcon;
    }

    public String getDescription() {
        return description;
    }
}
