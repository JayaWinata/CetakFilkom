package CetakFilkom.BackEnd;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

import CetakFilkom.Data;
import CetakFilkom.Error.DateOutOfBoundsException;
import CetakFilkom.Lembaran.Buku;
import CetakFilkom.Lembaran.Lembaran;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author H P
 */
public class MenuAdmin extends javax.swing.JFrame {

        /**
         * Creates new form Menu
         */
        public MenuAdmin() {
                initComponents();
                setLocationRelativeTo(null);
                setResizable(false);
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                panelMenuAdmin = new javax.swing.JPanel();
                buttonBackMenu = new javax.swing.JButton();
                labelMenu = new javax.swing.JLabel();
                labelIdMenu = new javax.swing.JLabel();
                labelHargaMenu = new javax.swing.JLabel();
                labelNamaMenu = new javax.swing.JLabel();
                labelJenisMenu = new javax.swing.JLabel();
                teksIdMenu = new javax.swing.JTextField();
                teksHargaMenu = new javax.swing.JTextField();
                teksNamaMenu = new javax.swing.JTextField();
                comboBoxJenisMenu = new javax.swing.JComboBox<>();
                buttonTambahMenu = new javax.swing.JButton();
                buttonHapusMenu = new javax.swing.JButton();
                buttonUpdateMenu = new javax.swing.JButton();
                buttonLihatMenu = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                panelMenuAdmin.setBackground(new java.awt.Color(52, 73, 94));

                buttonBackMenu.setText("Kembali");
                buttonBackMenu.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                buttonBackMenuMouseClicked(evt);
                        }
                });

                labelMenu.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
                labelMenu.setForeground(new java.awt.Color(255, 255, 255));
                labelMenu.setText("MENU");

                labelIdMenu.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
                labelIdMenu.setForeground(new java.awt.Color(255, 255, 255));
                labelIdMenu.setText("ID Menu");

                labelHargaMenu.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
                labelHargaMenu.setForeground(new java.awt.Color(255, 255, 255));
                labelHargaMenu.setText("Harga Menu");

                labelNamaMenu.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
                labelNamaMenu.setForeground(new java.awt.Color(255, 255, 255));
                labelNamaMenu.setText("Nama Menu");

                labelJenisMenu.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
                labelJenisMenu.setForeground(new java.awt.Color(255, 255, 255));
                labelJenisMenu.setText("Jenis Menu");

                comboBoxJenisMenu.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Lembaran", "Buku" }));

                buttonTambahMenu.setText("Tambah");
                buttonTambahMenu.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        buttonTambahMenuActionPerformed(evt);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }

                        private void buttonTambahMenuActionPerformed(ActionEvent evt) throws IOException {
                                StringJoiner sj = new StringJoiner(",");
                                String tipe = (String) comboBoxJenisMenu.getSelectedItem();
                                String id = (String) teksIdMenu.getText();
                                String nama = (String) teksNamaMenu.getText();
                                int harga = Integer.parseInt(teksHargaMenu.getText());
                                sj.add(id);
                                sj.add(nama);
                                sj.add(teksHargaMenu.getText());
                                Lembaran l = null;
                                if (comboBoxJenisMenu.getSelectedItem().equals("Lembaran")) {
                                        l = new Lembaran(nama, harga);
                                } else {
                                        l = new Buku(nama);
                                }
                                l.setHarga(harga);
                                Data.tambah(id, l, (tipe + " " + sj.toString()));
                                teksHargaMenu.setText("");
                                teksIdMenu.setText("");
                                teksHargaMenu.setText("");
                                teksNamaMenu.setText("");
                        }
                });

                buttonHapusMenu.setText("Hapus");
                buttonHapusMenu.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        buttonHapusMenuActionPerformed(evt);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }

                        private void buttonHapusMenuActionPerformed(ActionEvent evt) throws IOException {
                                String id = (String) teksIdMenu.getText();
                                Data.hapus(id, "Menu.txt");
                                teksHargaMenu.setText("");
                                teksIdMenu.setText("");
                                teksHargaMenu.setText("");
                                teksNamaMenu.setText("");
                        }
                });

                buttonUpdateMenu.setText("Ubah");
                buttonUpdateMenu.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        buttonUpdateMenuActionPerformed(evt);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });

                buttonLihatMenu.setText("Lihat");
                buttonLihatMenu.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                try {
                                        buttonLihatMenuMouseClicked(evt);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                });

                javax.swing.GroupLayout panelMenuAdminLayout = new javax.swing.GroupLayout(panelMenuAdmin);
                panelMenuAdmin.setLayout(panelMenuAdminLayout);
                panelMenuAdminLayout.setHorizontalGroup(
                                panelMenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelMenuAdminLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(panelMenuAdminLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panelMenuAdminLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(30, 30, 30)
                                                                                                .addGroup(panelMenuAdminLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(panelMenuAdminLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(labelIdMenu)
                                                                                                                                .addComponent(labelHargaMenu)
                                                                                                                                .addComponent(labelNamaMenu)
                                                                                                                                .addComponent(labelJenisMenu))
                                                                                                                .addGroup(panelMenuAdminLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(buttonTambahMenu)
                                                                                                                                .addGap(1, 1, 1)))
                                                                                                .addGap(44, 44, 44)
                                                                                                .addGroup(panelMenuAdminLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(panelMenuAdminLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(teksHargaMenu,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                165,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(teksIdMenu,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                165,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(teksNamaMenu,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                165,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(comboBoxJenisMenu,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(panelMenuAdminLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(buttonHapusMenu)
                                                                                                                                .addGap(43, 43, 43)
                                                                                                                                .addComponent(buttonUpdateMenu)))
                                                                                                .addContainerGap(47,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(panelMenuAdminLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(buttonBackMenu)
                                                                                                .addGap(64, 64, 64)
                                                                                                .addComponent(labelMenu)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(buttonLihatMenu)
                                                                                                .addGap(17, 17, 17)))));
                panelMenuAdminLayout.setVerticalGroup(
                                panelMenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelMenuAdminLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(panelMenuAdminLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(labelMenu)
                                                                                .addComponent(buttonBackMenu)
                                                                                .addComponent(buttonLihatMenu))
                                                                .addGap(25, 25, 25)
                                                                .addGroup(panelMenuAdminLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelIdMenu)
                                                                                .addComponent(teksIdMenu,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(panelMenuAdminLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelHargaMenu)
                                                                                .addComponent(teksHargaMenu,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(22, 22, 22)
                                                                .addGroup(panelMenuAdminLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelNamaMenu)
                                                                                .addComponent(teksNamaMenu,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(panelMenuAdminLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(labelJenisMenu)
                                                                                .addComponent(comboBoxJenisMenu,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                41, Short.MAX_VALUE)
                                                                .addGroup(panelMenuAdminLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(buttonTambahMenu)
                                                                                .addComponent(buttonHapusMenu)
                                                                                .addComponent(buttonUpdateMenu))
                                                                .addGap(45, 45, 45)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panelMenuAdmin, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addGap(117, 117, 117)

                                                                                .addContainerGap(117,
                                                                                                Short.MAX_VALUE))));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panelMenuAdmin, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addGap(157, 157, 157)

                                                                                .addContainerGap(157,
                                                                                                Short.MAX_VALUE))));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void buttonBackMenuMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonBackMenuMouseClicked
                // GEN-FIRST:event_buttonBackMenuMouseClicked
                Admin p = new Admin();
                p.setVisible(true);
                dispose();
                // GEN-LAST:event_buttonBackMenuMouseClicked
        }// GEN-LAST:event_buttonBackMenuMouseClicked

        private void buttonUpdateMenuActionPerformed(java.awt.event.ActionEvent evt)
                        throws IOException, DateOutOfBoundsException {// GEN-FIRST:event_buttonUpdateMenuActionPerformed
                StringJoiner sj = new StringJoiner(",");
                String tipe = (String) comboBoxJenisMenu.getSelectedItem();
                String id = (String) teksIdMenu.getText();
                String nama = (String) teksNamaMenu.getText();
                int harga = Integer.parseInt(teksHargaMenu.getText());
                sj.add(id);
                sj.add(nama);
                sj.add(teksHargaMenu.getText());
                Lembaran l = null;
                if (comboBoxJenisMenu.getSelectedItem().equals("Lembaran")) {
                        l = new Lembaran(nama, harga);
                } else {
                        l = new Buku(nama);
                }
                Data.ubah(id, "Menu.txt", (tipe + " " + sj.toString()), l);
                teksHargaMenu.setText("");
                teksIdMenu.setText("");
                teksHargaMenu.setText("");
                teksNamaMenu.setText("");
        }// GEN-LAST:event_buttonUpdateMenuActionPerformed

        private void buttonLihatMenuMouseClicked(java.awt.event.MouseEvent evt)
                        throws FileNotFoundException, IOException {// GEN-FIRST:event_buttonLihatMenuMouseClicked
                LihatMenu l = new LihatMenu();
                try (BufferedReader b = new BufferedReader(new FileReader("src\\CetakFilkom\\File\\Menu.txt"))) {
                        String line;
                        while ((line = b.readLine()) != null) {
                                String[] st = line.split(" ", 2);
                                String[] data = st[1].split(",");
                                LihatMenu.getTable().addRow(data);
                        }
                }
                l.setVisible(true);
        }// GEN-LAST:event_buttonLihatMenuMouseClicked

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(MenuAdmin.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(MenuAdmin.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(MenuAdmin.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(MenuAdmin.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                }
                // </editor-fold>
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new MenuAdmin().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton buttonBackMenu;
        private javax.swing.JButton buttonHapusMenu;
        private javax.swing.JButton buttonLihatMenu;
        private javax.swing.JButton buttonTambahMenu;
        private javax.swing.JButton buttonUpdateMenu;
        private javax.swing.JComboBox<String> comboBoxJenisMenu;
        private javax.swing.JTextField teksIdMenu;
        private javax.swing.JLabel labelHargaMenu;
        private javax.swing.JLabel labelJenisMenu;
        private javax.swing.JLabel labelMenu;
        private javax.swing.JLabel labelNamaMenu;
        private javax.swing.JLabel labelIdMenu;
        private javax.swing.JPanel panelMenuAdmin;
        private javax.swing.JTextField teksHargaMenu;
        private javax.swing.JTextField teksNamaMenu;
        // End of variables declaration//GEN-END:variables
}
