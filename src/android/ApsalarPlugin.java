package org.apache.cordova.core;

import android.app.Activity;

import com.apsalar.sdk.Apsalar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class ApsalarPlugin extends CordovaPlugin {
    public static final String ACTION_INITIALIZE = "initialize";
    public static final String ACTION_SET_USER_ID = "setUserId";
    public static final String ACTIONS_PURCHASE = "purchase";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_INITIALIZE)) {
            this.initialize(args.getString(0), args.getString(1), args.getString(2), callbackContext);
            return true;
        }

        if (action.equals(ACTION_SET_USER_ID)) {
            this.setUserId(args.getString(0), callbackContext);
            return true;
        }

        if (action.equals(ACTIONS_PURCHASE)) {
            this.purchase(callbackContext);
            return true;
        }

        return false;
      }

    private void initialize(final String apiKey, final String apiSecret, final String fbAppId, final CallbackContext callbackContext) {
        final Activity currentActivity = this.cordova.getActivity();
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Apsalar.setFBAppId(fbAppId);
                Apsalar.startSession(currentActivity, apiKey, apiSecret);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            }
        });
    }

    private void setUserId(final String userId, final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Apsalar.event("registration", "registered_user_id", userId);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            }
        });

    }

    private void purchase(final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Apsalar.event("purchase", "total", 0);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            }
        });
    }

    @Override
    public void onPause(boolean multitasking) {
        Apsalar.unregisterApsalarReceiver();
    }
}
