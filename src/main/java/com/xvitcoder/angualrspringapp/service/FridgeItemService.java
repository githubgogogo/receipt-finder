package com.xvitcoder.angualrspringapp.service;

import java.util.List;

import com.xvitcoder.angualrspringapp.beans.FridgeItem;
import com.xvitcoder.angualrspringapp.beans.Receipt;

/**
 * User: jeremy.yang
 */
public interface FridgeItemService {
    public List<FridgeItem> getAllFridgeItems();

    public FridgeItem getFridgeItemByName(String name);

    public void addFridgeItem(FridgeItem fridgeItem);

    public void deleteAll();
    
    public void fakeUpload();
    
    public Receipt findReceipt(List<Receipt> receipts);

}
