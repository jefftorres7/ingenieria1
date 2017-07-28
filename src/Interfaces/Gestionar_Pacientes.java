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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class Gestionar_Pacientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Gestionar_Pacientes
     */
    DefaultTableModel modelo;
    Calendar cal = Calendar.getInstance();
    Calendar calendarios = Calendar.getInstance();
    Date fe = calendarios.getTime();

    public Gestionar_Pacientes() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        cargarTipSan();
        txtEdad.setEditable(false);
        bloquearTextos();
        bloquearBotonesInicio();
        carTablaPacientes(txtBuscarporCedula.getText());
        tblPacientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int fila = tblPacientes.getSelectedRow();
                if (tblPacientes.getSelectedRow() != -1) {
                    txtCedula.setText(tblPacientes.getValueAt(fila, 0).toString());
                    txtNombres.setText(tblPacientes.getValueAt(fila, 1).toString());
                    txtApellidos.setText(tblPacientes.getValueAt(fila, 2).toString());
                    txtTelefono.setText(tblPacientes.getValueAt(fila, 3).toString());
                    txtPeso.setText(tblPacientes.getValueAt(fila, 4).toString());
                    txtAltura.setText(tblPacientes.getValueAt(fila, 5).toString());
                    jdcFechaNacimiento.setDate(TransformaraFecha(tblPacientes.getValueAt(fila, 6).toString()));
                    txtEdad.setText(tblPacientes.getValueAt(fila, 7).toString());
                    jcbxTipoSangre.setSelectedItem(tblPacientes.getValueAt(fila, 8).toString());
                    txtaAlergias.setText(tblPacientes.getValueAt(fila, 9).toString());
                    txtaEnfermedades.setText(tblPacientes.getValueAt(fila, 10).toString());

                    desbloquearTextos();
                    bloquearUpdate();
                }
            }
        });
    }

    public String FecActual() {
        String fech;
        Calendar calendario = Calendar.getInstance();
        Date fecha = calendario.getTime();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        fech = formatoFecha.format(fecha);
//        horamin[2]= calendario.get(Calendar.DAY_OF_MONTH);
//        horamin[3]= calendario.get(Calendar.MONTH)+1;
//        horamin[4]= calendario.get(Calendar.YEAR);
        return fech;

    }

    public Date TransformaraFecha(String Fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {

            fecha = formatoDelTexto.parse(Fecha);

        } catch (ParseException ex) {

        }
        return fecha;
    }

    public void cargarTipSan() {
        jcbxTipoSangre.addItem("");
        jcbxTipoSangre.addItem("A POSITIVO");
        jcbxTipoSangre.addItem("A NEGATIVO");
        jcbxTipoSangre.addItem("B POSITIVO");
        jcbxTipoSangre.addItem("B NEGATIVO");
        jcbxTipoSangre.addItem("O POSITIVO");
        jcbxTipoSangre.addItem("O NEGATIVO");
        jcbxTipoSangre.addItem("AB POSITIVO");
        jcbxTipoSangre.addItem("AB NEGATIVO");
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
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtPeso.setEnabled(false);
        txtAltura.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEdad.setEnabled(false);
        txtaAlergias.setEnabled(false);
        txtaEnfermedades.setEnabled(false);
        jcbxTipoSangre.setEnabled(false);
        jdcFechaNacimiento.setEnabled(false);
        txtCedula.setEnabled(false);
    }

    public void desbloquearTextos() {
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtPeso.setEnabled(true);
        txtAltura.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtEdad.setEnabled(true);
        txtaAlergias.setEnabled(true);
        txtaEnfermedades.setEnabled(true);
        jcbxTipoSangre.setEnabled(true);
        jdcFechaNacimiento.setEnabled(true);
        txtCedula.setEnabled(true);
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
        txtNombres.setText("");
        txtApellidos.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        txtTelefono.setText("");
        txtEdad.setText("");
        txtaAlergias.setText("");
        txtaEnfermedades.setText("");
        jcbxTipoSangre.setSelectedIndex(0);
        jdcFechaNacimiento.setDateFormatString(FecActual());
        txtCedula.setText("");
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

    public String FechaaCadena(Date fec) {
        String FECHA;
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        FECHA = formatoFecha.format(fec);
        return FECHA;
    }

    public void CrearHhistoria() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String NUM_HIS, FEC_HIS, ESTADO;
        NUM_HIS = txtCedula.getText();
        FEC_HIS = FechaaCadena(fe);
        ESTADO = "SI";
        String sql = "";
        sql = "insert into historias(NUM_HIS, FEC_HIS, ESTADO) values(?,?,?)";
        try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, NUM_HIS);
                psd.setString(2, FEC_HIS);
                psd.setString(3, ESTADO);
                int n = psd.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se creo la historia clinica");
            }
    }

    public void guardarPaciente() {
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CÉDULA");
        } else if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS NOMBRES");
        } else if (txtApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS APELLIDOS");
        } else if (jcbxTipoSangre.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "ESCOJA UN TIPO DE SANGRE");
        } else if (txtPeso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL PESO");
        } else if (txtAltura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA ALTURA");
        } else if (txtEdad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA EDAD");
        } else if (jdcFechaNacimiento.getDateFormatString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA FECHA DE NACIMIENTO");
        } else if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL TELEFONO");
        } else {

            String CED_PAC, NOM1_PAC, NOM2_PAC, APEP_PAC, APEM_PAC, TEL_PAC, PESO, ALTURA, FEC_NAC_PAC, EDAD, TIP_SAN, ALERGIAS, ENFERMEDADES, ESTADO;
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            CED_PAC = txtCedula.getText();
            String a[] = new String[2];
            String p[] = new String[2];
            a = txtNombres.getText().split(" ");
            p = txtApellidos.getText().split(" ");
            int year = cal.get(Calendar.YEAR);
            int escojido = jdcFechaNacimiento.getDate().getYear() + 1900;
            NOM1_PAC = a[0];
            NOM2_PAC = a[1];
            APEP_PAC = p[0];
            APEM_PAC = p[1];
            TEL_PAC = txtTelefono.getText();
            PESO = txtPeso.getText();
            ALTURA = txtAltura.getText();
            FEC_NAC_PAC = FechaaCadena(jdcFechaNacimiento.getDate());
            EDAD = String.valueOf(year - escojido);
            TIP_SAN = jcbxTipoSangre.getSelectedItem().toString();
            if (txtaAlergias.getText().isEmpty()) {
                ALERGIAS = "NINGUNA";
            } else {
                ALERGIAS = txtaAlergias.getText();
            }
            if (txtaEnfermedades.getText().isEmpty()) {
                ENFERMEDADES = "NINGUNA";
            } else {
                ENFERMEDADES = txtaEnfermedades.getText();
            }
            ESTADO = "SI";
            String sql = "";
            String sql2 = "";
            String CED;
            sql = "insert into pacientes(CED_PAC,NOM1_PAC,NOM2_PAC,APEP_PAC,APEM_PAC,TEL_PAC,PESO,ALTURA,FEC_NAC_PAC,EDAD,TIP_SAN,ALERGIAS,ENFERMEDADES,ESTADO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            sql2 = "SELECT * FROM pacientes";
            try {

                Statement psd2 = cn.createStatement();
                ResultSet rs = psd2.executeQuery(sql2);
                while (rs.next()) {
                    CED = rs.getString("CED_PAC");
                    if (CED.equals(txtCedula.getText())) {
                        JOptionPane.showMessageDialog(null, "EL PACIENTE YA EXISTE");
                        txtCedula.setText("");
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO VERIFICAR EL PACIENTE");
            }
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, CED_PAC);
                psd.setString(2, NOM1_PAC);
                psd.setString(3, NOM2_PAC);
                psd.setString(4, APEP_PAC);
                psd.setString(5, APEM_PAC);
                psd.setString(6, TEL_PAC);
                psd.setFloat(7, Float.valueOf(PESO));
                psd.setFloat(8, Float.valueOf(ALTURA));
                psd.setString(9, FEC_NAC_PAC);
                psd.setInt(10, Integer.valueOf(EDAD));
                psd.setString(11, TIP_SAN);
                psd.setString(12, ALERGIAS);
                psd.setString(13, ENFERMEDADES);
                psd.setString(14, ESTADO);
                int n = psd.executeUpdate();
                CrearHhistoria();
                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                    JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
                    limpiarTextos();
                    bloquearBotonesGuardar();
                    bloquearTextos();
                    txtCedula.setEnabled(false);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se Inserto el Dato");
            }
        }
        carTablaPacientes(txtBuscarporCedula.getText());
    }

    public void Modificar() {
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CÉDULA");
        } else if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS NOMBRES");
        } else if (txtApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS APELLIDOS");
        } else if (jcbxTipoSangre.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "ESCOJA UN TIPO DE SANGRE");
        } else if (txtPeso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL PESO");
        } else if (txtAltura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA ALTURA");
        } else if (txtEdad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA EDAD");
        } else if (jdcFechaNacimiento.getDateFormatString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA FECHA DE NACIMIENTO");
        } else if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL TELEFONO");
        } else {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String ID = txtCedula.getText();
            String sql = "";
            String a[] = new String[2];
            String p[] = new String[2];
            a = txtNombres.getText().split(" ");
            p = txtApellidos.getText().split(" ");
            sql = "UPDATE pacientes SET NOM1_PAC='" + a[0] + "',"
                    + "NOM2_PAC='" + a[1] + "',"
                    + "APEP_PAC='" + p[0] + "',"
                    + "APEM_PAC='" + p[1] + "',"
                    + "TEL_PAC='" + txtTelefono.getText() + "',"
                    + "PESO='" + txtPeso.getText() + "',"
                    + "ALTURA='" + txtAltura.getText() + "',"
                    + "FEC_NAC_PAC='" + FechaaCadena(jdcFechaNacimiento.getDate()) + "',"
                    + "EDAD='" + txtEdad.getText() + "',"
                    + "TIP_SAN='" + jcbxTipoSangre.getSelectedItem().toString() + "',"
                    + "ALERGIAS='" + txtaAlergias.getText() + "',"
                    + "ENFERMEDADES='" + txtaEnfermedades.getText() + "'"
                    + "WHERE CED_PAC='" + ID + "'";
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
        carTablaPacientes(txtBuscarporCedula.getText());
    }

    public void BorrarPaciente() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        String sql = "";
        sql = "UPDATE pacientes SET ESTADO='NO' where CED_PAC='" + txtCedula.getText() + "'";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            int a = JOptionPane.showConfirmDialog(null, "Desea borrar al Paciente" + "  " + txtNombres.getText() + "   " + txtApellidos.getText());
            if (a == 0) {
                int n = psd.executeUpdate();
                modelo.removeRow(tblPacientes.getSelectedRow());
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "se elimino correctamente el dato");
                    limpiarTextos();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se Pudo Eliminar el Dato");
        }
        carTablaPacientes(txtBuscarporCedula.getText());
    }

    public void carTablaPacientes(String dato) {
        String[] titulos = {"CÉDULA", "NOMBRES", "APELLIDOS", "TELEFONO", "PESO", "ALTURA", "FEC. NAC", "EDAD", "TIPO SANGRE", "ALERGIAS", "ENFERMEDADES"};
        String[] registros = new String[11];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "SELECT * FROM pacientes where CED_PAC LIKE'%" + dato + "%'AND ESTADO='SI'";
        try {

            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("CED_PAC");
                registros[1] = rs.getString("NOM1_PAC") + " " + rs.getString("NOM2_PAC");
                registros[2] = rs.getString("APEP_PAC") + " " + rs.getString("APEM_PAC");
                registros[3] = rs.getString("TEL_PAC");
                registros[4] = rs.getString("PESO");
                registros[5] = rs.getString("ALTURA");
                registros[6] = rs.getString("FEC_NAC_PAC");
                registros[7] = rs.getString("EDAD");
                registros[8] = rs.getString("TIP_SAN");
                registros[9] = rs.getString("ALERGIAS");
                registros[10] = rs.getString("ENFERMEDADES");
                modelo.addRow(registros);
            }
            tblPacientes.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        tblPacientes.setModel(modelo);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jdcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        txtTelefono = new javax.swing.JTextField();
        txtAltura = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jcbxTipoSangre = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaAlergias = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaEnfermedades = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtBuscarporCedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitulo.setText("DATOS PACIENTE");

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

        jLabel3.setText("APELLIDOS:");

        jLabel6.setText("ALTURA:");

        jLabel7.setText("EDAD:");

        jLabel4.setText("TELEDONO:");

        jLabel5.setText("PESO:");

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        jLabel13.setText("kg.");

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });

        jLabel12.setText("FECHA DE MACIMIENTO");

        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });

        jdcFechaNacimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdcFechaNacimientoFocusLost(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtAltura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaKeyTyped(evt);
            }
        });

        jLabel11.setText("m.");

        txtEdad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEdadMouseClicked(evt);
            }
        });
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        jLabel2.setText("NOMBRES:");

        jLabel15.setText("CÉDULA:");

        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombres)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel13))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel11))
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setText("ENFERMEDADES:");

        jLabel8.setText("TIPO DE SANGRE:");

        jLabel9.setText("ALERGIAS:");

        txtaAlergias.setColumns(20);
        txtaAlergias.setRows(5);
        jScrollPane1.setViewportView(txtaAlergias);

        txtaEnfermedades.setColumns(20);
        txtaEnfermedades.setRows(5);
        jScrollPane2.setViewportView(txtaEnfermedades);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcbxTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jcbxTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblPacientes);

        jLabel14.setText("BUSCAR POR CÉDULA:");

        txtBuscarporCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarporCedulaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarporCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtBuscarporCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitulo)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        bloquearBotonesNuevo();
        desbloquearTextos();
        txtCedula.requestFocus();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        MetodosGenerales g = new MetodosGenerales();
        if (g.ValidaCedula(txtCedula.getText())) {
             guardarPaciente();
        }else{
            JOptionPane.showMessageDialog(this, "CEDULA INCORRECTA VERIFIQUE SU CEDULA..");
        }
       
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        Modificar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarTextos();
        bloquearTextos();
        bloquearBotonesGuardar();
        tblPacientes.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        BorrarPaciente();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_btnSalirActionPerformed

    private void jdcFechaNacimientoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdcFechaNacimientoFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_jdcFechaNacimientoFocusLost

    private void txtEdadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEdadMouseClicked
        // TODO add your handling code here:
        String EDAD;
        int year = cal.get(Calendar.YEAR);
        int escojido = jdcFechaNacimiento.getDate().getYear() + 1900;
        EDAD = String.valueOf(year - escojido);
        txtEdad.setText(EDAD);
    }//GEN-LAST:event_txtEdadMouseClicked

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        controlLetras(evt);
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        controlLetras(evt);
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtAlturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
    }//GEN-LAST:event_txtAlturaKeyTyped

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtBuscarporCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarporCedulaKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
        carTablaPacientes(txtBuscarporCedula.getText());
    }//GEN-LAST:event_txtBuscarporCedulaKeyTyped

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
            Logger.getLogger(Gestionar_Pacientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestionar_Pacientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Gestionar_Pacientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestionar_Pacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Gestionar_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestionar_Pacientes().setVisible(true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox jcbxTipoSangre;
    private com.toedter.calendar.JDateChooser jdcFechaNacimiento;
    public javax.swing.JLabel lblTitulo;
    public javax.swing.JTable tblPacientes;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscarporCedula;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtaAlergias;
    private javax.swing.JTextArea txtaEnfermedades;
    // End of variables declaration//GEN-END:variables
}
