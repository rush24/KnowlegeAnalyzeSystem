<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<link href="<c:url value='/index/bower_components/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value='/index/css/login-style.css'/>"/>
		<script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
		<script src="<c:url value='/index/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>
		<title>登录</title> 
		<style type="text/css"> 
			
		</style> 
		
		<script type="text/javascript">
             /*提交验证，异步传输*/ 
            $(document).ready(function(){
            	
            	  $("#acc,#pas").click(
                 		 function(){               			 
                   	   
                   	    $('#fff').hide();
                  });
            	
            	
                 $("form").submit(function (){
                	  if(document.forms.loginform.username.value == ""){
     				  document.getElementById("acc").placeholder="请输入";
                      return false; 
                      }
                	  else if(document.forms.loginform.password.value == ""){
      					document.getElementById("pas").placeholder="请输入";}
                	  else{             
                       $.ajax({                  
                           url: 'CheckLoginServlet',                   
                           dataType: 'text',                  
                           data: $("form").serialize(),                  
                           success: function (msg) {                      
                           if (msg == "no") 
                           {                          
                               document.getElementById("tishi").innerHTML="<span id='fff' style='color:#28BBE6;font-size:15px;'>用户名或密码不正确" ;                                	   
                                return;                      
                                }                  
                           if (msg == "teacherOk"){  
                               window.location.href = "TeacherInfoServlet?method=teaLoadInfo";                      	  
                          }
                           if (msg == "studentOk"){  
                               window.location.href = "StudentInfoServlet?method=stuInfo";                      	  
                          }
                        	  
                          }
                        });          
                     }        
                return false;                         //阻止表单提交     
                });
               
            });                   
	</script>
		
		
	</head> 
	<body> 
		<div class="container">
			<div class="row">
				<!-- <div class="col-xs-2">
				</div> -->
				<div class="col-xs-12 text-center welcome"><p>知诸网</p></div>

			</div>				
			<div class="row main">
				<div class="col-sm-6 text-right">
					<div><img src="<c:url value='/index/image/logo.jpg'/>" class="img-rounded img" width="100%" height="100%"></div>
				</div>
				<div class="col-sm-6 rdiv">
					<div class="border">
						<form id="ddd" action="<%=request.getContextPath() %>/CheckLoginServlet" method="post" name="loginform" class="form">
							<div id="tishi" style="text-align:center;"></div>
							<input class="input" type="text" id="acc" name="username" placeholder="账号"/>
							<input class="input" type="password" id="pas" name="password" placeholder="密码"/>	
							<input type="hidden" name="method" value="checkInfo"/>
							<div class="radio row">
								<div class="col-xs-6"><input type="radio" name="user" value="teacher" checked="checked">
									<span>教师</span>
								</div>
								<div class="col-xs-6"><input type="radio" name="user" value="student">
								<span>学生</span>
								</div>
							</div>
							<button type="submit" id="login_button" onclick="return check(this);" >登录</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body> 
</html> 
