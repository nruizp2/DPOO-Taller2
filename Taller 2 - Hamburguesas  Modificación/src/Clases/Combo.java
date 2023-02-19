package Clases;
import java.util.*;
public class Combo extends Producto{
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> ItemsCombo;
	public Combo(String nombreCombo, double descuento,ArrayList<ProductoMenu> ItemsCombo) {
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
		this.ItemsCombo = ItemsCombo;}
	public void agregarItemACombo(ProductoMenu itemCombo) {
		this.ItemsCombo.add(itemCombo);}
	public int getPrecio() {
		int suma = 0;
		for(int i=0;i<this.ItemsCombo.size();i++) {
			suma += this.ItemsCombo.get(i).getPrecio();}
		return (int)(suma*(1-this.descuento));}
	public String getNombre() {return this.nombreCombo;}
	public String generarTextoFactura() {
		String concatenacion = "	->"+getNombre();
		concatenacion += "(precio,calorias):(" + Integer.toString(getPrecio()) + "," + getCalorias()+ ")";
		for(int i=0;i<ItemsCombo.size();i++) {
			concatenacion += "\n"+"		-" + ItemsCombo.get(i).getNombre();
		}
		return concatenacion;}
	@Override
	public int getCalorias() {
		int caloriass = 0;
		for(int i=0;i<this.ItemsCombo.size();i++) {caloriass+=this.ItemsCombo.get(i).getCalorias();}
		return caloriass;}
	
}
