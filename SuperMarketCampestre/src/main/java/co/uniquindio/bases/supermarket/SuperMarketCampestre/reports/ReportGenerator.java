package co.uniquindio.bases.supermarket.SuperMarketCampestre.reports;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.Conexion;
import net.sf.jasperreports.engine.JasperCompileManager;
public class ReportGenerator extends Conexion{
	public void createReport1() {
		 try {
		      Map parameters = new HashMap();
		      parameters.put("cantidad", "PAISES");
		      parameters.put("nombre", "");
		      JasperReport report = JasperCompileManager.compileReport(
		          "/home/crisisanchezp/Documentos/cantidadDeDomiciliosPorEmpleado.jrxml");
		      JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
		      // Exporta el informe a PDF
		      JasperExportManager.exportReportToPdfFile(print,
		          "Informe1MySQL.pdf");
		      //Para visualizar el pdf directamente desde java
		      JasperViewer.viewReport(print, true);
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	public void createReport2() {
		 try {
		      Map parameters = new HashMap();
		      parameters.put("Cantidad", "");
		      parameters.put("direccion", "");
		      JasperReport report = JasperCompileManager.compileReport(
		          "/home/crisisanchezp/Documentos/cantidadDeDomiciliosPorEmpleado.jrxml");
		      JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
		      // Exporta el informe a PDF
		      JasperExportManager.exportReportToPdfFile(print,
		          "Informe1MySQL.pdf");
		      //Para visualizar el pdf directamente desde java
		      JasperViewer.viewReport(print, true);
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	

}
