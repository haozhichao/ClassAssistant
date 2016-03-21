define(['jquery'], function ($) {

  function init() {
    initClick();
    initClassRoomList();
  }

  //初始化，显示所有课堂
  function initClassRoomList(){
    var role = getRole();
    var html = [];
    //类型判断，产生不同的类型
    var url;
    if(role == 0){
      url = "/classRoom/getAllByStudent";
      html.push('<div><button id="add" type="button" class="btn btn-primary" style="margin-left: 20px;" data-toggle="modal" data-target="#addMyModal"><span class="glyphicon glyphicon-plus"></span>&nbsp;加入课堂</button></div>');
    }else{
      url = "/classRoom/getAllByTeacher";
      html.push('<div><button id="create" type="button" class="btn btn-primary" style="margin-left: 20px;" data-toggle="modal" data-target="#createMyModal"><span class="glyphicon glyphicon-plus"></span>&nbsp;创建课堂</button></div>');
    }

    $.ajax({
      type:"POST",
      url:url,
      dataType:"JSON",
      success:function(data){
        $.each(data,function(index,item){
            html.push('<div class="panel panel-info" id="'+item.id+'" style="width: 250px;height: 200px;background-color: #FFFFFF;margin-left: 20px;margin-top: 20px;float:left">');
            html.push('<div class="panel-heading"><a href="../index.html?id='+item.id+'">'+item.name+'</a></div>');
            html.push('<div style="height: 30px"><lable>人数:'+item.stunum+'</lable></div>');
            html.push('<div   style="height: 30px"><lable>创建时间:'+FormatDate(item.createrdate)+'</lable></div>');
            html.push('</div>');
        });

        $('#classroomList').html(html.join(''));

      }
    });

  }
  //按钮事件的绑定
  function initClick() {

    //创建课堂
    $("#createClassroom").click(function(){
        var name = $("#name").val();
      $.ajax({
        type:"POST",
        url:"/classRoom/createClassRoom",
        data:{name:name},
        dataType:"JSON",
        success:function(data){
          if(data>0){
            window.location.reload();//刷新当前页面.
          }else{
            alert("添加失败");
          }
        }
      });
    });
    //加入课堂
    $("#addClassroom").click(function(){
      var uuid = $("#uuid").val();
      $.ajax({
        type:"POST",
        url:"/classRoom/addClassRoom",
        data:{uuid:uuid},
        dataType:"JSON",
        success:function(data){
          if(data>0){
            window.location.reload();//刷新当前页面.
          }else if(data == -3){
            alert("你已在班级中");
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

  //初始化日期
  function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
  }

  //获取角色
  function  getRole(){
    //获取角色
    var role;
    $.ajax({
      type:"POST",
      async: false,
      url:"/classRoom/getRole",
      success:function(data){
          role = data;
      }
    });
    return role;
  }

  //=======
  var classroomList = {};
  classroomList.init = function () {
    init();
  };
  return classroomList;
});
