package co.edu.unicauca.administrador.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import co.edu.unicauca.administrador.models.ClsEmpresa;
import co.edu.unicauca.administrador.models.ClsFactura;
import co.edu.unicauca.administrador.models.ClsValores;
import co.edu.unicauca.administrador.servicios.ClsEmpresaServices;
import co.edu.unicauca.administrador.servicios.ClsFacturaServices;
import co.edu.unicauca.administrador.servicios.ClsValoresServices;

@SuppressWarnings("serial")
public class GUIAdministrador extends JFrame {
	
	private ClsFacturaServices objFacturaServices;
	private ClsEmpresaServices objEmpresaServices;
	private ClsValoresServices objValoresServices;
	    
	//Paneles o contenedores de componentes
    private final JPanel panelNorte;
    private final JPanel panelEste;
    private final JPanel panelCentral;
    private final JPanel panelSur;  
    
    //Etiqueta
    private final JLabel etiquetaTitulo;
    
    //Areas de Texto 
    private final JTextArea areaTextoInformacion;
    
    //Botones
    private final JButton botonEliminarTodasFacturas;
    private final JButton botonListarFacturas;
    private final JButton botonEliminarFactura;
    private final JButton botonDatosEmpresa;
    private final JButton botonEditarEmpresa;
    private final JButton botonEditarValores;
    private final JButton botonSalir; 
    
    //Modelo de la lista
    private final DefaultListModel<String> modeloLista;
    //JList o lista para las hamburguesas
    private final JList<String> listListaFacturas;
    
    public GUIAdministrador() {
    	
    	super("Administrador");
    	this.objFacturaServices = new ClsFacturaServices();
    	this.objEmpresaServices = new ClsEmpresaServices();
    	this.objValoresServices = new ClsValoresServices();
    	
    	//Agregamos un Esquema de distribucion a nuestro JFrame
    	this.setLayout(new BorderLayout(5, 5));
    	
    	/**
    	 * Panel Norte
    	 */
    	
    	//Panel que se ubica en la parte norte
        panelNorte = new JPanel();
        panelNorte.setLayout(new GridLayout(1, 1, 5, 5));
        //Etiqueta titulo
        etiquetaTitulo = new JLabel("Administrador", SwingConstants.CENTER);
        etiquetaTitulo.setToolTipText("Titulo");
        etiquetaTitulo.setBorder(BorderFactory.createLineBorder(new Color(249, 168, 37), 3));
        etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 50));
        etiquetaTitulo.setBackground(new Color(76, 175, 80));
        etiquetaTitulo.setOpaque(true);
        //Agregamos la etiqueta al panel Norte
        panelNorte.add(etiquetaTitulo);
        //Agreamos el panel al JFrame
        add(panelNorte, BorderLayout.NORTH);
    	
        /**
         * Panel Este
         */
        
        //Panel que se ubica en la parte Este 
        panelEste = new JPanel();
        panelEste.setLayout(new BorderLayout(5, 5));
        
        modeloLista = new DefaultListModel<String>();
        //Lista de facturas
        listListaFacturas = new JList<String>();
        listListaFacturas.setModel(modeloLista);
        listListaFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listListaFacturas.setBackground(new Color(254, 249, 231));
        listListaFacturas.setFont(new Font("Tahoma", Font.PLAIN, 15));
        listListaFacturas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(62, 39, 35), 3),
                "Lista de Facturas: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        
        listListaFacturas.addListSelectionListener(
            new ListSelectionListener() {
                //Evento para mostrar Factura
                public void valueChanged(ListSelectionEvent evento) {
                    buscarYMostrarFactura();
                }
            }
        );
        
        //Agregamos la lista al panel Este
        panelEste.add(new JScrollPane(listListaFacturas), BorderLayout.CENTER);
        //Agregamos el panel este al JFrame
        add(panelEste, BorderLayout.EAST);
        
        /**
         * Panel Central
         */
        //Panel que se ubica en la parte central
        panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout(5, 5));
        //Area donde se mostrara la informacion de las facturas
        areaTextoInformacion = new JTextArea();
        areaTextoInformacion.setEditable(false);
        areaTextoInformacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 3),
                "Informacion: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        areaTextoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
        areaTextoInformacion.setBackground(new Color(224, 224, 224 ));
        //Agreamos la area de texto de informacion de las facturas al panel central
        panelCentral.add(new JScrollPane(areaTextoInformacion), BorderLayout.CENTER);
        
        
     	//Boton Eliminar todas las Facturas
        Icon eliminar = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/eliminar_opt.jpg"));
        botonEliminarTodasFacturas = new JButton(" Eliminar Todas las Facturas ", eliminar);
        botonEliminarTodasFacturas.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonEliminarTodasFacturas.setBackground(new Color(240, 178, 122));
        botonEliminarTodasFacturas.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonEliminarTodasFacturas.setEnabled(true);
        //Agrega una accion al boton eliminar todas las facturas
        botonEliminarTodasFacturas.addActionListener(
            new ActionListener() //Clase interna anonima
            {
            	//Evento de eliminar todas las facturas registradas
                public void actionPerformed(ActionEvent evento) {
                    eliminarTodasFacturas();
                }
            }     
        );
        //Agregamos el boton de eliminar todas las facturas al panel central
        panelCentral.add(botonEliminarTodasFacturas, BorderLayout.SOUTH);
        //Agregamos el panel central al JFrame
        add(panelCentral, BorderLayout.CENTER);
        
        /**
         * Panel Sur
         */
        panelSur = new JPanel();
        panelSur.setLayout(new GridLayout(2, 3, 5, 5));    
        panelSur.setBackground(new Color(255, 171, 145));
        panelSur.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(191, 54, 12), 3),
                "Opciones: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        
        //Boton Listar Facturas
        Icon listar = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/listar_opt.jpg"));
        botonListarFacturas = new JButton("   Listar Facturas  ", listar);
        botonListarFacturas.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonListarFacturas.setBackground(new Color(215, 189, 226));
        botonListarFacturas.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Listar
        botonListarFacturas.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de listar las facturas registradas
                public void actionPerformed(ActionEvent evento) {
                    listarFacturasRegistradas();
                }
            }     
        );
        panelSur.add(botonListarFacturas);
        
        //Boton Eliminar Factura
        Icon eliminarFactura = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/eliminar_opt.jpg"));
        botonEliminarFactura = new JButton("   Eliminar Factura ", eliminarFactura);
        botonEliminarFactura.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonEliminarFactura.setBackground(new Color(240, 178, 122));
        botonEliminarFactura.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonEliminarFactura.setEnabled(false);
        //Agrega una accion al boto Eliminar
        botonEliminarFactura.addActionListener(
            new ActionListener() //Clase interna anonima
            {
            	//Evento de eliminar una hamburguesas del pedido
                public void actionPerformed(ActionEvent evento) {
                    eliminarFacturaRegistrada();
                }
            }     
        );
        panelSur.add(botonEliminarFactura);
        
        //Boton Mostrar Datos Empresa y valores
        Icon datosEmpresa = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/burger_opt.png"));
        botonDatosEmpresa = new JButton("    Datos Empresa  ", datosEmpresa);
        botonDatosEmpresa.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonDatosEmpresa.setBackground(new Color(208, 211, 212));
        botonDatosEmpresa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Datos Empresa       
        botonDatosEmpresa.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de mostrar datos y valores al administrador
                public void actionPerformed(ActionEvent evento) {
                    mostrarDatosEmpresaYPrecios();
                }
            }     
        );
        panelSur.add(botonDatosEmpresa);
        
        //Boton editar datos Empresa
        Icon modificarEmpresa = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/modificar_opt.png"));
        botonEditarEmpresa = new JButton("  Editar Empresa  ", modificarEmpresa);
        botonEditarEmpresa.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonEditarEmpresa.setBackground(new Color(133, 193, 233));
        botonEditarEmpresa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonEditarEmpresa.setEnabled(true);
        //Agrega una accion al boton Editar Empresa
        botonEditarEmpresa.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Agrega el evento de editar los datos de la empresa
                public void actionPerformed(ActionEvent evento) {
                    editarDatosEmpresa();
                }
            }     
        );
        panelSur.add(botonEditarEmpresa);
        
        //Boton editar Valores
        Icon modificarValores = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/modificar_opt.png"));
        botonEditarValores = new JButton("  Editar Valores  ", modificarValores);
        botonEditarValores.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonEditarValores.setBackground(new Color(133, 193, 233));
        botonEditarValores.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonEditarValores.setEnabled(true);
        //Agrega una accion al boton editar Valores
        botonEditarValores.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Agrega el evento de editar valores de las hamburguesas.
                public void actionPerformed(ActionEvent evento) {
                    editarValores();
                }
            }     
        );
        panelSur.add(botonEditarValores);
        
        //BotonSalir
        Icon salir = new ImageIcon(getClass().getResource("/co/edu/unicauca/administrador/recursos/salir_opt.png"));
        botonSalir = new JButton(" Salir ", salir);
        botonSalir.setFont(new Font("Tahoma", Font.BOLD, 20));
        botonSalir.setBackground(new Color(249, 231, 159));
        botonSalir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        botonSalir.addActionListener(
            new ActionListener() {
                //Agrega el evento de salir
                public void actionPerformed(ActionEvent e) {
                    salir();
                }
            }   
        );
        //Agreamos el boton salir al panel Sur
        panelSur.add(botonSalir);
        //Agreamos el panel Sur al JFrame
        add(panelSur, BorderLayout.SOUTH);

        this.addWindowListener(
        	new WindowAdapter() {
        		@Override
                public void windowClosing(WindowEvent e) {
                    cerrar();
        		}
        	}
        );
    }//Fin del constructor de GUIAdministrador
    
    /**
     * Busca y muestra la informacion de una factura.
     */
    private void buscarYMostrarFactura()
    {
    	if(!listListaFacturas.isSelectionEmpty())
    	{
    		this.botonEliminarFactura.setEnabled(true);
        	String seleccionLista = this.modeloLista.getElementAt(listListaFacturas.getSelectedIndex());
        	String[] cadenaAux = seleccionLista.split("_");
            Integer idFactura = Integer.parseInt(cadenaAux[1]);
            ClsFactura objFactura = objFacturaServices.consultarFactura(idFactura);
            
            if(objFactura != null)
        	{
        		this.areaTextoInformacion.setText("");
        		this.areaTextoInformacion.append("\nFactura No: " + objFactura.getIdFactura());
        		this.areaTextoInformacion.append("\n\nHamburguesas de tipo pequeño: " + objFactura.getNumHamburguesasPequenias());
        		this.areaTextoInformacion.append("\nHamburguesas de tipo mediano: " + objFactura.getNumHamburguesasMedianas());
        		this.areaTextoInformacion.append("\nHamburguesas de tipo grande: " + objFactura.getNumHamburguesasGrandes());
        		this.areaTextoInformacion.append("\n\nCosto sin IVA del pedido: $" + objFactura.getCostoSinIVA());
        		this.areaTextoInformacion.append("\nIVA del pedido: " + objFactura.getIVA());
        		this.areaTextoInformacion.append("\nCosto con IVA del pedido: " + objFactura.getCostoConIVA());
        		this.areaTextoInformacion.append("\n------------------------");
        	}
            else {
            	areaTextoInformacion.setText("");
                areaTextoInformacion.append("\nNo se encontro la factura.");
            }
    	}
    }
    
    
    private void eliminarTodasFacturas()
    {
    	if(!this.modeloLista.isEmpty()) {
    		String[] opciones = {"Eliminar todas las Facturas", "Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Eliminar todas las Facturas?", "Eliminar todas las Facturas", 0, 0, null, opciones, this);
            
            if(eleccion == JOptionPane.YES_OPTION)
            {
            	listListaFacturas.clearSelection();
            	this.botonEliminarFactura.setEnabled(false);
            		
            	Boolean seEliminaronFacturas = objFacturaServices.eliminarTodasFacturas();
            	
                if(seEliminaronFacturas) {
                	this.modeloLista.clear();
                	this.areaTextoInformacion.setText("");
                	this.areaTextoInformacion.append("Se eliminaron todas las facturas con EXITO.");
                }
                else {
                	this.areaTextoInformacion.setText("");
                	this.areaTextoInformacion.append("Las facturas no se eliminaron.");
                }
                this.botonEliminarFactura.setEnabled(false);
                
            }
            else if(eleccion == JOptionPane.NO_OPTION) {
                System.out.println("Se cancelo la eliminacion de todas las facturas.");
            }	
    	}
    	else {
    		this.areaTextoInformacion.setText("");
        	this.areaTextoInformacion.append("No existen facturas registradas.");
    	}	
    }
    
    /**
     * Permite listar las facturas que se encuentran registradas
     */
    private void listarFacturasRegistradas()
    {
    	listListaFacturas.clearSelection();
    	this.botonEliminarFactura.setEnabled(false);
    	modeloLista.clear();
    	List<ClsFactura> listaFacturasRegistradas = this.objFacturaServices.listarFacturas();
    	if(!listaFacturasRegistradas.isEmpty())
    	{
    		for(int i=0; i<listaFacturasRegistradas.size(); i++) {
    			this.modeloLista.addElement("Factura_" + listaFacturasRegistradas.get(i).getIdFactura());
    		}
    	}
    	else {
    		this.areaTextoInformacion.setText("");
    		this.areaTextoInformacion.append("No existen facturas registradas.");
    	}
    }
    
    /**
     * Elimina la factura que se selecciono de la lista.
     */
    private void eliminarFacturaRegistrada() 
    {
    	if(!listListaFacturas.isSelectionEmpty())
    	{
    		String[] opciones = {"Eliminar Factura", "Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Eliminar la Factura?", "Eliminar Factura", 0, 0, null, opciones, this);
            
            if(eleccion == JOptionPane.YES_OPTION)
            {
            	String seleccionLista = this.modeloLista.getElementAt(listListaFacturas.getSelectedIndex());
            	String[] cadenaAux = seleccionLista.split("_");
                Integer idFactura = Integer.parseInt(cadenaAux[1]);
                
                Boolean seEliminoFactura = objFacturaServices.eliminarFactura(idFactura);
                
                if(seEliminoFactura) {
                	this.modeloLista.remove(listListaFacturas.getSelectedIndex());
                	this.areaTextoInformacion.setText("");
                	this.areaTextoInformacion.append("La factura se elimino con EXITO.");
                }
                else {
                	this.areaTextoInformacion.setText("");
                	this.areaTextoInformacion.append("La factura no se elimino con exito.");
                }
                this.botonEliminarFactura.setEnabled(false);
                
            }
            else if(eleccion == JOptionPane.NO_OPTION) {
                System.out.println("Se cancelo la eliminacion de la factura.");
            }  
    	}
    }
    
    /**
     * Permite visualizar los datos de la empresa y valores de las hamburguesas
     */
    private void mostrarDatosEmpresaYPrecios()
    {
    	ClsEmpresa objDatosEmpresa = objEmpresaServices.consultarInformacionEmpresa();
    	ClsValores objValores = objValoresServices.consultarValores();
    	this.areaTextoInformacion.setText("");
    	if(objDatosEmpresa != null)
    	{
    		this.areaTextoInformacion.append("\nInformacion de la empresa: ");
    		this.areaTextoInformacion.append("\nNombre: " + objDatosEmpresa.getNombre());
    		this.areaTextoInformacion.append("\nNIT: " + objDatosEmpresa.getNIT());
    		this.areaTextoInformacion.append("\n_______________________________");
    		
    	}
    	if(objValores != null)
    	{
    		this.areaTextoInformacion.append("\nPrecio unidad ham pequeña: " + objValores.getValorHamburguesaPequenia());
    		this.areaTextoInformacion.append("\nPrecio unidad ham mediana: " + objValores.getValorHamburguesaMediana());
    		this.areaTextoInformacion.append("\nPrecio unidad ham grande: " + objValores.getValorHamburgeusaGrande());
    		this.areaTextoInformacion.append("\nEl precio por ingrediente extra es de: " + objValores.getValorIngredienteExtra());
    	} 
    }
    
    /**
     * Crea la vista para editar la informacion de la empresa.
     */
    private void editarDatosEmpresa() 
    {
    	ClsEmpresa objEmpresaActualizar = objEmpresaServices.consultarInformacionEmpresa();
    	if(objEmpresaActualizar != null) {
    		GUIEmpresa vistaEmpresa = new GUIEmpresa(objEmpresaActualizar);
    		vistaEmpresa.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    		vistaEmpresa.setSize(640, 310);
    		vistaEmpresa.setVisible(true);
    		vistaEmpresa.setLocationRelativeTo(null);		
    	}
    	else {
    		this.areaTextoInformacion.setText("");
    		this.areaTextoInformacion.append("No se puede editar la informacion de la empresa.");
    	}
    }
    
    /**
     * Crea la vista para editar la informacion de los valores.
     */
    private void editarValores()
    {
    	ClsValores objValoresActualizar = objValoresServices.consultarValores();
    	if(objValoresActualizar != null) {
    		GUIValores vistaValores = new GUIValores(objValoresActualizar);
    		vistaValores.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    		vistaValores.setSize(640, 410);
    		vistaValores.setVisible(true);
    		vistaValores.setLocationRelativeTo(null);
    	}
    	else {
    		this.areaTextoInformacion.setText("");
    		this.areaTextoInformacion.append("No se puede editar los valores de las hamburguesas.");
    	}
    }
    
    /**
     * Permite cerrar o salir de la aplicacion.
     */
    private void salir(){
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
}
