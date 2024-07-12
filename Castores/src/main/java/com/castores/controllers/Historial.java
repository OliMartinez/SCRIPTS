/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.castores.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.castores.dao.HistoricoDAO;
import com.castores.models.Historico;

@WebServlet("/Historico")
public class Historial extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HistoricoDAO movimientoDAO;

    public void init() {
        movimientoDAO = new HistoricoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String nombreUsuario = (String) request.getSession().getAttribute("nombre");
        String movimiento = request.getParameter("movimiento");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Historico hist = new Historico();
        hist.setIdProducto(idProducto);
        hist.setNombreUsuario(nombreUsuario);
        hist.setMovimiento(movimiento);
        hist.setCantidad(cantidad);

        try {
            movimientoDAO.insertMovimiento(hist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

