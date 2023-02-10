/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.PhatThuongManagerController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.HocSinh;
import services.MysqlConnection;
import views.PhatThuongManagePanel;

/**
 *
 * @author TNTDT
 */
public class ChonHSController {

    public static List<HocSinh> findAll() {
        List<HocSinh> lstHs = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;

        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "SELECT ID, hoTen, gioiTinh, namSinh FROM `nhan_khau` WHERE YEAR(CURRENT_TIMESTAMP) - YEAR(namSinh) <= 18";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HocSinh hs = new HocSinh(
                        rs.getString("hoTen"),
                        rs.getDate("namSinh"),
                        rs.getString("gioiTinh"),
                        rs.getInt("ID")
                );
                lstHs.add(hs);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhatThuongManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstHs;
    }

    public static List<HocSinh> timKienTheoTen(String ten) {
        List<HocSinh> lstHs = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;

        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "SELECT ID, hoTen, gioiTinh, namSinh FROM `nhan_khau` WHERE YEAR(CURRENT_TIMESTAMP) - YEAR(namSinh) <= 18 AND hoTen LIKE '%" + ten + "%'";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HocSinh hs = new HocSinh(
                        rs.getString("hoTen"),
                        rs.getDate("namSinh"),
                        rs.getString("gioiTinh"),
                        rs.getInt("ID")
                );
                lstHs.add(hs);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhatThuongManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstHs;
    }

    public static List<HocSinh> timTen(String hoTen) {
        List<HocSinh> lstHs = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;

        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "SELECT ID, hoTen, gioiTinh, namSinh FROM `nhan_khau` WHERE YEAR(CURRENT_TIMESTAMP) - YEAR(namSinh) <= 18 AND hoTen = '" + hoTen + "'";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HocSinh hs = new HocSinh(
                        rs.getString("hoTen"),
                        rs.getDate("namSinh"),
                        rs.getString("gioiTinh"),
                        rs.getInt("ID")
                );
                lstHs.add(hs);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhatThuongManagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstHs;
    }
}
