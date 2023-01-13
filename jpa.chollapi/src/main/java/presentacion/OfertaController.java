package presentacion;

import java.util.List;

import modelo.Oferta;
import modelo.Producto;
import persistencia.dao.OfertaDAO;
import persistencia.dao.impl.OfertaDAOImplJpa;

public class OfertaController {
	
	OfertaDAO ofertaDAO;
	
	public OfertaController() {
		ofertaDAO = new OfertaDAOImplJpa();
	}
	
	public void crearOferta(Oferta oferta) {
		List listaOfertas = ofertaDAO.findAll();
		
		
		
	}

	public List<Oferta> ultimas5(Long idProducto) {
		return ofertaDAO.ultimas5(idProducto);
	}
	
	public List<Oferta> ultimas10() {
		return ofertaDAO.ultimas10();
	}

}
