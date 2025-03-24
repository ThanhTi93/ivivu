<%-- 
    Document   : Location
    Created on : Mar 4, 2025, 2:22:10 PM
    Author     : titranthanh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <!-- link font-awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            <%@ include file="/WEB-INF/css/LayoutAdmin.css" %>
        </style>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
            />
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    </head>
    <body>
        <div class="container-fluid admin">
            <%@ include file="/WEB-INF/admin/includes/Menu.jsp" %>
            <div class="right" id="main-content">
                <%@ include file="/WEB-INF/admin/includes/Header.jsp" %>
                <div >
                    <div class="container mt-5">
                        <h2 class="text-center">Add TourStartTime</h2>
                        <form action="addtourstarttime" method="post" class="p-4 shadow-lg rounded bg-light mt-3">                       
                            <div class="mb-3">
                                <label class="form-label">Date</label>
                                <input type="date" name="date" class="form-control" required />
                            </div>                                 
                            <div class="mb-3">
                                <label class="form-label">Price</label>
                                <input type="number" class="form-control" name="price" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Quantity</label>
                                <input type="number" class="form-control" name="quantity" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Discount</label>
                                <input type="number" class="form-control" name="discount" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Tour</label>
                                <select class="form-select" name="categoryId" aria-label="Default select example">
                                    <option selected disabled>Vui lòng chọn Category</option>
                                    <c:forEach var="o" items="${tourPackages}">
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>                          
                    <!-- Nút Hành Động -->
                    <div class="d-flex justify-content-between">
                        <a href="location" class="btn btn-secondary">
                            <i class="fa-solid fa-arrow-left"></i> Quay Lại
                        </a>
                        <button type="submit" class="btn btn-success">
                            <i class="fa-solid fa-plus"></i> Add
                        </button>
                    </div>
                    </form>
                </div>
            </div>
            <script>
                <%@ include file="/WEB-INF/js/LayoutAdmin.js" %>
            </script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                    integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
            crossorigin="anonymous"></script>
    </body>
</html>
