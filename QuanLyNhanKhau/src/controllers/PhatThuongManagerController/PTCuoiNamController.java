/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.PhatThuongManagerController;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PhatThuongCuoiNamModel;
import services.MysqlConnection;
import views.PhatThuongManagerFrame.QuanLyPhanQuaJFrame;

/**
 *
 * @author TNTDT
 */
public class PTCuoiNamController {

    public static void phatThuong(PhatThuongCuoiNamModel pt) {

        try {
            Connection conn = null;
            PreparedStatement pstm = null;
            
            conn = MysqlConnection.getMysqlConnection();
            String sql = "INSERT INTO `phat_thuong_cuoi_nam` (`IDHS`, `IDPhanQua`) VALUES (?,?)";
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, pt.getIDHS());
            pstm.setInt(2, pt.getIDPhanQua());
            pstm.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PTCuoiNamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
