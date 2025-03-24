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
                  <div class="container">
              <div class="row row-cols-1 row-cols-md-3">
                  <div class="col mt-3">
                      <h3>List TourSchedule</h3>
                  </div>
                  <div class="col mt-3">
                      <form action="/tourschedule" method="get">
                          <div class="input-group">
                              <input type="text" class="form-control" placeholder="Search..." name="search" aria-label="Search category" aria-describedby="button-addon2">
                              <button class="btn btn-success" type="submit" id="button-addon2">
                                  <i class="fa-solid fa-magnifying-glass"></i>
                              </button>
                          </div>
                      </form>
                  </div>
                  <div class="col mt-3 text-end">
                      <a href="/addtourschedule" type="button" class="btn btn-success text-white">Add TourSchedule</a>
                  </div>
              </div>
              <div class="table-responsive">
                  <table class="table table-striped mt-5">
                      <thead>
                          <tr>
                              <th scope="col">#</th>
                              <th scope="col">Name</th>
                               <th scope="col">Day</th>
                              <th scope="col">Description</th>
                              <th scope="col">Actions</th>
                          </tr>
                      </thead>
                      <tbody>
                          <c:forEach items="${tourSchedule}" var="c">
                              <tr>
                                  <td>${c.id}</td>
                                  <td>${c.name}</td>
                                   <td>${c.dayTour}</td>
                                  <td>${c.description}</td>
                                  <td>
                                      <a href="edittourschedule?id=${c.id}" class="text-white">
                                          <button type="button" class="btn btn-primary">
                                              <i class="fa-solid fa-pen-to-square"></i>
                                          </button>
                                      </a>
                                      <!-- Nút Xóa (Gọi Modal) -->
                                      <button type="button" class="btn btn-danger ms-3 delete-btn" data-bs-toggle="modal" 
                                              data-bs-target="#deleteModal" data-id="${c.id}">
                                          <i class="fa-solid fa-trash-can"></i>
                                      </button>
                                  </td>
                              </tr>
                          </c:forEach>
                      </tbody>
                  </table>
                  <!-- PHÂN TRANG VỚI BOOTSTRAP -->
                  <nav>
                      <ul class="pagination justify-content-center">
                          <c:if test="${currentPage > 1}">
                              <li class="page-item">
                                  <a class="page-link" href="tourschedule?page=${currentPage - 1}">« Previous</a>
                              </li>
                          </c:if>

                          <c:forEach begin="1" end="${totalPages}" var="i">
                              <li class="page-item ${i == currentPage ? 'active' : ''}">
                                  <a class="page-link" href="tourschedule?page=${i}">${i}</a>
                              </li>
                          </c:forEach>

                          <c:if test="${currentPage < totalPages}">
                              <li class="page-item">
                                  <a class="page-link" href="tourschedule?page=${currentPage + 1}">Next »</a>
                              </li>
                          </c:if>
                      </ul>
                  </nav>
              </div>
          </div>

          <!-- Delete Confirmation Modal -->
          <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                          Are you sure you want to delete this tourschedule?
                      </div>
                      <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                          <form id="deleteForm" method="post" action="deletetourschedule">
                              <input type="hidden" name="id" id="deleteId">
                              <button type="submit" class="btn btn-danger">Delete</button>
                          </form>
                      </div>
                  </div>
              </div>
          </div>

          <script>
              document.addEventListener("DOMContentLoaded", function () {
                  let deleteButtons = document.querySelectorAll(".delete-btn");
                  let deleteIdInput = document.getElementById("deleteId");

                  deleteButtons.forEach(button => {
                      button.addEventListener("click", function () {
                          let categoryId = this.getAttribute("data-id");
                          deleteIdInput.value = categoryId;
                      });
                  });
              });
          </script>
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
