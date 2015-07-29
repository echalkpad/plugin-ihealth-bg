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

#pragma mark -
#pragma mark some Methods

- (void) startDiscovery:(CDVInvokedUrlCommand*)command{


    bgController = [BG5Controller shareIHBg5Controller];

}
- (void) setUnit:(CDVInvokedUrlCommand*)command{

     NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

     NSArray *bgArray = [bgController getAllCurrentBG5Instace];


     if(bgArray.count>0){

        BG5 *bgInstance = [bgArray objectAtIndex:0];

        [message setObject:bgInstance.serialNumber forKey:@"address"];

        [bgInstance commandInitBGSetUnit:BGUnit_mmolPL BGUserID:@"" clientID:@"" clientSecret:@"" Authentication:^(UserAuthenResult result) {
            
        } DisposeBGBottleID:^(NSNumber *bottleID) {


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
- (void) startMeasure:(CDVInvokedUrlCommand*)command{

     NSMutableDictionary* message = [[NSMutableDictionary alloc] init];

     NSArray *bgArray = [bgController getAllCurrentBG5Instace];


     if(bgArray.count>0){

        BG5 *bgInstance = [bgArray objectAtIndex:0];

        [message setObject:bgInstance.serialNumber forKey:@"address"];


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




@end
