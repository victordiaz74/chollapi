package presentacion;

import persistencia.dao.ProductoDAO;
import persistencia.dao.impl.ProductoDAOImplJpa;

public class ProductoController {

	ProductoDAO productoDAO;
	
	public ProductoController() {
		productoDAO = new ProductoDAOImplJpa();
	}
	
	
	
}
