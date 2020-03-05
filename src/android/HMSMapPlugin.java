package com.huawei.hmsmapplugin;

import android.app.Activity;
import android.content.Intent;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HMSMapPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("openHuaweiMap")) {
            this.openHuaweiMap();
            return true;
        }
        return false;
    }

    private void openHuaweiMap() {
        Activity activity = this.cordova.getActivity();
        Intent intent = new Intent(activity, MapsActivity.class);
        activity.startActivity(intent);
       
    }
}
