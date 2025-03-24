<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách Tour</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <style>
        <%@ include file="/WEB-INF/css/LayoutAdmin.css" %>
    </style>
</head>

<body>
    <%@ include file="/WEB-INF/client/includes/Header.jsp" %>

    <div class="container mt-3">
        <h2 class="text-center mb-4">Danh sách Tour</h2>

        <div class="row row-cols-xl-3 row-cols-md-2 row-cols-1 g-3">
            <!-- Duyệt qua danh sách tour bằng JSTL -->
            <c:choose>
                <c:when test="${not empty toursWithImages}">
                    <c:forEach var="tour" items="${toursWithImages}">
                        <div class="col">
                            <div class="card p-2 shadow-sm">
                                <img src="${tour.img_url != null ? tour.img_url : 'default.jpg'}" class="card-img-top" alt="Hình ảnh tour">
                                <div class="card-body">
                                    <h5 class="card-title">${tour.name}</h5>
                                    <p class="card-text">${tour.namelocation}</p>
                                    <a href="tourDetail?id=${tour.id}" class="btn btn-primary">Chi tiết</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Không có tour nào để hiển thị.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <%@ include file="/WEB-INF/client/includes/Footer.jsp" %>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
