
<%@ page import="org.example.baitap1.models.Student" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Student</title>
</head>
<body>
<h1>Delete Student</h1>
<form action="/student?action=DELETE" method="post">
    <input type="hidden" name="id" value="${student.id}">
    <p>Are you sure you want to delete student <b>${student.name}</b>?</p>
    <button type="submit">Delete</button>
    <a href="/student?action=GETALL">Cancel</a>
</form>
</body>
</html>
