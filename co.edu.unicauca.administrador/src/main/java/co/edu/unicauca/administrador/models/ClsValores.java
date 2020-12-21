package co.edu.unicauca.administrador.models;

public class ClsValores {

	private Integer idValor;
	private Integer idEmpresa;
	private Float valorIngredienteExtra;
	private Float valorHamburguesaPequenia;
	private Float valorHamburguesaMediana;
	private Float valorHamburgeusaGrande;
	
	public ClsValores() {
		super();
	}

	public ClsValores(Integer idValor, Integer idEmpresa, Float valorIngredienteExtra, Float valorHamburguesaPequenia,
			Float valorHamburguesaMediana, Float valorHamburgeusaGrande) {
		super();
		this.idValor = idValor;
		this.idEmpresa = idEmpresa;
		this.valorIngredienteExtra = valorIngredienteExtra;
		this.valorHamburguesaPequenia = valorHamburguesaPequenia;
		this.valorHamburguesaMediana = valorHamburguesaMediana;
		this.valorHamburgeusaGrande = valorHamburgeusaGrande;
	}

	public Integer getIdValor() {
		return idValor;
	}

	public void setIdValor(Integer idValor) {
		this.idValor = idValor;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Float getValorIngredienteExtra() {
		return valorIngredienteExtra;
	}

	public void setValorIngredienteExtra(Float valorIngredienteExtra) {
		this.valorIngredienteExtra = valorIngredienteExtra;
	}

	public Float getValorHamburguesaPequenia() {
		return valorHamburguesaPequenia;
	}

	public void setValorHamburguesaPequenia(Float valorHamburguesaPequenia) {
		this.valorHamburguesaPequenia = valorHamburguesaPequenia;
	}

	public Float getValorHamburguesaMediana() {
		return valorHamburguesaMediana;
	}

	public void setValorHamburguesaMediana(Float valorHamburguesaMediana) {
		this.valorHamburguesaMediana = valorHamburguesaMediana;
	}

	public Float getValorHamburgeusaGrande() {
		return valorHamburgeusaGrande;
	}

	public void setValorHamburgeusaGrande(Float valorHamburgeusaGrande) {
		this.valorHamburgeusaGrande = valorHamburgeusaGrande;
	}	
}
