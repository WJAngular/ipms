package com.wechat.bean;

import java.util.ArrayList;

public class MenuItem extends MenuBase {

	private ArrayList<MenuSubItem> sub_button;

	public ArrayList<MenuSubItem> getSub_button() {
		return sub_button;
	}

	public void setSub_button(ArrayList<MenuSubItem> sub_button) {
		this.sub_button = sub_button;
	}

}
