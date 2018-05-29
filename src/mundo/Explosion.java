package mundo;

public class Explosion extends Orientacion implements InterfaceAvance{
	/**
	 * Atributo de tipo constante que indica el tipo de explosion en este caso es una explosion del tanque
	 */
	public static final String EXPLOSION_TANQUE="TANQUE";
	/**
	 * Atributo de tipo constante que indica el tipo de explosion en este caso es una explosion de la bala
	 */
	public static final String EXPLOSION_BALA="BALA";
	/**
	 * Atributo de tipo constante que indica la espera entre imagenes en la secuencia de imagenes en la explosion de la bala
	 */
	public static final long ESPERA_EXPLOSION_BALA=15;
	/**
	 * Atributo de tipo constante que indica la espera entre imagenes en la secuencia de imagen de explosion de tanque
	 */
	public static final long ESPERA_EXPLOSION_TANQUE=30;
	/**
	 * Atributo de tipo String que indica el tipo de explosion
	 */
	private String tipoExplosion;
	/**
	 * Constructor de la explsion
	 * @param x en el cual sera asignado la explosion 
	 * @param y en el cual sera asginado la explosion
	 * @param estado es el estado de la explosion 
	 * @param orientacion es la orientacion de la explosion , aunque este no afecta el resultado
	 * @param tipoExplosion el parametro tipo String que indica el tipo de explosion de la bala o tanque
	 */
	public Explosion(int x, int y, boolean estado, String orientacion, String tipoExplosion) {
		super(x, y, estado, orientacion, ESPERA_EXPLOSION_BALA);
		this.tipoExplosion= tipoExplosion;
		if(this.tipoExplosion.equals(EXPLOSION_TANQUE)){
			setEspera(ESPERA_EXPLOSION_TANQUE);
			setRutaImagen("./data/imgs/explosiones/"+tipoExplosion+"/"+getImagenActual()+".png");
		}
		else if(this.tipoExplosion.equals(EXPLOSION_BALA)){
			setRutaImagen("./data/imgs/explosiones/"+tipoExplosion+"/"+getImagenActual()+".png");
		}
	}
	/**
	 * Metodo que se encarga de retornar el tipo de explosion la cual fue creada
	 * @return tipoExplosion
	 */
	public String getTipoExplosion() {
		return tipoExplosion;
	}
	/**
	 * Este metodo se encarga de modificar el tipo de explosion por una recibida por parametro
	 * @param tipoExplosion es el tipo de explosion que se pondra
	 */
	public void setTipoExplosion(String tipoExplosion) {
		this.tipoExplosion = tipoExplosion;
	}
	/**
	 * Metodo que se encarga de avanzar la imagen en la explosion para poder hacer el efecto visual de la explosion
	 */
	@Override
	public void avanceImagen(){
		sumarImagen();
		if(tipoExplosion.equals(EXPLOSION_BALA)){
			if(getImagenActual()>25)setEstado(ESTADO_INACTIVO);
			setRutaImagen("./data/imgs/explosiones/"+tipoExplosion+"/"+getImagenActual()+".png");
		}
		else if(tipoExplosion.equals(EXPLOSION_TANQUE)){
			if(getImagenActual()>27)setEstado(ESTADO_INACTIVO);
			setRutaImagen("./data/imgs/explosiones/"+tipoExplosion+"/"+getImagenActual()+".png");
		}
	}
	/**
	 * Metodo que se encarga de avanzar la explosion sobre el eje x y y, aunque aqui este no afecta ya que la explosion solo se provoca en un solo lugar
	 */
	@Override
	public void avanceAdelante(){
	}

}