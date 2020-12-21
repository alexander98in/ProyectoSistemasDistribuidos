package co.edu.unicauca.administrador.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import co.edu.unicauca.administrador.models.ClsEmpresa;
import co.edu.unicauca.administrador.servicios.ClsEmpresaServices;

@SuppressWarnings("serial")
public class GUIEmpresa extends JFrame {

	private ClsEmpresaServices objEmpresaServices;
	private ClsEmpresa objEmpresa;
	
	//Paneles de la GUICliente
    private final JPanel panelNorte;
    private final JPanel panelCentral;
    private final JPanel panelSur;
    
    //Etiquetas para la GUICliente
    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaNombreEmpresa;
    private final JLabel etiquetaNitEmpresa;
    
    //Campos de Texto
    private final JTextField campoTextoNombreEmpresa;
    private final JTextField campoTextoNitEmpresa;
    
    //Botones
    private final JButton botonCancelar;
    private final JButton botonActualizar;
    
    public GUIEmpresa(ClsEmpresa objEmpresaActualizar) {
    	
    	super("Editar Empresa");
    	this.objEmpresaServices = new ClsEmpresaServices();
    	this.objEmpresa = objEmpresaActualizar;
    	
    	//Agregamos un esquema de distribucion a nuestro JFrame
    	this.setLayout(new BorderLayout(5, 5));
    	
    	/**
    	 * Panel Norte
    	 */
    	
    	//Panel que se ubica en la parte norte
    	this.panelNorte = new JPanel();
    	panelNorte.setLayout(new GridLayout(1, 1, 5, 5));
    	//Etiqueta titulo
        etiquetaTitulo = new JLabel("Editar Empresa", SwingConstants.CENTER);
        etiquetaTitulo.setToolTipText("Titulo");
        etiquetaTitulo.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 50));
        etiquetaTitulo.setBackground(new Color(249, 231, 159));
        etiquetaTitulo.setOpaque(true);
        //Agregamos la etiqueta al panel Norte
        panelNorte.add(etiquetaTitulo);
        //Agreamos el panel al JFrame
        add(panelNorte, BorderLayout.NORTH);
        
        /**
         * Panel Central
         */
        
        //Panel que se ubica en la parte central
        panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(2, 2, 5, 5));
        //Etiqueta nombre empresa
        this.etiquetaNombreEmpresa = new JLabel("Nombre empresa:  ", SwingConstants.RIGHT);
        this.etiquetaNombreEmpresa.setToolTipText("Ingrese el nombre de la empresa.");
        this.etiquetaNombreEmpresa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.etiquetaNombreEmpresa.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.etiquetaNombreEmpresa.setBackground(new Color(163, 228, 215));
        this.etiquetaNombreEmpresa.setOpaque(true);
        //Etiqueta Nit de la empresa
        this.etiquetaNitEmpresa = new JLabel("NIT:  ", SwingConstants.RIGHT);
        this.etiquetaNitEmpresa.setToolTipText("Ingrese el NIT de la empresa.");
        this.etiquetaNitEmpresa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.etiquetaNitEmpresa.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.etiquetaNitEmpresa.setBackground(new Color(163, 228, 215));
        this.etiquetaNitEmpresa.setOpaque(true);
        //Campo de texto del nombre de la empresa
        this.campoTextoNombreEmpresa = new JTextField();
        this.campoTextoNombreEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.campoTextoNombreEmpresa.setBackground(new Color(240, 243, 244));     
        //Campo de texto NIT de la empresa
        this.campoTextoNitEmpresa = new JTextField();
        this.campoTextoNitEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.campoTextoNitEmpresa.setBackground(new Color(240, 243, 244));     
        //Llenamos los campos con los valores.
        llenarCamposDeTexto();
        //Agregamos los elementos al panel central
        this.panelCentral.add(this.etiquetaNombreEmpresa);
        this.panelCentral.add(this.campoTextoNombreEmpresa);
        this.panelCentral.add(this.etiquetaNitEmpresa);
        this.panelCentral.add(this.campoTextoNitEmpresa);
        //Agregamos el panel central al esquema principal
        this.add(panelCentral, BorderLayout.CENTER);
        
        /**
         * Panel Sur
         */
        
        //Panel que se ubica en la parte sur
        this.panelSur = new JPanel();
        this.panelSur.setLayout(new GridLayout(1, 2, 5, 5));
        
        //Boton Cancelar
        Icon cancelar = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/salir_opt.png"));
        botonCancelar = new JButton(" Cancelar ", cancelar);
        botonCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonCancelar.setBackground(new Color(241, 148, 138));
        botonCancelar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Cancelar
        botonCancelar.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de cancelar edicion de la informacion de la empresa
                public void actionPerformed(ActionEvent evento) {
                    cancelarEditarEmpresa();
                }
            }     
        );
        
        //Boton Actualizar
        Icon actulizar = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/actualizar_opt.jpg"));
        botonActualizar = new JButton(" Actualizar Empresa ", actulizar);
        botonActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonActualizar.setBackground(new Color(174, 214, 241));
        botonActualizar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonActualizar.setEnabled(true);
        //Agrega un evento al boton Actualizar
        botonActualizar.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Procesa evento de actualizar la informacion de una empresa
                public void actionPerformed(ActionEvent evento) {
                    actualizarInformacionEmpresa();
                }
            }     
        );
        
        //Agregamos los botones al panel sur
        this.panelSur.add(this.botonCancelar);
        this.panelSur.add(this.botonActualizar);
        //Agregamos el panel sur al esquema principal
        this.add(this.panelSur, BorderLayout.SOUTH);
        
        this.addWindowListener(
            new WindowAdapter() {
           		@Override
                public void windowClosing(WindowEvent e) {
           			cerrar();
            	}
            }
        );
        
	}//Fin del constructor de la GUIEmpresa
    
    /**
     * Llena los campos de texto con los datos de la empresa
     */
    private void llenarCamposDeTexto() {
    	if(this.objEmpresa != null) {
    		this.campoTextoNombreEmpresa.setText(objEmpresa.getNombre());
    		this.campoTextoNitEmpresa.setText(objEmpresa.getNIT());
    	}
    }
    
    /**
     * Cancela la edicion de la informacion de la empresa
     */
    private void cancelarEditarEmpresa()
    {
    	this.dispose();
    }
    
    /**
     * Actualiza la informacion de la empresa.
     */
    private void actualizarInformacionEmpresa()
    {	
        String[] opciones = {"Confirmar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea actualizar la informacion de la empresa?", "Actualizar informacion", 0, 0, null, opciones, this);
        
        if(eleccion == JOptionPane.YES_OPTION)
        {
        	if(this.campoTextoNombreEmpresa.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un Nombre para la empresa.", "Error nombre Empresa", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoNombreEmpresa.requestFocus();
            }

            if(this.campoTextoNitEmpresa.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un NIT para la empresa.", "Error NIT empresa.", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoNitEmpresa.requestFocus();
            }
            
            String nombreEmpresa = this.campoTextoNombreEmpresa.getText();
            String nitEmpresa = this.campoTextoNitEmpresa.getText();
            ClsEmpresa objEmpresaActualizar = new ClsEmpresa(this.objEmpresa.getIdEmpresa(), nombreEmpresa, nitEmpresa);
            objEmpresaActualizar = objEmpresaServices.actualizarInformacionEmpresa(objEmpresaActualizar);
            if(objEmpresaActualizar != null) {
            	System.out.println("Se actualizo la informacion de la empresa.");
            	this.dispose();
            }
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo la actualizacion de la informacion de la empresa.");
        }
    }
    
    /**
     * Pide una confirmacion para cerrar la aplicacion
     */
    private void cerrar()
    {
        String[] opciones = {"Cerrar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Cerrar esta ventana?", "Cerrar", 0, 0, null, opciones, this);
        if(eleccion == JOptionPane.YES_OPTION) {
            this.dispose();
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo el cierre.");
        }
    }
}
