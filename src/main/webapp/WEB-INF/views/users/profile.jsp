<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <html>

      <head>
        <title>Title</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <style>
          .form-label {
            text-align: right;
          }
        </style>
      </head>

      <body>
        <jsp:include page="../common/header.jsp" />
        <div class="container my-2">
          <div class="card">
            <div class="card-header bg-success text-light">User Profile</div>
            <div class="card-body">
              <form>
                <div class="form-group row">
                  <label class="col-sm-6 col-form-label form-label">Name</label>
                  <div class="col-sm-6">
                    <p class="form-control-static">${sessionScope.user.name}</p>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-6 col-form-label form-label">Type</label>
                  <div class="col-sm-6">
                    <p class="form-control-static">${sessionScope.user.type}</p>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-6 col-form-label form-label">Email</label>
                  <div class="col-sm-6">
                    <p class="form-control-static">${sessionScope.user.email}</p>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-6 col-form-label form-label">Phone</label>
                  <div class="col-sm-6">
                    <p class="form-control-static">${sessionScope.user.phone}</p>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-6 col-form-label form-label">Date of Birth</label>
                  <div class="col-sm-6">
                    <p class="form-control-static">${sessionScope.user.dob}</p>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-6 col-form-label form-label">Address</label>
                  <div class="col-sm-6">
                    <p class="form-control-static">${sessionScope.user.address}</p>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-6 col-form-label form-label"></label>
                  <div class="col-sm-6">
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/users/edit">Edit User</a>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
      </body>

      </html>