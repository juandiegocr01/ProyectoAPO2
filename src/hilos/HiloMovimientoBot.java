package hilos;

import interfaz.InterfazPrincipal;
import mundo.Bot;
import mundo.NoExisteBotException;
import mundo.NoExisteLaCasillaException;
import mundo.Tablero;
import mundo.Tanque;

public class  HiloMovimientoBot extends Thread{
	/**
	 * Relacion con tipo bot 
	 */
	private Bot bot;
	/**
	 * Relacion con la interfaz del juego
	 */
	private InterfazPrincipal interfazJuego;
	/**
	 * Relacion con tablero
	 */
	private Tablero tab;
	/**
	 * Constructor del hilo 
	 * @param t es el tablero asignado para que conozca el bot
	 * @param bot es el bot asignado al hilo
	 * @param interfazJuego es la interfaz del juego con la cual interactuara
	 */
	public HiloMovimientoBot(Tablero t, Bot bot, InterfazPrincipal interfazJuego){
		this.bot= bot;
		tab= t;
		this.interfazJuego= interfazJuego;
	}
	/**
	 * Es el run del hilo el cual se encarga de dar avance a la secuencia asignada al bot
	 */
	@Override
	public void run(){
		while(bot.getVidas()>0 && interfazJuego.darEstadoEnJuego()== true){
			if( bot.getContadorSecuencia()!=-1){
				bot.setOrientacion(bot.getSecuenciaPasos()[bot.getContadorSecuencia()]);
				bot.avanceAdelante();
				bot.avanzarSecuencia();
			}
		   try {
			if(bot.tanqueEnRango(tab.buscarElTanque()) && tab.darTanque().getEstado()== Tanque.ESTADO_ACTIVO){
				   interfazJuego.generarBalaBot(bot);
			   }
		} catch (NoExisteLaCasillaException | NoExisteBotException e1) {
		}
		  if( tab.darLaTanque()!=null){
		   try {
			if(bot.tanqueEnRango(tab.buscarLaTanque()) && tab.darLaTanque().getEstado() == Tanque.ESTADO_ACTIVO){
				   interfazJuego.generarBalaBot(bot);
			   }
		} catch (NoExisteLaCasillaException | NoExisteBotException e) {
		}
		  }
		   if(bot.aguilaEnRango(tab.darElAguila()) == true){
			   try {
				interfazJuego.generarBalaBot(bot);
			} catch (NoExisteBotException e) {
			}
		   }
			try {
				sleep(Bot.ESPERA_SECUENCIA);
			} catch (Exception e) {
			}
			interfazJuego.actualizarInterfaz();
		}
	}

	
	
}
