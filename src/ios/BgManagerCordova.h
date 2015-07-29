#import <Cordova/CDVPlugin.h>
#import "BGHeader.h"
@interface BgManagerCordova :CDVPlugin


-(void)sendCallBackJsonData:(NSDictionary*)dataDic command:(CDVInvokedUrlCommand*)command;

- (void) startDiscovery:(CDVInvokedUrlCommand*)command;
- (void) setUnit:(CDVInvokedUrlCommand*)command;
- (void) startMeasure:(CDVInvokedUrlCommand*)command;

@end


      
        
           
       
 