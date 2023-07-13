<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <html>

            <head>
                <title>Title</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
                    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
                    crossorigin="anonymous">
            </head>

            <body>
                <jsp:include page="../common/header.jsp" />
                <div class="container my-2">
                    <div class="card">
                        <div class="card-header bg-success text-light">User Confirm</div>
                        <div class="card-body">
                            <form:form action="${pageContext.request.contextPath}/users/save" method="post">
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${userForm.name}" readonly>
                                        <input type="hidden" name="name" value="${userForm.name}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">email</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${userForm.email}" readonly>
                                        <input type="hidden" name="email" value="${userForm.email}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">password</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" value="${userForm.password}"
                                            readonly>
                                        <input type="hidden" name="password" value="${userForm.password}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">confirmPassword</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" value="${userForm.confirmPassword}"
                                            readonly>
                                        <input type="hidden" name="confirmPassword" value="${userForm.confirmPassword}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">type</label>
                                    <div class="col-sm-10">
                                        <c:choose>
                                            <c:when test="${userForm.type == 1}">
                                                <input type="text" class="form-control" value="User" readonly>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" class="form-control" value="Admin" readonly>
                                            </c:otherwise>
                                        </c:choose>
                                        <input type="hidden" name="type" value="${userForm.type}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">phone</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${userForm.phone}" readonly>
                                        <input type="hidden" name="phone" value="${userForm.phone}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">dob</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${userForm.dob}" readonly>
                                        <input type="hidden" name="dob" value="${userForm.dob}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">address</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value="${userForm.address}" readonly>
                                        <input type="hidden" name="address" value="${userForm.address}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">profile</label>
                                    <div class="col-sm-10">
                                        <img src="${pageContext.request.contextPath}/resources/profiles/${userForm.profile}"
                                            alt="User Profile" style="height: 100px;width: 100px;">
                                        <input type="hidden" name="profile" value="${userForm.profile}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10 offset-sm-2">
                                        <button type="submit" class="btn btn-info text-light">Confirm</button>
                                        <a href="#" class="btn btn-secondary text-light"
                                            onclick="cancelAction(); return false;">Cancel</a>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <script>
                    function cancelAction() {
                        window.history.back();
                    }
                </script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
                    crossorigin="anonymous"></script>
                <jsp:include page="../common/footer.jsp" />
            </body>

            </html>