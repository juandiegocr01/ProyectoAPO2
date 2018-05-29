package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public  class Tablero {
	/**
	 * Este relacion de casillas en modo de matriz indica el arreglo de casillas del escenario creado en el tablero
	 */
	private Casilla[][] escenario;
	/**
	 * Esta relacion indica el jugador uno en el tablero
	 */
	private Jugador elTanque;
	/**
	 * Esta relacion indica el jugador dosen el tablero
	 */
	private Jugador laTanque;
	/**
	 * Esta relacion con la clase puntaje indica el puntaje general de todo el tablero
	 */
	private Puntaje puntajeGeneral;
	/**
	 * Este atributo indica en indice del bot el cual acaba de ser pegado por una bala de un jugador
	 */
	private int recienPegado;
	/**
	 * Este atributo indica el id del jugador que acaba de ser pegado por la bala de un jugador
	 */
	private String idPegado;
	/**
	 * Este arreglo de bots indica los bots jugardados en el tablero para la creacion del juego
	 */
	private ArrayList<Bot> bots;
	/**
	 * Constructor del tablero
	 */
	public Tablero(){
		puntajeGeneral= new Puntaje(0);
//		laTanque =  new Jugador(200, 200, Jugador.ESTADO_ACTIVO, 100, Orientacion.ORIENTACION_NORTE, Jugador.ID_JUGADOR2);
	}
	/**
	 * Metodo que se encarga de buscar la casilla donde se encuentra un objeto actualmente.
	 * @param x Posicion x del objeto que se esta buscando.
	 * @param y Posicion y del objeto que se esta buscando.
	 * @return La casilla donde esta posicionado el objeto.
	 * @throws NoExisteLaCasillaException se lanza cuando la casilla que se busca no se encuentra.
	 */
	public Casilla buscarCasilla(int x, int y) throws NoExisteLaCasillaException{
		if(x < 0 && y >= 0 ){
			x = 0;
		}
		else if(x >= 0 && y <0){
			y = 0;
		}
		else if(x < 0 && y <0){
			x = 0;
	       y = 0;
		}
		boolean encontro = false;
		Casilla laCasilla = null;
		for(int i = 0; i < escenario.length && !encontro; i++){
			for(int j = 0; j < escenario[0].length && !encontro; j++){
				Casilla actual =  escenario[i][j];
			    if(actual.getX() <= x && actual.coordenadaXFinal() >= x && actual.getY() <= y && actual.coordenadaYFinal()>= y){
			    	laCasilla = actual;
			    	encontro = true;
			    }
		  }
		}
		
		if(laCasilla == null){
			throw new NoExisteLaCasillaException();
		}
		return laCasilla;
	}
	
	/**
	 * Metodo que se encarga de asignarle las coordenadas a cada casilla.
	 */
	
	public void asignarCoordenadasCasillas(){
		int x = 0;
		int y = 0;
		for(int i = 0; i < escenario.length; i++){
			x= 0;
			if(i!= 0){
				y+= 58;	
			}
			for(int j  = 0; j < escenario[0].length; j++){
				escenario[i][j].setX(x);
				escenario[i][j].setY(y);
				x+= 58;
			}
		
	  }
	}
	/**
	 * Este metodo se encarga de rotornar el arreglo de bots del tablero
	 * @return bots
	 */
	public ArrayList<Bot> getBots() {
		return bots;
	}
	/**
	 * Este metodo se encarga de cambiar el arreglo de bots del tablero
	 * @param bots es el parametro ni
	 */
	public void setBots(ArrayList<Bot> bots) {
		this.bots = bots;
	}
    /**
     * Metodo que retorna la matriz con las casillas que conforman el mapa del juego.
     * @return el escenario del juego.
     */
	public Casilla[][] darTablero(){
		return escenario;
	}
	/**
	 * Metodo que se encarga de retornar el tanque del jugador 1.
	 * @return  el tanque del jugador 1.
	 */
	public Tanque darTanque(){
		return elTanque;
	}
	/**
	 * Metodo que se encarga de retornar el tanque del jugador 2.
	 * @return  el tanque del jugador 2.
	 */
	public Jugador darLaTanque() {
		return laTanque;
	}
	
	/**
	 * Metodo que modifica el tanque del jugador 2.
	 * @param laTanque el nuevo tanque del jugador 2.
	 */

	public void modificarLaTanque(Jugador laTanque) {
		this.laTanque = laTanque;
	}
	/**
	 * Metodo que busca la posicion en la matriz de una casilla en particular.
	 * @param x El x de la casilla que se esta buscando.
	 * @param y El y de la casilla que se esta buscando.
	 * @return Un arreglo de dos posiciones de integers con la primera posicion siendo la fila, y la segunda la columna.
	 */
	public int[] buscarCasillaEnMatriz(int x , int y){
		int[] coordenadas = new int[2];
		boolean encontro = false;
		for(int i = 0; i < escenario.length && !encontro; i++){
			for(int j = 0; j < escenario[0].length && !encontro; j++){
				Casilla actual =  escenario[i][j];
			    if(actual.getX() == x && actual.getY() == y){
			        coordenadas[0] = i;
			    	coordenadas[1] =j;
			    	encontro = true;
			    }
		  }
		}
		if(encontro == false){
			throw new NullPointerException();
		}
		return coordenadas;
	}
	/**
	 * Metodo que se encarga de buscar la casilla donde se encuentra el tanque del jugador 1.
	 * @return la casilla donde se encuentra el tanque del jugador 1.
	 * @throws NoExisteLaCasillaException se lanza si la casilla del tanque buscada no existe.\
	 * 
	 */
	public Casilla buscarElTanque() throws NoExisteLaCasillaException{
		Casilla laCasilla = buscarCasilla(elTanque.getX(), elTanque.getY());
		return laCasilla;
	}
	/**
	 * Metodo que se encarga de buscar la casilla donde se encuentra el tanque del jugador 2.
	 * @return la casilla donde se encuentra el tanque del jugador 2.
	 * @throws NoExisteLaCasillaException se lanza si la casilla buscada no existe.
	 */
    public Casilla buscarLaTanque() throws NoExisteLaCasillaException{
		Casilla laCasilla = buscarCasilla(laTanque.getX(), laTanque.getY());
		return laCasilla;
	}
	/**
	 * Metodo que se encarga de buscar la casilla que sea de tipo Aguila.
	 * @return la casilla con el tipo Aguila.
	 */
	public Casilla darElAguila(){
		boolean encontro = false;
		Casilla aguila = null;
				for(int i = 0; i < escenario.length && !encontro ; i++){
					for(int j = 0; j < escenario[0].length && !encontro; j++){
						if(escenario[i][j].getTipo() == Casilla.AGUILAID){
							aguila = escenario[i][j];
							encontro = true;
						}
					}
				}
		return aguila;
	}
	/**
	 * Este metodo se encarga de buscar un bot en el arreglo de bots en cuestion , este retornara un int con la indicacion de la posicion del arreglo donde se encuentra el bot
	 * @param buscar es el bot a buscar
	 * @return es el indice del arreglo donde se encuentra el bot en el arreglo de bots del tablero
	 * @throws NoExisteBotException excepcion que se lanza si no se encuentra el bot.
	 */
	public int BuscarBot(Bot buscar) throws NoExisteBotException{
		int indiceBuscar =0;
		boolean encontro= false;
		for (int i = 0; i < bots.size() && encontro==false; i++) {
			if(bots.get(i).equals(buscar)){
				indiceBuscar=i;
				encontro=true;
			}
		}
		if(encontro == false){
			throw new NoExisteBotException();
		}
		return indiceBuscar;
	}
	public String getIdPegado() {
		return idPegado;
	}
	public void setIdPegado(String idPegado) {
		this.idPegado = idPegado;
	}
	/**
	 * Este metodo se encarga de buscar la bala en los bots , este metodo retornara un int en el cual indica la posicion del arreglo donde el bot tiene la bala a buscar
	 * @param buscar
	 * @return
	 */
	public int indiceBalaEnBot(Bala buscar){
		int indiceBuscar =-1;
		boolean encontro= false;
		for (int i = 0; i < bots.size() && encontro==false; i++) {
			ArrayList<Bala> balasBot=bots.get(i).getBalaNormal();
			for (int j = 0; j < balasBot.size(); j++) {
				if(bots.get(i).getBalaNormal().get(j).equals(buscar)){
					indiceBuscar=i;
					encontro=true;	
				}
			}
		}
		return indiceBuscar;
	}
	/**
	 * Este metodo en encarga de buscar el indice de la bala en el arreglo de un bot en especifico 
	 * @param buscar es el int del bot en el cual se esta buscando la bala 
	 * @param balaBuscar es la bala en cuestion a buscar
	 * @return el indice de la bala si se encuentra en el arreglo , de lo contrario retornara un -1
	 */
	public int buscarBalaEnBot(int buscar, Bala balaBuscar){
		int indiceBuscar=-1;
		boolean encontro= false;
		for (int i = 0; i < bots.get(buscar).getBalaNormal().size() && encontro==false; i++) {
			if(bots.get(buscar).getBalaNormal().get(i).equals(balaBuscar)){
				indiceBuscar=i;
				encontro= true;
			}
		}
		return indiceBuscar;
	}
	/**
	 * Este metodo se encarga de retornar un int en caso de que la bala pertenesca al arreglo de bala del jugador1, de lo contrario retornara un -1.
	 * @param solicitado es la bala la cual se buscara en el arreglo
	 * @return un int con la indicacion de la posicion de la bala en el arreglo del jugador 1 , si no se encuentra retornara un -1
	 */
	public int BuscarBalaEnJugador1(Bala solicitado){
		int indiceBuscar=-1;
		boolean encontro=false;
		for (int i = 0; i < elTanque.getBalaNormal().size() && encontro==false; i++) {
			if(elTanque.getBalaNormal().get(i).equals(solicitado)){
				indiceBuscar=i;
				encontro=true;
			}
		}
		return indiceBuscar;
	}
	/**
	 * Este metodo se encarga de retornar un int en caso de que la bala pertenesca al arreglo de bala del jugador2, de lo contrario retornara un -1.
	 * @param solicitado es la bala la cual se buscara en el arreglo
	 * @return un int con la indicacion de la posicion de la bala en el arreglo del jugador 2 , si no se encuentra retornara un -1
	 */
	public int BuscarBalaEnJugador2(Bala solicitado){
		int indiceBuscar=-1;
		boolean encontro=false;
		if( laTanque!= null){
		for (int i = 0; i < laTanque.getBalaNormal().size() && encontro==false; i++) {
			if(laTanque.getBalaNormal().get(i).equals(solicitado)){
				indiceBuscar=i;
				encontro=true;
			}
		}
		}
		return indiceBuscar;
	}
	/**
	 * Este metodo se encarga de retornar un boolean en caso de que la bala en cuestion toque cualquier tipo de bot , esta bala previmente se revisa si pertence a un jugador
	 * de lo contrario retorna un false, este metodo asigna el recien pegado para obtener el bot que es tocado en cuestion 
	 * @param solicitado es la bala solicitada para verificar si toca un bot
	 * @return retorna un boolean en caso de que la bala en cuestion toque un bot
	 */
	public boolean TocaBot(Bala solicitado){
		boolean encontro= false;
		int indice=BuscarBalaEnJugador1(solicitado);
		int indice2= BuscarBalaEnJugador2(solicitado);
		if( indice!=-1 || indice2!= -1){
		for (int i = 0; i < bots.size() && encontro==false; i++) {
			if( bots.get(i).toco(solicitado)== true){
			setRecienPegado(i);
			encontro=true;
			}
		}
		
		}
		return encontro;
	}
	/**
	 * Este metodo se encarga de verificar si la bala en cuestion en caso de pertener a un bot enemigo , verificar si en cuestion la bala toca
	 * a un jugador ya sea el dos o el uno para en caso de que si , retornar un boolean con la confirmacion
	 * @param solicitado la bala que se verifica si toca un jugador
	 * @return un boolean indicando la condicion de la bala al tocar un jugador
	 */
	public boolean TocaJugador(Bala solicitado){
		boolean encontro= false;
	    int indice= indiceBalaEnBot(solicitado);
		if( indice!=-1){
			if( elTanque.toco(solicitado)== true ){
				encontro=true;
				idPegado= Jugador.ID_JUGADOR1;
			}
			else if( laTanque != null  && laTanque.toco(solicitado) == true){
				encontro=true;
				idPegado= Jugador.ID_JUGADOR2;
			}
		}
		return encontro;
	}
	/**
	 * Este metodo se encarga de gacer dano al bot que acaba de ser recien pegado en el juego , este recibe un dano segun el tipo de bala que recibe
	 * este metodo tambien se encarga de restar una vida al bot en caso de que la vida del bot sea agotada
	 * @param bala es la bala el cual le hara dano al bot 
	 */
	public void hacerDanoBot(Bala bala) {
		int dano = bala.getDano();
		bots.get(recienPegado).hacerDano(dano);
		if(bots.get(recienPegado).getVida()<0){
			bots.get(recienPegado).restarVida();
			bots.get(recienPegado).setEstado(Tanque.ESTADO_INACTIVO);
			bots.get(recienPegado).generarExplosion();
			puntajeGeneral.sumarPuntaje(Puntaje.PUNTAJE_TANQUE);
		}
	}
	/**
	 * Este metodo se encarga de gacer dano a un jugador dependiendo de la bala que lo golpee , este tiene la capacidad de generar una
	 * explosion en caso de que la vida del tanque sea menor que 0
	 * @param bala es el objeto bala el cual le hara dano al bot
	 */
	public void hacerDanoJugador(Bala bala) {
	   int dano= bala.getDano();
	   if( idPegado.equals(Jugador.ID_JUGADOR1)){
		   elTanque.hacerDano(dano);
		   if(elTanque.getVida()<0){
			   elTanque.setEstado(Tanque.ESTADO_INACTIVO);
			   elTanque.generarExplosion();
			   puntajeGeneral.restarPuntaje(Puntaje.PUNTAJE_MUERTE);
		   }
	   }
	   else if( idPegado.equals(Jugador.ID_JUGADOR2)){
		   laTanque.hacerDano(dano);
		   if(laTanque.getVida()<0){
			   laTanque.setEstado(Tanque.ESTADO_INACTIVO);
			   laTanque.generarExplosion();
		   }
	   }
	}
	/**
	 * Este metodo se encarga de retornar un int con la indicion del bot recien pegado
	 * @return recien pegado
	 */
	public int getRecienPegado() {
		return recienPegado;
	}
	/**
	 * Metodo que se encarga de modificar el parametro de recien pegado por uno recibido por parametro
	 * @param recienPegado es el parametro el cual se modificadra
	 */
	public void setRecienPegado(int recienPegado) {
		this.recienPegado = recienPegado;
	}
	/**
	 * Metodo que se encarga de reiniciar un tanque del jugador uno o dos , dandola la vida de nuevo , un estado de activo.
	 * @param id es el id ya sea del jugador uno o dos
	 */
	public void reinciarTanque(String id) {
		if( id.equals(Jugador.ID_JUGADOR1)){
		elTanque.setEstado(Tanque.ESTADO_ACTIVO);
		elTanque.setVida(100);
		elTanque.setX(elTanque.getxInicial());
		elTanque.setY(elTanque.getyInicial());
		}
		else if(id.equals(Jugador.ID_JUGADOR2)){
			laTanque.setEstado(Tanque.ESTADO_ACTIVO);
			laTanque.setVida(100);
			laTanque.setX(laTanque.getxInicial());
			laTanque.setY(laTanque.getyInicial());
		}
	}
	/**
	 * Metodo que se encarga de reiniciar un bot , poniendolo activo , dandole indicaciones de la secuencia.
	 * @param bot el indice del bot a reiniciar
	 */
	public void reinciarBot(int bot) {
		bots.get(bot).setEstado(Tanque.ESTADO_ACTIVO);
		bots.get(bot).setVida(100);
		bots.get(bot).setX(bots.get(bot).getxInicial());
		bots.get(bot).setY(bots.get(bot).getyInicial());
		bots.get(bot).setContadorSecuencia(1);
	}
	/**
	 * Metodo que se encarga de cargar el nivel de la partida actual ,tiene la capacidad de reproducir de un jugador o dos
	 * @param f
	 */
	public void cargarNivel (File f) throws FileNotFoundException, IOException{
		@SuppressWarnings("resource")
		BufferedReader lector  = new BufferedReader(new FileReader(f));
		String[] valores;
		String mensaje;
		mensaje = lector.readLine();
		valores =  mensaje.split(" ");
		int ancho,alto;
		ancho = Integer.parseInt(valores[0]);
		alto =  Integer.parseInt(valores[1]);
		escenario =  new Casilla[ancho][alto];
		for (int i = 0; i < ancho; i++){
			mensaje = lector.readLine();
			for (int j = 0; j < alto; j++){
				valores = mensaje.split(" ");
				char id =  valores[j].charAt(0);
				escenario[i][j] = new Casilla (id);
			}
		}
		asignarCoordenadasCasillas();
       bots = new ArrayList<Bot>();
		for (int x = 0; x < ancho; x++){
			for (int y = 0; y < alto; y++){
				if(escenario[x][y].getTipo() == Casilla.JUGADOR1ID){	
					elTanque = new Jugador(escenario[x][y].getX() + 23, escenario[x][y].getY()+25, Tanque.ESTADO_ACTIVO, 100, Orientacion.ORIENTACION_NORTE, Jugador.ID_JUGADOR1);
				}
				else if(escenario[x][y].getTipo() == Casilla.JUGADOR2ID){
					laTanque = new Jugador(escenario[x][y].getX() + 23, escenario[x][y].getY()+25, Tanque.ESTADO_ACTIVO, 100, Orientacion.ORIENTACION_NORTE, Jugador.ID_JUGADOR2);
				}
				else if(escenario[x][y].getTipo() == Casilla.BOTSID){
					Bot nuevo = new Bot(escenario[x][y].getX()+ 23, escenario[x][y].getY()+ 25, Tanque.ESTADO_ACTIVO, 100, Orientacion.ORIENTACION_SUR,null );
					bots.add(nuevo);				
				}
			}
		}
		mensaje = lector.readLine();
		int contador=0;
		String[] secuencia= null;
		 while( mensaje != null )
		    {
			 if( !mensaje.startsWith( "//" ) && !mensaje.equals( "" ) )
	          {
				 valores= mensaje.split(" ");
				 if(valores.length<=1) {
					 secuencia= new String[Integer.parseInt(valores[0])];
				 }
				 else{
					 for (int i = 0; i < secuencia.length; i++) {
					 secuencia[i]= valores[i];
				     }
					 bots.get(contador).setSecuenciaPasos(secuencia);
					 bots.get(contador).setOrientacion(secuencia[0]);
					 contador++;
				 }
	          }
	         try
	         {
	             mensaje = lector.readLine( );
	         }
	         catch( Exception e )
	         {
	             throw new IOException( "Error al cargar los datos almacenados sobre el juego" );
	         }
		    }
	     lector.close();	
	}
	/**
	 * Metodo para obtener el puntaje general del tablero
	 * @return el puntaje general del tablero
	 */
	public Puntaje getPuntajeGeneral() {
		return puntajeGeneral;
	}
	/**
	 * Metodo que se encarga de modificar el puntaje general del juego
	 * @param puntajeGeneral el parametro del nuevo puntaje a poner
	 */
	public void setPuntajeGeneral(Puntaje puntajeGeneral) {
		this.puntajeGeneral = puntajeGeneral;
	}
	public void modificarElTanque(Jugador elTanque2) {
		elTanque = elTanque2;
		
	}
	public void modificarEscenario(Casilla[][] escenario2) {
		escenario = escenario2;
	}
	public Casilla[][] getEscenario() {
		return escenario;
	}

}
