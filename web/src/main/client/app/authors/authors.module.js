angular.module('app.authors', ['ngRoute','app.authors.templates'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider.when('/authors/dialog', {
        	templateUrl: 'authors/dialog/dialog.html', 
        	controller: 'AuthorSearchController'
        });
    });


