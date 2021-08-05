<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //해더 네비 -->


		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/asideGallery.jsp"></c:import>
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">

						<c:if test="${authUser != null}">
							<button id="btnImgUpload">이미지올리기</button>
						</c:if>
						<div class="clear"></div>


						<ul id="viewArea">

							<c:forEach items="${getList}" var="GalleryVo" varStatus="status">
								<!-- 이미지반복영역 -->
								<li>
									<div class="view" id="v-${GalleryVo.no}" >
										<img id="imgitem" class="imgItem" src="${pageContext.request.contextPath}/upload/${GalleryVo.saveName}" data-no="${GalleryVo.no}">
										<div class="imgWriter">
											작성자: <strong>${GalleryVo.name}</strong>
										</div>
									</div>
								</li>
								<!-- 이미지반복영역 -->
							</c:forEach>

						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->

	<!-- ****************************************POP UP********************************************* -->
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath}/gallery/insert" enctype="multipart/form-data">
					<input type="hidden" name="no" value="${no}">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" id="xclose" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div id="modalview" class="modal-body"></div>
				<form method="post" action="${pageContext.request.contextPath}/gallery/delete">
					<div class="modal-footer">
						<input type="hidden" id="vno" value="">

						<button type="button" id="viewclose" class="btn btn-default" data-dismiss="modal">닫기</button>

						<c:if test="${authUser != null}">
							<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
						</c:if>

					</div>


				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
	$("#gallery").on("click", "#btnImgUpload", function() {

		$("#addModal").modal();
	})

	$("#gallery").on("click", "#imgitem", function() {

		var no = $(this).data("no");
		console.log(no)

		$.ajax({

			url : "${pageContext.request.contextPath }/gallery/view",
			type : "post",
			//contentType : "application/json",
			data : {
				no : no,
			},

			//dataType : "json",
			success : function(galleryVo) {
				
				$("#viewModal").modal();
				
				render(galleryVo);
				
				$("#vno").val(no);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		})

	})

	$("#btnDel").on("click", function() {

		var no = $("#vno").val();
		console.log(no);
		$.ajax({

			url : "${pageContext.request.contextPath }/gallery/delete",
			type : "post",
			//contentType : "application/json",
			data : {
				no : no,
			},

			//dataType : "json",
			success : function(count) {

				$("#v-" + no).remove();
				$("#viewModal").hide();

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		})
	})

	$("#viewclose").on("click", function() {

		$("#imageArea").remove();

	})

	$("#xclose").on("click", function() {

		$("#imageArea").remove();

	})

	function render(galleryVo) {

		var str = '<div id="imageArea">'
		str += '<div class="formgroup">'
		str += '<img id="viewModelImg" src="${pageContext.request.contextPath}/upload/' + galleryVo.saveName + '">'
		str += '</div>'
		str += '<div class="formgroup">'
		str += '<p id="viewModelContent">' + galleryVo.content + '</p>'
		str += '</div>'
		str += '</div>'

		$("#modalview").append(str)
	}
</script>

</html>
