package com.example.DigitalCampus.objects;

public class Temas {

	private int id;
	private String nombre;

	public Temas() {
	}
	
	public Temas(int i, String string) {
		this.id = i;
		this.nombre = string;
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nombre;
	}

	public void setNom(String nom) {
		this.nombre = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
