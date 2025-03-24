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
                    <div class="container">
                        <h2 class="text-center">Add TourPackages</h2>
                        <form action="addtourpackages" method="post" enctype="multipart/form-data" class="p-4 shadow-lg rounded bg-light mt-4">
                            <div class="row row-cols-1 row-cols-lg-3">
                                <div class="col">
                                    <div class="mb-3">
                                        <label class="form-label">Name</label>
                                        <input type="text" class="form-control" name="name" placeholder="Nhập tên Tourpackage" required>
                                    </div>                                 
                                    <div class="mb-3">
                                        <label class="form-label">Description</label>
                                        <textarea class="form-control" name="description" rows="3" placeholder="Nhập mô tả" required></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Location</label>
                                        <select class="form-select" name="locationId" aria-label="Default select example">
                                            <option selected disabled>Vui lòng chọn Location</option>
                                            <c:forEach var="location" items="${locations}">
                                                <option value="${location.id}">${location.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div> 
                                </div>
                                <div class="col">
                                    <div class="mb-3">
                                        <label class="form-label">TourService</label>
                                        <div class="row row-cols-2">
                                            <c:forEach var="service" items="${listTourService}">
                                                <div class="col">
                                                    <input class="form-check-input" type="checkbox" id="${service.id}" name="tourService[]" value="${service.id}">
                                                    <label class="form-check-label" for="${service.id}">${service.name}</label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Transportation</label>
                                        <div class="row row-cols-2">                          
                                        <c:forEach var="transportation" items="${listTransportation}">
                                                <div class="col">
                                                    <input class="form-check-input" type="checkbox" id="${transportation.id}" name="tourTransportation[]" value="${transportation.id}">
                                                    <label class="form-check-label" for="${transportation.id}"><i class="${transportation.icon}"></i> ${transportation.name}</label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="mb-3">
                                        <label class="form-label">Img Url</label>
                                        <div id="previewContainer" class="d-flex flex-wrap gap-2 justify-content-center my-2">
                                            <!-- Ảnh xem trước sẽ hiển thị ở đây -->
                                            <img id="previewImage" src="https://res.ivivu.com/hotel/img/logo.svg" class="mt-3" >
                                        </div>                                     
                                        <input type="file" class="form-control" name="images" accept="image/*" id="imageInput" multiple required>                                 
                                    </div>
                                </div>
                                <script>
                                    const imageInput = document.getElementById('imageInput');
                                    const previewContainer = document.getElementById('previewContainer');

                                    imageInput.addEventListener('change', () => {
                                        previewContainer.innerHTML = ""; // Xóa ảnh cũ khi chọn lại

                                        const files = imageInput.files;
                                        if (files.length > 0) {
                                            Array.from(files).forEach(file => {
                                                const reader = new FileReader();
                                                reader.onload = (e) => {
                                                    const imgElement = document.createElement('img');
                                                    imgElement.src = e.target.result;
                                                    imgElement.classList.add('rounded', 'border', 'p-1');
                                                    imgElement.style.width = "120px";
                                                    imgElement.style.height = "120px";
                                                    imgElement.style.objectFit = "cover";
                                                    previewContainer.appendChild(imgElement);
                                                };
                                                reader.readAsDataURL(file);
                                            });
                                        }
                                    });
                                </script>
                                <style>
                                    #previewContainer img {
                                        transition: transform 0.2s ease;
                                    }
                                    #previewContainer img:hover {
                                        transform: scale(1.1);
                                    }
                                </style>
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
