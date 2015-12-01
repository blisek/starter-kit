angular.module('app.authors').controller('AuthorSearchController', function($scope, $http, currentContextPath) {

	$scope.authors = [];
	
	$scope.loadAuthors = function() {
		$http.get(currentContextPath.get() + 'services/authors/author').then(function(res) {
			$scope.authors = angular.copy(res.data);
			console.log(JSON.stringify($scope.authors));
		}, function(err) {
			console.log(err.data);
		});
	};
	
	
	$scope.loadAuthors();
});