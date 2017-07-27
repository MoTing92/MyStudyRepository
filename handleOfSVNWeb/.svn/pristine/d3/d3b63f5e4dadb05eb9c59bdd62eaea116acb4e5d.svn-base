<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form id="svnList" class="form-inline definewidth m20" action="svn/listRepository" method="get">
    菜单名称：
    <input type="text" name="httpPath" id="httpPath"class="abc input-default" placeholder="检出地址"><br>
    <input type="text" name="username" id="username"class="abc input-default" placeholder="用户名"><br>
    <input type="text" name="password" id="password"class="abc input-default" placeholder="密码"><br> 
    <button type="button" class="btn btn-primary" id="list">查询</button>&nbsp;&nbsp; 
    <button type="button" class="btn btn-success" id="addnew">创建版本库</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>选择</th>
        <th>名字</th>
        <th>检出命令</th>
        <th>状态</th>
    </tr>
    </thead>
</table>

</body>
<script type="text/javascript">
    $(function () {
    	$("#list").click(function(){
    		
    		alter("dhj");
			$("#svnList").submit(function (date) {
				for (var i = 0; i < date.length; i++) {
					alter(date[i]);
				}
			});
	 });

		$('#addnew').click(function(){

				window.location.href="add.html";
		 });


    });
	
</script>
</html>
