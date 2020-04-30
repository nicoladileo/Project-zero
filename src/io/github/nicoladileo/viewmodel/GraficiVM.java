package io.github.nicoladileo.viewmodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class GraficiVM {
	private Log logger = LogFactory.getLog(this.getClass());
	
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) throws IOException {
		Selectors.wireComponents(view, this, false);
		this.caricaGrafici();
	}
	
	private void caricaGrafici() throws IOException {
		InputStream input = getClass().getClassLoader().getResourceAsStream("grafico-torta.txt");
		String code = IOUtils.toString(input, "utf-8"); 
		String graf1 = code.replace("%%DATA%%", "10,3");
		graf1 = graf1.replace("%%TOTALE%%", "Totale 13");
		graf1 = graf1.replace("%%ID%%", "grafico1");
		Clients.evalJavaScript(graf1);
		
		String graf2 = code.replace("%%DATA%%", "50,50");
		graf2 = graf2.replace("%%TOTALE%%", "Totale 100");
		graf2 = graf2.replace("%%ID%%", "grafico2");
		Clients.evalJavaScript(graf2);
		
		String graf3 = code.replace("%%DATA%%", "21,192");
		graf3 = graf3.replace("%%TOTALE%%", "Totale 213");
		graf3 = graf3.replace("%%ID%%", "grafico3");
		Clients.evalJavaScript(graf3);
	}
	
	@Command
	public void stampa() throws JRException {
		InputStream is;
		JasperPrint jasperPrint = null;
		is = this.getClass().getResourceAsStream("/report.jasper");
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("nome","Paolo");
		parameters.put("cognome", "Rossi");
		jasperPrint = JasperFillManager.fillReport(is, parameters, new JREmptyDataSource());
		byte[] rawData = JasperExportManager.exportReportToPdf(jasperPrint);
		Filedownload.save(rawData, "pdf", "report");
	}
}
