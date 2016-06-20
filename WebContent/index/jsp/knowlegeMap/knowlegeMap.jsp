<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">

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
	<!-- jQuery -->
	<script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js '/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/knowlegeMap.css'/>" />
	<link rel="stylesheet" href="<c:url value='/index/css/modal.css'/>" />
<title>知诸网</title>

<script
	src="http://maps.google.cn/maps/api/js?key=AIzaSyADfs5Foq0tlpP8ZSyrwy1vzbLghl5OsYg&callback=initMap">
</script>

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
										<a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=语文&mapType=vosMap'/>">语文</a>
		                            </li>
									<li>
		                                <a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=数学&mapType=vosMap'/>">数学</a>
		                            </li>
		                            <li>
		                                <a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=英文&mapType=vosMap'/>">英文</a>
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
			<!-- 搜索按钮 -->
			<div class="row">
			<div class="input-group custom-search-form col-xs-9 sea pull-left">
				<input type="text" id="searchText" class="form-control" placeholder="搜索知识点..." onkeypress="pressKey()">
				<span class="input-group-btn">
				<button id="searchButton" class="btn btn-default" type="button">
			    		<i class="fa fa-search"></i>
				</button>
				</span>
			</div>
			<!-- 模态框 -->
			<c:if test="${sessionScope.hasCustomerMade == true && sessionScope.chooseCustomerMade == true }">
				<button class="btn btn-default btn-lg pull-right makemap" onclick="changeToVosMap('${sessionScope.course }');">学立方地图</button>
			</c:if>
			<c:if test="${sessionScope.hasCustomerMade == true && sessionScope.chooseCustomerMade == false }">
				<button class="btn btn-default btn-lg pull-right makemap" onclick="changeToMyMap('${sessionScope.course }');">我的地图</button>
			</c:if>
			<c:if test="${sessionScope.hasCustomerMade == false }">
			
				<button class="btn btn-default btn-lg pull-right makemap" data-toggle="modal" data-target="#makemap" data-backdrop="false">
				   按需构建
				</button>
			</c:if>
			</div>
			<!-- 上传文件构建地图 -->
			<form  name="myform" action="<c:url value='/KnowlegeMapServlet?method=fileUpload'/>" method="post"
       			enctype="multipart/form-data">
			<div class="modal fade" id="makemap" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog">
				  <div class="modal-content">
					 <div class="modal-header">
						<button type="button" class="close" 
						   data-dismiss="modal" aria-hidden="true" onclick="clearname();">
							  &times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
						   请上传需构建的原始数据
						</h4>
					 </div>
					 <div class="modal-body text-center">
						<div style="margin-top:20px;">
							<button type="button" class="btn btn-default" data-toggle="modal" data-target="#seetemplet" data-backdrop="false">
								查看模板
							</button>
						</div>
						<div style="margin-top:20px;">
							<!-- <input type="file" class="btn btn-default" value="选择文件"> -->
							<a href="javascript:;" class="file">选择文件
								<input type="file" id="fileToUpload" name="fileToUpload" size="20" onchange="getname(this);"/>
							</a>
						</div>
						<span id="filename"></span>
						<div style="margin:15px 0;">
<!-- 							<input type="submit"  class="btn btn-default" value="提交文件" data-toggle="modal" data-target="#upload" data-backdrop="false" onclick="uploadinfo();">
 -->							<!-- </input> -->
							<button type="button" id="buttonUpload" class="btn btn-default" data-toggle="modal" data-target="#upload" data-backdrop="false" onclick="upload();" >
								上传文件
							</button>
						</div>
					 </div>
				  </div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
			</form>
		    <!-- 查看模板 -->
			<div class="modal fade" id="seetemplet" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog">
				  <div class="modal-content">
					 <div class="modal-header">
						<button type="button" class="close" 
						   data-dismiss="modal" aria-hidden="true">
							  &times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
						   数据样例
						</h4>
					 </div>
					 <div class="modal-body">
						<p>
							该数据为所有学生在该科目做过的所有错题的知识点，可以通过数据库工具利用SQL语句查询后导出Excel文件。<strong>请按照如下所述以及图样创建数据表: </strong><br /><br />
							<span class="glyphicon glyphicon-star-empty">&nbsp;</span>第一列字段:学生ID(如:"01")，<br />
							<span class="glyphicon glyphicon-star-empty">&nbsp;</span>第二列字段:错误知识点名称(如:"三角函数"，注意不是错题题目)，<br />
							<span class="glyphicon glyphicon-star-empty">&nbsp;</span>第三列字段:错误知识点ID(ID需能表示知识点出现时间的前后顺序，如二年级的知识点ID一定比一年级的大)，<br />
							<span class="glyphicon glyphicon-star-empty">&nbsp;</span>第四列字段:错误知识点所属年级(如"一年级"，注意:只能以一至九年级命名)。
						</p><br />
						<div class="text-center">
							<img src="<c:url value='/index/image/templet-table.png'/>" alt="模板表格"  style="width:90%;"/>
						</div>
					 </div>
				  </div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
			<!-- 上传文件 -->
			<div class="modal fade" id="upload" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clearupload();">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								正在构建你的知识地图，请稍后...
							</h4>
						</div>
						<div class="modal-body">
							<div class="lab">
								<span class="text-left labinfo" id="labinfo1">正在筛选处理数据...</span>
								<span class="glyphicon glyphicon-ok-circle pull-right ok-circle" id="ok-circle1"></span>
							</div>
							<div class="lab">
								<span class="text-left labinfo" id="labinfo2">正在关联知识点...</span>
								<span class="glyphicon glyphicon-ok-circle pull-right ok-circle" id="ok-circle2"></span>
							</div>
							<div class="lab">
								<span class="text-left labinfo" id="labinfo3">正在构建地图...</span>
								<span class="glyphicon glyphicon-ok-circle pull-right ok-circle" id="ok-circle3"></span>
							</div>
							<div class="lab text-center">
								<button class="btn btn-info ok" id="okbtn" class="close" data-dismiss="modal" aria-hidden="true" onclick="clearupload();">构建</button>
							</div>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
			<div id="map"></div>
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
USGSOverlay.prototype = new google.maps.OverlayView();
var map;
var remainRemovedPaths = [];
function initMap() {
	  map = new google.maps.Map(document.getElementById('map'), {
	    center: {lat: 45, lng: 55},
	    zoom: 7,
	    streetViewControl: false,
	    mapTypeControlOptions: {
	      mapTypeIds: ['moon']
	    }
	  });

	  var moonMapType = new google.maps.ImageMapType({
	    getTileUrl: function(coord, zoom) {
	        var normalizedCoord = getNormalizedCoord(coord, zoom);
	        if (!normalizedCoord) {
	          return null;
	        } 
	        var bound = Math.pow(2, zoom); 
            return "<c:url value='/imag/background.jpg'/>";
	    },
	    tileSize: new google.maps.Size(256, 256),
	    maxZoom: 10,
	    minZoom: 5,
	    radius: 1738000,
	    name: 'Moon'
	  });

	  map.mapTypes.set('moon', moonMapType);
	  map.setMapTypeId('moon');


	var flightPath;

	<c:forEach items="${keywordLineList}" var="keywordNodeArr">
		flightPath = new google.maps.Polyline({
			path : [ {
				lat : ${keywordNodeArr[0].latitude} + 0.05,
				lng : ${keywordNodeArr[0].longtitude} + 0.05
			}, {
				lat : ${keywordNodeArr[1].latitude} + 0.05,
				lng : ${keywordNodeArr[1].longtitude} + 0.05
			} ],
			geodesic : false,
			strokeColor : '#4A4A4A',
			strokeOpacity : 1,
			strokeWeight : 0.5
		});

		flightPath.setMap(map);
	</c:forEach>

	var srcImage = "<c:url value='/imag/icon.png'/>";
	var name;
	var bounds;
	var overlay;
	<c:forEach items="${keywordNodeList}" var="keywordNode">
		name = "${keywordNode.name}"; 
		bounds = new google.maps.LatLngBounds(
			new google.maps.LatLng(${keywordNode.latitude},${keywordNode.longtitude}), 
			new google.maps.LatLng((${keywordNode.latitude} + 0.1), (${keywordNode.longtitude} + 0.1))
		);  
		srcImage = "<c:url value='/imag/grade'/>" + ${keywordNode.gradeIndex} + ".png";
		overlay = new USGSOverlay(bounds, srcImage, name, map);
	</c:forEach>
				
}

	// Normalizes the coords that tiles repeat across the x axis (horizontally)
	// like the standard Google map tiles.
	function getNormalizedCoord(coord, zoom) {
	  var y = coord.y;
	  var x = coord.x;

	  // tile range in one direction range is dependent on zoom level
	  // 0 = 1 tile, 1 = 2 tiles, 2 = 4 tiles, 3 = 8 tiles, etc
	  var tileRange = 1 << zoom;

	  // don't repeat across y-axis (vertically)
	  if (y < 0 || y >= tileRange) {
	    return null;
	  }

	  // repeat across x-axis
	  if (x < 0 || x >= tileRange) {
	    x = (x % tileRange + tileRange) % tileRange;
	  }

	  return {x: x, y: y};
	}

	/** @constructor */
	function USGSOverlay(bounds, image, name, map) {

		// Initialize all properties.
		this.bounds_ = bounds;
		this.image_ = image;
		this.name_ = name;
		this.map_ = map;

		// Define a property to hold the image's div. We'll
		// actually create this div upon receipt of the onAdd()
		// method so we'll leave it null for now.
		this.imgdiv_ = null;
		this.spandiv_ = null;
		this.btndiv_ = null;
		// Explicitly call setMap on this overlay.
		this.setMap(map);
	}

	/**
	 * onAdd is called when the map's panes are ready and the overlay has been
	 * added to the map.
	 */
	USGSOverlay.prototype.onAdd = function() {

		var imgdiv = document.createElement('div');
		imgdiv.style.borderStyle = 'none';
		imgdiv.style.borderWidth = '0px';
		imgdiv.style.position = 'absolute';
 
		// Create the img element and attach it to the div.
		var img = document.createElement('img');
		img.src = this.image_;
  		img.style.width = '100%';
		img.style.height = '100%';
		img.style.position = 'absolute';  
		imgdiv.appendChild(img);

		var spandiv = document.createElement('div');
		spandiv.style.borderStyle = 'none';
		spandiv.style.borderWidth = '0px';
		spandiv.style.position = 'absolute'; 

		// Create label element and attach it to the div
		var span = document.createElement('span');
		span.innerHTML = this.name_;
		span.style.color = "black";
		spandiv.appendChild(span);

		var btndiv = document.createElement('div');
		btndiv.style.borderStyle = 'none';
		btndiv.style.borderWidth = '0px';
		btndiv.style.position = 'absolute';
		
		var button = document.createElement('button');
		button.style.width = '100%';
		button.style.height = '100%';
		button.style.opacity = "0";
 		button.setAttribute("id",this.name_);
 		button.setAttribute("onclick","findSourcePath(this.id);");
 		btndiv.appendChild(button);
 		
		this.imgdiv_ = imgdiv;
		this.spandiv_ = spandiv;
		this.btndiv_ = btndiv;
		// Add the element to the "overlayLayer" pane.
		var panes = this.getPanes();
		//panes.setAttribute("style","border:2px solid #0026ff");
		panes.overlayImage.appendChild(imgdiv);
		panes.overlayImage.appendChild(spandiv);
		panes.overlayMouseTarget.appendChild(btndiv);
	};

	USGSOverlay.prototype.draw = function() {

		// We use the south-west and north-east
		// coordinates of the overlay to peg it to the correct position and size.
		// To do this, we need to retrieve the projection from the overlay.
		var overlayProjection = this.getProjection();

		// Retrieve the south-west and north-east coordinates of this overlay
		// in LatLngs and convert them to pixel coordinates.
		// We'll use these coordinates to resize the div.
		var sw = overlayProjection.fromLatLngToDivPixel(this.bounds_
				.getSouthWest());
		var ne = overlayProjection.fromLatLngToDivPixel(this.bounds_
				.getNorthEast());

		// Resize the image's div to fit the indicated dimensions.
		var idiv = this.imgdiv_;
		idiv.style.left = sw.x + 'px';
		idiv.style.top = ne.y + 'px';
		idiv.style.width = (ne.x - sw.x) + 'px';
		idiv.style.height = (ne.x - sw.x) + 'px';

		var sdiv = this.spandiv_;
		if (this.map_.zoom <= 8) {
			sdiv.style.display = "none";
		} else {
			sdiv.style.display = "block";
			sdiv.style.left = idiv.style.left;
			sdiv.style.top = ne.y +(ne.x - sw.x) + 'px';
	   		sdiv.style.width = (ne.x - sw.x) + 'px';
	 		sdiv.style.textAlign = "center";
		}
		
		var bdiv = this.btndiv_;
		bdiv.style.left = sw.x + 'px';
		bdiv.style.top = ne.y + 'px';
		bdiv.style.width = (ne.x - sw.x) + 'px';
		bdiv.style.height = (ne.x - sw.x) + 'px';
};
		
	//The onRemove() method will be called automatically from the API if
	//we ever set the overlay's map property to 'null'.
	USGSOverlay.prototype.onRemove = function() {
		this.imgdiv_.parentNode.removeChild(this.imgdiv_);
		this.spandiv_.parentNode.removeChild(this.spandiv_);
		this.imgdiv_ = null;
		this.spandiv_ = null;
	};

	google.maps.event.addDomListener(window, 'load', initMap);
	
	//朔源按钮事件
	function findSourcePath(keywordName){
		$.ajax({
			type: "post",
			data:{  
			    method : "findSourcePath",  
			    keywordName : keywordName
				}, 
			url: "<c:url value='/KnowlegeMapServlet'/>",
			dataType: "json",
			success: function (json) {
				removeRemainPaths();//删除之前的朔源路径
				for (var i = 0; i < json[0].length; i++) {	
					var flightPath = new google.maps.Polyline({
						path : [ {
							lat : json[0][i][0].latitude + 0.05,
							lng : json[0][i][0].longtitude + 0.05
						}, {
							lat : json[0][i][1].latitude + 0.05,
							lng : json[0][i][1].longtitude + 0.05
						} ],
						geodesic : false,
						strokeColor : '#FF0000',
						strokeOpacity : 1,
						strokeWeight : 2.0
					});

					flightPath.setMap(map); 
					remainRemovedPaths.push(flightPath);
				}

				for (var i = 0; i < json[1].length; i++) {	
					var flightPath = new google.maps.Polyline({
						path : [ {
							lat : json[1][i][0].latitude + 0.05,
							lng : json[1][i][0].longtitude + 0.05
						}, {
							lat : json[1][i][1].latitude + 0.05,
							lng : json[1][i][1].longtitude + 0.05
						} ],
						geodesic : false,
						strokeColor : '#4A90E2',
						strokeOpacity : 1,
						strokeWeight : 2.0
					});

					flightPath.setMap(map); 
					remainRemovedPaths.push(flightPath);
				}
			}, 
			error: function () {
				alert("加载失败");
			}
		}); 
	}
	//删除之前的朔源路径
	function removeRemainPaths(){
		for(var i = 0;i < remainRemovedPaths.length;i++)
			remainRemovedPaths[i].setMap(null);
	}
	
	//回车搜索
	function pressKey(){	
		if((event.keyCode == 13)){
			var keywordName = $("#searchText").val();
			$.ajax({
				type: "post",
				data:{  
				    method : "searchKeywordNode",  
				    keywordName : keywordName
					}, 
				url: "<c:url value='/KnowlegeMapServlet'/>",
				dataType: "json",
				success: function (json) {
					map.setCenter(new google.maps.LatLng(json['latitude'],json['longtitude']));
					map.setZoom(9);
				}, 
				error: function () {
					alert("加载失败");
				}
			}); 
		}	
	}

	var url = "<c:url value='/KnowlegeMapServlet?method=fileUpload'/>";
	function upload(){
		$.ajaxFileUpload({
	        url:url,//处理图片脚本
	        secureuri :false,
	        fileElementId :'fileToUpload',//file控件id
	        dataType : 'json',
	        success : function (data, status){
	        		showlab1();  
	   	    		if(data['status'] == 1){
					showcircle1();                  	  
					url = "<c:url value='/KnowlegeMapServlet?method=AssociateKeywordsWithR'/>";
					upload();
					showlab2();
			   	}
	   	    		if(data['status'] == 2){
					showcircle2(); 
					showbtn();
				}
	
	        },
	        error: function(data, status, e){
	            alert(e);
	        }
	})
	}
	function showlab1(){
		document.getElementById('labinfo1').style.display='inline-block';
	}
	function showcircle1(){
		document.getElementById('ok-circle1').style.display='block';
	}
	function showlab2(){
		document.getElementById('labinfo2').style.display='inline-block';
	}
	function showcircle2(){
		document.getElementById('ok-circle2').style.display='block';
	}
	function showlab3(){
		document.getElementById('labinfo3').style.display='inline-block';
	}
	function showcircle3(){
		document.getElementById('ok-circle3').style.display='block';
	}
	function showbtn(){
		document.getElementById('okbtn').style.display='inline-block';
	}

	
	$(document).ready(
		function() {
			//搜索按钮事件
			$("#searchButton").bind("click", function () {
				var keywordName = $("#searchText").val();
				$.ajax({
					type: "post",
					data:{  
					    method : "searchKeywordNode",  
					    keywordName : keywordName
						}, 
					url: "<c:url value='/KnowlegeMapServlet'/>",
					dataType: "json",
					success: function (json) {
						map.setCenter(new google.maps.LatLng(json['latitude'],json['longtitude']));
						map.setZoom(9);
					}, 
					error: function () {
						alert("加载失败");
					}
				}); 
			});

			/*$("#buttonUpload").click(function(){  
			    $.ajaxFileUpload({
			        url:"<c:url value='/KnowlegeMapServlet?method=fileUpload'/>",//处理图片脚本
			        secureuri :false,
			        fileElementId :'fileToUpload',//file控件id
			        dataType : 'json',
			        success : function (data, status){
			        	upload();
			        	test1();
			            if(typeof(data.error) != 'undefined'){
			                if(data.error != ''){
			                    alert(data.error);
			                }else{
			                    alert(data.msg);
			                }
			            }
			        },
			        error: function(data, status, e){
			            alert(e);
			        }
			})
			return false;
			  })*/ 
		}

	);
</script>
	<!-- 模态框js -->
	<script src="<c:url value='/index/js/modal.js'/>"></script>
	
	<script src="<c:url value='/index/js/checkFile.js'/>"></script>
	<!-- ajaxfileupload.js -->
	<script src="<c:url value='/index/js/ajaxfileupload.js'/>"></script>
	<!-- 构建知识地图模态框js -->
	<script src="<c:url value='/index/js/knowlegeMap.js'/>"></script>
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