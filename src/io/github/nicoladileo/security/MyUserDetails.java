package io.github.nicoladileo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.nicoladileo.dao.DAOOperatore;
import io.github.nicoladileo.model.Operatore;

@Service
public class MyUserDetails implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Operatore operatore = null;
		if (username == null)
			throw new UsernameNotFoundException("cannot found user: "+username);
		
		try {
			operatore = DAOOperatore.getInstance().getOperatore(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("cannot found user: "+username);
		}
		if (operatore == null)
			throw new UsernameNotFoundException("cannot found user: "+username);
		return operatore;
	}
}
