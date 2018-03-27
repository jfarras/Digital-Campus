package com.example.DigitalCampus.objects;

public class Carreras {
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int carrera) {
		this.id = carrera;
	}

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Carreras(int id,String nombre){
		this.id = id;
		this.nombre = nombre;
	}

	public Carreras() {
		// TODO Auto-generated constructor stub
	}
	
}
