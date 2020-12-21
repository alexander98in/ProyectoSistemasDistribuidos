package co.edu.unicauca.administrador.vista;

import javax.swing.JFrame;

public class ClsMenu {

	public static void main(String[] args) {
		GUIAdministrador vistaAdmin = new GUIAdministrador();
		vistaAdmin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vistaAdmin.setSize(850, 620);
		vistaAdmin.setVisible(true);
		vistaAdmin.setLocationRelativeTo(null);

	}

}
