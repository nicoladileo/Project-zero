package io.github.nicoladileo.model;

import java.sql.Date;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Operatore extends User {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Date dataRegistrazione;
	private Boolean notExpired;
	private Centro centro;
	
	public Operatore() {
		super("", "", true, true, true, true, null);
		this.id = 0;
		this.nome = "";
		this.cognome = "";	
		this.username = "";
		this.password = "";
		this.email = "";
		this.dataRegistrazione = null;
		this.notExpired = true;
		this.centro = new Centro();
	}
	
	public Operatore(Integer id, String nome, String cognome, String username, String password, String email, Centro centro,
			Boolean notExpired, Date dataRegistrazione, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, notExpired, true, true, true, authorities);
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.centro = centro;
		this.dataRegistrazione = dataRegistrazione;
		this.notExpired = notExpired;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	public Boolean getNotExpired() {
		return notExpired;
	}
	public void setNotExpired(Boolean notExpired) {
		this.notExpired = notExpired;
	}
}
