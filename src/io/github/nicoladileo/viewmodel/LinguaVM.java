package io.github.nicoladileo.viewmodel;

import java.io.IOException;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class LinguaVM {
	private Log logger = LogFactory.getLog(this.getClass());
		
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	

	@Command
	@NotifyChange({"*"})
	public void cambioLingua(@BindingParam("lang") String lang) throws IOException {
		String localeValue = lang;
		Session session = Sessions.getCurrent();
        Locale prefer_locale = localeValue.length() > 2 ?  new Locale(localeValue.substring(0,2),localeValue.substring(3)) : new Locale(localeValue);
        session.setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, prefer_locale);
        Executions.sendRedirect("");
	}
}
