package com.examen.app.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity @Table(name = "barcos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Barco {

@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id_barco;

@Column(name="nombre", nullable = false)
private String nombre;

@Column(name="origen", nullable = false)
private String origen;

@Column(name="destino", nullable = false)  
private String destino;

@Column(name="capacidad", nullable = false)
private String capacidad;

@OneToMany(mappedBy = "barco", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Contenedor> contenedor;
}
//Barco (ID, nombre, origen, destino, capacidad)