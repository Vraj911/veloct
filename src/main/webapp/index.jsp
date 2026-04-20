<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/head.jsp"/>

<body>
<div class="site-wrap">
    <jsp:include page="templates/header.jsp"/>

    <!-- HERO -->
    <div class="site-blocks-cover"
         style="background-image: url(${pageContext.request.contextPath}/static/images/hero_1.jpg);"
         data-aos="fade">

        <div class="container">
            <div class="row align-items-start align-items-md-center justify-content-end">
                <div class="col-md-5 text-center text-md-left pt-5 pt-md-0">
                    <h1 class="mb-2">Find Your Perfect Style</h1>

                    <div class="intro-text">
                        <p class="mb-4">
                            Discover premium quality footwear designed for comfort and style.
                            From everyday wear to festive collections, upgrade your look effortlessly.
                        </p>

                        <p>
                            <a href="shop" class="btn btn-sm btn-primary">Shop Now</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- FEATURES -->
    <div class="site-section site-section-sm site-blocks-1">
        <div class="container">
            <div class="row">

                <div class="col-md-6 col-lg-4 d-lg-flex mb-4">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-truck"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Free Shipping</h2>
                        <p>Fast and reliable delivery across India with zero shipping charges.</p>
                    </div>
                </div>

                <div class="col-md-6 col-lg-4 d-lg-flex mb-4">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-refresh2"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Easy Returns</h2>
                        <p>7-day hassle-free return and exchange policy for complete peace of mind.</p>
                    </div>
                </div>

                <div class="col-md-6 col-lg-4 d-lg-flex mb-4">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-help"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Customer Support</h2>
                        <p>We’re here to help you with orders, queries, and product assistance.</p>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <jsp:include page="templates/collections-section.jsp"/>
    <jsp:include page="templates/featured-products.jsp"/>

    <!-- SALE SECTION -->
    <div class="site-section block-8">
        <div class="container">

            <div class="row justify-content-center mb-5">
                <div class="col-md-7 text-center">
                    <h2>Big Sale!</h2>
                </div>
            </div>

            <div class="row align-items-center">

                <div class="col-md-12 col-lg-7 mb-5">
                    <img src="${pageContext.request.contextPath}/static/images/blog_1.jpg"
                         class="img-fluid rounded">
                </div>

                <div class="col-md-12 col-lg-5 text-center">
                    <h2>Up to 50% OFF</h2>

                    <p class="mb-4">
                        Grab exciting deals on trending footwear. Limited time offer — don’t miss out.
                    </p>

                    <p>
                        <a href="shop" class="btn btn-primary btn-sm">Shop Now</a>
                    </p>
                </div>

            </div>
        </div>
    </div>

    <jsp:include page="templates/footer.jsp"/>
</div>

<jsp:include page="templates/scripts.jsp"/>
</body>
</html>