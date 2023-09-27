package com.example.project_akhir_redon_daffa.RecycleView;

public class Beasiswa {
    private  String foto;
    private String nama;
    private String tanggal;
    private String details;
    private String web;
    private String key;

    public  Beasiswa(){
    }
    public Beasiswa(String nama, String tanggal, String details, String web, String foto) {
        this.foto = foto;
        this.nama = nama;
        this.tanggal = tanggal;
        this.details = details;
        this.web = web;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        key = nama;
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getDetails() {
        return details;
    }

    public String getFoto() {
        return foto;
    }

    public String getWeb() { return web; }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setWeb(String web) {
        this.web = web;
    }

}
