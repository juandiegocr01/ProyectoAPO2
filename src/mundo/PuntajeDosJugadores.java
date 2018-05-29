package mundo;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class PuntajeDosJugadores extends Puntaje {
  
  /**
   * Metodo que representa la hoja o rama a la izquierda del puntaje actual.
   */
  private PuntajeDosJugadores izquierda;
  /**
   * Metodo que representa la hoja o rama a la derecha del puntaje actual.
   */
  private PuntajeDosJugadores derecha;
 
  
  
  /**
   * Constructor de la clase.
   * @param puntaje puntaje conseguido en el juego.
   * @param nombre  nombre de los jugadores que consiguieron el puntaje.
   */
  public PuntajeDosJugadores(int puntaje, String nombre) {
	super(puntaje, nombre);
}

/**
 * Metodo que retorna la hoja o rama a la derecha del puntaje actual.
 * @return la hoja o rama derecha del puntaje.
 */
public PuntajeDosJugadores getDerecha() {
		return derecha;
	}

    /**
     * Metodo que modifica la hoja o rama derecha del puntaje actual.
     * @param derecha Puntaje nuevo para asignar.
     */
	public void setDerecha(PuntajeDosJugadores derecha) {
		this.derecha = derecha;
	}

	/**
	 * Metodo que retorna la hoja o rama a la izquierda del puntaje actual.
	 * @return la hoja o rama izquierda del puntaje.
	 */
	public PuntajeDosJugadores getIzquierda() {
		return izquierda;
	}

   /**
    * Metodo que modifica la hoja o rama izquierda del puntaje actual.
    * @param izquierda Puntaje nuevo para asignar.
    */
	public void setIzquierda(PuntajeDosJugadores izquierda) {
		this.izquierda = izquierda;
	}
	/**
	 * Metodo recursivo que inserta un nuevo puntaje al arbol.
	 * @param a nuevo puntaje a agregar.
	 */
	public void insertarPuntaje(Puntaje a){
	 if(a instanceof PuntajeDosJugadores){
		if(this.compareTo(a) < 0){
			if(this.derecha == null){
				derecha =  (PuntajeDosJugadores) a;
			}
			else{
				this.derecha.insertarPuntaje(a);
			}
		}
		else{
			if(izquierda == null){
				izquierda = (PuntajeDosJugadores) a;
			}
			else{
				izquierda.insertarPuntaje(a);
			}
		}
	 	
	}

	}
	/**
	 * Metodo recursivo que agrega de menor a mayor a un ArrayList los puntajes.
	 * @param participantes ArrayList al que se desea agregar los puntajes.
	 */
	public void inOrder(ArrayList <Puntaje> participantes){
		if(izquierda != null){
			izquierda.inOrder(participantes);
		}
		participantes.add(this);
		if(derecha != null){
			derecha.inOrder(participantes);
		}
	}
}

