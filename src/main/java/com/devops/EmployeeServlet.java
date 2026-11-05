package com.devops;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> employees = new ArrayList<>();

        String sql = "SELECT id, name, department FROM employees ORDER BY id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(new String[]{
                    String.valueOf(resultSet.getInt("id")),
                    resultSet.getString("name"),
                    resultSet.getString("department")
                });
            }

            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/employees.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Unable to retrieve employees.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String department = request.getParameter("department");

        if (name == null || name.isBlank()
                || department == null || department.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Name and department are required.");
            return;
        }

        String sql = "INSERT INTO employees (name, department) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name.trim());
            statement.setString(2, department.trim());
            statement.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/employees");

        } catch (Exception e) {
            throw new ServletException("Unable to add employee.", e);
        }
    }
}
