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
    public static final String ACTIONS_SEND_EVENT = "sendEvent";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_INITIALIZE)) {
            this.initialize(args.getString(0), args.getString(1), args.getString(2), callbackContext);
            return true;
        }

        if (action.equals(ACTIONS_SEND_EVENT)) {
            this.sendEvent(args.getString(0), args.getString(1), args.getString(2), callbackContext);
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

    private void sendEvent(final String eventName, final String key, final String value, final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Apsalar.event(eventName, key, value);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            }
        });
    }

    @Override
    public void onPause(boolean multitasking) {
        Apsalar.unregisterApsalarReceiver();
    }

    @Override
    public void onResume(boolean multitasking) {
        Apsalar.registerReceiver(this.cordova.getActivity().getApplicationContext());
    }
}
