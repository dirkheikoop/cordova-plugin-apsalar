var apsalarPlugin = {
  initialize: function(apiKey, apiSecret, fbAppId, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'ApsalarPlugin',
      'initialize', [apiKey, apiSecret, fbAppId]
    );
  },

  sendEvent: function(eventName, key, value, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'ApsalarPlugin',
      'sendEvent', [eventName, key, value]
    );
  },
};

module.exports = apsalarPlugin;
