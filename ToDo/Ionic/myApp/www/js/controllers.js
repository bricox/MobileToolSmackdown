angular.module('starter.controllers', [])



.controller('FriendsCtrl', function($scope, Friends) {
  $scope.friends = Friends.all();
})

.controller('FriendDetailCtrl', function($scope, $stateParams, Friends) {
  $scope.friend = Friends.get($stateParams.friendId);
})

.controller('AccountCtrl', function($scope) {
})

.controller('ToDoCtrl', function($scope, ToDoService, $ionicModal){
	var self=this;

	$scope.toDoList = ToDoService.getList();
	$scope.selectedItem = {};
	

	// Create and load the Modal
  	$ionicModal.fromTemplateUrl('templates/todo-detail.html', function(modal) {
    	$scope.taskModal = modal;
  	}, {
    	scope: $scope,
    	animation: 'slide-in-up'
  	});	

  	

	$scope.createItem = function(){
		$scope.selectedItem = {title: "", complete: false};
		$scope.taskModal.show();
	}

	$scope.addItem= function(){
		ToDoService.addItem($scope.selectedItem);
		$scope.taskModal.hide();
	}

	$scope.checkItem= function(item){
		item.complete = !item.complete;
		ToDoService.saveList();
	}

	$scope.closeNewTask = function(){
		$scope.selectedItem = {};
		$scope.taskModal.hide();
	}

	$scope.clickComplete = function(item){
		item.complete = !item.complete;
		ToDoService.saveList($scope.toDoList);
	}
});
