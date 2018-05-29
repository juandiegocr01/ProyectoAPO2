package hilos;

import interfaz.InterfazPrincipal;
import mundo.Bala;
import mundo.Casilla;
import mundo.NoExisteLaCasillaException;
import mundo.Orientacion;
import mundo.Tablero;
import mundo.Tanque;

public class HiloMovimientoBala extends Thread{
	/**
	 * Relacion con la bala asignada al hilo
	 */
	private Bala bala;
	/**
	 * Relacion con el tablero el cual conocera la bala para la obtencion de algunos metodos o casillas
	 */
	private Tablero tab;
	/**
	 * Relacion con la interfaz principal asignada
	 */
	private InterfazPrincipal principal;
	/**
	 * Constructor del hilo 
	 * @param t es el tablero asignado
	 * @param bala es la bala asignada al hilo
	 * @param interfazJuego es la interfaz del juego principal con la cual interactuara
	 */
	public HiloMovimientoBala(Tablero t, Bala bala, InterfazPrincipal interfazJuego){
		tab = t;
		this.bala=bala;
		this.principal = interfazJuego;
	}
	/**
	 * Es el run del hilo actual el cual se encargara del movimiento de la bala atravez del mapa al igual que de su imagen
	 */
	@Override
	public void run(){
		while(bala.getEstado()==Bala.ESTADO_ACTIVO && principal.darEstadoEnJuego()== true){
			if(tab.TocaBot(bala) == true && tab.getBots().get(tab.getRecienPegado()).getEstado()== Tanque.ESTADO_ACTIVO &&  tab.getBots().get(tab.getRecienPegado()).getVidas()>0){
			 tab.hacerDanoBot(bala);
			 bala.setEstado(Bala.ESTADO_INACTIVO);
			 generarExplosion();	
			}
			else if( tab.TocaJugador(bala) == true){
				tab.hacerDanoJugador(bala);
				bala.setEstado(Bala.ESTADO_INACTIVO);
				generarExplosion();
			}
			else{
			if(bala.getOrientacion().equals(Orientacion.ORIENTACION_OESTE)){
					oeste();
		    }
			else if(bala.getOrientacion().equals(Orientacion.ORIENTACION_NORTE)){
					norte();
			}
			else if(bala.getOrientacion().equals(Orientacion.ORIENTACION_SUR)){
					sur();
			}
			else if(bala.getOrientacion().equals(Orientacion.ORIENTACION_ESTE)){
					este();
			}
			}
	    }
	}
	/**
	 * En caso de que la orientacion de la bala para la busqueda de un choque , en este caso si la orientacion es oeste
	 * @throws NoExisteLaCasillaException 
	 */
	public void oeste() {
		try {
		Casilla siguiente  = tab.buscarCasilla(bala.getX()-1, bala.getY()+15);
		if(bala.getX()  > 0){
			if(siguiente.isCamino() != true && siguiente.getTipo() != Casilla.AGUAID){
			  if(siguiente.coordenadaXFinal() < bala.getX()){
				  bala.avanceImagen();
				   bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();
					}
			  else{
				  //Bala toca muro
				  if(siguiente.getTipo()==Casilla.LADRILLOID ){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionLadrillo();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  if(siguiente.getTipo()== Casilla.AGUILAID){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionAguila();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  bala.setEstado(Bala.ESTADO_INACTIVO);
				  generarExplosion();
			    }
			  }
			else{
			   	  bala.avanceImagen();
					bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();
			     }
	     }
		else{
			//Bala toca extremo
			  bala.setEstado(Bala.ESTADO_INACTIVO);
			  generarExplosion();
		}
		
		} catch (Exception e) {
			bala.setEstado(Bala.ESTADO_INACTIVO);
			generarExplosion();
		}
	}
	/**
	 * En caso de que la orientacion de la bala para la busqueda de un choque , en este caso si la orientacion es norte
	 * @throws NoExisteLaCasillaException 
	 */
	public void norte() {
		try {
		Casilla siguiente =tab.buscarCasilla(bala.getX() + 15, bala.getY() - 1);
		if(bala.getY()  > 0){
			if(siguiente.isCamino() != true && siguiente.getTipo() != Casilla.AGUAID){
			  if(siguiente.coordenadaYFinal() < bala.getY()){
				  bala.avanceImagen();
				   bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();

					}
			  else{
				  // Bala toca muro
				  if(siguiente.getTipo()==Casilla.LADRILLOID){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionLadrillo();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  if(siguiente.getTipo()== Casilla.AGUILAID){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionAguila();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  bala.setEstado(Bala.ESTADO_INACTIVO);
				  generarExplosion();
			    }
			  }
			else{
			   	  bala.avanceImagen();
					bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();
			   }
	     }
		else{
			//Bala toca extremo
			  bala.setEstado(Bala.ESTADO_INACTIVO);
			  generarExplosion();
		}

		} catch (Exception e) {
			bala.setEstado(Bala.ESTADO_INACTIVO);
			generarExplosion();
		}
	}
	/**
	 * En caso de que la orientacion de la bala para la busqueda de un choque , en este caso si la orientacion es sur
	 * @throws NoExisteLaCasillaException 
	 */
	public void sur(){
		try {
			
		Casilla siguiente =tab.buscarCasilla(bala.getX() + 15, bala.getY() + 30);
		if(siguiente==null){
			  bala.setEstado(Bala.ESTADO_INACTIVO);
			  generarExplosion();
		}
		else if(bala.getY() < principal.altoPanelTablero()){
			if(siguiente.isCamino() != true && siguiente.getTipo() != Casilla.AGUAID){
			  if(siguiente.getY() > bala.getYFinal()){
				  bala.avanceImagen();
				   bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();

					}
			  else{
				  //Bala toca muro
				  if(siguiente.getTipo()==Casilla.LADRILLOID){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionLadrillo();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  if(siguiente.getTipo()== Casilla.AGUILAID){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionAguila();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  
				  bala.setEstado(Bala.ESTADO_INACTIVO);
				 generarExplosion();
			    }
			  }
			else{
			   	  bala.avanceImagen();
					bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();
			   }
	     }
		else{
			//Bala toca extremo
			 if(siguiente.getTipo()==Casilla.LADRILLOID){
				  siguiente.colisionLadrillo();
			  }
			  bala.setEstado(Bala.ESTADO_INACTIVO);
			 generarExplosion();
		}
		} catch (Exception e) {
			bala.setEstado(Bala.ESTADO_INACTIVO);
		 generarExplosion();
		}
	}
	/**
	 * En caso de que la orientacion de la bala para la busqueda de un choque , en este caso si la orientacion es este
	 * @throws NoExisteLaCasillaException 
	 */
	public void este(){
			
		try {
		Casilla siguiente = tab.buscarCasilla(bala.getX()+30, bala.getY()+15);
			
		if(bala.getXFinal() <  principal.anchoPanelTablero()){
			if(siguiente.isCamino() != true && siguiente.getTipo() != Casilla.AGUAID){
			  if(siguiente.getX() > bala.getXFinal()){
//				  System.out.println("Entro");
				  bala.avanceImagen();
				   bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();

					}
			  else{
				  //Bala toca muro
				  if(siguiente.getTipo()==Casilla.LADRILLOID){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionLadrillo();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  if(siguiente.getTipo()== Casilla.AGUILAID){
					  int[] coordenadas = tab.buscarCasillaEnMatriz(siguiente.getX(), siguiente.getY());
					  tab.darTablero()[coordenadas[0]][coordenadas[1]].colisionAguila();
					  principal.cambiarImagen( tab.darTablero()[coordenadas[0]][coordenadas[1]], coordenadas[0], coordenadas[1]);
				  }
				  bala.setEstado(Bala.ESTADO_INACTIVO);
				  generarExplosion();
			    }
			  }
			else{
			   	  bala.avanceImagen();
					bala.avanceAdelante();
					try {
						sleep(Bala.ESPERA_BALA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.actualizarInterfaz();
			     }
	     }
		else{
			//Bala toca extremo
			  bala.setEstado(Bala.ESTADO_INACTIVO);
			  generarExplosion();
		}
		} catch (Exception e) {
			bala.setEstado(Bala.ESTADO_INACTIVO);
			generarExplosion();
		}
	}
	/**
	 * Metodo que se encarga de generar la explosion de la bala
	 */
	public void generarExplosion(){
		principal.generarExplosion(bala);
	}
}
