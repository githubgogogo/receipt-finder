package com.xvitcoder.angualrspringapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xvitcoder.angualrspringapp.beans.FridgeItem;
import com.xvitcoder.angualrspringapp.beans.Ingrediant;
import com.xvitcoder.angualrspringapp.beans.Receipt;
import com.xvitcoder.angualrspringapp.beans.Unit;
import com.xvitcoder.angualrspringapp.service.FridgeItemService;

/**
 * User: jeremy.yang
 */
@Controller
@RequestMapping("/receiptfinder")
public class ReceiptFinderController {

    @Autowired
    private FridgeItemService fridgeItemService;
    
    @RequestMapping("/fridgeitemlist.json")
    public @ResponseBody List<FridgeItem> getFridgeItemList() {
        return fridgeItemService.getAllFridgeItems();
    }
    
    @RequestMapping(value = "/findreceipt", method = RequestMethod.POST)
    public @ResponseBody Receipt findreceipt(@RequestBody List<HashMap<String, Object>> request) {
    	
    	List<Receipt> receipts = new ArrayList<Receipt>();
    	
    	for (HashMap<String, Object> receiptMap : request) {
    		Receipt receipt = new Receipt();
    		
    		receipt.setName((String)receiptMap.get("name"));
    		List<HashMap<String, String>> ingredientsMap = (List<HashMap<String, String>>)receiptMap.get("ingredients");
    		
    		List<Ingrediant> ingrediants = new ArrayList<Ingrediant>();
    		for (HashMap<String, String> ingredientMap : ingredientsMap) {
    			Ingrediant ingrediant = new Ingrediant(ingredientMap.get("item"), Integer.valueOf(ingredientMap.get("amount")), Unit.valueOf(ingredientMap.get("unit")));
    			ingrediants.add(ingrediant);
    		}
    		receipt.setIngrediants(ingrediants);
    		receipts.add(receipt);
    	}
    	
    	return fridgeItemService.findReceipt(receipts);
    }
    
    @RequestMapping(value = "/fakeupload", method = RequestMethod.POST)
    public @ResponseBody void fakeupload() {
    	fridgeItemService.fakeUpload();
    }
    
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = "multipart/form-data", produces = "application/json")
	public @ResponseBody String importPartDetails(@RequestParam MultipartFile[] itemFile) {
		
		String fileName = "";
		MultipartFile multipartFile = null;
		
		fileName = itemFile[0].getOriginalFilename();
		multipartFile = itemFile[0];
		System.out.println("fileName : " + fileName);
//		if(ICC_MAD.equals(type)){
//			fileName = excelICCMADFile[0].getOriginalFilename();
//			multipartFile = excelICCMADFile[0];
//		} else if(LOCATION_REFERENCE.equals(type)){
//			fileName = locationReferenceFile[0].getOriginalFilename();
//			multipartFile = locationReferenceFile[0];
//			
//		} else {
//			fileName = excelFile[0].getOriginalFilename();
//			multipartFile = excelFile[0];
//		}
//		if (multipartFile != null && fileName.length() > 0) {
//			try {
//				results = adminService.importExcel(multipartFile.getInputStream(), fileName, type);
//			} catch (IOException e) {
//				logger.error(e.getMessage(), e);
//				ProcessResult processResult = new ProcessResult();
//				processResult.setCode(AppConstants.UPLOADERROR);
//				processResult.setMessage("Could not open the file specified.");
//				results.add(processResult);
//			}
//		}
//		else {
//			ProcessResult processResult = new ProcessResult();
//			processResult.setCode(AppConstants.UPLOADERROR);
//			processResult.setMessage("Please upload an excel file");
//			results.add(processResult);
//		}
//		
//		return new DataList<ProcessResult>(results);
		return "success";
		
	}

    @RequestMapping("/layout")
    public String getReceiptFinderPage() {
        return "receiptfinder/layout";
    }
}
