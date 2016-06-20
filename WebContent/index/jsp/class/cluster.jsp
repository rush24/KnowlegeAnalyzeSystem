<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <base href="<%=basePath%>">
    
    <title>知诸网</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		    <div id="main" style="height:680px;width:100%;"></div>
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
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/scatter' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                var option = {
                    title : {
        text : '',
        subtext : ''
    },
    tooltip : {
        trigger: 'axis',
        axisPointer:{
            show: true,
            type : 'cross',
            lineStyle: {
                type : 'dashed',
                width : 1
            }
        }
    },
	//右上角工具箱
    toolbox: {
        show : false,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
	//显示范围类目
    dataZoom: {
        show: true,
        start : 0,
        end : 100
    },
	//类目种类
    legend : {
        data : ['基础常模','良好常模','进阶常模','优秀常模']
    },
    dataRange: {
        min: 10,
        max: 100,
        orient: 'horizontal',
        y: 45,
        x: 'center',
        //text:['高','低'],           // 文本，默认为数值文本
        color:['lightgreen','orange'],
        splitNumber: 4
    },
    xAxis : [
        {
            type : 'category',
            axisLabel: {
                formatter : function(v) {
                    var arr = ['一年级(上)','一年级(下)','二年级(上)','二年级(下)','三年级(上)','三年级(下)','四年级(上)','四年级(下)','五年级(上)','五年级(下)','六年级(上)','六年级(下)'];
					return arr[v-1];
                }
            },
            data : function (){
                var list = [];
                var len = 0;
				//x轴类目数量
                while (len++ < 12) {
                    list.push(len);
                }
                return list;
            }()
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    animation: false,
    series : [
        {
            name:'基础常模',
            type:'scatter',
            tooltip : {
                trigger: 'item',
                formatter : function (params) {
					//标签信息
                    /* return params.seriesName + ' （'  + '类目' + params.value[0] + '）<br/>'
                           + params.value[1] + ', ' 
                           + params.value[2]; */
                    return params.seriesName + '<br/>'
                    			+ params.value[3] + '<br/>'
                    			+ params.value[2] + '次';
                },
                axisPointer:{
                    show: true
                }
            },
            symbolSize: function (value){
                return Math.round(value[2]/2);
            },
            data: (function () {
                var d = [];
                /*var len = 0;
                var value;
                while (len++ < 12) {
                    d.push([
                        len,
						//y轴
                        (Math.random()*30).toFixed(2) - 0,
                        //图形大小值
						(Math.random()*100).toFixed(2) - 0
                    ]);
                }*/
                /*[1,(10).toFixed(2) - 0,(50).toFixed(2) - 0],[1,(10).toFixed(2) - 0,(100).toFixed(2) - 0],[1,(30).toFixed(2) - 0,(150).toFixed(2) - 0]*/
                <c:forEach items="${badList}" var="cluster">
                		var sonOfData1 = [];
					sonOfData1.push("${cluster.gradeNum}");
                		sonOfData1.push((${cluster.yAxis}).toFixed(2) - 0);
                		sonOfData1.push((${cluster.area}).toFixed(2) - 0);
                		sonOfData1.push("${cluster.name}");
                		d.push(sonOfData1);
				</c:forEach> 
				
				
                return d;
            })()
        },
        {
            name:'良好常模',
            type:'scatter',
            tooltip : {
                trigger: 'item',
                formatter : function (params) {

                    return params.seriesName + '<br/>'
                    			+ params.value[3] + '<br/>'
                    			+ params.value[2] + '次';
                },
                axisPointer:{
                    show: true
                }
            },
            symbolSize: function (value){
                return Math.round(value[2]/2);
            },
            data: (function () {
                var d = [];
                <c:forEach items="${middle_badList}" var="cluster">
	        			var sonOfData1 = [];
					sonOfData1.push("${cluster.gradeNum}");
	        			sonOfData1.push((${cluster.yAxis}).toFixed(2) - 0);
	        			sonOfData1.push((${cluster.area}).toFixed(2) - 0);
	        			sonOfData1.push("${cluster.name}");
	        			d.push(sonOfData1);
				</c:forEach> 
                return d;
            })()
        },
        {
            name:'进阶常模',
            type:'scatter',
            tooltip : {
                trigger: 'item',
                formatter : function (params) {
                    return params.seriesName + '<br/>'
        					+ params.value[3] + '<br/>'
        					+ params.value[2] + '次';
                },
                axisPointer:{
                    show: true
                }
            },
            symbolSize: function (value){
                return Math.round(value[2]/2);
            },
            data: (function () {
                var d = [];
                <c:forEach items="${middle_goodList}" var="cluster">
		    			var sonOfData1 = [];
					sonOfData1.push("${cluster.gradeNum}");
		    			sonOfData1.push((${cluster.yAxis}).toFixed(2) - 0);
		    			sonOfData1.push((${cluster.area}).toFixed(2) - 0);
		    			sonOfData1.push("${cluster.name}");
		    			d.push(sonOfData1);
				</c:forEach> 
                return d;
            })()
        },
        {
            name:'优秀常模',
            type:'scatter',
            tooltip : {
                trigger: 'item',
                formatter : function (params) {
                	return params.seriesName + '<br/>'
					+ params.value[3] + '<br/>'
					+ params.value[2] + '次';
                },
                axisPointer:{
                    show: true
                }
            },
            symbolSize: function (value){
                return Math.round(value[2]/2);
            },
            data: (function () {
                var d = [];
                <c:forEach items="${middle_goodList}" var="cluster">
		    			var sonOfData1 = [];
					sonOfData1.push("${cluster.gradeNum}");
		    			sonOfData1.push((${cluster.yAxis}).toFixed(2) - 0);
		    			sonOfData1.push((${cluster.area}).toFixed(2) - 0);
		    			sonOfData1.push("${cluster.name}");
		    			d.push(sonOfData1);
				</c:forEach> 
                return d;
            })()
        }
    ]
                };
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        
    </script>
    <!-- 模态框js -->
	<script src="<c:url value='/index/js/modal.js'/>"></script>
    <!-- Bootstrap Core JavaScript -->
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
