//
//  TodoItem.h
//  ToDoList
//
//  Created by Alef Bogale on 10/6/14.
//  Copyright (c) 2014 Alef Bogale. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TodoItem : NSObject

@property (nonatomic, strong) NSString *title;
@property (nonatomic, assign) BOOL isChecked;

- (id)init:(NSString *)title
 isChecked:(BOOL) isChecked;

- (void)saveToFile: (NSInteger)index itemToSave: (TodoItem *) item;

- (NSMutableArray *)loadFromFile;

@end