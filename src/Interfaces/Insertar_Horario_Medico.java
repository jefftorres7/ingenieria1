/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static Interfaces.Gestionar_Medicos.txtCedula;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jefferson
 */
public class Insertar_Horario_Medico extends javax.swing.JInternalFrame {

    /**
     * Creates new form Insertar_Horario_Medico
     */
    DefaultTableModel modelo;
    String dia, DESDE, HASTA;

    public Insertar_Horario_Medico() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        bloquearBotonesInicio();
        bloquearElementosInicio();
        pnlMiercoles.setVisible(false);
        pnlLunes.setVisible(false);
        pnlMartes.setVisible(false);
        pnlJueves.setVisible(false);
        pnlViernes.setVisible(false);
        pnlSabado.setVisible(false);
        pnlDomingo.setVisible(false);
        cargarHoras(cbxDesdeLunes);
        cargarHoras(cbxDesdeMartes);
        cargarHoras(cbxDesdeMiercoles);
        cargarHoras(cbxDesdeJueves);
        cargarHoras(cbxDesdeViernes);
        cargarHoras(cbxDesdeSabado);
        cargarHoras(cbxDesdeDomingo);
        cargarHoras(cbxHastaLunes);
        cargarHoras(cbxHastaMartes);
        cargarHoras(cbxHastaMiercoles);
        cargarHoras(cbxHastaJueves);
        cargarHoras(cbxHastaViernes);
        cargarHoras(cbxHastaSabado);
        cargarHoras(cbxHastaDomingo);
        carTablaHorarios(Gestionar_Medicos.txtCedula.getText());
        tblHorarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int fila = tblHorarios.getSelectedRow();
                if (tblHorarios.getSelectedRow() != -1) {
                    if (tblHorarios.getValueAt(fila, 0).toString().equals("LUNES")) {
                        tgbLunes.setSelected(true);
                        tgbMartes.setSelected(false);
                        tgbMiercoles.setSelected(false);
                        tgbJueves.setSelected(false);
                        tgbViernes.setSelected(false);
                        tgbSabado.setSelected(false);
                        tgbDomingo.setSelected(false);
                        pnlLunes.setVisible(true);
                        pnlMartes.setVisible(false);
                        pnlMiercoles.setVisible(false);
                        pnlJueves.setVisible(false);
                        pnlViernes.setVisible(false);
                        pnlSabado.setVisible(false);
                        pnlDomingo.setVisible(false);
//                        seleccionLunes();
                        cbxDesdeLunes.setSelectedItem(tblHorarios.getValueAt(fila, 1).toString());
                        cbxHastaLunes.setSelectedItem(tblHorarios.getValueAt(fila, 2).toString());
                        dia = "LUNES";
                    }
                    if (tblHorarios.getValueAt(fila, 0).toString().equals("MARTES")) {
                        tgbMartes.setSelected(true);
                        tgbMiercoles.setSelected(false);
                        tgbJueves.setSelected(false);
                        tgbViernes.setSelected(false);
                        tgbSabado.setSelected(false);
                        tgbDomingo.setSelected(false);
                        tgbLunes.setSelected(false);
                        pnlLunes.setVisible(false);
                        pnlMartes.setVisible(true);
                        pnlMiercoles.setVisible(false);
                        pnlJueves.setVisible(false);
                        pnlViernes.setVisible(false);
                        pnlSabado.setVisible(false);
                        pnlDomingo.setVisible(false);

                        cbxDesdeMartes.setSelectedItem(tblHorarios.getValueAt(fila, 1).toString());
                        cbxHastaMartes.setSelectedItem(tblHorarios.getValueAt(fila, 2).toString());
                        dia = "MARTES";
                    }
                    if (tblHorarios.getValueAt(fila, 0).toString().equals("MIERCOLES")) {
                        tgbMartes.setSelected(false);
                        tgbMiercoles.setSelected(true);
                        tgbJueves.setSelected(false);
                        tgbViernes.setSelected(false);
                        tgbSabado.setSelected(false);
                        tgbDomingo.setSelected(false);
                        tgbLunes.setSelected(false);
                        pnlLunes.setVisible(false);
                        pnlMartes.setVisible(false);
                        pnlMiercoles.setVisible(true);
                        pnlJueves.setVisible(false);
                        pnlViernes.setVisible(false);
                        pnlSabado.setVisible(false);
                        pnlDomingo.setVisible(false);

                        cbxDesdeMartes.setSelectedItem(tblHorarios.getValueAt(fila, 1).toString());
                        cbxHastaMartes.setSelectedItem(tblHorarios.getValueAt(fila, 2).toString());
                        dia = "MIERCOLES";
                    }
                    if (tblHorarios.getValueAt(fila, 0).toString().equals("JUEVES")) {
                        tgbMartes.setSelected(false);
                        tgbMiercoles.setSelected(false);
                        tgbJueves.setSelected(true);
                        tgbViernes.setSelected(false);
                        tgbSabado.setSelected(false);
                        tgbDomingo.setSelected(false);
                        tgbLunes.setSelected(false);
                        pnlLunes.setVisible(false);
                        pnlMartes.setVisible(false);
                        pnlMiercoles.setVisible(false);
                        pnlJueves.setVisible(true);
                        pnlViernes.setVisible(false);
                        pnlSabado.setVisible(false);
                        pnlDomingo.setVisible(false);

                        cbxDesdeMartes.setSelectedItem(tblHorarios.getValueAt(fila, 1).toString());
                        cbxHastaMartes.setSelectedItem(tblHorarios.getValueAt(fila, 2).toString());
                        dia = "JUEVES";
                    }
                    if (tblHorarios.getValueAt(fila, 0).toString().equals("VIERNES")) {
                        tgbMartes.setSelected(false);
                        tgbMiercoles.setSelected(false);
                        tgbJueves.setSelected(false);
                        tgbViernes.setSelected(true);
                        tgbSabado.setSelected(false);
                        tgbDomingo.setSelected(false);
                        tgbLunes.setSelected(false);
                        pnlLunes.setVisible(false);
                        pnlMartes.setVisible(false);
                        pnlMiercoles.setVisible(false);
                        pnlJueves.setVisible(false);
                        pnlViernes.setVisible(true);
                        pnlSabado.setVisible(false);
                        pnlDomingo.setVisible(false);

                        cbxDesdeMartes.setSelectedItem(tblHorarios.getValueAt(fila, 1).toString());
                        cbxHastaMartes.setSelectedItem(tblHorarios.getValueAt(fila, 2).toString());
                        dia = "VIERNES";
                    }
                    if (tblHorarios.getValueAt(fila, 0).toString().equals("SABADO")) {
                        tgbMartes.setSelected(false);
                        tgbMiercoles.setSelected(false);
                        tgbJueves.setSelected(false);
                        tgbViernes.setSelected(false);
                        tgbSabado.setSelected(true);
                        tgbDomingo.setSelected(false);
                        tgbLunes.setSelected(false);
                        pnlLunes.setVisible(false);
                        pnlMartes.setVisible(false);
                        pnlMiercoles.setVisible(false);
                        pnlJueves.setVisible(false);
                        pnlViernes.setVisible(false);
                        pnlSabado.setVisible(true);
                        pnlDomingo.setVisible(false);

                        cbxDesdeMartes.setSelectedItem(tblHorarios.getValueAt(fila, 1).toString());
                        cbxHastaMartes.setSelectedItem(tblHorarios.getValueAt(fila, 2).toString());
                        dia = "SABADO";
                    }
                    if (tblHorarios.getValueAt(fila, 0).toString().equals("DOMINGO")) {
                        tgbMartes.setSelected(false);
                        tgbMiercoles.setSelected(false);
                        tgbJueves.setSelected(false);
                        tgbViernes.setSelected(false);
                        tgbSabado.setSelected(false);
                        tgbDomingo.setSelected(true);
                        tgbLunes.setSelected(false);
                        pnlLunes.setVisible(false);
                        pnlMartes.setVisible(false);
                        pnlMiercoles.setVisible(false);
                        pnlJueves.setVisible(false);
                        pnlViernes.setVisible(false);
                        pnlSabado.setVisible(false);
                        pnlDomingo.setVisible(true);

                        cbxDesdeMartes.setSelectedItem(tblHorarios.getValueAt(fila, 1).toString());
                        cbxHastaMartes.setSelectedItem(tblHorarios.getValueAt(fila, 2).toString());
                        dia = "DOMINGO";
                    }
                    desbloquearElementosNuevo();
                    bloquearUpdate();
                    DESDE = tblHorarios.getValueAt(fila, 1).toString();
                    HASTA = tblHorarios.getValueAt(fila, 2).toString();
                }

            }

        });
    }

    public void bloquearUpdate() {
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(true);
    }

    public void bloquearBotonesInicio() {
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
    }

    public void bloquearBotonesNuevo() {
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
    }

    public void bloquearBotonesGuardar() {
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnBorrar.setEnabled(false);
    }

    public void bloquearElementosInicio() {
        tgbLunes.setEnabled(false);
        tgbMartes.setEnabled(false);
        tgbMiercoles.setEnabled(false);
        tgbJueves.setEnabled(false);
        tgbViernes.setEnabled(false);
        tgbSabado.setEnabled(false);
        tgbDomingo.setEnabled(false);
    }

    public void desbloquearElementosNuevo() {
        tgbLunes.setEnabled(true);
        tgbMartes.setEnabled(true);
        tgbMiercoles.setEnabled(true);
        tgbJueves.setEnabled(true);
        tgbViernes.setEnabled(true);
        tgbSabado.setEnabled(true);
        tgbDomingo.setEnabled(true);
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

    public void cargarHoras(JComboBox jcbHoras) {
        jcbHoras.addItem(" ");
        jcbHoras.addItem("1:00");
        jcbHoras.addItem("2:00");
        jcbHoras.addItem("3:00");
        jcbHoras.addItem("4:00");
        jcbHoras.addItem("5:00");
        jcbHoras.addItem("6:00");
        jcbHoras.addItem("7:00");
        jcbHoras.addItem("8:00");
        jcbHoras.addItem("9:00");
        jcbHoras.addItem("10:00");
        jcbHoras.addItem("11:00");
        jcbHoras.addItem("12:00");
        jcbHoras.addItem("13:00");
        jcbHoras.addItem("14:00");
        jcbHoras.addItem("15:00");
        jcbHoras.addItem("16:00");
        jcbHoras.addItem("17:00");
        jcbHoras.addItem("18:00");
        jcbHoras.addItem("19:00");
        jcbHoras.addItem("20:00");
        jcbHoras.addItem("21:00");
        jcbHoras.addItem("22:00");
        jcbHoras.addItem("23:00");
        jcbHoras.addItem("24:00");
    }

    public void deseleccionar() {
        tgbLunes.setSelected(false);
        tgbMartes.setSelected(false);
        tgbMiercoles.setSelected(false);
        tgbJueves.setSelected(false);
        tgbViernes.setSelected(false);
        tgbSabado.setSelected(false);
        tgbDomingo.setSelected(false);

    }

    public void seleccionLunes() {
        if (tgbLunes.isSelected()) {
            pnlLunes.setVisible(true);
            tgbMartes.setEnabled(false);
            tgbMiercoles.setEnabled(false);
            tgbJueves.setEnabled(false);
            tgbViernes.setEnabled(false);
            tgbSabado.setEnabled(false);
            tgbDomingo.setEnabled(false);
        } else {
            pnlLunes.setVisible(false);
            desbloquearElementosNuevo();
        }
    }

    public void seleccionMartes() {
        if (tgbMartes.isSelected()) {
            pnlMartes.setVisible(true);
            tgbLunes.setEnabled(false);
            tgbMiercoles.setEnabled(false);
            tgbJueves.setEnabled(false);
            tgbViernes.setEnabled(false);
            tgbSabado.setEnabled(false);
            tgbDomingo.setEnabled(false);
        } else {
            pnlMartes.setVisible(false);
            desbloquearElementosNuevo();
        }
    }

    public void seleccionMiercoles() {
        if (tgbMiercoles.isSelected()) {
            pnlMiercoles.setVisible(true);
            tgbLunes.setEnabled(false);
            tgbMartes.setEnabled(false);
            tgbJueves.setEnabled(false);
            tgbViernes.setEnabled(false);
            tgbSabado.setEnabled(false);
            tgbDomingo.setEnabled(false);
        } else {
            pnlMiercoles.setVisible(false);
            desbloquearElementosNuevo();
        }
    }

    public void seleccionJueves() {
        if (tgbJueves.isSelected()) {
            pnlJueves.setVisible(true);
            tgbLunes.setEnabled(false);
            tgbMartes.setEnabled(false);
            tgbMiercoles.setEnabled(false);
            tgbViernes.setEnabled(false);
            tgbSabado.setEnabled(false);
            tgbDomingo.setEnabled(false);
        } else {
            pnlJueves.setVisible(false);
            desbloquearElementosNuevo();
        }
    }

    public void seleccionViernes() {
        if (tgbViernes.isSelected()) {
            pnlViernes.setVisible(true);
            tgbLunes.setEnabled(false);
            tgbMartes.setEnabled(false);
            tgbMiercoles.setEnabled(false);
            tgbJueves.setEnabled(false);
            tgbSabado.setEnabled(false);
            tgbDomingo.setEnabled(false);
        } else {
            pnlViernes.setVisible(false);
            desbloquearElementosNuevo();
        }
    }

    public void seleccionSabado() {
        if (tgbSabado.isSelected()) {
            pnlSabado.setVisible(true);
            tgbLunes.setEnabled(false);
            tgbMartes.setEnabled(false);
            tgbMiercoles.setEnabled(false);
            tgbJueves.setEnabled(false);
            tgbViernes.setEnabled(false);
            tgbDomingo.setEnabled(false);
        } else {
            pnlSabado.setVisible(false);
            desbloquearElementosNuevo();
        }
    }

    public void seleccionDomingo() {
        if (tgbDomingo.isSelected()) {
            pnlDomingo.setVisible(true);
            tgbLunes.setEnabled(false);
            tgbMartes.setEnabled(false);
            tgbMiercoles.setEnabled(false);
            tgbJueves.setEnabled(false);
            tgbViernes.setEnabled(false);
            tgbSabado.setEnabled(false);
        } else {
            pnlDomingo.setVisible(false);
            desbloquearElementosNuevo();
        }
    }

    public void guardarHorario() {
        if (tgbLunes.isSelected() && (cbxDesdeLunes.getSelectedIndex() == 0 || cbxHastaLunes.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "ESCOJA EL HORARIO PARA EL DIA LUNES..");
        } else if (tgbMartes.isSelected() && (cbxDesdeMartes.getSelectedIndex() == 0 || cbxHastaMartes.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "ESCOJA EL HORARIO PARA EL DIA MARTES..");
        } else if (tgbMiercoles.isSelected() && (cbxDesdeMiercoles.getSelectedIndex() == 0 || cbxHastaMiercoles.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "ESCOJA EL HORARIO PARA EL DIA MIERCOLES..");
        } else if (tgbJueves.isSelected() && (cbxDesdeJueves.getSelectedIndex() == 0 || cbxHastaJueves.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "ESCOJA EL HORARIO PARA EL DIA JUEVES..");
        } else if (tgbViernes.isSelected() && (cbxDesdeViernes.getSelectedIndex() == 0 || cbxHastaViernes.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "ESCOJA EL HORARIO PARA EL DIA VIERNES..");
        } else if (tgbSabado.isSelected() && (cbxDesdeSabado.getSelectedIndex() == 0 || cbxHastaSabado.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "ESCOJA EL HORARIO PARA EL DIA SABADO..");
        } else if (tgbDomingo.isSelected() && (cbxDesdeDomingo.getSelectedIndex() == 0 || cbxHastaDomingo.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "ESCOJA EL HORARIO PARA EL DIA DOMINGO..");
        } else if (Gestionar_Medicos.txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "PRIMERO DEBE REGISTRAR AL MÉDICO..");
        } else {

            String DIA, DESDE, HASTA, COD_MED_PER, ESTADO;
            String tem = null, temdesde = null, temhasta = null;
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            if (tgbLunes.isSelected()) {
                tem = "LUNES";
                temdesde = cbxDesdeLunes.getSelectedItem().toString();
                temhasta = cbxHastaLunes.getSelectedItem().toString();
            }
            if (tgbMartes.isSelected()) {
                tem = "MARTES";
                temdesde = cbxDesdeMartes.getSelectedItem().toString();
                temhasta = cbxHastaMartes.getSelectedItem().toString();
            }
            if (tgbMiercoles.isSelected()) {
                tem = "MIERCOLES";
                temdesde = cbxDesdeMiercoles.getSelectedItem().toString();
                temhasta = cbxHastaMiercoles.getSelectedItem().toString();
            }
            if (tgbJueves.isSelected()) {
                tem = "JUEVES";
                temdesde = cbxDesdeJueves.getSelectedItem().toString();
                temhasta = cbxHastaJueves.getSelectedItem().toString();
            }
            if (tgbViernes.isSelected()) {
                tem = "VIERNES";
                temdesde = cbxDesdeViernes.getSelectedItem().toString();
                temhasta = cbxHastaViernes.getSelectedItem().toString();
            }
            if (tgbSabado.isSelected()) {
                tem = "SABADO";
                temdesde = cbxDesdeSabado.getSelectedItem().toString();
                temhasta = cbxHastaSabado.getSelectedItem().toString();
            }
            if (tgbDomingo.isSelected()) {
                tem = "DOMINGO";
                temdesde = cbxDesdeDomingo.getSelectedItem().toString();
                temhasta = cbxHastaDomingo.getSelectedItem().toString();
            }
            DIA = tem;
            DESDE = temdesde;
            HASTA = temhasta;
            COD_MED_PER = Gestionar_Medicos.txtCedula.getText();
            ESTADO = "SI";
            String sql = "";
            sql = "insert into horarios(DIA, DESDE, HASTA, COD_MED_PER, ESTADO) values(?,?,?,?,?)";
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, DIA);
                psd.setString(2, DESDE);
                psd.setString(3, HASTA);
                psd.setString(4, COD_MED_PER);
                psd.setString(5, ESTADO);
                int n = psd.executeUpdate();

                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                    JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
                    Gestionar_Medicos.lblCheck.setVisible(true);
                    bloquearElementosInicio();
                    bloquearBotonesGuardar();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se Inserto el Dato");
            }
        }
        carTablaHorarios(Gestionar_Medicos.txtCedula.getText());
    }

    public void BorrarHorario() {
        System.out.println(dia);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        String sql = "";
        sql = "UPDATE horarios SET ESTADO='NO' where COD_MED_PER='" + Gestionar_Medicos.txtCedula.getText() + "' AND DIA='" + dia + "' AND DESDE='" + DESDE + "'AND HASTA='" + HASTA + "'";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            int a = JOptionPane.showConfirmDialog(null, "Desea borrar el horario del doctor" + "  " + Gestionar_Medicos.txtCedula.getText() + "  del día  " + dia + " " + DESDE + " " + HASTA);
            if (a == 0) {
                int n = psd.executeUpdate();
                modelo.removeRow(tblHorarios.getSelectedRow());
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "se elimino correctamente el dato");
                    pnlMiercoles.setVisible(false);
                    pnlLunes.setVisible(false);
                    pnlMartes.setVisible(false);
                    pnlJueves.setVisible(false);
                    pnlViernes.setVisible(false);
                    pnlSabado.setVisible(false);
                    pnlDomingo.setVisible(false);
                    bloquearElementosInicio();
                    bloquearBotonesInicio();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "No se Pudo Eliminar el Dato");
        }
        carTablaHorarios(Gestionar_Medicos.txtCedula.getText());
    }

    public void carTablaHorarios(String dato) {
        String[] titulos = {"DIA", "DESDE", "HASTA", "ESTADO"};
        String[] registros = new String[4];
        modelo = new DefaultTableModel(null, titulos);
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "SELECT * FROM horarios where COD_MED_PER LIKE'%" + dato + "%' AND ESTADO='SI'";
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
            tblHorarios.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        tblHorarios.setModel(modelo);

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
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pnlLunes = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxDesdeLunes = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbxHastaLunes = new javax.swing.JComboBox();
        pnlMartes = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxDesdeMartes = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbxHastaMartes = new javax.swing.JComboBox();
        pnlMiercoles = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxDesdeMiercoles = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cbxHastaMiercoles = new javax.swing.JComboBox();
        pnlJueves = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbxDesdeJueves = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        cbxHastaJueves = new javax.swing.JComboBox();
        pnlViernes = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbxDesdeViernes = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        cbxHastaViernes = new javax.swing.JComboBox();
        pnlSabado = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbxDesdeSabado = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        cbxHastaSabado = new javax.swing.JComboBox();
        pnlDomingo = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbxDesdeDomingo = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        cbxHastaDomingo = new javax.swing.JComboBox();
        pnlDias = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tgbDomingo = new javax.swing.JToggleButton();
        tgbJueves = new javax.swing.JToggleButton();
        tgbMiercoles = new javax.swing.JToggleButton();
        tgbMartes = new javax.swing.JToggleButton();
        tgbLunes = new javax.swing.JToggleButton();
        tgbSabado = new javax.swing.JToggleButton();
        tgbViernes = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHorarios = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        txtBusacarporCedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("INSERTAR HORARIO MÉDICO");

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
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
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
                .addGap(21, 21, 21)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnBorrar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel3.setText("          LUNES:");

        jLabel4.setText("DESDE:");

        jLabel5.setText("HASTA:");

        javax.swing.GroupLayout pnlLunesLayout = new javax.swing.GroupLayout(pnlLunes);
        pnlLunes.setLayout(pnlLunesLayout);
        pnlLunesLayout.setHorizontalGroup(
            pnlLunesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLunesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(48, 48, 48)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cbxDesdeLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(cbxHastaLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLunesLayout.setVerticalGroup(
            pnlLunesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLunesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLunesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cbxDesdeLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbxHastaLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("        MARTES:");

        jLabel7.setText("DESDE:");

        jLabel8.setText("HASTA:");

        javax.swing.GroupLayout pnlMartesLayout = new javax.swing.GroupLayout(pnlMartes);
        pnlMartes.setLayout(pnlMartesLayout);
        pnlMartesLayout.setHorizontalGroup(
            pnlMartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMartesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(47, 47, 47)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cbxDesdeMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cbxHastaMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMartesLayout.setVerticalGroup(
            pnlMartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMartesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cbxDesdeMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbxHastaMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("   MIÉRCOLES:");

        jLabel10.setText("DESDE:");

        jLabel11.setText("HASTA:");

        javax.swing.GroupLayout pnlMiercolesLayout = new javax.swing.GroupLayout(pnlMiercoles);
        pnlMiercoles.setLayout(pnlMiercolesLayout);
        pnlMiercolesLayout.setHorizontalGroup(
            pnlMiercolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMiercolesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(45, 45, 45)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(cbxDesdeMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(cbxHastaMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMiercolesLayout.setVerticalGroup(
            pnlMiercolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMiercolesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMiercolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(cbxDesdeMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cbxHastaMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel12.setText("        JUEVES:");

        jLabel13.setText("DESDE:");

        jLabel14.setText("HASTA:");

        javax.swing.GroupLayout pnlJuevesLayout = new javax.swing.GroupLayout(pnlJueves);
        pnlJueves.setLayout(pnlJuevesLayout);
        pnlJuevesLayout.setHorizontalGroup(
            pnlJuevesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJuevesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(48, 48, 48)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(cbxDesdeJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(cbxHastaJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlJuevesLayout.setVerticalGroup(
            pnlJuevesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJuevesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlJuevesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(cbxDesdeJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cbxHastaJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel15.setText("      VIERNES:");

        jLabel18.setText("DESDE:");

        jLabel21.setText("HASTA:");

        javax.swing.GroupLayout pnlViernesLayout = new javax.swing.GroupLayout(pnlViernes);
        pnlViernes.setLayout(pnlViernesLayout);
        pnlViernesLayout.setHorizontalGroup(
            pnlViernesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViernesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(48, 48, 48)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(cbxDesdeViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(cbxHastaViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlViernesLayout.setVerticalGroup(
            pnlViernesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViernesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlViernesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(cbxDesdeViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(cbxHastaViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel16.setText("      SABADO:");

        jLabel19.setText("DESDE:");

        jLabel22.setText("HASTA:");

        javax.swing.GroupLayout pnlSabadoLayout = new javax.swing.GroupLayout(pnlSabado);
        pnlSabado.setLayout(pnlSabadoLayout);
        pnlSabadoLayout.setHorizontalGroup(
            pnlSabadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSabadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(48, 48, 48)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(cbxDesdeSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(cbxHastaSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSabadoLayout.setVerticalGroup(
            pnlSabadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSabadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSabadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(cbxDesdeSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cbxHastaSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel17.setText("   DOMINGO:");

        jLabel20.setText("DESDE:");

        jLabel23.setText("HASTA:");

        javax.swing.GroupLayout pnlDomingoLayout = new javax.swing.GroupLayout(pnlDomingo);
        pnlDomingo.setLayout(pnlDomingoLayout);
        pnlDomingoLayout.setHorizontalGroup(
            pnlDomingoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDomingoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(48, 48, 48)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(cbxDesdeDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(cbxHastaDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDomingoLayout.setVerticalGroup(
            pnlDomingoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDomingoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDomingoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20)
                    .addComponent(cbxDesdeDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cbxHastaDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlJueves, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMiercoles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlMartes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlViernes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSabado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDomingo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("DIAS:");

        tgbDomingo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbDomingo.setText("D");
        tgbDomingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbDomingoActionPerformed(evt);
            }
        });

        tgbJueves.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbJueves.setText("J");
        tgbJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbJuevesActionPerformed(evt);
            }
        });

        tgbMiercoles.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbMiercoles.setText("X");
        tgbMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbMiercolesActionPerformed(evt);
            }
        });

        tgbMartes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbMartes.setText("M");
        tgbMartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbMartesActionPerformed(evt);
            }
        });

        tgbLunes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbLunes.setText("L");
        tgbLunes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tgbLunesStateChanged(evt);
            }
        });
        tgbLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbLunesActionPerformed(evt);
            }
        });
        tgbLunes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tgbLunesFocusGained(evt);
            }
        });
        tgbLunes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tgbLunesPropertyChange(evt);
            }
        });

        tgbSabado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbSabado.setText("S");
        tgbSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbSabadoActionPerformed(evt);
            }
        });

        tgbViernes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tgbViernes.setText("V");
        tgbViernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbViernesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDiasLayout = new javax.swing.GroupLayout(pnlDias);
        pnlDias.setLayout(pnlDiasLayout);
        pnlDiasLayout.setHorizontalGroup(
            pnlDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgbLunes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgbMartes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgbMiercoles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgbJueves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgbViernes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgbSabado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgbDomingo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDiasLayout.setVerticalGroup(
            pnlDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tgbJueves, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tgbLunes)
                            .addComponent(tgbMartes)
                            .addComponent(tgbMiercoles)
                            .addComponent(tgbViernes)
                            .addComponent(tgbSabado)
                            .addComponent(tgbDomingo)))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblHorarios);

        jLabel24.setText("BUSCAR POR CEDULA:");

        txtBusacarporCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusacarporCedulaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(txtBusacarporCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtBusacarporCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        bloquearBotonesNuevo();
        desbloquearElementosNuevo();
//        txtCedula.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardarHorario();
        deseleccionar();
        bloquearBotonesInicio();
        pnlMiercoles.setVisible(false);
        pnlLunes.setVisible(false);
        pnlMartes.setVisible(false);
        pnlJueves.setVisible(false);
        pnlViernes.setVisible(false);
        pnlSabado.setVisible(false);
        pnlDomingo.setVisible(false);

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        bloquearElementosInicio();
        deseleccionar();
        pnlMiercoles.setVisible(false);
        pnlLunes.setVisible(false);
        pnlMartes.setVisible(false);
        pnlJueves.setVisible(false);
        pnlViernes.setVisible(false);
        pnlSabado.setVisible(false);
        pnlDomingo.setVisible(false);
//        limpiarTextos();
//        bloquearTextos();
//        bloquearBotonesGuardar();
        bloquearBotonesInicio();
        tblHorarios.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        BorrarHorario();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tgbLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbLunesActionPerformed
        // TODO add your handling code here:
        seleccionLunes();

    }//GEN-LAST:event_tgbLunesActionPerformed

    private void tgbMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbMartesActionPerformed
        // TODO add your handling code here:
        seleccionMartes();
    }//GEN-LAST:event_tgbMartesActionPerformed

    private void tgbMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbMiercolesActionPerformed
        // TODO add your handling code here:
        seleccionMiercoles();
    }//GEN-LAST:event_tgbMiercolesActionPerformed

    private void tgbJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbJuevesActionPerformed
        // TODO add your handling code here:
        seleccionJueves();
    }//GEN-LAST:event_tgbJuevesActionPerformed

    private void tgbViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbViernesActionPerformed
        // TODO add your handling code here:
        seleccionViernes();
    }//GEN-LAST:event_tgbViernesActionPerformed

    private void tgbSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbSabadoActionPerformed
        // TODO add your handling code here:
        seleccionSabado();
    }//GEN-LAST:event_tgbSabadoActionPerformed

    private void tgbDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbDomingoActionPerformed
        // TODO add your handling code here:
        seleccionDomingo();
    }//GEN-LAST:event_tgbDomingoActionPerformed

    private void txtBusacarporCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusacarporCedulaKeyTyped
        // TODO add your handling code here:
        carTablaHorarios(txtBusacarporCedula.getText());
    }//GEN-LAST:event_txtBusacarporCedulaKeyTyped

    private void tgbLunesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tgbLunesStateChanged
        // TODO add your handling code here
    }//GEN-LAST:event_tgbLunesStateChanged

    private void tgbLunesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tgbLunesFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tgbLunesFocusGained

    private void tgbLunesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tgbLunesPropertyChange

    }//GEN-LAST:event_tgbLunesPropertyChange

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
            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Insertar_Horario_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insertar_Horario_Medico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxDesdeDomingo;
    private javax.swing.JComboBox cbxDesdeJueves;
    private javax.swing.JComboBox cbxDesdeLunes;
    private javax.swing.JComboBox cbxDesdeMartes;
    private javax.swing.JComboBox cbxDesdeMiercoles;
    private javax.swing.JComboBox cbxDesdeSabado;
    private javax.swing.JComboBox cbxDesdeViernes;
    private javax.swing.JComboBox cbxHastaDomingo;
    private javax.swing.JComboBox cbxHastaJueves;
    private javax.swing.JComboBox cbxHastaLunes;
    private javax.swing.JComboBox cbxHastaMartes;
    private javax.swing.JComboBox cbxHastaMiercoles;
    private javax.swing.JComboBox cbxHastaSabado;
    private javax.swing.JComboBox cbxHastaViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlDias;
    private javax.swing.JPanel pnlDomingo;
    private javax.swing.JPanel pnlJueves;
    private javax.swing.JPanel pnlLunes;
    private javax.swing.JPanel pnlMartes;
    private javax.swing.JPanel pnlMiercoles;
    private javax.swing.JPanel pnlSabado;
    private javax.swing.JPanel pnlViernes;
    private javax.swing.JTable tblHorarios;
    private javax.swing.JToggleButton tgbDomingo;
    private javax.swing.JToggleButton tgbJueves;
    private javax.swing.JToggleButton tgbLunes;
    private javax.swing.JToggleButton tgbMartes;
    private javax.swing.JToggleButton tgbMiercoles;
    private javax.swing.JToggleButton tgbSabado;
    private javax.swing.JToggleButton tgbViernes;
    private javax.swing.JTextField txtBusacarporCedula;
    // End of variables declaration//GEN-END:variables
}
