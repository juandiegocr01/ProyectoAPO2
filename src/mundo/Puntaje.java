package mundo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Puntaje implements Comparable <Puntaje> , Serializable{
	/**
	 * Atributo de tipo constante que indica el puntaje por destruir cada bot
	 */
	public static int PUNTAJE_TANQUE= 100;
	/**
	 * Atributo de tipo constante que indica el puntaje por muerte de un jugador
	 */
	public static int PUNTAJE_MUERTE=50;
	/**
	 * Atributo de tipo int que indica el puntaje obtenido
	 */
	private int puntaje;
	/**
	 * Atributo de tipo String que indica el nombre el cual ha obtenido el puntaje
	 */
	private String nombre;
	/**
	 * Contrustructor del puntaje 
	 * @param puntaje es el puntaje obtenido
	 * @param nombre el nombre asignado para dicho puntaje
	 */
	public Puntaje(int puntaje, String nombre) {
		this.puntaje = puntaje;
		this.nombre = nombre;
	}
	/**
	 * Constructor del puntaje solo recibiendo como parametro el int del puntaje
	 * @param puntaje
	 */
	public Puntaje(int puntaje){
		this.puntaje=puntaje;
		nombre= "";
	}
	/**
	 * Meotodo que se encarga de retornar el nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo que se encarga de cambiar el nombre por uno recibido por parametro
	 * @param nombre es el nombre el cual va recibir 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo que se encarga de retornar el puntaje obtenido
	 * @return puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}
	/**
	 * Metodo que se encarga de modificar el puntaje por uno recibido por parametro
	 * @param puntaje es el puntaje nuevo que sera asignado
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	/**
	 * Metodo que se encarga de sumar al puntaje actual
	 * @param suma es la suma nueva para el puntaje
	 */
	public void sumarPuntaje(int suma){
		puntaje+=suma;
	}
	/**
	 * Metodo que se encarga de restar el puntaje por uno recibipo por parametro
	 * @param resta es el numero a restar al puntaje
	 */
	public void restarPuntaje(int resta){
		puntaje-=resta;
	}
	/**
	 * Metodo que se encarga de comparar por puntaje devolviendo un int que indica si es mayor o menor 
	 * @return resultado que es -1 en caso de que el puntaje recibiedo por parametro sea mayor que el actual , de lo contrario devolvera un 1
	 */
	public int compareTo(Puntaje p) {
		int resultado = 1;
		if(p != null){
		if(this.getPuntaje() > p.getPuntaje()){
			resultado = 1;
		}
		else if(this.getPuntaje() < p.getPuntaje()){
			resultado = -1;
		}
		else{
			resultado = 0;
		}
	  }
		return resultado;
	}
}
