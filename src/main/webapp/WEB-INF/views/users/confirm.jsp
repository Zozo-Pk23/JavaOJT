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
                        <div class="card-header bg-success text-light">User Confirm</div>
                        <div class="card-body">
                            <form:form action="/SCMBulletin_war/users/save" method="post">
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">name</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.name}</p>
                                        <input type="hidden" name="name" value="${userForm.name}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">email</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.email}</p>
                                        <input type="hidden" name="email" value="${userForm.email}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">password</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.password}</p>
                                        <input type="hidden" name="password" value="${userForm.password}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">confirmPassword</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.confirmPassword}</p>
                                        <input type="hidden" name="confirmPassword" value="${userForm.confirmPassword}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">type</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.type}</p>
                                        <input type="hidden" name="type" value="${userForm.type}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">phone</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.phone}</p>
                                        <input type="hidden" name="phone" value="${userForm.phone}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">dob</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.dob}</p>
                                        <input type="hidden" name="dob" value="${userForm.dob}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">address</label>
                                    <div class="col-sm-10">
                                        <p> ${userForm.address}</p>
                                        <input type="hidden" name="address" value="${userForm.address}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">profile</label>
                                    <div class="col-sm-10">
                                        <img src="${pageContext.request.contextPath}/resources/profiles/${userForm.profile}" alt="User Profile" style="height: 100px;width: 100px;">
                                        <input type="hidden" name="profile" value="${userForm.profile}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10 offset-sm-2">
                                        <button type="submit" class="btn btn-info text-light">Confirm</button>
                                        <button type="reset" class="btn btn-secondary text-light">Clear</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>