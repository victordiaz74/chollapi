package presentacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import modelo.Oferta;
import modelo.Producto;
import persistencia.jpa.Utilidades;


public class AppDAO {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main( String[] args ){
		
		OfertaController ofertaController = new OfertaController();
		Producto producto;
		Oferta oferta;
		EntityManager em = null; 
		int opcion = 0;
		
		try {
			em = Utilidades.getEntityManagerFactory().createEntityManager();
			
			while(opcion != 11) {
				System.out.println("1. Crear producto");
				System.out.println("2. Buscar producto");
				System.out.println("3. Actualizar producto");
				System.out.println("4. Eliminar producto");
				System.out.println("5. Crear oferta");
				System.out.println("6. Buscar oferta");
				System.out.println("7. Actualizar oferta");
				System.out.println("8. Eliminar oferta");
				System.out.println("9. Ultimas 5 ofertas de producto");
				System.out.println("10. Ultimas 10 ofertas");
				System.out.println("11. Salir");
				System.out.println("Elige una opcion: ");
				opcion = sc.nextInt();
				
				
				switch(opcion) {
				
				case 1:
					System.out.println("Introduce el nombre del producto: ");
					String nombre = sc.nextLine();
					producto = new Producto();
					producto.setNombre(nombre);
				
					System.out.println("Introduce el id del Fabricante: ");
					Long idFabricante = sc.nextLong();
					producto.setIdFabricante(idFabricante);
					System.out.println(producto.toString());
					em.getTransaction().begin();
					em.persist(producto);
					em.getTransaction().commit();
					System.out.println("Producto creado");
					break;
				
				case 2:
					System.out.println("Introduce el id del producto a buscar: ");
					Long idProducto = sc.nextLong();
					producto = new Producto();
					producto = em.find(Producto.class, idProducto);
					if(producto != null) {
						System.out.println(producto.toString());
					}else {
						System.out.println("Producto no encontrado");
					}
					
					break;

				case 3:
					System.out.println("Introduce el id del producto a actualizar: ");
					Long idProducto1 = sc.nextLong();
					producto = new Producto();
					producto = em.find(Producto.class, idProducto1);
					if(producto != null) {
						System.out.println(producto.toString());
						System.out.println("Introduce el nuevo nombre del producto: ");
						String nuevoNombre = sc.nextLine();
						producto.setNombre(nuevoNombre);
						System.out.println("Introduce el nuevo id del fabricante: ");
						Long nuevoIdFabricante = sc.nextLong();
						producto.setIdFabricante(nuevoIdFabricante);
						em.getTransaction().begin();
						em.merge(producto);
						em.getTransaction().commit();
						System.out.println("Producto actualizado");
						
					}else {
						System.out.println("Producto no encontrado");
					}
					
					break;
				
				case 4:
					System.out.println("Introduce el id a eliminar: ");
					Long idProducto2 = sc.nextLong();
					producto = new Producto();
					producto = em.find(Producto.class, idProducto2);
					if (producto != null) {
						System.out.println(producto);
						em.getTransaction().begin();
						em.remove(producto);
						em.getTransaction().commit();
						System.out.println("Producto eliminado...");
					} else {
						System.out.println("Producto no encontrado...");
					}
					break;
					
				case 5:
					System.out.println("Introduce la url de la oferta: ");
					String url = sc.nextLine();
					oferta = new Oferta();
					oferta.setUrl(url);
					
					System.out.println("Introduce la fecha de publicacion (Ej:12/12/2023): ");
					String fecha = sc.nextLine();
					System.out.println("Introduce la hora de publicacion (Ej:12:12): ");
					String hora = sc.nextLine();
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
					Date fechaDate = formatoFecha.parse(fecha);
					Date horaDate = formatoHora.parse(hora);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fechaDate);
					calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
					calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
					Date fechaConHora = calendar.getTime();
					oferta.setFechaPublicacion(fechaConHora);

					System.out.println("Introduce el precio de la oferta: ");
					Float precio = sc.nextFloat();
					oferta.setPrecio(precio);
					
					System.out.println("¿Quieres mostrar como disponible la oferta?(S/N): ");
					String respuesta = sc.nextLine();
					if(respuesta.equalsIgnoreCase("S")) {
						oferta.setDisponible(true);
					}else{
						oferta.setDisponible(false);						
					}
					
					System.out.println(oferta.toString());
					ofertaController.crearOferta(oferta);
					break;
				
				case 6:
					System.out.println("Introduce la id de la oferta a buscar: ");
					Long idOferta = sc.nextLong();
					oferta = new Oferta();
					oferta = em.find(Oferta.class, idOferta);
					if(oferta != null) {
						System.out.println(oferta.toString());
					}else {
						System.out.println("Oferta no encontrada");
					}
					
					break;
				
				case 7:
					System.out.println("Introduce la id de la oferta a actualizar: ");
					Long idOferta1 = sc.nextLong();
					oferta = new Oferta();
					oferta = em.find(Oferta.class, idOferta1);
					if(oferta != null) {
						System.out.println(oferta.toString());
						System.out.println("Introduce la nueva url: ");
						String nuevaUrl = sc.nextLine();
						oferta.setUrl(nuevaUrl);
						
						System.out.println("Introduce la nueva fecha de publicacion(dd/MM/yyyy): ");
						String nuevaFecha = sc.nextLine();
						System.out.println("Introduce la hora de publicacion (Ej:12:12): ");
						String nuevaHora = sc.nextLine();
						SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
						Date nuevaFechaDate = formatFecha.parse(nuevaFecha);
						Date nuevaHoraDate = formatHora.parse(nuevaHora);
						Calendar calendar1 = Calendar.getInstance();
						calendar1.setTime(nuevaFechaDate);
						calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
						calendar1.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
						Date fechaConHora1 = calendar1.getTime();
						oferta.setFechaPublicacion(fechaConHora1);
						
						System.out.println("Introduce el nuevo precio de la oferta: ");
						Float nuevoPrecio = sc.nextFloat();
						oferta.setPrecio(nuevoPrecio);
						
						System.out.println("¿Quieres mostrar como disponible la oferta?(S/N): ");
						String resp = sc.nextLine();
						if(resp.equalsIgnoreCase("S")) {
							oferta.setDisponible(true);
						}else{
							oferta.setDisponible(false);						
						}
						
						System.out.println(oferta.toString());
						
						
					}else {
						System.out.println("Oferta no encontrada");
					}
					
					break;
				
				case 8:
					System.out.println("Introduce el id de la oferta: ");
					Long idOferta2 = sc.nextLong();
					oferta = new Oferta();
					oferta = em.find(Oferta.class, idOferta2);
					if (oferta != null) {
						System.out.println(oferta);
						em.getTransaction().begin();
						em.remove(oferta);
						em.getTransaction().commit();
						System.out.println("Oferta eliminada...");
					} else {
						System.out.println("Oferta no encontrada...");
					}
					
				case 9:
					System.out.println("Introduce el id del producto a mostrar ofertas: ");
					Long idProducto3 = sc.nextLong();
					producto = new Producto();
					producto = em.find(Producto.class, idProducto3);
					if (producto != null) {
						System.out.println(ofertaController.ultimas5(idProducto3));
					} else {
						System.out.println("Producto no encontrado...");
					}
					
					break;
					
				case 10:
					System.out.println("Las 10 ultimas ofertas son: ");
					System.out.println(ofertaController.ultimas10());
					break;
					
				case 11:
					em.close();
					break;
					
				default:
					System.out.println("Opcion no valida\n");
					break;
					
				}
			}
			
		} catch (Exception e ) {
			if (em != null) {
				e.printStackTrace();
				System.out.println("Se va a hacer rollback de la transacción");
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
			
		//cerrar
		Utilidades.closeEntityManagerFactory();
		
	}

}
