# [WIP] Cordova - HMS Map Plugin

This plugin helps you to integrate HMS Map Kit in your applications.

## Preparation

Follow official [codelab](https://developer.huawei.com/consumer/en/codelab/HMSPreparation/index.html#0) for HMS Core enablement.

## Usage

1. Clone this repository and make any customizations by your needs

```sh
git clone git@github.com:onurkenis/cordova-hms-map-plugin.git
```

2. Go to your application root directory

```sh
cd app-root-directory
```

3. Add plugin by pointing the plugin directory

```sh
cordova plugin add plugin-directory
```

4. Call function from custom plugin.

```js
cordova.plugins.HMSMapPlugin.openHuaweiMap();
```
