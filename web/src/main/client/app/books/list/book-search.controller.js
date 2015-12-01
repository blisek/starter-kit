angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [];
    //$scope.book = undefined;
    $scope.prefix = '';
    $scope.gridOptions = { data: 'books' };

    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };

    $scope.search = function () {
        bookService.search($scope.prefix).then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function (err) {
            Flash.create('danger', 'Wyjątek:' + JSON.stringify(err), 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Książka została usunięta.', 'custom-class');
        });
    };
    
    $scope.addBookSend = function(book) {
    	bookService.addBook(book).then(function(res) {
    		//console.log(angular.toJson(res));
    		var bookRes = angular.copy(res.data);
    		$scope.books.push(bookRes);
    		Flash.create('success', 'Ksiazka ' + bookRes.title + ' zostala dodana.', 'custom-class');
    	}, function(err) {
    		Flash.create('danger', 'Wyjątek:' + err.data, 'custom-class');
    	});
    };

    $scope.addBook = function () {
        $modal.open({
            templateUrl: 'books/add/add-book-modal.html',
            controller: 'BookModalController',
            size: 'lg',
            resolve: {
            	book : function() {
            		return $scope.book;
            	}
            }
        }).result.then(function(result) {
        	$scope.addBookSend(angular.copy(result));
        });
    };
    
    $scope.editBook = function(arrayId) {
    	$modal.open({
    		templateUrl: 'books/edit/edit-book-modal.html',
    		controller: 'BookEditModalController',
    		size: 'md',
    		resolve: {
    			editedBook : function() {
    				console.log('in edited book getter, arrayId: ' + arrayId);
    				return angular.copy($scope.books[arrayId]);
    			}
    		}
    	}).result.then(function(result) {
    		console.log("Book edited");
    		var edited = angular.copy(result);
    		bookService.updateBook(edited).then(function(res) {
    			$scope.books[arrayId] = edited;
    			Flash.create("success", "Zmodyfikowano ksiazke " + edited.title, "custom-class");
    		}, function(err) {
    			Flash.create("danger", err.data, "custom-class");
    		});
    	});
    };

	$scope.search();
});
