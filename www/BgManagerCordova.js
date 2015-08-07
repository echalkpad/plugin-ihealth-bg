/*global cordova, module*/
var BgManagerCordova = function() {};  
  
BgManagerCordova.prototype.startDiscovery = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "startDiscovery", [mac]);
};

BgManagerCordova.prototype.stopDiscovery = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "stopDiscovery", [mac]);
};

BgManagerCordova.prototype.startMeasure = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "startMeasure", [mac]);
}; 

BgManagerCordova.prototype.connectDevice = function(mac, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "connectDevice", [mac]);
};

BgManagerCordova.prototype.setUnit = function(mac, successCallback, errorCallback, type) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setUnit", [mac, type]);
}; 

BgManagerCordova.prototype.getBattery = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "getBattery", [mac]);
};  

BgManagerCordova.prototype.setBottleId = function(mac, successCallback, errorCallback, bottleId) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setBottleId", [mac, bottleId]);
}; 

BgManagerCordova.prototype.getBottleId = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "getBottleId", [mac]);
};

<<<<<<< HEAD
BgManagerCordova.prototype.setBottleMessage = function(mac, successCallback, errorCallback, leftNum, timeTs) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setBottleMessage", [mac, leftNum, timeTs]);
=======
BgManagerCordova.prototype.setBottleMessage = function(mac, successCallback, errorCallback, qr, leftNum, time) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setBottleMessage", [mac, qr, leftNum, time]);
>>>>>>> origin/master
}; 

BgManagerCordova.prototype.getBottleMessage = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "getBottleMessage", [mac]);
};

BgManagerCordova.prototype.getOfflineData = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "getOfflineData", [mac]);
};

BgManagerCordova.prototype.deleteOfflineData = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "deleteOfflineData", [mac]);
};
  
BgManagerCordova.prototype.disConnectDevice = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "disConnectDevice", [mac]);
};

BgManagerCordova.prototype.setDisconnectCallback = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "setDisconnectCallback", [mac]);
};

BgManagerCordova.prototype.holdLink = function(mac, successCallback, errorCallback) {  
    cordova.exec(successCallback, errorCallback, "BgManagerCordova", "holdLink", [mac]);
};

var bgManagerCordova = new BgManagerCordova();  
module.exports = bgManagerCordova; 
