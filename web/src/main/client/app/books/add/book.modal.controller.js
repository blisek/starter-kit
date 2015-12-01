angular.module('app.books').controller('BookModalController', function ($scope, $modalInstance) {
    'use strict';

    $scope.title = '';
    $scope.titleError = '';
    $scope.currentAuthor = '';
    $scope.currentAuthorError = '';
    $scope.book = {title: '', authors : []};
    
    $scope.addAuthor = function(name) {
    	var strArray;

    	if($scope.currentAuthor == "") {
    		$scope.currentAuthorError = 'has-error';
    		return;
    	}
    	strArray = $scope.currentAuthor.split(" ");
    	if(strArray.length === 1) {
    		$scope.book.authors.push({firstName : '', lastName : strArray[0]});
    	} else {
    		$scope.book.authors.push({firstName : strArray[0], lastName : strArray[1]});
    	}
    	
    	$scope.currentAuthor = '';
    	$scope.currentAuthorError = '';
    };
    
    $scope.formValid = function() {
    	return !!($scope.book.title !== '' && $scope.book.authors.length > 0);
    };
    
});
