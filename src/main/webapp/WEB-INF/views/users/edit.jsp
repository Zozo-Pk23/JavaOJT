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
                        <div class="card-header bg-success text-light">User Edit</div>
                        <div class="card-body">
                            <form:form action="${pageContext.request.contextPath}/users/update" method="post"
                                modelAttribute="userForm" enctype="multipart/form-data">
                                <form:input path="password" id="password" class="form-control"
                                    value="${userForm.password}" type="hidden" />
                                <form:input path="confirmPassword" id="confirmPassword" class="form-control"
                                    value="${userForm.confirmPassword}" type="hidden" />
                                <form:input path="id" id="id" class="form-control" value="${userForm.id}"
                                    type="hidden" />
                                <div class="form-group row">
                                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                                    <div class="col-sm-10">
                                        <form:input path="name" id="name" class="form-control"
                                            value="${userForm.name}" />
                                        <form:errors path="name" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <form:input path="email" id="email" class="form-control"
                                            value="${userForm.email}" />
                                        <form:errors path="email" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="type" class="col-sm-2 col-form-label">Type</label>
                                    <div class="col-sm-10">
                                        <form:select path="type" id="type" class="form-control"
                                            value="${userForm.type}">
                                            <form:option value="0">Admin</form:option>
                                            <form:option value="1">User</form:option>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="phone" class="col-sm-2 col-form-label">phone</label>
                                    <div class="col-sm-10">
                                        <form:input path="phone" id="phone" class="form-control"
                                            value="${userForm.phone}" />
                                        <form:errors path="phone" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="dob" class="col-sm-2 col-form-label">Date Of Birth</label>
                                    <div class="col-sm-10">
                                        <form:input path="dob" id="dob" class="form-control" type="date"
                                            value="${userForm.dob}" />
                                        <form:errors path="dob" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="address" class="col-sm-2 col-form-label">Address</label>
                                    <div class="col-sm-10">
                                        <form:textarea path="address" class="form-control" id="address" rows="3"
                                            value="${userForm.address}" />
                                        <form:errors path="address" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="oldprofile" class="col-sm-2 col-form-label">Old Profile</label>
                                    <div class="col-sm-10">
                                        <form:input path="profile" id="oldprofile" class="form-control"
                                            value="${userForm.profile}" />
                                        <img src="${pageContext.request.contextPath}/resources/profiles/${userForm.profile}"
                                            alt="User Profile Image" style="width: 100px;height: 100px;" path="profile">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="profileFile" class="col-sm-2 col-form-label">Profile</label>
                                    <div class="col-sm-10">
                                        <form:input path="profileFile" type="file" class="form-control"
                                            name="profileFile" />
                                        <form:errors path="profile" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10 offset-sm-2">
                                        <button type="submit" class="btn btn-success text-light">Create</button>
                                        <button type="reset" class="btn btn-secondary text-light">Clear</button>
                                        <a href="${pageContext.request.contextPath}/users/resetPassword">Change
                                            Password</a>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
                    crossorigin="anonymous"></script>
                <jsp:include page="../common/footer.jsp" />
            </body>

            </html>