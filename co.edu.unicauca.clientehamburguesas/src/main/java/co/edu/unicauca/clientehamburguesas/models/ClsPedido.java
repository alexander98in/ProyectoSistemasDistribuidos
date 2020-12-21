package co.edu.unicauca.clientehamburguesas.models;

import java.util.ArrayList;

public class ClsPedido {
	
	private int numeroMesa;
	private ArrayList<ClsHamburguesa> listaHamburguesas;
	
	public ClsPedido() {
		this.listaHamburguesas = new ArrayList<ClsHamburguesa>();
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
	
	/**
	 * Metodos Auxiliares
	 * ----------------------------------------------------------
	 */
	
	public void agregarHamburguesa(ClsHamburguesa objHamburguesa){
        this.listaHamburguesas.add(objHamburguesa);
    }
	
	public void eliminarHamburguesa(int posicion) {
        this.listaHamburguesas.remove(posicion);
    }
	
	public void vaciarListaHamburguesas() {
        this.listaHamburguesas.clear();
    }
}
