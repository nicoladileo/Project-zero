package io.github.nicoladileo.viewmodel;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;

import io.github.nicoladileo.dao.DAOException;

public class MainAppVM {
private String paginaPrincipale = "";
	
	@Wire("#centerPrincipale")
	Include include;
	
	public String getPaginaPrincipale() {
		return paginaPrincipale;
	}

	public void setPaginaPrincipale(String paginaPrincipale) {
		this.paginaPrincipale = paginaPrincipale;
	}

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) throws IOException, DAOException {
		Selectors.wireComponents(view, this, false);
		this.caricaPaginaHome();
	}
	
	@GlobalCommand
	@NotifyChange({"paginaPrincipale"})
	public void caricaPaginaHome() {
		this.paginaPrincipale = "Home";
		this.include.setSrc(null);
		this.include.setSrc("home.zul");
	}
	
	@GlobalCommand
	@NotifyChange({"paginaPrincipale"})
	public void caricaPaginaLingua() {
		this.paginaPrincipale = "Lingua";
		this.include.setSrc(null);
		this.include.setSrc("lingua.zul");
	}
	
	@GlobalCommand
	@NotifyChange({"paginaPrincipale"})
	public void caricaPaginaGrafici() {
		this.paginaPrincipale = "Grafici";
		this.include.setSrc(null);
		this.include.setSrc("grafici.zul");
	}
}
