define(['toastr', 'bootstrapValidator'], function (toastr) {

    function init() {
        initNoticeList(); // 初始化列表
        initButton();     // 初始化点击事件
    }





    //========================
    var learnresources = {};
    learnresources.init = function () {
        init();
    };
    learnresources.removeNoticeModal = function(noticeId) {
        removeNoticeModal(noticeId);
    };
    return learnresources;
});
