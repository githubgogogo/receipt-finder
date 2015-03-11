package com.xvitcoder.angualrspringapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xvitcoder.angualrspringapp.beans.FridgeItem;
import com.xvitcoder.angualrspringapp.beans.Ingrediant;
import com.xvitcoder.angualrspringapp.beans.Receipt;
import com.xvitcoder.angualrspringapp.beans.Unit;


@Service("FridgeItemService")
public class FridgeItemServiceImpl implements FridgeItemService {
    
    private static List<FridgeItem> fiList = new ArrayList<FridgeItem>();
    private static Long id = 0L;
    
    
    @Override
	public List<FridgeItem> getAllFridgeItems() {
		return fiList;
	}

	@Override
	public FridgeItem getFridgeItemByName(String name) {
		for (FridgeItem fi : fiList) {
			if (fi.getName().equalsIgnoreCase(name)) {
				return fi;
			}
		}
		return null;
	}

	@Override
	public void addFridgeItem(FridgeItem fridgeItem) {
		fridgeItem.setId(++id);
		fiList.add(fridgeItem);
	}

    @Override
    public void deleteAll() {
        fiList.clear();
        id = 0L;
    }
    
    @Override
    public void fakeUpload() {
    	deleteAll();
    	
    	FridgeItem fridgeItem = new FridgeItem("bread", 10, Unit.slices, "25/12/2014");
    	addFridgeItem(fridgeItem);
    	fridgeItem = new FridgeItem("cheese", 10, Unit.slices, "25/12/2014");
    	addFridgeItem(fridgeItem);
    	fridgeItem = new FridgeItem("butter", 250, Unit.grams, "25/12/2014");
    	addFridgeItem(fridgeItem);
    	fridgeItem = new FridgeItem("peanut butter", 250, Unit.grams, "02/12/2014");
    	addFridgeItem(fridgeItem);
    	fridgeItem = new FridgeItem("mixed salad", 250, Unit.grams, "26/12/2013");
    	addFridgeItem(fridgeItem);
    }

	@Override
	public Receipt findReceipt(List<Receipt> receipts) {
		
		Receipt result = new Receipt();
		result.setName("Order Takeout");
		
		List<FridgeItem> fridgeItems = getAllFridgeItems();
		Map<String, FridgeItem> fridgeItemMap = new HashMap<String, FridgeItem>();
		for (FridgeItem fridgeItem : fridgeItems) {
			fridgeItemMap.put(fridgeItem.getName(), fridgeItem);
		}
		
		Date earliestUseByDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (Receipt receipt : receipts) {
			List<Ingrediant> ingrediants = receipt.getIngrediants();
			boolean hasEnoughItems = true;
			
			Date earliestUseByDateOfCurrentReceipt = null;
			for (Ingrediant ingrediant : ingrediants) {
				if (!fridgeItemMap.containsKey(ingrediant.getName())) {
					hasEnoughItems = false;
					break;
				} else {
					FridgeItem fridgeItem = fridgeItemMap.get(ingrediant.getName());
					
					if (Integer.valueOf(fridgeItem.getAmount()) < ingrediant.getAmount()) {
						hasEnoughItems = false;
						break;
					}
					
					if (earliestUseByDateOfCurrentReceipt == null) {
						try {
							earliestUseByDateOfCurrentReceipt = sdf.parse(fridgeItem.getUseBy());
						} catch (ParseException e) {
						}
					} else {
						Date currentUseByDate = null;
						try {
							currentUseByDate = sdf.parse(fridgeItem.getUseBy());
							if (currentUseByDate.before(earliestUseByDateOfCurrentReceipt))
								earliestUseByDateOfCurrentReceipt = currentUseByDate;
						} catch (ParseException e) {
						}
					}
				}
			}
			
			if (hasEnoughItems) {
				if (earliestUseByDate == null || earliestUseByDateOfCurrentReceipt.before(earliestUseByDate)) {
					earliestUseByDate = earliestUseByDateOfCurrentReceipt;
					result = receipt;
				}
			}
			
		}
		
		return result;
	}
	
	
    
    
}
