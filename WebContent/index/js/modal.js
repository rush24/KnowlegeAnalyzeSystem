function show(){
	var yn = document.getElementById("yn");
	var c = document.getElementById("c");
	yn.innerHTML = "正在导出...";
	c.style.display = "block";
	/*var a = 1;
	if(a == 1){
		alert(a);
		c.style.display = "none";
	}*/
}
function hide(){
	var yn = document.getElementById("yn");
	var c = document.getElementById("c");
	yn.innerHTML = "是否导出该年级的PDF";
	c.style.display = "none";
	/*var a = 1;
	if(a == 1){
		alert(a);
		c.style.display = "none";
	}*/
}