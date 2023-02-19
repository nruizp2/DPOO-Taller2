package Clases;

public class ProductoMenu extends Producto{
	private String nombre;
	private int precioBase;
	private int calorias;
	public ProductoMenu(String nombre, int precioBase, int calorias) 
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}
	public String getNombre(){
	return this.nombre;	}
	public int getPrecio() {
		return this.precioBase;}
	public String generarTextoFactura() {
		String concatenacion = "	->"+getNombre();
		concatenacion += "(precio,calorias):(" + Integer.toString(getPrecio()) + "," + getCalorias()+ ")";
		return concatenacion;
	}
	@Override
	public int getCalorias(){return this.calorias;}
	}
