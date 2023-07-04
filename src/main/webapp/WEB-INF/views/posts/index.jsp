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
                                <div class="card-header bg-success text-light">Post List</div>
                                <div class="card-body">
                                    <div class="row justify-content-center my-1">
                                        <form class="col-6 row">
                                            <div class="col-8 text-center">
                                                <input type="text" placeholder="Search title or description"
                                                    class="form-control">
                                            </div>
                                            <button type="submit"
                                                class="btn btn-success col-4 text-light">Search</button>
                                        </form>
                                        <div class="col-2 text-center">
                                            <a href="${pageContext.request.contextPath}/posts/create"
                                                class="btn btn-success form-control text-light">Create</a>
                                        </div>
                                        <div class="col-2 text-center">
                                            <a class="btn btn-success form-control text-light" href="${pageContext.request.contextPath}/posts/uploadForm">Upload</a>
                                        </div>
                                        <form class="col-2 text-center" action="${pageContext.request.contextPath}/posts/download" method="GET">
                                            <button class="btn btn-success form-control text-light" type="submit">Download</button>
                                        </form>                                        
                                    </div>
                                    <table class="table">
                                        <thead class="bg-info text-light">
                                            <tr>
                                                <th>Post Title</th>
                                                <th>Post Description</th>
                                                <th>Posted User</th>
                                                <th>Posted Date</th>
                                                <th>Operation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${posts}" var="post">
                                                <tr>
                                                    <td>
                                                        <a href="#" data-bs-toggle="modal"
                                                            data-bs-target="#exampleModal${post.id}">${post.title}</a>
                                                    </td>
                                                    <td>${post.description}</td>
                                                    <td>${post.createdUserId}</td>
                                                    <td>
                                                        <fmt:formatDate pattern="dd/MM/yyyy"
                                                            value="${post.createdAt}" />
                                                    </td>
                                                    <td>
                                                        <a class="btn btn-info text-light"
                                                            href="${pageContext.request.contextPath}/posts/edit?id=${post.id}">Edit</a>
                                                        <button type="button" class="btn btn-danger text-light"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#deleteModal${post.id}">Delete</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <c:forEach items="${posts}" var="post">
                            <div class="modal" tabindex="-1" id="deleteModal${post.id}">
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
                                                <p>${post.id}</p>
                                            </div>
                                            <div class="d-flex justify-content-between">
                                                <p>Title</p>
                                                <p>${post.title}</p>
                                            </div>
                                            <div class="d-flex justify-content-between">
                                                <p>Description</p>
                                                <p>${post.description}</p>
                                            </div>
                                            <div class="d-flex justify-content-between">
                                                <p>Status</p>
                                                <p>${post.status}</p>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">Close</button>
                                            <form action="${pageContext.request.contextPath}/posts/delete?id=${post.id}"
                                                method="post">
                                                <button type="submit" class="btn btn-danger">Delete</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <c:forEach items="${posts}" var="post">
                            <div class="modal fade" id="exampleModal${post.id}" tabindex="-1"
                                aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <table class="table">
                                                <tr>
                                                    <td>Title</td>
                                                    <td>${post.title}</td>
                                                </tr>
                                                <tr>
                                                    <td>Description</td>
                                                    <td>${post.description}</td>
                                                </tr>
                                                <tr>
                                                    <td>Status</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${post.status == 1}">
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
                                                        <fmt:formatDate value="${post.createdAt}"
                                                            pattern="dd-MM-yyyy" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Updated At</td>
                                                    <td>
                                                        <fmt:formatDate value="${post.updatedAt}"
                                                            pattern="dd-MM-yyyy" />
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
                    </body>

                    </html>