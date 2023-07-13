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
                        <div class="card-header bg-success text-light">Edit Post</div>
                        <div class="card-body">
                            <form:form action="${pageContext.request.contextPath}/posts/update?id=${postForm.id}"
                                method="post" modelAttribute="postForm">
                                <div class="form-group row">
                                    <label for="title" class="col-sm-2 col-form-label">Title</label>
                                    <div class="col-sm-10">
                                        <form:input path="title" id="title" class="form-control"
                                            value="${postForm.title}" />
                                        <form:errors path="title" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="description" class="col-sm-2 col-form-label">Description</label>
                                    <div class="col-sm-10">
                                        <form:textarea path="description" class="form-control" id="description" rows="3"
                                            value="${postForm.description}" />
                                        <form:errors path="description" cssClass="text-danger" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Status</label>
                                    <div class="col-sm-10">
                                        <div class="form-check form-switch">
                                            <form:checkbox path="status" cssClass="form-check-input" value="1"
                                                checked="${postForm.status == 1 ? 'checked' : ''}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10 offset-sm-2">
                                        <button type="submit" class="btn btn-info text-light">Edit</button>
                                        <input type="submit" class="btn btn-secondary text-light" value="Clear"
                                            onclick="document.getElementById('title').value = null;document.getElementById('description').value = null; return false;">
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