var exec = require('cordova/exec');

exports.openHuaweiMap = function (arg0, success, error) {
    exec(success, error, 'HMSMapPlugin', 'openHuaweiMap', [arg0]);
};
