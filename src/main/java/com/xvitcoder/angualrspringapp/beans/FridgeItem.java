package com.xvitcoder.angualrspringapp.beans;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xvitcoder
 * Date: 12/21/12
 * Time: 12:19 AM
 */
public class FridgeItem {
    private Long id;
    private String name;
    private Integer amount;
    private Unit unit;
    private String useBy;

    public FridgeItem() { }
    
    
	public FridgeItem(String name, Integer amount, Unit unit, String useBy) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
		this.useBy = useBy;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUseBy() {
		return useBy;
	}

	public void setUseBy(String useBy) {
		this.useBy = useBy;
	}

    
}
