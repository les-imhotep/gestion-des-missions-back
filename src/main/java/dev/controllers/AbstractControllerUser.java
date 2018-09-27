package dev.controllers;

import org.springframework.security.core.context.SecurityContextHolder;

public class AbstractControllerUser {
	public String getUserDetails() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
