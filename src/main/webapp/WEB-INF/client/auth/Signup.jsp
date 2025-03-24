<%-- 
    Document   : Location
    Created on : Mar 4, 2025, 2:22:10 PM
    Author     : titranthanh
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <%@ include file="/WEB-INF/client/includes/Header.jsp" %>
        <div class="container d-flex justify-content-center align-items-center mt-5">
            <div class="card p-4 shadow" style="width: 400px;">
                <h3 class="text-center mb-4">Đăng ký</h3>
                <form action="signup" method="post">
                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input type="text" name="username" class="form-control" placeholder="Ví dụ: Nguyen Van A" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" placeholder="email@gmail.com" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mật khẩu *</label>
                        <input type="password" name="password" class="form-control" required>

                    </div>

                    <div class="mb-3">
                        <label class="form-label">Nhập lại mật khẩu *</label>
                        <input type="password" name="confirmPassword" class="form-control" required>

                    </div>
                    <p class="text-center text-danger">${error}</p>
                    <button type="submit" class="btn btn-warning w-100 mb-2">Đăng ký</button>

                    <div class="text-center mb-2">Hoặc</div>

                    <div class="d-flex justify-content-between">
                        <button type="button" class="btn btn-primary w-50 me-1"><i class="fab fa-facebook-f"></i> Facebook</button>
                        <button type="button" class="btn btn-danger w-50 ms-1"><i class="fab fa-google"></i> Google</button>
                    </div>

                    <p class="text-center mt-3 text-muted" style="font-size: 0.9rem;">
                        Bằng cách đăng ký, Quý khách đồng ý tất cả <a href="#">điều kiện & điều khoản</a> của chúng tôi.
                    </p>
                </form>
            </div>
        </div>
        <%@ include file="/WEB-INF/client/includes/Footer.jsp" %>    
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
    </body>
</html>
