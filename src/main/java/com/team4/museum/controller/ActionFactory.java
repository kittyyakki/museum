package com.team4.museum.controller;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.IdcheckFormAction;
import com.team4.museum.controller.action.ArtworkListAction;
import com.team4.museum.controller.action.IndexAction;
import com.team4.museum.controller.action.LoginAction;
import com.team4.museum.controller.action.LoginFormAction;
import com.team4.museum.controller.action.LogoutAction;
import com.team4.museum.controller.action.JoinAction;
import com.team4.museum.controller.action.JoinFormAction;
import com.team4.museum.controller.action.qna.QnaListAction;

public class ActionFactory {

	private ActionFactory() {
	}

	private static final ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		return switch (command != null ? command : "") {

		case "", "index" -> new IndexAction();
		case "loginForm" -> new LoginFormAction();
		case "login" -> new LoginAction();
		case "logout" -> new LogoutAction();
		case "join" -> new JoinAction();
		case "joinForm" -> new JoinFormAction();
		case "idcheckForm" -> new IdcheckFormAction();
		case "artwork" -> new ArtworkListAction();
		case "qnaList" -> new QnaListAction();
		default -> null;

		};
	}

}
