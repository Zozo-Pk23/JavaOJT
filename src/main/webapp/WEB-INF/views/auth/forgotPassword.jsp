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
                                    <div class="card-header bg-success text-light">Forgot Passwords ?</div>
                                    <div class="card-body">
                                        <c:if test="${emailNotFound != null}">
                                            <div class="alert alert-danger" role="alert">
                                                ${emailNotFound}
                                            </div>
                                        </c:if>
                                        <c:if test="${success != null}">
                                            <div class="alert alert-success" role="alert">
                                                ${success}
                                            </div>
                                        </c:if>
                                        <form method="post" action="${pageContext.request.contextPath}/reset">
                                            <div class="row mb-3">
                                                <label for="email" class="col-md-4 col-form-label text-md-end"> Email
                                                </label>
                                                <div class="col-md-6">
                                                    <input class="form-control" type="email" autofocus="autofocus"
                                                        name="email" id="email" required="required" />
                                                </div>
                                            </div>
                                            <div class="row mb-0">
                                                <div class="col-md-8 offset-md-4">
                                                    <button type="submit" class="btn btn-success text-light">
                                                        Reset Password
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </body>

                </html>