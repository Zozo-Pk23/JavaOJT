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
                        <jsp:include page="../common/header.jsp" />
                        <div class="container my-2">
                            <div class="card">
                                <div class="card-header bg-success text-light">User List</div>
                                <div class="card-body">
                                    <% if (request.getParameter("created") !=null) { %>
                                        <% String message=request.getParameter("created"); %>
                                            <div class="alert alert-success" role="alert">
                                                <%= message %>
                                            </div>
                                            <% } %>
                                                <div class="row text-center">
                                                    <form class="row"
                                                        action="${pageContext.request.contextPath}/users/index"
                                                        method="get">
                                                        <div class="col-md-3">
                                                            <div class="form-group row">
                                                                <label for="searchName"
                                                                    class="col-md-4 col-form-label">Name:</label>
                                                                <div class="col-md-8">
                                                                    <input type="text" class="form-control"
                                                                        id="searchName" name="searchName">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <div class="form-group row">
                                                                <label for="searchEmail"
                                                                    class="col-md-4 col-form-label">Email:</label>
                                                                <div class="col-md-8">
                                                                    <input type="text" class="form-control"
                                                                        id="searchEmail" name="searchEmail">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <div class="form-group row">
                                                                <label for="searchStartDate"
                                                                    class="col-md-4 col-form-label">From:</label>
                                                                <div class="col-md-8">
                                                                    <input type="date" class="form-control"
                                                                        id="searchStartDate" name="searchStartDate">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-2">
                                                            <div class="form-group row">
                                                                <label for="searchEndDate"
                                                                    class="col-md-4 col-form-label">To:</label>
                                                                <div class="col-md-8">
                                                                    <input type="date" class="form-control"
                                                                        id="searchEndDate" name="searchEndDate">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-1">
                                                            <button type="submit"
                                                                class="btn btn-success text-white">Search</button>
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
                                                                    <td>${user.user.id}</td>
                                                                    <td><a href="#" data-bs-toggle="modal"
                                                                            data-bs-target="#exampleModal${user.user.id}">${user.user.name}</a>
                                                                    </td>
                                                                    <td>${user.user.email}</td>
                                                                    <td>${user.createdUserName}</td>
                                                                    <td>
                                                                        <c:choose>
                                                                            <c:when test="${user.user.type == 1}">
                                                                                User
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                Admin
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </td>
                                                                    <td>${user.user.phone}</td>
                                                                    <td>${user.user.dob}</td>
                                                                    <td>${user.user.address}</td>
                                                                    <td>
                                                                        ${user.user.createdAt}
                                                                    </td>
                                                                    <td>
                                                                        ${user.user.updatedAt}
                                                                    </td>
                                                                    <td>
                                                                        <button type="button"
                                                                            class="btn btn-danger text-light"
                                                                            data-bs-toggle="modal"
                                                                            data-bs-target="#deleteModal${user.user.id}">Delete</button>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                    <c:if test="${totalPages > 1}">
                                                        <nav aria-label="Page navigation example">
                                                            <ul class="pagination justify-content-end">
                                                                <li class="page-item">
                                                                    <c:if test="${currentPage > 1}">
                                                                        <a class="page-link"
                                                                            href="${pageContext.request.contextPath}/users/index?pageNumber=${currentPage - 1}&searchName=${searchName}&searchEmail=${searchEmail}&searchStartDate=${searchStartDate}&searchEndDate=${searchEndDate}"
                                                                            aria-label="Previous">
                                                                            <span aria-hidden="true">&laquo;</span>
                                                                            <span class="sr-only">Previous</span>
                                                                        </a>
                                                                    </c:if>
                                                                </li>
                                                                <c:forEach begin="1" end="${totalPages}" var="pageNum">
                                                                    <li class="page-item">
                                                                        <c:choose>
                                                                            <c:when test="${pageNum == currentPage}">
                                                                                <a class="page-link"
                                                                                    href="#">${pageNum}</a>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <a class="page-link"
                                                                                    href="${pageContext.request.contextPath}/users/index?pageNumber=${pageNum}&searchName=${searchName}&searchEmail=${searchEmail}&searchStartDate=${searchStartDate}&searchEndDate=${searchEndDate}">
                                                                                    ${pageNum}
                                                                                </a>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </li>
                                                                </c:forEach>
                                                                <li class="page-item">
                                                                    <c:if test="${currentPage < totalPages}">
                                                                        <a class="page-link"
                                                                            href="${pageContext.request.contextPath}/users/index?pageNumber=${currentPage + 1}&searchName=${searchName}&searchEmail=${searchEmail}&searchStartDate=${searchStartDate}&searchEndDate=${searchEndDate}"
                                                                            aria-label="Next">
                                                                            <span aria-hidden="true">&raquo;</span>
                                                                            <span class="sr-only">Next</span>
                                                                        </a>
                                                                    </c:if>
                                                                </li>
                                                            </ul>
                                                        </nav>
                                                    </c:if>
                                                </div>
                                </div>
                            </div>
                            <c:forEach items="${users}" var="user">
                                <div class="modal" tabindex="-1" id="deleteModal${user.user.id}">
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
                                                        <td>${user.user.id}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Name</td>
                                                        <td>${user.user.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Type</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${user.user.type == 1}">
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
                                                        <td>${user.user.email}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Phone</td>
                                                        <td>${user.user.phone}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Date of Birth</td>
                                                        <td>
                                                            ${user.user.dob}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address</td>
                                                        <td>${user.user.address}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">Close</button>
                                                <form
                                                    action="${pageContext.request.contextPath}/users/delete?id=${user.user.id}"
                                                    method="post">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:forEach items="${users}" var="user">
                                <div class="modal fade" id="exampleModal${user.user.id}" tabindex="-1"
                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <img src="${pageContext.request.contextPath}/resources/profiles/${user.user.profile}"
                                                    alt="User Profile Image" style="width: 100px;height: 100px;">
                                                <table class="table">
                                                    <tr>
                                                        <td>Name</td>
                                                        <td>${user.user.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Type</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${user.user.type == 1}">
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
                                                        <td>${user.user.email}</td>
                                                    </tr>

                                                    <tr>
                                                        <td>Phone</td>
                                                        <td>${user.user.phone}</td>
                                                    </tr>

                                                    <tr>
                                                        <td>Date of Birth</td>
                                                        <td>${user.user.dob}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address</td>
                                                        <td>${user.user.address}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created Date</td>
                                                        <td>
                                                            ${user.user.createdAt}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created User</td>
                                                        <td>
                                                            ${user.createdUserName}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Updated Date</td>
                                                        <td>
                                                            ${user.user.updatedAt}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Updated User</td>
                                                        <td>
                                                            ${user.updatedUserName}
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
                            <jsp:include page="../common/footer.jsp" />

                    </html>