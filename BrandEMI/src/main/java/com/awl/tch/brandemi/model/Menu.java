package com.awl.tch.brandemi.model;

import java.util.HashMap;
import java.util.List;

public class Menu {
	private List<HashMap<String,Object>> screen1;
	private List<HashMap<String,Object>> screen2;
	private List<HashMap<String,Object>> screen3;
	private List<HashMap<String,Object>> programs;
	public List<HashMap<String, Object>> getScreen1() {
		return screen1;
	}
	public void setScreen1(List<HashMap<String, Object>> screen1) {
		this.screen1 = screen1;
	}
	public List<HashMap<String, Object>> getScreen2() {
		return screen2;
	}
	public void setScreen2(List<HashMap<String, Object>> screen2) {
		this.screen2 = screen2;
	}
	public List<HashMap<String, Object>> getScreen3() {
		return screen3;
	}
	public void setScreen3(List<HashMap<String, Object>> screen3) {
		this.screen3 = screen3;
	}
	public List<HashMap<String, Object>> getPrograms() {
		return programs;
	}
	public void setPrograms(List<HashMap<String, Object>> programs) {
		this.programs = programs;
	}
	
	
}
