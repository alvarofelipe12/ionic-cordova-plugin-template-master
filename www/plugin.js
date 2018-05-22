
var exec = require('cordova/exec');

var PLUGIN_NAME = 'MiPlugin';

var MiPlugin = {
  saludo: function (name, j, successCallback, errorCallback){
        exec(successCallback, errorCallback, PLUGIN_NAME, name, j);
  }
};

module.exports = MiPlugin;
