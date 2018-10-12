<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Photon by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/static/assets/css/main.css" />
		<noscript><link rel="stylesheet" href="/resources/static/assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload" style="overflow:hidden;">

		<!-- Header -->
			<section id="header">
				<div class="inner">
				<span class="icon major fa-cloud"></span>

					<h1>PaaS-TA Sample App</h1>
					
					<div class="row">
			  			<div class="col-6">
							<button onclick='procCallAjax("/mysql", mysql)'> Mysql 데이터 가져오기 </button>
							<table>
								<thead>
									<tr>
						                <td>ID</td>
						                <td>Name</td>
						                <td>Email</td>
						                <td>Created on</td>
						            </tr>
								</thead>
								<tbody id="mysqlResult"></tbody>
								<tbody id="ghost" style="visibility:hidden">
									<tr>
						                <td>ID1</td>
						                <td>Name</td>
						                <td>Email</td>
						                <td>Created on</td>
						            </tr>
						            <tr>
						                <td>ID2</td>
						                <td>Name</td>
						                <td>Email</td>
						                <td>Created on</td>
						            </tr>
						            <tr>
						                <td>ID3</td>
						                <td>Name</td>
						                <td>Email</td>
						                <td>Created on</td>
						            </tr>
						            <tr>
						                <td>ID4</td>
						                <td>Name</td>
						                <td>Email</td>
						                <td>Created on</td>
						            </tr>
						            <tr>
						                <td>ID5</td>
						                <td>Name</td>
						                <td>Email</td>
						                <td>Created on</td>
						            </tr>
					            </tbody>
							</table>
						</div>
						<div class="col-6">
							<button onclick='procCallAjax("/postgres", postgres)'> Postgresql 데이터 가져오기 </button>
							<table>
								<thead>
									<tr>
						                <td>ID</td>
						                <td>Name</td>
						                <td>Email</td>
						                <td>Created on</td>
						            </tr>
								</thead>
								<tbody id="postResult"></tbody>
							</table>
						</div>
					</div>

		<!-- Scripts -->
			<script src="/resources/static/assets/js/jquery.min.js"></script>
			<script src="/resources/static/assets/js/browser.min.js"></script>
			<script src="/resources/static/assets/js/breakpoints.min.js"></script>
			<script src="/resources/static/assets/js/main.js"></script>

	</body>
</html>
<script type="text/javascript">

var procCallAjax = function(reqUrl, callback) {
    var reqData = "";
    $.ajax({
        url: reqUrl,
        method: "GET",
        dataType: 'json',
        async: false,
        contentType: "application/json",
        success: function(data) {
            callback(data);
        },
        error: function(xhr, status, error) {
            //alert("api error message");
        },
        complete : function(data) {
            // SKIP
            console.log("COMPLETE :: data :: ", data);
        }
    });
};

var mysql = function(data) {
	var dataStr = [];
	console.log('마이마이', data);
 	$.each(data, function(index, item){
		/* dataStr += JSON.stringify(item) + "\r\n"; */
		console.log('내려놓고 시퐁 ', item.id);
 		dataStr.push('<tr>' +
 				        '<td>' + item.id + '</td>' +
 				        '<td>' + item.name + '</td>' +
 				        '<td>' + item.email + '</td>' +
 				        "<td>" + item.created + "</td>" +
 			        '</tr>');

	});
	$('#mysqlResult').html(dataStr);
	$('#ghost').hide();
}


var postgres = function(data) {
	var dataStr = [];
	console.log('포스포스', data);
	$.each(data, function(index, item){
		/* dataStr += JSON.stringify(item) + "\r\n"; */
		dataStr.push('<tr>' +
			        '<td>' + item.id + '</td>' +
			        '<td>' + item.name + '</td>' +
			        '<td>' + item.email + '</td>' +
			        "<td>" + item.created + "</td>" +
		        '</tr>');
	});
	$('#postResult').html(dataStr);
}

</script>
