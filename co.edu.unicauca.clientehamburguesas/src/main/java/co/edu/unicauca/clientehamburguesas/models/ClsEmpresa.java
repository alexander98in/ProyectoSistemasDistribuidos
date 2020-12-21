package co.edu.unicauca.clientehamburguesas.models;

public class ClsEmpresa {

	private Integer idEmpresa;
	private String nombre;
	private String NIT;
	
	public ClsEmpresa() {
		super();
	}

	public ClsEmpresa(Integer idEmpresa, String nombre, String nIT) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombre = nombre;
		this.NIT = nIT;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String nIT) {
		this.NIT = nIT;
	}

}
