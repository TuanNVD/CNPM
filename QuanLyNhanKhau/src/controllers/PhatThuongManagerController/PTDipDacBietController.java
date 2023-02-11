/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.PhatThuongManagerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PTDipDacBietModel;
import services.MysqlConnection;

/**
 *
 * @author TNTDT
 */
public class PTDipDacBietController {
    public static void phatQua(PTDipDacBietModel pt){
                try {
            Connection conn = null;
            PreparedStatement pstm = null;
            
            conn = MysqlConnection.getMysqlConnection();
            String sql = "INSERT INTO `pt_dip_dac_biet` (`IDHS`, `IDPhanQua`, `IDDipDacBiet`) VALUES (?,?,?)";
            pstm = conn.prepareCall(sql);
            pstm.setInt(1, pt.getIDHS());
            pstm.setInt(2, pt.getIDPhanQua());            
            pstm.setInt(3, pt.getIDDipDacBiet());

            
            pstm.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PTCuoiNamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
