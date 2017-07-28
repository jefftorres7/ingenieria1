/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static Interfaces.Principal.jdpPrincipal;
import java.awt.Color;
import java.awt.Dimension;
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
public class Gestionar_Medicos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Gestionar_Pacientes
     */
    DefaultTableModel modelo;
    Calendar cal = Calendar.getInstance();

    public Gestionar_Medicos() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        cargarEsp();
        txtEdad.setEditable(false);
        bloquearTextos();
        lblCheck.setVisible(false);
        bloquearBotonesInicio();
        carTablaMedicos(txtBuscarporCedula.getText());
        tblMedicos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int fila = tblMedicos.getSelectedRow();
                if (tblMedicos.getSelectedRow() != -1) {
                    txtCedula.setText(tblMedicos.getValueAt(fila, 0).toString());
                    txtNombres.setText(tblMedicos.getValueAt(fila, 1).toString());
                    txtApellidos.setText(tblMedicos.getValueAt(fila, 2).toString());
                    txtTelefono.setText(tblMedicos.getValueAt(fila, 3).toString());
                    jdcFechaNacimiento.setDate(TransformaraFecha(tblMedicos.getValueAt(fila, 4).toString()));
                    txtEdad.setText(tblMedicos.getValueAt(fila, 5).toString());
                    jcbxEspecialidades.setSelectedItem(tblMedicos.getValueAt(fila, 6).toString());
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

    public void cargarEsp() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql2="";
        sql2="select * from especialidades";
        try {
                Statement psd2 = cn.createStatement();
                ResultSet rs = psd2.executeQuery(sql2);
                while (rs.next()) {
                    jcbxEspecialidades.addItem(rs.getString("NOM_ESP"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO CARGAR LAS ESPECIALIDADED");
            }
    }

    public void bloquearUpdate() {
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(true);
        btnAgregarHorario.setEnabled(true);
    }

    public void bloquearTextos() {
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEdad.setEnabled(false);
        jcbxEspecialidades.setEnabled(false);
        jdcFechaNacimiento.setEnabled(false);
        txtCedula.setEnabled(false);
        btnAgregarHorario.setEnabled(false);
    }

    public void desbloquearTextos() {
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtEdad.setEnabled(true);
        jcbxEspecialidades.setEnabled(true);
        jdcFechaNacimiento.setEnabled(true);
        txtCedula.setEnabled(true);
        btnAgregarHorario.setEnabled(true);
    }

    public void bloquearBotonesInicio() {
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
        btnAgregarHorario.setEnabled(false);
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
        txtTelefono.setText("");
        txtEdad.setText("");
        jcbxEspecialidades.setSelectedIndex(0);
        jdcFechaNacimiento.setDate(TransformaraFecha(FecActual()));
        txtCedula.setText("");
    }

    public void bloquearBotonesGuardar() {
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
        btnAgregarHorario.setEnabled(false);
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

    public void guardarMedico() {
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CÉDULA");
        } else if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS NOMBRES");
        } else if (txtApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS APELLIDOS");
        } else if (jcbxEspecialidades.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "ESCOJA UNA ESPECIALIDAD");
        }  else if (txtEdad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA EDAD");
        } else if (jdcFechaNacimiento.getDateFormatString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA FECHA DE NACIMIENTO");
        } else if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL TELEFONO");
        } else if (lblCheck.isVisible()==false) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN HORARIO PARA EL MÉDICO");
        } else {

            String CED_MED, NOM1_MED, NOM2_MED, APEP_MED, APEM_MED, TEL_MED, FEC_NAC_MED, EDAD, ESPECIALIDAD, ESTADO;
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            CED_MED = txtCedula.getText();
            String a[] = new String[2];
            String p[] = new String[2];
            a = txtNombres.getText().split(" ");
            p = txtApellidos.getText().split(" ");
            int year = cal.get(Calendar.YEAR);
            int escojido = jdcFechaNacimiento.getDate().getYear() + 1900;
            NOM1_MED = a[0];
            NOM2_MED = a[1];
            APEP_MED = p[0];
            APEM_MED = p[1];
            FEC_NAC_MED = FechaaCadena(jdcFechaNacimiento.getDate());
            EDAD = String.valueOf(year - escojido);
            ESPECIALIDAD = jcbxEspecialidades.getSelectedItem().toString();
            if (txtTelefono.getText().isEmpty()) {
                TEL_MED = "NINGUNO";
            } else {
                TEL_MED = txtTelefono.getText();
            }
            ESTADO = "SI";
            String sql = "";
            String sql2 = "";
            String CED;
            sql = "insert into medicos(CED_MED, NOM1_MED, NOM2_MED, APEP_MED, APEM_MED, TEL_MED, FEC_NAC_MED, EDAD, ESPECIALIDAD, ESTADO) values(?,?,?,?,?,?,?,?,?,?)";
            sql2 = "SELECT * FROM medicos";
            try {

                Statement psd2 = cn.createStatement();
                ResultSet rs = psd2.executeQuery(sql2);
                while (rs.next()) {
                    CED = rs.getString("CED_MED");
                    if (CED.equals(txtCedula.getText())) {
                        JOptionPane.showMessageDialog(null, "EL MÉDICO YA EXISTE");
                        txtCedula.setText("");
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                JOptionPane.showMessageDialog(null, "NO SE PUDO VERIFICAR EL MÉDICO");
            }
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, CED_MED);
                psd.setString(2, NOM1_MED);
                psd.setString(3, NOM2_MED);
                psd.setString(4, APEP_MED);
                psd.setString(5, APEM_MED);
                psd.setString(6, TEL_MED);
                psd.setString(7, FEC_NAC_MED);
                psd.setInt(8, Integer.valueOf(EDAD));
                psd.setString(9, ESPECIALIDAD);
                psd.setString(10, ESTADO);
                int n = psd.executeUpdate();

                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                    JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
                    lblCheck.setVisible(false);
                    limpiarTextos();
                    bloquearBotonesGuardar();
                    bloquearTextos();
                    txtCedula.setEnabled(false);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se Inserto el Dato");
            }
        }
        carTablaMedicos(txtBuscarporCedula.getText());
    }

    public void Modificar() {
       if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CÉDULA");
        } else if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS NOMBRES");
        } else if (txtApellidos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOS APELLIDOS");
        } else if (jcbxEspecialidades.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "ESCOJA UNA ESPECIALIDAD");
        }  else if (txtEdad.getText().isEmpty()) {
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
            sql = "UPDATE medicos SET NOM1_MED='" + a[0] + "',"
                    + "NOM2_MED='" + a[1] + "',"
                    + "APEP_MED='" + p[0] + "',"
                    + "APEM_MED='" + p[1] + "',"
                    + "TEL_MED='" + txtTelefono.getText() + "',"
                    + "FEC_NAC_MED='" + FechaaCadena(jdcFechaNacimiento.getDate()) + "',"
                    + "EDAD='" + txtEdad.getText() + "',"
                    + "ESPECIALIDAD='" + jcbxEspecialidades.getSelectedItem().toString() + "'"
                    + "WHERE CED_MED='" + ID + "'";
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
                 JOptionPane.showMessageDialog(null, ex);
                JOptionPane.showMessageDialog(null, "No se pudo Actualizar la fila ");
            }
        }
        carTablaMedicos(txtBuscarporCedula.getText());
    }

    public void BorrarPaciente() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        String sql = "";
        sql = "UPDATE medicos SET ESTADO='NO' where CED_MED='" + txtCedula.getText() + "'";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            int a = JOptionPane.showConfirmDialog(null, "Desea borrar el Médico" + "  " + txtNombres.getText() + "   " + txtApellidos.getText());
            if (a == 0) {
                int n = psd.executeUpdate();
                modelo.removeRow(tblMedicos.getSelectedRow());
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "se elimino correctamente el dato");
                    limpiarTextos();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se Pudo Eliminar el Dato");
        }
        carTablaMedicos(txtBuscarporCedula.getText());
    }

    public void carTablaMedicos(String dato) {
        String[] titulos = {"CÉDULA", "NOMBRES", "APELLIDOS", "TELEFONO", "FEC. NAC", "EDAD", "ESPECIALIDAD"};
        String[] registros = new String[7];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "SELECT * FROM medicos where CED_MED LIKE'%" + dato + "%'AND ESTADO='SI'";
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
            tblMedicos.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        tblMedicos.setModel(modelo);

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
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jdcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        txtTelefono = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcbxEspecialidades = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarHorario = new javax.swing.JButton();
        lblCheck = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMedicos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtBuscarporCedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitulo.setText("NUEVO MÉDICO");

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
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        jLabel3.setText("APELLIDOS:");

        jLabel7.setText("EDAD:");

        jLabel4.setText("TELEDONO:");

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });

        jLabel12.setText("FECHA DE MACIMIENTO");

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

        jLabel8.setText("ESPECIALIDAD:");

        jLabel16.setText("CEDULA:");

        jLabel1.setText("HORARIO:");

        btnAgregarHorario.setText("INGRESAR HORARIO");
        btnAgregarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarHorarioActionPerformed(evt);
            }
        });

        lblCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/checkeo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel16)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombres)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbxEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregarHorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCheck)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jcbxEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(btnAgregarHorario)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblCheck))))
        );

        tblMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblMedicos);

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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTitulo)
                                .addGap(180, 180, 180))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        guardarMedico();
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
        tblMedicos.clearSelection();
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

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtBuscarporCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarporCedulaKeyTyped
        // TODO add your handling code here:
        controlNumeros(evt);
        carTablaMedicos(txtBuscarporCedula.getText());
    }//GEN-LAST:event_txtBuscarporCedulaKeyTyped

    private void btnAgregarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHorarioActionPerformed
        // TODO add your handling code here:
         Insertar_Horario_Medico hormed = new Insertar_Horario_Medico();
        jdpPrincipal.add(hormed);
        Dimension desktopSize = jdpPrincipal.getSize();
        Dimension FrameSize = hormed.getSize();
        hormed.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        hormed.show();
    }//GEN-LAST:event_btnAgregarHorarioActionPerformed

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
            Logger.getLogger(Gestionar_Medicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestionar_Medicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Gestionar_Medicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gestionar_Medicos.class.getName()).log(Level.SEVERE, null, ex);
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
                new Gestionar_Medicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarHorario;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox jcbxEspecialidades;
    private com.toedter.calendar.JDateChooser jdcFechaNacimiento;
    public static javax.swing.JLabel lblCheck;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JTable tblMedicos;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscarporCedula;
    public static javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
