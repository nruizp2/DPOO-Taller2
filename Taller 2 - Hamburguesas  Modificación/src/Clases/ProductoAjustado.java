package Clases;

import java.util.ArrayList;

public class ProductoAjustado extends Producto{
	private Producto base;
	private ArrayList<Ingredientes> agregados;
	private ArrayList<Ingredientes> eliminados;
	public ProductoAjustado(Producto base,ArrayList<Ingredientes> agregados,
			ArrayList<Ingredientes> eliminados, int calorias){
		this.base = base;
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
				if(i != this.eliminados.size()-1);{
					concatenacion += ",";}}}
		if (this.agregados.size() > 0) {
			concatenacion += " con ";
			for(int i=0;i<this.agregados.size();i++) {
				concatenacion += this.agregados.get(i).getNombre();
				if(i != this.agregados.size()-1);{
					concatenacion += ",";}}}
		concatenacion += "(precio,calorias): (" + Integer.toString(getPrecio()) + "," + getCalorias()+")";
		return concatenacion;
	}
	@Override
	public int getCalorias() {
		int calorias = this.base.getCalorias();
		for(int i=0;i<this.agregados.size();i++) {calorias += this.agregados.get(i).getCalorias();}
		for(int i=0;i<this.eliminados.size();i++) {calorias -= this.agregados.get(i).getCalorias();}
		return calorias;
	}
}	
