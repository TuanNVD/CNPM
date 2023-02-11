/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.PhatThuongManagerFrame;

import controllers.PhatThuongManagerController.ChonHSController;
import controllers.PhatThuongManagerController.PTDipDacBietController;
import controllers.PhatThuongManagerController.PhanQuaController;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.HocSinh;
import models.PTDipDacBietModel;
import models.PhanQuaModel;
import services.MysqlConnection;

/**
 *
 * @author TNTDT
 */
public class PTDacBietJrame extends javax.swing.JFrame {

    private JFrame parentJFrame;
    DefaultTableModel tableModel;
    List<HocSinh> lstHs;

    /**
     * Creates new form PTCuoiNamJFrame
     *
     * @param parentJFrame
     */
    public PTDacBietJrame(JFrame parentJFrame) {
        initComponents();
        this.parentJFrame = parentJFrame;
        tableModel = (DefaultTableModel) tblNhanThuong.getModel();
        tblNhanThuong.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblNhanThuong.setRowHeight(30);
        tblNhanThuong.validate();
        tblNhanThuong.repaint();
        tblNhanThuong.setFont(new Font("Arial", Font.PLAIN, 14));
        tblNhanThuong.getColumnModel().getColumn(0).setMaxWidth(40);
        tblNhanThuong.getColumnModel().getColumn(0).setMinWidth(40);
        tblNhanThuong.getColumnModel().getColumn(0).setPreferredWidth(80);

        parentJFrame.setEnabled(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentJFrame.setEnabled(true);
                close();
            }
        });
        showHSNhanThuong();
        initComboBox();
        initComboBox2();
        txtConLai.setText(soLuongConLai(idQua(boxQua.getSelectedItem().toString())));
    }

    private void close() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure to close??", "Confirm", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            this.parentJFrame.setEnabled(true);
            dispose();
        }
    }

    public void showHSNhanThuong() {

        Connection conn = null;
        Statement stm = null;
        lstHs = new ArrayList<>();

        try {

            conn = MysqlConnection.getMysqlConnection();
            String sql = "select ID, hoTen, ROUND(Datediff(CURDATE(), namSinh)/365,0) as tuoi from nhan_khau where ROUND(Datediff(CURDATE(), namSinh)/365,0) <= 18";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0);
            while (rs.next()) {
//                tableModel.addRow(new Object[]{
//                    tableModel.getRowCount() + 1,
//                    rs.getInt("ID"),
//                    rs.getString("hoTen"),
//                    rs.getString("tuoi"),});
                HocSinh hs = new HocSinh(
                        rs.getString("hoTen"),
                        rs.getInt("ID"),
                        rs.getInt("tuoi")
                );
                lstHs.add(hs);
            }
            tableModel.fireTableDataChanged();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tableModel.setRowCount(0);
        lstHs.forEach((HocSinh hss) -> {
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, hss.getIdNhanKhau(),
                hss.getHoTen(), hss.getTuoi()});
        });
    }

    private void initComboBox() {
        Connection conn = null;
        Statement stm = null;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select tenQua, soLuong, soLuongDaPhat from phan_qua";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            boxQua.removeAllItems();
            while (rs.next()) {
                boxQua.addItem(rs.getString("tenQua"));

            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void initComboBox2() {
        Connection conn = null;
        Statement stm = null;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select tenDipDacBiet from dip_dac_biet";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            boxDipDB.removeAllItems();
            while (rs.next()) {
                boxDipDB.addItem(rs.getString("tenDipDacBiet"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private int idDip(String dip) {
        int idDip = -1;
        Connection conn = null;
        Statement stm = null;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select ID, tenDipDacBiet from dip_dac_biet";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("tenDipDacBiet").equals(dip)) {
                    idDip = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return idDip;
    }

    private String soLuongConLai(int id) {
        Connection conn = null;
        Statement stm = null;
        String conlai = "";
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select ID, soLuong, soLuongDaPhat from phan_qua";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("ID") == id) {
                    int cl = rs.getInt("soLuong") - rs.getInt("soLuongDaPhat");
                    conlai = Integer.toString(cl);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return conlai;
    }

    private int idQua(String qua) {
        int idQua = -1;
        Connection conn = null;
        Statement stm = null;
        try {
            conn = MysqlConnection.getMysqlConnection();
            String sql = "select ID, tenQua from phan_qua";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("tenQua").equals(qua)) {
                    idQua = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return idQua;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanThuong = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        boxQua = new javax.swing.JComboBox<>();
        txtPhatQua = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        boxDipDB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtConLai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phát thưởng dịp đặc biệt");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNhanThuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Nhân Khẩu", "Họ Tên", "Tuổi"
            }
        ));
        tblNhanThuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanThuongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanThuong);
        if (tblNhanThuong.getColumnModel().getColumnCount() > 0) {
            tblNhanThuong.getColumnModel().getColumn(0).setMinWidth(50);
            tblNhanThuong.getColumnModel().getColumn(0).setMaxWidth(50);
            tblNhanThuong.getColumnModel().getColumn(1).setMinWidth(120);
            tblNhanThuong.getColumnModel().getColumn(1).setMaxWidth(120);
            tblNhanThuong.getColumnModel().getColumn(3).setMinWidth(120);
            tblNhanThuong.getColumnModel().getColumn(3).setMaxWidth(120);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 570, 268));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText(" Danh sách nhân khẩu nhận quà (đang trong độ tuổi học tập)");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 550, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Phát quà cho học sinh dịp đặc biệt");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 15, 399, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("Phần quà:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 94, -1));

        boxQua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        boxQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boxQuaMouseClicked(evt);
            }
        });
        boxQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxQuaActionPerformed(evt);
            }
        });
        jPanel1.add(boxQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 150, -1));

        txtPhatQua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPhatQua.setText("Phát Quà");
        txtPhatQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhatQuaActionPerformed(evt);
            }
        });
        jPanel1.add(txtPhatQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 118, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("Dịp phát quà:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 127, -1));

        boxDipDB.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(boxDipDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 340, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText(" Số lượng còn lại:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 120, -1));

        txtConLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtConLai.setText("jLabel9");
        jPanel1.add(txtConLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void boxQuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxQuaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_boxQuaMouseClicked

    private void tblNhanThuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanThuongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblNhanThuongMouseClicked

    private void txtPhatQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhatQuaActionPerformed
        // TODO add your handling code here:
        String dipPhatQua = boxDipDB.getSelectedItem().toString();
        String phanQua = boxQua.getSelectedItem().toString();
        lstHs = ChonHSController.findAll();
        int idDipDacBiet = this.idDip(dipPhatQua);
        int idQua = this.idQua(phanQua);
        int sl = lstHs.size();
        if (PhanQuaController.check(idQua, sl)) {
            JOptionPane.showMessageDialog(rootPane, "Không đủ phần quà, cần thêm số lượng quà");
        } else {
            if (idQua != -1 || idDipDacBiet != -1) {
                if (!"".equals(dipPhatQua) && !"".equals(phanQua)) {

                    lstHs.forEach((HocSinh hss) -> {
                        
                        PTDipDacBietModel pt = new PTDipDacBietModel(hss.getIdNhanKhau(), idQua, idDipDacBiet);
                        PTDipDacBietController.phatQua(pt);
                        PhanQuaModel qua = PhanQuaController.find(idQua);
                        PhanQuaController.tangSoLuongDaPhat(qua);

                    });
                    txtConLai.setText(soLuongConLai(idQua(boxQua.getSelectedItem().toString())));
                    JOptionPane.showMessageDialog(rootPane, "Phát thưởng thành công");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Điền thông tin");
                }
            }
        }
    }//GEN-LAST:event_txtPhatQuaActionPerformed

    private void boxQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxQuaActionPerformed
        // TODO add your handling code here:
        txtConLai.setText(soLuongConLai(idQua(boxQua.getSelectedItem().toString())));
    }//GEN-LAST:event_boxQuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxDipDB;
    private javax.swing.JComboBox<String> boxQua;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhanThuong;
    private javax.swing.JLabel txtConLai;
    private javax.swing.JButton txtPhatQua;
    // End of variables declaration//GEN-END:variables
}
