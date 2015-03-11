package com.xvitcoder.angualrspringapp.beans;

import java.util.List;

public class Receipt {
	String name;
	List<Ingrediant> ingrediants;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingrediant> getIngrediants() {
		return ingrediants;
	}
	public void setIngrediants(List<Ingrediant> ingrediants) {
		this.ingrediants = ingrediants;
	}
	
	
}
