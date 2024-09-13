<%@ page import="org.example.baitap1.models.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<h1>Student List</h1>
<a href="/student?action=CREATE">Add New Student</a>
<form action="/student" method="get">
    <input type="hidden" name="action" value="SEARCH">
    <input type="text" name="name" placeholder="Nhập tên sinh viên">
    <button type="submit">Tìm kiếm</button>
</form>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students != null) {
            for (Student student : students) {
    %>
    <tr>
        <td><%= student.getId() %></td>
        <td><%= student.getFullName() %></td>
        <td><%= student.getEmail() %></td>
        <td><%= student.getAddress() %></td>
        <td><%= student.getPhone() %></td>
        <td><%= student.isStatus() ? "Active" : "Inactive" %></td>
        <td>
            <a href="/student?action=EDIT&id=<%= student.getId() %>">Edit</a>
            <a href="/student?action=DELETE&id=<%= student.getId() %>" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
