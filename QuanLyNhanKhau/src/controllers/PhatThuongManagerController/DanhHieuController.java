/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.PhatThuongManagerController;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DanhHieuModel;
import services.MysqlConnection;

/**
 *
 * @author TNTDT
 */
public class DanhHieuController {

    public static void themDanhHieu(DanhHieuModel danhhieu) {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "insert into danh_hieu (IDNhanKhau, hoTen, namSinh, danhHieu, ngayGhiNhanDanhHieu) values (?,?,?,?,?)";
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, danhhieu.getIDNhanKhau());
            pstm.setString(2, danhhieu.getHoTen());
            pstm.setDate(3, (Date) danhhieu.getNamSinh());
            pstm.setString(4, danhhieu.getDanhHieu());
            pstm.setDate(5, (Date) danhhieu.getNgayGhiNhanDanhHieu());
            pstm.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DanhHieuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getID(DanhHieuModel danhhieu) {
        Connection conn = null;
        Statement stm = null;
        int id = -1;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select ID, hoTen from danh_hieu";
            stm = conn.createStatement();

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("hoTen").equals(danhhieu.getHoTen())) {
                    id = rs.getInt("ID");
                    break;
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DanhHieuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
