package com.xvitcoder.angualrspringapp.beans;

import java.util.List;

public class ReceiptRequest {
	String name;
	List<Ingrediant> inrediants;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingrediant> getInrediants() {
		return inrediants;
	}
	public void setInrediants(List<Ingrediant> inrediants) {
		this.inrediants = inrediants;
	}
	
	
}
