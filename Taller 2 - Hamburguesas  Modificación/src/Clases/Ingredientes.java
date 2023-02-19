package Clases;

public class Ingredientes {
	private String nombre;
	private int calorias;
	private int costoAdicional;
	//methods
	public Ingredientes(String nombre, int costoAdicional, int calorias)	
	{
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.calorias = calorias;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public int getcostoAdicional()
	{
		return this.costoAdicional;
	}
	public int getCalorias(){return this.calorias;}
}

