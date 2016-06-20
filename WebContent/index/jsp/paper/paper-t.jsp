<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>知诸网</title>

<!-- Bootstrap Core CSS -->
<link	href="<c:url value='/index/bower_components/bootstrap/dist/css/bootstrap.min.css'/>"	rel="stylesheet">

<!-- MetisMenu CSS -->
<link	href="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.css'/>"	rel="stylesheet">

<!-- Timeline CSS -->
<link href="<c:url value='/index/dist/css/timeline.css'/>"	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='/index/dist/css/sb-admin-2.css'/>"	rel="stylesheet">

<!-- Morris Charts CSS -->
<link	href="<c:url value='/index/bower_components/morrisjs/morris.css'/>" rel="stylesheet">

<!-- Custom Fonts -->
<link	href="<c:url value='/index/bower_components/font-awesome/css/font-awesome.min.css'/>"	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<!-- jQuery -->
    <script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>" />
	<script src="<c:url value='/index/js/jquery-ui.min.js'/>"></script>
	<script src="<c:url value='/index/js/select-widget-min.js'/>"></script>
	<script src="<c:url value='/index/js/highcharts.js'/> "></script>
	<link rel="stylesheet" href="<c:url value='/index/css/paper-t.css'/>" />
</head>

<body style="background-color: white">
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">知诸网</a>
            </div>
            <!-- nav header -->

            <!-- /.退出 -->
            <ul class="nav navbar-top-links navbar-right pull-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        ${sessionScope.name }&nbsp;&nbsp;<i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> ${sessionScope.name }</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="<%=request.getContextPath() %>/LogoutServlet" target="_top"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">	
		                <!-- /功能 -->
		                <li><a href="#"><i class="fa fa-dashboard fa-fw"></i> 成绩分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a href="<c:url value='/TeacherGrowServlet?method=loadGrowStu'/>">学生成长轨迹图</a>
						</li>
						<li>
							<%-- <a href="<c:url value='/TeacherServlet?method=courseInfo&courseName=chinese'/>" target="main">语文</a> --%>
							<a href="<c:url value='/TeacherGrowServlet?method=loadGradeStu'/>">学生学习履历图</a>
						</li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
						试卷分析<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=语文'/>"
							>语文</a></li>
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=数学'/>"
							>数学</a></li>
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=英文'/>"
							>英语</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>知识点分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="<c:url value='/RelationServlet?method=relation&course=语文'/>"
							>语文</a></li>
						<li><a href="<c:url value='/RelationServlet?method=relation&course=数学'/>"
							>数学</a></li>
						<li><a href="<c:url value='/RelationServlet?method=relation&course=英文'/>"
							>英语</a></li>
					</ul></li>
				 <li>
					<a href="#"><i class="fa fa-files-o fa-fw"></i> 聚类分析<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li>
								<a href="<c:url value='/ClusterServlet?method=load&subject=语文'/>" >语文</a>
                            </li>
							<li>
                                <a href="<c:url value='/ClusterServlet?method=load&subject=数学'/>" >数学</a>
                            </li>
                            <li>
                                <a href="<c:url value='/ClusterServlet?method=load&subject=英文'/>" >英文</a>
                            </li>
                        </ul>
                </li>
                <li>
					<a href="#"><i class="fa fa-files-o fa-fw"></i> 知识地图<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li>
								<a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=语文'/>">语文</a>
                            </li>
							<li>
                                <a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=数学'/>">数学</a>
                            </li>
                            <li>
                                <a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=英文'/>">英文</a>
                            </li>
                        </ul> 
                </li>
		            </ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
			<div class="row" style="margin-top:20px;">
				<div class="col-lg-2"></div>
				<div class="div diff col-lg-3">
					<div class="col-xs-3">
						<i class="glyphicon glyphicon-edit p pp"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="ptop p">
							难度：
							<p id="hardRate">
								0<c:out value="${hardrate}">0</c:out>
							</p>
						</div>
						<div class="p">
							<div class="p" id="hardString"><c:out value="${hardString}">未选择试卷</c:out></div>
						</div>
					</div>
				</div>
				<div class="col-lg-1"></div>
				<div class="div vali col-lg-3">
					<div class="col-xs-3">
						<i class="glyphicon glyphicon-tags p pp"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="ptop p">
							信度：
							<p id="believeRate">
								0<c:out value="${believerate}">0</c:out>
							</p>
						</div>
						<div class="p" id="believeString"><c:out value="${believeString}">未选择试卷</c:out></div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<div class="row">
						<div class="col-lg-5"></div>
						<div class="sec1 col-lg-7">
							<div class="divp">选择年级</div>
							<!-- 年级下拉选择框 -->
							<form action="" method="get" class="form">
								<select name="drop" class="ui-select" id="grade-select">
									<option value="选择年级">选择年级</option>
									<c:forEach items="${gradeList}" var="grade">
										<option value="${grade}">${grade}</option>
									</c:forEach>
								</select>
							</form>
						</div>
					</div>
				</div>
			
				<div class="sec2 col-xs-6" id="formdiv2">
					<div class="divp">选择试卷</div>
					<!-- 试卷下拉选择框 -->
					<form action="" method="get" class="form" id="form2">
						<select name="drop" class="ui-select" id="test-select">
							<option>选择试卷</option>
							
						</select>
					</form>
				</div>
			</div>
			<div id="container"></div>
			<div class="info">
				<!-- 试卷信息 -->
				<table class="table table-bordered" id="tab1">
					<caption class="text-center title">错题分析</caption>
					<thead>
						<tr>
							<th class="text-center" id="tabhead">编号</th>
							<th class="text-center papertitle" id="tabhead">试卷题目</th>
							<th class="text-center" id="tabhead">做题人数</th>
							<th class="text-center" id="tabhead">正确人数</th>
							<th class="text-center" id="tabhead">错误率</th>
						</tr>
					</thead>
					<tbody>
						<%-- <c:forEach items="${vQuestionList}" var="vQuestion">
							<tr>
								<td class="td3">${vQuestion.sequence}</td>
								<td class="tdp">
									${vQuestion.subject}</br></br>
									(A): ${vQuestion.choiceA}</br>
									(B): ${vQuestion.choiceB}</br>
									(C): ${vQuestion.choiceC}</br>
									(D): ${vQuestion.choiceD}</br>
								</td>
								<td class="td3">${vQuestion.num}</td>
								<td class="td3">${vQuestion.successNum}</td>
								<td class="td3">${vQuestion.rate}%</td>
							</tr>
						</c:forEach> --%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	
	<script>
	var xAxisData = [];
	var seriesData = [];
	//下拉选择框
	$(document).ready(function(){
		//年级下拉框
		$("#grade-select").selectWidget({
			change       : function (changes) {
				//删除原来下拉选择框
				if(changes != "选择年级"){
				var form = document.getElementById("form2");
				form.parentNode.removeChild(form);}
				
				var grade =  $("#grade-select option:selected").text();
				subject = "${selectSubject}";
				
				 if("选择年级"!=grade){
				 var ajax = new XMLHttpRequest(); //得到ajax对象
				   
				 //设置地址和 方法为post
				 var method = "post";
				 var url ="<c:url value='/TeacherPaperServlet?method=loadTestList'/>&time="+new Date().getTime();
				 //alert(url);
				 ajax.open(method, url);
					 
				 //设置请求头
				 ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
				 //发送请求
				 var content="grade="+grade+"&subject="+subject;
				 ajax.send(content);
				 
				 ajax.onreadystatechange = function(){
					 if(ajax.readyState==4 && ajax.status==200){
						 
						 var jsonJava= ajax.responseText;  //这个json是Java书写的json
						 var json = eval("("+jsonJava+")");
						 
						 var div = document.getElementById("formdiv2");
						 var form2 = document.createElement("form");
						 var select = document.createElement("select");
						 
						 var option = document.createElement("option");
    					 option.innerHTML="选择试卷";
						 select.appendChild(option);
						 
						 for(var i=0;i<json.length;i++){
							var testId=json[i]['pk_test_main_ID'];
							 var testType = json[i]['testType'];  //获得试卷类型
							 switch (testType) {
								 case 1:
									 testname = "T1";
									 break;
								 case 2:
									 testname = "T2";
									 break;
								 case 3:
									 testname = "T3";
									 break;
								 case 4:
									 testname = "T4";
									 break;
								 case 5:
									 testname = "S1";
									 break;
								 case 6:
									 testname = "T6";
									 break;
								 case 7:
									 testname = "T7";
									 break;
								 case 8:
									 testname = "T8";
									 break;
								 case 9:
									 testname = "T9";
									 break;
								 default :
									 testname = "T2";
							 }
							var option = document.createElement("option");
	    					option.innerHTML=testname;
							option.value=testId;  //给option的属性value赋值
							select.appendChild(option);
						 }
						 
							 
						 
						//添加结点
						select.setAttribute("class","ui-select");
						select.setAttribute("name","drop1");
						select.setAttribute("id","test-select");
						form2.setAttribute("id","form2");
						form2.setAttribute("class","form");
						form2.setAttribute("method","get");
						form2.appendChild(select);
						div.appendChild(form2);
						
						$(document).ready(function(){
							$("#test-select").selectWidget({
								change       : function (changes) {
									//清空表格的内容			
									var tab = document.getElementById("tab1");
									var trs = tab.getElementsByTagName("tr");
									for(var i=trs.length-1;i>0;i--){
										tab.deleteRow(i);
									}
									
									var testID = $("#test-select option:selected").val();
									var index=document.getElementById("grade-select").selectedIndex;//获取当前选择项的索引.
									var grade=document.getElementById("grade-select").options[index].innerHTML;//获取当前选择项的值.	
									var grade = $("#grade-select option:selected").text();
									subject = "${selectSubject}";
									if("选择试卷"!=testID){
										var ajax = new XMLHttpRequest(); //得到ajax对象
										   
										 //设置地址和 方法为post
										 var method = "post";
										 var url ="<c:url value='/TeacherPaperServlet?method=loadGrade'/>&time="+new Date().getTime();
										 //alert(url);
										 ajax.open(method, url);
											 
										 //设置请求头
										 ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
										 //发送请求
										 var content="testID="+testID;
										 ajax.send(content);
										 
										 ajax.onreadystatechange = function(){
											
											 if(ajax.readyState==4 && ajax.status==200){
												 var jsonJava= ajax.responseText;  //这个json是Java书写的json
												 var jsonjs = eval("("+jsonJava+")");
												 
												 //初始化
												 xAxisData = [];
												 seriesData = [];
												 
												 //读取数据
												 var vQuestionKeywordList=jsonjs[0]['vQuestionKeywordList'];
												 for(var i=0;i<vQuestionKeywordList.length;i++){
													 var keyword=vQuestionKeywordList[i]['keyword'];
													 xAxisData.push(keyword);
												 }
												 for(var i=0;i<vQuestionKeywordList.length;i++){
													 var wrong = vQuestionKeywordList[i]['wrong'];
													 seriesData.push(parseFloat(wrong*100));
												 }
												 pic(); //给柱状图赋值
												 
												 //得到难度的结点
												 var hardRateEle=document.getElementById("hardRate");
												 var hardStringEle = document.getElementById("hardString");
												 
												 var hardRate=jsonjs[0]['hardrate'];
												 var hardString = jsonjs[0]['hardString'];
												 
												 //赋值
												 hardRateEle.innerHTML = hardRate;
												 hardStringEle.innerHTML = hardString;
												 
												 //得到信度的结点
												 var believeRateEle = document.getElementById("believeRate");
												 var believeStringEle = document.getElementById("believeString");
												 
												 var believeRate = jsonjs[0]['believerate'];
												 var believeString = jsonjs[0]['believeString'];
												 
												 //赋值
												 believeRateEle.innerHTML = believeRate;
												 believeStringEle.innerHTML = believeString;
												 
												 
												 
												
												 
												 var vQuestion = jsonjs[0]['vQuestionList'];
												 for(var i=0;i<vQuestion.length;i++){
													 var tr = tab.insertRow();
													 var td1= tr.insertCell();
													 var td2= tr.insertCell();
													 var td3= tr.insertCell();
													 var td4= tr.insertCell();
													 var td5= tr.insertCell();
													 
													 td1.id="td3";
													 td2.className="tdp";
													 td3.id="td3";
													 td4.id="td3";
													 td5.id="td3";
													 
													 td1.innerHTML = vQuestion[i]['sequence'];
													 td2.innerHTML = vQuestion[i]['subject']+"</br></br>(A):"+vQuestion[i]['choiceA']+"</br>(B):"+vQuestion[i]['choiceB']+"</br>(C): "+vQuestion[i]['choiceC']+"</br>(D): "+vQuestion[i]['choiceD'];
													 td3.innerHTML = vQuestion[i]['num'];
												 	 td4.innerHTML = vQuestion[i]['successNum'];
												 	 td5.innerHTML = vQuestion[i]['rate'];	
												 	 
												 	 
												 }
											 }
										 }
									}
								},
								effect       : "slide",
								keyControl   : true,
								speed        : 200,
								scrollHeight : 250
							});
							
						});
						 
					 }
				 }
			  }
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		});
		
		//试卷下拉框
		$("#test-select").selectWidget({
			change       : function (changes) {
				return changes;
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		});
		
	});
		
		
		/* document.getElementById("grade-select").onchange=function(){
			
			var testElement = document.getElementById("test-select");
			testElement.options.length=1;
			
			var grade =  $("#grade-select option:selected").text();
			subject = "${selectSubject}";
			
			 if("选择年级"!=grade){
			 var ajax = new XMLHttpRequest(); //得到ajax对象
			   
			 //设置地址和 方法为post
			 var method = "post";
			 var url ="<c:url value='/TeacherPaperServlet?method=loadTestList'/>&time="+new Date().getTime();
			 //alert(url);
			 ajax.open(method, url);
				 
			 //设置请求头
			 ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
			 //发送请求
			 var content="grade="+grade+"&subject="+subject;
			 ajax.send(content);
			 
			 ajax.onreadystatechange = function(){
				 if(ajax.readyState==4 && ajax.status==200){
					 var jsonJava= ajax.responseText;  //这个json是Java书写的json
					 var json = eval("("+jsonJava+")");
					 //alert(jsonJava);
					 for(var i=0;i<json.length;i++){
						var testId=json[i]['pk_test_main_ID'];
						 var testType = json[i]['testType'];  //获得试卷类型
						 switch (testType) {
							 case 1:
								 testname = "T1";
								 break;
							 case 2:
								 testname = "T2";
								 break;
							 case 3:
								 testname = "T3";
								 break;
							 case 4:
								 testname = "T4";
								 break;
							 case 5:
								 testname = "S1";
								 break;
							 case 6:
								 testname = "T6";
								 break;
							 case 7:
								 testname = "T7";
								 break;
							 case 8:
								 testname = "T8";
								 break;
							 case 9:
								 testname = "T9";
								 break;
							 default :
								 testname = "T2";
						 }
						var option = document.createElement("option");
    					option.innerHTML=testname;
						option.value=testId;  //给option的属性value赋值
    					testElement.appendChild(option);
					 }
				 }
			 }
		  }
		}  */
		
		document.getElementById("test-select").onchange=function(){
			//清空表格的内容			
			var tab = document.getElementById("tab1");
			var trs = tab.getElementsByTagName("tr");
			for(var i=trs.length-1;i>0;i--){
				tab.deleteRow(i);
			}
			
			var testID = $("#test-select option:selected").val();
			var index=document.getElementById("grade-select").selectedIndex;//获取当前选择项的索引.
			var grade=document.getElementById("grade-select").options[index].innerHTML;//获取当前选择项的值.	
			var grade = $("#grade-select option:selected").text();
			subject = "${selectSubject}";
			if("选择试卷"!=testID){
				var ajax = new XMLHttpRequest(); //得到ajax对象
				   
				 //设置地址和 方法为post
				 var method = "post";
				 var url ="<c:url value='/TeacherPaperServlet?method=loadGrade'/>&time="+new Date().getTime();
				 //alert(url);
				 ajax.open(method, url);
					 
				 //设置请求头
				 ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
				 //发送请求
				 var content="testID="+testID;
				 ajax.send(content);
				 
				 ajax.onreadystatechange = function(){
					
					 if(ajax.readyState==4 && ajax.status==200){
						 var jsonJava= ajax.responseText;  //这个json是Java书写的json
						 var jsonjs = eval("("+jsonJava+")");
						 
						 //初始化
						 xAxisData = [];
						 seriesData = [];
						 
						 //读取数据
						 var vQuestionKeywordList=jsonjs[0]['vQuestionKeywordList'];
						 for(var i=0;i<vQuestionKeywordList.length;i++){
							 var keyword=vQuestionKeywordList[i]['keyword'];
							 xAxisData.push(keyword);
						 }
						 for(var i=0;i<vQuestionKeywordList.length;i++){
							 var wrong = vQuestionKeywordList[i]['wrong'];
							 seriesData.push(parseFloat(wrong*100));
						 }
						 pic(); //给柱状图赋值
						 
						 //得到难度的结点
						 var hardRateEle=document.getElementById("hardRate");
						 var hardStringEle = document.getElementById("hardString");
						 
						 var hardRate=jsonjs[0]['hardrate'];
						 var hardString = jsonjs[0]['hardString'];
						 
						 //赋值
						 hardRateEle.innerHTML = hardRate;
						 hardStringEle.innerHTML = hardString;
						 
						 //得到信度的结点
						 var believeRateEle = document.getElementById("believeRate");
						 var believeStringEle = document.getElementById("believeString");
						 
						 var believeRate = jsonjs[0]['believerate'];
						 var believeString = jsonjs[0]['believeString'];
						 
						 //赋值
						 believeRateEle.innerHTML = believeRate;
						 believeStringEle.innerHTML = believeString;
						 
						 
						 
						
						 
						 var vQuestion = jsonjs[0]['vQuestionList'];
						 for(var i=0;i<vQuestion.length;i++){
							 var tr = tab.insertRow();
							 var td1= tr.insertCell();
							 var td2= tr.insertCell();
							 var td3= tr.insertCell();
							 var td4= tr.insertCell();
							 var td5= tr.insertCell();
							 
							 td1.className="td3";
							 td2.className="tdp";
							 td3.className="td3";
							 td4.className="td3";
							 td5.className="td3";
							 
							 td1.innerHTML = vQuestion[i]['sequence'];
							 td2.innerHTML = vQuestion[i]['subject']+"</br></br>(A):"+vQuestion[i]['choiceA']+"</br>(B):"+vQuestion[i]['choiceB']+"</br>(C): "+vQuestion[i]['choiceC']+"</br>(D): "+vQuestion[i]['choiceD'];
							 td3.innerHTML = vQuestion[i]['num'];
						 	 td4.innerHTML = vQuestion[i]['successNum'];
						 	 td5.innerHTML = vQuestion[i]['rate'];	
							 
							 
						 }
						 
					 }
				 }
				
				
			}
			
		}
 	 
		
		
	</script>
	

	<script src="<c:url value='/index/js/paper-t.js'/> "></script>
	<!-- /#wrapper -->

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<c:url value='/index/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.js'/>"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="<c:url value='/index/bower_components/raphael/raphael-min.js'/>"></script>
	<script
		src="<c:url value='/index/bower_components/morrisjs/morris.min.js'/>"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/index/dist/js/sb-admin-2.js'/>"></script>

</body>

</html>
