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
<link href="<c:url value='/index/bower_components/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.css'/>" rel="stylesheet">

<!-- Timeline CSS -->
<link href="<c:url value='/index/dist/css/timeline.css'/>" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='/index/dist/css/sb-admin-2.css'/>" rel="stylesheet">

<!-- Morris Charts CSS -->
<link	href="<c:url value='/index/bower_components/morrisjs/morris.css'/>"	rel="stylesheet">

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
<script src="<c:url value='/index/js/highcharts.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/index/css/paper-s.css'/>" />
<link rel="stylesheet" href="<c:url value='/index/css/modal.css'/>" />
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
                       ${sessionScope.sname }&nbsp;&nbsp;<i class="fa fa-caret-down"></i>
                   </a>
                   <ul class="dropdown-menu dropdown-user">
                       <li><a href="#"><i class="fa fa-user fa-fw"></i> ${sessionScope.sname }</a>
                       </li>
                       <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${sessionScope.sID }</a>
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
		                <li>
							<a href="#"><i class="fa fa-dashboard fa-fw"></i> 成绩分析<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<%-- <a href="<c:url value='/StudentServlet?method=allInfo&courseName=sum'/>" target="main">成长轨迹图</a> --%>
									<a href="<c:url value='/StudentGrowServlet?method=loadPr'/>">个人成长轨迹图</a>
		                        </li>
		                        <li>
		                            <%-- <a href="<c:url value='/StudentServlet?method=courseInfo&courseName=chinese'/>" target="main">学习履历图</a> --%>
		                        	<a href="<c:url value='/StudentGrowServlet?method=loadStuYear'/>">个人学习履历图</a>
		                        </li>
		                    </ul>
		                </li>
		                <li>
							<a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 试卷分析<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li>
										<a href="<c:url value='/StudentPaperServlet?method=loadGradeList&subject=语文'/>">语文</a>
		                            </li>
									<li>
		                                <a href="<c:url value='/StudentPaperServlet?method=loadGradeList&subject=数学'/>">数学</a>
		                            </li>
		                            <li>
		                                <a href="<c:url value='/StudentPaperServlet?method=loadGradeList&subject=英文'/>">英语</a>
		                            </li>
		                        </ul>
		                </li>                                                
		                <li>
							<a href="#"><i class="fa fa-sitemap fa-fw"></i> 知识点分析<span
								class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="<c:url value='/RelationServlet?method=relation&course=语文'/>"
										>语文</a></li>
									<li><a href="<c:url value='/RelationServlet?method=relation&course=数学'/>"
										>数学</a></li>
									<li><a href="<c:url value='/RelationServlet?method=relation&course=英文'/>"
										>英语</a></li>
								</ul>
		                </li>
		                <li>
							<a href="#"><i class="fa fa-files-o fa-fw"></i> 聚类分析<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li>
										<a href="<c:url value='/ClusterServlet?method=load&subject=语文'/>">语文</a>
		                            </li>
									<li>
		                                <a href="<c:url value='/ClusterServlet?method=load&subject=数学'/>">数学</a>
		                            </li>
		                            <li>
		                                <a href="<c:url value='/ClusterServlet?method=load&subject=英文'/>">英文</a>
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
		                <li>
							<a href="#"><i class="glyphicon glyphicon-download"></i> 导出履历<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<c:forEach items="${sessionScope.gradeList}" var="grade">
							           <li>    
										<a href="#" data-toggle="modal" data-target="#myModal" data-backdrop="false"  class="selectGrade">${grade.name}</a>
		                               </li>
  					            	</c:forEach>	
		                        </ul> 
		                </li>
		                <!-- 模态框（Modal） -->
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hide();">
											&times;
										</button>
										<h4 class="modal-title" id="myModalLabel">
											导出PDF
										</h4>
									</div>
									<div class="modal-body text-center">
										<p id="yn">是否导出该年级的PDF ?</p>
										<a id="kaka" data-dismiss="modal" aria-hidden="true" style="visibility:hidden" href="javascript:;" onclick="gotoUrl();return false">点击下载文件</a>
										<div class="spinner" id="c">
										  <div class="dot1"></div>
										  <div class="dot2"></div>
										</div>
									</div>
									<div>
									<span id="alertMessage"></span>
									</div>
									
								
									<div class="modal-footer" id="foot">
										<!-- <button type="button" class="btn btn-default" data-dismiss="modal">
											关闭
										</button> -->								
										<button type="button" class="btn btn-primary" onclick="responseMessage()">
											生成 
										</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal -->
						</div>
		            </ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
			<div class="row">				
			<div class="sec1 col-xs-6">
				<div class="divp">选择年级</div>
				<!-- 年级下拉选择框 -->
				<form action="" method="get" class="form">
					<select name="drop" class="ui-select" id="grade-select">
						<option value="选择年级">选择年级</option>
						<c:forEach items="${gradeList}" var="vScope">
							<option value="${vScope.name}">${vScope.name}</option>
						</c:forEach>
					</select>
				</form>
			</div>
			
			<div class="sec2 col-xs-6" id="formdiv2">
				<div class="divp">选择试卷</div>
				<!-- 试卷下拉选择框 -->
				<form action="" method="get" class="form" id="form2">
					<select name="drop" class="ui-select" id="test-select">
						<option value="选择试卷">选择试卷</option>
					</select>
				</form>
			</div>
			</div>
			<div id="container" class="col-lg-12"></div>
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
							<th class="text-center" id="tabhead">学生状态</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">

window.grade="";

	
	$(".selectGrade").each(function(){    
	 $(this).click(function(){
	 	 grade=$(this).html();	
	 	var kaka = document.getElementById("kaka");
	 	kaka.style.visibility="hidden"
	 	 
	 })
	 })
	
	function responseMessage(){
		var yn = document.getElementById("yn");
		var c = document.getElementById("c");
		var kaka = document.getElementById("kaka");

		yn.innerHTML = "正在导出……";
		c.style.display = "block";
		$.ajax({
			type: "post",
			data:{  
			    method : "downloadPDF",  
			    grade:grade
				}, 
			url: "<c:url value='/PDFServlet'/>",
			dataType: "text",
			success: function (msg) {
				if (msg == "END")
					{
					yn.innerHTML = "已经生成PDF";
					c.style.display = "none";
					kaka.style.visibility="visible";
					}
				
			}
		})
		
	
	}
	 


	function gotoUrl(){
	    window.location.href="PDFServlet?method=loadToLocal&grade="+grade;
	    hide();
	}

function hide(){
		var yn = document.getElementById("yn");
		var c = document.getElementById("c");
		yn.innerHTML = "是否导出该年级的PDF ?";
		c.style.display = "none";
	}

	</script>
	<script>
	var xAxisData = [];
	var seriesData = [];

	 //下拉选择框
		$(document).ready(
			function() {
				
				
	
				$("#grade-select").selectWidget(
				{
					change : function(changes) {
						var testElement = document.getElementById("test-select");
						testElement.options.length=1;
						//删除原来下拉选择框
						if(changes != "选择年级"){
						var form = document.getElementById("form2");
						form.parentNode.removeChild(form);}
						
						//var grade = this[this.selectedIndex].innerHTML; 
						var grade = $("#grade-select").find("option:selected").text();
						subject = "${selectSubject}";
						 if("选择年级"!=grade){
							 var ajax = new XMLHttpRequest(); //得到ajax对象
							   
							 //设置地址和 方法为post
							 var method = "post";
							 var url ="<c:url value='/StudentPaperServlet?method=loadTestList'/>&time="+new Date().getTime();
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
										/* var option = document.createElement("option");
				    					option.innerHTML=testname;
										option.value=testId;   //给option的属性value赋值
				    					testElement.appendChild(option);  */
				    					//testElement.add(new Option(testname,testId));
				    					//$("#test-select").append("<option>"+testname+"</option>");
										
										var option = document.createElement("option");
										option.innerHTML=testname;
										option.value=testId;
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
												var tab = document.getElementById("tab1");
												var trs = tab.getElementsByTagName("tr");
												for(var i=trs.length-1;i>0;i--){
													tab.deleteRow(i);
												}
												
												//var testID = this[this.selectedIndex].value; //试卷id
												var testID = $("#test-select").find("option:selected").val()
												if("选择试卷"!=testID){
													var ajax = new XMLHttpRequest(); //得到ajax对象
													   
													 //设置地址和 方法为post
													 var method = "post";
													 var url ="<c:url value='/StudentPaperServlet?method=loadGrade'/>&time="+new Date().getTime();
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
																 seriesData.push(parseFloat(wrong));
															 }
															 pic(); //给柱状图赋值
															 
															
															 
															 var vQuestion = jsonjs[0]['vQuestionList'];
															 for(var i=0;i<vQuestion.length;i++){
																 var tr = tab.insertRow();
																 var td1= tr.insertCell();
																 var td2= tr.insertCell();
																 var td3= tr.insertCell();
																 var td4= tr.insertCell();
																 var td5= tr.insertCell();
																 var td6= tr.insertCell();
																
																 td1.id="td3";
																 td2.className="tdp";
																 td3.id="td3";
																 td4.id="td3";
																 td5.id="td3";
																 td6.id="td3";
																 
																 td1.innerHTML = vQuestion[i]['sequence'];
																 td2.innerHTML = vQuestion[i]['subject']+"</br></br>(A):"+vQuestion[i]['choiceA']+"</br>(B):"+vQuestion[i]['choiceB']+"</br>(C): "+vQuestion[i]['choiceC']+"</br>(D): "+vQuestion[i]['choiceD'];
																 td3.innerHTML = vQuestion[i]['num'];
															 	 td4.innerHTML = vQuestion[i]['successNum'];
															 	 td5.innerHTML = vQuestion[i]['rate'];
															 	 td6.innerHTML = vQuestion[i]['stustatus'];
																 
																 
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
					effect : "slide",
					keyControl : true,
					speed : 200,
					scrollHeight : 250
				});
				


			 	 $("#test-select").selectWidget(
				{
					change : function(changes) {
						return changes;
					},
					effect : "slide",
					keyControl : true,
					speed : 200,
					scrollHeight : 250
				}); 
 
		}); 
			 
			
			
	function pic(){
		//错误率图
		$(document).ready(
			function() {
				var chart = {
					type : 'bar'
				};
				var title = {
					text : '知识点错误率'
				};
				var xAxis = {
					categories : xAxisData,
					title : {
						text : null
					}
				};
				var yAxis = {
					min : 0,
					title : {
						text : '',
						align : 'high'
					},
					labels : {
						overflow : 'justify'
					}
				};
				var tooltip = {
					valueSuffix : '%'
				};
				var plotOptions = {
					bar : {
						dataLabels : {
							enabled : true
						}
					}
				};
				var legend = {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'top',
					x : 0,
					y : 0,
					floating : true,
					borderWidth : 1,
					backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow : true
				};
				var credits = {
					enabled : false
				};

				var series = [ {
					name : '错误率',
					data :seriesData
				} ];
				
				
				
				var json = {};
				json.chart = chart;
				json.title = title;
				json.tooltip = tooltip;
				json.xAxis = xAxis;
				json.yAxis = yAxis;
				json.series = series;
				json.plotOptions = plotOptions;
				json.legend = legend;
				json.credits = credits;
				$('#container').highcharts(json);
			});
	}
	pic();
	</script>


	<!-- 模态框js -->
	<script src="<c:url value='/index/js/modal.js'/>"></script>
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
