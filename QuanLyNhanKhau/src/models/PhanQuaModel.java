/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author TNTDT
 */
public class PhanQuaModel {
    
    private int ID;
    private String tenQua;
    private int giaTri;
    private int soLuong;
    private int soLuongDaPhat;

    public PhanQuaModel(int ID, String tenQua, int giaTri, int soLuong, int soLuongDaPhat) {
        this.ID = ID;
        this.tenQua = tenQua;
        this.giaTri = giaTri;
        this.soLuong = soLuong;
        this.soLuongDaPhat = soLuongDaPhat;
    }

    public PhanQuaModel(String tenQua, int giaTri, int soLuong) {
        this.tenQua = tenQua;
        this.giaTri = giaTri;
        this.soLuong = soLuong;
    }

    public PhanQuaModel() {
        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenQua() {
        return tenQua;
    }

    public void setTenQua(String tenQua) {
        this.tenQua = tenQua;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuongDaPhat() {
        return soLuongDaPhat;
    }

    public void setSoLuongDaPhat(int soLuongDaPhat) {
        this.soLuongDaPhat = soLuongDaPhat;
    }

    
    
    
}
