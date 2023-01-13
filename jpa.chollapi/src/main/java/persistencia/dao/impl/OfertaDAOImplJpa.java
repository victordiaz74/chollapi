package persistencia.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import modelo.Oferta;
import modelo.Producto;
import persistencia.dao.OfertaDAO;
import persistencia.jpa.Utilidades;

public class OfertaDAOImplJpa extends GenericDAOImplJpa<Oferta,Integer> implements OfertaDAO {

	@Override
	public List<Oferta> ultimas10() {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();
		List<Oferta> ofertas = em.createQuery(
				"select o from Oferta o order by o.fechaPublicacion desc limit 10"
				).getResultList();
		
		return ofertas;
	}

	@Override
	public List<Oferta> ultimas5(Long idProducto) {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();
		List<Oferta> ofertas = em.createQuery(
				"select o from Oferta o join Chollo c join Producto p on o.idOferta = c.idOferta and c.idProducto = p.idProducto where p.idProducto = :idProducto order by o.fechaPublicacion desc limit 5"
				).setParameter("idProducto", idProducto).getResultList();
		
		return ofertas;
	}

}

