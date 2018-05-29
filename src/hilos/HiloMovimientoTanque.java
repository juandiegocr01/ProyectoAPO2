package hilos;

import interfaz.InterfazPrincipal;
import mundo.Tanque;

public class HiloMovimientoTanque extends Thread{
	/**
	 * Relacion de tipo tanque
	 */
	private Tanque tanque;
	/**
	 * Relacion con la interfazPrincipal del juego
	 */
	private InterfazPrincipal interfazJuego;
	/**
	 * Constructor del hilo
	 * @param tanque es el tanque que se le asiganara al hilo
	 * @param interfazJuego es la interfaz del juego principal asignara para que interactue
	 */
	public HiloMovimientoTanque(Tanque tanque, InterfazPrincipal interfazJuego){
		this.tanque= tanque;
		this.interfazJuego= interfazJuego;
	}
	/**
	 * Es el run del movimiento del tanque , se trata de seguir la secuencia del tanque en cuanto a imagen
	 */
	@Override
	public void run(){
		while(interfazJuego.darEstadoEnJuego() == true){
			tanque.avanceImagen();
			try {
				sleep(Tanque.ESPERA_TANQUE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			interfazJuego.actualizarInterfaz();
		}
	}
	
}
