'use strict';

/**
 * ReceiptFinderController
 * @constructor
 */
var ReceiptFinderController = function($scope, $http) {
	
	$scope.receipts = {};
	
	$scope.fetchFridgeItemList = function() {
        $http.get('receiptfinder/fridgeitemlist.json').success(function(fridgeItemList){
            $scope.items = fridgeItemList;
        });
    };
    
    
    $scope.findReceipt = function() {
    	
    	var textReceipts = $scope.receipts;
    	var obj = JSON.parse(textReceipts);
    	
    	$http.post('receiptfinder/findreceipt', obj).success(function(result) {
    	            $scope.result = result;
    	        }).error(function() {
    	            $scope.setError('error!');
    	        });
    	
    };
    
    $scope.uploadFile = function() {
    	
    	$http.post('receiptfinder/fakeupload').success(function(){
    		$scope.fetchFridgeItemList();
        });
    	
    	// TODO
//		var excelFile = $("#itemFile").val();
//		var excelFileComponent = 'itemFile';
//		//var spinner = $("#spinnerUpload");
//		if (excelFile == '') {
//			//$scope.setError("Please select a file to upload.");
//			return false;
//		}
//    	
//      $.ajaxFileUpload({  
//      url : 'receiptfinder/fileUpload',
//      secureuri : false,  
//      fileElementId : excelFileComponent,
//      dataType : 'json',  
//      //data : {fileType:type},
//      success : function(data, status) { 
//			//$("#loading").hide();
//      	//spinner.hide();
//      	//$scope.setSuccess('Uploading file is finished successfully.');
//      },  
//      error : function(data, status, e) {  
//			//$("#loading").hide();
//			//spinner.hide();
//      }  
//  });
    	
    	
    	
//    	$http.post('receiptfinder/fileUpload', $scope.itemFile,
//    			{
//    				headers:{'Content-Type' : 'multipart/form-data'}
//    			})
//    		.success(function(d) {
//    			console.log(d);
//    		});
    	
    	
   
	};
	
	$scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };
    
    $scope.fetchFridgeItemList();
    
};