define(['jquery'], function ($) {

  function init() {

    //添加按钮动态生成
    initAddButtn();

    //课堂列表动态生成
    initClassRoomList();

    //按钮绑定事件
    initClick();
  }

  //按钮事件的绑定
  function initClick() {

    $('[data-toggle="offcanvas"]').click(function () {
      $('.row-offcanvas').toggleClass('active')
    });

    //创建课堂点击事件
    createClassroomClick();

    //加入课堂点击事件
    addClassroomClick();

    //更新个人信息数据回显
    updateMessageClick();

    //修改信息
    updateClick();


  }

  //初始化，显示所有课堂
  function initClassRoomList(){
    var role = getRole();
    var html = [];
    //类型判断，产生不同的类型
    var url;
    if(role == 0){
      url = "/classRoom/getAllByStudent";
    }else{
      url = "/classRoom/getAllByTeacher";
    }
    $.ajax({
      type:"POST",
      url:url,
      dataType:"JSON",
      success:function(data){
        $.each(data,function(index,item){
          html.push('<div class="panel panel-info" id="'+item.id+'" style="width: 250px;height: 200px;background-color: #FFFFFF;margin-left: 20px;margin-top: 20px;float:left">');
          html.push('<div class="panel-heading"><a href="/classRoom/classRoom2Room?id='+item.id+'">'+item.name+'</a></div>');
          html.push('<div style="height: 30px"><lable>人数:'+item.stunum+'</lable></div>');
          html.push('<div   style="height: 30px"><lable>创建时间:'+FormatDate(item.createrdate)+'</lable></div>');
          html.push('</div>');
        });

        $('#classroomList').html(html.join(''));

      }
    });

  }

  function initAddButtn(){
    var html = [];
    var role = getRole();
    //类型判断，产生不同的类型
    if(role == 0){
      html.push('<button id="add" type="button" class="btn btn-primary" style="margin-left: 20px;" data-toggle="modal" data-target="#addMyModal"><span class="glyphicon glyphicon-plus"></span>&nbsp;加入课堂</button>');
    }else{
      html.push('<button id="create" type="button" class="btn btn-primary" style="margin-left: 20px;" data-toggle="modal" data-target="#createMyModal"><span class="glyphicon glyphicon-plus"></span>&nbsp;创建课堂</button> ');
    }
    $("#addButtn").html(html.join(''));
  }

  function updateClick(){
    //修改信息
    $("#update").click(function () {
      var role = getRole();
      var url;
      var data;
      if(role == 0){
        url="/student/update";
        data = {
          username:$('#username').val(),
          stuid:$("#stuid").val(),
          school:$("#school").val(),
          telpthone:$("#telpthone").val(),
          email:$("#email").val(),
          persign:$("#persign").val(),
        };
      }else{
        url = "/teacher/update";
        data = {
          username:$('#username').val(),
          telphone:$("#telpthone").val(),
          email:$("#email").val(),
          persign:$("#persign").val(),
        };
      }
      $.ajax({
        type:"POST",
        url:url,
        dataType:"JSON",
        data:data,
        success:function(data){
          if(data>0){
            alert("修改成功");
          }else{
            alert("修改失败");
          }
        }
      });
    });
  }

  function updateMessageClick(){
    //更新个人信息数据回显
    $('#updateMessage').click(function () {
      var html = [];
      var role = getRole();
      var url;
      if(role == 0){
        url="/student/getStudent";
        html.push('<label for="username">用户名</label>' +
            '<input type="text" name="content" class="form-control" id="username" />' +
            '<label for="stuid">学号</label>' +
            '<input type="text" class="form-control" id="stuid" />' +
            '<label for="school">学校</label>' +
            '<input type="text" class="form-control" id="school" />' +
            '<label for="telpthone">手机</label>' +
            '<input type="text" class="form-control" id="telpthone" />' +
            '<label for="email">邮箱</label>' +
            '<input type="text" class="form-control" id="email" />' +
            '<label for="persign">个性签名</label>' +
            '<textarea class="form-control" rows="3" id="persign" ></textarea>');
      }else{
        url = "/teacher/getTeacher";
        html.push('<label for="username">用户名</label>' +
            '<input type="text" name="content" class="form-control" id="username" />' +
            '<label for="telphone">手机</label>' +
            '<input type="text" class="form-control" id="telpthone" />' +
            '<label for="email">邮箱</label>' +
            '<input type="text" class="form-control" id="email" />' +
            '<label for="persign">个性签名</label>' +
            '<textarea class="form-control" rows="3" id="persign" ></textarea>');
      }
      $("#modalBody").html(html.join(''));
      $.ajax({
        type:"POST",
        async: false,
        url:url,
        dataType:"JSON",
        success:function(data){
          $('#username').val(data.username) ;
          //角色判断
          if(role == 0){
            $("#stuid").val(data.stuid);
            if(data.stuid != null){
              $('#stuid').attr("disabled",true);
            }
            $("#school").val(data.school);
            $("#telpthone").val(data.telpthone);
          }else{
            $("#telpthone").val(data.telphone);
          }
          $("#email").val(data.email);
          $("#persign").text(data.persign);
        }
      });
    });
  }

  function createClassroomClick(){
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
            $("#addMyModal").modal('hide');
            //局部刷新
            initClassRoomList();
            //window.location.reload();//刷新当前页面.
          }else{
            alert("添加失败");
          }
        }
      });
    });
  }

  function addClassroomClick(){
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
            $("#createMyModal").modal('hide');
            initClassRoomList();
          }else if(data == -3){
            alert("你已在班级中");
            $("#createMyModal").modal('hide');
            initClassRoomList();
          }else{
            alert("添加失败");
          }
        }
      });
    });
  }


  //初始化日期j
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
