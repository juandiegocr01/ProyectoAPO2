package interfaz;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;


import hilos.HiloExplosion;
import hilos.HiloMovimientoBala;
import hilos.HiloMovimientoBot;
import hilos.HiloMovimientoTanque;
import mundo.Bala;
import mundo.Bot;
import mundo.Casilla;
import mundo.Juego;
import mundo.Jugador;
import mundo.NoExisteBotException;
import mundo.PuntajeDosJugadores;
import mundo.PuntajeUnJugador;
import mundo.Tablero;
import mundo.Tanque;

@SuppressWarnings("serial")
public class InterfazPrincipal extends JFrame{
	/**
	 * Atributo de tipo constante que indica si el juego empieza como un jugador
	 */
	public static final String UN_JUGADOR="UN_JUGADOR";
	/**
	 * Atributo de tipo constante que indica si el juego empieza como dos jugador
	 */
	public static final String DOS_JUGADORES="DOS_JUGADORES";
	/**
	 * Atributo que relaciona el mundo con la interfaz
	 */

	private Juego mundo;
	
	/**
	 * Atributo que relaciona el panel tablero con la interfaz
	 */
	
	private PanelTablero tablero;
	
	/**
	 * Atributo que relaciona el panel menu con la interfaz
	 */
	
	private PanelMenu panelMenu;
	
	/**
	 * Atributo que relaciona el panel puntaje con la interfaz
	 */
	
	private PanelPuntajes panelPuntaje;
	
	/**
	 * Atributo que relaciona el panel instruccion con la interfaz
	 */
	
	private PanelInstruccion panelInstruccion;
	
	/**
	 * Atributo que representa un nuevo panel
	 */
	
	private JPanel panelNuevo;
	
	/**
	 * Atributo que representa un panel auxiliar
	 */
	
	private JPanel aux;
	
	/**
	 * Atributo que representa la barra de salida del juego
	 */
	
	private MenuSalida menuSalida;
	/**
	 * Atributo tipo boolean que indica si ya se determino el puntaje del jugador
	 */
	private boolean determinoPuntaje;
	/**
	 * Atributo de tipo int que indica el puntajeAnterior a un juego en caso de seguir jugando
	 */
	private int puntajeAnterior;
	/**
	 * Atributo de tipo String que indica que tipo de juego escogio el jugador
	 */
	private String numJugadores;
	
	/**
	 * Constructor de la interfaz
	 */
	
	public InterfazPrincipal(){
		
		setTitle("Tanks");
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		mundo  =  new Juego();
		try {
			mundo.cargarPuntajes1();
			mundo.cargarPuntajes2();
			panelMenu = new PanelMenu(this, "./imagenes/TANKS (3).png");
			panelMenu.setBackground(Color.BLACK);
			
			panelPuntaje = new PanelPuntajes(this, "./imagenes/PUNTAJES.png");
			panelPuntaje.setBackground(Color.BLACK);
			
			
			menuSalida = new MenuSalida(this);
			
			panelInstruccion = new PanelInstruccion(this, "./imagenes/INSTRUCCIONES.png");
			panelInstruccion.setBackground(Color.BLACK);
			
			panelNuevo = new JPanel();
			panelNuevo.add(panelMenu);
			panelNuevo.setBackground(Color.BLACK);

			
			setLayout(new BorderLayout());
			add(panelNuevo, BorderLayout.CENTER);
			
			puntajeAnterior=0;
			numJugadores="";
			
			
			JMenuBar miMenuBar = new JMenuBar();
			miMenuBar.add(menuSalida);
			setJMenuBar(miMenuBar);
			this.setBackground(Color.BLACK);
			pack();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * Metodo que permite ir al panel tablero
	 */
	
	public void cambiarAJuego(){
		JFileChooser fc = new JFileChooser("./niveles/Un Jugador");
		fc.setDialogTitle("Tanks");
	 	File archivoTorneo =null;
			int resultado = fc.showOpenDialog(this);
			if(resultado == JFileChooser.APPROVE_OPTION){
			 archivoTorneo = fc.getSelectedFile();
				if(archivoTorneo!=null){
					try{
						mundo.cargarJuego(archivoTorneo);
					}catch(Exception ea){
//						ea.printStackTrace();
						JOptionPane.showMessageDialog(this, ea.getMessage());
					}
					tablero = new PanelTablero(this);
					tablero.setRequestFocusEnabled(false);
					tablero.setFocusTraversalKeysEnabled(true);
					tablero.setFocusable(true);
					panelNuevo.remove(0);
					aux = panelMenu;
					panelNuevo.add(tablero);
					determinoPuntaje= false;
					numJugadores=UN_JUGADOR;
				}
		}
			if(mundo.getElTablero()!=null ){
			HiloMovimientoTanque hilo= new HiloMovimientoTanque(mundo.getElTablero().darTanque(),this);
			hilo.start();
			if( mundo.getElTablero().darLaTanque()!= null){
			HiloMovimientoTanque hilo2= new HiloMovimientoTanque(mundo.getElTablero().darLaTanque(),this);
			hilo2.start();
			}
			for (int i = 0; i < mundo.getElTablero().getBots().size(); i++) {
				HiloMovimientoTanque hiloNuevo= new HiloMovimientoTanque(mundo.getElTablero().getBots().get(i),this);
				hiloNuevo.start();
				HiloMovimientoBot hiloNuevo2= new HiloMovimientoBot(mundo.getElTablero(),mundo.getElTablero().getBots().get(i),this);
				hiloNuevo2.start();
		
			}
			}
			pack();
	}
	
	/**
	 * Metodo que permite cambiar al panel multijugador
	 */
	
	public void cambiarAMultijugador(){
		JFileChooser fc = new JFileChooser("./niveles/Dos Jugadores");
		fc.setDialogTitle("Tanks");
	 	File archivoTorneo =null;
			int resultado = fc.showOpenDialog(this);
			if(resultado == JFileChooser.APPROVE_OPTION){
			 archivoTorneo = fc.getSelectedFile();
				if(archivoTorneo!=null){
					try{
						mundo.cargarJuego(archivoTorneo);
					}catch(Exception ea){
						ea.printStackTrace();
						JOptionPane.showMessageDialog(this, ea.getMessage());
					}
					tablero = new PanelTablero(this);
					tablero.setRequestFocusEnabled(false);
					tablero.setFocusTraversalKeysEnabled(true);
					tablero.setFocusable(true);
					panelNuevo.remove(0);
					aux = panelMenu;
					panelNuevo.add(tablero);
					determinoPuntaje= false;
					numJugadores=DOS_JUGADORES;
				}
		}
			if(mundo.getElTablero()!=null){
			HiloMovimientoTanque hilo= new HiloMovimientoTanque(mundo.getElTablero().darTanque(),this);
			hilo.start();
			if( mundo.getElTablero().darLaTanque()!= null){
			HiloMovimientoTanque hilo2= new HiloMovimientoTanque(mundo.getElTablero().darLaTanque(),this);
			hilo2.start();
			}
			for (int i = 0; i < mundo.getElTablero().getBots().size(); i++) {
				HiloMovimientoTanque hiloNuevo= new HiloMovimientoTanque(mundo.getElTablero().getBots().get(i),this);
				hiloNuevo.start();
				HiloMovimientoBot hiloNuevo2= new HiloMovimientoBot(mundo.getElTablero(),mundo.getElTablero().getBots().get(i),this);
				hiloNuevo2.start();
		
			}
			}
			pack();
	}
	
	/**
	 * Metodo que permite cambiar al panel puntaje
	 */
	
	public void cambiarAPuntaje(){
		panelNuevo.remove(0);
		aux = panelMenu;
		panelNuevo.add(panelPuntaje);
		pack();
	}
	
	/**
	 * Metodo que permite cambiar al panel instrucciones
	 */
	
	public void cambiarAInstrucciones(){
		panelNuevo.remove(0);
		aux = panelMenu;
		panelNuevo.add(panelInstruccion);
		pack();
	}
	
	/**
	 * Metodo que permite salir del juego
	 */
	
	public void salir(){
		int opcion;
		if( aux!= null){
		opcion = JOptionPane.showConfirmDialog(this, "¿Desea guardar el juego actual antes de salir?");
	    if(opcion==JOptionPane.YES_OPTION){
				int puntaje = mundo.getElTablero().getPuntajeGeneral().getPuntaje();
				puntaje+=puntajeAnterior;
				puntajeAnterior=0;
				if( puntaje<0) puntaje=0;
				if(numJugadores.equals(UN_JUGADOR)){
					String nombre=JOptionPane.showInputDialog("Su puntaje fue " + puntaje + " por favor digite su nombre: ");
				    mundo.agregarPuntaje(new PuntajeUnJugador(puntaje, nombre));
				    try {
				    	mundo.guardarPuntaje1();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, e.getMessage());
					}
				}
				else if(numJugadores.equals(DOS_JUGADORES)){
					String nombres=JOptionPane.showInputDialog("Su puntaje fue " + puntaje + " por favor digite su nombre seguido del se su compañero :");
				    mundo.insercion(new PuntajeDosJugadores(puntaje, nombres));
				   
				    try {
				    	mundo.guardarPuntaje2();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, e.getMessage());
					}
				}
				mundo.salirJuego();
	    }
	    else{
	    	 volverAnterior();
	    }
		}
		else System.exit(0);
	}
	
	/**
	 * Metodo que permite ir al menu principal del juego
	 */
	
	public void volverAnterior(){
		if( aux==null){
			System.exit(0);
		}
		else{
			panelNuevo.remove(0);
			panelNuevo.add(aux);
			aux=null;
			repaint();
			pack();
		}
	}
	
	/**
	 * Metodo que actualiza el tablero
	 */
	
	public void actualizarInterfaz() {
		if(darEstadoEnJuego()== true){
			tablero.repaint();
			tablero.revalidate();
		}
		else if(mundo.getGanar() == true && determinoPuntaje==false){
			determinoPuntaje= true;
			int puntaje = mundo.getElTablero().getPuntajeGeneral().getPuntaje();
			puntaje+=puntajeAnterior;
			if( puntaje<0) puntaje=0;
			int opcion = JOptionPane.showConfirmDialog(this, "¿Desea seguir jugando acumulando mas puntaje?");
			if(opcion==JOptionPane.YES_OPTION){
				puntajeAnterior= puntaje;
				if( numJugadores.equals(UN_JUGADOR))cambiarAJuego();
				else if( numJugadores.equals(DOS_JUGADORES))cambiarAMultijugador();
			}
			else{
				puntajeAnterior=0;
				if(numJugadores.equals(UN_JUGADOR)){
					String nombre=JOptionPane.showInputDialog("Su puntaje fue " + puntaje + " por favor digite su nombre: ");
					 if( nombre!=null && !nombre.equals("")){
						 mundo.agregarPuntaje(new PuntajeUnJugador(puntaje, nombre));
						 try {
							 mundo.guardarPuntaje1();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this, e.getMessage());
						}
					 }
					  else JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
				}
				else if(numJugadores.equals(DOS_JUGADORES)){
					String nombres=JOptionPane.showInputDialog("Su puntaje fue " + puntaje + " por favor digite su nombre seguido del se su compañero :");
				    mundo.insercion(new PuntajeDosJugadores(puntaje, nombres));
				    try {
				    	mundo.guardarPuntaje2();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, e.getMessage());
					}
				}
				volverAnterior();
			}
		}
		else if(mundo.getPerder() == true && determinoPuntaje==false){
			determinoPuntaje= true;
			int puntaje = mundo.getElTablero().getPuntajeGeneral().getPuntaje();
			puntaje+=puntajeAnterior;
			puntajeAnterior=0;
			if( puntaje<0) puntaje=0;
			if(numJugadores.equals(UN_JUGADOR)){
				String nombre=JOptionPane.showInputDialog("Su puntaje fue " + puntaje + " por favor digite su nombre: ");
			    if( nombre!=null && !nombre.equals("")){
			    	mundo.agregarPuntaje(new PuntajeUnJugador(puntaje, nombre));
			    	try {
			    		mundo.guardarPuntaje1();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, e.getMessage());
					}
			    }
			    else JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
			}
			else if(numJugadores.equals(DOS_JUGADORES)){
				String nombres=JOptionPane.showInputDialog("Su puntaje fue " + puntaje + " por favor digite su nombre seguido del se su compañero :");
			    mundo.insercion(new PuntajeDosJugadores(puntaje, nombres));
			    try {
			    	mundo.guardarPuntaje2();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
			volverAnterior();
		}
	}
	
	public static void main(String[] args) {
		InterfazPrincipal p =  new InterfazPrincipal();
		p.setVisible(true);
	}
	
	/**
	 * Metodo que da el tanque del tablero
	 * @return tanque del tablero
	 */
	
  public Tanque darTanque(){
	  return mundo.getElTablero().darTanque();
  }
  
  /**
   * Metodo que da el tanque del tablero
   * @return tanque del tablero
   */
  
  public Tanque darLaTanque(){
	  return mundo.getElTablero().darLaTanque();
  }
  
  /**
   * Metodo que asigna el tablero
   * @return el tablero del juego
   */

  public Tablero darTablero(){
	  return mundo.getElTablero();
  }
  
  /**
   * Metodo que contiene a todos los bots del juego
   * @return bots del juego
   */
  
  public ArrayList<Bot> darBots(){
	  return mundo.getElTablero().getBots();
  }

  /**
   * Metodo que genera la bala de los jugadores
   * @param id
   * @param bala
   */
  
  	public void generarBala(String id, String bala) {
	if( id.equals(Jugador.ID_JUGADOR1)){
		if( bala.equals(Bala.BALA_NORMAL)){
	darTanque().generarBala(Bala.BALA_NORMAL);
	HiloMovimientoBala NuevoHilo= new HiloMovimientoBala(mundo.getElTablero(),darTanque().getRecienCreada(), this);
	NuevoHilo.start();
		}
		else {
			darTanque().generarBala(Bala.BALA_ESPECIAL);
			HiloMovimientoBala NuevoHilo= new HiloMovimientoBala(mundo.getElTablero(),darTanque().getBalaEspecial(), this);
			NuevoHilo.start();
		}
	}
	else if( id.equals(Jugador.ID_JUGADOR2)){
	darLaTanque().generarBala(Bala.BALA_NORMAL);
	HiloMovimientoBala NuevoHilo= new HiloMovimientoBala(mundo.getElTablero(),darLaTanque().getRecienCreada(), this);
	NuevoHilo.start();
	}
}

	/**
	 * Metodo que genera la bala del bot
	 * @param botBala
	 * @throws NoExisteBotException Lanza la excpecion si no encuentra el bot.
	 */

	public void generarBalaBot(Bot botBala) throws NoExisteBotException {
	int i =mundo.getElTablero().BuscarBot(botBala);
	mundo.getElTablero().getBots().get(i).generarBala(Bala.BALA_NORMAL);
	HiloMovimientoBala NuevoHilo= new HiloMovimientoBala(mundo.getElTablero(),mundo.getElTablero().getBots().get(i).getRecienCreada(), this);
	NuevoHilo.start();
}

	/**
	 * Metodo que establece el ancho del tablero
	 * @return limite a lo ancho del tablero
	 */

	public int anchoPanelTablero(){
	return tablero.darLimitesAncho();
}
	
	/**
	 * Metodo que establece el alto del tablero
	 * @return limite a lo alto del tablero
	 */
	
	public int altoPanelTablero(){
	return tablero.darLimitesAlto();
}

	/**
	 * Metodo que genera la explosion de la bala
	 * @param bala 
	 */

	public void generarExplosion(Bala bala){
	int i = mundo.getElTablero().BuscarBalaEnJugador1(bala);
	int j=-1;
	int k=mundo.getElTablero().BuscarBalaEnJugador2(bala);
	int BotBala=mundo.getElTablero().indiceBalaEnBot(bala);
	if( BotBala!=-1){
		j=mundo.getElTablero().buscarBalaEnBot(BotBala, bala);
	}
	if( i!=-1 ){
	this.darTanque().getBalaNormal().get(i).generarExplosion();
	HiloExplosion NuevoHilo= new HiloExplosion(darTanque().getBalaNormal().get(i).getExplosion(), this);
	NuevoHilo.start();
	}
	else if(j!=-1){
		mundo.getElTablero().getBots().get(BotBala).getBalaNormal().get(j).generarExplosion();
		HiloExplosion NuevoHilo= new HiloExplosion(mundo.getElTablero().getBots().get(BotBala).getBalaNormal().get(j).getExplosion(), this);
		NuevoHilo.start();
	}
	else if(k != -1){
		this.darLaTanque().getBalaNormal().get(k).generarExplosion();
		HiloExplosion NuevoHilo= new HiloExplosion(darLaTanque().getBalaNormal().get(k).getExplosion(), this);
		NuevoHilo.start();
	}
}

	/**
	 * Metodo que cambia la imagen de las casillas
	 * @param n Casilla a dar pasado por parametro
	 * @param i posicion a dar la casilla
	 * @param j posicion a dar la casilla
	 */
	
	public void cambiarImagen(Casilla n, int i, int j){
	ImageIcon nuevo = new ImageIcon(n.getRuta());
	tablero.getImagenes()[i][j] = nuevo;
}

	/**
	 * Metodo que reinicia el tanque del jugador
	 * @param id del jugador pasado por parametro
	 */

	public void reiniciarElTanque(String id) {
	mundo.getElTablero().reinciarTanque(id);
}

	/**
	 * Metodo que reinicia el bot
	 * @param bot indica el bot a reiniciar pasado por parametro
	 */

	public void reinciarBot(int bot) {
	mundo.getElTablero().reinciarBot(bot);
}

	/**
	 * Metodo que genera la explosion del jugador
	 * @param id del jugador pasado por parametro
	 */

	public void generarExplosionJugador(String id) {
	if( id.equals(Jugador.ID_JUGADOR1)){
		HiloExplosion nuevoHilo2= new HiloExplosion(mundo.getElTablero().darTanque().getExplosion(), this);
		nuevoHilo2.start();
	}
	else if(id.equals(Jugador.ID_JUGADOR2)){
		HiloExplosion nuevoHilo2= new HiloExplosion(mundo.getElTablero().darLaTanque().getExplosion(), this);
		nuevoHilo2.start();
	}
}

	/**
	 * Metodo que genera la explosion de el bot
	 * @param i
	 */
	
	public void generarExplosionBot(int i) {
	HiloExplosion nuevoHilo2= new HiloExplosion(mundo.getElTablero().getBots().get(i).getExplosion(), this);
	nuevoHilo2.start();
    }
	public boolean darEstadoEnJuego(){
		mundo.juegoTerminadoGanado();
		mundo.juegoTerminadoPerdido();
		return mundo.getEnJuego();
	}

	public void mostrarPuntajesUnJugador() {
		String mensaje=mundo.generarReporteUnJugador();
		panelPuntaje.mostrarPuntaje(mensaje);
	}

	public void mostrarPuntajesDosJugadores() {
		String mensaje=mundo.generarReporteDosJugadores();
		panelPuntaje.mostrarPuntaje(mensaje);
	}

	public void buscarPuntajeUnJugador() {
		String nombre=JOptionPane.showInputDialog("Favor igresar el nombre a buscar en puntaje un Jugador :");
		if( nombre!=null && !nombre.equals("")){
	    	try {
				String mensaje=mundo.buscarPuntajeUnJugador(nombre);
				this.panelPuntaje.mostrarPuntaje(mensaje);
	    	} catch (Exception e) {
			  JOptionPane.showConfirmDialog(this, "No se encuentra el jugador buscado");
			}
	    }
	    else JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
	}

	public void buscarPuntajeDosJugadores() {
		String nombre=JOptionPane.showInputDialog("Favor igresar el nombre a buscar en puntaje de los jugadores :");
	    if( nombre!=null && !nombre.equals("")){
	    	try {
				String mensaje=mundo.buscarPuntajeDosJugadores(nombre);
				this.panelPuntaje.mostrarPuntaje(mensaje);
	    	} catch (Exception e) {
			  JOptionPane.showConfirmDialog(this, "No se encuentra los jugadores buscado");
			}
	    }
	    else JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
	}

}
