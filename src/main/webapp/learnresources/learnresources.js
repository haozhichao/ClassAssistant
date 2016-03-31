define(['toastr', 'bootstrapValidator'], function (toastr) {

    function init() {
        //initNoticeList(); // 初始化列表
        //initButton();     // 初始化点击事件
    }




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
