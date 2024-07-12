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
import com.castores.models.Historico;
import java.sql.Timestamp;

public class HistoricoDAO {
    private String jdbcURL = "jdbc:mysql://localhost/inventario";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_MOVIMIENTO_SQL = "INSERT INTO historico (idProducto, nombreUsuario, movimiento, cantidad) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_MOVIMIENTOS = "SELECT * FROM historico";

    public void insertMovimiento(Historico movimiento) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIMIENTO_SQL);
            preparedStatement.setInt(1, movimiento.getIdProducto());
            preparedStatement.setString(2, movimiento.getNombreUsuario());
            preparedStatement.setString(3, movimiento.getMovimiento());
            preparedStatement.setInt(4, movimiento.getCantidad());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Historico> selectAllMovimientos() {
        List<Historico> movimientos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIMIENTOS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idHist = rs.getInt("idHist");
                int idProducto = rs.getInt("idProducto");
                String nombreUsuario = rs.getString("nombreUsuario");
                String movimiento = rs.getString("movimiento");
                int cantidad = rs.getInt("cantidad");
                Timestamp fecha = rs.getTimestamp("fecha");
                movimientos.add(new Historico(idHist, idProducto, nombreUsuario, movimiento, cantidad, fecha));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movimientos;
    }
}

