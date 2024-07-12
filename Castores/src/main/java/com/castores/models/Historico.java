/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.castores.models;

import java.sql.Timestamp;

public class Historico {
    private int idHist;
    private int idProducto;
    private String nombreUsuario;
    private String movimiento;
    private int cantidad;
    private Timestamp fecha;

    // Constructor vacío
    public Historico() {}

    // Constructor con parámetros
    public Historico(int idProducto, String nombreUsuario, String movimiento, int cantidad) {
        this.idProducto = idProducto;
        this.nombreUsuario = nombreUsuario;
        this.movimiento = movimiento;
        this.cantidad = cantidad;
    }

    public Historico(int idHist, int idProducto, String nombreUsuario, String movimiento, int cantidad, Timestamp fecha) {
        this.idHist = idHist;
        this.idProducto = idProducto;
        this.nombreUsuario = nombreUsuario;
        this.movimiento = movimiento;
        this.cantidad = cantidad; 
        this.fecha = fecha;   
}

    // Getters y Setters
    public int getIdHist() {
        return idHist;
    }

    public void setIdHist(int idHist) {
        this.idHist = idHist;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}

