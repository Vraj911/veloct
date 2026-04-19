<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<header class="site-navbar" role="banner">
    <div class="site-navbar-top">
        <div class="container">
            <div class="row align-items-center">

                <!-- SEARCH -->
                <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
                    <form action="${pageContext.request.contextPath}/search" method="get" class="site-block-top-search">
                        <span class="icon icon-search2"></span>
                        <input name="keyword" type="text" class="form-control border-0" placeholder="Search">
                    </form>
                </div>

                <!-- LOGO -->
                <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
                    <div class="site-logo">
                        <a href="${pageContext.request.contextPath}/" class="js-logo-clone">Shoppers</a>
                    </div>
                </div>

                <!-- USER ICONS -->
                <div class="col-6 col-md-4 order-3 order-md-3 text-right">
                    <div class="site-top-icons">
                        <ul>

                            <c:if test="${sessionScope.account != null}">
                                <li>
                                    <c:if test="${account.base64Image != null}">
                                        <img class="icon"
                                             src="data:image/jpg;base64,${account.base64Image}"
                                             id="dropdownMenuReference"
                                             data-toggle="dropdown"
                                             style="width:1.5em;border-radius:50%;">
                                    </c:if>

                                    <c:if test="${account.base64Image == null}">
                                        <img class="icon"
                                             src="${pageContext.request.contextPath}/static/images/blank_avatar.png"
                                             id="dropdownMenuReference"
                                             data-toggle="dropdown"
                                             style="width:1.5em;border-radius:50%;">
                                    </c:if>

                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/profile-page">Profile</a>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
                                    </div>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.account == null}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/login">
                                        <span class="icon icon-person"></span>
                                    </a>
                                </li>
                            </c:if>

                            <!-- CART -->
                            <li>
                                <a href="${pageContext.request.contextPath}/cart.jsp" class="site-cart">
                                    <span class="icon icon-shopping_cart"></span>
                                    <c:if test="${order.cartProducts.size() != null}">
                                        <span class="count">${order.cartProducts.size()}</span>
                                    </c:if>
                                </a>
                            </li>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- NAVBAR -->
    <nav class="site-navigation text-center">
        <div class="container">
            <ul class="site-menu">

                <li class="${home_active}">
                    <a href="${pageContext.request.contextPath}/">Home</a>
                </li>

                <li class="${about_active}">
                    <a href="${pageContext.request.contextPath}/about.jsp">About</a>
                </li>

                <li class="${shop_active}">
                    <a href="${pageContext.request.contextPath}/shop">Shop</a>
                </li>

                <li class="${contact_active}">
                    <a href="${pageContext.request.contextPath}/contact.jsp">Contact</a>
                </li>

                <c:if test="${sessionScope.account != null}">
                    <li>
                        <a href="${pageContext.request.contextPath}/order-history">Orders History</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.account.isSeller == 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/product-management">Products</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/order-management">Orders</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.account.isAdmin == 1}">
                    <li>
                        <a href="#">Website Management</a>
                    </li>
                </c:if>

            </ul>
        </div>
    </nav>
</header>