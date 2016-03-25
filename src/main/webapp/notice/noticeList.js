/**
 * Created by haozhichao on 1/6/2016.
 */
define(['toastr', 'bootstrapValidator'], function (toastr) {
    var role = 0; // 当前角色，初始为学生
    function init() {
        initCurrentRole();// 初始化当前角色
        initPageDom();    // 初始化页面Dom元素
        initNoticeList(); // 初始化列表
        initButton();     // 初始化点击事件
    }

    /**
     * 初始公告的列表
     */
    function initNoticeList() {
        noticeList();
    }

    /**
     * 初始化页面Dom元素
     */
    function initPageDom() {
        var html = [];
        if(role == 1) { // 如果当前角色是老师，则显示发布公告按钮
            html.push('<div class="pull-right">');
            html.push('<button class="btn btn-info" id="addNotice">发布公告</button>');
            html.push('</div>');
            $('#releaseDiv').html(html.join(''));
            // 发布公告按钮
            initAddNotice();
        }
    }

    /**
     * 获取当前角色
     */
    function initCurrentRole() {
        $.ajax({
            type:"POST",
            async:false,
            url:"/classRoom/getRole",
            dataType:"JSON",
            success:function(data){
                role = data;
            }
        });
    }
    /**
     * 刷新公告列表
     */
    function refeshNoticeList() {
        noticeList();
    }

    /**
     * 公告的列表
     */
    function noticeList() {
        var html = [];
        $.ajax({
            type:"POST",
            url:"/notice/list",
            dataType:"JSON",
            success:function(data){
                if(data.length == 0) {
                    html.push('暂无公告');
                } else {
                    $.each(data,function(index,item){
                        html.push('<div class="panel panel-default" id="notice_'+ item.id +'">');
                        html.push('<div class="panel-heading">'+ item.title); // 标题
                        if(role == 1) {
                            html.push('<button type="button" class="close" aria-hidden="true" onclick="removeNoticeModal('+ item.id +')">×</button>');
                        }
                        html.push('</div>');
                        html.push('<div class="panel-body">'+ item.content +'</div>');    // 内容
                        html.push('<div class="panel-footer">发布人：'+ item.teachername +'&nbsp;&nbsp;'+ new Date(item.releasedate).Format("yyyy-MM-dd HH:mm:ss") +'</div>');  // 发布人、发布时间
                        html.push('</div>');
                    });
                }
                $('#noticeList').html(html.join(''));
            }
        });
    }
    /**
     * 添加公告
     */
    function addNotice() {
        var title = $("#title").val();
        var content = $("#content").val();
        $.ajax({
            type:"POST",
            url:"/notice/add",
            data:{title:title, content:content},
            dataType:"JSON",
            success:function(data){
                if(data == 1) {
                    toastr.success("发布成功!");
                    $('#addButton').removeAttr("disabled");// 让提交按钮生效
                    $("#addModal").modal('hide');
                    refeshNoticeList();
                } else{
                    toastr.error("发布失败，请重新发布!");
                }
            }
        });
    }

    /**
     * 删除公告提示框
     * @param noticeId
     */
    function removeNoticeModal(noticeId){
        $("#removeModal").modal("show");
        $("#noticeIdInput").val(noticeId);
    }

    /**
     * 删除公告
     */
    function removeNotice() {
        var noticeId = $("#noticeIdInput").val();
         $.ajax({
             type:"POST",
             url:"/notice/remove",
             data:{noticeId: noticeId},
             dataType:"JSON",
             success:function(data){
                 if(data == 1) {
                     toastr.success("删除成功!");
                     $("#removeModal").modal('hide');
                     refeshNoticeList();
                 } else{
                    toastr.error("删除失败，请重新操作!");
                 }
             }
         });
    }
    /**
     * 初始化点击事件
     */
    function initButton() {
        // 添加按钮
        initAddButton();

        // 删除按钮
        initRemoveButton();
    }

    /**
     * 添加事件
     */
    function initAddButton() {
        $("#addButton").click(function(){
            $("#addForm").data('bootstrapValidator').validate(); // 让校验生效
            if( $("#addForm").data('bootstrapValidator').isValid()) {
                $('#addButton').attr("disabled","disabled");// 让提交按钮失效
                addNotice();
            }
        });
    }
    function initAddNotice() {
        $("#addNotice").on("click", function () {
            $("#addModal").modal("show");
            isValidator(); // 初始化校验
        });
    }
    function initRemoveButton() {
        $("#removeButton").on("click", function () {
            removeNotice();
        });
    }

    /*表单数据，非空校验*/
    function isValidator() {
        var validator = {
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                title: {
                    validators: {
                        notEmpty: {
                            message: '公告标题不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 255,
                            message: '输入长度1~255个字符'
                        }
                    }
                },
                content: {
                    validators: {
                        notEmpty: {
                            message: '公告内容不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 255,
                            message: '输入长度1~255个字符'
                        }
                    }
                }
            }
        };
        $('#addForm').bootstrapValidator(validator);
    }
    //========================
    var notice = {};
    notice.init = function () {
        init();
    };
    notice.removeNoticeModal = function(noticeId) {
        removeNoticeModal(noticeId);
    };
    return notice;
});
