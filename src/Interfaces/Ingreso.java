/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Jefferson
 */
public class Ingreso extends javax.swing.JFrame {

    /**
     * Creates new form Ingreso_Usuarios
     */
    public Ingreso() {
        if (VerificarAdministrador() == false) {
            int n = JOptionPane.showConfirmDialog(this, "No existe Administrador desea Ingresar un Administrador?");
            if (n == 0) {
                Ingresar_Usuario_Admin ad = new Ingresar_Usuario_Admin();
                ad.setVisible(true);
            }
            if (n == 1 || n == 2) {
                System.exit(0);
            }
        } else {
            this.setVisible(true);
            initComponents();
            this.lblAviso.setVisible(false);
            btnCancelar.setEnabled(false);
        }

    }

    public boolean VerificarAdministrador() {
        boolean si = false;
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String tipo, sql = "";
        sql = "select * from usuarios";
        try {
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                tipo = rs.getString("TIP_USU");
                if (tipo.equals("ADMINISTRADOR")) {
                    si = true;
                }
//                    

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "A ocurrido un Error Intentelo Mas Tarde....");
        }
        return si;
    }

    public void Ingreso() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select * from usuarios";
        try {
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            String usuario = txtUsuario.getText();
            String contrasenia = txtPass.getText();

            while (rs.next()) {
                String var3 = rs.getString("LOG_USU");
                String var4 = rs.getString("PAS_USU");
                if ((usuario.equals(var3)) && (contrasenia.equals(var4))) {
                    Principal m = new Principal();
                    if ("ADMINISTRADOR".equals(rs.getString("TIP_USU"))) {
                        //////MAXIMIZAR EL MENU/
                        m.setExtendedState(MAXIMIZED_BOTH);
                        ///////////////////////////
                        m.setVisible(true);
                        // cerrar una ventana
                        this.dispose();
                        ////////////////////          
                    } else {
                        if ("EMPLEADO".equals(rs.getString("TIP_USU"))) {
                            m.setExtendedState(MAXIMIZED_BOTH);
                            ///////////////////////////
                            m.setVisible(true);
                            m.setEnabled(true);
                            m.menuEspecialidad.setVisible(false);
                            m.jmiGestionarPacientes.setVisible(false);
                            m.jmiGestionrMedicos.setVisible(false);
                            m.menuServicios.setVisible(false);
                            m.menuUusuarios.setVisible(false);
                            m.jmiReporteUsuarios.setVisible(false);
                            // cerrar una ventana
                            this.dispose();
                            ////////////////////  
                        } else if ("MÉDICO".equals(rs.getString("TIP_USU"))) {
                            m.setExtendedState(MAXIMIZED_BOTH);
                            ///////////////////////////
                            m.setVisible(true);
                            m.setEnabled(true);
                            m.menuEspecialidad.setVisible(false);
                            m.menuMedicos.setVisible(false);
                            m.MenuCitas.setVisible(false);
                            m.menuServicios.setVisible(false);
                            m.menuUusuarios.setVisible(false);
                            m.menuReportes.setVisible(false);
                            // cerrar una ventana
                            this.dispose();
                            ////////////////////  
                        }
                    }

                } else {
                    lblAviso.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "A ocurrido un Error Intentelo Mas Tarde....");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpnPanelDatosIngreso = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPass = new java.awt.TextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblAviso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BanerIngreso.jpg"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BanerIngreso.jpg"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DoctorPrin.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Usuario:");

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Contraseña:");

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpnPanelDatosIngresoLayout = new javax.swing.GroupLayout(jpnPanelDatosIngreso);
        jpnPanelDatosIngreso.setLayout(jpnPanelDatosIngresoLayout);
        jpnPanelDatosIngresoLayout.setHorizontalGroup(
            jpnPanelDatosIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPanelDatosIngresoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnPanelDatosIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jpnPanelDatosIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jpnPanelDatosIngresoLayout.setVerticalGroup(
            jpnPanelDatosIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPanelDatosIngresoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnPanelDatosIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnPanelDatosIngresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblAviso.setForeground(new java.awt.Color(204, 0, 0));
        lblAviso.setText("USUARIO O CLAVE INCORRECTA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jpnPanelDatosIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAviso)
                        .addGap(84, 84, 84)))
                .addComponent(jLabel6)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(24, 24, 24)
                        .addComponent(jButton1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnPanelDatosIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAviso)
                        .addGap(21, 21, 21)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        txtUsuario.setText("");
        lblAviso.setEnabled(false);
        txtPass = this.txtPass;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
        if (txtUsuario.getText().length() > 0) {
            btnCancelar.setEnabled(true);
        } else {
            btnCancelar.setEnabled(false);
        }

    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Ingreso();

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        // TODO add your handling code here:
        txtPass.setEchoChar('*');
        controlNumeros(evt);
    }//GEN-LAST:event_txtPassKeyTyped

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
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Ingreso().setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jpnPanelDatosIngreso;
    private javax.swing.JLabel lblAviso;
    private java.awt.TextField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
