/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static Interfaces.Agregar_Cita.med;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jefferson
 */
public class Seleccionar_Medico extends javax.swing.JInternalFrame {

    /**
     * Creates new form Ver_Especialidades
     */
    DefaultTableModel modelo;

    public Seleccionar_Medico() {
        initComponents();
        btnAceptar.setEnabled(false);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        carTablaMedicos("");
        tblMedicosVista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                btnAceptar.setEnabled(true);

            }
        });
    }

    public void carTablaMedicos(String dato) {
        String[] titulos = {"CÉDULA", "NOMBRES", "APELLIDOS", "TELEFONO", "FEC. NAC", "EDAD", "ESPECIALIDAD"};
        String[] registros = new String[7];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "", sql2 = "";
        int i = 0;
        sql = "SELECT * FROM medicos where CED_MED LIKE'%" + dato + "%'AND ESTADO='SI' AND ESPECIALIDAD='" + Agregar_Cita.jcbxEspecialidades.getSelectedItem().toString() + "'";
        sql2 = "select * from medicosvista where ESTADO='SI'";
        try {
            Statement psd1 = cn.createStatement();
            ResultSet rs1 = psd1.executeQuery(sql2);
            while (rs1.next()) {
                i++;
            }
            if (i == 0) {
                JOptionPane.showMessageDialog(this, "No existen Médícos..");
                this.btnBuscar.setEnabled(false);
                this.txtBuscarporCedula.setEnabled(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {

            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("CED_MED");
                registros[1] = rs.getString("NOM1_MED") + " " + rs.getString("NOM2_MED");
                registros[2] = rs.getString("APEP_MED") + " " + rs.getString("APEM_MED");
                registros[3] = rs.getString("TEL_MED");
                registros[4] = rs.getString("FEC_NAC_MED");
                registros[5] = rs.getString("EDAD");
                registros[6] = rs.getString("ESPECIALIDAD");
                modelo.addRow(registros);
            }
            tblMedicosVista.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        tblMedicosVista.setModel(modelo);

    }

    public void controlLetras(KeyEvent evt) {
        char c;
        c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            getToolkit().beep();
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

    public void ValidacionMayusculas(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            String cadena = ("" + caracter);
            caracter = cadena.toUpperCase().charAt(0);
            evt.setKeyChar(caracter);
        }
    }

    public void cargarDias() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "", sql2 = "";
        int i = 0;
        String DIASEL;
        sql = "SELECT * FROM horarios where COD_MED_PER='" + med + "'";
        try {
            Statement psd1 = cn.createStatement();
            ResultSet rs1 = psd1.executeQuery(sql);
            while (rs1.next()) {
                i++;
                DIASEL = rs1.getString("DIA");
                if (DIASEL.equals("LUNES")) {
                    Agregar_Cita.tgbLunes1.setForeground(Color.blue);
                    Agregar_Cita.chxLunes.setEnabled(true);
                }
                if (DIASEL.equals("MARTES")) {
                    Agregar_Cita.tgbMartes1.setForeground(Color.blue);
                    Agregar_Cita.chxMartes.setEnabled(true);
                }
                if (DIASEL.equals("MIERCOLES")) {
                    Agregar_Cita.tgbMiercoles1.setForeground(Color.blue);
                    Agregar_Cita.chxMiercoles.setEnabled(true);
                }
                if (DIASEL.equals("JUEVES")) {
                    Agregar_Cita.tgbJueves1.setForeground(Color.blue);
                    Agregar_Cita.chxJueves.setEnabled(true);
                }
                if (DIASEL.equals("VIERNES")) {
                    Agregar_Cita.tgbViernes1.setForeground(Color.blue);
                    Agregar_Cita.chxViernes.setEnabled(true);
                }
                if (DIASEL.equals("SABADO")) {
                    Agregar_Cita.tgbSabado1.setForeground(Color.blue);
                    Agregar_Cita.chxSabado.setEnabled(true);
                }
                if (DIASEL.equals("DOMINGO")) {
                    Agregar_Cita.tgbDomingo1.setForeground(Color.blue);
                    Agregar_Cita.chxDomingo.setEnabled(true);
                }
            }
            if (i == 0) {
                JOptionPane.showMessageDialog(this, "No existe Horarios..");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void carTablaHorarios(String dato) {
        String[] titulos = {"DIA", "DESDE", "HASTA", "ESTADO"};
        String[] registros = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "SELECT * FROM horarios where COD_MED_PER LIKE'%" + dato + "%'";
        try {

            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("DIA");
                registros[1] = rs.getString("DESDE");
                registros[2] = rs.getString("HASTA");
                registros[3] = rs.getString("ESTADO");
                modelo.addRow(registros);
            }
            Agregar_Cita.tblHoras.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        Agregar_Cita.tblHoras.setModel(modelo);

    }

    public void Aceptar() {
        int fila = tblMedicosVista.getSelectedRow();
        if (tblMedicosVista.getSelectedRow() != -1) {
            Agregar_Cita.med = tblMedicosVista.getValueAt(fila, 0).toString();
            Agregar_Cita.txtMmedico.setText(tblMedicosVista.getValueAt(fila, 1).toString() + "  " + tblMedicosVista.getValueAt(fila, 2).toString());
//            Agregar_Cita.areaConfirmacion.setText(Agregar_Cita.areaConfirmacion.getText()+"\n"+"MEDICO: "+" "+ tblMedicosVista.getValueAt(fila, 1).toString() + "  " + tblMedicosVista.getValueAt(fila, 2).toString());
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
        tblMedicosVista = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarporCedula = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("LISTADO DE MÉDICOS");

        tblMedicosVista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblMedicosVista);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("BUSCAR POR NOMBRE");

        txtBuscarporCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarporCedulaKeyTyped(evt);
            }
        });

        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscarporCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnBuscar)))
                        .addGap(46, 46, 46)
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(66, 66, 66))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtBuscarporCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptar)
                            .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        carTablaMedicos(txtBuscarporCedula.getText());
        if (tblMedicosVista.getRowCount() != 0) {
            JOptionPane.showMessageDialog(this, "SE ENCONTRARON COINCIDENCIAS CON EL MÉDICO " + "  " + txtBuscarporCedula.getText());
        } else {
            carTablaMedicos("");
            JOptionPane.showMessageDialog(this, "NO SE ENCONTRÓ EL MÉDICO " + "  " + txtBuscarporCedula.getText());

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarporCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarporCedulaKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        controlNumeros(evt);
    }//GEN-LAST:event_txtBuscarporCedulaKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Aceptar();
        cargarDias();
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(Seleccionar_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seleccionar_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seleccionar_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seleccionar_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seleccionar_Medico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMedicosVista;
    private javax.swing.JTextField txtBuscarporCedula;
    // End of variables declaration//GEN-END:variables
}
