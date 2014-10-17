//
//  AddTitleViewController.swift
//  ToDoList
//
//  Created by Alef Bogale on 10/5/14.
//  Copyright (c) 2014 Alef Bogale. All rights reserved.
//

import UIKit
import QuartzCore

class AddTitleViewController: UIViewController {

    @IBOutlet var titleTextView: UITextView!
    @IBOutlet var myLabel: UILabel!
    var todoItem: TodoItem!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        //To make the border look very close to a UITextField
        self.titleTextView.layer.borderColor = UIColor.grayColor().CGColor;
        self.titleTextView.layer.borderWidth = 2.0;
        self.titleTextView.layer.cornerRadius = 5;
        self.titleTextView.clipsToBounds = true;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func saveTitle(sender: UIButton) {
        self.todoItem = TodoItem(self.titleTextView.text, isChecked: false);
        if (!self.todoItem.title.isEmpty) {
            self.todoItem.saveToFile(-1, itemToSave: self.todoItem);
        }
    }

    /*
    // #pragma mark - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue!, sender: AnyObject!) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
