package com.example.term7webclient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

@WebServlet(name = "loginServlet", value = "/login-servlet")

public class LoginServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        // retrieve session variable, cannot access it directly
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            session.setAttribute("message", "username or password is empty");
            response.sendRedirect("index.jsp");
        } else {
            // Read database connection properties from the properties file
            Properties properties = new Properties();
            try (InputStream input = new FileInputStream("C:\\Users\\Kiran\\Documents\\connection2.properties")) {
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String dbURL = properties.getProperty("url");
            String dbUser = properties.getProperty("user");
            String dbPassword = properties.getProperty("password");

            // Establish the database connection
            try {
                Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                String sql = "select agtFirstName, agtLastName, password from agents where username=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String dbPasswordFromDatabase = rs.getString("password");
                    if (password.equals(dbPasswordFromDatabase)) {
                        session.setAttribute("logged_in", true);
                        // Retrieve agent's first name and last name
                        String firstName = rs.getString("agtFirstName");
                        String lastName = rs.getString("agtLastName");

                        // Combine first name and last name and store as "agent_name"
                        String agentName = firstName + " " + lastName;
                        session.setAttribute("agent_name", agentName);
                        conn.close();
                        response.sendRedirect("booking.jsp");
                    } else {
                        session.setAttribute("message", "Invalid username or password");
                        conn.close();
                        response.sendRedirect("index.jsp");
                    }
                } else {
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