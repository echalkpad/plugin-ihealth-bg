#import "BgManagerCordova.h"

@implementation BgManagerCordova{
    
    BG5Controller *bgController;

}

NSString* theCallbackId;




#pragma mark -
#pragma mark Cordova Methods


- (void)pluginInitialize {

    NSLog(@"Bluetooth Serial Cordova Plugin - BLE version");

    [super pluginInitialize];

    bgController = [BG5Controller shareIHBg5Controller];

}

- (id)init
{
    self = [super init];
    bgController = [BG5Controller shareIHBg5Controller];
    return self;
}


#pragma mark -
#pragma mark My Methods

-(void)sendCallBackJsonData:(NSDictionary*)dataDic command:(CDVInvokedUrlCommand*)command{

    CDVPluginResult* pluginResult = nil;
    
    NSError *error = Nil;
    
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dataDic options:(NSJSONWritingPrettyPrinted) error:&error];

    NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString: jsonString];

    [pluginResult setKeepCallbackAsBool:YES];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

    
}

- (BG5*) getBG5withMac:(NSString *)mac{


    if (mac.length>0)
    {

         bgController = [BG5Controller shareIHBg5Controller];

    NSArray *bgArray = [bgController getAllCurrentBG5Instace];

    if (bgArray.count>0)
    {
        for(BG5 *tempBG5 in bgArray){

          if([mac isEqualToString:tempBG5.serialNumber]){
            
            return tempBG5;
            break;
          
          }
        }
    }else{

        return nil;

    }    
        
    }else{

         return nil;

    }
   

}


#pragma mark -
#pragma mark some Methods

- (void) startDiscovery:(CDVInvokedUrlCommand*)command{


    bgController = [BG5Controller shareIHBg5Controller];


    NSArray *bgArray = [bgController getAllCurrentBG5Instace];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    for (int i = 0; i < bgArray.count; i++)
    {
        
    BG5*myBg5=[bgArray objectAtIndex:i];
        
       
    [message setObject:@"discovery doing" forKey:@"msg"];
    [message setObject:myBg5.serialNumber forKey:@"address"];
    [message setObject:@"BG5" forKey:@"name"];


    [self sendCallBackJsonData:message command:command];

    }

}

- (void) stopDiscovery:(CDVInvokedUrlCommand*)command{

     NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:@"discovery done" forKey:@"msg"];

    [message setObject:mac forKey:@"address"];

    [self sendCallBackJsonData:message command:command];

}

- (void) setUnit:(CDVInvokedUrlCommand*)command{

    NSString* mac = [command.arguments objectAtIndex:0];

    NSNumber* unit = [command.arguments objectAtIndex:1];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){


        [message setObject:mac forKey:@"address"];

        [message setObject:@"setUnit" forKey:@"msg"];

        [bgInstance commandInitBGSetUnit:unit DisposeBGErrorBlock:^(NSNumber *errorID) {


        
        }];

         [self sendCallBackJsonData:message command:command];


    }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }


}

- (void) setBottleId:(CDVInvokedUrlCommand*)command{

    NSString* mac = [command.arguments objectAtIndex:0];

     NSNumber* bottleID = [command.arguments objectAtIndex:1];


     NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){

     [bgInstance commandSendBottleID:bottleID DisposeBGSendBottleIDBlock:^(BOOL sendOk) {

        [message setObject:mac forKey:@"address"];

        [message setObject:@"setBottleId" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];

        
    } DisposeBGErrorBlock:^(NSNumber *errorID) {
        
    }];



     }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }



}
- (void) getBottleId:(CDVInvokedUrlCommand*)command{


    NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:mac forKey:@"address"];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){


    [bgInstance commandGetbottleID:^(NSNumber *bottleID) {

         [message setObject:@"bottleid" forKey:@"msg"];

         [message setObject:bottleID forKey:@"bottleid"];


         [self sendCallBackJsonData:message command:command];

        
    } DisposeBGErrorBlock:^(NSNumber *errorID) {
        
    }];

     }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }




}

- (void) setBottleMessage:(CDVInvokedUrlCommand*)command{


     NSString* mac = [command.arguments objectAtIndex:0];

     NSString* qr = [command.arguments objectAtIndex:1];

     NSNumber* leftNum = [command.arguments objectAtIndex:2];

     NSString* timeTs = [command.arguments objectAtIndex:3];


     NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

     [message setObject:mac forKey:@"address"];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){



     [bgInstance commandSendBGCodeString:qr validDate:timeTs remainNum:leftNum DisposeBGSendCodeBlock:^(BOOL sendOk) {


         [message setObject:@"setBottleMessage" forKey:@"msg"];

         [self sendCallBackJsonData:message command:command];
        
    } DisposeBGStartModel:^(BGOpenMode mode) {
        
    } DisposeBGErrorBlock:^(NSNumber *errorID) {
        
    }];


     }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }




}
- (void) getBottleMessage:(CDVInvokedUrlCommand*)command{


     NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:mac forKey:@"address"];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){


     [bgInstance commandReadBGCodeDic:^(NSDictionary *codeDic) {
        
        NSNumber *ShiTnum=[codeDic valueForKey:@"Strips"];
        NSNumber*bottleID=[codeDic valueForKey:@"bottleID"];
        NSString*date=[codeDic valueForKey:@"Date"];
        
         [message setObject:@"code" forKey:@"msg"];

         [message setObject:bottleID forKey:@"bottleid"];

         [message setObject:ShiTnum forKey:@"leftnum"];

         [message setObject:date forKey:@"expiretime"];

         [self sendCallBackJsonData:message command:command];  
        
        
        
    } DisposeBGErrorBlock:^(NSNumber *errorID) {
        
    }];


     }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }

}

- (void) getOfflineData:(CDVInvokedUrlCommand*)command{

    NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:mac forKey:@"address"];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){



     [bgInstance commandTransferMemorryData:^(NSNumber *dataCount) {
        
    } DisposeBGHistoryData:^(NSDictionary *historyDataDic) {


         [message setObject:@"value" forKey:@"msg"];

        [message setObject:historyDataDic forKey:@"history"];

        [self sendCallBackJsonData:message command:command];  


        
    } DisposeBGErrorBlock:^(NSNumber *errorID) {
        
    }];

     }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }


}

- (void) getBattery:(CDVInvokedUrlCommand*)command{


    NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];


    [message setObject:@"No Battery" forKey:@"msg"];

    [message setObject:mac forKey:@"address"];


    [self sendCallBackJsonData:message command:command];  

}

- (void) deleteOfflineData:(CDVInvokedUrlCommand*)command{

      NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:mac forKey:@"address"];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){



     [bgInstance commandDeleteMemorryData:^(BOOL deleteOk) {

     [message setObject:@"deleteOfflineData" forKey:@"msg"];


     [self sendCallBackJsonData:message command:command];
        
    }];

      }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }




}
- (void) holdLink:(CDVInvokedUrlCommand*)command{

     NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:mac forKey:@"address"];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){

     [bgInstance commandKeepConnectDisposeBGErrorBlock:^(NSNumber *errorID) {
        
    }];

      [message setObject:@"holdLink" forKey:@"msg"];


     [self sendCallBackJsonData:message command:command];

     }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }



}
- (void) startMeasure:(CDVInvokedUrlCommand*)command{

      NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:mac forKey:@"address"];

    BG5 *bgInstance = [self getBG5withMac:mac];


     if(bgInstance!=nil){


        [bgInstance commandCreateBGtestModel:BGMeasureMode_Blood DisposeBGStripInBlock:^(BOOL stripIn) {

             [message setObject:@"strip in" forKey:@"msg"];

             [self sendCallBackJsonData:message command:command];

        
         } DisposeBGBloodBlock:^(BOOL blood) {

             [message setObject:@"get blood" forKey:@"msg"];

             [self sendCallBackJsonData:message command:command];

        
         } DisposeBGResultBlock:^(NSNumber *result) {

              [message setObject:@"value" forKey:@"msg"];

              [message setObject:result forKey:@"value"];

             [self sendCallBackJsonData:message command:command];
        
         } DisposeBGTestModelBlock:^(BGMeasureMode mode) {
        
         } DisposeBGErrorBlock:^(NSNumber *errorID) {

             [message setObject:@"strip out" forKey:@"msg"];

             [self sendCallBackJsonData:message command:command];
        
         }];

    }else{

        [message setObject:@"No Device" forKey:@"msg"];

        [self sendCallBackJsonData:message command:command];       
    }

}

- (void) disConnectDevice:(CDVInvokedUrlCommand*)command{

     NSString* mac = [command.arguments objectAtIndex:0];

    NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

    [message setObject:@"disconnect" forKey:@"msg"];

    [message setObject:mac forKey:@"mac"];

    [message setObject:@"BP5" forKey:@"type"];

    [self sendCallBackJsonData:message command:command];


}
- (void) setDisconnectCallback:(CDVInvokedUrlCommand*)command{


     NSString* mac = [command.arguments objectAtIndex:0];


}




@end
