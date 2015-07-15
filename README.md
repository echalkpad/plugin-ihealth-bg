# Cordova iHealth Plugin for Bg5

This is a cordova plugin for ihealth bg5.

## Using Cordova plugins directly in your meteor application

### Add the plugin

    $ meteor add cordova:com.ihealth.plugin.bgmanagercordova@https://github.com/iHealthLab/plugin-ihealth-bg/tarball/485ecc5d5fd9165a93bf1b45ddcee4faa18e48af


### Remove the plugin

    $ meteor remove cordova:com.ihealth.plugin.bgmanagercordova
    

##### BgManagerCordova.startDiscovery("", success, failure);
>> 启动普通蓝牙扫面，10秒后自动结束扫面

##### BgManagerCordova.stopDiscovery("", success, failure);


##### BgManagerCordova.startMeasure(mac, success, failure);

##### BgManagerCordova.stopMeasure(mac, success, failure);

##### BgManagerCordova.setUnit(mac, success, failure, 1);

##### BgManagerCordova.setBottleMessage(mac, success, failure);

##### BgManagerCordova.getBottleMessage(mac, success, failure);

##### BgManagerCordova.setBottleId(mac, success, failure);

##### BgManagerCordova.getBottleId(mac, success, failure);

##### BgManagerCordova.getOfflineData(mac, success, failure);

##### BgManagerCordova.getBattery(mac, success, failure);

##### BgManagerCordova.deleteOfflineData(mac, success, failure);

##### BgManagerCordova.disConnectDevice(mac, success, failure);


  
