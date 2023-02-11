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
public class DipDacBietModel {
    private int ID;
    private String tenDipDacBiet;
    private Date ngayDienRa;

    public DipDacBietModel(int ID, String tenDipDacBiet, Date ngayDienRa) {
        this.ID = ID;
        this.tenDipDacBiet = tenDipDacBiet;
        this.ngayDienRa = ngayDienRa;
    }

    public DipDacBietModel(String tenDipDacBiet, Date ngayDienRa) {
        this.tenDipDacBiet = tenDipDacBiet;
        this.ngayDienRa = ngayDienRa;
    }

    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenDipDacBiet() {
        return tenDipDacBiet;
    }

    public void setTenDipDacBiet(String tenDipDacBiet) {
        this.tenDipDacBiet = tenDipDacBiet;
    }

    public Date getNgayDienRa() {
        return ngayDienRa;
    }

    public void setNgayDienRa(Date ngayDienRa) {
        this.ngayDienRa = ngayDienRa;
    }
    
    
}
