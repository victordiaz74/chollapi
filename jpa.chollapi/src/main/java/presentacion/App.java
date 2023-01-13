package presentacion;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import modelo.Oferta;
import modelo.Producto;
import persistencia.jpa.Utilidades;


public class App {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main( String[] args ){
		
		Producto producto;
		Oferta oferta;
		EntityManager em = null; 
		int opcion = 0;
		
		try {
			em = Utilidades.getEntityManagerFactory().createEntityManager();
			
			
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
	
	/*@Test
	public void testConexion() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
	    EntityManager em = emf.createEntityManager();
	    Query q = em.createQuery("SELECT COUNT(e) FROM MiEntidad e");
	    long count = (Long) q.getSingleResult();
	    assertTrue(count > 0);
	    em.close();
	    emf.close();
	}*/


}
