/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author TNTDT
 */
public class DanhHieuModel {

    private int ID;
    private int IDNhanKhau;
    private String hoTen;
    private Date namSinh;
    private String danhHieu;
    private Date ngayGhiNhanDanhHieu;

    public DanhHieuModel(int ID, String hoTen, Date namSinh, String danhHieu, Date ngayGhiNhanDanhHieu) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.danhHieu = danhHieu;
        this.ngayGhiNhanDanhHieu = ngayGhiNhanDanhHieu;
    }

    public DanhHieuModel(String hoTen, Date namSinh, String danhHieu, Date ngayGhiNhanDanhHieu) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.danhHieu = danhHieu;
        this.ngayGhiNhanDanhHieu = ngayGhiNhanDanhHieu;
    }

    public DanhHieuModel(int IDNhanKhau, String hoTen, Date namSinh, Date ngayGhiNhanDanhHieu) {
        this.IDNhanKhau = IDNhanKhau;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.ngayGhiNhanDanhHieu = ngayGhiNhanDanhHieu;
    }
    
    

    public int getIDNhanKhau() {
        return ID;
    }

    public void setIDNhanKhau(int IDnk) {
        this.IDNhanKhau = IDnk;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getDanhHieu() {
        return danhHieu;
    }

    public void setDanhHieu(String danhHieu) {
        this.danhHieu = danhHieu;
    }

    public Date getNgayGhiNhanDanhHieu() {
        return ngayGhiNhanDanhHieu;
    }

    public void setNgayGhiNhanDanhHieu(Date ngayGhiNhanDanhHieu) {
        this.ngayGhiNhanDanhHieu = ngayGhiNhanDanhHieu;
    }

}
