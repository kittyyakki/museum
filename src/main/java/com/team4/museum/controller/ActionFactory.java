package com.team4.museum.controller;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.ArtworkListAction;
import com.team4.museum.controller.action.IndexAction;

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
		case "artwork" -> new ArtworkListAction();
		default -> null;
		};
	}

}
