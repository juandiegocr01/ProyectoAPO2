package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * La clase juego modela todo un modelo de juego el cual consiste en la destruccion de tanques
 * para la mayor obtencion de puntaje asi sea de un jugador o multijugador
 * 
 * @author Manuel Alejandro Quintero <manuel.quintero@correo.icesi.edu.co>
 * @author Juan Esteban Gallo Plaza <juan.gallo1@correo.icesi.edu.co>
 * @author Juan Diego Caicedo Rojas <juan.caicedo6@correo.icesi.edu.co>
 */
public class Juego {
	
	public static final String RUTA_PUNTAJE1 = "./data/guardar/puntajeTanks1";
	public static final String RUTA_PUNTAJE2 = "./data/guardar/puntajeTanks2";
	
   /**
    * Atributo que representa el tablero del juego.   
    */
	private Tablero elTablero;
	/**
	 * Atributo booleano que determina si se ha ganado el juego.
	 */
	private boolean ganar;
	/**
	 * Aributo booleano que determina si se ha perdido el juego.
	 */
	private boolean perder;
	/**
	 * Atributo booleano que determina si el juego esta activo.
	 */
	private boolean enJuego;
	
	/**
	 * Atributo que representa la raiz del arbol binario de puntajes de dos jugadores.
	 */
	private PuntajeDosJugadores raizDosJugadores;
	/**
	 * Atributo que representa el primer puntaje de la lista doblemente enlazada de los puntajes de un jugador.
	 */
	private PuntajeUnJugador primero;
	
	
	/**
	 * Constructor del juego.
	 */
	public Juego(){
		
	}
	/**
	 * Metodo que retorna el tablero del juego.
	 * @return el tablero del juego.
	 */
	public Tablero getElTablero() {
		return elTablero;
	} 
	
	/**
	 * Metodo que cambia el tablero del juego.
	 * @param elTablero el nuevo tablero con el que se va a actualizar.
	 */

	public void setElTablero(Tablero elTablero) {
		this.elTablero = elTablero;
	}

	/**
	 * Metodo que retorna el booleano de ganar. True si se gano la partida.
	 * @return booleano ganar.
	 */
	public boolean getGanar() {
		return ganar;
	}
	/**
	 * Metodo que cambia el estado del booleano de ganar.
	 * @param nuevo estado booleano con el que se va actualizar el atributo ganar.
	 */

	public void setGanar(boolean ganar) {
		this.ganar = ganar;
	}

	/**
	 * Metodo que retorna el booleano de perder. True si se perdio la partida.
	 * @return booleano perder.
	 */
	public boolean getPerder() {
		return perder;
	}
	/**
	 * Metodo que cambia el estado del booleano de perder.
	 * @param nuevo estado booleano con el que se va actualizar el atributo perder.
	 */

	public void setPerder(boolean perder) {
		this.perder = perder;
	}

	/**
	 * Metodo que retorna el booleano de enJuego. True si se esta jugando la partida.
	 * @return booleano enJuego.
	 */
	public boolean getEnJuego() {
		return enJuego;
	}
    
	/**
	 * Metodo que cambia el estado del booleano de enJuego.
	 * @param nuevo estado booleano con el que se va actualizar el atributo enJuego.
	 */
	public void setEnJuego(boolean enJuego) {
		this.enJuego = enJuego;
	}
	
   /**
    * Metodo que carga un archivo para inicializar el juego.
    * @param f Archivo con el nivel a cargar.
    * @throws FileNotFoundException Excepcion que se lanza si no se encuentra el archivo.
    * @throws IOException Excepcion que se lanza en caso de algún error al cargar.
    */
	
	public void cargarJuego(File f) throws FileNotFoundException, IOException{
		elTablero = new Tablero();
		elTablero.cargarNivel(f);
		enJuego = true;
	}
	public void cargarPuntajes1() throws Exception{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_PUNTAJE1));
			PuntajeUnJugador aux= (PuntajeUnJugador)ois.readObject();
			this.agregarPuntaje(aux);
			ois.close();
		} catch (FileNotFoundException e) {
			primero = new PuntajeUnJugador(0, "");
		} catch (IOException e) {
			throw new Exception("Error al cargar el archivo");
		} catch (ClassNotFoundException e) {
			throw new Exception("Error al cargar el archivo");
		}
	}
	public void cargarPuntajes2() throws Exception{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_PUNTAJE2));
			PuntajeDosJugadores aux= (PuntajeDosJugadores)ois.readObject();
			this.insercion(aux);
			ois.close();
		} catch (FileNotFoundException e) {
		  raizDosJugadores = new PuntajeDosJugadores(0,"");
		} catch (IOException e) {
			throw new Exception("Error al cargar el archivo");
		} catch (ClassNotFoundException e) {
			throw new Exception("Error al cargar el archivo");
		}
	}
	
	public void guardarPuntaje1() throws Exception{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_PUNTAJE1));
			oos.writeObject(primero);
			oos.close();
		} catch (IOException e) {
			throw new Exception("Error al guardar el archivo");
		}
	}
	public void guardarPuntaje2() throws Exception{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_PUNTAJE2));
			oos.writeObject(raizDosJugadores);
			oos.close();
		} catch (IOException e) {
			throw new Exception("Error al guardar el archivo");
		}
	}
	
	
	/**
	 * Metodo que retorna un valor de verdad que define si se perdio el juego.
	 * @return un valor de verdad que determina si el juego se perdio.
	 */
	public boolean juegoTerminadoPerdido(){
		boolean acabo  = false;
		Casilla aguila = elTablero.darElAguila();
		if(aguila == null){
			acabo = true;
			perder= true;
			ganar = false;
			enJuego = false;
		}
		return acabo;
	}
	/**
	 * Metodo que retorna un valor de verdad que define si se gano el juego.
	 * @return un valor de verdad que determina si el juego se gano.
	 */
	public boolean juegoTerminadoGanado(){
		boolean acabo  = true;
		if( getElTablero()!=null){
			if( getElTablero().getBots()!=null){
		for(int i = 0; i < getElTablero().getBots().size() && acabo; i++){
		   Bot actual = getElTablero().getBots().get(i);
			if(actual.getVidas() != 0){
				acabo = false;
			}
		}
		if(acabo == true){
			ganar = true;
			enJuego = false;
		}
			}
		}
		return acabo;
	}
	public PuntajeDosJugadores getRaizDosJugadores() {
		return raizDosJugadores;
	}
	public void setRaizDosJugadores(PuntajeDosJugadores raizDosJugadores) {
		this.raizDosJugadores = raizDosJugadores;
	}
	public PuntajeUnJugador getPrimero() {
		return primero;
	}
	public void setPrimero(PuntajeUnJugador primero) {
		this.primero = primero;
	}
	/**
	 * Metodo que agrega un puntaje a la lista doblemente enlazada de puntajes de un jugador.
	 * @param p Puntaje de un juego de un jugador a agregar.
	 */
	public void agregarPuntaje(PuntajeUnJugador p){
		if(primero != null){
		PuntajeUnJugador aux = primero;
		PuntajeUnJugador anterior = null;
		   while(aux!= null && !(p.compararPorNombre(anterior)>0 && p.compararPorNombre(aux)<0)){
			if(aux.compararPorNombre(p) == 0){
		     
			}
			else{
				anterior = aux;
				aux = aux.darSiguiente();
			}
		  }
		  if(aux == null){
			 anterior.cambiarSiguiente(p);
			 p.cambiarAnterior(anterior);
		  }else{
			  anterior.cambiarSiguiente(p);
			  p.cambiarAnterior(anterior);
			  p.cambiarSiguiente(aux);
			  aux.cambiarAnterior(p);
		  }
	
		}
		else{
			primero = p;
		}
	}
	/**
	 * Metodo que elimina un puntaje de la lista doblemente enlazada de los puntajes de un jugador.
	 * @param nombre nombre del jugador con el puntaje a eliminar.
	 * @return 
	 */
	public void eliminarPuntajeUnJugador(String nombre){
		int r = this.contarPuntajeUnJugador();
		if(r!=0){
			if(primero.getNombre().equals(nombre)){
				primero = primero.darSiguiente();
				if(primero!=null){
					primero.cambiarAnterior(null);		
				}
			}
			else{
				PuntajeUnJugador anterior = primero;
				PuntajeUnJugador temporal = primero.darSiguiente();
				while(temporal!= null && !(temporal.getNombre().equals(nombre))){
					anterior = temporal;
					temporal = temporal.darSiguiente();
				}
				if(temporal != null){
					anterior.cambiarSiguiente(temporal.darSiguiente());
					if(temporal.compararPorNombre(darUltimo()) != 0){
						temporal.darSiguiente().cambiarAnterior(anterior);
					}
				}
				
				
			}
		}
	}
	/**
	 * Metodo que cuenta la cantidad de puntajes dentro de la lista doblemente enlazada de puntajes de un juego de un jugador.
	 * @return un integer con el numero de puntajes dentro de la lista.
	 */
	public int contarPuntajeUnJugador(){
		PuntajeUnJugador actual = primero;
		int total = 0;
		while(actual != null){
			total++;
			actual = actual.darSiguiente();
		}
		
		return total;
	}
	
	/**
	 * Retorna el ultimo puntaje en la lista doblemente enlazada de puntajes de un juego de un jugador.
	 * @return El ultimo puntaje de la lista.
	 */
	public PuntajeUnJugador darUltimo (){
		PuntajeUnJugador actual = primero;
		if(actual != null){
			while(actual.darSiguiente()!= null){
				actual = actual.darSiguiente();
		}
	  }
	  return actual;   
  }
	
	/**
	 * Metodo que determina si el arbol binario de puntajes de un juego de dos jugadores es vacio.
	 * True donde la raiz del arbol sea null. False si no.
	 * @return un valor de verdad determinando si el arbol esta vacio. 
	 */
	public boolean arbolVacio(){
		return raizDosJugadores == null;
	}
	/**
	 * Metodo que agrega un nuevo puntaje al arbol binario de puntajes de un juego de dos jugadores.
	 * @param a Puntaje que se desea agregar. Este puntaje debe ser una instancia de la clase PuntajeDosJugadores.
	 */
	public void insercion(Puntaje a){
	 if( a instanceof PuntajeDosJugadores){
		if(arbolVacio()){
			raizDosJugadores = (PuntajeDosJugadores) a;
		}
		else {
			if(raizDosJugadores.compareTo(a) > 0){
				if( raizDosJugadores.getIzquierda() == null){
					raizDosJugadores.setIzquierda((PuntajeDosJugadores) a);
				}
				else{
					raizDosJugadores.getIzquierda().insertarPuntaje(a);
				}
			}else{
				if(raizDosJugadores.getDerecha() == null){
					raizDosJugadores.setDerecha((PuntajeDosJugadores) a);
				}else{
					raizDosJugadores.getDerecha().insertarPuntaje(a);
				}
			}
		}
	   }
	}
	/**
	 * Metodo que retorna un arreglo con los puntajes en orden de menor a mayor del arbol binario.
	 * @return ArrayList con los puntajes de un juego de dos jugadores organizados de menor a mayor.
	 */
	public ArrayList <Puntaje> darPuntajesDosJugadores(){
		ArrayList <Puntaje> puntajes = new ArrayList<Puntaje>();
		raizDosJugadores.inOrder(puntajes);
		return puntajes;
	}
	public ArrayList <Puntaje> darPuntajesUnJugador(){
		PuntajeUnJugador aux= primero;
		ArrayList<Puntaje> retorno= new ArrayList<Puntaje>();
		while (aux!= null) {
			retorno.add(aux);
			aux= aux.darSiguiente();
		}
		return retorno;
	}
	public void salirJuego() {
		enJuego=false;
		perder=false;
		ganar=false;
		elTablero= null;
	}
	public String generarReporteUnJugador() {
		String mensaje="Nombre  puntaje"+ "\n";
		PuntajeUnJugador aux= primero;
		while (aux!=null) {
			mensaje+= aux.getNombre()+"  "+ aux.getPuntaje() + "\n";
			aux=aux.darSiguiente();
		}
		return mensaje;
	}
	public String generarReporteDosJugadores(){
		String mensaje="Nombre  puntaje"+ "\n";
		ArrayList<Puntaje> puntajes= darPuntajesDosJugadores();
		for (int i = 0; i < puntajes.size(); i++) {
			mensaje+= puntajes.get(i).getNombre()+"  "+puntajes.get(i).getPuntaje()+"\n"; 
		}
		return mensaje;
	}
	public String buscarPuntajeDosJugadores(String buscado) throws Exception{
	String mensaje="Nombre  puntaje"+ "\n";
	   ArrayList<Puntaje> puntajes= darPuntajesDosJugadores();
	   int indiceBuscado= buscarPuntaje(puntajes, buscado);
	   if(indiceBuscado==-1){
		   throw new Exception("No se encuentra el jugador solicitado");
	   }
	   mensaje+= puntajes.get(indiceBuscado).getNombre()+"  "+puntajes.get(indiceBuscado).getPuntaje()+"\n"; 
	   return mensaje;
	}
	public String buscarPuntajeUnJugador(String buscado) throws Exception{
	String mensaje="Nombre  puntaje"+ "\n";
	   ArrayList<Puntaje> puntajes= darPuntajesUnJugador();
	   int indiceBuscado= buscarPuntaje(puntajes, buscado);
	   if(indiceBuscado==-1){
		   throw new Exception("No se encuentra el jugador solicitado");
	   }
	   mensaje+= puntajes.get(indiceBuscado).getNombre()+"  "+puntajes.get(indiceBuscado).getPuntaje()+"\n"; 
	   return mensaje;
	}
	public int buscarPuntaje(ArrayList<Puntaje> lista, String buscado){
	    int retorno=-1;
	    int inicio = 0;
	    int fin = lista.size() - 1;
	    int pos;
	    boolean encontro=false;
	    while (inicio <= fin && encontro==false) {
	        pos = (inicio+fin) / 2;
	        if (lista.get(pos).getNombre().equals(buscado)){
	            retorno=pos;
	            encontro=true;
	        }
	        else if ( lista.get(pos).getNombre().compareTo(buscado)==-1) {
	            inicio = pos+1;
	        }
	        else {
	        fin = pos-1;
	        }
	    }
	    return retorno;
	}
}
