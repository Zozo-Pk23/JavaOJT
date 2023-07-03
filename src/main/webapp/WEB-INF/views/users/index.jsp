<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                    <html>

                    <head>
                        <title>Title</title>
                         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                            rel="stylesheet"
                            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
                            crossorigin="anonymous">
                    </head>

                    <body>
                        <div class="container">
                            <div class="card">
                                <div class="card-header bg-success text-light">User List</div>
                                <div class="card-body">
                                    <div class="row text-center">
                                        <form class="row">
                                            <div class="col-md-1">Name:</div>
                                            <div class="col-md-2"><input type="text" class="form-control"></div>
                                            <div class="col-md-1">Email:</div>
                                            <div class="col-md-2"><input type="text" class="form-control"></div>
                                            <div class="col-md-1">From:</div>
                                            <div class="col-md-2"><input type="date" class="form-control"></div>
                                            <div class="col-md-1">to:</div>
                                            <div class="col-md-2"><input type="date" class="form-control"></div>
                                            <div class="col-md-1">
                                                <button class="btn btn-success text-white">Search</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr class="bg-info text-light">
                                                    <th>No</th>
                                                    <th>Name</th>
                                                    <th>Email</th>
                                                    <th>Created User</th>
                                                    <th>Type</th>
                                                    <th>Phone</th>
                                                    <th>Date of Birth</th>
                                                    <th>Address</th>
                                                    <th>Created_Date</th>
                                                    <th>Updated_Date</th>
                                                    <th>Operation</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${users}" var="user">
                                                    <tr>
                                                        <td>${user.id}</td>
                                                        <td><a href="#" data-bs-toggle="modal"
                                                                data-bs-target="#exampleModal${user.id}">${user.name}</a>
                                                        </td>
                                                        <td>${user.email}</td>
                                                        <td>${user.createdUserId}</td>
                                                        <td>${user.type}</td>
                                                        <td>${user.phone}</td>
                                                        <td>${user.dob}</td>
                                                        <td>${user.address}</td>
                                                        <td>
                                                            <fmt:formatDate pattern="yyyy/MM/dd"
                                                                value="${user.createdAt}" />
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate pattern="yyyy/MM/dd"
                                                                value="${user.updatedAt}" />
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btn btn-danger text-light"
                                                                data-bs-toggle="modal"
                                                                data-bs-target="#deleteModal${user.id}">Delete</button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <c:forEach items="${users}" var="user">
                                <div class="modal" tabindex="-1" id="deleteModal${user.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Delete Confirm</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <p style="color:red">Are you sure to delete user?</p>
                                                <table class="table">
                                                    <tr>
                                                        <td>ID</td>
                                                        <td>${user.id}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Name</td>
                                                        <td>${user.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Type</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${user.type == 1}">
                                                                    User
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Admin
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Email</td>
                                                        <td>${user.email}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Phone</td>
                                                        <td>${user.phone}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Date of Birth</td>
                                                        <td>
                                                            ${user.dob}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address</td>
                                                        <td>${user.address}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">Close</button>
                                                <form
                                                    action="${pageContext.request.contextPath}/users/delete?id=${user.id}"
                                                    method="post">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:forEach items="${users}" var="user">
                                <div class="modal fade" id="exampleModal${user.id}" tabindex="-1"
                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <img src="${pageContext.request.contextPath}/resources/profiles/${user.profile}"
                                                    alt="User Profile Image" style="width: 100px;height: 100px;">
                                                <table class="table">
                                                    <tr>
                                                        <td>Name</td>
                                                        <td>${user.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Type</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${user.type == 1}">
                                                                    User
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Admin
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Email</td>
                                                        <td>${user.email}</td>
                                                    </tr>

                                                    <tr>
                                                        <td>Phone</td>
                                                        <td>${user.phone}</td>
                                                    </tr>

                                                    <tr>
                                                        <td>Date of Birth</td>
                                                        <td>${user.dob}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address</td>
                                                        <td>${user.address}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created Date</td>
                                                        <td>
                                                            <fmt:formatDate value="${user.createdAt}"
                                                                pattern="yyyy-MM-dd" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created User</td>
                                                        <td>
                                                            ${user.createdUserId}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Updated Date</td>
                                                        <td>
                                                            <fmt:formatDate value="${user.updatedAt}"
                                                                pattern="yyyy-MM-dd" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Updated User</td>
                                                        <td>
                                                            ${user.updatedUserId}
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
                            crossorigin="anonymous"></script>

                    </html>