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

                <div class="container mt-5">
                    <h2 class="text-center">Add TourSchedule</h2>
                    <form action="addtourschedule" method="post" enctype="multipart/form-data" class="p-4 shadow-lg rounded bg-light mt-3">
                        <div class="row row-cols-1 row-cols-lg-2">
                            <div class="col">
                                <div class="mb-3">
                                    <label class="form-label">Name</label>
                                    <input type="text" class="form-control" name="name" placeholder="Nhập tên lịch trình" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Day Tour</label>
                                    <input type="number" class="form-control" name="dayTour" placeholder="Nhập số ngày" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Tour Packages</label>
                                    <select class="form-select" name="locationId" aria-label="Default select example" required>
                                        <option selected disabled>Vui lòng chọn TourPackages</option>
                                        <c:forEach var="o" items="${tourPackages}">
                                            <option value="${o.id}">${o.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Description</label>
                                    <textarea class="form-control" name="description" rows="3" placeholder="Nhập mô tả" required></textarea>
                                </div>
                            </div>

                            <!-- Chọn địa điểm tham quan -->
                            <div class="col">
                                <div class="mb-3">
                                    <label class="form-label">Attractions <i class="fa-solid fa-images" data-bs-toggle="modal" data-bs-target="#chooseattractions"></i></label>
                                    <div class="row row-cols-3 list_attraction"></div>
                                </div>
                            </div>
                        </div>

                        <!-- Nút Hành Động -->
                        <div class="d-flex justify-content-between">
                            <a href="schedulelist" class="btn btn-secondary">
                                <i class="fa-solid fa-arrow-left"></i> Quay Lại
                            </a>
                            <button type="submit" class="btn btn-success">
                                <i class="fa-solid fa-plus"></i> Add
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Modal chọn địa điểm -->
                <div class="modal fade" id="chooseattractions" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Chọn địa điểm tham quan</h5>
                                <input class="ms-3 p-1" type="text" placeholder="Tìm kiếm...">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="row row-cols-4">
                                    <c:forEach var="o" items="${attractions}">
                                        <div class="col" data-id="${o.id}">
                                            <div class="img">
                                                <img src="${o.imgUrl}" alt="">
                                            </div>
                                            <p class="text-center">${o.name}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    document.addEventListener('DOMContentLoaded', () => {
                        const attractionContainer = document.querySelector('.list_attraction');
                        const attractionItems = document.querySelectorAll('#chooseattractions .col');                      
                        // Gắn sự kiện click cho từng item
                        attractionItems.forEach(item => {
                            item.addEventListener('click', () => {
                                const imgSrc = item.querySelector('img').src;
                                const name = item.querySelector('p').textContent;
                                const id = item.getAttribute('data-id');

                                console.log('🎯 Item Clicked:', {imgSrc, name, id});

                                // Kiểm tra trùng lặp
                                const existingItem = attractionContainer.querySelector(`input[value="${id}"]`);
                                if (existingItem) {
                                    console.log('🔄 Attraction already selected, removing...');
                                    existingItem.closest('.col').remove();
                                } else {
                                    console.log('➕ Adding new attraction...');

                                    // Tạo thẻ cha .col mới
                                    const newAttraction = document.createElement('div');
                                    newAttraction.classList.add('col');

                                    // Tạo từng phần tử con
                                    const imgDiv = document.createElement('div');
                                    imgDiv.classList.add('img');
                                    const img = document.createElement('img');
                                    img.src = imgSrc;
                                    img.alt = 'Attraction Image';

                                    const nameParagraph = document.createElement('p');
                                    nameParagraph.textContent = name;

                                    const hiddenInput = document.createElement('input');
                                    hiddenInput.type = 'hidden';
                                    hiddenInput.name = 'selectedAttractions';
                                    hiddenInput.value = id;

                                    // Gắn từng phần tử con vào thẻ cha
                                    imgDiv.appendChild(img);
                                    newAttraction.appendChild(imgDiv);
                                    newAttraction.appendChild(nameParagraph);
                                    newAttraction.appendChild(hiddenInput);

                                    // Đưa vào danh sách hiển thị
                                    attractionContainer.appendChild(newAttraction);
                                    console.log('✅ New attraction added!');
                                }
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
