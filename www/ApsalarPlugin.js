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
      'setUserId', [userId]
    );
  },

  purchase: function(successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'ApsalarPlugin',
      'purchase', []
    );
  },
};

module.exports = apsalarPlugin;
