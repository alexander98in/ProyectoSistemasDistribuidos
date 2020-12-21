package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import sop_corba.GestionNotificacionPackage.hamburguesaNotificacionDTO;
import sop_corba.GestionNotificacionPackage.notificacionDTO;

@SuppressWarnings("serial")
public class GUINotificacion extends JFrame {

    //Paneles de la GUICliente
    private final JPanel panelNorte;
    private final JPanel panelCentral;
    private final JPanel panelSur;
    
    //Etiquetas para la GUICliente
    private final JLabel etiquetaTitulo;
    
    //Areas de Texto 
    public final JTextArea areaTextoInformacion;
 
    //Botones
    private final JButton botonSalir;
 
    public GUINotificacion() 
    {	
    	super("Notificacion");
    	//Agregamos un esquema de distribucion a nuestro JFrame
    	this.setLayout(new BorderLayout(5, 5));
    	
    	/**
    	 * Panel Norte
    	 */
    	
    	//Panel que se ubica en la parte norte
    	this.panelNorte = new JPanel();
    	panelNorte.setLayout(new GridLayout(1, 1, 5, 5));
    	//Etiqueta titulo
        etiquetaTitulo = new JLabel("Notificación", SwingConstants.CENTER);
        etiquetaTitulo.setToolTipText("Titulo");
        etiquetaTitulo.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 50));
        etiquetaTitulo.setBackground(new Color(212, 239, 223));
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
        panelCentral.setLayout(new BorderLayout(5, 5));
        //Area donde se mostrara la informacion de la notificacion
        areaTextoInformacion = new JTextArea();
        areaTextoInformacion.setEditable(true);
        areaTextoInformacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 3),
                "Informacion: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        areaTextoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        areaTextoInformacion.setBackground(new Color(229, 231, 233));
        //Agreamos la area de texto de informacion de la notificacion al panel central
        panelCentral.add(new JScrollPane(areaTextoInformacion), BorderLayout.CENTER);
        //Agregamos el panel central al esquema principal
        this.add(panelCentral, BorderLayout.CENTER);
        
        /**
         * Panel Sur
         */
        
        //Panel que se ubica en la parte sur
        this.panelSur = new JPanel();
        this.panelSur.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.panelSur.setBackground(new Color(178, 223, 219));
        //Boton Salir
        botonSalir = new JButton(" SALIR ");
        botonSalir.setFont(new Font("Tahoma", Font.BOLD, 20));
        botonSalir.setBackground(new Color(241, 148, 138));
        botonSalir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonSalir.setMaximumSize(new Dimension(180,150));
        botonSalir.setPreferredSize(new Dimension(250,70));
        //Agrega una accion al boton Salir
        botonSalir.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de salir
                public void actionPerformed(ActionEvent evento) {
                    salir();
                }
            }     
        );    
        //Agregamos los botones al panel sur
        this.panelSur.add(this.botonSalir);
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
    }
    //Fin del constructor de la GUIEmpresa
 
    /**
     * Cancela la edicion de la informacion de la empresa
     */
    private void salir()
    {
    	System.exit(0);
    }
    
    /**
     * Pide una confirmacion para cerrar la aplicacion
     */
    private void cerrar()
    {
        String[] opciones = {"Cerrar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Cerrar la Aplicacion?", "Cerrar", 0, 0, null, opciones, this);
        if(eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo el cierre.");
        }
    }
    
    /**
     * Muestra la notificacion del pedido
     * @param objNotificacionPedido 
     */
    public void mostrarNotificacion(notificacionDTO objNotificacionPedido)
    {
        String notificacion = "";
        
        notificacion += "\n = = = Nuevo pedido = = = \n";
        notificacion += "\n No de Mesa: " + objNotificacionPedido.numeroMesa;
        int numHam = 0;
        for(hamburguesaNotificacionDTO hamburguesa: objNotificacionPedido.listaHamburguesas) {
            notificacion += "\n\n Hamburguesa No: " + (numHam+1);
            notificacion += "\n Tipo: " + obtenerTipoHamburguesa(hamburguesa.tipo);
            notificacion += "\n Cantidad Ingredientes Extra: " + hamburguesa.cantidadIngredientesExtra;  
        }
        notificacion += "\n---------------------------------------\n";
        this.areaTextoInformacion.append(notificacion);
        
    }  
    
    /**
     * Obtiene el tipo de hamburguesa
     * @param parTipo
     * @return String 
     */
    private String obtenerTipoHamburguesa(char parTipo){
        switch(parTipo){
            case 'p':
                return "Pequeña.";
            case 'm':
                return "Mediana.";
            case 'g':
                return "Grande.";
            default:
                return "Ninguna.";
        }
    }
}
