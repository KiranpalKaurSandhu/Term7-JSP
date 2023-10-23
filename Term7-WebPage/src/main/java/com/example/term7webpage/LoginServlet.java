package com.example.term7webpage;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "loginServlet", value = "/login-servlet",
        initParams = {
                @WebInitParam(name = "dbURL", value = "jdbc:mariadb://localhost:3306/travelexperts"),
                @WebInitParam(name = "dbUser", value = "Kiranpal"),
                @WebInitParam(name ="dbPassword", value = "password")
        })
public class LoginServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        // retrieve session variable, cannot access it directly
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || password == null  || username.equals("") || password.equals(""))
        {
            session.setAttribute("message", "username or password is empty");
            response.sendRedirect("index.jsp");
        }
        else {
            // read from database
            try {
                Connection conn = DriverManager.getConnection(getInitParameter("dbURL"),
                        getInitParameter("dbUser"), getInitParameter("dbPassword"));
                String sql = "select password from agents where username=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    if (password.equals(rs.getString(1))) {
                        session.setAttribute("logged_in", true);
                        conn.close();
                        response.sendRedirect("booking.jsp");
                    }
                    else {
                        session.setAttribute("message", "Invalid username or password");
                        conn.close();
                        response.sendRedirect("index.jsp");
                    }
                }
                else {
                    session.setAttribute("message", "Invalid username or password");
                    conn.close();
                    response.sendRedirect("index.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        doGet(req, resp);
    }

    public void destroy() {
    }
}