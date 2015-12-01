angular.module('app.books').controller('BookEditModalController', function ($scope, $modalInstance, editedBook) {
    'use strict';
    
    $scope.edBook = function() {};
    $scope.editedBook = editedBook;
    
    console.log(JSON.stringify(editedBook));
});
