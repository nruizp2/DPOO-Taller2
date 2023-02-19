package Clases;

public class Bebida extends Producto{
	private String nombre;
	private int precio;
	private int calorias;
	public Bebida(String nombre,int precio,int calorias) {
		this.calorias = calorias;
		this.nombre = nombre;
		this.precio = precio;}
	public String getNombre() {return this.nombre;}
	public int getPrecio() {return this.precio;}
	public int getCalorias() {return this.calorias;}
	public String generarTextoFactura() {
		return ("	->"+this.nombre + "(precio,calorias): " + "("+this.precio+","+this.calorias+")");
	}
}
