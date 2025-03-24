<%-- 
    Document   : Header
    Created on : Mar 20, 2025, 8:13:39 AM
    Author     : titranthanh
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img src="https://www.ivivu.com/du-lich/content/img/logo.svg" alt="">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Tour trong nước</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Tour nước ngoài</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
              aria-expanded="false">
              Dropdown
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" tabindex="-1" aria-disabled="true">Khuyến mãi</a>
          </li>
        </ul>
       <div class="dropdown navbar-end me-3">
          <div class="dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
              <img src="https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/06/anh-dai-dien-mac-dinh-56.jpg" alt="" style="width: 40px; height: 40px; border-radius: 50%; cursor: pointer;">
          </div>
          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
            <li><a class="dropdown-item" href="/signup"><i class="fa-solid fa-user-plus"></i> Signup</a></li>
              <li><a class="dropdown-item" href="/login"><i class="fa-solid fa-arrow-right-to-bracket"></i> Login</a></li>
              <li><a class="dropdown-item" href="/support"><i class="fa-solid fa-headset"></i> Support</a></li>
          </ul>
      </div>
      </div>
    </div>
  </nav>
