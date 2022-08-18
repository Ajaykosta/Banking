package com.banking.reception;

import com.banking.utility.MenuController;

public class Reception {
	public static void main(String[] args) {
		MenuController menu = new MenuController();
		while (true) {
			menu.mainMenu();
		}
	}
}