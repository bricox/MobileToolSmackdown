using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using Xamarin.Forms;
using ToDoShared.Repository;

namespace ToDoShared
{	
	public partial class MyPage : ContentPage
	{	
		private static ToDoRepository Repository = new ToDoRepository();
		public ObservableCollection<ToDoItem> toDos { get; set; }

		public MyPage ()
		{
			Title = "To Do Items";

			InitializeComponent ();

			var toolBarItem = new ToolbarItem ();
			toolBarItem.Name = "Add";
			toolBarItem.Command = new Command (AddItem);

			ToolbarItems.Add (toolBarItem);

			InitilizeList ();


		}

		protected override void OnAppearing ()
		{
			base.OnAppearing ();
			RefreshList ();
		}

			

		public void InitilizeList(){
			RefreshList ();

			this.myList.ItemTapped += (sender, e) => {
				var toDoItem = e.Item as ToDoItem;
				 
				if (toDoItem == null)
					return;

				Navigation.PushAsync(new ToDoItemDetail(toDoItem));

			};
		}

		public void RefreshList(){
			toDos = new ObservableCollection<ToDoItem> ();

			foreach (var toDo in Repository.Get()) {
				toDos.Add (toDo);
			}

			this.myList.ItemsSource = toDos;

		}

		public void AddItem(){
			var newItem = new ToDoItem ();
			Navigation.PushAsync (new ToDoItemDetail (newItem));
		}
	}
}

