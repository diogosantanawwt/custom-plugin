package cordova.plugins;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface ICustomVenmoPlugin {
    void coolMethod(String message, CallbackContext callbackContext);
}

/**
 * This class echoes a string called from JavaScript.
 */
public class CustomVenmoPlugin extends CordovaPlugin implements ICustomVenmoPlugin {

    public static ICustomVenmoPlugin instance;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        instance = this;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    @Override
    private void coolMethod(String message, CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext(); 

        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
