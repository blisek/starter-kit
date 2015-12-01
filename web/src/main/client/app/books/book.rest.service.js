angular.module('app.books').factory('bookRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return $http.get(currentContextPath.get() + 'services/books/books-by-title', {params: {titlePrefix: titlePrefix}});
        },
        deleteBook: function (bookId) {
        	console.log("[Delete] BookId: " + bookId);
            return $http.delete(currentContextPath.get() + 'services/books/book', {params: {bookId: bookId}});
        },
        addBook: function(book) {
        	return $http.post(currentContextPath.get() + 'services/books/book', book);
        },
        updateBook: function(book) {
        	return $http.put(currentContextPath.get() + 'services/books/book', book);
        }
    };
});
