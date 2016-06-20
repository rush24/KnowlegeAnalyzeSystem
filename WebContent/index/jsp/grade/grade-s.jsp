<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
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
    <link href="<c:url value='/index/bower_components/morrisjs/morris.css'/>" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<c:url value='/index/bower_components/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	<script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>"/>
	<script src="<c:url value='/index/js/jquery-ui.min.js'/>"></script>
	<script src="<c:url value='/index/js/select-widget-min.js'/>"></script>
  	<link rel="stylesheet" href="<c:url value='/index/css/modal.css'/>" />
	<style type="text/css">
		.sec{
			position:absolute;
			right:20%;
		}
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
								</div><!-- /.modal-content -->
							</div><!-- /.modal -->
						</div>
		            </ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
		 	<div class="text-center" style="margin-bottom:50px;"><span style="font-size:25px;">个人学习履历</span>
			<div class="sec text-left">
				<!-- 下拉选择框 -->
					<form action="" method="get" class="form">
						<select name="drop1" class="ui-select" id="stu-select">
							<option>选择年级</option>
							<c:forEach items="${stuYearList}" var="stuYear">
								<option value="${stuYear.classyear}">${stuYear.classyear}</option>
							</c:forEach>
						</select>
					</form>
			</div>
			</div> 
		    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->	
			<div id="mainchinese" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="prchinese" style="height:400px;width:100%;margin:0 auto;"></div>	
			<div id="mainmath" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="prmath" style="height:400px;width:100%;margin:0 auto;"></div>	
			<div id="mainenglish" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="prenglish" style="height:400px;width:100%;margin:0 auto;"></div>
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
    
    // 语文前测数据
    var chineseBefData = [60, 70, 85, 78, 86, 83, 77, 66, 55, 44];
  	// 语文后测数据
    var chineseAftData = [80, 74, 81, 90, 73, 80, 88, 77, 90, 88];
  	// 语文前后测相差数据
  	var chineseGradeCutData = [60, 70, 81, 78, 73, 80, 77, 66, 55, 44];
  	// 语文前后测相差数据起点数据
  	var chineseGradeHighData = [20, 4, 4, 12, 13, 3, 11, 11, 35, 44];
  	// 语文成绩-PR关系数据
    var chineseGradeAndPrData = [[21.5,20.34],[32.5,33.33],[45,40.89],[57.5,60.78],[80.8,75.79],[75,80.45]];
  	
 	// 数学前测数据
    var mathBefData = [60, 70, 85, 78, 86, 83, 77, 66, 55, 44];
  	// 数学后测数据
    var mathAftData = [80, 74, 81, 90, 73, 80, 88, 77, 90, 88];
  	// 数学前后测相差数据
  	var mathGradeCutData = [60, 70, 81, 78, 73, 80, 77, 66, 55, 44];
  	// 数学前后测相差数据起点数据
  	var mathGradeHighData = [20, 4, 4, 12, 13, 3, 11, 11, 35, 44];
  	// 数学成绩-PR关系数据
    var mathGradeAndPrData = [[21.5,20.34],[32.5,33.33],[45,40.89],[57.5,60.78],[80.8,75.79],[75,80.45]];
  	
 	// 英文前测数据
    var englishBefData = [60, 70, 85, 78, 86, 83, 77, 66, 55, 44];
  	// 英文后测数据
    var englishAftData = [80, 74, 81, 90, 73, 80, 88, 77, 90, 88];
  	// 英文前后测相差数据
  	var englishGradeCutData = [60, 70, 81, 78, 73, 80, 77, 66, 55, 44];
  	// 英文前后测相差数据起点数据
  	var englishGradeHighData = [20, 4, 4, 12, 13, 3, 11, 11, 35, 44];
  	// 英文成绩-PR关系数据
    var englishGradeAndPrData = [[21.5,20.34],[32.5,33.33],[45,40.89],[57.5,60.78],[80.8,75.79],[75,80.45]];
    
  	function pic(){
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
				'echarts/chart/bar'	// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mainchinese')); 
                
                var option = {
                    title :{
						text : '语文',
						subtext : ''
					},
					tooltip : {
						trigger: 'axis',
						formatter: function (params){		//显示信息标签
							return params[0].name + ' : '
								   + (params[2].value - params[1].value > 0 ? '+' : '-') 
								   + params[0].value + '<br/>'
								   + params[2].seriesName + ' : ' + params[2].value + '<br/>'
								   + params[3].seriesName + ' : ' + params[3].value + '<br/>'
						}
					},
					toolbox: {
						show : true,
						feature : {
							magicType : {show: true, type: ['line', 'bar']},
							restore : {show: true},
						}
					},
					legend: {
						data:['前测', '后测'],
						selectedMode:false
					},
					xAxis : [
						{
							type : 'category',
							//x轴
							data : ['T1','T2','T3','T4','S1','T5','T6','T7','T8','S2']
						}
					],
					yAxis : [
						{
							type : 'value',
							min : 0,
							max : 100
						}
					],
					
					series : [
						{
							name:'前测',
							type:'line',
							data:chineseBefData
						},
						{		//前测折线图
							name:'后测',
							type:'line',
							symbol:'none',							
							data:chineseAftData
						},
						{		//关于差距柱状图
							name:'前测2',
							type:'bar',
							stack: '1',
							barWidth: 6,
							itemStyle:{
								normal:{
									color:'rgba(0,0,0,0)'
								},
								emphasis:{
									color:'rgba(0,0,0,0)'
								}
							},
							//柱状图起始高度(前后测比较的小的值)
							data: chineseGradeHighData
						},
						{		//差距柱状图
							name:'变化',
							type:'bar',
							stack: '1',
							data: chineseGradeCutData
						}
					]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );


		// 数学   路径配置
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
				'echarts/chart/bar'	// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mainmath')); 
                
                var option = {
                    title :{
						text : '数学',
						subtext : ''
					},
					tooltip : {
						trigger: 'axis',
						formatter: function (params){
							return params[0].name + ' : '
								   + (params[2].value - params[1].value > 0 ? '+' : '-') 
								   + params[0].value + '<br/>'
								   + params[2].seriesName + ' : ' + params[2].value + '<br/>'
								   + params[3].seriesName + ' : ' + params[3].value + '<br/>'
						}
					},
					toolbox: {
						show : true,
						feature : {
							magicType : {show: true, type: ['line', 'bar']},
							restore : {show: true}
						}
					},
					legend: {
						data:['前测', '后测'],
						selectedMode:false
					},
					xAxis : [
						{
							type : 'category',
							data : ['T1','T2','T3','T4','S1','T5','T6','T7','T8','S2']
						}
					],
					yAxis : [
						{
							type : 'value',
							min : 0,
							max : 100
						}
					],
					series : [
						{
							name:'前测',
							type:'line',
							data:mathBefData
						},
						{		//前测折线图
							name:'后测',
							type:'line',
							symbol:'none',
							data:mathAftData
						},
						{		//关于差距柱状图
							name:'前测2',
							type:'bar',
							stack: '1',
							barWidth: 6,
							itemStyle:{
								normal:{
									color:'rgba(0,0,0,0)'
								},
								emphasis:{
									color:'rgba(0,0,0,0)'
								}
							},
							//柱状图起始高度(前后测比较的小的值)
							data:mathGradeHighData
						},
						{		//差距柱状图
							name:'变化',
							type:'bar',
							stack: '1',
							data:mathGradeCutData
						}
					]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );

		// 英语   路径配置
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
				'echarts/chart/bar'	// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mainenglish')); 
                
                var option = {
                    title :{
						text : '英语',
						subtext : ''
					},
					tooltip : {
						trigger: 'axis',
						formatter: function (params){
							return params[0].name + ' : '
								   + (params[2].value - params[1].value > 0 ? '+' : '-') 
								   + params[0].value + '<br/>'
								   + params[2].seriesName + ' : ' + params[2].value + '<br/>'
								   + params[3].seriesName + ' : ' + params[3].value + '<br/>'
						}
					},
					toolbox: {
						show : true,
						feature : {
							magicType : {show: true, type: ['line', 'bar']},
							restore : {show: true},
						}
					},
					legend: {
						data:['前测', '后测'],
						selectedMode:false
					},
					xAxis : [
						{
							type : 'category',
							data : ['T1','T2','T3','T4','S1','T5','T6','T7','T8','S2']
						}
					],
					yAxis : [
						{
							type : 'value',
							min : 0,
							max : 100
						}
					],
					series : [
						{
							name:'前测',
							type:'line',
							data:englishBefData
						},
						{		//前测折线图
							name:'后测',
							type:'line',
							symbol:'none',
							data:englishAftData
						},
						{		//关于差距柱状图
							name:'前测2',
							type:'bar',
							stack: '1',
							barWidth: 6,
							itemStyle:{
								normal:{
									color:'rgba(0,0,0,0)'
								},
								emphasis:{
									color:'rgba(0,0,0,0)'
								}
							},
							//柱状图起始高度(前后测比较的小的值)
							data:englishGradeHighData
						},
						{		//差距柱状图
							name:'变化',
							type:'bar',
							stack: '1',
							data:englishGradeCutData
						}
					]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );

		// 语文散点   路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/scatter'	// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('prchinese')); 
                
                var option = {
                    title : {
        text: '语文',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis',
        showDelay : 0,
        formatter : function (params) {
            if (params.value.length > 1) {
                return params.seriesName + ' :<br/>'
                   + params.value[0] + '分,PR: ' 
                   + params.value[1] + '% ';
            }
            else {
                return params.seriesName + ' :<br/>'
                   + params.name + ' : '
                   + params.value + '% ';
            }
        },  
        axisPointer:{
            show: true,
            type : 'cross',
            lineStyle: {
                type : 'dashed',
                width : 1
            }
        }
    },
    legend: {
        data:['成绩-PR']
    },
    toolbox: {
        show : true,
        feature : {
            //mark : {show: true},
            dataZoom : {show: true},
            //dataView : {show: true, readOnly: false},
            restore : {show: true},
            //saveAsImage : {show: true}
        }
    },
    xAxis : [
        {
            type : 'value',
            scale:true,
            axisLabel : {
                formatter: '{value} 分'
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            scale:true,
            axisLabel : {
                formatter: '{value} %'
            }
        }
    ],
 
    series : [
        {
            name:'成绩-PR',
            type:'scatter',
            data: chineseGradeAndPrData,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }        
    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );

		// 数学散点   路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/scatter'	// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('prmath')); 
                
                var option = {
                    title : {
        text: '数学',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis',
        showDelay : 0,
        formatter : function (params) {
            if (params.value.length > 1) {
                return params.seriesName + ' :<br/>'
                   + params.value[0] + '分,PR: ' 
                   + params.value[1] + '% ';
            }
            else {
                return params.seriesName + ' :<br/>'
                   + params.name + ' : '
                   + params.value + '% ';
            }
        },  
        axisPointer:{
            show: true,
            type : 'cross',
            lineStyle: {
                type : 'dashed',
                width : 1
            }
        }
    },
    legend: {
        data:['成绩-PR']
    },
    toolbox: {
        show : true,
        feature : {
            //mark : {show: true},
            dataZoom : {show: true},
            //dataView : {show: true, readOnly: false},
            restore : {show: true},
            //saveAsImage : {show: true}
        }
    },
    xAxis : [
        {
            type : 'value',
            scale:true,
            axisLabel : {
                formatter: '{value} 分'
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            scale:true,
            axisLabel : {
                formatter: '{value} %'
            }
        }
    ],
    series : [
        {
            name:'成绩-PR',
            type:'scatter',
            data: mathGradeAndPrData,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }        
    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );

		// 英语散点   路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/scatter'	// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('prenglish')); 
                
                var option = {
                    title : {
        text: '英语',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis',
        showDelay : 0,
        formatter : function (params) {
            if (params.value.length > 1) {
                return params.seriesName + ' :<br/>'
                   + params.value[0] + '分,PR: ' 
                   + params.value[1] + '% ';
            }
            else {
                return params.seriesName + ' :<br/>'
                   + params.name + ' : '
                   + params.value + '% ';
            }
        },  
        axisPointer:{
            show: true,
            type : 'cross',
            lineStyle: {
                type : 'dashed',
                width : 1
            }
        }
    },
    legend: {
        data:['成绩-PR']
    },
    toolbox: {
        show : true,
        feature : {
            //mark : {show: true},
            dataZoom : {show: true},
            //dataView : {show: true, readOnly: false},
            restore : {show: true},
            //saveAsImage : {show: true}
        }
    },
    xAxis : [
        {
            type : 'value',
            scale:true,
            axisLabel : {
                formatter: '{value} 分'
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            scale:true,
            axisLabel : {
                formatter: '{value} %'
            }
        }
    ],
    series : [
        {
            name:'成绩-PR',
            type:'scatter',
            data: mathGradeAndPrData,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }        
    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
  	}   
/* 
	//下拉选择框
	$(document).ready(function(){		
		$(".ui-select").selectWidget({
			change       : function (changes) {
				classyear=$(".ui-select").val();
				location.href = "<c:url value='/StudentGrowServlet?method=loadGrade&classyear='/>"+classyear;
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		});
		
	});	 */
	//下拉选择框
	$(document).ready(function(){		
		$(".ui-select").selectWidget({
			change       : function (changes) {
				var grade= $("#stu-select option:selected").text(); 
				
				 if("选择年级"!=grade){
					 
					 var ajax = new XMLHttpRequest(); //得到ajax对象
					   
					 //设置地址和 方法为post
					 var method = "post";
					 var url ="<c:url value='/StudentGrowServlet?method=loadGrade'/>&time="+new Date().getTime();
					 //alert(url);
					 ajax.open(method, url);
						 
					 //设置请求头
					 ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
					 //发送请求
					 var content="grade="+grade;
					 ajax.send(content);
					 ajax.onreadystatechange = function(){
						 if(ajax.readyState==4 && ajax.status==200){
							 var jsonJava= ajax.responseText;  //这个json是Java书写的json
							 var json = eval("("+jsonJava+")");
							 //alert(jsonJava);
							
							//为语文前后侧图赋值
							chineseBefData = [];// 设置语文前测为空
							chineseAftData = [];// 设置语文后测为空
							chineseGradeCutData = [];// 设置语文前后测相差数据为空
							chineseGradeHighData = [];// 设置语文前后测相差数据起点为空
							
						
							
							var chineseGradeBefList=json[0]['chineseGradeBefList'];
							for(var i=0;i<chineseGradeBefList.length;i++){
								if(chineseGradeBefList[i]['score']==0){
									chineseBefData.push("");
								}else{
									chineseBefData.push(chineseGradeBefList[i]['score']);
								}
							}
							
							var chineseGradeAftList=json[0]['chineseGradeAftList'];
							for(var i=0;i<chineseGradeAftList.length;i++){
								if(chineseGradeAftList[i]['score']==0){
									chineseAftData.push("");
								}else{
									chineseAftData.push(chineseGradeAftList[i]['score']);
								}
							}
							
							var chineseGradeCutList=json[0]['chineseGradeCutList'];
							for(var i=0;i<chineseGradeCutList.length;i++){
								if(chineseGradeCutList[i]['score']==0){
									chineseGradeCutData.push("");
								}else{
									chineseGradeCutData.push(chineseGradeCutList[i]['score']);
								}
							}
							
							var chineseGradeHighList=json[0]['chineseGradeHighList'];
							for(var i=0;i<chineseGradeHighList.length;i++){
								if(chineseGradeHighList[i]['score']==0){
									chineseGradeHighData.push("");
								}else{
									chineseGradeHighData.push(chineseGradeHighList[i]['score']);
								}
							}
							

					
							
							chineseGradeAndPrData = [];
							var chineseGradeAndPr=json[0]['chineseGradeAndPr'];
							for(var i=0;i<chineseGradeAndPr.length;i++){
								var tempData = [];
								tempData.push(chineseGradeAndPr[i]['score']);
								tempData.push(chineseGradeAndPr[i]['pr']);
								chineseGradeAndPrData.push(tempData);
								
							}
							
							//为数学前后侧图赋值
							mathBefData = [];// 设置数学前测为空
							mathAftData = [];// 设置数学后测为空
							mathGradeCutData = [];// 设置数学前后测相差数据为空
							mathGradeHighData = [];// 设置数学前后测相差数据起点为空
							

							var mathGradeBefList=json[0]['mathGradeBefList'];
							for(var i=0;i<mathGradeBefList.length;i++){
								if(mathGradeBefList[i]['score']==0){
									mathBefData.push("");
								}else{
									mathBefData.push(mathGradeBefList[i]['score']);
								}
							}
							
							var mathGradeAftList=json[0]['mathGradeAftList'];
							for(var i=0;i<mathGradeAftList.length;i++){
								if(mathGradeAftList[i]['score']==0){
									mathAftData.push("");
								}else{
									mathAftData.push(mathGradeAftList[i]['score']);
								}
							}
							
							var mathGradeCutList=json[0]['mathGradeCutList'];
							for(var i=0;i<mathGradeCutList.length;i++){
								if(mathGradeCutList[i]['score']==0){
									mathGradeCutData.push("");
								}else{
									mathGradeCutData.push(mathGradeCutList[i]['score']);
								}
							}
							
							var mathGradeHighList=json[0]['mathGradeHighList'];
							for(var i=0;i<mathGradeHighList.length;i++){
								if(mathGradeHighList[i]['score']==0){
									mathGradeHighData.push("");
								}else{
									mathGradeHighData.push(mathGradeHighList[i]['score']);
								}
							}
							
							mathGradeAndPrData = [];
							var mathGradeAndPr=json[0]['mathGradeAndPr'];
							for(var i=0;i<mathGradeAndPr.length;i++){
								var tempData = [];
								tempData.push(mathGradeAndPr[i]['score']);
								tempData.push(mathGradeAndPr[i]['pr']);
								mathGradeAndPrData.push(tempData);
								
							}
							
							//为数学前后侧图赋值
							englishBefData = [];// 设置数学前测为空
							englishAftData = [];// 设置数学后测为空
							englishGradeCutData = [];// 设置数学前后测相差数据为空
							englishGradeHighData = [];// 设置数学前后测相差数据起点为空
							

							var englishGradeBefList=json[0]['englishGradeBefList'];
							for(var i=0;i<englishGradeBefList.length;i++){
								if(englishGradeBefList[i]['score']==0){
									englishBefData.push("");
								}else{
									englishBefData.push(englishGradeBefList[i]['score']);
								}
							}
							
							var englishGradeAftList=json[0]['englishGradeAftList'];
							for(var i=0;i<englishGradeAftList.length;i++){
								if(englishGradeAftList[i]['score']==0){
									englishAftData.push("");
								}else{
									englishAftData.push(englishGradeAftList[i]['score']);
								}
							}
							
							var englishGradeCutList=json[0]['englishGradeCutList'];
							for(var i=0;i<englishGradeCutList.length;i++){
								if(englishGradeCutList[i]['score']==0){
									englishGradeCutData.push("");
								}else{
									englishGradeCutData.push(englishGradeCutList[i]['score']);
								}
							}
							
							var englishGradeHighList=json[0]['englishGradeHighList'];
							for(var i=0;i<englishGradeHighList.length;i++){
								if(englishGradeHighList[i]['score']==0){
									englishGradeHighData.push("");
								}else{
									englishGradeHighData.push(englishGradeHighList[i]['score']);
								}
							}
							
							englishGradeAndPrData = [];
							var englishGradeAndPr=json[0]['englishGradeAndPr'];
							for(var i=0;i<englishGradeAndPr.length;i++){
								var tempData = [];
								tempData.push(englishGradeAndPr[i]['score']);
								tempData.push(englishGradeAndPr[i]['pr']);
								englishGradeAndPrData.push(tempData);
								
							}
							
							pic();
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