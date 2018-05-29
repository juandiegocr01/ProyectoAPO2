package mundo;

public class Bot extends Tanque{
	/**
	 * Este atributo de tipo constante indica la espera entre secuencia para el avance del bot
	 */
	public static final long ESPERA_SECUENCIA=80;
	/**
	 * Este atributo indica la secuencia seguida por el bot
	 */
	private String[] secuenciaPasos;
	/**
	 * Este atributo indica en donde se encuentra la secuencia en el arreglo de secuencias peliminar
	 */
	private int contadorSecuencia;
	/**
	 * Este atributo indica el numero de vidas del bot 
	 */
	private int vidas;
	
	/**
	 * Contructor del bot a crear 
	 * @param x es el x asignado para las x inicial del bot
	 * @param y es el y asignado para las y inicial del bot
	 * @param estado es el estado de bot en cuestion
	 * @param vida es la vida que se le dara al bot
	 * @param orientacion es la orientacion que se le dara al bot 
	 * @param secuenciaPasos es la secuencia de pasos que seguira el bot atra ves del mapa
	 */
	public Bot(int x, int y, boolean estado, int vida, String orientacion, String[] secuenciaPasos) {
		super(x, y, estado, vida, orientacion);
		setxInicial(x);
		setyInicial(y);
		contadorSecuencia=0 ;
		vidas=2;
		this.secuenciaPasos = secuenciaPasos;
		setColor(Tanque.COLOR_AZUL);
		setRutaImagen("./data/imgs/tanques/"+getColor()+"/"+getColor()+""+orientacion+""+getImagenActual()+".png");
	}
	/**
	 * Metodo que se encarga de retornar un boolean indicando si el jugador uno se encuentra en rango
	 * @param tanque es la casilla actual donde esta el tanque en el momento a buscar
	 * @return un boolean indicando si se encuentra en rango el tanque 
	 */
	public boolean tanqueEnRango(Casilla tanque){
		boolean encontro = false;
		if(getOrientacion().equals(Orientacion.ORIENTACION_OESTE) || getOrientacion().equals(ORIENTACION_ESTE)){
			if(getOrientacion().equals(Orientacion.ORIENTACION_OESTE)){
				if(tanque.getX() <= getX() && (getY()>= tanque.getY() && getY() <=  tanque.coordenadaYFinal()) ){
					encontro = true;
				}
			}
			else{
				if(tanque.getX() >= getX() && (getY() >= tanque.getY()  && getY() <  tanque.coordenadaYFinal()) ){
					encontro = true;
				}
			}
		}
		else{
			if(getOrientacion().equals(Orientacion.ORIENTACION_NORTE)){
				if(tanque.getY() <= getY() && (getX()>= tanque.getX() && getX() <=  tanque.coordenadaXFinal()) ){
					encontro = true;
				}
			}
			else{
				if(tanque.getY() >= getY() && (getX()>= tanque.getX() && getX() <=  tanque.coordenadaXFinal()) ){
					encontro = true;
				}
			}
		}
		return encontro;
	}
	/**
	 * Este metodo se encarga de retornar un boolean en caso de que la aguila se encuentre en rango para el bot , segun la orientacion
	 * @param aguila es la casilla donde se encuentra el aguila
	 * @return retorna un boolean en caso de que el aguila seencuentre en rango del bot
	 */
	public boolean aguilaEnRango(Casilla aguila){
		boolean encontro = false;
		if(aguila != null){
		if(getOrientacion().equals(Orientacion.ORIENTACION_OESTE) || getOrientacion().equals(ORIENTACION_ESTE)){
			if(getOrientacion().equals(Orientacion.ORIENTACION_OESTE)){
				if(aguila.getX() <= getX() && (getY()>= aguila.getY() && getY() <=  aguila.coordenadaYFinal()) ){
					encontro = true;
				}
			}
			else{
				if(aguila.getX() >= getX() && (getY() >= aguila.getY()  && getY() <  aguila.coordenadaYFinal()) ){
					encontro = true;
				}
			}
		}
		else{
			if(getOrientacion().equals(Orientacion.ORIENTACION_NORTE)){
				if(aguila.getY() <= getY() && (getX()>= aguila.getX() && getX() <=  aguila.coordenadaXFinal()) ){
					encontro = true;
				}
			}
			else{
				if(aguila.getY() >= getY() && (getX()>= aguila.getX() && getX() <=  aguila.coordenadaXFinal()) ){
					encontro = true;
				}
			}
		}
	    }
		return encontro;
	}
	/**
	 * Este metodo se encarga de avanzar en la secuencia de movimientos del bot , este se devuelve a 0 cuando termina la secuencia 
	 */
	public void avanzarSecuencia() {
		contadorSecuencia++;
		if(contadorSecuencia>=secuenciaPasos.length)contadorSecuencia=-1;	
	}
	/**
	 * Este metodo se encarga de retornar las vidas actuales de los bots 
	 * @return vidas
	 */
	public int getVidas() {
		return vidas;
	}
	/**
	 * Este metodo se encarga de modificar las vidas por un int recibido por parametro
	 * @param vidas este indica las nuevas vidas a dar al bot
	 */
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	/**
	 * Este metodo se encarga de restar una vida al bot al ser eliminado por un jugador
	 */
	public void restarVida() {
		setVidas(getVidas()-1);
	}
	/**
	 * Metodo que se encarga de retornar un arreglo de Strings con la secuencia de pasos del bot
	 * @return
	 */
	public String[] getSecuenciaPasos() {
		return secuenciaPasos;
	}
	/**
	 * Este metodo se encarga de modificar la secuencia de pasos por una recibida por parametro
	 * @param secuenciaPasos es la secuencia nueva 
	 */
	public void setSecuenciaPasos(String[] secuenciaPasos) {
		this.secuenciaPasos = secuenciaPasos;
	}
	/**
	 * Este metodo se encarga de retornar el contador de secuencias actual 
	 * @return contadorSecuencia
	 */
	public int getContadorSecuencia() {
		return contadorSecuencia;
	}
	/**
	 * Este metodo se encarga de 
	 * @param contadorSecuencia
	 */
	public void setContadorSecuencia(int contadorSecuencia) {
		this.contadorSecuencia = contadorSecuencia;
	}
	/**
	 * Este metodo se encarga de dar un avance hacia adelante en el bot
	 */
	@Override
	public void avanceAdelante(){
		//System.out.println(getOrientacion());
		if( getOrientacion().equals(ORIENTACION_ESTE)) {
			setX(getX()+AVANCE_TANQUE_BOT);
			calcularFinales();
		}
		else if( getOrientacion().equals(ORIENTACION_NORTE)){
			setY(getY()-AVANCE_TANQUE_BOT);
			calcularFinales();
		}
		else if( getOrientacion().equals(ORIENTACION_OESTE)){
			setX(getX()-AVANCE_TANQUE_BOT);
			calcularFinales();
		}
		else if(getOrientacion().equals(ORIENTACION_SUR)){
			setY(getY()+AVANCE_TANQUE_BOT);
			calcularFinales();
		}
	}
}