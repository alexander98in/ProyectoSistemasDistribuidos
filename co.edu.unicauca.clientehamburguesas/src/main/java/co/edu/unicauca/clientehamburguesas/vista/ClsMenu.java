package co.edu.unicauca.clientehamburguesas.vista;

import javax.swing.JFrame;

public class ClsMenu {

	public static void main(String[] args) {
		GUICliente vistaCliente = new GUICliente();
        vistaCliente.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vistaCliente.setSize(850, 620);
        vistaCliente.setVisible(true);
        vistaCliente.setLocationRelativeTo(null);
	}
}
