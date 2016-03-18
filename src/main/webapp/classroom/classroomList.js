define([ 'toastr'], function (toastr) {
  function init() {
    initClick();
  }
  // 初始化点击事件
  function initClick() {
    $('[data-toggle="offcanvas"]').click(function () {
      $('.row-offcanvas').toggleClass('active')
    });
  }
  //=======
  var classroomList = {};
  classroomList.init = function () {
    init();
  };
  return classroomList;
});
