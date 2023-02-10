/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.PhatThuongManagerFrame;

import controllers.PhatThuongManagerController.PhanQuaController;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.PhanQuaModel;
import services.MysqlConnection;

/**
 *
 * @author TNTDT
 */
public class QuanLyPhanQuaJFrame extends javax.swing.JFrame {

    private JFrame parentJFrame;
    List<PhanQuaModel> lstQua = new ArrayList<>();
    DefaultTableModel tableModel;

    /**
     * Creates new form ThongKePhanQuaJFrame
     */
    public QuanLyPhanQuaJFrame(JFrame parentJFrame) {
        initComponents();
        this.parentJFrame = parentJFrame;
        tableModel = (DefaultTableModel) tblPhanQua.getModel();
        tblPhanQua.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblPhanQua.setRowHeight(40);
        tblPhanQua.validate();
        tblPhanQua.repaint();
        tblPhanQua.setFont(new Font("Arial", Font.PLAIN, 14));
        tblPhanQua.getColumnModel().getColumn(0).setMaxWidth(40);
        tblPhanQua.getColumnModel().getColumn(0).setMinWidth(40);
        tblPhanQua.getColumnModel().getColumn(0).setPreferredWidth(80);
        parentJFrame.setEnabled(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentJFrame.setEnabled(true);
                close();
            }
        });
        countTong();
        countDaPhat();
        tongTien();
        showQua();
    }

    private void close() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure to close??", "Confirm", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            this.parentJFrame.setEnabled(true);
            dispose();
        }
    }

    public void countTong() {
        Connection conn = null;
        Statement stm = null;

        int count = 0;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select soLuong from phan_qua";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                count += rs.getInt("soLuong");
            }
            txtTong.setText(Integer.toString(count));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhanQuaJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void countDaPhat() {
        Connection conn = null;
        Statement stm = null;

        int count = 0;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select soLuongDaPhat from phan_qua";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                count += rs.getInt("soLuongDaPhat");
            }
            txtConLai.setText(Integer.toString(count));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhanQuaJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tongTien() {
        Connection conn = null;
        Statement stm = null;

        int tong = 0;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select giaTri, soLuong from phan_qua";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                tong = tong + rs.getInt("giatri") * rs.getInt("soLuong");
            }
            txtTien.setText(Integer.toString(tong));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhanQuaJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showQua() {
        lstQua = PhanQuaController.findAll();

        tableModel.setRowCount(0);
        lstQua.forEach((PhanQuaModel qua) -> {
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, qua.getTenQua(),
                qua.getGiaTri(), qua.getSoLuong(), qua.getSoLuongDaPhat()});
        });
    }

    public void setText() {
        txtTenQua.setText("");
        txtGiaTri.setText("");
        txtSoLuong.setText("");

    }

    public void mouseClick() {
        int selectIndex = tblPhanQua.getSelectedRow();
        if (selectIndex >= 0) {
            PhanQuaModel qua = lstQua.get(selectIndex);
            txtTenQua.setText(qua.getTenQua());
            txtGiaTri.setText(Integer.toString(qua.getGiaTri()));
            txtSoLuong.setText(Integer.toString(qua.getSoLuong()));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTong = new javax.swing.JLabel();
        txtConLai = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenQua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtGiaTri = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnThemMoi = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThemSoLuong = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhanQua = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("QUẢN LÝ PHẦN QUÀ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Số lượng phần quà:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Số phần quà đã phát:");

        txtTong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtConLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tên phần quà:");

        txtTenQua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Giá trị:");

        txtGiaTri.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("VNĐ");

        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Số lượng:");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThemSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemSoLuong.setText("Thêm số lượng");
        btnThemSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSoLuongActionPerformed(evt);
            }
        });

        tblPhanQua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Phần Quà", "Giá Trị (VNĐ)", "Số Lượng", "Đã Phát"
            }
        ));
        tblPhanQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanQuaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhanQua);
        if (tblPhanQua.getColumnModel().getColumnCount() > 0) {
            tblPhanQua.getColumnModel().getColumn(0).setMinWidth(50);
            tblPhanQua.getColumnModel().getColumn(0).setMaxWidth(50);
            tblPhanQua.getColumnModel().getColumn(3).setMinWidth(100);
            tblPhanQua.getColumnModel().getColumn(3).setMaxWidth(100);
            tblPhanQua.getColumnModel().getColumn(4).setMinWidth(100);
            tblPhanQua.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tổng số tiền:");

        txtTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("VNĐ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSoLuong))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTenQua))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(54, 54, 54)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThemSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTien, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTenQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoi))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemSoLuong))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnXoa))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        close();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        // TODO add your handling code here:
        String tenQua = txtTenQua.getText();
        String giatri = txtGiaTri.getText();
        String soluong = txtSoLuong.getText();

        if (!"".equals(tenQua) && !"".equals(giatri) && !"".equals(soluong)) {
            int giaTri = Integer.parseInt(giatri);
            int soLuong = Integer.parseInt(soluong);
            PhanQuaModel phanQua = new PhanQuaModel(tenQua, giaTri, soLuong);
            PhanQuaController.themMoiPhanQua(phanQua);
            JOptionPane.showMessageDialog(rootPane, "Thêm mới thành công");
            countTong();
            countDaPhat();
            tongTien();
            showQua();
            setText();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng điền đầy đủ thông tin");
        }

    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int selectIndex = tblPhanQua.getSelectedRow();
        if (selectIndex >= 0) {
            int option = JOptionPane.showConfirmDialog(rootPane, "Sửa thông tin?");
            PhanQuaModel qua = lstQua.get(selectIndex);
            String tenQua = txtTenQua.getText();
            String giatri = txtGiaTri.getText();
            String soluong = txtSoLuong.getText();
            if (!"".equals(tenQua) && !"".equals(giatri) && !"".equals(soluong) && option == 0) {
                int giaTri = Integer.parseInt(giatri);
                int soLuong = Integer.parseInt(soluong);
                PhanQuaController.sua(qua.getID(), tenQua, giaTri, soLuong);
                countTong();
                countDaPhat();
                tongTien();
                showQua();
                setText();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Điền thông tin");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chọn quà cần sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSoLuongActionPerformed
        // TODO add your handling code here:
        int selectIndex = tblPhanQua.getSelectedRow();
        if (selectIndex >= 0) {
            int option = JOptionPane.showConfirmDialog(rootPane, "Thêm số lượng quà");
            PhanQuaModel qua = lstQua.get(selectIndex);
            String soluongthem = txtSoLuong.getText();

            if (!"".equals(soluongthem) && option == 0) {
                int soLuongThem = Integer.parseInt(soluongthem);
                PhanQuaController.sua(qua.getID(), qua.getTenQua(), qua.getGiaTri(), soLuongThem + qua.getSoLuong());
                countTong();
                countDaPhat();
                tongTien();
                showQua();
                setText();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Điền số lượng cần thêm");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Chọn quà cần sửa");
        }
    }//GEN-LAST:event_btnThemSoLuongActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int selectIndex = tblPhanQua.getSelectedRow();
        if (selectIndex >= 0) {
            PhanQuaModel qua = lstQua.get(selectIndex);
            int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete this item");
            if (option == 0) {
                PhanQuaController.xoa(qua.getID());
                countTong();
                countDaPhat();
                tongTien();
                showQua();
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblPhanQuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanQuaMouseClicked
        // TODO add your handling code here:
        mouseClick();
    }//GEN-LAST:event_tblPhanQuaMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThongKePhanQuaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThongKePhanQuaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThongKePhanQuaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThongKePhanQuaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new QuanLyPhanQuaJFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnThemSoLuong;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPhanQua;
    private javax.swing.JLabel txtConLai;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenQua;
    private javax.swing.JLabel txtTien;
    private javax.swing.JLabel txtTong;
    // End of variables declaration//GEN-END:variables
}
