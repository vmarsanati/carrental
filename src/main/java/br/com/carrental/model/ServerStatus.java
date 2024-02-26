package br.com.carrental.model;

import org.springframework.stereotype.Component;

@Component
public class ServerStatus {

	private static boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		ServerStatus.success = success;
	}	
	
}
