<%@ page import="org.example.baitap1.models.Student" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Student</title>
</head>
<body>
<h1>Create New Student</h1>
<form action="/student" method="post">
    <input type="hidden" name="action" value="ADD">
    <label for="name">Full Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br>
    <label for="status">Active:</label>
    <input type="checkbox" id="status" name="status"><br>
    <button type="submit">Add Student</button>
</form>
<a href="/student?action=GETALL">Back to Student List</a>
</body>
</html>
