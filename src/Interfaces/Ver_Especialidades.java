/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jefferson
 */
public class Ver_Especialidades extends javax.swing.JInternalFrame {

    /**
     * Creates new form Ver_Especialidades
     */
    DefaultTableModel modelo;

    public Ver_Especialidades() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        carTablaEspecialidades(txtBuscarporNombre.getText());
    }

    public void carTablaEspecialidades(String dato) {
        String[] titulos = {"NOMBRE", "DESCRIPCION"};
        String[] registros = new String[2];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql2, sql = "";
        int i = 0;
        sql2 = "select *from especialidadesvista";
        sql = "SELECT * FROM especialidades where NOM_ESP LIKE'%" + dato + "%'";

        try {
            Statement psd1 = cn.createStatement();
            ResultSet rs1 = psd1.executeQuery(sql2);
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                JOptionPane.showMessageDialog(this, "NO existen Especialidades..");
                this.btnBuscar.setEnabled(false);
                this.txtBuscarporNombre.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {

            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("NOM_ESP");
                registros[1] = rs.getString("DES_ESP");
                modelo.addRow(registros);
            }
            if (i > 0) {
                tblEspecialidadesvista.setModel(modelo);
            } else {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void controlLetras(KeyEvent evt) {
        char c;
        c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            getToolkit().beep();
        }
    }

    public void ValidacionMayusculas(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            String cadena = ("" + caracter);
            caracter = cadena.toUpperCase().charAt(0);
            evt.setKeyChar(caracter);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspecialidadesvista = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarporNombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("LISTADO DE ESPECIALIDADES");

        tblEspecialidadesvista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblEspecialidadesvista);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("BUSCAR POR NOMBRE");

        txtBuscarporNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarporNombreKeyTyped(evt);
            }
        });

        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(139, 139, 139))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarporNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton1)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtBuscarporNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnBuscar)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        carTablaEspecialidades(txtBuscarporNombre.getText());
        if (tblEspecialidadesvista.getRowCount() != 0) {
            JOptionPane.showMessageDialog(this, "SE ENCONTRARON COINCIDENCIAS CON LA ESPECIALIDAD" + "  " + txtBuscarporNombre.getText());
        } else {
            JOptionPane.showMessageDialog(this, "NO SE ENCONTRÓ LA ESPECIALIDAD" + "  " + txtBuscarporNombre.getText());
            carTablaEspecialidades("");

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarporNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarporNombreKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        controlLetras(evt);
    }//GEN-LAST:event_txtBuscarporNombreKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ver_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ver_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ver_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ver_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ver_Especialidades().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEspecialidadesvista;
    private javax.swing.JTextField txtBuscarporNombre;
    // End of variables declaration//GEN-END:variables
}
