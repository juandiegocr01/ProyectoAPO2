package mundo;

public class Bala extends Orientacion implements InterfaceAvance{
	/**
	 * Este atributo de tipo constante indica el tipo de bala en este caso una bala normal indicada con NORMAL
	 */
	public static final String BALA_NORMAL="NORMAL";
	/**
	 * Este atributo de tipo constante indica el tipo de bala en este caso una bala especial indicada con ESPECIAL
	 */
	public static final String BALA_ESPECIAL="ESPECIAL";
	/**
	 * Este atributo de tipo constante indica el dano de una bala normal en este caso de 10
	 */
	public static final int DANO_NORMAL= 6;
	/**
	 * Este atributo de tipo constante indica el dano de una bala especial , en este caso de dano 20
	 */
	public static final int DANO_ESPECIAL= 15;
	/**
	 * Este atributo de tipo constante indica la espera de la bala , a la hora de la la creacion del hilo de avance de imagen
	 */
	public static final long ESPERA_BALA=20;
	/**
	 * Este atributo de tipo constante indica el avance de la bala normal en el mapa ya sea de x o y
	 */
	public static final int AVANCE_BALA_NORMAL=6;
	/**
	 * Este atributo de tipo constante indica el avance de la bala especial atra vez de mapa , ya se de x o y
	 */
	public static final int AVANCE_BALA_ESPECIAL=7;
	
	/**
	 *Este atributo indica el tipo de bala a crear 
	 */
	private String tipoBala;
	/**
	 * Este atributo indica el dano de la bala en cuestion
	 */
	private int dano;
	/**
	 * Este atributo indica el x final de la bala , en cuanto en pixeles de imagen
	 */
	private int xFinal;
	/**
	 * Este atributo indica el y final de la bala , en cuanto en pixeles de imagen
	 */
	private int yFinal;
	/**
	 * Esta relacion indica con el tipo explosion para cuando la bala esta en estado inactivo
	 */
	private Explosion explosion;
	/**
	 * Constructo de la bala
	 * @param x es el x donde se va asignar la bala a la hora de ser generada por el tanque
	 * @param y es el y donde se va asignar la bala a la hora de ser generada por el tanque
	 * @param estado es el estado de la bala donde se va crear
	 * @param orientacion es la orientacion de la bala donde se va diriguir al ser generada
	 * @param tipoBala es el tipo de bala que se va crear
	 */
	public Bala(int x, int y, boolean estado, String orientacion, String tipoBala) {
		super(x, y, estado, orientacion,ESPERA_BALA);
		this.tipoBala = tipoBala;
		if(this.tipoBala.equals(BALA_NORMAL)){
			dano=DANO_NORMAL;
			setRutaImagen("./data/imgs/balas/"+tipoBala+"/"+tipoBala+"_"+getImagenActual()+".png");
		}
		else if(this.tipoBala.equals(BALA_ESPECIAL)){
			dano= DANO_ESPECIAL;
			setRutaImagen("./data/imgs/balas/"+tipoBala+"/"+tipoBala+"_"+orientacion+"_"+getImagenActual()+".png");
		}
	}
	/**
	 * Este metodo se encarga de retornar el dano de la bala 
	 * @return dano
	 */
	public int getDano() {
		return dano;
	}
	/**
	 * Este metodo se encarga de modificar el dano de la bala por una recibida en parametro
	 * @param dano es el dano nuevo que se va dar a la bala
	 */
	public void setDano(int dano) {
		this.dano = dano;
	}
	/**
	 * Este metodo se encarga de retornar el tipo de bala 
	 * @return tipoBala
	 */
	public String getTipoBala() {
		return tipoBala;
	}
	/**
	 * Este metodo se encarga de modificar el tipo de bala por una recibida por parametro
	 * @param tipoBala es el parametro el cual se va modificar 
	 */
	public void setTipoBala(String tipoBala) {
		this.tipoBala = tipoBala;
	}
	/**
	 * Este metodos se encarga de avanzar una imagen en la secuencia , en caso de salirse de la secuencia vuelve y inicia de nuevo
	 */
	@Override
	public void avanceImagen() {
		sumarImagen();
		if(tipoBala.equals(BALA_NORMAL)){
			if(getImagenActual()>3)setImagenActual(1);
			setRutaImagen("./data/imgs/balas/"+tipoBala+"/"+tipoBala+"_"+getImagenActual()+".png");
		}
		else if(tipoBala.equals(BALA_ESPECIAL)){
			if(getImagenActual()>2)setImagenActual(1);
			setRutaImagen("./data/imgs/balas/"+tipoBala+"/"+tipoBala+"_"+getOrientacion()+"_"+getImagenActual()+".png");
		}
	}
	/**
	 * Este metodo se encarga del avance de la bala atravez del mapa dependiendo de la orientacion.
	 */
	@Override
	public void avanceAdelante() {
		if(this.tipoBala.equals(BALA_NORMAL)){
			if( getOrientacion().equals(ORIENTACION_ESTE)){
				setX(getX()+AVANCE_BALA_NORMAL);
				calcularFinales();
			}
			else if( getOrientacion().equals(ORIENTACION_NORTE)){
				setY(getY()-AVANCE_BALA_NORMAL);
				calcularFinales();
			}
			else if( getOrientacion().equals(ORIENTACION_OESTE)){
				setX(getX()-AVANCE_BALA_NORMAL);
				calcularFinales();
			}
			else if(getOrientacion().equals(ORIENTACION_SUR)){
				setY(getY()+AVANCE_BALA_NORMAL);
				calcularFinales();
			}
		}
		else if(this.tipoBala.equals(BALA_ESPECIAL)){
			if( getOrientacion().equals(ORIENTACION_ESTE)) setX(getX()+AVANCE_BALA_ESPECIAL);
			else if( getOrientacion().equals(ORIENTACION_NORTE)) setY(getY()-AVANCE_BALA_ESPECIAL);
			else if( getOrientacion().equals(ORIENTACION_OESTE))setX(getX()-AVANCE_BALA_ESPECIAL);
			else if(getOrientacion().equals(ORIENTACION_SUR))setY(getY()+AVANCE_BALA_ESPECIAL);
		}
	}
	/**
	 * Este metodo se encarga de crear la explosion de la bala
	 */
	public void generarExplosion(){
		explosion= new Explosion(getX(), getY(), Explosion.ESTADO_ACTIVO, getOrientacion(), Explosion.EXPLOSION_BALA);
	}
	/**
	 * Este metodo se encarga de cambiar lo finales de la imagen en cuanto el tamano
	 */
	public void calcularFinales(){
			xFinal = getX()+30;
			yFinal = getY()+30;
	}
	/**
	 * Este metodo se encarga de retornar el Xfinal de la bala
	 * @return xFinal
	 */
	public int getXFinal(){
		return xFinal;
	}
	/**
	 * Este metodo se encarga de retornar el Yfinal de la bala
	 * @return YFinal
	 */
	public int getYFinal(){
		return yFinal;
	}
	/**
	 * Este metodo se encarga de retornar la explosion de la bala , este metodo se llamara y siempre sera !=null
	 * @return explosion
	 */
	public Explosion getExplosion() {
		return explosion;
	}
	/**
	 * Este metodo se encarga de modificar la relacion de explosion de la bala por una recibida por parametro
	 * @param explosion es la explosion que se cambiara
	 */
	public void setExplosion(Explosion explosion) {
		this.explosion = explosion;
	}
}
