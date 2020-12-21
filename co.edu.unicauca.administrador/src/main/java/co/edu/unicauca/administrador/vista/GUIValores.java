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

import co.edu.unicauca.administrador.models.ClsValores;
import co.edu.unicauca.administrador.servicios.ClsValoresServices;

@SuppressWarnings("serial")
public class GUIValores extends JFrame {
	
	private ClsValoresServices objValoresServices;
	private ClsValores objValores;
	
	//Paneles de la GUICliente
    private final JPanel panelNorte;
    private final JPanel panelCentral;
    private final JPanel panelSur;
    
    //Etiquetas para la GUICliente
    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaValorHamPequenia;
    private final JLabel etiquetaValorHamMediana;
    private final JLabel etiquetaValorHamGrande;
    private final JLabel etiquetaValorIngreExtra;
    
    //Campos de Texto
    private final JTextField campoTextoValorHamPequenia;
    private final JTextField campoTextoValorHamMediana;
    private final JTextField campoTextoValorHamGrande;
    private final JTextField campoTextoValorIngreExtra;
    
    //Botones
    private final JButton botonCancelar;
    private final JButton botonActualizar;
    
    public GUIValores(ClsValores objValoresActualizar) {
    	
    	super("Editar Valores");
    	this.objValoresServices = new ClsValoresServices();
    	this.objValores = objValoresActualizar;
    	
    	//Agregamos un esquema de distribucion a nuestro JFrame
    	this.setLayout(new BorderLayout(5, 5));
    	
    	/**
    	 * Panel Norte
    	 */
    	
    	//Panel que se ubica en la parte norte
    	this.panelNorte = new JPanel();
    	panelNorte.setLayout(new GridLayout(1, 1, 5, 5));
    	//Etiqueta titulo
        etiquetaTitulo = new JLabel("Editar Valores", SwingConstants.CENTER);
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
        panelCentral.setLayout(new GridLayout(4, 2, 5, 5));
        
        //Etiqueta valor hamburguesa pequenia
        this.etiquetaValorHamPequenia = new JLabel("Valor Hamburguesa Pequeña:  ", SwingConstants.RIGHT);
        this.etiquetaValorHamPequenia.setToolTipText("Ingrese el valor para la hamburguesa de tipo pequeña.");
        this.etiquetaValorHamPequenia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.etiquetaValorHamPequenia.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.etiquetaValorHamPequenia.setBackground(new Color(163, 228, 215));
        this.etiquetaValorHamPequenia.setOpaque(true);
        
        //Etiqueta valor hamburguesa mediana
        this.etiquetaValorHamMediana = new JLabel("Valor Hamburguesa Mediana:  ", SwingConstants.RIGHT);
        this.etiquetaValorHamMediana.setToolTipText("Ingrese el valor para la hamburguesa de tipo mediana.");
        this.etiquetaValorHamMediana.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.etiquetaValorHamMediana.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.etiquetaValorHamMediana.setBackground(new Color(163, 228, 215));
        this.etiquetaValorHamMediana.setOpaque(true);
        
        //Etiqueta valor hamburguesa Grande
        this.etiquetaValorHamGrande = new JLabel("Valor Hamburguesa Grande:  ", SwingConstants.RIGHT);
        this.etiquetaValorHamGrande.setToolTipText("Ingrese el valor para la hamburguesa de tipo grande.");
        this.etiquetaValorHamGrande.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.etiquetaValorHamGrande.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.etiquetaValorHamGrande.setBackground(new Color(163, 228, 215));
        this.etiquetaValorHamGrande.setOpaque(true);
        
        //Etiqueta valor ingrediente extra
        this.etiquetaValorIngreExtra = new JLabel("Valor por ingrediente extra:  ", SwingConstants.RIGHT);
        this.etiquetaValorIngreExtra.setToolTipText("Ingrese el valor del ingrediente extra.");
        this.etiquetaValorIngreExtra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.etiquetaValorIngreExtra.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.etiquetaValorIngreExtra.setBackground(new Color(163, 228, 215));
        this.etiquetaValorIngreExtra.setOpaque(true);
        
        //Campo de texto del valor de la hamburguesa pequeña
        this.campoTextoValorHamPequenia = new JTextField();
        this.campoTextoValorHamPequenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.campoTextoValorHamPequenia.setBackground(new Color(240, 243, 244));     
        //Campo de texto del valor de la hamburguesa mediana
        this.campoTextoValorHamMediana = new JTextField();
        this.campoTextoValorHamMediana.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.campoTextoValorHamMediana.setBackground(new Color(240, 243, 244));  
        //Campo de texto del valor de la hamburguesa grande
        this.campoTextoValorHamGrande = new JTextField();
        this.campoTextoValorHamGrande.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.campoTextoValorHamGrande.setBackground(new Color(240, 243, 244)); 
        //Campo de texto del valor del ingrediente extra
        this.campoTextoValorIngreExtra = new JTextField();
        this.campoTextoValorIngreExtra.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.campoTextoValorIngreExtra.setBackground(new Color(240, 243, 244)); 
        
        //Llenamos los campos con los valores.
        llenarCamposDeTexto();
        //Agregamos los elementos al panel central
        this.panelCentral.add(this.etiquetaValorHamPequenia);
        this.panelCentral.add(this.campoTextoValorHamPequenia);
        this.panelCentral.add(this.etiquetaValorHamMediana);
        this.panelCentral.add(this.campoTextoValorHamMediana);
        this.panelCentral.add(this.etiquetaValorHamGrande);
        this.panelCentral.add(this.campoTextoValorHamGrande);
        this.panelCentral.add(this.etiquetaValorIngreExtra);
        this.panelCentral.add(this.campoTextoValorIngreExtra);
 
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
                //Evento de cancelar edicion de los valores
                public void actionPerformed(ActionEvent evento) {
                    cancelarEditarValores();
                }
            }     
        );
        
        //Boton Actualizar
        Icon actulizar = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/actualizar_opt.jpg"));
        botonActualizar = new JButton(" Actualizar Valores ", actulizar);
        botonActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonActualizar.setBackground(new Color(174, 214, 241));
        botonActualizar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonActualizar.setEnabled(true);
        //Agrega un evento al boton Actualizar
        botonActualizar.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Procesa evento de actualizar los valores de las hamburguesas
                public void actionPerformed(ActionEvent evento) {
                    actualizarValores();
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
    	if(this.objValores != null) {
    		this.campoTextoValorHamPequenia.setText(""+this.objValores.getValorHamburguesaPequenia());
    		this.campoTextoValorHamMediana.setText(""+this.objValores.getValorHamburguesaMediana());
    		this.campoTextoValorHamGrande.setText(""+this.objValores.getValorHamburgeusaGrande());
    		this.campoTextoValorIngreExtra.setText(""+this.objValores.getValorIngredienteExtra());
    	}
    }
    
    /**
     * Cancela la edicion de los valores de las hamburguesas
     */
    private void cancelarEditarValores()
    {
    	this.dispose();
    }
    
    /**
     * Actualiza la informacion de la empresa.
     */
    private void actualizarValores()
    {	
        String[] opciones = {"Confirmar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea actualizar la informacion de la empresa?", "Actualizar informacion", 0, 0, null, opciones, this);
        
        if(eleccion == JOptionPane.YES_OPTION)
        {
        	if(this.campoTextoValorHamPequenia.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor para la hamburguesa pequeña.", "Error valor Hamburguesa", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoValorHamPequenia.requestFocus();
            }
        	if(this.campoTextoValorHamMediana.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor para la hamburguesa mediana.", "Error valor Hamburguesa", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoValorHamMediana.requestFocus();
            }	
        	if(this.campoTextoValorHamGrande.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor para la hamburguesa grande.", "Error valor Hamburguesa", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoValorHamGrande.requestFocus();
            }	
        	if(this.campoTextoValorIngreExtra.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor para el ingrediente extra.", "Error valor Ingrediente Extra", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoValorIngreExtra.requestFocus();
            }

            Float valorHamburguesaPequenia = null;
            Float valorHamburguesaMediana = null;
            Float valorHamburguesaGrande = null;
            Float valorIngredienteExtra = null;
            
            try
            {
            	valorHamburguesaPequenia = Float.parseFloat(this.campoTextoValorHamPequenia.getText());
            	valorHamburguesaMediana = Float.parseFloat(this.campoTextoValorHamMediana.getText());
            	valorHamburguesaGrande = Float.parseFloat(this.campoTextoValorHamGrande.getText());
            	valorIngredienteExtra = Float.parseFloat(this.campoTextoValorIngreExtra.getText());
            	
            	ClsValores objValoresActualizar = new ClsValores(this.objValores.getIdValor(), this.objValores.getIdEmpresa(), 
            			valorIngredienteExtra, valorHamburguesaPequenia, valorHamburguesaMediana, valorHamburguesaGrande);
            	objValoresActualizar = this.objValoresServices.actualizarValores(objValoresActualizar);
            	if(objValoresActualizar != null) {
            		System.out.println("Se actualizo los valores de las hamburguesas.");
            		this.dispose();
            	}
                
            }
            catch(NumberFormatException ex) 
            {
            	System.out.println("Ingreso no valido. " + ex.getMessage());
            	JOptionPane.showMessageDialog(this, "Debe ingresar un numero float.", "Error valor ingresado", JOptionPane.ERROR_MESSAGE);
                this.campoTextoValorHamPequenia.requestFocus();
            }
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo la actualizacion de los valores de las hamburguesas.");
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
