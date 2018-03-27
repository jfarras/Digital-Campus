package com.example.DigitalCampus.objects;

public class Aulas {

	private String nombre;
	private int id;
	
	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Aulas( int id,String nom){
		this.nombre=nom;
		this.id=id;
	}
}
