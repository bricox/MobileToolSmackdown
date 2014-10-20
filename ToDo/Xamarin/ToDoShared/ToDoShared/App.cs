using System;
using Xamarin.Forms;

namespace ToDoShared
{
	public class App
	{
		public static Page GetMainPage ()
		{	
			var itemListPage = new MyPage ();
			return new NavigationPage (itemListPage);
//			return new ContentPage { 
//				Content = new Label {
//					Text = "Hello, Forms!",
//					VerticalOptions = LayoutOptions.CenterAndExpand,
//					HorizontalOptions = LayoutOptions.CenterAndExpand,
//				},
//			};
		}
	}
}

