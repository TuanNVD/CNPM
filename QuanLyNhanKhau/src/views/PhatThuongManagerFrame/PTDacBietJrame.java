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
import static views.PhatThuongManagerFrame.ChonQuaJFrame.idQua;

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
        parentJFrame.setEnabled(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentJFrame.setEnabled(true);
                close();
            }

        });
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

    public String chonHs() {
        int selectIndex = tblNhanThuong.getSelectedRow();
        String tenHs = "";
        if (selectIndex >= 0) {
            HocSinh hs = lstHs.get(selectIndex);
            tenHs = hs.getHoTen();
        }
        return tenHs;
    }

    public String chonTuoi() {
        int selectIndex = tblNhanThuong.getSelectedRow();
        String tuoi = "";
        if (selectIndex >= 0) {
            HocSinh hs = lstHs.get(selectIndex);
            tuoi = Integer.toString(hs.getTuoi());
        }
        return tuoi;
    }

    public String chonIDNK() {
        int selectIndex = tblNhanThuong.getSelectedRow();
        String ID = "";
        if (selectIndex >= 0) {
            HocSinh hs = lstHs.get(selectIndex);
            ID = Integer.toString(hs.getIdNhanKhau());
        }
        return ID;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtIdNK = new javax.swing.JTextField();
        txtTuoi = new javax.swing.JTextField();
        boxQua = new javax.swing.JComboBox<>();
        txtPhatQua = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        boxDipDB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtConLai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phát thưởng dịp đặc biệt");

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

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText(" Danh sách học sinh");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Phát quà cho học sinh dịp đặc biệt");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Họ Tên:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("ID Nhân Khẩu:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tuổi:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Phần quà:");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtIdNK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIdNK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNKActionPerformed(evt);
            }
        });

        txtTuoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        txtPhatQua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPhatQua.setText("Phát Quà");
        txtPhatQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhatQuaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("Dịp phát quà:");

        boxDipDB.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("còn lại:");

        txtConLai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConLai.setText("jLabel9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTen)
                                .addComponent(txtIdNK)
                                .addComponent(txtTuoi)
                                .addComponent(boxDipDB, 0, 364, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(boxQua, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtPhatQua, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(boxDipDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdNK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boxQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtConLai))
                    .addComponent(jLabel6))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPhatQua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtIdNKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNKActionPerformed

    private void boxQuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxQuaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_boxQuaMouseClicked

    private void tblNhanThuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanThuongMouseClicked
        // TODO add your handling code here:
        String s1 = this.chonHs();
        String s2 = this.chonTuoi();
        String s3 = this.chonIDNK();
        if (!"".equals(s1)) {
            txtTen.setText(s1);
            txtTuoi.setText(s2);
            txtIdNK.setText(s3);
        }

    }//GEN-LAST:event_tblNhanThuongMouseClicked

    private void txtPhatQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhatQuaActionPerformed
        // TODO add your handling code here:
        String dipPhatQua = boxDipDB.getSelectedItem().toString();
        String ten = txtTen.getText();
        String idNhanKhau = txtIdNK.getText();
        String tuoi = txtTuoi.getText();
        String phanQua = boxQua.getSelectedItem().toString();

        int idDipDacBiet = this.idDip(dipPhatQua);
        int idQua = this.idQua(phanQua);
        
        if (PhanQuaController.check(idQua)) {
            JOptionPane.showMessageDialog(rootPane, "Phần quà chọn đã hết");
        } else {
            if (idQua != -1 || idDipDacBiet != -1) {
                if (!"".equals(dipPhatQua) && !"".equals(ten) && !"".equals(idNhanKhau)
                        && !"".equals(tuoi) && !"".equals(phanQua)) {
                    int idnk = Integer.parseInt(idNhanKhau);
                    PTDipDacBietModel pt = new PTDipDacBietModel(idnk, idQua, idDipDacBiet);
                    PTDipDacBietController.phatQua(pt);
                    PhanQuaModel qua = PhanQuaController.find(idQua);
                    PhanQuaController.tangSoLuongDaPhat(qua);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhanThuong;
    private javax.swing.JLabel txtConLai;
    private javax.swing.JTextField txtIdNK;
    private javax.swing.JButton txtPhatQua;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}
