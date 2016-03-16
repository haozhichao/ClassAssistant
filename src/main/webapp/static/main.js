/**
 * Created by Jinlei on 10/16/2015.
 */
require.config({
    paths: {
        jquery: 'js/jquery.min',
        blockUI: './js/plugin/jquery.blockUI',
        cookie: './js/plugin/jquery.cookie',
        domReady: './js/plugin/domReady',
        css: './js/plugin/css',
        json: './js/plugin/jquery.json-2.4_min',
        validate: './js/plugin/jquery.validate.min',
        ajaxfileupload: './js/plugin/ajaxfileupload',
        bootstrap: './js/bootstrap/js/bootstrap.min',
        dataTables: './js/dataTables/js/jquery.dataTables',
        boottable: './js/boottable/bootstrap-table.min',
        boottable_zh_cn: './js/boottable/bootstrap-table-zh-CN.min',
        daterangepicker: './js/daterangepicker/daterangepicker',
        datetimepicker: './js/datetimepicker/bootstrap-datetimepicker.min',
        toastr: './js/toastr/toastr',
        layout: './theme/layout/scripts/layout',
        typeahead:'./js/bootstrap3-typeahead',
        select2: './js/select2/select2.min',
        raty:'./js/raty/jquery.raty.min',
        form:'./js/jquery.form.min',
        bootstrapProgressbar: './js/bootstrapProgressbar/bootstrap-progressbar',
        jqueryConfirm: './js/jqueryConfirm/jquery-confirm.min',
        ajaxfileupload2: './js/ajaxfileupload/ajaxfileupload',
        wangEditor: './js/wangEditor/js/wangEditor-1.3.12',
        bootstrapValidator: './js/bootstrapValidator/js/bootstrapValidator',
        pager:'./js/jquery.twbsPagination'
    },
    shim: {
        jquery: ['css!../static/css/awesome/css/font-awesome.min.css'],
        cookie: ['jquery'],
        raty:['jquery'],
        form:['jquery'],
        pager:['jquery','bootstrap'],
        select2: ['css!./js/select2/select2-bootstrap.css', 'css!./js/select2/select2.css','jquery'],
        bootstrap: ['css!./js/bootstrap/css/bootstrap.min.css', 'jquery'],
        dataTables: ['css!./js/dataTables/css/jquery.dataTables.css'],
        boottable: ['css!./js/boottable/bootstrap-table.min.css', 'jquery', 'bootstrap'],
        boottable_zh_cn: ['jquery', 'bootstrap', 'boottable'],
        daterangepicker: ['css!./js/datepicker/daterangepicker.css', 'jquery'],
        datetimepicker:['css!./js/datetimepicker/bootstrap-datetimepicker.css', 'jquery', 'bootstrap'],
        toastr: ['css!./js/toastr/toastr.css','jquery'],
        layout: ['css!theme/global/plugins/font-awesome/css/font-awesome.min.css',
                 'css!theme/global/plugins/simple-line-icons/simple-line-icons.min.css',
                 'css!js/bootstrap/css/bootstrap.min.css',
                 'css!theme/global/plugins/morris/morris.css',
                 'css!theme/global/css/components-rounded.css',
                 'css!theme/global/css/plugins.css',
                 'css!theme/layout/css/layout.css',
                 'css!theme/layout/css/themes/default.css',
                 'css!theme/layout/css/custom.css',
                 'theme/global/plugins/jquery-migrate.min',
                 'theme/global/plugins/jquery-ui/jquery-ui.min',
                 'theme/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min',
                 'theme/global/plugins/morris/morris.min',
                 'theme/global/plugins/morris/raphael-min',
                 'theme/global/scripts/metronic',
                 'jquery',
                 'bootstrap'
            ],
        jqueryConfirm: ['css!./js/jqueryConfirm/jquery-confirm.min.css', 'jquery'],
        wangEditor: ['css!./js/wangEditor/css/wangEditor-1.3.12.css', 'jquery'],
        bootstrapValidator: ['css!./js/bootstrapValidator/css/bootstrapValidator.css', 'jquery', 'bootstrap'],
        ajaxfileupload: ['jquery'],
        ajaxfileupload2: ['jquery'],
        bootstrapProgressbar: ['jquery', 'bootstrap']

    }
//    waitSeconds: 0
});

require(['jquery', 'bootstrap', 'blockUI', 'toastr'], function($, bootstrap, blockUI, toastr){
    window.its = {};

    its.show = function(msg){
        var obj = $.alert({
            title: false,
            animation: 'top',
            confirmButton: false,
            closeIcon: false,
            columnClass:'col-md-2 col-md-offset-6',
            content: msg
        });
        setTimeout(function(){
            obj.close();
        }, 1500);
    }
    /**
     * 所有请求ajax添加loading事件
     */
    $(document).ajaxStart(function () {
        $.blockUI({
            // $.blockUI.defaults.message = "请稍候";(不写在$.blockUI({})里，写在外面)
            message: '<span style="display:inline"><img src="../../static/image/loading.gif"></span>',

            // 指的是提示框的css
            css: {
                border: 'none',
                width: "45px",   // 宽度小一点
                top: "50%",
                left: "50%"
            },

            // 遮光罩的css
            // 等价$.blockUI.defaults.overlayCSS.backgroundColor = "#E4E7EC";
            overlayCSS: {
                backgroundColor: "#FCFCFC",
                opacity:"0.8"
            }
        });
    });

    $(document).ajaxStop(function () {
        $.unblockUI();
    });

    // 对Date的扩展，将 Date 转化为指定格式的String
    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
    // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
    // 例子：
    // (new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423
    // (new Date()).Format("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18
    Date.prototype.Format = function(fmt){
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "H+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

    /**
     * 重写toJSON方法
     * @returns {string}
     */
    Date.prototype.toJSON = function () {
        var format = this.Format("yyyy-MM-dd HH:mm:ss");
        return (format.replace(" ", "T") + ".000Z");
    }
});
