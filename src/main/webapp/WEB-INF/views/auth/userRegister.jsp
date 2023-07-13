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

                <div class="container">
                    <div class="row justify-content-center align-items-center" style="height: 100vh;">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header bg-success text-light">User Create</div>
                                <div class="card-body">
                                    <form:form action="${pageContext.request.contextPath}/registered" method="post"
                                        modelAttribute="userForm" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <label for="name" class="col-sm-4 col-form-label">Name</label>
                                            <div class="col-sm-8">
                                                <form:input path="name" id="name" class="form-control" />
                                                <form:errors path="name" cssClass="text-danger" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="email" class="col-sm-4 col-form-label">Email</label>
                                            <div class="col-sm-8">
                                                <form:input path="email" id="email" class="form-control"
                                                    autocomplete="off" />
                                                <form:errors path="email" cssClass="text-danger" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="password" class="col-sm-4 col-form-label">Password</label>
                                            <div class="col-sm-8">
                                                <form:input path="password" id="password" class="form-control"
                                                    type="password" />
                                                <form:errors path="password" cssClass="text-danger" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="password" class="col-sm-4 col-form-label">Confirm
                                                Password</label>
                                            <div class="col-sm-8">
                                                <form:input path="confirmPassword" id="confirmPassword"
                                                    class="form-control" autocomplete="off" type="password" />
                                                <form:errors path="confirmPassword" cssClass="text-danger" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-8 offset-sm-4">
                                                <button type="submit" class="btn btn-success text-light">Create</button>
                                                <a class="btn btn-secondary text-light"
                                                    href="${pageContext.request.contextPath}/register">Clear</a>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
                    crossorigin="anonymous"></script>
            </body>

            </html>