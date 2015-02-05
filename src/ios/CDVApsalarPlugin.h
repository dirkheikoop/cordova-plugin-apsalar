#import <Cordova/CDV.h>
#import "Apsalar.h"

@interface CDVApsalarPlugin: CDVPlugin

- (void)initialize: (CDVInvokedUrlCommand*)command;
- (void)sendEvent: (CDVInvokedUrlCommand*)command;

@end

