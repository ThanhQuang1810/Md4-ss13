package org.example.baitap1.controller;

import org.example.baitap1.dao.student.IStudentDao;
import org.example.baitap1.dao.student.StudentDao;
import org.example.baitap1.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(value = "/student")
public class StudentServlet extends HttpServlet {
    private final IStudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "GETALL";

        switch (action) {
            case "GETALL":
                showAllStudents(request, response);
                break;
            case "DELETE":
                deleteStudent(request, response);
                break;
            case "DETAIL":
                showStudentDetail(request, response);
                break;
            case "EDIT":
                showEditForm(request, response);
                break;
            case "CREATE":
                showCreateForm(request, response);
                break;
            case "SEARCH":
                searchStudentByName(request, response);
                break;
            default:
                response.sendRedirect("/student?action=GETALL");
                break;
        }
    }

    private void showAllStudents(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", studentDao.getAll());
        request.getRequestDispatcher("/views/list.jsp").forward(request, response);
    }

    private void deleteStudent(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        studentDao.delete(id);
        response.sendRedirect("/student?action=GETALL");
    }

    private void showStudentDetail(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        request.setAttribute("student", studentDao.getById(id));
        request.getRequestDispatcher("/views/detail.jsp").forward(request, response);
    }

    private void showEditForm(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        request.setAttribute("student", studentDao.getById(id));
        request.getRequestDispatcher("/views/edit.jsp").forward(request, response);
    }

    private void showCreateForm(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/create.jsp").forward(request, response);
    }

    private void searchStudentByName(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("students", studentDao.searchByName(name));
        request.getRequestDispatcher("/views/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "ADD":
                    addStudent(request, response);
                    break;
                case "UPDATE":
                    updateStudent(request, response);
                    break;
                default:
                    response.sendRedirect("/student?action=GETALL");
                    break;
            }
        } else {
            response.sendRedirect("/student?action=GETALL");
        }
    }

    private void addStudent(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        boolean status = request.getParameter("status") != null;
        Student newStudent = new Student(
                0,
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("phone"),
                status
        );
        studentDao.save(newStudent);
        response.sendRedirect("/student?action=GETALL");
    }

    private void updateStudent(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        boolean status = request.getParameter("status") != null;
        Student updatedStudent = new Student(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("phone"),
                status
        );
        studentDao.update(updatedStudent);
        response.sendRedirect("/student?action=GETALL");
    }
}
