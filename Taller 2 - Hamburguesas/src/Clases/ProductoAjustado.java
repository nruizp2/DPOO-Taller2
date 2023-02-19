package Clases;

import java.util.ArrayList;

public class ProductoAjustado extends Producto{
	private Producto base;
	private ArrayList<Ingredientes> agregados;
	private ArrayList<Ingredientes> eliminados;
	public ProductoAjustado(Producto producto,ArrayList<Ingredientes> agregados,ArrayList<Ingredientes> eliminados){
		this.base = producto;
		this.agregados = agregados;
		this.eliminados = eliminados;}
	public String getNombre() {
		return this.base.getNombre();}
	public int getPrecio() {
		int adicion = 0;
		for(int i=0;i<this.eliminados.size();i++) {
			adicion += this.eliminados.get(i).getcostoAdicional();}
		return this.base.getPrecio() + adicion;}
	public String generarTextoFactura() {
		String concatenacion = "	->"+this.base.getNombre();
		if(this.eliminados.size() > 0){
			concatenacion += " sin ";
			for(int i=0;i<this.eliminados.size();i++) {
				concatenacion += this.eliminados.get(i).getNombre();
				if(i != this.eliminados.size()-2);{
					concatenacion += ",";}}}
		if (this.agregados.size() > 0) {
			concatenacion += " con ";
			for(int i=0;i<this.agregados.size();i++) {
				concatenacion += this.agregados.get(i).getNombre();
				if(i != this.agregados.size()-2);{
					concatenacion += ",";}}}
		concatenacion += ":" + Integer.toString(getPrecio());
		return concatenacion;
	}
}	
