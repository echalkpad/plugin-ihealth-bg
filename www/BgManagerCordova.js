/*global cordova, module*/
var BgManagerCordova = function() {};  
  
BgManagerCordova.prototype.startDiscovery = function(mac, successCallback, errorCallback, test) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "startDiscovery", [mac, test]);
};

BgManagerCordova.prototype.stopDiscovery = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "stopDiscovery", [mac]);
};

BgManagerCordova.prototype.startMeasure = function(mac, successCallback, errorCallback, test) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "startMeasure", [mac, test]);
}; 

BgManagerCordova.prototype.connectDevice = function(mac, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "connectDevice", [mac]);
};

BgManagerCordova.prototype.setUnit = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setUnit", [mac]);
}; 

BgManagerCordova.prototype.getBattery = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "getBattery", [mac]);
};  

BgManagerCordova.prototype.setBottleMessage = function(mac, successCallback, errorCallback, test) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setBottleMessage", [mac, test]);
}; 

BgManagerCordova.prototype.getBottleMessage = function(mac, successCallback, errorCallback, test) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "getBottleMessage", [mac, test]);
};

BgManagerCordova.prototype.getOfflineData = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "getOfflineData", [mac]);
};

BgManagerCordova.prototype.deleteOfflineData = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "deleteOfflineData", [mac]);
};
  
BgManagerCordova.prototype.disConnectDevice = function(mac, successCallback, errorCallback, test) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "disConnectDevice", [mac, test]);
};

BgManagerCordova.prototype.setDisconnectCallback = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setDisconnectCallback", [mac]);
};

BgManagerCordova.prototype.holdLink = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "holdLink", [mac]);
};

var bgManagerCordova = new BgManagerCordova();  
module.exports = bgManagerCordova; 
