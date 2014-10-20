using System;
using System.Linq;
using System.Collections;
using System.Collections.Generic;
using ToDoShared.Repository;
using System.IO;



namespace ToDoShared.Repository
{
	public class ToDoRepository
	{
		private static List<ToDoItem> toDoItems = new List<ToDoItem>();
		public ToDoRepository ()
		{
			if (toDoItems.Count == 0){
			toDoItems.Add (new ToDoItem{ Id=0, Title = "Buy Milk" });
			toDoItems.Add (new ToDoItem{ Id=1, Title = "Eat More Chicken" });
			}

		}

		public List<ToDoItem> Get(){
			return toDoItems;
		}

		public ToDoItem Get(int id){
			return toDoItems.FirstOrDefault(p => p.Id == id);
		}

		public void Save(ToDoItem toDoItem){
			if (!toDoItem.Id.HasValue) {
				toDoItem.Id = getNextId();
				toDoItems.Add (toDoItem);
			} else {
				var currentItem = toDoItems.FirstOrDefault (p => p.Id == toDoItem.Id);
				currentItem.Title = toDoItem.Title;
				currentItem.Complete = toDoItem.Complete;
			}


		}

		private int getNextId(){
			return toDoItems.Count;
		}




	}
}

