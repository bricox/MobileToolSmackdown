using System;
using System.Collections.Generic;
using Xamarin.Forms;
using ToDoShared.Repository;

namespace ToDoShared
{	
	public partial class ToDoItemDetail : ContentPage
	{	

		private ToDoRepository repository;
		private ToDoItem toDoItem;

		public ToDoItemDetail (ToDoItem toDoItem)
		{
			this.toDoItem = toDoItem;
			this.BindingContext = this.toDoItem;
			this.repository = new ToDoRepository ();

			InitializeComponent ();

			var toolBarItem = new ToolbarItem ();
			toolBarItem.Name = "Save";
			toolBarItem.Command = new Command (SaveItem);

			ToolbarItems.Add (toolBarItem);

		}

		private void SaveItem(){
			repository.Save (this.toDoItem);
			MessagingCenter.Send (this.toDoItem, "Refresh");
			Navigation.PopAsync ();
		}




	}
}

