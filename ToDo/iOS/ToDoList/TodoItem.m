//
//  TodoItem.m
//  ToDoList
//
//  Created by Alef Bogale on 10/6/14.
//  Copyright (c) 2014 Alef Bogale. All rights reserved.
//

#import "TodoItem.h"
#define PLISTNAME @"todo_items.plist"

@implementation TodoItem

- (id) init:(NSString *)title isChecked:(BOOL)isChecked
{
    self = [super init];
    if (self != nil) {
        _title = title;
        _isChecked = isChecked;
    }
    return self;
}


- (NSString *)pListName: (NSString *)pList;
{
    NSString *path = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES)[0];
    return [path stringByAppendingPathComponent:pList];
}

- (NSMutableArray *)loadFromFile
{
    NSMutableArray *todoItems = [NSMutableArray arrayWithContentsOfFile:[self pListName:PLISTNAME]];
    NSLog(@"Array from plist: %@", todoItems);
    
    if (todoItems != nil) {
        for (NSArray *todoItem in todoItems) {
            if (todoItem != nil || [todoItem count] > 0) {
                self.title = todoItem[0];
                self.isChecked = [todoItem[1]  isEqual: @"YES"] ? YES : NO;
            }
        }
    }
    
    return todoItems;
}


- (void)saveToFile: (NSInteger)index itemToSave: (TodoItem *) item;
{
    BOOL isNew = index == -1 ? YES: NO;
    
    NSArray *todoItem = [NSArray arrayWithObjects: item.title,
                         (item.isChecked ? @"YES" : @"NO"), nil];

    NSMutableArray *items = [self loadFromFile];
    
    if (items == nil) {
        items = [[NSMutableArray alloc] init];
    }
    
    if (isNew) {
        [items addObject: todoItem];
    }
    else {
        [items replaceObjectAtIndex:index withObject:todoItem];
    }
    
    [items writeToFile:[self pListName:PLISTNAME] atomically:YES];
    
    NSLog(@"Array: %@", items);
}

@end
