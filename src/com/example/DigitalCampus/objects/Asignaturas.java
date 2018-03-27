package com.example.DigitalCampus.objects;

import java.util.ArrayList;

public class Asignaturas {

	private String titulo;
	private String descripcion;
	public ArrayList<String> getTemas() {
		return temas;
	}
	public void setTemas(ArrayList<String> temas) {
		this.temas = temas;
	}
	private int id;
	private ArrayList<String> temas;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
