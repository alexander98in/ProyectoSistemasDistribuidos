package co.edu.unicauca.clientehamburguesas.models;

public class ClsFactura {
	
	//attribute declaration
	private Integer idFactura;
	private Integer idEmpresa;
	private Integer numHamburguesasPequenias;
	private Integer numHamburguesasMedianas;
	private Integer numHamburguesasGrandes;
	private Float costoSinIVA;
	private Float IVA;
	private Float costoConIVA;
		
	//parameterless constructor
	public ClsFactura() {
		super();
	}

	public ClsFactura(Integer idFactura, Integer idEmpresa, Integer numHamburguesasPequenias,
			Integer numHamburguesasMedianas, Integer numHamburguesasGrandes, Float costoSinIVA, Float iVA,
			Float costoConIVA) {
		super();
		this.idFactura = idFactura;
		this.idEmpresa = idEmpresa;
		this.numHamburguesasPequenias = numHamburguesasPequenias;
		this.numHamburguesasMedianas = numHamburguesasMedianas;
		this.numHamburguesasGrandes = numHamburguesasGrandes;
		this.costoSinIVA = costoSinIVA;
		this.IVA = iVA;
		this.costoConIVA = costoConIVA;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getNumHamburguesasPequenias() {
		return numHamburguesasPequenias;
	}

	public void setNumHamburguesasPequenias(Integer numHamburguesasPequenias) {
		this.numHamburguesasPequenias = numHamburguesasPequenias;
	}

	public Integer getNumHamburguesasMedianas() {
		return numHamburguesasMedianas;
	}

	public void setNumHamburguesasMedianas(Integer numHamburguesasMedianas) {
		this.numHamburguesasMedianas = numHamburguesasMedianas;
	}

	public Integer getNumHamburguesasGrandes() {
		return numHamburguesasGrandes;
	}

	public void setNumHamburguesasGrandes(Integer numHamburguesasGrandes) {
		this.numHamburguesasGrandes = numHamburguesasGrandes;
	}

	public Float getCostoSinIVA() {
		return costoSinIVA;
	}

	public void setCostoSinIVA(Float costoSinIVA) {
		this.costoSinIVA = costoSinIVA;
	}

	public Float getIVA() {
		return IVA;
	}

	public void setIVA(Float iVA) {
		IVA = iVA;
	}

	public Float getCostoConIVA() {
		return costoConIVA;
	}

	public void setCostoConIVA(Float costoConIVA) {
		this.costoConIVA = costoConIVA;
	}
}
