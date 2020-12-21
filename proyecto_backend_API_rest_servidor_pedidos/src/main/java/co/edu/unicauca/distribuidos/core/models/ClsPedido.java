package co.edu.unicauca.distribuidos.core.models;

import java.util.ArrayList;

public class ClsPedido {
	
	private int numeroMesa;
	private ArrayList<ClsHamburguesa> listaHamburguesas;
	
	public ClsPedido() {
		this.listaHamburguesas = new ArrayList<>();
	}

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public ArrayList<ClsHamburguesa> getListaHamburguesas() {
		return listaHamburguesas;
	}

	public void setListaHamburguesas(ArrayList<ClsHamburguesa> listaHamburguesas) {
		this.listaHamburguesas = listaHamburguesas;
	}
}
