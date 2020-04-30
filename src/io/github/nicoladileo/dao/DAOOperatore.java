package io.github.nicoladileo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.zaxxer.hikari.HikariDataSource;

import io.github.nicoladileo.model.Centro;
import io.github.nicoladileo.model.Operatore;
import io.github.nicoladileo.util.Utility;


public class DAOOperatore {
	private static DAOOperatore singleton = new DAOOperatore();
	private static final String SelectOperatore = "SELECT TAB_OPERATORI.ID AS ID, TAB_OPERATORI.NOME AS NOME,COGNOME,USERNAME,PASSWORD,EMAIL,"
							  + "DATA_REGISTRAZIONE,DATA_SCADENZA,TAB_CENTRI.ID AS ID_CENTRO,TAB_CENTRI.DESCRIZIONE AS CENTRO FROM TAB_OPERATORI "
							  + "INNER JOIN TAB_CENTRI ON TAB_CENTRI.ID = TAB_OPERATORI.ID_CENTRO WHERE 1=1 ";
	private static final String SelectRuoli = "SELECT RUOLO FROM tab_ruoli INNER JOIN tab_abilitazioni ON "
												   + "tab_ruoli.ID = tab_abilitazioni.ID_RUOLO WHERE 1=1 ";
	private static final String FilterUsername = " USERNAME = ? ";
	private static final String FilterIdOperatore = " ID_OPERATORE = ? ";
	private static final String AND = " AND ";
	
	private DAOOperatore() {}
	
	public static DAOOperatore getInstance() {
		return singleton;
	}
	
	public Operatore getOperatore(String username) throws DAOException {
		String query = SelectOperatore;
		Operatore operatore = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		HikariDataSource dataSource; 
		Connection conn = null;
		Integer counter = 0;
		
		if (!StringUtils.isBlank(username))
			query += AND + FilterUsername;
		
		try {
			dataSource = DataSourceFactory.getInstance().getHikariDataSource(Utility.caricaDatasourceProperties());
			conn = dataSource.getConnection();
			
			stmt = conn.prepareStatement(query);
			
			if (!StringUtils.isBlank(username))
				stmt.setString(++counter, username);
				
			res = stmt.executeQuery();
			
			if (res.next()) {
				Integer idOperatore = res.getInt("ID");
				String nome = res.getString("NOME");
				String cognome = res.getString("COGNOME");
				String usernameOperatore = res.getString("USERNAME");
				String password = res.getString("PASSWORD");
				String email = res.getString("EMAIL");
				Date dataRegistrazione = res.getDate("DATA_REGISTRAZIONE");
				Date dataScadenza = res.getDate("DATA_SCADENZA");
				Centro centro = new Centro();
				centro.setId(res.getInt("ID_CENTRO"));
				centro.setDescrizione(res.getString("CENTRO"));
				Boolean notExpired = notExpired(dataScadenza.getTime(), Calendar.getInstance().getTimeInMillis());
				List<? extends GrantedAuthority> ruoli = this.getRuoliOperatore(idOperatore, conn, dataSource);
				operatore = new Operatore(idOperatore, nome, cognome, usernameOperatore, password, email, centro, notExpired, dataRegistrazione,ruoli);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} finally {
			try {
				stmt.close();
				res.close();
				conn.close();
			}
			catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return operatore;
	}
	
	private Boolean notExpired(Long dataScadenza, Long dataAttuale) {
		return dataScadenza > dataAttuale;
	}
	
	private List<? extends GrantedAuthority> getRuoliOperatore(Integer idOperatore, Connection conn, HikariDataSource dataSource) throws DAOException {
		String query = SelectRuoli;
		List<SimpleGrantedAuthority> ruoli = new ArrayList<SimpleGrantedAuthority>();
		PreparedStatement stmt = null;
		ResultSet res = null;
		Integer counter = 0;
		
		if (idOperatore != null)
			query += AND + FilterIdOperatore;
		
		try {
			stmt = conn.prepareStatement(query);
			
			if (idOperatore != null)
				stmt.setInt(++counter, idOperatore);
				
			res = stmt.executeQuery();
			
			while (res.next())
				ruoli.add(new SimpleGrantedAuthority(res.getString("RUOLO")));			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				stmt.close();
				res.close();
			}
			catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return ruoli;
	}
}
