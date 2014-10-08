angular.module('starter.services', [])

/**
 * A simple example service that returns some data.
 */
.factory('Friends', function() {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var friends = [
    { id: 0, name: 'Scruff McGruff' },
    { id: 1, name: 'G.I. Joe' },
    { id: 2, name: 'Miss Frizzle' },
    { id: 3, name: 'Ash Ketchum' }
  ];

  return {
    all: function() {
      return friends;
    },
    get: function(friendId) {
      // Simple index lookup
      return friends[friendId];
    }
  }
})
.factory('ToDoService', function(){
    var self = this;

    self.todoList = [];
    
    var list = window.localStorage['todoList'];

    if (list){
      self.todoList = angular.fromJson(list);
    }

    return {
      addItem: addItem,
      getList: getList,
      saveList: saveList
    };

    function addItem(todo){
      self.todoList.push(todo);
      saveList();
    }

    function getList(){
      return self.todoList;
    }

    function saveList(){
      window.localStorage['todoList'] = angular.toJson(self.todoList);
    }


})
;
