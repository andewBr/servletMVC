package com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Получение данных из формы
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        // Вывод ответа
        out.println("<html>");
        out.println("<head><title>FormServlet Response</title></head>");
        out.println("<body>");
        out.println("<h2>Hello, " + name + "!</h2>");
        out.println("<p>Your age is: " + age + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
