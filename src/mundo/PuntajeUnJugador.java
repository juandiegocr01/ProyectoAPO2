package mundo;

@SuppressWarnings("serial")
public class PuntajeUnJugador extends Puntaje {
	/**
	 * Atributo que indica el indice del siguiente jugador debido al doble enlace
	 */
	private PuntajeUnJugador siguiente;
	/**
	 * Atributo que indica el indice del anterior jugador debido al doble enlace
	 */
	private PuntajeUnJugador anterior;
	/**
	 * Contructor de puntajeUnJugador
	 * @param puntajeObtenido es el puntaje obtenido
	 * @param nombre es el nombre del jugador cuyo puntaje fue obtenido
	 */
	public PuntajeUnJugador(int puntajeObtenido, String nombre) {
		super(puntajeObtenido,nombre);
	}
	/**
	 * Metodo que se encarga de dar siguiente 
	 * @return siguiente
	 */
	public PuntajeUnJugador darSiguiente(){
		return siguiente;
	}
	/**
	 * Metodo que se encarga de cambiar siguiente 
	 * @param sig es el jugador a remplazar
	 */
	public void cambiarSiguiente(PuntajeUnJugador sig){
		siguiente = sig;
	}
	/**
	 * Metodo que se encarga de dar anterior
	 * @return anterior
	 */
	public PuntajeUnJugador darAnterior() {
		return anterior;
	}
	/**
	 * Metodo que se encarga de cambiar el anterior por un recibido por parametro
	 * @param anterior es el anterior nuevo a poner
	 */
	public void cambiarAnterior(PuntajeUnJugador anterior) {
		this.anterior = anterior;
	}
	/**
	 * Metodo que se encarga de comparar por nombre 
	 * @param p es el jugador a comparar
	 * @return un indice que indica si es mayor o menor
	 */
	public int compararPorNombre(PuntajeUnJugador p){
		int respuesta = -1;
		if(p!=null){
		respuesta =getNombre().compareToIgnoreCase(p.getNombre());
		}
		return respuesta;
		}
}

