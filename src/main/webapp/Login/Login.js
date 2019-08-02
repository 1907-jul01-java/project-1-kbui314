/**
 * 
 */

let button = document.getElementById("button");
button.addEventListener("click",processLogin);

function processLogin(){
	var request = new XMLHttpRequest();
	var username = document.getElementsByName("username")[0].value;
	var userpassword = document.getElementsByName("password")[0].value;
	var params = "username=" + username + "&userpassword=" + userpassword;
	request.open('POST', "http://localhost:8080/project1/LoginServlet", true);
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var line = request.responseText;
				console.log(line);
				console.log("Test reponse");
			}
		}
	};
	request.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	request.send(params);
}