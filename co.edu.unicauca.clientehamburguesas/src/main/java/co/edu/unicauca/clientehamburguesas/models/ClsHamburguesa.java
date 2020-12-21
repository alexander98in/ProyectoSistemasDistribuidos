package co.edu.unicauca.clientehamburguesas.models;

public class ClsHamburguesa {
	
	//attribute declaration
	private Integer idHamburguesa;
	private Integer idFactura;
	private String nombre;
	private char tipo;
	private Integer cantidadIngredientesExtra;
	private Float costo;
	
	public ClsHamburguesa() {
		super();
	}

	public ClsHamburguesa(Integer idHamburguesa, Integer idFactura, String nombre, char tipo,
			Integer cantidadIngredientesExtra, Float costo) {
		super();
		this.idHamburguesa = idHamburguesa;
		this.idFactura = idFactura;
		this.nombre = nombre;
		this.tipo = tipo;
		this.cantidadIngredientesExtra = cantidadIngredientesExtra;
		this.costo = costo;
	}

	public Integer getIdHamburguesa() {
		return idHamburguesa;
	}

	public void setIdHamburguesa(Integer idHamburguesa) {
		this.idHamburguesa = idHamburguesa;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public Integer getCantidadIngredientesExtra() {
		return cantidadIngredientesExtra;
	}

	public void setCantidadIngredientesExtra(Integer cantidadIngredientesExtra) {
		this.cantidadIngredientesExtra = cantidadIngredientesExtra;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}
}
