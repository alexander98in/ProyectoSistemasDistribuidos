package co.edu.unicauca.clientehamburguesas.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import co.edu.unicauca.clientehamburguesas.bussines.ClsComputoIVA;
import co.edu.unicauca.clientehamburguesas.bussines.ClsConteoHamburguesas;
import co.edu.unicauca.clientehamburguesas.models.ClsEmpresa;
import co.edu.unicauca.clientehamburguesas.models.ClsFactura;
import co.edu.unicauca.clientehamburguesas.models.ClsHamburguesa;
import co.edu.unicauca.clientehamburguesas.models.ClsPedido;
import co.edu.unicauca.clientehamburguesas.models.ClsValores;
import co.edu.unicauca.clientehamburguesas.servicios.ClsEmpresaServices;
import co.edu.unicauca.clientehamburguesas.servicios.ClsPedidoServices;
import co.edu.unicauca.clientehamburguesas.servicios.ClsValoresServices;

/**
 * Interfaz Grafica de Usuario para el cliente
 * @author Leyder Alexander Inagan
 */
@SuppressWarnings("serial")
public class GUICliente extends JFrame {
    
    private ClsPedidoServices objPedidoServices;
    private ClsEmpresaServices objEmpresaServices;
    private ClsValoresServices objValoresServices;
    
    //private Integer numeroMesa;
    private ClsPedido objPedido;
    private int numeroHamburguesas;
    
    //Paneles de la GUICliente
    private final JPanel panelNorte;
    private final JPanel panelSur;
    private final JPanel panelCentral;
    private final JPanel panelEste;
    private final JPanel panelInternoIngresoDatos;
    private final JPanel panelInternoListaHam;
    private final JPanel panelNorteDelPanelLista;
    private final JPanel panelCentroDelPanelLista;
     
    //Etiquetas para la GUICliente
    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaNombreHamburguesa;
    private final JLabel etiquetaTipoHamburguesa;
    private final JLabel etiquetaCantIngreExtra;
    private final JLabel etiquetaPrecio;
    
    //Campos de Texto
    private final JTextField campoTextoNombreHamburguesa;
    private final JTextField campoTextoCantIngExt;
    private final JTextField campoTextoPrecio;
    private final JTextField campoTextoNumMesa;
    
    //JComboBox para el tipo de la Hamburguesa
    private static final String[] tiposHamburguesa = {"Pequeña", "Mediana", "Grande"};
    private final JComboBox<String> listaDesplTipoHam;
    
    //JList o lista para las hamburguesas
    private final JList<String> listaListHamburguesas;
    
    //Modelo de la lista para hamburguesas
    private final DefaultListModel<String> modeloLista;
    //Areas de Texto
    private final JTextArea areaTextoInformacion;
    
    //Botones
    private final JButton botonRealizarCompra;
    private final JButton botonActualizar;
    private final JButton botonModificarPedido;
    private final JButton botonListarPedidos;
    private final JButton botonEliminar;
    private final JButton botonDatosEmpresa;
    private final JButton botonEfectuarPedido;
    private final JButton botonSalir;    
    
    public GUICliente()
    {
        super("Cliente Usuario");
        
        this.objPedidoServices = new ClsPedidoServices();
        this.objEmpresaServices = new ClsEmpresaServices(); 
        this.objValoresServices = new ClsValoresServices();
        this.objPedido = new ClsPedido();
        this.numeroHamburguesas = 0;
        
        this.setLayout(new BorderLayout(5, 5));
        
        /**
         * Panel Norte
         */
        this.panelNorte = new JPanel();
        this.panelNorte.setLayout(new GridLayout(1, 1, 5, 5));
        //Etiqueta titulo
        etiquetaTitulo = new JLabel("Chubby Burger", SwingConstants.CENTER);
        etiquetaTitulo.setToolTipText("Titulo");
        etiquetaTitulo.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        etiquetaTitulo.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 50));
        etiquetaTitulo.setBackground(new Color(249, 231, 159));
        etiquetaTitulo.setOpaque(true);
        //Agregamos la etiqueta al panel norte
        this.panelNorte.add(etiquetaTitulo);
        //Agregamos el panel norte al esquema principal
        this.add(panelNorte, BorderLayout.NORTH);
         
        /**
         * Panel Central
         */
        this.panelCentral = new JPanel();
        this.panelCentral.setLayout(new GridLayout(2, 1, 5, 5));
        
        //Elementos del panel interno que se adhiere al panel central
  
        //Etiqueta del Nombre de la Hamburguesa
        this.etiquetaNombreHamburguesa = new JLabel("Nombre:  ", SwingConstants.RIGHT);
        this.etiquetaNombreHamburguesa.setToolTipText("Ingrese el nombre de su hamburguesa.");
        this.etiquetaNombreHamburguesa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.etiquetaNombreHamburguesa.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.etiquetaNombreHamburguesa.setBackground(new Color(163, 228, 215));
        this.etiquetaNombreHamburguesa.setOpaque(true);
        
        //Etiqueta tipo de Hamburguesa
        this.etiquetaTipoHamburguesa = new JLabel("Tipo de Hamburguesa:  ", SwingConstants.RIGHT);
        etiquetaTipoHamburguesa.setToolTipText("Seleccione el tipo de la hamburguesa que desea.");
        etiquetaTipoHamburguesa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaTipoHamburguesa.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaTipoHamburguesa.setBackground(new Color(163, 228, 215));
        etiquetaTipoHamburguesa.setOpaque(true);
        
        //Etiqueta cantidad de ingredientes extra
        etiquetaCantIngreExtra = new JLabel("Ingredientes Extra:  ", SwingConstants.RIGHT);
        etiquetaCantIngreExtra.setToolTipText("Ingrese la cantidad de ingredientes extra");
        etiquetaCantIngreExtra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaCantIngreExtra.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaCantIngreExtra.setBackground(new Color(163, 228, 215));
        etiquetaCantIngreExtra.setOpaque(true);
        
        //Etiqueta Precio
        etiquetaPrecio = new JLabel("Precio:  ", SwingConstants.RIGHT);
        etiquetaPrecio.setToolTipText("Precio de su Hamburguesa");
        etiquetaPrecio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        etiquetaPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaPrecio.setBackground(new Color(163, 228, 215));
        etiquetaPrecio.setOpaque(true);
        
        //Campo de texto del nombre de la hamburguesa
        this.campoTextoNombreHamburguesa = new JTextField();
        this.campoTextoNombreHamburguesa.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.campoTextoNombreHamburguesa.setBackground(new Color(240, 243, 244));     
        this.campoTextoNombreHamburguesa.addFocusListener(
            new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent evento) {
                    validarIdHamburguesa();
                } 
            }
        );
        
        //Lista desplegable de tipos de hamburguesa
        listaDesplTipoHam = new JComboBox<String>(tiposHamburguesa);
        listaDesplTipoHam.setMaximumRowCount(3);
        listaDesplTipoHam.setBackground(new Color(213, 219, 219));
        this.listaDesplTipoHam.addItemListener(
        	new ItemListener() {	
				public void itemStateChanged(ItemEvent e) {
					colocarPrecio();
				}
			}	
        );
        
        //Campo de texto cantidad de ingredientes Extra
        campoTextoCantIngExt = new JTextField();
        campoTextoCantIngExt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoTextoCantIngExt.setBackground(new Color(240, 243, 244));
        campoTextoCantIngExt.addFocusListener(
            new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent evento) {
                    comprobarIngredientesExtra();
                } 
            }
        );

        //Campo de texto del precio de la Hamburguesa
        campoTextoPrecio = new JTextField();
        colocarPrecio();
        campoTextoPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoTextoPrecio.setBackground(new Color(240, 243, 244));
        campoTextoPrecio.setEditable(false);
        
        //Panel interno que se adhiere al panel central
        panelInternoIngresoDatos = new JPanel();
        panelInternoIngresoDatos.setLayout(new GridLayout(4, 2, 5, 5));
        //Agregamos los elementos al panel interno
        panelInternoIngresoDatos.add(etiquetaNombreHamburguesa);
        panelInternoIngresoDatos.add(campoTextoNombreHamburguesa);
        panelInternoIngresoDatos.add(etiquetaTipoHamburguesa);
        panelInternoIngresoDatos.add(listaDesplTipoHam);
        panelInternoIngresoDatos.add(etiquetaCantIngreExtra);
        panelInternoIngresoDatos.add(campoTextoCantIngExt);
        panelInternoIngresoDatos.add(etiquetaPrecio);
        panelInternoIngresoDatos.add(campoTextoPrecio);
        
        //Panel Central
        this.panelCentral.add(panelInternoIngresoDatos);
         
        //Panel Interno Extra
        panelInternoListaHam = new JPanel();
        panelInternoListaHam.setLayout(new BorderLayout(5, 5));
       
        //Elementos del panel Interno Lista
     
        //Boton Realizar Compra
        Icon compra = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/compra_opt.png"));
        botonRealizarCompra = new JButton(" Realizar Compra ", compra);
        botonRealizarCompra.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonRealizarCompra.setBackground(new Color(169, 223, 191));
        botonRealizarCompra.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega un evento al boton Realizar COMPRA
        botonRealizarCompra.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Procesa evento de realizar Compra de una Hamburguesa
                public void actionPerformed(ActionEvent evento) {
                    realizarCompra();
                }
            }     
        );  
        
        //Boton Actualizar
        Icon actulizar = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/actualizar_opt.jpg"));
        botonActualizar = new JButton(" Actualizar ", actulizar);
        botonActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonActualizar.setBackground(new Color(174, 214, 241));
        botonActualizar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonActualizar.setEnabled(false);
        //Agrega un evento al boton Actualizar
        botonActualizar.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Procesa evento de actualizar una hamburguesa.
                public void actionPerformed(ActionEvent evento) {
                    actualizarPedidoHamburguesa();
                }
            }     
        );
        
        //Panel Norte Extra
        panelNorteDelPanelLista = new JPanel();
        panelNorteDelPanelLista.setLayout(new GridLayout(1, 2, 5, 5));
        panelNorteDelPanelLista.add(botonRealizarCompra);
        panelNorteDelPanelLista.add(botonActualizar);
     
        //Se agrega el panel Norte del panel lista al panel Interno Lista
        panelInternoListaHam.add(panelNorteDelPanelLista, BorderLayout.NORTH);
        
        //Elementos que se agregan al panel centro extra
      
        //JList
        modeloLista = new DefaultListModel<String>();
        listaListHamburguesas = new JList<String>();
        listaListHamburguesas.setModel(modeloLista);
        listaListHamburguesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaListHamburguesas.setBackground(new Color(254, 249, 231));
        listaListHamburguesas.setFont(new Font("Tahoma", Font.PLAIN, 15));
        listaListHamburguesas.addListSelectionListener(
            new ListSelectionListener() {
                //Evento cuando se selecciona un item de la lista.
                public void valueChanged(ListSelectionEvent evento) {
                    habilitarModificarYEliminar();
                }
            }
        );
        
        //Panel Centro Extra
        panelCentroDelPanelLista = new JPanel();
        panelCentroDelPanelLista.setLayout(new BorderLayout(5, 5));
        panelCentroDelPanelLista.setBackground(new Color(254, 249, 231));
        panelCentroDelPanelLista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2), 
            "Lista de Hamburguesas: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
            new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
   
        panelCentroDelPanelLista.add(new JScrollPane(listaListHamburguesas), BorderLayout.CENTER);
        
        //Se agregar el panel Centro del panel lista al panel interno lista      
        panelInternoListaHam.add(panelCentroDelPanelLista);
        
        //Agregamos el panel internoLista al panel central
        panelCentral.add(panelInternoListaHam);
        
        //Agregamos el panel central al esquema principal
        add(panelCentral, BorderLayout.CENTER);
        
        
        /**
         * Panel Este del esquema principal
         */
        
        //Panel Este de la GUICliente
        panelEste = new JPanel();
        panelEste.setLayout(new BorderLayout(5, 5));
        
        //Campo de texto del numero de la mesa
        campoTextoNumMesa = new JTextField();
        campoTextoNumMesa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        campoTextoNumMesa.setBackground(new Color(187, 222, 251));
        campoTextoNumMesa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(55, 71, 79), 2), 
                "Ingrese el numero de la mesa: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        campoTextoNumMesa.setEditable(true);
        
        //Area de texto Informacion
        areaTextoInformacion = new JTextArea(".............................................................");
        areaTextoInformacion.setEditable(false);
        areaTextoInformacion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2), 
            "Información: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
            new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
        areaTextoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        areaTextoInformacion.setBackground(new Color(229, 232, 232));
        
        //Se agrega los componentes al panel del ESTE
        panelEste.add(this.campoTextoNumMesa, BorderLayout.NORTH);
        panelEste.add(new JScrollPane(areaTextoInformacion), BorderLayout.CENTER);
        //Se agrega el panel Este al esquema principal.
        add(panelEste, BorderLayout.EAST);
        
        /**
         * Panel Sur del esquema principal.
         */
        
        //Panel Sur de la GUICliente
        panelSur = new JPanel();
        panelSur.setLayout(new GridLayout(2, 3, 5, 5));
        
        //Boton Modificar Pedido
        Icon modificarPedido = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/modificar_opt.png"));
        botonModificarPedido = new JButton("  Modificar Pedido  ", modificarPedido);
        botonModificarPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonModificarPedido.setBackground(new Color(133, 193, 233));
        botonModificarPedido.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonModificarPedido.setEnabled(false);
        //Agrega una accion al boton Modificar Pedido
        botonModificarPedido.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Agrega el evento de modificar pedido.
                public void actionPerformed(ActionEvent evento) {
                    ModificarPedido();
                }
            }     
        );
        panelSur.add(botonModificarPedido);
        
        //Boton Listar Pedidos
        Icon listar = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/listar_opt.jpg"));
        botonListarPedidos = new JButton("  Listar Pedidos  ", listar);
        botonListarPedidos.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonListarPedidos.setBackground(new Color(215, 189, 226));
        botonListarPedidos.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Listar
        botonListarPedidos.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de listar las hamburguesas de los pedidos.
                public void actionPerformed(ActionEvent evento) {
                    listarPedidos();
                }
            }     
        );
        panelSur.add(botonListarPedidos);
        
        //Boton Eliminar Pedido
        Icon eliminar = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/eliminar_opt.jpg"));
        botonEliminar = new JButton(" Eliminar Pedido ", eliminar);
        botonEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonEliminar.setBackground(new Color(240, 178, 122));
        botonEliminar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        botonEliminar.setEnabled(false);
        //Agrega una accion al boto Eliminar
        botonEliminar.addActionListener(
            new ActionListener() //Clase interna anonima
            {
            	//Evento de eliminar una hamburguesas del pedido
                public void actionPerformed(ActionEvent evento) {
                    EliminarHamburguesaPedido();
                }
            }     
        );
        panelSur.add(botonEliminar);
        
        //Boton Mostrar Datos Empresa
        Icon datosEmpresa = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/burger_opt.png"));
        botonDatosEmpresa = new JButton("    Datos Empresa  ", datosEmpresa);
        botonDatosEmpresa.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonDatosEmpresa.setBackground(new Color(208, 211, 212));
        botonDatosEmpresa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Datos Empresa       
        botonDatosEmpresa.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de mostrar datos y valores al cliente
                public void actionPerformed(ActionEvent evento) {
                    mostrarDatosEmpresaYPrecios();
                }
            }     
        );
        panelSur.add(botonDatosEmpresa);
        
        //Boton Efectuar Pago
        Icon Pagar = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/pagar_opt.png"));
        botonEfectuarPedido = new JButton(" Pagar-Efectuar Pedido", Pagar);
        botonEfectuarPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
        botonEfectuarPedido.setBackground(new Color(115, 198, 182));
        botonEfectuarPedido.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Salir
        botonEfectuarPedido.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de efectua pago.
                public void actionPerformed(ActionEvent evento) {
                   efectuarPagoPedido();
                }
            }     
        );
        panelSur.add(botonEfectuarPedido);
        
        //Boton Salir
        Icon salir = new ImageIcon(getClass().getResource("/co/edu/unicauca/clientehamburguesas/recursos/salir_opt.png"));
        botonSalir = new JButton("Cerrar Programa ", salir);
        botonSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonSalir.setBackground(new Color(241, 148, 138));
        botonSalir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Agrega una accion al boton Salir
        botonSalir.addActionListener(
            new ActionListener() //Clase interna anonima
            {
                //Evento de cerrar la aplicacion.
                public void actionPerformed(ActionEvent evento) {
                    cerrarAplicacion();
                }
            }     
        );
        this.panelSur.add(botonSalir);
        
        this.add(panelSur, BorderLayout.SOUTH); 
        
        this.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    cerrar();
                }
            }
        );
    }//Fin del constructo de la clase GUICliente.
    
    private void cerrar() {
        String[] opciones = {"Cerrar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Cerrar la Aplicacion?", "Cerrar", 0, 0, null, opciones, this);
        if(eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo el cierre");
        }
    }
    
    private void habilitarModificarYEliminar()
    {
        this.botonModificarPedido.setEnabled(true);
        this.botonEliminar.setEnabled(true);
    }
    
    private void deshabilitarModificarYEliminar()
    {
        this.botonModificarPedido.setEnabled(false);
        this.botonEliminar.setEnabled(false);
    }
    
    private void habilitarCampos()
    {
        this.campoTextoNombreHamburguesa.setEditable(true);
        this.campoTextoCantIngExt.setEditable(true);
        this.listaDesplTipoHam.setEnabled(true);
    }
    
    /**
     * Coloca el precio en el campo de texto de precios.
     */
    private void colocarPrecio()
    {
    	ClsValores objValores = objValoresServices.consultarValores();
    	char tipoHamburguesa = obtenerTipoHamburguesa();
    	
    	if(objValores != null)
    	{
    		if(tipoHamburguesa == 'p') {
    			this.campoTextoPrecio.setText("" + objValores.getValorHamburguesaPequenia());
    		}
    		if(tipoHamburguesa == 'm') {
    			this.campoTextoPrecio.setText("" + objValores.getValorHamburguesaMediana());
    		}
    		if(tipoHamburguesa == 'g') {
    			this.campoTextoPrecio.setText("" + objValores.getValorHamburgeusaGrande());
    		}
    	}
    }
    
    /**
     * Determina si el nombre de una hamburguesa ya existe.
     * @param nombreHamburguesa
     * @param pedido
     * @return boolean
     */
    private static boolean existeHamburguesa(String nombreHamburguesa, ClsPedido pedido)
    {
        boolean existeHamburguesa = false;
        for(int i=0; i < pedido.getListaHamburguesas().size(); i++)
        {
            if(nombreHamburguesa.equals(pedido.getListaHamburguesas().get(i).getNombre())){
                existeHamburguesa = true;
                break;
            }
        }
        return existeHamburguesa;
    }
    
    /**
     * Valida el ingreso del nombre de la hamburguesa en el campo de texto.
     */
    private void validarIdHamburguesa() {
        String nombreHamburguesa = this.campoTextoNombreHamburguesa.getText();
        boolean existeNombre = existeHamburguesa(nombreHamburguesa, objPedido);
        if(existeNombre) {
            JOptionPane.showMessageDialog(this, "El nombre de esa hamburguesa ya se encuentra registrado.", "Error Nombre Hamburguesa", JOptionPane.INFORMATION_MESSAGE);
            this.campoTextoNombreHamburguesa.setText("");
            this.campoTextoNombreHamburguesa.requestFocus();
        }
    }
    
    /**
     * Valida el ingreso de los ingredientes extra para la hamburguesa.
     */
    private void comprobarIngredientesExtra() {
        String cadena;
        int cantidadIngredientes = 0;
        if(campoTextoCantIngExt.getText().length() != 0) {
            cadena = this.campoTextoCantIngExt.getText();
            try 
            {
                cantidadIngredientes = Integer.parseInt(cadena);
                if(cantidadIngredientes < 0)
                {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un numero mayor que cero", "Error ingredientes extra.", JOptionPane.INFORMATION_MESSAGE);
                    campoTextoCantIngExt.setText("");
                }
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Entrada no Valida, debe ingresar un numero entero..." + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Debes ingresar un numero.", "Error Cantidad Ingredientes Extra.", JOptionPane.INFORMATION_MESSAGE);
                campoTextoCantIngExt.setText("");
            }
        }
    }
    
    /**
     * Obtiene el tipo de la hamburguesa
     * @return char
     */
    private char obtenerTipoHamburguesa() 
    {
        String seleccionListaDesplTipo = (String) this.listaDesplTipoHam.getSelectedItem();
        char tipoHamburguesa = 's';
        
        if(seleccionListaDesplTipo.equals("Pequeña")) 
            tipoHamburguesa = 'p';
        
        if(seleccionListaDesplTipo.equals("Mediana"))
            tipoHamburguesa = 'm';
        
        if(seleccionListaDesplTipo.equals("Grande"))
            tipoHamburguesa = 'g';
        
        return tipoHamburguesa;
    }
    
    /**
     * Limpia los campos, para volver a ingresar sus valores
     */
    private void limpiarCampos()
    {
        this.campoTextoNombreHamburguesa.setText("");
        this.campoTextoCantIngExt.setText("");
    }
    
    /**
     * Inhabilita los campos, para no ingresar informacion
     */
    private void inhabilitarCampos()
    {
        this.campoTextoNombreHamburguesa.setEditable(false);
        this.campoTextoCantIngExt.setEditable(false);
        this.listaDesplTipoHam.setEnabled(false);
    }
   
    /**
     * Realiza la compra de una hamburguesa y la agrega al pedido.
     */
    private void realizarCompra() 
    {
        boolean bandera = false;
        
        if(this.numeroHamburguesas >= 0 && this.numeroHamburguesas < 12) {
            
            if(this.campoTextoNombreHamburguesa.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para la hamburguesa.", "Error Nombre Hamburguesa.", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoNombreHamburguesa.requestFocus();
            }

            if(this.campoTextoCantIngExt.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad de ingredientes extra.", "Error Cantidad ingredientes extra", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoCantIngExt.requestFocus();
            }

            String nombreHamburguesa = this.campoTextoNombreHamburguesa.getText();
            if(existeHamburguesa(nombreHamburguesa, this.objPedido)) {
            	this.campoTextoNombreHamburguesa.setText("");
            	this.campoTextoNombreHamburguesa.requestFocus();
            }
            else
            {
                char tipoHamburguesa = obtenerTipoHamburguesa();
                int ingredientesExtra = 0;
                float precio = Float.parseFloat(this.campoTextoPrecio.getText());

                try 
                {
                    ingredientesExtra = Integer.parseInt(this.campoTextoCantIngExt.getText());
                    if(ingredientesExtra < 0) {
                        this.campoTextoCantIngExt.setText("");
                        this.campoTextoCantIngExt.requestFocus();
                    }
                    else {
                        bandera = true;
                    }   
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("Entrada no Valida. " + ex.getMessage());
                    this.campoTextoCantIngExt.setText("");
                    this.campoTextoCantIngExt.requestFocus();
                }
                
                if(bandera) {
                	ClsHamburguesa objHamburguesa = new ClsHamburguesa(1, 1, nombreHamburguesa, tipoHamburguesa, ingredientesExtra, precio);
                	this.objPedido.agregarHamburguesa(objHamburguesa);
                    this.numeroHamburguesas += 1;
                    limpiarCampos();
                    this.modeloLista.addElement(nombreHamburguesa);
                }
            } 
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No puedes pedir mas Hamburguesas.", "No puedes comprar mas", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            inhabilitarCampos();
        }
    }
    
    /**
     * Actualiza la informacion de una hamburguesa del pedido
     */
    private void actualizarPedidoHamburguesa() {
        boolean bandera = false;
        this.modeloLista.remove(this.listaListHamburguesas.getSelectedIndex());
        
        if(this.campoTextoNombreHamburguesa.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un Nombre para la Hamburguesa.", "Error nombre Hamburguesa", JOptionPane.INFORMATION_MESSAGE);
            this.campoTextoNombreHamburguesa.requestFocus();
        }

        if(this.campoTextoCantIngExt.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad de ingredientes extra.", "Error Cantidad ingredientes extra", JOptionPane.INFORMATION_MESSAGE);
            this.campoTextoCantIngExt.requestFocus();
        }

        String nombreHamburguesa = this.campoTextoNombreHamburguesa.getText();
        if(existeHamburguesa(nombreHamburguesa, this.objPedido)) {
        	this.campoTextoNombreHamburguesa.setText("");
        	this.campoTextoNombreHamburguesa.requestFocus();
        }
        else
        {
            char tipoHamburguesa = obtenerTipoHamburguesa();
            int ingredientesExtra = 0;
            float precio = Float.parseFloat(this.campoTextoPrecio.getText());

            try 
            {
                ingredientesExtra = Integer.parseInt(this.campoTextoCantIngExt.getText());
                if(ingredientesExtra < 0) {
                    this.campoTextoCantIngExt.setText("");
                    this.campoTextoCantIngExt.requestFocus();
                }
                else {
                    bandera = true;
                }   
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Entrada no Valida. " + ex.getMessage());
                this.campoTextoCantIngExt.setText("");
                this.campoTextoCantIngExt.requestFocus();
            }
                
            if(bandera) {
                ClsHamburguesa objHamburguesa = new ClsHamburguesa(1, 1, nombreHamburguesa, tipoHamburguesa, ingredientesExtra, precio);
                this.objPedido.agregarHamburguesa(objHamburguesa);
                limpiarCampos();
                this.modeloLista.addElement(nombreHamburguesa);
                this.botonRealizarCompra.setEnabled(true);
                this.botonActualizar.setEnabled(false);  
            }
        }  
    }
    
    /**
     * Permite modificar una hamburguesa del pedido
     */
    private void ModificarPedido(){
        
        String nombreHamburguesa = this.modeloLista.getElementAt(this.listaListHamburguesas.getSelectedIndex());
        ClsHamburguesa hamburguesa = null;
        int pos = 0;
        for(ClsHamburguesa burger: this.objPedido.getListaHamburguesas()) {
            if(nombreHamburguesa.equals(burger.getNombre())) {
                hamburguesa = burger;
                this.objPedido.eliminarHamburguesa(pos);
                break;
            }
            pos++;
        }
        
        if(hamburguesa != null) {
            this.campoTextoNombreHamburguesa.setText(hamburguesa.getNombre());
            this.campoTextoCantIngExt.setText(String.valueOf(hamburguesa.getCantidadIngredientesExtra()));
            this.campoTextoPrecio.setText(String.valueOf(hamburguesa.getCosto()));
            String item = "";
            
            if(hamburguesa.getTipo() == 'p') 
                item = "Pequeña";
            if(hamburguesa.getTipo() == 'm')
                item = "Medianda";
            if(hamburguesa.getTipo() == 'g')
                item = "Grande";
            
            listaDesplTipoHam.setSelectedItem(item);
        }
        
        habilitarCampos();
        deshabilitarModificarYEliminar();
        this.botonRealizarCompra.setEnabled(false);
        this.botonActualizar.setEnabled(true);
    }

    /**
     * Obtengo el tipo de la hamburguesa
     * @param tipo
     * @return String
     */
    private String obtenerTipoHamburguesa(char tipo) {
        String tipoHam = "";
        
        if(tipo == 'p') 
            tipoHam = "Pequeña";
        
        if(tipo == 'm')
            tipoHam = "Mediana";
        
        if(tipo == 'g')
            tipoHam = "Grande";
        
        return tipoHam;
    }
    
    /**
     * Obtiene le precio con IVA incluido del pedido de hamburguesas
     * @return
     */
    private float obtenerPrecioConIVA() {
    	
    	ClsConteoHamburguesas objConteoHamburguesas = new ClsConteoHamburguesas(this.objPedido.getListaHamburguesas());
    	ClsValores objValores = objValoresServices.consultarValores();
    	ClsComputoIVA objComputoIVA = new ClsComputoIVA(objConteoHamburguesas, objValores);
    	
    	return objComputoIVA.getCostoConIVA();
    }
    
    /**
     * Permite listar las hamburguesas del pedido
     */
    private void listarPedidos() {
        areaTextoInformacion.setText("");
        int num = 0;
        if(objPedido.getListaHamburguesas().isEmpty()) {
            areaTextoInformacion.append("\nNo hay pedidos registrados...");
        }
        else{
            areaTextoInformacion.append("\n=== Listado de Hamburguesas Pedidos ===\n");
            for(ClsHamburguesa burger: objPedido.getListaHamburguesas()){
                areaTextoInformacion.append("\nHamburguesa No: " + (num+1));
                areaTextoInformacion.append("\nNombre: " + burger.getNombre());
                areaTextoInformacion.append("\nTipo: " + obtenerTipoHamburguesa(burger.getTipo()));
                areaTextoInformacion.append("\nCantidad de Ingredientes extra: " + burger.getCantidadIngredientesExtra());
                areaTextoInformacion.append("\nPrecio: " + burger.getCosto());
                areaTextoInformacion.append("\n------------------------------");
                num++;
            }
            
            areaTextoInformacion.append("\n\nEl precio con IVA del pedido es: " + obtenerPrecioConIVA());
        }
    }
    
    /**
     * Elimina una hamburguesa del pedido
     */
    private void EliminarHamburguesaPedido() {
        
        String[] opciones = {"Eliminar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "¿Desea Eliminar la Hamburguesa?", "Eliminar", 0, 0, null, opciones, this);
        if(eleccion == JOptionPane.YES_OPTION) {
           
            String nombreHamburguesa = this.modeloLista.getElementAt(listaListHamburguesas.getSelectedIndex());
        
            for(int i=0; i<this.objPedido.getListaHamburguesas().size(); i++) {
                if(nombreHamburguesa.equals(objPedido.getListaHamburguesas().get(i).getNombre())){
                    this.objPedido.eliminarHamburguesa(i);
                    break;
                }
            }
        
            this.modeloLista.remove(listaListHamburguesas.getSelectedIndex());
            this.numeroHamburguesas--;
            deshabilitarModificarYEliminar();
            
        }
        else if(eleccion == JOptionPane.NO_OPTION) {
            System.out.println("Se cancelo la eliminacion.");
        }
        habilitarCampos();
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
     * Limpia la lista de las hamburguesas del pedido
     */
    private void vaciarListaDeLaVista()
    {
        modeloLista.clear();
        deshabilitarModificarYEliminar();
    }  
    
    /**
     * Permite efectuar el pago del pedido y mostrar la factura correspondiente.
     */
    private void efectuarPagoPedido() 
    {
        if(!objPedido.getListaHamburguesas().isEmpty())
        {
        	boolean esValidoNumeroMesa = false;
        	if(this.campoTextoNumMesa.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el numero de la mesa.", "Error numero mesa", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoNumMesa.requestFocus();
            }
        	
        	int numeroMesa = 0;
        	try 
            {
                numeroMesa = Integer.parseInt(this.campoTextoNumMesa.getText());
                esValidoNumeroMesa = true;
            }
            catch(NumberFormatException ex)
            {
            	JOptionPane.showMessageDialog(this, "El dato para el numero de la mesa es incorrecto.", "Error numero mesa", JOptionPane.INFORMATION_MESSAGE);
                this.campoTextoNumMesa.setText("");
                this.campoTextoNumMesa.requestFocus();
            }
        	
        	if(esValidoNumeroMesa) {
        		this.objPedido.setNumeroMesa(numeroMesa);
            	ClsFactura objFacturaCliente = objPedidoServices.registrarPedido(this.objPedido);
            	this.objPedido.vaciarListaHamburguesas();
            	
            	if(objFacturaCliente != null)
            	{
            		this.areaTextoInformacion.setText("");
            		this.areaTextoInformacion.append("\nFactura No: " + objFacturaCliente.getIdFactura());
            		this.areaTextoInformacion.append("\n\nHamburguesas de tipo pequeño: " + objFacturaCliente.getNumHamburguesasPequenias());
            		this.areaTextoInformacion.append("\nHamburguesas de tipo mediano: " + objFacturaCliente.getNumHamburguesasMedianas());
            		this.areaTextoInformacion.append("\nHamburguesas de tipo grande: " + objFacturaCliente.getNumHamburguesasGrandes());
            		this.areaTextoInformacion.append("\n\nCosto sin IVA del pedido: $" + objFacturaCliente.getCostoSinIVA());
            		this.areaTextoInformacion.append("\nIVA del pedido: " + objFacturaCliente.getIVA());
            		this.areaTextoInformacion.append("\nCosto con IVA del pedido: " + objFacturaCliente.getCostoConIVA());
            		this.areaTextoInformacion.append("\n------------------------");
            	}
            	vaciarListaDeLaVista();
            	this.numeroHamburguesas = 0;
            	habilitarCampos();
        	}
        }
        else{
            areaTextoInformacion.setText("");
            areaTextoInformacion.append("\nNo hay hamburguesas registradas.");
        }     
    }
     
    /**
     * Permite cerrar la aplicacion
     */
    private void cerrarAplicacion() {
        System.exit(0);
    }
}
