//获取OS版本
function detectOS() {
	var sUserAgent = navigator.userAgent;
	var isWin = (navigator.platform == "Win32")
			|| (navigator.platform == "Windows");
	var isMac = (navigator.platform == "Mac68K")
			|| (navigator.platform == "MacPPC")
			|| (navigator.platform == "Macintosh")
			|| (navigator.platform == "MacIntel");
	if (isMac)
		return "Mac";
	var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
	if (isUnix)
		return "Unix";
	var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
	if (isLinux)
		return "Linux";
	if (isWin) {
		var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1
				|| sUserAgent.indexOf("Windows 2000") > -1;
		if (isWin2K)
			return "Win2000";
		var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1
				|| sUserAgent.indexOf("Windows XP") > -1;
		if (isWinXP)
			return "WinXP";
		var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1
				|| sUserAgent.indexOf("Windows 2003") > -1;
		if (isWin2003)
			return "Win2003";
		var isWinVista = sUserAgent.indexOf("Windows NT 6.0") > -1
				|| sUserAgent.indexOf("Windows Vista") > -1;
		if (isWinVista)
			return "WinVista";
		var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1
				|| sUserAgent.indexOf("Windows 7") > -1;
		if (isWin7)
			return "Win7";
		var isWin7 = sUserAgent.indexOf("Windows 8") > -1
				|| sUserAgent.indexOf("Windows 8") > -1;
		if (isWin7)
			return "Win8";
	}
	return "win10";
}

// 获取浏览器版本
var getBrowser = function() {
	var userAgent = navigator.userAgent;
	var info = "";
	var tempArray = "";
	if (/[Ff]irefox(\/\d+\.\d+)/.test(userAgent)) {
		tempArray = /([Ff]irefox)\/(\d+\.\d+)/.exec(userAgent);
		info += tempArray[1] + tempArray[2];
	} else if (/MSIE \d+\.\d+/.test(userAgent)) {
		tempArray = /MS(IE) (\d+\.\d+)/.exec(userAgent);
		info += tempArray[1] + tempArray[2];
	} else if (/[Cc]hrome\/\d+/.test(userAgent)) {
		tempArray = /([Cc]hrome)\/(\d+)/.exec(userAgent);
		info += tempArray[1] + tempArray[2];
	} else if (/[Vv]ersion\/\d+\.\d+\.\d+(\.\d)* *[Ss]afari/.test(userAgent)) {
		tempArray = /[Vv]ersion\/(\d+\.\d+\.\d+)(\.\d)* *([Ss]afari)/
				.exec(userAgent);
		info += tempArray[3] + tempArray[1];
	} else if (/[Oo]pera.+[Vv]ersion\/\d+\.\d+/.test(userAgent)) {
		tempArray = /([Oo]pera).+[Vv]ersion\/(\d+)\.\d+/.exec(userAgent);
		info += tempArray[1] + tempArray[2];
	} else {
		info += "unknown";
	}
	return info;
};

var getDate = function() {
	var d = new Date();
	var str = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()
			+ " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
	return str;
};



var timerID = null
var timerRunning = false
function MakeArray(size) {
	this.length = size;
	for (var i = 1; i <= size; i++) {
		this[i] = "";
	}
	return this;
}
function stopclock() {
	if (timerRunning)
		clearTimeout(timerID);
	timerRunning = false
}
function showtime() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var date = now.getDate();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	var day = now.getDay();
	Day = new MakeArray(7);
	Day[0] = "星期天";
	Day[1] = "星期一";
	Day[2] = "星期二";
	Day[3] = "星期三";
	Day[4] = "星期四";
	Day[5] = "星期五";
	Day[6] = "星期六";
	var timeValue = "";
	timeValue += year + "年";
	timeValue += ((month < 10) ? "0" : "") + month + "月";
	timeValue += date + "日 ";
	timeValue += (Day[day]) + " ";
	timeValue += hours;
	timeValue += ((minutes < 10) ? ":0" : ":") + minutes;
	timeValue += ((seconds < 10) ? ":0" : ":") + seconds;
	document.all.timeshow.innerHTML = timeValue;
	timerID = setTimeout("showtime()", 1000);
	timerRunning = true
}
