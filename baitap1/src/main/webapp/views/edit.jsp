<%@ page import="org.example.baitap1.models.Student" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<h1>Edit Student</h1>
<form action="/student?action=UPDATE" method="post">
    <input type="hidden" name="id" value="${student.id}">
    <label for="name">Full Name:</label>
    <input type="text" id="name" name="name" value="${student.fullName}" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${student.email}" required><br>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${student.address}"><br>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="${student.phone}"><br>
    <label for="status">Status:</label>
    <input type="checkbox" id="status" name="status" ${student.status ? 'checked' : ''}><br>
    <button type="submit">Update</button>
</form>
</body>
</html>
