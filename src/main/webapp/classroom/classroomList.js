define(['jquery'], function ($) {
  function init() {
    initClick();
    initClassRoomList();
  }

  //初始化，显示所有课堂
  function initClassRoomList(){

    var html = [];
    $.ajax({
      type:"POST",
      url:"/classRoom/getAll",
      dataType:"JSON",
      success:function(data){
        $.each(data,function(index,item){
            html.push('<div id="'+item.id+'" style="width: 150px;height: 150px;background-color: #e4b9c0;margin-left: 20px;margin-top: 20px;float:left">');
            html.push('<div style="height: 30px"><lable><a href="../index.html?id='+item.id+'">'+item.name+'</a></lable></div>');
            html.push('<div style="height: 30px"><lable>人数:'+item.stunum+'</lable></div>');
            html.push('<div style="height: 30px"><lable>创建时间:'+FormatDate(item.createrdate)+'</lable></div>');
            html.push('</div>');
        });
        html.push('<div id="create" style="width: 150px;height: 150px;margin-left: 20px;float:left">');
        html.push('<img id="add" src="images/add.png" style = "margin-top: 20px; width: 150px; height: 150px; cursor: pointer;"  data-toggle="modal" data-target="#myModal"/>');
        html.push('</div>');
        $('#classroomList').html(html.join(''));
      }
    });

  }
  // 初始化点击事件
  function initClick() {

    //创建课堂
    $("#addClassroom").click(function(){
        var name = $("#name").val();
      $.ajax({
        type:"POST",
        url:"/classRoom/add",
        data:{name:name},
        dataType:"JSON",
        success:function(data){
          if(data>0){
            alert("添加成功");
            window.location.reload();//刷新当前页面.
          }else{
            alert("添加失败");
          }
        }
      });
    });

    $('[data-toggle="offcanvas"]').click(function () {
      $('.row-offcanvas').toggleClass('active')
    });
  }
  function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
  }
  //=======
  var classroomList = {};
  classroomList.init = function () {
    init();
  };
  return classroomList;
});
