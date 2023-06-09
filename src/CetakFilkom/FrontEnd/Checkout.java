/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CetakFilkom.FrontEnd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CetakFilkom.Data;
import CetakFilkom.Error.PromotionNotMetExcpetion;

/**
 *
 * @author HP
 */
public class Checkout extends javax.swing.JFrame {

    /**
     * Creates new form Checkout
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public Checkout() throws FileNotFoundException, IOException {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        jComboBoxPromo.addItem("");
        try (BufferedReader b = new BufferedReader(new FileReader("src\\CetakFilkom\\File\\Promosi.txt"))) {
            String line;
            while ((line = b.readLine()) != null) {
                String[] tmp = line.split(" ");
                String[] data = tmp[1].split(",");
                String temp = data[0] + " " + data[3] + " (" + data[5] + " - " + data[4] + ") " + " (" + data[1] + " - "
                        + data[2] + ")";
                jComboBoxPromo.addItem(temp);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonCheckout = new javax.swing.JButton();
        jButtonBackCheckout = new javax.swing.JButton();
        jLabelCheckout = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jComboBoxPromo = new javax.swing.JComboBox<>();
        jTextFieldBiaya = new javax.swing.JTextField();
        jTextFieldOngkir = new javax.swing.JTextField();
        jLabelBiaya = new javax.swing.JLabel();
        jLabelOngkir = new javax.swing.JLabel();
        model = new DefaultTableModel();
        jTableCheckout = new javax.swing.JTable(model);
        jTextFieldBiaya.setEditable(false);
        jTextFieldOngkir.setEditable(false);
        jTextFieldBiaya.setText(String.format("%.0f", App.order.getBiaya()));
        jTextFieldOngkir.setText(String.format("%.0f", App.order.getBiayaOngkir()));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(52, 73, 94));

        jButtonCheckout.setText("Checkout");
        jButtonCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonCheckoutActionPerformed(evt);
                } catch (PromotionNotMetExcpetion e) {
                    e.printStackTrace();
                } catch (ArithmeticException e) {
                    JOptionPane.showMessageDialog(null, "Saldo anda tidak cukup", "", JOptionPane.DEFAULT_OPTION);
                }
            }
        });

        jButtonBackCheckout.setText("Kembali");
        jButtonBackCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonBackCheckoutActionPerformed(evt);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Anda tidak memenuhi syarat untuk mendapatkan promo");
                    e.printStackTrace();
                }
            }
        });

        jLabelCheckout.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabelCheckout.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCheckout.setText("CHECKOUT");

        jTableCheckout.setModel(new javax.swing.table.DefaultTableModel(
                new String[] {
                        "ID", "Menu", "Kuantitas", "Harga"
                }, 0));
        jScrollPane1.setViewportView(jTableCheckout);
        jLabelBiaya.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabelBiaya.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBiaya.setText("Biaya");

        jLabelOngkir.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabelOngkir.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOngkir.setText("Ongkir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(193, 193, 193)
                                                .addComponent(jLabelCheckout))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabelOngkir)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jTextFieldOngkir,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 224,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabelBiaya)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jTextFieldBiaya,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 224,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jButtonBackCheckout)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jButtonCheckout))
                                                        .addComponent(jComboBoxPromo,
                                                                javax.swing.GroupLayout.Alignment.TRAILING, 0,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 426,
                                                                Short.MAX_VALUE))))
                                .addContainerGap(52, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelCheckout)
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxPromo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldBiaya, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelBiaya))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldOngkir, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelOngkir))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonBackCheckout)
                                        .addComponent(jButtonCheckout))
                                .addContainerGap(22, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCheckoutActionPerformed(java.awt.event.ActionEvent evt)
            throws PromotionNotMetExcpetion, ArithmeticException {// GEN-FIRST:event_jButtonCheckoutActionPerformed
        CheckoutYes p = new CheckoutYes();
        String promo = (String) jComboBoxPromo.getSelectedItem();
        String idPromo = (promo.split(" "))[0];
        if (!promo.equals("")) {
            App.order.applyPromo(idPromo, Data.getMapPromosi().get(idPromo));
        }
        App.order.checkOut();
        App.order.setTanggal();
        p.setVisible(true);
        dispose();
    }// GEN-LAST:event_jButtonCheckoutActionPerformed

    private void jButtonBackCheckoutActionPerformed(java.awt.event.ActionEvent evt)
            throws FileNotFoundException, IOException {// GEN-FIRST:event_jButtonBackCheckoutActionPerformed
        MenuCustomer p = new MenuCustomer();
        p.setVisible(true);
        dispose();
    }// GEN-LAST:event_jButtonBackCheckoutActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Checkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Checkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Checkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Checkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Checkout().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static javax.swing.table.DefaultTableModel getTable() {
        return (DefaultTableModel) jTableCheckout.getModel();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBackCheckout;
    private javax.swing.JButton jButtonCheckout;
    private javax.swing.JComboBox<String> jComboBoxPromo;
    private javax.swing.JLabel jLabelBiaya;
    private javax.swing.JLabel jLabelCheckout;
    private javax.swing.JLabel jLabelOngkir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTableCheckout;
    private javax.swing.JTextField jTextFieldBiaya;
    private javax.swing.JTextField jTextFieldOngkir;
    private javax.swing.table.DefaultTableModel model;
    // End of variables declaration//GEN-END:variables
}
