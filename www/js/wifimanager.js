var localPath = "http://123.56.143.239:8080/subway/"
var listenerHanddler = {
  'showStatusBar': 'showStatusBar',
  'checkApp': 'checkApp'
}
console.log = function (data) {
  alert(data);
}
function checkApp() {
  window.frames[0].postMessage("{'isApp':true}", '*');
}
window.addEventListener('message', function (e) {
  if (typeof(e.data) == "object" && Object.prototype.toString.call(e.data).toLowerCase() == "[object object]" && !e.data.length) {
    jsonData = e.data;
  } else {
    jsonData = eval('(' + e.data + ')');
  }
  eval(listenerHanddler[jsonData.event] + "(" + jsonData.params + ")");
}, false);
function showStatusBar(param) {
  if (param) {
    var barName = "ionheader";
    var barType = param['barType'];
    var usr = param['usr'];
    var diamondnum = param['diamondnum'];
    if (barType && "" != barType) {
      barName = barName + barType;
    }
    for (var i = 0; i < document.getElementsByName("saigoheader").length; i++) {
      if (barName != document.getElementsByName("saigoheader")[i].id) {
        document.getElementsByName("saigoheader")[i].style.display = 'none';
      } else {
        document.getElementsByName("saigoheader")[i].style.display = '';
      }
    }
    if (!usr || "" == usr) {
      document.getElementById("unlogin").style.display = '';
      document.getElementById("logined").style.display = 'none';
    } else {
      document.getElementById("unlogin").style.display = 'none';
      document.getElementById("logined").style   .display = '';
      if (diamondnum) {
        document.getElementById("diamondnum").innerText = diamondnum;
      }
    }
  }
}
var wifiScan = function (successProcess) {
  if (undefined != WifiWizard) {
    WifiWizard.startScan(function () {
      WifiWizard.getScanResults({}, function (data) {
        successProcess(data);
      }, function (e) {
        console.log("扫描失败", e);
      });
    }, function (e) {
      console.log("扫描失败", e);
    });
  }
}
var addSign = function (x, y, sense) {
  wifiScan(function (data) {
    console.log(JSON.stringify(data));
    $.ajax({
      type: 'POST',
      url: localPath + "location/wifi/addSign",
      data: {
        x: x,
        y: y,
        sense: sense,
        wifiInfo: JSON.stringify(data)
      },
      success: function (data) {
        console.log(data);
      },
      error: function (data) {
        console.log(JSON.stringify(data));
      },
      dataType: "json"
    });
  })
}
var isDeviceLoad = false;
function getDeviceUuid() {
  if (undefined != device) {
    return device.uuid;
  }
  return "";
}
function getDeviceModel() {
  if (undefined != device) {
    return device.model;
  }
  return "";
}
var getPosition = function (func) {
  wifiScan(function (data) {
    $.ajax({
      type: 'POST',
      url: localPath + "location/wifi/getPosition",
      data: {
        wifiInfo: JSON.stringify(data),
        uuid: getDeviceUuid(),
        model: getDeviceModel(),
      },
      success: function (data) {
        func(data);
      },
      error: function (data) {
        console.log(JSON.stringify(data));
      },
      dataType: "json"
    });
  })
}
document.addEventListener("deviceready", onDeviceReady, false);
function onDeviceReady() {
  console.log(device.cordova);
  isDeviceLoad = true;
}

var getIp = function () {
  var ip = "";
  $.ajax({
    type: 'get',
    url: "http://1212.ip138.com/ic.asp",
    data: {},
    async: false,
    success: function (data) {
      ip = data.split("[")[1].split("]")[0];
    },
    error: function (data) {
      console.log(JSON.stringify(data));
    },
    dataType: "html"
  });
  return ip;
}
// setInterval("wifiScan()", 1);
