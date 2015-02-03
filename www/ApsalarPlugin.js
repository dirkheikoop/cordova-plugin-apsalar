var apsalarPlugin = {
  initialize: function(apiKey, apiSecret, fbAppId, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'ApsalarPlugin',
      'initialize', [apiKey, apiSecret, fbAppId]
    );
  },

  setUserId: function(userId, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'ApsalarPlugin',
      'initialize', [userId]
    );
  },

  purchase: function(value, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'ApsalarPlugin',
      'initialize', [value]
    );
  },
};

module.exports = apsalarPlugin;
