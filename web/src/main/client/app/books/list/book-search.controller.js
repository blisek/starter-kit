angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [{'id': 1, 'title': 'AAAA', 'author': 'JDJ'}];
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
            Flash.create('danger', 'Wyjątek:' + err.toString(), 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Książka została usunięta.', 'custom-class');
        });
    };

    $scope.addBook = function () {
        $modal.open({
            templateUrl: 'books/add/add-book-modal.html',
            controller: 'BookModalController',
            size: 'lg',
            resolve: {
            	book: function() {
            		return $scope.book;
            	}
            }
        }).result.then(function (book) {
        	alert(book);        
        }, function() {
        
        });
    };

});
