/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.PhatThuongManagerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DipDacBietModel;
import services.MysqlConnection;
import views.PhatThuongManagerFrame.QuanLyPhanQuaJFrame;

/**
 *
 * @author TNTDT
 */
public class DipDacBietController {

    public static List<DipDacBietModel> fillAll() {
        List<DipDacBietModel> lst = new ArrayList<>();

        Connection conn = null;
        Statement stm = null;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "SELECT * FROM dip_dac_biet";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                DipDacBietModel dip = new DipDacBietModel(
                        rs.getInt("ID"),
                        rs.getString("tenDipDacBiet"),
                        rs.getDate("ngayDienRa")
                );
                lst.add(dip);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhanQuaJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public static void themMoi(DipDacBietModel dip) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = MysqlConnection.getMysqlConnection();
            String sql = "INSERT INTO `dip_dac_biet` (`ID`, `tenDipDacBiet`, `ngayDienRa`) VALUES (NULL,?,?)";
            pstm = conn.prepareCall(sql);
            pstm.setString(1, dip.getTenDipDacBiet());
            pstm.setDate(2, dip.getNgayDienRa());
            pstm.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhanQuaJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void xoa(int id) {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "delete from dip_dac_biet where id = ?";
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhanQuaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void sua(int id, String ten, Date ngay){
                com.mysql.jdbc.Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = (com.mysql.jdbc.Connection) MysqlConnection.getMysqlConnection();
            String sql = "update dip_dac_biet set tenDipDacBiet = ?, ngayDienRa = ? where id = ?";
            pstm = conn.prepareCall(sql);
            pstm.setString(1, ten);
            pstm.setDate(2, ngay);
            pstm.setInt(3, id);
            pstm.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhanQuaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
