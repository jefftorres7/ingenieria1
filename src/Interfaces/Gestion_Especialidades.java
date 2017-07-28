/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jefferson
 */
public class Gestion_Especialidades extends javax.swing.JInternalFrame {

    /**
     * Creates new form Gestion_Especialidades
     */
    DefaultTableModel modelo;

    public Gestion_Especialidades() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);       
        bloquearTextos();
        bloquearBotonesInicio();
        carTablaEspecialidades(txtBuscarNombre.getText());
        tblEspecialidades.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int fila = tblEspecialidades.getSelectedRow();
                if (tblEspecialidades.getSelectedRow() != -1) {
                    txtNombre.setText(tblEspecialidades.getValueAt(fila, 0).toString());
                    txtaDescripcion.setText(tblEspecialidades.getValueAt(fila, 1).toString());
                    desbloquearTextos();
                    bloquearUpdate();
                }
            }
        });

    }

    public void bloquearUpdate() {
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(true);
    }

    public void bloquearTextos() {
        txtNombre.setEnabled(false);
        txtaDescripcion.setEnabled(false);
    }

    public void desbloquearTextos() {
        txtNombre.setEnabled(true);
        txtaDescripcion.setEnabled(true);
    }

    public void bloquearBotonesInicio() {
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
    }

    public void bloquearBotonesNuevo() {
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
    }

    public void limpiarTextos() {
        txtNombre.setText("");
        txtaDescripcion.setText("");
    }

    public void bloquearBotonesGuardar() {
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
    }

    public void controlNumeros(KeyEvent evt) {
        char c;
        c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            getToolkit().beep();
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

    public void guardarEspecialidad() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL NOMBRE");
        } else if (txtaDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA DESCRIPCIÓN");
        } else {

            String NOM_ESP, DES_ESP;
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            NOM_ESP = txtNombre.getText();
            DES_ESP = txtaDescripcion.getText();
            String sql = "";
            String sql2 = "";
            String ESP;
            sql = "insert into especialidades(NOM_ESP,DES_ESP) values(?,?)";
            sql2 = "SELECT * FROM especialidades";
            try {

                Statement psd2 = cn.createStatement();
                ResultSet rs = psd2.executeQuery(sql2);
                while (rs.next()) {
                    ESP = rs.getString("NOM_ESP");
                    if (ESP.equals(txtNombre.getText())) {
                        JOptionPane.showMessageDialog(null, "PLACA YA EXISTENTE");
                        txtNombre.setText("");
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO VERIFICAR LA ESPECIALIDAD");
            }
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, NOM_ESP);
                psd.setString(2, DES_ESP);
                int n = psd.executeUpdate();

                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                    JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
                    limpiarTextos();
                    bloquearBotonesGuardar();
                    bloquearTextos();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se Inserto el Dato");
            }
        }
        carTablaEspecialidades(txtBuscarNombre.getText());
    }

    public void Modificar() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre");
        } else if (txtaDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Descripción");
        } else {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String ID = null,sql2="";
             int fila = tblEspecialidades.getSelectedRow();
             String NOMBRE=tblEspecialidades.getValueAt(fila, 0).toString();
            sql2="select ID_ESP from especialidades Where NOM_ESP='"+NOMBRE+"'";
             try {

                Statement psd2 = cn.createStatement();
                ResultSet rs = psd2.executeQuery(sql2);
                while (rs.next()) {
                    ID = rs.getString("ID_ESP");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO VERIFICAR LA ESPECIALIDAD");
            }
            String sql = "";
            sql = "UPDATE especialidades SET NOM_ESP='" + txtNombre.getText() + "',"
                    + "DES_ESP='" + txtaDescripcion.getText() + "'"
                    + "WHERE ID_ESP='" + ID + "'";
            limpiarTextos();
            bloquearTextos();
            bloquearBotonesGuardar();
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se actualizó Correctamente la fila");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Actualizar la fila ");
            }
        }
        carTablaEspecialidades(txtBuscarNombre.getText());
    }
    
     public void BorrarEspecialidad() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        String sql = "";
        sql = "delete from especialidades where NOM_ESP='" + txtNombre.getText() + "'";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            int a = JOptionPane.showConfirmDialog(null, "Desea borrar la Especialidad" +"  "+txtNombre.getText());
            if (a == 0) {
                int n = psd.executeUpdate();
                modelo.removeRow(tblEspecialidades.getSelectedRow());
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "se elimino correctamente el dato");
                    limpiarTextos();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se Pudo Eliminar el Dato");
        }
         carTablaEspecialidades(txtBuscarNombre.getText());
    }

    public void carTablaEspecialidades(String dato) {
        String[] titulos = {"NOMBRE", "DESCRIPCION"};
        String[] registros = new String[2];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "SELECT * FROM especialidades where NOM_ESP LIKE'%" + dato + "%'";
        try {

            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("NOM_ESP");
                registros[1] = rs.getString("DES_ESP");
                modelo.addRow(registros);
            }
            tblEspecialidades.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        tblEspecialidades.setModel(modelo);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEspecialidades = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscarNombre = new javax.swing.JTextField();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ESPECIALIDADES");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ESPECIALIDADES");

        jLabel2.setText("NOMBRE:");

        jLabel3.setText("DESCRIPCÓN:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        txtaDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaDescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtaDescripcion);

        tblEspecialidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblEspecialidades);

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnBorrar.setText("BORRAR");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnBorrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        jLabel4.setText("BUSCAR POR NOMBRE:");

        txtBuscarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarNombreKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(jLabel1)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        bloquearBotonesNuevo();
        desbloquearTextos();
        txtNombre.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardarEspecialidad();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarTextos();
        bloquearTextos();
        bloquearBotonesGuardar();
        tblEspecialidades.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        controlLetras(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtaDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaDescripcionKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        controlLetras(evt);
    }//GEN-LAST:event_txtaDescripcionKeyTyped

    private void txtBuscarNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNombreKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtBuscarNombreKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        Modificar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        BorrarEspecialidad();
        bloquearBotonesInicio();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void txtBuscarNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNombreKeyReleased
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        controlLetras(evt);
        carTablaEspecialidades(txtBuscarNombre.getText());
    }//GEN-LAST:event_txtBuscarNombreKeyReleased

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
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException ex) {
            Logger.getLogger(Gestion_Especialidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestion_Especialidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Gestion_Especialidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestion_Especialidades.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Gestion_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Gestion_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Gestion_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Gestion_Especialidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_Especialidades().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblEspecialidades;
    private javax.swing.JTextField txtBuscarNombre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtaDescripcion;
    // End of variables declaration//GEN-END:variables
}
