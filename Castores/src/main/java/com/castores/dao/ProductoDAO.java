/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.castores.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.castores.models.Producto;

public class ProductoDAO {
    private String jdbcURL = "jdbc:mysql://localhost/inventario";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO productos (nombre, precio) VALUES (?, ?)";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM productos";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE productos SET cantidad = ? WHERE idProducto = ?";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM productos WHERE idProducto = ?";
    private static final String UPDATE_PRODUCT_STATUS = "UPDATE productos SET estatus = ? WHERE idProducto = ?";


    public void insertProduct(Producto producto) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setFloat(2, producto.getPrecio());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Producto> selectAllProducts() {
        List<Producto> productos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                int cantidad = rs.getInt("cantidad");
                int estatus = rs.getInt("estatus");
                productos.add(new Producto(idProducto, nombre, precio, cantidad, estatus));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void updateProductQuantity(int idProducto, int cantidad) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, idProducto);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Producto selectProductById(int idProducto) {
        Producto producto = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, idProducto);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio"); 
                int cantidad = rs.getInt("cantidad");
                int estatus = rs.getInt("estatus");
                producto = new Producto(idProducto, nombre, precio, cantidad, estatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }
    public void updateProductStatus(int idProducto, int estatus) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_STATUS);
            preparedStatement.setInt(1, estatus);
            preparedStatement.setInt(2, idProducto);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

