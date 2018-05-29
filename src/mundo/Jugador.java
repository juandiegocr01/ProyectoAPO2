package mundo;

public class Jugador extends Tanque{
	/**
	 * Constante tipo String con el valor que determina el id del jugador 1.
	 */
	public static final String ID_JUGADOR1= "JUGADOR1";
	/**
	 * Constante tipo String con el valor que determina el id del jugador 2.
	 */
	public static final String ID_JUGADOR2= "JUGADOR2";
	
	/**
	 * Atributo que representa el id del jugador.
	 */
	private String id;
	
  /**
   * Contructor del jugador
   * @param x Posicion x inicial del tanque del jugador.
   * @param y Posicion y inicial del tanque del jugador.
   * @param estado Estado actual del tanque del jugador.
   * @param vida Vida del tanque del jugador.
   * @param orientacion Orientacion del tanque del jugador.
   * @param id  Id del jugador.
   */
	public Jugador(int x, int y, boolean estado, int vida, String orientacion, String id) {
		super(x, y, estado, vida, orientacion);
		setxInicial(x);
		setyInicial(y);
		this.id= id;
		if(this.id.equals(ID_JUGADOR1)){
			setColor(Tanque.COLOR_VERDE);
		}
		else {
			setColor(COLOR_ROJO);
		}
		setRutaImagen("./data/imgs/tanques/"+getColor()+"/"+getColor()+"_"+getOrientacion()+"_"+getImagenActual()+".png");
	}

	@Override
	/**
	 * Metodo que genera un avance adelante al tanque del jugador segun la orientacion.
	 */
	public void avanceAdelante(){
		//System.out.println(getOrientacion());
		if( getOrientacion().equals(ORIENTACION_ESTE)) {
			setX(getX()+AVANCE_TANQUE);
			calcularFinales();
		}
		else if( getOrientacion().equals(ORIENTACION_NORTE)){
			setY(getY()-AVANCE_TANQUE);
			calcularFinales();
		}
		else if( getOrientacion().equals(ORIENTACION_OESTE)){
			setX(getX()-AVANCE_TANQUE);
			calcularFinales();
		}
		else if(getOrientacion().equals(ORIENTACION_SUR)){
			setY(getY()+AVANCE_TANQUE);
			calcularFinales();
		}
	}
}
