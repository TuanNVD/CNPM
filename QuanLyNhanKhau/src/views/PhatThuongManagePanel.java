/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import controllers.PhatThuongManagerController.ChonHSController;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import models.HocSinh;
import services.MysqlConnection;
import views.PhatThuongManagerFrame.PTCuoiNamJFrame;
import views.PhatThuongManagerFrame.QuanLyPhanQuaJFrame;

/**
 *
 * @author TNTDT
 */
public class PhatThuongManagePanel extends javax.swing.JPanel {
    
    private JFrame parentFrame;
    DefaultTableModel tableModel;
    List<HocSinh> lstHs;
    /**
     * Creates new form HoKhauManagePanel
     * @param parentFrame
     */
    public PhatThuongManagePanel (JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();
        tableModel = (DefaultTableModel) tblHs.getModel();
        tblHs.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblHs.setRowHeight(40);
        tblHs.validate();
        tblHs.repaint();
        tblHs.setFont(new Font("Arial", Font.PLAIN, 14));
        tblHs.getColumnModel().getColumn(0).setMaxWidth(40);
        tblHs.getColumnModel().getColumn(0).setMinWidth(40);
        tblHs.getColumnModel().getColumn(0).setPreferredWidth(80);
        show();
    }

    /**
     * Creates new form PhatThuongManagePanel
     */
    
    public void show(){
        lstHs = new ArrayList<>();
        lstHs = ChonHSController.findAll();
        tableModel.setRowCount(0);
        lstHs.forEach((HocSinh hss) -> {
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, hss.getIdNhanKhau(),
                hss.getHoTen(), hss.getGioiTinh(), hss.getNamSinh()});
        });

        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHs = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnPhatThuongCuoiNam = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnQuanLyPhanQua = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ CẤP PHÁT PHẦN THƯỞNG");

        tblHs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Nhân Khẩu", "Họ và tên", "Giới Tính", "Ngày sinh"
            }
        ));
        tblHs.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tblHs);
        if (tblHs.getColumnModel().getColumnCount() > 0) {
            tblHs.getColumnModel().getColumn(0).setMinWidth(30);
            tblHs.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblHs.getColumnModel().getColumn(0).setMaxWidth(30);
            tblHs.getColumnModel().getColumn(1).setMinWidth(120);
            tblHs.getColumnModel().getColumn(1).setMaxWidth(120);
            tblHs.getColumnModel().getColumn(3).setMinWidth(80);
            tblHs.getColumnModel().getColumn(3).setMaxWidth(80);
            tblHs.getColumnModel().getColumn(4).setMinWidth(100);
            tblHs.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Danh sách nhân khẩu trong độ tuổi học tập");

        btnPhatThuongCuoiNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhatThuongCuoiNam.setText("PHÁT THƯỞNG CUỐI NĂM");
        btnPhatThuongCuoiNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhatThuongCuoiNamActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("PHÁT THƯỞNG DỊP ĐẶC BIỆT");

        btnQuanLyPhanQua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanLyPhanQua.setText("QUẢN LÝ PHẦN QUÀ");
        btnQuanLyPhanQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyPhanQuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPhatThuongCuoiNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuanLyPhanQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnQuanLyPhanQua, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnPhatThuongCuoiNam, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPhatThuongCuoiNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhatThuongCuoiNamActionPerformed
        // TODO add your handling code here:
        PTCuoiNamJFrame ptCuoiNam = new PTCuoiNamJFrame(parentFrame);
        ptCuoiNam.setLocationRelativeTo(null);
        ptCuoiNam.setResizable(false);
        ptCuoiNam.setVisible(true);
        
    }//GEN-LAST:event_btnPhatThuongCuoiNamActionPerformed

    private void btnQuanLyPhanQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyPhanQuaActionPerformed
        // TODO add your handling code here:
        QuanLyPhanQuaJFrame qlPhanQua = new QuanLyPhanQuaJFrame(parentFrame);
        qlPhanQua.setLocationRelativeTo(null);
        qlPhanQua.setResizable(false);
        qlPhanQua.setVisible(true);
        
    }//GEN-LAST:event_btnQuanLyPhanQuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPhatThuongCuoiNam;
    private javax.swing.JButton btnQuanLyPhanQua;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHs;
    // End of variables declaration//GEN-END:variables
}
