using System;
using System.Collections.Generic;
using Xamarin.Forms;

namespace ToDoShared
{	
	public partial class ToDoList : ContentPage
	{	
		private static ToDoShared.Repository.ToDoRepository Repository 
			= new ToDoShared.Repository.ToDoRepository ();

		public ToDoList ()
		{
			InitializeComponent ();
			LoadList ();
		}

		void LoadList(){
			var toDoList = Repository.Get ();

			this.myList.ItemsSource = toDoList;


		}
	}
}

