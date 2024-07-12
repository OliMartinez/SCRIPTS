/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.castores.controllers;

import com.castores.dao.HistoricoDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.castores.dao.ProductoDAO;
import com.castores.models.Historico;
import com.castores.models.Producto;
import java.util.List;

@WebServlet("/Inventario")
public class Inventario extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductoDAO productoDAO;
    private HistoricoDAO historicoDAO;
    List<Producto> listaProductos;

    public void init() {
        productoDAO = new ProductoDAO();
        historicoDAO = new HistoricoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "verInventario":
                    listaProductos = productoDAO.selectAllProducts();
                    mostrarProductos(request, response);
                    break;
                case "salidaInventario":
                    listaProductos = productoDAO.selectAllProducts();
                    salidaProductos(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        String jsonResponse = "";
        response.getWriter().write(jsonResponse);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "agregar":
                    agregarProducto(request, response);
                    break;
                case "entrada":
                    entradaProducto(request, response);
                    break;
                case "salida":
                    salidaProducto(request, response);
                    break;
                case "cambiarEstatus":
                    cambiarEstatusProducto(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void mostrarProductos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("verInventario.jsp").forward(request, response);
    }

    private void salidaProductos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("salidaProductos.jsp").forward(request, response);
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        Float precio = Float.parseFloat(request.getParameter("precio"));
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        productoDAO.insertProduct(producto);
        response.sendRedirect("Inventario?action=verInventario");
    }

    private void entradaProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String nameUser = request.getParameter("nameUser");
        Producto producto = productoDAO.selectProductById(idProducto);
        producto.setCantidad(producto.getCantidad() + cantidad);
        productoDAO.updateProductQuantity(idProducto, producto.getCantidad());
        Historico movimiento = new Historico(idProducto, nameUser, "entrada", cantidad);
        historicoDAO.insertMovimiento(movimiento);
        response.sendRedirect("Inventario?action=verInventario");
    }

    private void salidaProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String nameUser = request.getParameter("nameUser");
        Producto producto = productoDAO.selectProductById(idProducto);
        if (producto.getCantidad() >= cantidad) {
            producto.setCantidad(producto.getCantidad() - cantidad);
            productoDAO.updateProductQuantity(idProducto, producto.getCantidad());
            Historico movimiento = new Historico(idProducto, nameUser, "salida", cantidad);
            historicoDAO.insertMovimiento(movimiento);
            response.sendRedirect("Inventario?action=salidaInventario");
        } else {
            response.sendRedirect("Inventario?action=salidaInventario&error=1");
        }
    }

    private void cambiarEstatusProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int nuevoEstatus = Integer.parseInt(request.getParameter("nuevoEstatus"));
        productoDAO.updateProductStatus(idProducto, nuevoEstatus);
        response.sendRedirect("Inventario?action=verInventario");
    }

}
