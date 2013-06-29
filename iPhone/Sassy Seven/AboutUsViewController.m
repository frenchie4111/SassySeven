//
//  AboutUsViewController.m
//  Sassy Seven
//
//  Created by Michael Lyons on 3/15/13.
//  Copyright (c) 2013 Serpent Software. All rights reserved.
//

#import "AboutUsViewController.h"

@interface AboutUsViewController ()

@end

@implementation AboutUsViewController

@synthesize backgroundImage = _backgroundImage;

- (IBAction)homePressed {
    [self dismissModalViewControllerAnimated:NO];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    self.backgroundImage.alpha = 0;
    self.backgroundImage.opaque = NO;
    
    [UIView animateWithDuration:1 animations:^(void){
        self.backgroundImage.alpha = 1;
    }];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    UIImage *pattern = [UIImage imageNamed:@"subtlenet2.png"];
    self.view.backgroundColor = [UIColor colorWithPatternImage:pattern];
}

@end
