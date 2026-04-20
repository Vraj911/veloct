<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/head.jsp"/>

<body>
<div class="site-wrap">

    <% request.setAttribute("about_active", "active"); %>
    <jsp:include page="templates/header.jsp"/>

    <!-- BREADCRUMB -->
    <div class="bg-light py-3">
        <div class="container">
            <a href="/">Home</a> / <strong>About</strong>
        </div>
    </div>

    <!-- ABOUT -->
    <div class="site-section">
        <div class="container">
            <div class="row mb-5">

                <div class="col-md-6">
                    <img src="${pageContext.request.contextPath}/static/images/blog_1.jpg"
                         class="img-fluid rounded">
                </div>

                <div class="col-md-6">
                    <h2>How We Started</h2>

                    <p>
                        Veloct was created to provide high-quality and affordable footwear for everyone.
                        We aim to combine style, comfort, and durability in every product.
                    </p>

                    <p>
                        Our mission is to simplify shopping by offering trusted products,
                        fast delivery, and a seamless experience.
                    </p>
                </div>

            </div>
        </div>
    </div>

    <!-- TEAM -->
    <div class="site-section">
        <div class="container">

            <div class="text-center mb-5">
                <h2>Our Team</h2>
            </div>

            <div class="row text-center">

                <div class="col-md-3">
                    <img src="${pageContext.request.contextPath}/static/images/person1.jpg" class="img-fluid mb-3">
                    <h4>Vraj Shah</h4>
                    <p>Frontend developer & Operations</p>
                </div>

                <div class="col-md-3">
                    <img src="${pageContext.request.contextPath}/static/images/person2.jpg" class="img-fluid mb-3">
                    <h4>Pratham Pawar</h4>
                    <p>Database administrator</p>
                </div>

                <div class="col-md-3">
                    <img src="${pageContext.request.contextPath}/static/images/person3.jpg" class="img-fluid mb-3">
                    <h4>Het Sondagar</h4>
                    <p>Backend developer</p>
                </div>

                <div class="col-md-3">
                    <img src="${pageContext.request.contextPath}/static/images/person4.jpg" class="img-fluid mb-3">
                    <h4>Amaan Patel</h4>
                    <p>Frontend Developer</p>
                </div>

            </div>
        </div>
    </div>

    <jsp:include page="templates/footer.jsp"/>
</div>

<jsp:include page="templates/scripts.jsp"/>
</body>
</html>