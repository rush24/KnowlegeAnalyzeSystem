
function getname(obj){
	photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	if(photoExt!='.csv'){
		alert("请上传后缀名csv的照片!");
		obj.value = "";
		document.getElementById('filename').innerHTML=obj.value;
	    return false;
	}else{
		document.getElementById('filename').innerHTML=obj.value;
	}
}
function clearname(){
	document.getElementById('filename').innerHTML="";
}

function clearupload(){
	document.getElementById('labinfo1').style.display='none';
	document.getElementById('ok-circle1').style.display='none';
	document.getElementById('labinfo2').style.display='none';
	document.getElementById('ok-circle2').style.display='none';
	document.getElementById('labinfo3').style.display='none';
	document.getElementById('ok-circle3').style.display='none';
	document.getElementById('okbtn').style.display='none';
	window.location.reload();
}
function changeToMyMap(course){
    window.location.href = "KnowlegeMapServlet?method=getMapData&course=" + course + "&mapType=customerMadeMap";                      	  
}
function changeToVosMap(course){
    window.location.href = "KnowlegeMapServlet?method=getMapData&course=" + course + "&mapType=vosMap";                      	  
}
