package br.com.carrental.model;

import java.util.ArrayList;
import java.util.List;

public enum UserRole {
	SUPERADMIN,ADMIN,DEFAULT;
	
	public List<UserRole> getRoles(){
		List<UserRole> roles = new ArrayList<>();
		switch(this) {
			case SUPERADMIN:
				roles.add(SUPERADMIN);
				roles.add(ADMIN);
				roles.add(DEFAULT);
				break;
			case ADMIN:
				roles.add(ADMIN);
				roles.add(DEFAULT);
				break;
			case DEFAULT:
				roles.add(DEFAULT);
				break;
			
			default:
				break;
		}		
		return roles;
	}
}
