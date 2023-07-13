<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                <html>

                <head>
                    <title>Title</title>
                    <link rel="stylesheet"
                        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
                        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
                        crossorigin="anonymous">
                </head>

                <body>
                    <div class="container">
                        <div class="row justify-content-center align-items-center" style="height: 100vh;">
                            <div class="col-md-8">
                                <div class="card">
                                    <div class="card-header bg-success text-light">Log in</div>
                                    <div class="card-body">
                                        <% if (request.getParameter("created") !=null) { %>
                                            <% String message=request.getParameter("created"); %>
                                                <div class="alert alert-success" role="alert">
                                                    <%= message %>
                                                </div>
                                                <% } %>
                                                    <c:if test="${emailNotFound != null or incorrectPassword != null}">
                                                        <div class="alert alert-danger" role="alert">
                                                            ${emailNotFound}
                                                            ${incorrectPassword}
                                                        </div>
                                                    </c:if>
                                                    <form:form method="post"
                                                        action="${pageContext.request.contextPath}/authenticate"
                                                        modelAttribute="loginform">
                                                        <div class="row mb-3">
                                                            <label for="email"
                                                                class="col-md-4 col-form-label text-md-end">Email
                                                                Address
                                                                : </label>
                                                            <div class="col-md-6">
                                                                <form:input path="email" class="form-control"
                                                                    type="text" autofocus="autofocus" id="email" />
                                                                <form:errors path="email" cssClass="text-danger" />
                                                            </div>
                                                        </div>
                                                        <div class="row mb-3">
                                                            <label for="password"
                                                                class="col-md-4 col-form-label text-md-end">Password
                                                                :
                                                            </label>
                                                            <div class="col-md-6">
                                                                <form:input path="password" class="form-control"
                                                                    type="password" id="password" />
                                                                <form:errors path="password" cssClass="text-danger" />
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6 offset-md-4">
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="checkbox"
                                                                        name="remember" id="remember">
                                                                    <label class="form-check-label" for="remember">
                                                                        Remember me
                                                                    </label>
                                                                    <a class="btn btn-link"
                                                                        href="${pageContext.request.contextPath}/forgotPassword">
                                                                        Forgotten Password?
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-8 offset-md-4">
                                                                <button type="submit"
                                                                    class="btn btn-success text-light">
                                                                    Login
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <div class="row mb-3">
                                                            <div class="col-md-6 offset-md-4">
                                                                <a class="btn btn-link"
                                                                    href="${pageContext.request.contextPath}/register">
                                                                    Create Account?
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </body>

                </html>