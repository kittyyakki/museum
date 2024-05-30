package com.team4.museum.controller;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.IndexAction;
import com.team4.museum.controller.action.LoginAction;
import com.team4.museum.controller.action.LoginFormAction;
import com.team4.museum.controller.action.LogoutAction;

public class ActionFactory {

	private ActionFactory() {
	}

	private static final ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		return switch (command) {

		case "index" -> new IndexAction();
		case "loginForm" -> new LoginFormAction();
		case "login" -> new LoginAction();
		case "logout" -> new LogoutAction();
		default -> null;

		};
	}

}
