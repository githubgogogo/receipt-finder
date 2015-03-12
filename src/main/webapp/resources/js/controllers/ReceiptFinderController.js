'use strict';

/**
 * ReceiptFinderController
 * @constructor
 */
var ReceiptFinderController = function($scope, $http) {
	
	$scope.receipts = "";
	
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

    $scope.uploadCsv = function() {
        
        //var csvFile = $("#csvfile")[0].files[0];
        var csvFileInput = document.getElementById('csvfile');
        
        if(csvFileInput.value.length == 0){
            alert("No file selected.");
            return;
        }

        var file = csvFileInput.files[0];
        
        var textType = /text.*/;
        var reader = new FileReader();
        reader.onload = function(e) {
            var csvContent = reader.result;
            console.log(csvContent);
            //processData(csvContent);
            $http.post('receiptfinder/uploadfridgeitems', csvContent).success(function(result) {
                $scope.setError(result);
            }).error(function() {
                $scope.setError('error!');
            });
        };

        reader.readAsText(file);
    };

//    function processData(allText) {
//        var allTextLines = allText.split(/\r\n|\n/);
//        var lines = [];
//        
//        var fridgeItems = [];
//        
//
//        for (var i=0; i<allTextLines.length; i++) {
//            var data = allTextLines[i].split(',');
//                var tarr = [];
//                fridgeItems[i].name = data[0];
//                fridgeItems[i].amount = data[1];
//                fridgeItems[i].unit = data[2];
//                fridgeItems[i].useby = data[3];
////                for (var j=0; j<data.length; j++) {
////                    console.log(data[j]);
////                }
//                console.log(fridgeItems[i]);
//        }
//    }
    
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