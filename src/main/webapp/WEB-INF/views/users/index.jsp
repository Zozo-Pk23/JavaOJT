<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
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
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.createdUserId}</td>
                        <td>${user.type}</td>
                        <td>${user.phone}</td>
                        <td>${user.dob}</td>
                        <td>${user.address}</td>
                        <td><fmt:formatDate pattern="yyyy/MM/dd" value="${user.createdAt}"/></td>
                        <td><fmt:formatDate pattern="yyyy/MM/dd" value="${user.updatedAt}"/></td>
                        <td>
                            <button type="button" class="btn btn-danger text-light">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>
