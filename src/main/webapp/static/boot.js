(function() {
    var _its = window.its = window.its || {};

    avoidConsoleError();

    // 公有库配置：{id:[[js列表],[css列表]]}
    var CONF_LIBS = {
        'bootstrap': [['js/bootstrap/js/bootstrap.min.js'], ['js/bootstrap/css/bootstrap.min.css']],
        'boottable': [['js/boottable/bootstrap-table.min.js', 'js/boottable/bootstrap-table-zh-CN.min.js'], ['js/boottable/bootstrap-table.min.css']],
        'dataTables': [['js/dataTables/js/jquery.dataTables.js'], ['js/dataTables/css/jquery.dataTables.css']],
        'blockUI': [['js/plugin/jquery.blockUI.js'], []],
        'toastr': [['js/toastr/toastr.js'], ['js/toastr/toastr.css']],
        'select2': [['js/select2/select2.min.js'], ['js/select2/select2-bootstrap.css', 'js/select2/select2.css']],
        'typeahead': [['js/bootstrap3-typeahead.js'], []],
        'raty': [['js/raty/jquery.raty.min.js'], []],
        'form': [['js/jquery.form.min.js'], []],
        'jqueryConfirm': [['js/jqueryConfirm/jquery-confirm.min.js'], ['js/jqueryConfirm/jquery-confirm.min.css']],
        'ajaxfileupload2': [['js/ajaxfileupload/ajaxfileupload.js'], []],
        'wangEditor': [['js/ajaxfileupload/ajaxfileupload.js'], ['js/wangEditor/css/wangEditor-1.3.12.css']],
        'bootstrapValidator': [['js/ajaxfileupload/ajaxfileupload.js'], ['js/bootstrapValidator/css/bootstrapValidator.css']],
        'bootstrapProgressbar': [['js/ajaxfileupload/ajaxfileupload.js'], []],
        'layout': [['theme/global/plugins/jquery-migrate.min.js',
                    'theme/global/plugins/jquery-ui/jquery-ui.min.js',
                    'theme/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js',
                    'theme/global/plugins/morris/morris.min.js',
                    //'theme/global/plugins/morris/raphael-min.js',
                    'theme/global/scripts/metronic.js',
                    'theme/layout/scripts/layout.js'],[]]
    };
    
    var loaded = {};
    
    // 确定boot.js加载路径
    bootPATH = '/';
    var bootURL = '';
    var userlibs = [];

    initBootPath();
    console.debug('bootPath %o', bootPATH);
    _its.bootPath = bootPATH;

    // 1 加载公共库
    var preloadLibs = ['js/jquery.min.js', 'js/sea.js', 'js/plugin/jquery.json-2.4_min.js'];
    loadJs(preloadLibs);
    // 2 加载主题样式
    if (bootURL.indexOf('layout') > -1) {
        var libConf = CONF_LIBS['layout'];
        if (libConf){
            loadLib(libConf);
        }
    }
    // 3 加载用户通过url传入的lib
    loadLibs(userlibs);

    function loadJs(paths){
        console.debug('loadJs:%o', paths);
        for (var _i = 0, _len = paths.length; _i < _len; _i++) {
            var path = paths[_i];
            if (!loaded[path]){
                document.write('<script src="' + bootPATH + path + '" type="text/javascript"></script>');
                loaded[path]=true;
            }
        }
    }

    function loadCss(paths){
        console.debug('loadCss: %o', paths);
        for (var _i = 0, _len = paths.length; _i < _len; _i++) {
            var path = paths[_i];
            if (!loaded[path]){
                document.write('<link href="' + bootPATH + path +'" rel="stylesheet" type="text/css" />');
                loaded[path]=true;
            }
        }
    }
    function loadLib(lib){
        console.debug('loadLib: %o', lib);
        if (lib.length != 2) return;
        loadJs(lib[0]);
        loadCss(lib[1]);
    }
    function loadLibs(libs){
        console.debug('loadLibs: %o', libs);
        for (var _i = 0, _len = libs.length; _i < _len; _i++) {
            var libId = libs[_i];
            var libConf = CONF_LIBS[libId];
            if (libConf){
                loadLib(libConf);
            }
        }
    }
	function setCookie(name, value) {
		var argv = setCookie.arguments;
		var argc = setCookie.arguments.length;
		var expires = (argc > 2) ? argv[2] : null;
		if(expires!=null) {
			var LargeExpDate = new Date ();
			LargeExpDate.setTime(LargeExpDate.getTime() + (expires*1000*3600*24));        
		}
		document.cookie = name + "=" + escape (value)+((expires == null) ? "" : ("; expires=" +LargeExpDate.toGMTString()));
	}
    function getCookie(sName) {
        var aCookie = document.cookie.split("; ");
        var lastMatch = null;
        for (var i = 0; i < aCookie.length; i++) {
            var aCrumb = aCookie[i].split("=");
            if (sName == aCrumb[0]) {
                lastMatch = aCrumb;
            }
        }
        if (lastMatch) {
            var v = lastMatch[1];
            if (v === undefined) return v;
            return unescape(v);
        }
        return null;
    }

    // Avoid `console` errors in browsers that lack a console.
    function avoidConsoleError(){
        var method;
        var noop = function () {};
        var methods = [
            'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
            'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
            'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
            'timeStamp', 'trace', 'warn'
        ];
        var length = methods.length;
        var console = (window.console = window.console || {});
    
        while (length--) {
            method = methods[length];
    
            // Only stub undefined methods.
            if (!console[method]) {
                console[method] = noop;
            }
        }
    }
    function initBootPath(){
        var bootjs = document.getElementsByTagName("script");
        for (var i = 0; i < bootjs.length; i++) {
            var src = bootjs[i].src;
            var idx = src.indexOf("boot.js");
            if (idx > 0){
                bootURL = src;
                bootPATH = src.substring(0, idx);
                var len = src.length;
                if (len > idx+1){
                    userlibs = src.substring(idx+8).split(',');
                }
            }
        }
    }

})();
