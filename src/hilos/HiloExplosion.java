package hilos;

import interfaz.InterfazPrincipal;
import mundo.Explosion;

public class HiloExplosion extends Thread{
	/**
	 * Relacion con la explosion obtenida 
	 */
	private Explosion explosion;
	/**
	 * Relacion con la interfazPrincipal asignada
	 */
	private InterfazPrincipal interfazJuego;
	/**
	 * Constructor del hilo
	 * @param explosion es la explosion asignada al hilo
	 * @param interfazJuego es la interfazPrincipal con la cual se interactuaara
	 */
	public HiloExplosion(Explosion explosion, InterfazPrincipal interfazJuego){
		this.explosion= explosion;
		this.interfazJuego= interfazJuego;
	}
	/**
	 * Run del hilo el cual se encarga de dar un avance en la imagen de la explosion actual
	 */
	@Override
	public void run(){
		while(explosion.getEstado()==Explosion.ESTADO_ACTIVO && interfazJuego.darEstadoEnJuego()== true){
			explosion.avanceImagen();
			try {
				if( explosion.getTipoExplosion().equals(Explosion.EXPLOSION_BALA)){
				sleep(Explosion.ESPERA_EXPLOSION_BALA);
				}
				else if(explosion.getTipoExplosion().equals(Explosion.EXPLOSION_TANQUE)){
					sleep(Explosion.ESPERA_EXPLOSION_TANQUE);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			interfazJuego.actualizarInterfaz();
		}
	}
}
