define(['layout'], function () {
    /**
     * 初始化菜单导航
     * @private
     */
    function _initNav() {
        $("#overview").on('click', function(){
            $(this).on("click", function () {
                var _text = '<h1>'+ $(this).html() +'<small>' + $(this).html() + '</small></h1>';
                $(".page-title").html(_text);

                // 更新页面
                _navOnclick(this);

                // 重置导航选中样式
                $(".hor-menu > ul li").removeClass("active");
                $(this).parent().addClass("active");
            });
        });
        $(".hor-menu > ul >>>> a").each(function (index, item) {
            $(this).on("click", function () {
                var _text = '<h1>'+ $($(this).parent().parent().parent().children()[0]).text() +'<small>' + $(this).html() + '</small></h1>';
                $(".page-title").html(_text);

                // 更新页面
                _navOnclick(this);

                // 重置导航选中样式
                $("#overview").parent().removeClass("active");
                $(".hor-menu > ul li").removeClass("active");
                $(this).parent().parent().parent().addClass("active");
                $(this).parent().addClass("active");
            });
        });

        _navOnclick($("#overview"));
    }
    function _navOnclick(dom) {
        $.get($(dom).attr("url"), function (data) {
            $('#mainContent').html(data);
        });
    }

    var index = {};
    index.init = function () {
        Layout.init();
        _initNav();
    };
    return index;
});
