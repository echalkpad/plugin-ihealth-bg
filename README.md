# Cordova iHealth Plugin for Bg5

This is a cordova plugin for ihealth bg5.

## Using Cordova plugins directly in your meteor application

### Add the plugin

    $ meteor add cordova:com.ihealth.plugin.bgmanagercordova@https://github.com/iHealthLab/plugin-ihealth-bg/tarball/485ecc5d5fd9165a93bf1b45ddcee4faa18e48af


### Remove the plugin

    $ meteor remove cordova:com.ihealth.plugin.bgmanagercordova
    

##### BgManagerCordova.startDiscovery("", success, failure);
> 启动普通蓝牙扫面，10秒后自动结束扫面。

##### BgManagerCordova.stopDiscovery("", success, failure);
> 停止普通蓝牙扫面。

##### BgManagerCordova.startMeasure(mac, success, failure);
> 开始进行血糖测量

##### BgManagerCordova.setUnit(mac, success, failure, 1);
> 设置血糖单位

##### BgManagerCordova.setBottleMessage(mac, success, failure);
> 设置设备BG5中试条瓶信息 

##### BgManagerCordova.getBottleMessage(mac, success, failure);
> 获得设备BG5中试条瓶信息 

##### BgManagerCordova.setBottleId(mac, success, failure);
> 设置设备BG5中试条瓶ID 

##### BgManagerCordova.getBottleId(mac, success, failure);
> 获得设备BG5中试条瓶ID 

##### BgManagerCordova.getOfflineData(mac, success, failure);
> 获得设备BG5中离线数据 

##### BgManagerCordova.deleteOfflineData(mac, success, failure);
> 删除设备BG5中离线数据  

  
