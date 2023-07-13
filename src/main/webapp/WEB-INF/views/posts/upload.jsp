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
                        <div class="card-header bg-success text-light">Upload csv File</div>
                        <div class="card-body">
                            <c:if test="${required != null or format != null}">
                                <div class="alert alert-danger" role="alert">
                                    ${required}
                                    ${format}
                                </div>
                            </c:if>
                            <c:forEach items="${errors}" var="err" varStatus="loop">
                                <c:if test="${err != null }">
                                    <div class="alert alert-danger" role="alert">
                                        ${err }
                                    </div>
                                </c:if>
                            </c:forEach>
                            <form action="${pageContext.request.contextPath}/posts/upload" method="post"
                                enctype="multipart/form-data">
                                <div class="form-group row">
                                    <label for="csv" class="col-sm-4 col-form-label">CSV FIle</label>
                                    <div class="col-sm-8">
                                        <input type="file" name="file" id="csv" accept=".csv" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4"></label>
                                    <div class="col-sm-8">
                                        <button class="btn btn-success text-light" type="submit">Upload</button>
                                        <a href="${pageContext.request.contextPath}/posts/uploadForm"
                                            class="btn btn-secondary">Clear</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
                    crossorigin="anonymous"></script>
                <jsp:include page="../common/footer.jsp" />
            </body>

            </html>