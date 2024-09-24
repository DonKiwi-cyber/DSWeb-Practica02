/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dsweb.practica02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ian
 */
public class CreateProduct {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double salePrice = Double.parseDouble(request.getParameter("salePrice"));
        double purchasePrice = Double.parseDouble(request.getParameter("purchasePrice"));

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE product SET name = ?, salePrice = ?, purchasePrice = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, salePrice);
            stmt.setDouble(3, purchasePrice);
            stmt.setInt(4, id);
            
            stmt.executeUpdate();
            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
