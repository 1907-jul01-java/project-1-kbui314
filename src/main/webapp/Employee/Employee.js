window.onload = function(){
	getEmployeeName();
	getRequestTable();
	loadProfile();
}
function getEmployeeName(){
	var request = new XMLHttpRequest();
	request.open('POST', "http://localhost:8080/project1/EmployeeServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = JSON.parse(request.responseText).displayUser;
				var display = document.getElementById("employeeName");
				display.innerHTML = `<h3>${line}</h3>`;
				
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send("methodname=getUserName");
}

function getRequestTable(){
	var request = new XMLHttpRequest();
	request.open('POST', "http://localhost:8080/project1/EmployeeServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = JSON.parse(request.responseText);
				var table = document.getElementById("requestTable");
				var div = "<thead><tr><th>Request ID</th><th>Username</th><th>Description</th><th>Amount</th><th>Status</th><th>Date Created</th><th>Manager</th></tr></thead><tbody>";
				for(var i = 0; i < line.length;i++){
					var count = line[i];
					div += "<tr><td>"+count.request_id+"</td><td>"+count.username+"</td><td>"+count.description+"</td><td>"+count.amount+"</td><td>"+count.status+"</td><td>"+count.date+"</td><td>"+count.manager+"</td></tr>";
				}
				table.innerHTML = div+"</tbody>";
				dataTable();
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send("methodname=getRequestTable");
}

function dataTable(){
	$('#requestTable').DataTable();
}

function createRequestWithImage(base64Image){
	var request = new XMLHttpRequest();
	var description = document.getElementsByName("description")[0].value;
	var amount = document.getElementsByName("amount")[0].value;
	var currentDate = new Date();
	var date = currentDate.getMonth()+"/"+currentDate.getDate()+"/"+currentDate.getFullYear();
	const stringReplace = (string) =>{
		return string.replace(/[+]/g, '%2B');
	}
	var image = stringReplace(base64Image);

	var params = "methodname=requestWithImage"+"&description="+description+"&amount="+amount+"&date="+date+"&img="+image;
	
	request.open('POST', "http://localhost:8080/project1/CreateRequestServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var message = JSON.parse(request.responseText).displayMessage;
				document.getElementById('description').value="";
				document.getElementById('amount').value="";
				alert(message);
				document.location.reload(true);
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(params);
}

function createRequest(){
	var request = new XMLHttpRequest();
	var description = document.getElementsByName("description")[0].value;
	var amount = document.getElementsByName("amount")[0].value;
	var currentDate = new Date();
	var date = currentDate.getMonth()+"/"+currentDate.getDate()+"/"+currentDate.getFullYear();
	var params = "methodname=request"+"&description="+description+"&amount="+amount+"&date="+date;
	
	request.open('POST', "http://localhost:8080/project1/CreateRequestServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var message = JSON.parse(request.responseText).displayMessage;
				document.getElementById('description').value="";
				document.getElementById('amount').value="";
				alert(message);
				document.location.reload(true);
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(params);
}

function convertImage(){
	var image = document.getElementById("image").files;
	
	if(image.length > 0){
		var imageUpload = image[0];
		var fileReader = new FileReader();
		fileReader.onload = function(fileLoadedEvent){
			var base64Before = fileLoadedEvent.target.result;
			console.log(base64Before);
			createRequestWithImage(base64Before);
		}
		fileReader.readAsDataURL(imageUpload);
	}else{
		createRequest();
	}
}

function loadProfile(){
	var request = new XMLHttpRequest();
	request.open('POST', "http://localhost:8080/project1/EmployeeServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var phone = JSON.parse(request.responseText).phoneNumber;
				var email = JSON.parse(request.responseText).email;
				var display = document.getElementById("employeePhone");
				display.innerHTML = `<h3>Phone Number: ${phone}</h3>`;
				display = document.getElementById("employeeEmail");
				display.innerHTML = `<h3>Email: ${email}</h3>`;
				document.getElementById('phonenumber').value = phone;
				document.getElementById('email').value = email;
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(encodeURI("methodname=loadProfile"));
}

function updateProfile(){
	var request = new XMLHttpRequest();
	var phoneNumber = document.getElementsByName("phonenumber")[0].value;
	var email = document.getElementsByName("email")[0].value;
	var params = "methodname=updateProfile"+"&phoneNumber="+phoneNumber+"&email="+email;
	request.open('POST', "http://localhost:8080/project1/EmployeeServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				document.location.reload(true);
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(params);
}

function logout(){
	var request = new XMLHttpRequest();
	request.open('POST', "http://localhost:8080/project1/EmployeeServlet", true);
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

