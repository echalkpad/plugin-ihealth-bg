#import <Cordova/CDVPlugin.h>
#import "BGHeader.h"
@interface BgManagerCordova :CDVPlugin


- (void) sendCallBackJsonData:(NSDictionary*)dataDic command:(CDVInvokedUrlCommand*)command;
- (BG5*) getBG5withMac:(NSString *)mac;

- (void) startDiscovery:(CDVInvokedUrlCommand*)command;
- (void) stopDiscovery:(CDVInvokedUrlCommand*)command;
- (void) setUnit:(CDVInvokedUrlCommand*)command;
- (void) setBottleId:(CDVInvokedUrlCommand*)command;
- (void) getBottleId:(CDVInvokedUrlCommand*)command;
- (void) setBottleMessage:(CDVInvokedUrlCommand*)command;
- (void) getBottleMessage:(CDVInvokedUrlCommand*)command;
- (void) getBattery:(CDVInvokedUrlCommand*)command;
- (void) getOfflineData:(CDVInvokedUrlCommand*)command;
- (void) deleteOfflineData:(CDVInvokedUrlCommand*)command;
- (void) holdLink:(CDVInvokedUrlCommand*)command;

- (void) startMeasure:(CDVInvokedUrlCommand*)command;


- (void) disConnectDevice:(CDVInvokedUrlCommand*)command;
- (void) setDisconnectCallback:(CDVInvokedUrlCommand*)command;

@end


      
        
           
       
 