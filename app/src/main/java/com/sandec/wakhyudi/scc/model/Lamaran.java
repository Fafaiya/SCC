package com.sandec.wakhyudi.scc.model;

/**
 * Created by wakhyudi on 23/01/18.
 */

public class Lamaran {
    private String idPelamar;
    private String nikPelamar;
    private String namaPelamar;
    private String cvPelamar;
    private String emailPelamar;
    private String perusahaanDilamar;
    private String iconPerusahaan;

    public Lamaran(String idPelamar, String nikPelamar, String namaPelamar, String cvPelamar, String emailPelamar, String perusahaanDilamar, String iconPerusahaan) {
        this.idPelamar = idPelamar;
        this.nikPelamar = nikPelamar;
        this.namaPelamar = namaPelamar;
        this.cvPelamar = cvPelamar;
        this.emailPelamar = emailPelamar;
        this.perusahaanDilamar = perusahaanDilamar;
        this.iconPerusahaan = iconPerusahaan;
    }

    public String getIdPelamar() {
        return idPelamar;
    }

    public String getNikPelamar() {
        return nikPelamar;
    }

    public String getNamaPelamar() {
        return namaPelamar;
    }

    public String getCvPelamar() {
        return cvPelamar;
    }

    public String getEmailPelamar() {
        return emailPelamar;
    }

    public String getPerusahaanDilamar() {
        return perusahaanDilamar;
    }

    public String getIconPerusahaan() {
        return iconPerusahaan;
    }
}
