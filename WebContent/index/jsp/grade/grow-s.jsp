<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>知诸网</title>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<!-- Bootstrap Core CSS -->
    <link href="<c:url value='/index/bower_components/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.css'/>" rel="stylesheet">
    <!-- Timeline CSS -->
    <link href="<c:url value='/index/dist/css/timeline.css'/>" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value='/index/dist/css/sb-admin-2.css'/>" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="<c:url value='/index/bower_components/morrisjs/morris.css'/>" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<c:url value='/index/bower_components/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
	<script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
	<script src="<c:url value='/index/js/jquery-ui.min.js'/>"></script>
	<script src="<c:url value='/index/js/select-widget-min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/index/css/modal.css'/>" />
	<style type="text/css">
		.nav > li:hover .dropdown-menu {display: block;}
	</style>
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
								</div>
							</div>
						</div>
		            </ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
			<div class="text-center" style="font-size:25px;margin-bottom:50px;">个人成长轨迹</div>
		    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->	
			<div id="mainchinese" style="height:400px"></div>	
			<div id="mainmath" style="height:400px"></div>	
			<div id="mainenglish" style="height:400px"></div>
		</div>
	</div>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
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
    

        // 语文   路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
    require(
        [
            'echarts',
            'echarts/chart/line',
			'echarts/chart/bar'// 使用柱状图就加载bar模块，按需加载
        ],
		 function (ec) {
		
							// 基于准备好的dom，初始化echarts图表
								var myChart = ec.init(document
										.getElementById('mainchinese'));
			var option = {
									
							
							title : {
										text : '语文',
										subtext : ''
									},
							tooltip : {
								trigger : 'axis'
							},
							legend : {
								data : [ '各阶段学习PR值' ]
							},
							toolbox : {
								show : true,
								feature : {
									//mark : {show: true},
									//dataView : {show: true, readOnly: false},
									magicType : {
										show : true,
										type : [ 'line', 'bar' ]
									},
									restore : {
										show : true
									},
								//saveAsImage : {show: true}
								}
							},
							calculable : true,
							xAxis : [ 
							          {
										type : 'category',
										boundaryGap : false,
										data : [ '一年级上', '一年级下', '二年级上', '二年级下',
												'三年级上', '三年级下', '四年级上', '四年级下', '五年级上',
												'五年级下', '六年级上' ]
									  } 
							        ],
							
							
							yAxis : [ {
										type : 'value',
										axisLabel : {
											formatter : '{value} '
										}
									} ],
							series : [ {
										name : '各阶段学习PR值',
										type : 'line',
										data : [ 81, 51, 75, 63, 92, 83, 70, 71, 88,
												75, 60, 55, 77 ],
										markPoint : {
											data : [ {
												type : 'max',
												name : '最大值'
											}, {
												type : 'min',
												name : '最小值'
											} ]
										},
										markLine : {
											data : [ {
														type : 'average',
														name : '平均值'
													} ]
												   }
										} ]
						 	
						};
			//为语文折线图赋值
			var value0 = [];
			var value1 = [];
			<c:forEach items="${chineseList}" var="studentPr">
				value0.push("${studentPr.classyear}");
			</c:forEach>
			<c:forEach items="${chineseList}" var="studentPr">
				value1.push("${studentPr.avgPR}");
			</c:forEach>
						
			option['xAxis'][0]['data'] = value0;
			option['series'][0]['data']= value1;
		
			// 为echarts对象加载数据 
			myChart.setOption(option);																	
		});
    

					
		// 数学   路径配置
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});

		// 使用
		require([ 'echarts', 'echarts/chart/line',
				'echarts/chart/bar'// 使用柱状图就加载bar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document
					.getElementById('mainmath'));

			var option = {
				title : {
					text : '数学',
					subtext : ''
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '各阶段学习PR值' ]
				},
				toolbox : {
					show : true,
					feature : {
						//mark : {show: true},
						//dataView : {show: true, readOnly: false},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
					//saveAsImage : {show: true}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ '一年级上', '一年级下', '二年级上', '二年级下',
							'三年级上', '三年级下', '四年级上', '四年级下', '五年级上',
							'五年级下', '六年级上', '六年级下' ]
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value} '
					}
				} ],
				series : [ {
					name : '各阶段学习PR值',
					type : 'line',
					data : [ 81, 50, 75, 63, 96, 83, 70, 57, 88,
							75, 90, 55, 77, 79 ],
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				} ]
			};
			//为图赋值
			var value2 = [];
			var value3 = [];
			<c:forEach items="${mathList}" var="studentPr">
				value2.push("${studentPr.classyear}");
			</c:forEach>
			<c:forEach items="${mathList}" var="studentPr">
				value3.push("${studentPr.avgPR}");
			</c:forEach>
						
			option['xAxis'][0]['data'] = value2;
			option['series'][0]['data']= value3;

			// 为echarts对象加载数据 
			myChart.setOption(option);
		});

	// 英语   路径配置
	require.config({
		paths : {
			echarts : 'http://echarts.baidu.com/build/dist'
		}
	});

	// 使用
	require([ 'echarts', 'echarts/chart/line',
			'echarts/chart/bar'// 使用柱状图就加载bar模块，按需加载
	], function(ec) {
		// 基于准备好的dom，初始化echarts图表
		var myChart = ec.init(document
				.getElementById('mainenglish'));

		var option = {
			title : {
				text : '英语',
				subtext : ''
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '各阶段学习PR值' ]
			},
			toolbox : {
				show : true,
				feature : {
					//mark : {show: true},
					//dataView : {show: true, readOnly: false},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
				//saveAsImage : {show: true}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				data : [ '一年级上', '一年级下', '二年级上', '二年级下',
						'三年级上', '三年级下', '四年级上', '四年级下', '五年级上',
						'五年级下', '六年级上', '六年级下' ]
			} ],
			yAxis : [ {
				type : 'value',
				axisLabel : {
					formatter : '{value} '
				}
			} ],
			series : [ {
				name : '各阶段学习PR值',
				type : 'line',
				data : [ 81, 51, 75, 63, 92, 83, 70, 71, 88,
						75, 60, 55, 77, 79 ],
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			} ]
		};
		var value4 = [];
		var value5 = [];
		<c:forEach items="${englishList}" var="studentPr">
			value4.push("${studentPr.classyear}");
		</c:forEach>
		<c:forEach items="${englishList}" var="studentPr">
			value5.push("${studentPr.avgPR}");
		</c:forEach>
				
		option['xAxis'][0]['data'] = value4;
		option['series'][0]['data']= value5;

		// 为echarts对象加载数据 
		myChart.setOption(option);
	});
</script>
	<%-- <c:forEach items="${chineseList}" var="studentPr">
		<div>
			<span>年级</span>
			<span>${studentPr.classyear}</span>
			<span>pr</span>
			<span>${studentPr.avgPR}</span>
			
		</div>
	</c:forEach> --%>
	
	<!-- 模态框js -->
	<script src="<c:url value='/index/js/modal.js'/>"></script>
	
	<script src="<c:url value='/index/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.js'/>"></script>
    <!-- Morris Charts JavaScript -->
    <script src="<c:url value='/index/bower_components/raphael/raphael-min.js'/>"></script>
    <script src="<c:url value='/index/bower_components/morrisjs/morris.min.js'/>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/index/dist/js/sb-admin-2.js'/>"></script>
</body>
</html>