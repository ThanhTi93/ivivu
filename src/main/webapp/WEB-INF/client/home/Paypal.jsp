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

        <div class="container mt-5">
            <h2 class="mb-4">Thanh Toán</h2>
            <form action="payment" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Tour Quantity</label>
                    <input type="number" class="form-control"  name="quantity" placeholder="Enter tour name" required>
                </div>

                <div class="mb-3">
                    <label for="price" class="form-label">Price ($)</label>
                    <input type="number" class="form-control"  name="price" placeholder="Enter price" required>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Phone</label>
                    <input type="text" class="form-control"  name="phone" placeholder="Enter price" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>

        <%@ include file="/WEB-INF/client/includes/Footer.jsp" %>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
    </body>
</html>
