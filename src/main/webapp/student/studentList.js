/**
 * Created by haozhichao on 1/6/2016.
 */
define(['toastr', 'bootstrapValidator', 'boottable','boottable_zh_cn'], function (toastr) {

    function init() {
        initStudentList(); // 初始化列表
        initButton();     // 初始化点击事件
    }

    /**
     * 初始成员的列表
     */
    function initStudentList() {
        studentList();
    }

    /**
     * 刷新成员列表
     */
    function refeshNoticeList() {
        studentList();
    }

    /**
     * 成员的列表
     */
    function studentList() {
        initTable();
    }

    // 成员表格字段
    var studentColumns = [
        {
            field: 'name',
            title: '姓名',
            width: '11%',
            align: 'center',
            valign: 'middle'
        },
        {
            field: 'school',
            title: '学校',
            width: '11%',
            align: 'center',
            valign: 'middle'
        },
        {
            field: 'stuid',
            title: '学号',
            width: '11%',
            align: 'center',
            valign: 'middle'
        },
        {
            field: 'email',
            title: '邮箱',
            width: '11%',
            align: 'center',
            valign: 'middle'
        },
        {
            field: 'telpthone',
            title: '联系电话',
            width: '11%',
            align: 'center',
            valign: 'middle'
        },
        {
            field: 'id',
            title: '学生id',
            visible: false
        }
    ];
    // 列表相关的方法
    function initTable() {
        $("#studentList").bootstrapTable({
            // studentId:studentId,
            method: 'GET',
            url: '/student/getByPage?classRoomId=' + 21,
            cache: false,
            striped: true,
            search: false,
            clickToSelect: true,
            pagination: true,
            pageNumber: 1,
            pageSize: 5,
            pageList: "[5, 10, 20]",
            locale: 'zh-CN',
            sidePagination: 'server',
            columns: studentColumns,
            queryParams: getAddPageParam,
            responseHandler: responseHandler
        });
    }
    /**
     * 分页查询参数处理
     * @param params
     * @returns {{pageIndex: *, pageSize: (number|*), sortField: *, sortOrder: (boolean|*|string|Array)}}
     * @private
     */
    function getAddPageParam(params) {
        var _params = {
            pageIndex: (params.offset) / (params.limit),
            pageSize: params.limit,
            sortField: (params.sort == undefined) ? "" : params.sort,
            sortOrder: params.order,
        }
        // _params.productId = this.productId;
        return _params;
    }
    /**
     * 分页加载回调函数，处理返回数据
     * @param data
     * @returns {{total: *, rows: *}}
     * @private
     */
    function responseHandler(data) {
        var _data = data;
        return {
            total: _data.totalRecord,
            rows: _data.data
        }
    }

    /**
     * 初始化按钮事件
     */
    function initButton(){
        $('#export').click(function(){
            exportReportMore();
        });
    }

    //导出报表
    function exportReportMore(){
        //以表单的形式提交，设置请求表头，浏览器可以认为是下载
        var postForm = document.getElementById("exportForm");
        postForm.method = "post";
        postForm.action ="/export/export?pro=student&list="+21;
        postForm.submit();

    }
    //========================
    var student = {};
    student.init = function () {
        init();
    };
    return student;
});
