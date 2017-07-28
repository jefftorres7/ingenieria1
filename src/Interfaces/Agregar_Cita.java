/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static Interfaces.Principal.jdpPrincipal;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jefferson
 */
public class Agregar_Cita extends javax.swing.JInternalFrame {

    /**
     * Creates new form Agregar_Cita
     */
    public static String pac;
    public static String med;
    String DESDE, HASTA, DIASELECCIONADO;
    DefaultTableModel modelo;
    Calendar calendarios = Calendar.getInstance();
    Date fe = calendarios.getTime();

    public Agregar_Cita() {
        initComponents();
        bloquearBotonesInicio();
        bloquearElementosInicio();
        carTablaCitas(txtBuscarporCedula.getText());
        desactivarchkdias();
        cargarEsp();
        txtMmedico.setEditable(false);
        txtPacienteCita.setEditable(false);
        txtMmedico.setEnabled(false);
        txtPacienteCita.setEnabled(false);
        btnBuscarMedico.setEnabled(false);
        bntBuscarPpaciente.setEnabled(false);
        chkConfirm.setEnabled(false);
        tblHoras.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int fila = tblHoras.getSelectedRow();
                if (tblHoras.getSelectedRow() != -1) {
                    DESDE = tblHoras.getValueAt(fila, 1).toString();
                    HASTA = tblHoras.getValueAt(fila, 2).toString();
                    DIASELECCIONADO = tblHoras.getValueAt(fila, 0).toString();
                    chkConfirm.setEnabled(true);
                }

            }

        });
    }

    public void desbloquearNuevo() {
        txtPacienteCita.setEnabled(true);
        bntBuscarPpaciente.setEnabled(true);
        jcbxEspecialidades.setEnabled(true);
    }
    
    
    
    

    public void repo() {
        try {
            Conexion cc = new Conexion();
            Map parametros = new HashMap();
            parametros.put("cedula", pac);
            JasperReport reporte = JasperCompileManager.compileReport("C:\\Users\\Jefferson\\Documents\\NetBeansProjects\\CITMEDIC\\src\\Reportes\\repo.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, cc.conectar());
            JInternalFrame AutosRepor = new JInternalFrame("CITA MÉDICA");
            AutosRepor.getContentPane().add(new JRViewer(print));
            jdpPrincipal.add(AutosRepor);
            AutosRepor.pack();
            AutosRepor.setClosable(true);
            AutosRepor.setMaximizable(true);
            AutosRepor.setResizable(true);
            AutosRepor.setVisible(true);
            AutosRepor.setSize(jdpPrincipal.getSize());
//            JasperViewer.viewReport(print);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);

        }
    }

    public void cargarEsp() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql2 = "";
        sql2 = "select * from especialidades";
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

    public void bloquearElementosInicio() {
        tgbLunes1.setEnabled(false);
        tgbMartes1.setEnabled(false);
        tgbMiercoles1.setEnabled(false);
        tgbJueves1.setEnabled(false);
        tgbViernes1.setEnabled(false);
        tgbSabado1.setEnabled(false);
        tgbDomingo1.setEnabled(false);
        jcbxEspecialidades.setEnabled(false);
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
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
    }

    public void desactivarchkdias() {
        chxLunes.setEnabled(false);
        chxMartes.setEnabled(false);
        chxMiercoles.setEnabled(false);
        chxJueves.setEnabled(false);
        chxViernes.setEnabled(false);
        chxSabado.setEnabled(false);
        chxDomingo.setEnabled(false);
    }
    

    public void carTablaHorarios(String dato, String DIA) {
        String[] titulos = {"DIA", "DESDE", "HASTA", "ESTADO"};
        String[] registros = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "SELECT * FROM horarios where COD_MED_PER ='" + dato + "'AND DIA ='" + DIA + "' AND ESTADO='SI' ";
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
            tblHoras.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        tblHoras.setModel(modelo);

    }

//    public void cargarDias() {
//        Conexion cc = new Conexion();
//        Connection cn = cc.conectar();
//        String sql = "", sql2 = "";
//        int i = 0;
//        String DIASEL;
//        sql = "SELECT * FROM horarios where COD_MED_PER='" + med + "'";
//        try {
//            Statement psd1 = cn.createStatement();
//            ResultSet rs1 = psd1.executeQuery(sql);
//            while (rs1.next()) {
//                i++;
//                DIASEL=rs1.getString("DIA");
//                if (DIASEL.equals("LUNES")) {
//                    tgbLunes1.setForeground(Color.blue);
//                }
//                if (DIASEL.equals("MARTES")) {
//                    tgbMartes1.setForeground(Color.blue);
//                }
//                if (DIASEL.equals("MIERCOLES")) {
//                    tgbMiercoles1.setForeground(Color.blue);
//                }
//                if (DIASEL.equals("JUEVES")) {
//                    tgbJueves1.setForeground(Color.blue);
//                }
//                if (DIASEL.ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt("VIERNES")) {
//                    tgbViernes1.setForeground(Color.blue);
//                }
//                if (DIASEL.equals("SABADO")) {
//                    tgbSabado1.setForeground(Color.blue);
//                }
//                if (DIASEL.equals("DOMINGO")) {
//                    tgbDomingo1.setForeground(Color.blue);
//                }
//            }
//            if (i == 0) {
//                JOptionPane.showMessageDialog(this, "No existe Horarios..");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void seleccionLuneschx() {
        if (chxLunes.isSelected()) {
            carTablaHorarios(med, "LUNES");
        } else {

        }
    }
// dias de la semana:

    public String devuelvedia() {
        String[] strDays = new String[]{
            "DOMINGO",
            "LUNES",
            "MARTES",
            "MIERCOLES",
            "JUEVES",
            "VIERNES",
            "SABADO"};
        String diasemana = strDays[calendario.getDate().getDay()];
        return diasemana;
    }

    public void guardarCita() {
        boolean ver = false;
        String PAC_CITA, ESPECIALIDADE, MED_CITA, FECHA, HORA, AGENDADA;
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        PAC_CITA = pac;
        ESPECIALIDADE = jcbxEspecialidades.getSelectedItem().toString();
        MED_CITA = med;
        FECHA = FechaaCadena(calendario.getDate());
        HORA = DESDE;
        AGENDADA = "SI";
        String sql = "";
        String sql2 = "";
        String sql3 = "";
        String FECH, PACI, MEDI, ES, HOR, AG;
        sql = "insert into citas(PAC_CITA, ESPECIALIDAD, MED_CITA, FECHA, HORA, AGENDADA) values(?,?,?,?,?,?)";
        sql2 = "SELECT * FROM citas";
        System.out.println(med + DESDE + HASTA + DIASELECCIONADO);
        sql3 = "update horarios set ESTADO='NO' WHERE COD_MED_PER='" + med + "'AND DESDE='" + DESDE + "'AND HASTA='" + HASTA + "'AND DIA='" + DIASELECCIONADO + "'";
        try {

            Statement psd2 = cn.createStatement();
            ResultSet rs = psd2.executeQuery(sql2);
            while (rs.next()) {
                PACI = rs.getString("PAC_CITA");
                ES = rs.getString("ESPECIALIDAD");
                MEDI = rs.getString("MED_CITA");
                FECH = rs.getString("FECHA");
                HOR = rs.getString("HORA");
                AG = rs.getString("AGENDADA");
                if (PACI.equals(PAC_CITA) && ES.equals(ESPECIALIDADE) && MEDI.equals(MED_CITA) && FECH.equals(FECHA) && HOR.equals(HORA) && AG.equals("SI")) {
                    JOptionPane.showMessageDialog(null, "NO SE PUEDE AGENDAR LA CITA PARA EL PACIENTE YA A SIDO ASIGNADA EN LA FECHA SELECCIONADA");
                    calendario.setDate(fe);
                    ver = true;
                }

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "NO SE PUDO VERIFICAR LA CITA");
        }
        if (ver == false) {
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, PAC_CITA);
                psd.setString(2, ESPECIALIDADE);
                psd.setString(3, MED_CITA);
                psd.setString(4, FECHA);
                psd.setString(5, HORA);
                psd.setString(6, AGENDADA);
                int n = psd.executeUpdate();

                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                    JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
                    PreparedStatement psd1 = cn.prepareStatement(sql3);
                    psd1.executeUpdate();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                JOptionPane.showMessageDialog(null, "No se Inserto el Dato");
            }
        }
        carTablaCitas(txtBuscarporCedula.getText());
    }

    public void carTablaCitas(String dato) {
        String[] titulos = {"NUM_CITA", "PACIENTE", "MEDICO", "ESPECIALIDAD", "FECHA", "HORA"};
        String[] registros = new String[6];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "", sql1 = "", sql2 = "";
        sql = "SELECT * FROM citas where PAC_CITA LIKE'%" + dato + "%'AND AGENDADA='SI'";
        try {

            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("NUM_CITA");
                registros[1] = rs.getString("PAC_CITA");
                registros[2] = rs.getString("MED_CITA");
                registros[3] = rs.getString("ESPECIALIDAD");
                registros[4] = rs.getString("FECHA");
                registros[5] = rs.getString("HORA");
                modelo.addRow(registros);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        tblCitas.setModel(modelo);

    }

    public void seleccionMarteschx() {
        if (chxMartes.isSelected()) {
            carTablaHorarios(med, "MARTES");
        } else {

        }
    }

    public void seleccionMiercoleschx() {
        if (chxMiercoles.isSelected()) {
            carTablaHorarios(med, "MIERCOLES");
        } else {

        }
    }

    public void seleccionJueveschx() {
        if (chxJueves.isSelected()) {
            carTablaHorarios(med, "JUEVES");
        } else {

        }
    }

    public void seleccionVierneschx() {
        if (chxViernes.isSelected()) {
            carTablaHorarios(med, "VIERNES");
        } else {

        }
    }

    public void seleccionSabadochx() {
        if (chxSabado.isSelected()) {
            carTablaHorarios(med, "SABADO");
        } else {

        }
    }

    public void seleccionDomingochx() {
        if (chxDomingo.isSelected()) {
            carTablaHorarios(med, "DOMINGO");
        } else {

        }
    }

    public String FechaaCadena(Date fec) {
        String FECHA;
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        FECHA = formatoFecha.format(fec);
        return FECHA;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        bntBuscarPpaciente = new javax.swing.JButton();
        txtPacienteCita = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarMedico = new javax.swing.JButton();
        txtMmedico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbxEspecialidades = new javax.swing.JComboBox();
        pnlDias1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tgbDomingo1 = new javax.swing.JToggleButton();
        tgbJueves1 = new javax.swing.JToggleButton();
        tgbMiercoles1 = new javax.swing.JToggleButton();
        tgbMartes1 = new javax.swing.JToggleButton();
        tgbLunes1 = new javax.swing.JToggleButton();
        tgbSabado1 = new javax.swing.JToggleButton();
        tgbViernes1 = new javax.swing.JToggleButton();
        chxLunes = new javax.swing.JCheckBox();
        chxMartes = new javax.swing.JCheckBox();
        chxMiercoles = new javax.swing.JCheckBox();
        chxJueves = new javax.swing.JCheckBox();
        chxViernes = new javax.swing.JCheckBox();
        chxSabado = new javax.swing.JCheckBox();
        chxDomingo = new javax.swing.JCheckBox();
        calendario = new com.toedter.calendar.JCalendar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoras = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaConfirmacion = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        chkConfirm = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCitas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtBuscarporCedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("NUEVA CITA");

        bntBuscarPpaciente.setText("BUSCAR PACIENTE");
        bntBuscarPpaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBuscarPpacienteActionPerformed(evt);
            }
        });

        txtPacienteCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPacienteCitaActionPerformed(evt);
            }
        });

        jLabel2.setText("PACIENTE:");

        btnBuscarMedico.setText("BUSCAR MÉDICO");
        btnBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicoActionPerformed(evt);
            }
        });

        txtMmedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMmedicoActionPerformed(evt);
            }
        });

        jLabel3.setText("MEDICO:");

        jLabel4.setText("ESPECIALIDAD:");

        jcbxEspecialidades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbxEspecialidadesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPacienteCita, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtMmedico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bntBuscarPpaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPacienteCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bntBuscarPpaciente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbxEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMmedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMedico)))
        );

        jLabel6.setText("DIAS DISPONIBLES:");

        tgbDomingo1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbDomingo1.setText("D");
        tgbDomingo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbDomingo1ActionPerformed(evt);
            }
        });

        tgbJueves1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbJueves1.setText("J");
        tgbJueves1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbJueves1ActionPerformed(evt);
            }
        });

        tgbMiercoles1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbMiercoles1.setText("X");
        tgbMiercoles1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbMiercoles1ActionPerformed(evt);
            }
        });

        tgbMartes1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbMartes1.setText("M");
        tgbMartes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbMartes1ActionPerformed(evt);
            }
        });

        tgbLunes1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbLunes1.setText("L");
        tgbLunes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbLunes1ActionPerformed(evt);
            }
        });

        tgbSabado1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbSabado1.setText("S");
        tgbSabado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbSabado1ActionPerformed(evt);
            }
        });

        tgbViernes1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbViernes1.setText("V");
        tgbViernes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbViernes1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(chxLunes);
        chxLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chxLunesActionPerformed(evt);
            }
        });

        buttonGroup1.add(chxMartes);
        chxMartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chxMartesActionPerformed(evt);
            }
        });

        buttonGroup1.add(chxMiercoles);
        chxMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chxMiercolesActionPerformed(evt);
            }
        });

        buttonGroup1.add(chxJueves);
        chxJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chxJuevesActionPerformed(evt);
            }
        });

        buttonGroup1.add(chxViernes);
        chxViernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chxViernesActionPerformed(evt);
            }
        });

        buttonGroup1.add(chxSabado);
        chxSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chxSabadoActionPerformed(evt);
            }
        });

        buttonGroup1.add(chxDomingo);
        chxDomingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chxDomingoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDias1Layout = new javax.swing.GroupLayout(pnlDias1);
        pnlDias1.setLayout(pnlDias1Layout);
        pnlDias1Layout.setHorizontalGroup(
            pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDias1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgbLunes1)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chxLunes)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgbMartes1)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chxMartes)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgbMiercoles1)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chxMiercoles)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgbJueves1)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chxJueves)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgbViernes1)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chxViernes)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgbSabado1)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chxSabado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chxDomingo))
                    .addComponent(tgbDomingo1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDias1Layout.setVerticalGroup(
            pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDias1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDias1Layout.createSequentialGroup()
                        .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgbJueves1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tgbLunes1)
                                .addComponent(tgbMartes1)
                                .addComponent(tgbMiercoles1)
                                .addComponent(tgbViernes1)
                                .addComponent(tgbSabado1)
                                .addComponent(tgbDomingo1)))
                        .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDias1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDias1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(chxLunes))
                                    .addGroup(pnlDias1Layout.createSequentialGroup()
                                        .addComponent(chxMartes)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(pnlDias1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(chxJueves)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDias1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chxSabado, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chxDomingo, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDias1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlDias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chxMiercoles, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chxViernes, javax.swing.GroupLayout.Alignment.TRAILING)))))
        );

        tblHoras.setBackground(new java.awt.Color(0, 153, 51));
        tblHoras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblHoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblHoras);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel5.setText("ESCOJA LA HORA DE LA TABLA PARA LA CITA:");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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

        areaConfirmacion.setEditable(false);
        areaConfirmacion.setColumns(20);
        areaConfirmacion.setRows(5);
        jScrollPane2.setViewportView(areaConfirmacion);

        jLabel7.setText("CONFIRMACION:");

        chkConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConfirmActionPerformed(evt);
            }
        });

        jLabel8.setText("ACEPTE LA CONFIRMACION PARA GUARDAR");

        tblCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblCitas);

        jLabel14.setText("BUSCAR POR CÉDULA DEL PACIENTE :");

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
                .addContainerGap()
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(calendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(146, 146, 146)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkConfirm))
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(chkConfirm))
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntBuscarPpacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarPpacienteActionPerformed
        // TODO add your handling code here:
        Seleccionar_Paciente hormed = new Seleccionar_Paciente();
        jdpPrincipal.add(hormed);
        Dimension desktopSize = jdpPrincipal.getSize();
        Dimension FrameSize = hormed.getSize();
        hormed.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        hormed.show();
    }//GEN-LAST:event_bntBuscarPpacienteActionPerformed

    private void btnBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicoActionPerformed
        // TODO add your handling code here:
        Seleccionar_Medico hormed = new Seleccionar_Medico();
        jdpPrincipal.add(hormed);
        Dimension desktopSize = jdpPrincipal.getSize();
        Dimension FrameSize = hormed.getSize();
        hormed.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        hormed.show();
    }//GEN-LAST:event_btnBuscarMedicoActionPerformed

    private void txtPacienteCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPacienteCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteCitaActionPerformed

    private void tgbDomingo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbDomingo1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tgbDomingo1ActionPerformed

    private void tgbJueves1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbJueves1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tgbJueves1ActionPerformed

    private void tgbMiercoles1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbMiercoles1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tgbMiercoles1ActionPerformed

    private void tgbMartes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbMartes1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tgbMartes1ActionPerformed

    private void tgbLunes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbLunes1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tgbLunes1ActionPerformed

    private void tgbSabado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbSabado1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tgbSabado1ActionPerformed

    private void tgbViernes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbViernes1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tgbViernes1ActionPerformed

    private void txtMmedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMmedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMmedicoActionPerformed

    private void chxLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chxLunesActionPerformed
        // TODO add your handling code here:
        seleccionLuneschx();
    }//GEN-LAST:event_chxLunesActionPerformed

    private void chxMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chxMartesActionPerformed
        // TODO add your handling code here:
        seleccionMarteschx();
    }//GEN-LAST:event_chxMartesActionPerformed

    private void chxMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chxMiercolesActionPerformed
        // TODO add your handling code here:
        seleccionMiercoleschx();
    }//GEN-LAST:event_chxMiercolesActionPerformed

    private void chxJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chxJuevesActionPerformed
        // TODO add your handling code here:
        seleccionJueveschx();
    }//GEN-LAST:event_chxJuevesActionPerformed

    private void chxViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chxViernesActionPerformed
        // TODO add your handling code here:
        seleccionVierneschx();
    }//GEN-LAST:event_chxViernesActionPerformed

    private void chxSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chxSabadoActionPerformed
        // TODO add your handling code here:
        seleccionSabadochx();
    }//GEN-LAST:event_chxSabadoActionPerformed

    private void chxDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chxDomingoActionPerformed
        // TODO add your handling code here:
        seleccionDomingochx();
    }//GEN-LAST:event_chxDomingoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        bloquearBotonesNuevo();
        desbloquearNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardarCita();
        repo();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
//        Modificar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
//        BorrarPaciente();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void chkConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConfirmActionPerformed
        // TODO add your handling code here:
        if (devuelvedia().equals(DIASELECCIONADO) && calendario.getDate().after(fe)) {
            if (chkConfirm.isSelected()) {
                btnGuardar.setEnabled(true);
                areaConfirmacion.setText("PACIENTE: " + " " + txtPacienteCita.getText() + "\n" + "MEDICO:" + " "
                        + txtMmedico.getText() + "\n" + "FECHA: " + " "
                        + FechaaCadena(calendario.getDate())
                        + "\n" + "DIA: " + " " + DIASELECCIONADO
                        + "\n" + "HORA:" + " " + DESDE + " - " + HASTA);
                devuelvedia();
            } else {
                btnGuardar.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "FECHA INCORRECTA EL DIA DE LA FECHA DEBE SER: " + " " + DIASELECCIONADO + " " + "Y SUPERIOR A LA FECHA ACTUAL");
            chkConfirm.setSelected(false);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_chkConfirmActionPerformed

    private void txtBuscarporCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarporCedulaKeyTyped
        // TODO add your handling code here:
//        controlNumeros(evt);
//        carTablaMedicos(txtBuscarporCedula.getText());
        carTablaCitas(txtBuscarporCedula.getText());
    }//GEN-LAST:event_txtBuscarporCedulaKeyTyped

    private void jcbxEspecialidadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbxEspecialidadesItemStateChanged
        // TODO add your handling code here:
        txtMmedico.setText("");
    }//GEN-LAST:event_jcbxEspecialidadesItemStateChanged

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
            java.util.logging.Logger.getLogger(Agregar_Cita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Cita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Cita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Cita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregar_Cita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea areaConfirmacion;
    private javax.swing.JButton bntBuscarPpaciente;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    public static javax.swing.JButton btnBuscarMedico;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JCalendar calendario;
    private javax.swing.JCheckBox chkConfirm;
    public static javax.swing.JCheckBox chxDomingo;
    public static javax.swing.JCheckBox chxJueves;
    public static javax.swing.JCheckBox chxLunes;
    public static javax.swing.JCheckBox chxMartes;
    public static javax.swing.JCheckBox chxMiercoles;
    public static javax.swing.JCheckBox chxSabado;
    public static javax.swing.JCheckBox chxViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JComboBox jcbxEspecialidades;
    private javax.swing.JPanel pnlDias1;
    private javax.swing.JTable tblCitas;
    public static javax.swing.JTable tblHoras;
    public static javax.swing.JToggleButton tgbDomingo1;
    public static javax.swing.JToggleButton tgbJueves1;
    public static javax.swing.JToggleButton tgbLunes1;
    public static javax.swing.JToggleButton tgbMartes1;
    public static javax.swing.JToggleButton tgbMiercoles1;
    public static javax.swing.JToggleButton tgbSabado1;
    public static javax.swing.JToggleButton tgbViernes1;
    private javax.swing.JTextField txtBuscarporCedula;
    public static javax.swing.JTextField txtMmedico;
    public static javax.swing.JTextField txtPacienteCita;
    // End of variables declaration//GEN-END:variables
}
