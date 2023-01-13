package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the playlist database table.
 * 
 */
@Entity
@Table(name="producto")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idProducto")
	private Long idProducto;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="idFabricante") 
	private Long idFabricante; 
	
	//bi-directional many-to-many association to Oferta
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name="Chollo"
		, joinColumns={
			@JoinColumn(name="idProducto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idOferta")
			}
		)
	
	private List<Oferta> ofertas = new ArrayList<Oferta>();

	public Producto() {
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void addOferta(Oferta o) {
		ofertas.add( o );
		o.getProductos().add( this );
	}

	public void removeCancion(Oferta o) {
		ofertas.remove( o );
		o.getProductos().remove( this );
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", idFabricante=" + idFabricante
				+ ", ofertas=" + ofertas + "]";
	}
	
	
}
