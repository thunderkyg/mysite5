<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/mysite5/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="/mysite5/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">

			<!-- //aside -->
			<c:import url="/WEB-INF/views/includes/guestbookaside.jsp"></c:import>

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${pageContext.request.contextPath}" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit" id="btnSubmit">등록</button></td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->

					</form>

					<!-- jQuery 시작 -->
					<div id="listArea"></div>

				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- Footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->


	<!-- 삭제 모달창 -->
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록 삭제</h4>
				</div>
				<div class="modal-body">

					<label for="modalPassword">비밀번호</label> <input id="modalPassword" type="password" name="pass"> <input type="text" name="no">

				</div>
				<div class="modal-footer">
					<button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



</body>

<script type="text/javascript">
	$(document).ready(function() {
		console.log("화면 로딩 직전")

		//ajax 요청
		fetchList();

	});

	$("#listArea").on("click", ".btnDel", function() {
		console.log("삭제버튼")

		var no = $(this).data("no");
		console.log(no)
		$("[name=no]").val(no);

		$("#modalPassword").val("");

		$("#delModal").modal();
	})

	$("#modalBtnDel").on("click", function() {
		console.log("삭제 컨퍼메이션")

		var no = $("[name='no']").val();

		var guestbookVo = {
			guestbook_no : $("[name='no']").val(),
			password : $("[name='pass']").val()
		}

		console.log(guestbookVo)

		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/remove",
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,

			//dataType : "json",
			success : function(count) {
				//성공시 처리해야될 코드 작성
				if (count === 1) {
					//모달창 닫기
					$("#delModal").modal("hide")
					//리스트에 삭제버튼이 있던 테이블 화면에서 지운다

					$("#t-" + no).remove();
				} else {
					$("#delModal").modal("hide")
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	})

	$("#btnSubmit").on("click", function() {
		//전송 기능을 삭제하는 기능 
		event.preventDefault();

		console.log("[btnSubmitonClick]");

		//name값 읽어오기
		var userName = $("#input-uname").val();
		//password값 읽어오기
		var password = $("#input-pass").val();
		//content값 읽어오기
		var content = $("[name = content]").val();

		var guestbookVo = {
			name : userName,
			password : password,
			content : content
		}

		$.ajax({

			//url : "${pageContext.request.contextPath }/api/guestbook/write?name=" + userName + "&password=" + password + "&content=" + content,
			url : "${pageContext.request.contextPath }/api/guestbook/write",
			type : "post",
			//contentType : "application/json",
			data : {
				name : userName,
				password : password,
				content : content
			},

			//dataType : "json",
			success : function(guestbookVo) {
				console.log(guestbookVo);
				render(guestbookVo, "up")

				//입력폼 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name=content]").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		});
	})

	function fetchList() {

		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},

			//dataType : "json",
			success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/

				console.log(guestList)

				//화면에 그리기

				for (var i = 0; i < guestList.length; i++) {

					render(guestList[i], "down");

				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		});
	}

	function render(guestbookVo, type) {

		var str = '<table id="t-' + guestbookVo.guestbook_no + '" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td> ' + guestbookVo.guestbook_no + ' </td>';
		str += '		<td> ' + guestbookVo.name + ' </td>';
		str += '		<td> ' + guestbookVo.reg_date + ' </td>';
		str += '		<td><button class="btnDel" data-no= "' + guestbookVo.guestbook_no + '">삭제</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan="4"> ' + guestbookVo.content + ' </td>';
		str += '	</tr>';
		str += '</table>';

		if (type === 'down') {
			$("#listArea").append(str);
		} else if (type === 'up') {
			$("#listArea").prepend(str);
		} else {
			console.log("방향을 지정해 주세요")
		}

	}
	
	
	
</script>

</html>