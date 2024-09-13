<%@ page import="org.example.baitap1.models.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Search Results</title>
</head>
<body>
<h1>Search Results</h1>
<a href="/student?action=GETALL">Back to Student List</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Status</th>
    </tr>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students != null && !students.isEmpty()) {
            for (Student student : students) {
    %>
    <tr>
        <td><%= student.getId() %></td>
        <td><%= student.getFullName() %></td>
        <td><%= student.getEmail() %></td>
        <td><%= student.getAddress() %></td>
        <td><%= student.getPhone() %></td>
        <td><%= student.isStatus() ? "Active" : "Inactive" %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">No results found</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
