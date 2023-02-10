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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PhanQuaModel;
import services.MysqlConnection;
import views.PhatThuongManagerFrame.QuanLyPhanQuaJFrame;

/**
 *
 * @author TNTDT
 */
public class PhanQuaController {

    public static void themMoiPhanQua(PhanQuaModel phanQua) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            conn = MysqlConnection.getMysqlConnection();
            String sql = "INSERT INTO `phan_qua` (`ID`, `tenQua`, `giaTri`, `soLuong`, `soLuongDaPhat`) VALUES (NULL,?,?,?,?)";
            pstm = conn.prepareCall(sql);
            pstm.setString(1, phanQua.getTenQua());
            pstm.setString(2, Integer.toString(phanQua.getGiaTri()));
            pstm.setString(3, Integer.toString(phanQua.getSoLuong()));
            pstm.setString(4, Integer.toString(phanQua.getSoLuongDaPhat()));
            pstm.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhanQuaJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<PhanQuaModel> findAll() {

        List<PhanQuaModel> lstQua = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "SELECT * FROM phan_qua";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                PhanQuaModel qua = new PhanQuaModel(
                        rs.getInt("ID"),
                        rs.getString("tenQua"),
                        rs.getInt("giaTri"),
                        rs.getInt("soLuong"),
                        rs.getInt("soLuongDaPhat")
                );
                lstQua.add(qua);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhanQuaJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstQua;
    }

    public static void sua(int id, String tenqua, int giatri, int soluong) {
        com.mysql.jdbc.Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = (com.mysql.jdbc.Connection) MysqlConnection.getMysqlConnection();
            String sql = "update phan_qua set tenQua = ?, giaTri = ?, soLuong = ? where id = ?";
            pstm = conn.prepareCall(sql);
            pstm.setString(1, tenqua);
            pstm.setInt(2, giatri);
            pstm.setInt(3, soluong);
            pstm.setInt(4, id);
            pstm.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhanQuaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void xoa(int id) {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "delete from phan_qua where id = ?";
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhanQuaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void tangSoLuongDaPhat(PhanQuaModel qua) {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "UPDATE `phan_qua` SET `soLuongDaPhat` = ? WHERE `phan_qua`.`ID` = ?";
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, qua.getSoLuongDaPhat() + 1);
            pstm.setInt(2, qua.getID());
            pstm.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhanQuaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PhanQuaModel find(int idQua) {
        PhanQuaModel qua = new PhanQuaModel();
        Connection conn = null;
        Statement stm = null;
        String id = Integer.toString(idQua);
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select * from phan_qua where ID = " + id;
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("ID") == idQua) {
                    qua = new PhanQuaModel(rs.getInt("ID"),
                            rs.getString("tenQua"),
                            rs.getInt("giaTri"),
                            rs.getInt("soLuong"),
                            rs.getInt("soLuongDaPhat")
                    );
                    break;
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhanQuaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qua;
    }

    public static boolean check(int idQua) {
        
        boolean b = false;
        Connection conn = null;
        Statement stm = null;
        String id = Integer.toString(idQua);
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select ID, soLuong, soLuongDaPhat from phan_qua where ID = "+id;
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("ID") == idQua) {
                    if(rs.getInt("soLuong") <= rs.getInt("soLuongDaPhat")){
                        b = true;
                    }                     
                    break;
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PhanQuaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

}
