package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Aplicacion {
	private Restaurante restaurante = new Restaurante();
	public static void main(String[]args) throws IOException {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
	public void mostrarMenu() {
		System.out.println("1.Mostrar el Menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Agregar bebida a un pedido");
		System.out.println("5. Cerrar un pedido y guardar la factura");
		System.out.println("6. Consultar la información de un pedido anterior dado su id");
		System.out.println("7. Salir");}
	public void ejecutarAplicacion() throws IOException {
		boolean continuar = true;
		cargarDatos();
		while (continuar) {
			mostrarMenu();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1) {
				mostrarMenuRestaurante();}
			else if (opcion_seleccionada == 2) {
				IniciarPedido();}
			else if (opcion_seleccionada == 3) {
				AgregarElementoPedido();}
			else if (opcion_seleccionada == 4) {
				AgregarBebida();}
			else if (opcion_seleccionada == 5) {
				CerrarPedidoFactura();}
			else if (opcion_seleccionada == 6) {
				InfoPedido();}
			else if (opcion_seleccionada == 7) {
				continuar = false;}
		}
	}
	private void AgregarBebida() {
		for(int i=0;i<restaurante.getBebidas().size();i++) {
			System.out.println(i + " " + restaurante.getBebidas().get(i).getNombre());
		}
		int id = Integer.parseInt(input("Ingrese el id del producto que desea agregar"));
		Pedido pedido = restaurante.getPedidoEnCurso();
		pedido.agregarProducto(restaurante.getBebidas().get(id));
		
	}
	private void cargarDatos() throws IOException {
		File ingredientes = new File("data/ingredientes.txt");
		File menu = new File("data/menu.txt");
		File combos = new File("data/combos.txt");
		File bebidas = new File("data/bebidas.txt");
		restaurante.cargarInformacionRestaurante(ingredientes, menu, combos,bebidas);
	}
	private void InfoPedido() {
		int id = Integer.parseInt(input("Ingrese una id"));
		Pedido pedido = restaurante.getId(id);
		System.out.println("Cliente: " + pedido.getNombrecliente());
		System.out.println("Dirección: " + pedido.getDireccionCliente());
		for(int i=0;i<pedido.getItemsPedido().size();i++) {
			System.out.println(pedido.getItemsPedido().get(i).getNombre());}}
	private Producto agregarEliminarIngrediente(Producto producto) {
		ArrayList<Ingredientes> agregados = new ArrayList<Ingredientes>();
		ArrayList<Ingredientes> eliminados = new ArrayList<Ingredientes>();
		int agregar = Integer.parseInt(input("Desea agregar un ingrediente 0:Si, 1:No "));
		if(agregar == 0) {
			boolean continuar = true;
			for(int i=0;i<restaurante.getIngrendientes().size();i++) {
				String nombre = restaurante.getIngrendientes().get(i).getNombre();
				int costo = restaurante.getIngrendientes().get(i).getcostoAdicional();
				System.out.println(i+" "+nombre+": "+costo);}
			while(continuar == true) {
				int id = Integer.parseInt(input("Ingrese el id"));
				agregados.add(restaurante.getIngrendientes().get(id));
				int otro = Integer.parseInt(input("Desea agregar otro ingrediente 0:Si, 1:No "));
				if(otro == 1) {continuar = false;}}}
		int eliminar = Integer.parseInt(input("Desea eliminar un ingrediente 0:Si, 1:No "));
		if(eliminar == 0) {
			boolean continuar = true;
			for(int i=0;i<restaurante.getIngrendientes().size();i++) {
				String nombre = restaurante.getIngrendientes().get(i).getNombre();
				int costo = restaurante.getIngrendientes().get(i).getcostoAdicional();
				System.out.println(i+" "+nombre+": "+costo);}
			while(continuar == true) {
				int id = Integer.parseInt(input("Ingrese el id"));
				eliminados.add(restaurante.getIngrendientes().get(id));
				int otro = Integer.parseInt(input("Desea eliminar otro ingrediente 0:Si, 1:No "));
				if(otro == 1) {continuar = false;}}}
		if(eliminados.size() == 0 && agregados.size() == 0) {return producto;}
		else {
			ProductoAjustado productoAjuste = new ProductoAjustado(producto,agregados,eliminados, producto.getCalorias());
			return productoAjuste;
		}
	}
	private void CerrarPedidoFactura() throws IOException {
		boolean igual = restaurante.cerrarYGuardarPedido();
		if(igual == true) {System.out.println("Hay un pedido igual.");}
		else {System.out.println("No hay un pedido igual.");}
		
	}
	private void AgregarElementoPedido() {
		int idPedido = Integer.parseInt(input("Ingrese el id del producto que desea agregar"));
		Pedido pedido = restaurante.getPedidoEnCurso();
		if(idPedido < restaurante.getMenuBase().size()) {
			Producto productoAgregar = agregarEliminarIngrediente(restaurante.getMenuBase().get(idPedido));
			pedido.agregarProducto(productoAgregar);}
		else {
			pedido.agregarProducto(restaurante.getCombos().get(idPedido - restaurante.getMenuBase().size()));}
		
	}
	private void IniciarPedido() { 
		if (restaurante.getPedidoEnCurso() == null) {
			
		String nombre = input("Ingrese su nombre");
		String direccion = input("Ingrese su dirección ");
		restaurante.iniciarPedido(nombre, direccion);
		System.out.println("Pedido creado exitosamento con id:" + restaurante.getPedidoEnCurso().getIdPedido()); }
		else { System.out.println("Ya hay un pedido en curso.");}
		
	}
	private void mostrarMenuRestaurante() {
		ArrayList<ProductoMenu> productosMenu = restaurante.getMenuBase();
		ArrayList<Combo> combos = restaurante.getCombos();
		System.out.println("Menú (Id,Nombre,Precio)");
		int id = 0;
		for(int i = 0;i<productosMenu.size();i++) {
			String nombre = productosMenu.get(i).getNombre();
			int precio = productosMenu.get(i).getPrecio();
			System.out.println(id + ": "+ nombre + " " + precio + ".");
			id++;}
		for(int j=0;j<combos.size();j++) {
			String nombre = combos.get(j).getNombre();
			int presio = combos.get(j).getPrecio();
			System.out.println(id + ": " + nombre + " " + presio + ".");
			id++;
		}
	
		
	}
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
