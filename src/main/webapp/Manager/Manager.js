window.onload = function(){
	getManagerName();
	getRequestTable();
	Employees();
}
function getManagerName(){
	var request = new XMLHttpRequest();
	request.open('POST', "http://localhost:8080/project1/ManagerServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = JSON.parse(request.responseText).displayUser;
				
				var display = document.getElementById("managerName");
				display.innerHTML = `<h1 class="text-center">Manager: ${line}</h1>`;
				
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(encodeURI("methodname=getUserName"));
}

function getRequestTable(){
	var request = new XMLHttpRequest();
	var manage;
	request.open('POST', "http://localhost:8080/project1/ManagerServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = JSON.parse(request.responseText);
				
				var table = document.getElementById("requestTable");
				var div;
				div = "<thead><tr><th>Request ID</th><th>Username</th><th>Description</th><th>Amount</th><th>Status</th><th>Date Created</th><th>Accept/Deny</th></tr></thead><tbody>";
				for(var i = 0; i < line.length;i++){
/*					if(count.status == "Accepted" || count.status == "Denied"){
						manage = "";
					}*/
					var count = line[i];
					div += "<tr><td>"+count.request_id+"</td><td>"+count.username+"</td><td>"+count.description+"</td><td>"+count.amount+"</td><td>"+count.status+"</td><td>"+count.date+"</td>" ;
					if(count.manager == 'N/A'){
						div +=	"<td><button type='button' onClick='Accept("+count.request_id+")'>Accept</button><button type='button' onClick='Deny("+count.request_id+")'>Deny</button></td>";
					}
					if(count.manager != 'N/A'){
						div += "<td>Manager: "+count.manager+"</td>";
					}
					div+= "</tr>";
				}
				table.innerHTML = div+"</tbody>";
				dataRequestTable();
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send("methodname=getRequestTable");
}


function dataRequestTable(){
	var table = $('#requestTable').DataTable();
	$('#requestTable tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        getImage(data[0]);
    } );
}

function getImage(id){
	var request = new XMLHttpRequest();
	var action = "getImage";
	var params = "methodname="+ action + "&requestId=" + id;
	request.open('POST', "http://localhost:8080/project1/ManagerServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var JSONImage = JSON.parse(request.responseText).image;
			
				var display = document.getElementById("showImage");
				if(JSONImage == "No images were uploaded"){
					display.innerHTML = `<h3 class="text-center">${JSONImage}</h3>`;
				}else{
					var imageData = JSONImage;
					var imageView = document.createElement('img');
					imageView.src = imageData;
					display.innerHTML = imageView.outerHTML;
				}
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(params);
}

function Accept(request_id){
	var request = new XMLHttpRequest();
	var id = request_id; 
	var action = "Accepted"
	var params = "methodname="+ action + "&requestId=" + id;
	request.open('POST', "http://localhost:8080/project1/ManagerServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = JSON.parse(request.responseText);
				
				document.location.reload(true);
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(params);
}

function Deny(request_id){
	var request = new XMLHttpRequest();
	var id = request_id; 
	var action = "Denied"
	var params = "methodname="+ action + "&requestId=" + id;
	request.open('POST', "http://localhost:8080/project1/ManagerServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = JSON.parse(request.responseText);
				
				document.location.reload(true);
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(params);
}

function Employees(){
	var request = new XMLHttpRequest();
	request.open('POST', "http://localhost:8080/project1/ManagerServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = JSON.parse(request.responseText);
				
				var table = document.getElementById("employeeList");
				var div = "<thead><tr><th>Username</th><th>First Name</th><th>Last Name</th><th>Category</th><th>Phone Number</th><th>Email</th></tr></thead><tbody>";
				for(var i = 0; i < line.length;i++){
					var count = line[i];
					div += "<tr><td>"+count.username+"</td><td>"+count.firstName+"</td><td>"+count.lastName+"</td><td>"+count.category+"</td><td>"+count.phoneNumber+"</td><td>"+count.email+"</td></tr>";
				}
				table.innerHTML = div+"</tbody>";
				dataEmployeeTable();
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(encodeURI("methodname=getEmployees"));
}

function dataEmployeeTable(){
	$('#employeeList').DataTable();
}


function logout(){
	var request = new XMLHttpRequest();
	request.open('POST', "http://localhost:8080/project1/ManagerServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				window.location="http://localhost:8080/project1/Login.html";
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(encodeURI("methodname=logout"));
}


