package Clases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	private int idPedido;
	private boolean pedidoEnCurso;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido = new ArrayList<Producto>();
	public Pedido(String nombreCliente, String direccionCliente,boolean pedidoEnCurso, 
			int idPedido,ArrayList<Producto> itemsPedido) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.pedidoEnCurso = pedidoEnCurso;
		this.idPedido = idPedido;
		this.itemsPedido = itemsPedido;
		this.pedidoEnCurso = pedidoEnCurso;}
	public boolean getPedidoEnCurso() {return this.pedidoEnCurso;}
	public void cerrarPedido() {
		this.pedidoEnCurso = false;}
	public String getNombrecliente() {return this.nombreCliente;}
	public String getDireccionCliente() {return this.direccionCliente;}
	public ArrayList<Producto> getItemsPedido(){return this.itemsPedido;}
	public int getIdPedido() {return this.idPedido;}
	public void agregarProducto(Producto item) {itemsPedido.add(item);}
	private int getPrecioNetoPedido() {
		int suma = 0;
		for(int i=0;i<this.itemsPedido.size();i++) {
			suma += this.itemsPedido.get(i).getPrecio();}
		return suma;}
	private int getPrecioIVAPedido() {
		return (int) (getPrecioNetoPedido()*0.19);}
	private int getPrecioTotalPedido() {
		return getPrecioIVAPedido()+getPrecioNetoPedido();}
	public void guardarFactura() throws IOException {
		File rutaFactura = new File("Facturas/" + Integer.toString(idPedido)+".txt");
		rutaFactura.createNewFile();
		FileWriter writer = new FileWriter(rutaFactura);
		writer.write("Cliente: " + this.nombreCliente+"\n");
		writer.write("Dirección: " + this.direccionCliente+"\n");
		writer.write("Comida:"+"\n");
		for(int i=0;i<this.itemsPedido.size();i++) {
			writer.write(this.itemsPedido.get(i).generarTextoFactura()+"\n");
		}
		writer.write("Total Neto:" +getPrecioNetoPedido()+"\n");
		writer.write("IVA:" +getPrecioIVAPedido()+"\n");
		writer.write("Calorias Totales:" +caloriasTotales()+"\n");
		writer.write("TOTAL:" + getPrecioTotalPedido()+"\n");
		writer.close();
	}
	public int caloriasTotales() {
		int cal = 0;
		for(int i = 0; i < this.itemsPedido.size();i++){cal += this.itemsPedido.get(i).getCalorias();}
		return cal;
	}
	
	
	
}
