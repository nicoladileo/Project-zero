package io.github.nicoladileo.viewmodel;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

import io.github.nicoladileo.security.SecurityUtil;

public class HomeVM {
	private Log logger = LogFactory.getLog(this.getClass());
	private String userInfo;
	private Collection<GrantedAuthority> ruoli;
	
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		this.userInfo = SecurityUtil.getUserInfo();
		this.ruoli = SecurityUtil.getUser().getAuthorities();
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Collection<GrantedAuthority> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Collection<GrantedAuthority> ruoli) {
		this.ruoli = ruoli;
	}
}
