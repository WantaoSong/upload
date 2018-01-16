package com.upload.v3.common;

public enum ViewType {
	Jsp, Thymeleaf;

	public String getView(String view) {
		if (this.name().equalsIgnoreCase("jsp")) {
			return "jsp/" + view;
		}
		return "html/" + view;
	}
}
