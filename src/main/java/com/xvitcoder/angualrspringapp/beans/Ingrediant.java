package com.xvitcoder.angualrspringapp.beans;


/**
 * User: jeremy.yang
 */
public class Ingrediant {
    private String name;
    private Integer amount;
    private Unit unit;

    public Ingrediant() { }
    
    
	public Ingrediant(String name, Integer amount, Unit unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
