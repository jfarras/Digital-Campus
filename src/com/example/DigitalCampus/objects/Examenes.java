package com.example.DigitalCampus.objects;

public class Examenes {

	private int asignatura;
	private int carrera;
	private int aula;
	private String data;
	private int id;
	private String hora;
	private int numAlumnes;
	public int getNumAlumnes() {
		return numAlumnes;
	}
	public void setNumAlumnes(int numAlumnes) {
		this.numAlumnes = numAlumnes;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(int asignatura) {
		this.asignatura = asignatura;
	}
	public int getCarrera() {
		return carrera;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCarrera(int carrera) {
		this.carrera = carrera;
	}
	public int getAula() {
		return aula;
	}
	public void setAula(int aula) {
		this.aula = aula;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
