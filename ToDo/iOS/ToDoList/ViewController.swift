//
//  ViewController.swift
//  ToDoList
//
//  Created by Alef Bogale on 10/4/14.
//  Copyright (c) 2014 Alef Bogale. All rights reserved.
//

import UIKit
let CHECKED_IMAGE = "checked.png"
let UNCHECKED_IMAGE = "unchecked.png"

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet var myTableView: UITableView!
    var todoItem = TodoItem.alloc();
    var items: NSMutableArray!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.items = self.todoItem.loadFromFile();
        
        let count = self.items == nil ? 0 : self.items!.count;
        return count;
    }
    
    // Row display. Implementers should *always* try to reuse cells by setting each cell's reuseIdentifier and querying for available reusable cells with dequeueReusableCellWithIdentifier:
    // Cell gets various attributes set automatically based on table (separators) and data source (accessory views, editing controls)
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {

        // get the table cell
        var cell: UITableViewCell = self.myTableView.dequeueReusableCellWithIdentifier("TodoCell") as UITableViewCell
        
        // get the array object for the cell
        var item: NSArray? = self.items![indexPath.row] as? NSArray;
        
        if (item == nil) {return cell};
        
        // convert the array to TodoItem object
        let selectedItem = TodoItem("\(item![0])", isChecked: "\(item![1])" == "NO" ? false: true);
        
        // create custom button for the checkbox
        let checkBox: UIButton = UIButton.buttonWithType(UIButtonType.Custom) as UIButton;
        checkBox.tag = indexPath.row;
        checkBox.frame = CGRectMake(275, 7.5, 32, 32);
        
        // add checkbox images based on the checked value
        if (selectedItem.isChecked) {
            setButtonImage(checkBox, imageName: CHECKED_IMAGE)
        }
        else {
            setButtonImage(checkBox, imageName: UNCHECKED_IMAGE)
        }
        
        // add click action for checkbox
        checkBox.addTarget(self, action: "checkboxClicked:", forControlEvents: UIControlEvents.TouchUpInside);
        
        // display title
        cell.textLabel!.text = selectedItem.title;
        // add the button to the cell
        cell.contentView.addSubview(checkBox);
        
        return cell
    }
    
    func checkboxClicked(sender: UIButton!) {
        var touchedButton = sender as UIButton;
        
        let item: NSArray = self.items![touchedButton.tag] as NSArray;
        var itemToSave = TodoItem("\(item[0])", isChecked: false);

        // set the correct image and update change to plist
        if(isCurrentImageChecked(touchedButton)) {
            setButtonImage(touchedButton, imageName: UNCHECKED_IMAGE)
      
            itemToSave.isChecked = false;
            self.todoItem.saveToFile(touchedButton.tag, itemToSave: itemToSave);
        }
        else {
            setButtonImage(touchedButton, imageName: CHECKED_IMAGE)
            
            itemToSave.isChecked = true;
            self.todoItem.saveToFile(touchedButton.tag, itemToSave: itemToSave);
        }
    }
    
    func isCurrentImageChecked(currentButton: UIButton) -> Bool {
        if(currentButton.currentImage!.isEqual(UIImage(named: CHECKED_IMAGE))) {
            return true;
        }
        else {
            return false;
        }
    }
    
    func setButtonImage(currentButton: UIButton, imageName: String) {
        currentButton.setImage(UIImage(named: imageName), forState: UIControlState.Normal)
    }
}
