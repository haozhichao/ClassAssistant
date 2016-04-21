define(['jquery', 'toastr', 'bootstrapValidator', 'fileinput'], function (toastr) {

    function init() {

        initFileZh();
        initFileInput();
        // initNoticeList(); // 初始化列表
        // initButton();     // 初始化点击事件

    }
    // fileinput 插件汉化
    function initFileZh() {
        (function ($) {
            "use strict";

            $.fn.fileinputLocales['zh'] = {
                fileSingle: '文件',
                filePlural: '多个文件',
                browseLabel: '选择 &hellip;',
                removeLabel: '移除',
                removeTitle: '清除选中文件',
                cancelLabel: '取消',
                cancelTitle: '取消进行中的上传',
                uploadLabel: '上传',
                uploadTitle: '上传选中文件',
                msgNo: '没有',
                msgCancelled: '取消',
                msgZoomTitle: '查看详情',
                msgZoomModalHeading: '详细预览',
                msgSizeTooLarge: '文件 "{name}" (<b>{size} KB</b>) 超过了允许大小 <b>{maxSize} KB</b>.',
                msgFilesTooLess: '你必须选择最少 <b>{n}</b> {files} 来上传. ',
                msgFilesTooMany: '选择的上传文件个数 <b>({n})</b> 超出最大文件的限制个数 <b>{m}</b>.',
                msgFileNotFound: '文件 "{name}" 未找到!',
                msgFileSecured: '安全限制，为了防止读取文件 "{name}".',
                msgFileNotReadable: '文件 "{name}" 不可读.',
                msgFilePreviewAborted: '取消 "{name}" 的预览.',
                msgFilePreviewError: '读取 "{name}" 时出现了一个错误.',
                msgInvalidFileType: '不正确的类型 "{name}". 只支持 "{types}" 类型的文件.',
                msgInvalidFileExtension: '不正确的文件扩展名 "{name}". 只支持 "{extensions}" 的文件扩展名.',
                msgUploadAborted: '该文件上传被中止',
                msgValidationError: '验证错误',
                msgLoading: '加载第 {index} 文件 共 {files} &hellip;',
                msgProgress: '加载第 {index} 文件 共 {files} - {name} - {percent}% 完成.',
                msgSelected: '{n} {files} 选中',
                msgFoldersNotAllowed: '只支持拖拽文件! 跳过 {n} 拖拽的文件夹.',
                msgImageWidthSmall: '宽度的图像文件的"{name}"的必须是至少{size}像素.',
                msgImageHeightSmall: '图像文件的"{name}"的高度必须至少为{size}像素.',
                msgImageWidthLarge: '宽度的图像文件"{name}"不能超过{size}像素.',
                msgImageHeightLarge: '图像文件"{name}"的高度不能超过{size}像素.',
                msgImageResizeError: '无法获取的图像尺寸调整。',
                msgImageResizeException: '错误而调整图像大小。<pre>{errors}</pre>',
                dropZoneTitle: '拖拽文件到这里 &hellip;',
                fileActionSettings: {
                    removeTitle: '删除文件',
                    uploadTitle: '上传文件',
                    indicatorNewTitle: '没有上传',
                    indicatorSuccessTitle: '上传',
                    indicatorErrorTitle: '上传错误',
                    indicatorLoadingTitle: '上传 ...'
                }
            };
        })(window.jQuery);
    }

    /**
     * 初始化fileinput控件（第一次初始化）
     */
    function initFileInput() {
        var control = $('#file-upload');
        control.fileinput({
            language: 'zh', // 设置语言
            uploadUrl: '/studyResource/uploadFile',       // 上传的地址
            maxFileCount: '50', // 最大上传个数
            allowedFileExtensions : ['jpg', 'png','gif'], // 接收的文件后缀
            enctype: 'multipart/form-data',
            showUpload: true, // 是否显示上传按钮
            showCaption: true,// 是否显示标题
            browseClass: "btn btn-info", //按钮样式
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
        });
    }

     $("#file-upload").on("filebatchuploadsuccess", function(event, data, previewId, index) {
         console.info("event: " + event + ", data: " + data + ", previewId: " + previewId + ", index: " + index)
        //上传成功的一系列操作 ，  比如一些 刷新图片列表
     });

    function over(){
        showLink = $(".showLink");
        shouName = $(".shouName");
        shouName.css("display","none") ;
        showLink.css("display","block") ;
    }

    function out(){
        showLink = $(".showLink");
        shouName = $(".shouName");
        shouName.css("display","block") ;
        showLink.css("display","none") ;
    }

    //========================
    var learnresources = {};
    learnresources.init = function () {
        init();
    };
    learnresources.removeNoticeModal = function(noticeId) {
        removeNoticeModal(noticeId);
    };
    learnresources.out = function(){
        out();
    };
    learnresources.over = function(){
        over();
    };
    return learnresources;
});
