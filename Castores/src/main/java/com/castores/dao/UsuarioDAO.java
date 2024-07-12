/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.castores.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.castores.models.Usuario;

public class UsuarioDAO {
    private String jdbcURL = "jdbc:mysql://localhost/inventario";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String SELECT_USER_BY_NAME_AND_PASSWORD = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";

    public Usuario selectUser(String nombre, String contrasena) {
        Usuario usuario = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME_AND_PASSWORD);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, contrasena);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                int idRol = rs.getInt("idRol");
                usuario = new Usuario(idUsuario, idRol, nombre, contrasena);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}

