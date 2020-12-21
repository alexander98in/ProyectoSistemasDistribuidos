package co.edu.unicauca.clientehamburguesas.bussines;

import java.util.ArrayList;

import co.edu.unicauca.clientehamburguesas.models.ClsHamburguesa;

public class ClsConteoHamburguesas {
	
	private ArrayList<ClsHamburguesa> listaHamburguesas;
	private Integer numHamburguesasPequenias;
	private Integer numHamburguesasMedianas;
	private Integer numHamburguesasGrandes;
	private Integer numIngredientesExtra;
	private Integer numTotalHamburguesas;
	
	public ClsConteoHamburguesas() {
		super();
	}
	
	public ClsConteoHamburguesas(ArrayList<ClsHamburguesa> listaHamburguesas) {
		this.listaHamburguesas = listaHamburguesas;
		this.numHamburguesasPequenias = 0;
		this.numHamburguesasMedianas = 0;
		this.numHamburguesasGrandes = 0;
		this.numIngredientesExtra = 0;
		this.numTotalHamburguesas = 0;
		realizarConteoHamburguesasPorTipo();
	}
	
	private void realizarConteoHamburguesasPorTipo() 
	{
		for (int i = 0; i < listaHamburguesas.size(); i++)
		{
			if(listaHamburguesas.get(i).getTipo() == 'p')
				this.numHamburguesasPequenias++;
			if(listaHamburguesas.get(i).getTipo() == 'm')
				this.numHamburguesasMedianas++;
			if(listaHamburguesas.get(i).getTipo() == 'g')
				this.numHamburguesasGrandes++;
			//Ingredientes Extra
			this.numIngredientesExtra += listaHamburguesas.get(i).getCantidadIngredientesExtra();
        }
		
		this.numTotalHamburguesas = this.numHamburguesasPequenias + this.numHamburguesasMedianas + this.numHamburguesasGrandes;
	}

	public Integer getNumHamburguesasPequenias() {
		return numHamburguesasPequenias;
	}

	public Integer getNumHamburguesasMedianas() {
		return numHamburguesasMedianas;
	}

	public Integer getNumHamburguesasGrandes() {
		return numHamburguesasGrandes;
	}

	public Integer getNumIngredientesExtra() {
		return numIngredientesExtra;
	}

	public Integer getNumTotalHamburguesas() {
		return numTotalHamburguesas;
	}
}
