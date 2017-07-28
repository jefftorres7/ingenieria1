/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JRViewer;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author
 */
public class Clases_Reportes {

    String rutaReport="C:/reportes/";
    public void imprimirReporteConParametro(String NombreReporte, String NombreParametro, JDesktopPane Escritorio, Object Poner) {
        String Nomreporte=rutaReport+NombreReporte+".jrxml";
        String paramet=String.valueOf(Poner);
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            Map parametro = new HashMap();
            parametro.put(NombreParametro, paramet);
            JasperReport reporte = JasperCompileManager.compileReport(Nomreporte);
            JasperPrint print = JasperFillManager.fillReport(reporte, parametro, cc.conectar());
            // JasperViewer.viewReport(print, false);
            JInternalFrame interno = new JInternalFrame("Reporte");
            interno.getContentPane().add(new JRViewer(print));
            Escritorio.add(interno);
            interno.pack();
            interno.setClosable(true);
            interno.setMaximizable(true);
            interno.setResizable(true);
            interno.setVisible(true);
            interno.setResizable(true);
            interno.setSize(Escritorio.getSize());
        } catch (Exception ex) {
            //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void imprimirReporteConParametro(String NombreReporte, String NombreParametro1, String NombreParametro2, JDesktopPane Escritorio, Object Poner1, Object Poner2) {
       String Nomreporte=rutaReport+NombreReporte+".jrxml";
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            Map parametro = new HashMap();
            parametro.put(NombreParametro1, Poner1);
            parametro.put(NombreParametro2, Poner2);
            JasperReport reporte = JasperCompileManager.compileReport(Nomreporte);
            JasperPrint print = JasperFillManager.fillReport(reporte, parametro, cc.conectar());
            // JasperViewer.viewReport(print, false);
            JInternalFrame interno = new JInternalFrame("Reporte");
            interno.getContentPane().add(new JRViewer(print));
            Escritorio.add(interno);
            interno.pack();
            interno.setClosable(true);
            interno.setMaximizable(true);
            interno.setResizable(true);
            interno.setVisible(true);
            interno.setResizable(true);
            interno.setSize(Escritorio.getSize());
        } catch (Exception ex) {
            //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void imprimirReporte(String NombreReporte, JDesktopPane Escritorio) {
         String Nomreporte=rutaReport+NombreReporte+".jrxml";
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            JasperReport reporte = JasperCompileManager.compileReport(Nomreporte);
            JasperPrint print = JasperFillManager.fillReport(reporte, null, cc.conectar());
            // JasperViewer.viewReport(print, false);
            JInternalFrame interno = new JInternalFrame("Reporte");
            interno.getContentPane().add(new JRViewer(print));
            Escritorio.add(interno);
            interno.pack();
            interno.setClosable(true);
            interno.setMaximizable(true);
            interno.setResizable(true);
            interno.setVisible(true);
            interno.setResizable(true);
            interno.setSize(Escritorio.getSize());
        } catch (Exception ex) {
            //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void imprimirReporteNoVale(String NombreReporte) {
        try {
            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            JasperReport reporte = JasperCompileManager.compileReport("C:/reportes/reporeAutosPorPlaca.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, cc.conectar());
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
