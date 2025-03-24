<%-- 
    Document   : Header.jsp
    Created on : Mar 4, 2025, 1:02:20?PM
    Author     : titranthanh
--%>

<div class="right-header">
    <div class="header-left">
        <i class="fa-solid fa-magnifying-glass"></i>
        <div class="icon-bell">
            <i class="fa-solid fa-bell"></i>
            <span>5</span>
        </div>
        <div class="icon-message">
            <i class="fa-regular fa-envelope"></i>
            <span>9</span>
        </div>
    </div>
    <div class="header-right">
        <div class="img-nation">
            <img src="https://img.freepik.com/premium-vector/national-flag-vietnam-with-official-colors_445068-1852.jpg?w=2000"
                 alt="">
        </div>
        <div class="img-avatar" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg?t=st=1716778224~exp=1716781824~hmac=1d7139905036f9acbe055d04677d6dc5700d0cc31a539971e16b1cb1fd364359&w=1060"
                 alt="">
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">My Profile</a></li>
                <li><a class="dropdown-item" href="#">Notification</a></li>
                <li><a class="dropdown-item" href="#">Settings</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a id="logout" class="dropdown-item" th:href="@{/login}">Logout</a></li>
            </ul>
        </div>
    </div>
</div>
