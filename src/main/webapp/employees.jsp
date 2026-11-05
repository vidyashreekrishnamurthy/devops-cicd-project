<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Management - DevOps Project</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background: #f4f6f9;
        }

        h1 {
            color: #263b5e;
        }

        form, table {
            background: white;
            padding: 20px;
            margin-bottom: 25px;
            border-radius: 8px;
        }

        input {
            padding: 10px;
            margin: 8px;
        }

        button {
            padding: 10px 18px;
            background: #2563eb;
            color: white;
            border: none;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
    </style>
</head>

<body>

<h1>Employee Management System</h1>
<p>DevOps project using Java, PostgreSQL, Docker and Kubernetes.</p>

<h2>Add Employee</h2>

<form action="<%= request.getContextPath() %>/employees" method="post">
    <input type="text" name="name" placeholder="Employee Name" required>
    <input type="text" name="department" placeholder="Department" required>
    <button type="submit">Add Employee</button>
</form>

<h2>Employee Records</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Department</th>
    </tr>

    <%
        List<String[]> employees =
            (List<String[]>) request.getAttribute("employees");

        if (employees != null && !employees.isEmpty()) {
            for (String[] employee : employees) {
    %>
    <tr>
        <td><%= employee[0] %></td>
        <td><%= employee[1] %></td>
        <td><%= employee[2] %></td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="3">No employee records found.</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
