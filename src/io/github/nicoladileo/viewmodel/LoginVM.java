package io.github.nicoladileo.viewmodel;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zhtml.Form;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;

import io.github.nicoladileo.exception.APPException;
import io.github.nicoladileo.util.Utility;

public class LoginVM {
	private Log logger = LogFactory.getLog(this.getClass());
	private String username;
	private String password;
	private Page page;
	private String applicationName;

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) throws IOException {
		Map<String,String[]> params =  Executions.getCurrent().getParameterMap();
		if (params.containsKey("login_error") && params.get("login_error").length > 0) {
			String message = params.get("login_error")[0].toString();
			Clients.showNotification(message,"error",null, "middle_center",8000,true);
		}
		Selectors.wireComponents(view, this, false);
		this.page = view.getPage();
		this.applicationName = Utility.caricaApplicationProperties().getProperty("application.name");
	}
	
	@Command
	public void doLogin() throws APPException {
		logger.info("Inizio doLogin");
		if (StringUtils.isBlank(this.username) || StringUtils.isBlank(this.password)) {
			Clients.showNotification("Specificare username e password","error",null, "middle_center",5000);
			return;
		}
		Form form = new Form();
		try {
			form.setDynamicProperty("action", "j_spring_security_check");
		} catch (WrongValueException e1) {
			logger.info("Errore valore scorretto per il parametro uriAutenticazione");
			throw new APPException("Errore valore scorretto per il parametro uriAutenticazione");
		}
		form.setDynamicProperty("method", "post");
		form.setPage(page);

		Input usr = new Input();
		Input pwd = new Input();

		usr.setParent(form);
		usr.setDynamicProperty("type", "hidden");
		usr.setDynamicProperty("name", "j_username");
		usr.setValue(this.username);

		pwd.setParent(form);
		pwd.setDynamicProperty("type", "hidden");
		pwd.setDynamicProperty("name", "j_password");
		pwd.setValue(this.password);
		
		logger.info("Fine doLogin");
		Clients.submitForm(form);
	}
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
