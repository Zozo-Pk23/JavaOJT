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
                        <%@ include file="../common/header.jsp" %>
                            <div class="container my-2">
                                <div class="card">
                                    <div class="card-header bg-success text-light">Post List</div>
                                    <div class="card-body">
                                        <% if (request.getParameter("created") !=null) { %>
                                            <% String message=request.getParameter("created"); %>
                                                <div class="alert alert-success" role="alert">
                                                    <%= message %>
                                                </div>
                                                <% } %>
                                                    <div class="row justify-content-center my-1">
                                                        <form class="col-6 row"
                                                            action="${pageContext.request.contextPath}/posts/index"
                                                            method="get">
                                                            <div class="col-8 text-center">
                                                                <input type="text"
                                                                    placeholder="Search title or description"
                                                                    class="form-control" name="searchQuery">
                                                            </div>
                                                            <button type="submit"
                                                                class="btn btn-success col-4 text-light">Search</button>
                                                        </form>
                                                        <div class="col-2 text-center">
                                                            <a href="${pageContext.request.contextPath}/posts/create"
                                                                class="btn btn-success form-control text-light">Create</a>
                                                        </div>
                                                        <div class="col-2 text-center">
                                                            <a class="btn btn-success form-control text-light"
                                                                href="${pageContext.request.contextPath}/posts/uploadForm">Upload</a>
                                                        </div>
                                                        <form class="col-2 text-center"
                                                            action="${pageContext.request.contextPath}/posts/download"
                                                            method="GET">
                                                            <button class="btn btn-success form-control text-light"
                                                                type="submit">Download</button>
                                                        </form>
                                                    </div>
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th class="bg-info text-light">Post Title</th>
                                                                <th class="bg-info text-light">Post Description</th>
                                                                <th class="bg-info text-light">Posted User</th>
                                                                <th class="bg-info text-light">Posted Date</th>
                                                                <th class="bg-info text-light">Operation</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${posts}" var="post">
                                                                <tr>
                                                                    <td>
                                                                        <a href="#" data-bs-toggle="modal"
                                                                            data-bs-target="#exampleModal${post.post.id}">${post.post.title}</a>
                                                                    </td>
                                                                    <td>${post.post.description}</td>
                                                                    <td>${post.userName}</td>
                                                                    <td>
                                                                        <fmt:formatDate pattern="dd/MM/yyyy"
                                                                            value="${post.post.createdAt}" />
                                                                    </td>
                                                                    <td>
                                                                        <a class="btn btn-info text-light"
                                                                            href="${pageContext.request.contextPath}/posts/edit?id=${post.post.id}">Edit</a>
                                                                        <button type="button"
                                                                            class="btn btn-danger text-light"
                                                                            data-bs-toggle="modal"
                                                                            data-bs-target="#deleteModal${post.post.id}">Delete</button>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            <c:if test="${empty posts}">
                                                                <tr>
                                                                    <td colspan="5">
                                                                        <p class="text-center">No data available in a
                                                                            table.</p>
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                        </tbody>
                                                    </table>
                                                    <c:if test="${totalPages > 1}">
                                                        <nav aria-label="Page navigation example">
                                                            <ul class="pagination justify-content-end">
                                                                <li class="page-item">
                                                                    <c:if test="${currentPage > 1}">
                                                                        <a class="page-link"
                                                                            href="${pageContext.request.contextPath}/posts/index?pageNumber=${currentPage - 1}&searchQuery=${searchQuery}"
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
                                                                                    href="${pageContext.request.contextPath}/posts/index?pageNumber=${pageNum}&searchQuery=${searchQuery}">
                                                                                    ${pageNum}
                                                                                </a>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </li>
                                                                </c:forEach>
                                                                <li class="page-item">
                                                                    <c:if test="${currentPage < totalPages}">
                                                                        <a class="page-link"
                                                                            href="${pageContext.request.contextPath}/posts/index?pageNumber=${currentPage + 1}&searchQuery=${searchQuery}"
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

                            <c:forEach items="${posts}" var="post">
                                <div class="modal" tabindex="-1" id="deleteModal${post.post.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Delete Post</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <p>Are you sure that you would like to delete this post?</p>
                                                <div class="d-flex justify-content-between">
                                                    <p>ID</p>
                                                    <p>${post.post.id}</p>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <p>Title</p>
                                                    <p>${post.post.title}</p>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <p>Description</p>
                                                    <p>${post.post.description}</p>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <p>Status</p>
                                                    <p>
                                                        <c:choose>
                                                            <c:when test="${post.post.status == 1}">
                                                                Active
                                                            </c:when>
                                                            <c:otherwise>
                                                                Not Active
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">Close</button>
                                                <form
                                                    action="${pageContext.request.contextPath}/posts/delete?id=${post.post.id}"
                                                    method="post">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:forEach items="${posts}" var="post">
                                <div class="modal fade" id="exampleModal${post.post.id}" tabindex="-1"
                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <table class="table">
                                                    <tr>
                                                        <td>Title</td>
                                                        <td>${post.post.title}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Description</td>
                                                        <td>${post.post.description}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Status</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${post.post.status == 1}">
                                                                    Active
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Not Active
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created At</td>
                                                        <td>
                                                            <fmt:formatDate value="${post.post.createdAt}"
                                                                pattern="dd-MM-yyyy" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created User</td>
                                                        <td>
                                                            ${post.userName}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Updated At</td>
                                                        <td>
                                                            <fmt:formatDate value="${post.post.updatedAt}"
                                                                pattern="dd-MM-yyyy" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Update User</td>
                                                        <td>
                                                            ${post.updatedUserName}
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
                    </body>

                    </html>