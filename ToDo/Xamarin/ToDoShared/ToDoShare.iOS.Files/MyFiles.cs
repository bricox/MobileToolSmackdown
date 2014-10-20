using System;
using System.IO;

namespace ToDoShare.iOS.Files
{
	public class MyFiles
	{
		public MyFiles ()
		{

		}

		public void WriteFile(string fileName, string data){
			//this only works in iOS 7 or below!
			var filePath = Environment.GetFolderPath (Environment.SpecialFolder.MyDocuments);
			var file = Path.Combine (filePath, fileName);
			File.WriteAllText (file, data);
		}

		public string ReadFile(string fileName){
			//this only works in iOS 7 or below!
			var filePath = Environment.GetFolderPath (Environment.SpecialFolder.MyDocuments);
			var file = Path.Combine (filePath, fileName);
			return File.ReadAllText (file);
		}


	}
}

