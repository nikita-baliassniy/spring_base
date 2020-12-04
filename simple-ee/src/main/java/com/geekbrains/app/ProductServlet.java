package com.geekbrains.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;

@WebServlet(name = "ProductServlet", urlPatterns = "/prods")
public class ProductServlet extends HttpServlet {

    private String[] productTitles = new String[]{
            "apple",
            "apricot",
            "avocado",
            "banana",
            "cherry",
            "grapes",
            "kiwifruit",
            "lemon",
            "lime",
            "mandarin",
            "mango",
            "orange",
            "peach",
            "pear",
            "pineapple",
            "beans"
    };

    // GET http://localhost:8080/simple-ee/prods?quantity=5
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        StringBuilder answer = new StringBuilder();
        int number = Integer.parseInt(req.getParameter("quantity"));
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            answer.append(new Product(i + 1,
                    productTitles[random.nextInt(16)],
                    random.nextDouble() * 100));
            answer.append("<br>");
        }
        out.println("<html><body><h1>" + answer.toString() + "</h1></body></html>");
        out.close();
    }
}