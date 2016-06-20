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
	<link href="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.css '/>" rel="stylesheet">
	<!-- Timeline CSS -->
	<link href="<c:url value='/index/dist/css/timeline.css '/>" rel="stylesheet">
	<!-- Custom CSS -->
	<link href="<c:url value='/index/dist/css/sb-admin-2.css'/>" rel="stylesheet">
	<!-- Morris Charts CSS -->
	<link href="<c:url value='/index/bower_components/morrisjs/morris.css'/>" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="<c:url value='/index/bower_components/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<!-- jQuery -->
	<script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js '/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>" />
	<script src="<c:url value='/index/js/jquery-ui.min.js'/>"></script>
	<script src="<c:url value='/index/js/select-widget-min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/relation.css'/>" />
	<script src="<c:url value='/index/js/echarts.min.js'/>"></script>
	<script src="<c:url value='/index/js/d3.js '/>" charset="utf-8"></script>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/modal.css'/>" />
</head>

<body>
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
                        <c:choose>
							<c:when test="${empty sessionScope.sname }">
								${sessionScope.name }&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								${sessionScope.sname }&nbsp;&nbsp;
							</c:otherwise>						
						</c:choose>
                        <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
						<c:choose>
							<c:when test="${empty sessionScope.sname }">
								<li><a href="#"><i class="fa fa-user fa-fw"></i> ${sessionScope.name }</a>
                        		</li>
							</c:when>
							<c:otherwise>
								<li><a href="#"><i class="fa fa-user fa-fw"></i> ${sessionScope.sname }</a>
                        		</li>
                        		<li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${sessionScope.sID }</a>
                        		</li>
							</c:otherwise>						
						</c:choose>
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
								<c:choose>
									<c:when test="${empty sessionScope.sname }">
										<li>
											<a href="<c:url value='/TeacherGrowServlet?method=loadGrowStu'/>">学生成长轨迹图</a>
										</li>
										<li>
											<%-- <a href="<c:url value='/TeacherServlet?method=courseInfo&courseName=chinese'/>" target="main">语文</a> --%>
											<a href="<c:url value='/TeacherGrowServlet?method=loadGradeStu'/>">学生学习履历图</a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<%-- <a href="<c:url value='/StudentServlet?method=allInfo&courseName=sum'/>" target="main">成长轨迹图</a> --%>
											<a href="<c:url value='/StudentGrowServlet?method=loadPr'/>">个人成长轨迹图</a>
				                        </li>
				                        <li>
				                            <%-- <a href="<c:url value='/StudentServlet?method=courseInfo&courseName=chinese'/>" target="main">学习履历图</a> --%>
				                        	<a href="<c:url value='/StudentGrowServlet?method=loadStuYear'/>">个人学习履历图</a>
				                        </li>
									</c:otherwise>						
								</c:choose>	
		                    </ul>
		                </li>
		                <li>
							<a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 试卷分析<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<c:choose>
										<c:when test="${empty sessionScope.sname }">
											<li><a
												href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=语文'/>"
												>语文</a></li>
											<li><a
												href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=数学'/>"
												>数学</a></li>
											<li><a
												href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=英文'/>"
												>英语</a></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="<c:url value='/StudentPaperServlet?method=loadGradeList&subject=语文'/>">语文</a>
				                            </li>
											<li>
				                                <a href="<c:url value='/StudentPaperServlet?method=loadGradeList&subject=数学'/>">数学</a>
				                            </li>
				                            <li>
				                                <a href="<c:url value='/StudentPaperServlet?method=loadGradeList&subject=英文'/>">英语</a>
				                            </li>
										</c:otherwise>						
									</c:choose>
									
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
		                <c:choose>
							<c:when test="${empty sessionScope.sname }">
								
							</c:when>
							<c:otherwise>
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
							</c:otherwise>						
						</c:choose>
		                
		            </ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">			
			<div class="titlet">知识关联分析</div>
			<div class="row">	
				<!-- <div class="col-xs-9"></div> -->
	<!-- 年级下拉选择框 -->			
				<div class="sec col-xs-2">
					<form action="" method="get" class="form">
						<select name="drop1" class="ui-select" id="grade-select">
							<c:forEach items="${gradeList}" var="grade">
								<option value="${grade}"
									<c:if test="${grade eq selectedGrade}" >
			  									selected='selected'
			  						</c:if>
			  					>
									${grade}
								</option>
							</c:forEach>
						</select>
					</form>
				</div>
	<!-- 搜索按钮 -->
				<div class="input-group custom-search-form col-xs-10 pull-right sear">
					<span class="input-group-btn">
					<button id="searchButton" class="btn btn-default" type="button">
				    		<i class="fa fa-search"></i>
					</button>
					</span>
					<input type="text" id="searchText" class="form-control" placeholder="搜索知识点..." onkeypress="pressKey()">
		        </div>
		        
			</div>
			
	<!-- 知识关联网 -->		
			<div class="row">
				<div class="rel col-sm-12"><span style="font-size:20px"></span></div>			
			</div>
	<!-- 知识关联表 -->
			<div class="row">
			<div class="info col-md-4">
				<table class="table table-bordered" id="keywordTable">
					<caption class="text-center title">知识点关联表</caption>
					<thead>
						<tr>
							<th width="100px" class="text-center">知识点类型</th>
							<th width="" class="text-center">知识点名称</th>									
						</tr>
					</thead>
					<tbody id="keywordTbody">
	  					<tr>								
							<td class="td1">前继知识点</td>
							<td>数学广角,统计和复数</td>
						</tr>
						<tr>
							<td class="td1">后续知识点</td>
							<td>位置与方向,分数除法</td>
						</tr> 
					</tbody>
				</table>
			</div>
	<!-- 饼图和长条图 -->
					
				<div class="col-md-4 text-center" style="font-size:20px">难度比例<div id='bin' style="height:150px;width:100%;"></div></div>
				<div class="col-md-4 text-center" style="font-size:20px">对错概率<div id="tright" style="width:100%;height:150px"></div></div>
			</div>
	<!-- R图 -->		
			<div clss="row">
				<div class="imgd col-sm-6 col-lg-6">
					高频知识点绘图<img src="<c:url value='${img_fsetsLift}'/>"
						width="100%" height="90%" class="img" />
				</div>
				<div class="imgd col-sm-6 col-lg-6">
					关联分析规则图<img src="<c:url value='${img_ScottPlot}' />" width="100%"
						height="90%" class="img" />
				</div>
				<div class="imgdb col-sm-12 col-lg-12">
					知识点频数分布图<img src="<c:url value='${img_fsetsSup}'/>"
						width="100%" height="90%" class="img" />
				</div>
				<div class="imgd col-sm-6 col-lg-6">
					知识点关联结构网图<img src="<c:url value='${img_Graph}' />" width="100%"
						height="90%" class="img" />
				</div>
				<div class="imgd col-sm-6 col-lg-6">
					支持-置信度矩阵分布图<img src="<c:url value='${img_GroupedMatrix}'/>" width="100%"
						height="90%" class="img" />
				</div>
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
 <script type="text/javascript"> 
var easyCount;
var middleCount;
var hardCount;

var keywordNameList = [];
var rightRateList = [];
var wrongRateList = [];

//知识关联网图数据json数据
 	var nodes = [];
	var edges = [];
	
	<c:forEach items="${keywordArray}" var="keyword">
		var node = {
				name : "${keyword[0]}",
				imp : parseInt(${keyword[1]}),
 				beSearched : "${keyword[2]}" 
		};
		nodes.push(node); 
	</c:forEach>
	
	<c:forEach items="${edges}" var="edge">
		var edge2 = parseFloat(${edge[2]}) * 150;
		var jason = {
			source: ${edge[0]},
			target: ${edge[1]},
			value: edge2,
			imp: ${edge[3]
			}
		};
		edges.push(jason);
	</c:forEach> 
	
	var width = 1000;
	var height = 650;

	var svg = d3.select(".rel").append("svg").attr("width", width).attr(
		"height", height);

	var force = d3.layout.force().nodes(nodes) //指定节点数组
		.links(edges) //指定连线数组
		.size([width, height]) //指定范围
		.linkDistance(function(d) {
			return d.value;

		}) //指定连线长度
		.charge([-400]); //相互之间的作用力

	force.start(); //开始作用

	console.log(nodes);
	console.log(edges);

	//添加连线    
	var svg_edges = svg.selectAll("line").data(edges).enter()
		.append("line").style("stroke", "#ccc")
		.style("stroke", function(d){
			if (d.imp == 1)
			return "green";
			else 
				return "#ccc";
			})
			.style("stroke-width",function(d){
				if (d.imp == 1)	
					return 3;
				else 
					return 1;
			})
	var color = d3.scale.category20();
	//添加节点      
	var svg_nodes = svg.selectAll("circle").data(nodes).enter().append(
		"circle").attr("r", function(d){
			if(d.beSearched == 1){
				return 20;
			}
			else if(d.imp == 1){
				return 15;
			}
			else{
				return 10;
			}
		})

		.style("fill", function(d, i) {
		if(d.beSearched == 1){		
			return "red";
		}
		else if(d.imp == 1){
			return "green";
		}
		else {
			return color(i);
		}
	}).call(force.drag); //使得节点能够拖动

	//添加描述节点的文字
	var svg_texts = svg.selectAll("text").data(nodes).enter()
		.append("text").style("fill", "black").attr("dx", 20).attr(
			"dy", 8).text(function(d) {
			return d.name;
		});

	force.on("tick", function() { //对于每一个时间间隔

		//更新连线坐标
		svg_edges.attr("x1", function(d) {
			return d.source.x;
		}).attr("y1", function(d) {
			return d.source.y;
		}).attr("x2", function(d) {
			return d.target.x;
		}).attr("y2", function(d) {
			return d.target.y;
		});

		//更新节点坐标
		svg_nodes.attr("cx", function(d) {
			return d.x;
		}).attr("cy", function(d) {
			return d.y;
		});

		//更新文字坐标
		svg_texts.attr("x", function(d) {
			return d.x;
		}).attr("y", function(d) {
			return d.y;
		});
	});

	svg_nodes.on("mousedown", function(d, i) {
		d3.select(this).style("fill", "red");
		d3.select(this).style("r", "20");

		var idx = d.index;
		svg_edges.each(function(d) {
			if (d.target.index == idx || d.source.index == idx) {
				d3.select(this).style("stroke", "red");
				d3.select(this).style("stroke-width", 3);

			}
		});
		var dx= d.name;
	    svg_texts.each(function(d){
	    if(d.name==dx){
	    d3.select(this)
	    .style("fill","red");
	     d3.select(this)
	    .style("font-size",20)    
	    }
	  });

		
 		var keywordName = d.name;
		//请求加载知识点关联表信息
		$.ajax({
	                type: "post",
	                data:{  
	                    method : "getKeywordDetail",  
	                    keywordName : keywordName
	           		}, 
	                url: "<c:url value='/RelationServlet'/>",
	                dataType: "json",
	                success: function (json) {
 	                		$("#keywordTable tbody tr").remove();
 	                		keywordNameList = [];
 	                		rightRateList = [];
 	                		wrongRateList = [];
	                    for (var i = 0; i < json.length; i++) {	
 		                    	var html; 	
		                    	if(i == 0){
		                    		html =  "<tr><td class='td1'>" + json[i]['keywordType'] + "</td><td>" + json[i]['keywordName']  + "</tr>";
		                    	}else{
		                    		html = "<tr><td class='td1'>" + json[i]['keywordType'] + "</td><td>" + json[i]['keywordName']  + "</tr>";
		                    	}
		                    	$("#keywordTbody").append(html); 
  		                    	if(i == 0){
		                    		easyCount =  json[i]['easyCount'];
		                    		middleCount =  json[i]['middleCount'];
		                    		hardCount =  json[i]['hardCount'];
		                    	}else{
		                    		keywordNameList.push(json[i]['keywordName']);
		             			var rightRate = parseFloat(json[i]['rightRate']) * -1;
		                    		var wrongRate = parseFloat(json[i]['wrongRate']);
		                    		rightRateList.push(rightRate);
		                    		wrongRateList.push(wrongRate);
		                    	}     		                    	
	                    }
	                    pic();
	                }, 
	                error: function () {
	                    alert("加载失败");
	                }
	    }); 
		
	})
	svg_nodes.on("mouseout", function(d, i) {
		if(d.imp == 0){
			d3.select(this).style("fill", function() {
				return color(i);
			})
		}
		if(d.imp == 0){
			d3.select(this).style("r", "10");
		}
		else if(d.beSearched == 1){
 			d3.select(this).style("r", "20");
			d3.select(this).style("fill", "red");
		}
		else	{	
			d3.select(this).style("r", "15");
			d3.select(this).style("fill", "green");
		} 
		var idx = d.index;
		svg_edges.each(function(d) {
			if (d.target.index == idx || d.source.index == idx) {
				if(d.imp == 0){
					d3.select(this).style("stroke", "#ccc");
					d3.select(this).style("stroke-width", 1);
				}
				else
					{
					d3.select(this).style("stroke", "green");
					d3.select(this).style("stroke-width", 3);
				}	
				
			}
		});
		var dx= d.name;
	    svg_texts.each(function(d){
	    if(d.name==dx){
	    d3.select(this)
	    .style("fill","black");
	     d3.select(this)
	    .style("font-size",14)    
	    }
	  });

	});
	//回车搜索
	function pressKey(){
		if((event.keyCode == 13)){
			var keyword = $("#searchText").val();
			location.href = "<c:url value='/RelationServlet?method=search&keyword='/>" + keyword;
		}
	};
//下拉选择框
	$(document).ready(
		function() {
			//搜索按钮事件
			$("#searchButton").bind("click", function () {
				var keyword = $("#searchText").val();
				location.href = "<c:url value='/RelationServlet?method=search&keyword='/>" + keyword;
			});
			
			$("#grade-select").selectWidget({
				change: function(changes) {
				var grade = $("#grade-select").val();
				var course = "${selectedCourse}"
				location.href = "<c:url value='/RelationServlet?method=relation&grade='/>" + grade + "&course=" + course ;
			},
			effect: "slide",
			keyControl: true,
			speed: 200,
			scrollHeight: 250
		});
	});
		
 //饼图横条图json数据
	function pic(){
//饼图   路径配置
		 require.config({
		     paths: {
		         echarts: 'http://echarts.baidu.com/build/dist'
		     }
		 });
		 
		 // 使用
		 require(
		     [
		         'echarts',
		         'echarts/chart/pie',
					'echarts/chart/funnel'	// 使用柱状图就加载bar模块，按需加载
		     ],
		     function (ec) {
		         // 基于准备好的dom，初始化echarts图表
		         var myChart = ec.init(document.getElementById('bin')); 
		         
		         var option = {
		             title : {
		 				text: '',
		 				subtext: '',
		 				x:'center'
						},
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
						calculable : true,
					    series : [
					        {
					            name:'题目分布情况',
					            type:'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:[
					                {value:easyCount, name:'简单题'},
					                {value:middleCount, name:'中等题'},
					                {value:hardCount, name:'较难题'},
					            ]	        		
					        }
					    ]};
					    // 为echarts对象加载数据 
					    myChart.setOption(option); 
			});
		
// 左右柱形图
		    var myChart = echarts.init(document.getElementById('tright'));

		    // 指定图表的配置项和数据
		    var option = {
		tooltip : {
		    trigger: 'axis',
		    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		    }
		},
		legend: {
		    data:['正确概率','错误概率']
		},
		grid: {
		    left: '3%',
		    right: '4%',
		    bottom: '3%',
		    containLabel: true
		},
		xAxis : [
		    {
		        type : 'value'
		    }
		],
		yAxis : [
		    {
		        type : 'category',
		        axisTick : {show: false},
		        data : keywordNameList
		    }
		],
		series : [
		          {
		              name:'错误概率',
		              type:'bar',
		              stack: '总量',
		              itemStyle: {
		                  normal: {
		                      label : {show: true}
		                  }
		              },
		              data:wrongRateList
		          },
		          {
		              name:'正确概率',
		              type:'bar',
		              stack: '总量',
		              itemStyle: {normal: {
		                  label : {show: true, position: 'left'},
		                  color : '#009933'
		              }},
		              data:rightRateList
		          }
		      ]
		};

		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		
	} 
	
	
    </script>  
    <!-- 模态框js -->
	<script src="<c:url value='/index/js/modal.js'/>"></script>
	<!-- /#wrapper -->
	<!-- Bootstrap Core JavaScript -->
	<script	src="<c:url value='/index/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.js' />"></script>
	<!-- Morris Charts JavaScript -->
	<script src="<c:url value='/index/bower_components/raphael/raphael-min.js '/>"></script>
	<script src="<c:url value='/index/bower_components/morrisjs/morris.min.js'/>"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/index/dist/js/sb-admin-2.js'/>"></script>

</body>
</html>