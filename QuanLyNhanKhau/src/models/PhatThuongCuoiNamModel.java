/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author TNTDT
 */
public class PhatThuongCuoiNamModel {
    
    private int IDHS;
    private int IDPhanQua;

    public PhatThuongCuoiNamModel(int IDHS, int IDPhanQua) {
        this.IDHS = IDHS;
        this.IDPhanQua = IDPhanQua;
    }

    public int getIDHS() {
        return IDHS;
    }

    public void setIDHS(int IDHS) {
        this.IDHS = IDHS;
    }

    public int getIDPhanQua() {
        return IDPhanQua;
    }

    public void setIDPhanQua(int IDPhanQua) {
        this.IDPhanQua = IDPhanQua;
    }
    
    
    
}
