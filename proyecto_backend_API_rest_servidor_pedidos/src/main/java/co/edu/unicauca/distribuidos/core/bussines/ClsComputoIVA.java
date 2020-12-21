package co.edu.unicauca.distribuidos.core.bussines;

import co.edu.unicauca.distribuidos.core.models.ClsValores;

public class ClsComputoIVA {
	
	private ClsConteoHamburguesas conteoHamburguesas;
	private ClsValores valoresHamburguesa;
	private Float costoSinIVA;
	private Float IVA;
	private Float costoConIVA;
	
	public ClsComputoIVA() {
		super();
	}
	
	public ClsComputoIVA(ClsConteoHamburguesas conteoHamburguesas, ClsValores valores) {
		this.conteoHamburguesas = conteoHamburguesas;
		this.valoresHamburguesa = valores;
		this.costoSinIVA = (float) 0;
		this.IVA = (float) 0;
		this.costoConIVA = (float) 0;
		realizarCalculosIVA();
	}
	
	private void realizarCalculosIVA() {

        costoSinIVA = (this.conteoHamburguesas.getNumHamburguesasPequenias() * this.valoresHamburguesa.getValorHamburguesaPequenia()) + 
        		(this.conteoHamburguesas.getNumHamburguesasMedianas() * this.valoresHamburguesa.getValorHamburguesaMediana()) + 
        		(this.conteoHamburguesas.getNumHamburguesasGrandes() * this.valoresHamburguesa.getValorHamburgeusaGrande()) + 
        		(this.conteoHamburguesas.getNumIngredientesExtra() * this.valoresHamburguesa.getValorIngredienteExtra());
      
        generarIVA(this.costoSinIVA, this.conteoHamburguesas.getNumTotalHamburguesas());
        this.costoConIVA = this.costoSinIVA + this.IVA;
	}
	
	 private void generarIVA(Float costoSinIVA, Integer numTotalHamburguesas)
	 {   
		 //Calcula el IVA 
		 if (numTotalHamburguesas >= 1 && numTotalHamburguesas <= 3) 
			 this.IVA = ((costoSinIVA * 5) / 100);
	       
		 if (numTotalHamburguesas >= 4 && numTotalHamburguesas <= 7)
			 this.IVA = ((costoSinIVA * 8) / 100);
	        
		 if (numTotalHamburguesas >= 8 ){ 
			 if (costoSinIVA > 120)
				 this.IVA = ((costoSinIVA * 18) / 100);
			 else
				 IVA = ((costoSinIVA * 15) / 100);
		 } 
	 }

	public Float getCostoSinIVA() {
		return costoSinIVA;
	}

	public Float getIVA() {
		return IVA;
	}

	public Float getCostoConIVA() {
		return costoConIVA;
	}

}
