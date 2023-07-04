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
                            <form action="${pageContext.request.contextPath}/posts/upload" method="post"
                                enctype="multipart/form-data">
                                <div class="form-group row">
                                    <label for="csv" class="col-sm-2 col-form-label">CSV FIle</label>
                                    <div class="col-sm-10">
                                        <input type="file" name="file" id="csv" class="form-control" accept=".csv" />
                                    </div>
                                    <button class="btn btn-success text-light" type="submit">Upload</button>
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