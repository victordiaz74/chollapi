package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="oferta")
@NamedQuery(name="Oferta.findAll", query="SELECT o FROM Oferta o")
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idOferta")
	private Long idOferta;
	
	@Column(name="url")
	private String url;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaPublicacion")
	private Date fechaPublicacion;
	
	@Column(name="precio")
	private Float precio;
	
	@Column(name="disponible")
	private Boolean disponible;
	
	//bi-directional many-to-many association to Playlist
	@ManyToMany(mappedBy="ofertas", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<Producto> productos = new ArrayList<Producto>();
	
	public Oferta() {
		
	}

	public Long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void addProducto(Producto p) {
		productos.add( p );
		p.getOfertas().add( this );
	}

	public void removeProducto(Producto p) {
		productos.remove( p );
		p.getOfertas().remove( this );
	}

	@Override
	public String toString() {
		return "Oferta [idOferta=" + idOferta + ", url=" + url + ", fechaPublicacion=" + fechaPublicacion + ", precio="
				+ precio + ", disponible=" + disponible + ", productos=" + productos + "]";
	}
	
	

}
