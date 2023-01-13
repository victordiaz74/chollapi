package persistencia.dao;

import java.util.List;

import modelo.Oferta;
import modelo.Producto;

public interface OfertaDAO extends GenericDAO<Oferta, Integer>{
	
	public List<Oferta> ultimas10();
	
	public List<Oferta> ultimas5(Long idProducto);

}
