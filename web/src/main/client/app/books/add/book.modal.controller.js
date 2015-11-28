angular.module('app.books').controller('BookModalController', function ($scope, $uibModalInstance) {
    'use strict';

    $scope.title = '';
    $scope.titleError = '';
    $scope.currentAuthor = '';
    $scope.currentAuthorError = '';
    $scope.book = {title: '', authors : []};
    
    $scope.addAuthor = function(name) {
    	if($scope.currentAuthor == "") {
    		$scope.currentAuthorError = 'has-error';
    		return;
    	}
    	var strArray = $scope.currentAuthor.split(" ");
    	if(strArray.length === 1) {
    		$scope.book.authors.push({firstName : '', lastName : strArray[0]});
    	} else {
    		$scope.book.authors.push({firstName : strArray[0], lastName : strArray[1]});
    	}
    	
    	$scope.currentAuthor = '';
    	$scope.currentAuthorError = '';
    };
    
    $scope.addBook = function() {
    	if($scope.book.title == "") {
    		$scope.titleError = 'has-error';
    		return;
    	}
    	
    	$uibModalInstance.close($scope.book);
    };
    
});
