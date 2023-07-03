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
                    <div class="card">
                        <div class="card-header bg-success text-light">User Create</div>
                        <div class="card-body">
                            <form:form action="/SCMBulletin_war/users/add" method="post" modelAttribute="userForm" enctype="multipart/form-data">
                                <div class="form-group row">
                                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                                    <div class="col-sm-10">
                                        <form:input path="name" id="name" class="form-control" />
                                        <form:errors path="name" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <form:input path="email" id="email" class="form-control" />
                                        <form:errors path="email" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                                    <div class="col-sm-10">
                                        <form:input path="password" id="password" class="form-control"
                                            type="password" />
                                        <form:errors path="password" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="password" class="col-sm-2 col-form-label">Confirm Password</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" name="confirmPassword">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="type" class="col-sm-2 col-form-label">Type</label>
                                    <div class="col-sm-10">
                                        <form:select path="type" id="type" class="form-control">
                                            <form:option value="1">Admin</form:option>
                                            <form:option value="0">User</form:option>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="phone" class="col-sm-2 col-form-label">phone</label>
                                    <div class="col-sm-10">
                                        <form:input path="phone" id="phone" class="form-control" />
                                        <form:errors path="phone" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="dob" class="col-sm-2 col-form-label">Date Of Birth</label>
                                    <div class="col-sm-10">
                                        <form:input path="dob" id="dob" class="form-control" type="date" />
                                        <form:errors path="dob" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="address" class="col-sm-2 col-form-label">Address</label>
                                    <div class="col-sm-10">
                                        <form:input path="address" id="address" class="form-control" />
                                        <form:errors path="address" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="profile" class="col-sm-2 col-form-label">Profile</label>
                                    <div class="col-sm-10">
                                        <form:input path="profileFile" type="file" class="form-control" name="profileFile" />
                                        <form:input path="profile" id="profile" class="form-control" type="hidden" />
                                        <form:errors path="profileFile" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10 offset-sm-2">
                                        <button type="submit" class="btn btn-success text-light">Create</button>
                                        <button type="reset" class="btn btn-secondary text-light">Clear</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>