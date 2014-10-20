using System;

namespace ToDoShared
{
	public class ToDoItem
	{
		public string Title {
			get;
			set;
		}

		public int? Id {
			get;
			set;
		}

		public bool Complete {
			get;
			set;
		}

		public ToDoItem ()
		{

		}
	}
}

