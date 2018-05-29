package mundo;

public abstract class Orientacion {
	/**
	 * Atributo de tipo constante que indica el estado del objeto en concreto , en este caso activo
	 */
	public static final boolean ESTADO_ACTIVO=true;
	/**
	 * Atributo de tipo constante que indica el estado del objeto en concreto , en este caso inactivo
	 */
	public static final boolean ESTADO_INACTIVO=false;
	/**
	 * Atributo de tipo constante que indica la orientacion del objeto , en este caso orientacion norte
	 */
	public static final String ORIENTACION_NORTE="NORTE";
	/**
	 * Atributo de tipo constante que indica la orientacion del objeto , en este caso orientacion sur
	 */
	public static final String ORIENTACION_SUR="SUR";
	/**
	 * Atributo de tipo constante que indica la orientacion del objeto , en este caso orientacion este
	 */
	public static final String ORIENTACION_ESTE="ESTE";
	/**
	 * Atributo de tipo constante que indica la orientacion del objeto , en este caso orientacion oeste
	 */
	public static final String ORIENTACION_OESTE="OESTE";
	/**
	 * Atributo de tipo int que indica la posicion del objeto en el eje x
	 */
	private int x;
	/**
	 * Atributo de tipo int que indica la posicion dle objeto en el eje y
	 */
	private int y;
	/**
	 * Atributo de tipo boolean que indica el estado del objeto , puede ser activo o inactivo
	 */
	private boolean estado;
	/**
	 * Atributo que indica el numero de imagen actual que se encuentra en la secuencia a mostrar
	 */
	private int imagenActual;
	/**
	 * Atributo que indica la ruta de imagen del objeto , ya dependiendo de la orientacion y de la imagen actual
	 */
	private String rutaImagen;
	/**
	 * Atributo que indica la orientacion del objeto con respecto a los puntos cardinales, ya puede ser NORTE,SUR,ESTE,U OESTE
	 */
	private String orientacion;
	/**
	 * Atributo que indica la espera en la secuencia del hilo para la imagen a mostrar , cada objeto tiene su espera
	 */
	private long espera;
	/**
	 * Constructor de tipo orientacion ,este sera de tipo super para cada objeto
	 * @param x es el x donde se asignara el objeto
	 * @param y es el y al cual se asignara al objeto
	 * @param estado es el estado del objeto en cuanto a estado inactivo o activo
	 * @param orientacion es la orientacion que se le asiganara al objeto en cuestion 
	 * @param espera es la espera para la secuencia de imagen
	 */
	public Orientacion(int x, int y, boolean estado, String orientacion, long espera) {
		this.x = x;
		this.y = y;
		this.estado = estado;
		this.imagenActual = 1;
		this.rutaImagen = "";
		this.orientacion = orientacion;
		this.espera= espera;
	}
	/**
	 * Metodo que se encarga de retornar el x actual del objeto
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Metodo que se encarga de modificar el x por uno recibido por parametro
	 * @param x recibido por parametro para cambiar el x actual
	 */
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	/**
	 * Metodo que se encarga de modificar el y por uno recibido por parametro
	 * @param y recibido por parametro para cambiar el y actual
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Metodo que se encarga de retornar el estado actual del objeto 
	 * @return estado
	 */
	public boolean getEstado() {
		return estado;
	}
	/**
	 * Metodo que se encarga de modificar el estado por uno recibido por parametro
	 * @param estado recibido por parametro para cambiar el estado actual
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	 * Metodo que se encarga de retornar la imagen actual 
	 * @return imagenActual
	 */
	public int getImagenActual() {
		return imagenActual;
	}
	/**
	 * Metodo que se encarga de modificar la imagenActual por uno recibido por parametro
	 * @param imagenActual recibido por parametro para cambiar la imagen actual
	 */
	public void setImagenActual(int imagenActual) {
		this.imagenActual = imagenActual;
	}
	/**
	 * Metodo que se encarga de retornar la ruta de imagen actual
	 * @return rutaImagen
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}
	/**
	 * Metodo que se encarga de modificar la ruta de imagen actual por una recibida por parametro
	 * @param rutaImagen es la nueva ruta de imagen a dar
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	/**
	 * Metodo que se encarga de obtener la orientacion del objeto actual
	 * @return orientacion
	 */
	public String getOrientacion() {
		return orientacion;
	}
	/**
	 * Metodo que se encarga de modificar la orientacion actual del objeto por una recibida por parametro
	 * @param orientacion es la nueva orientacion a dar al objeto
	 */
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	/**
	 * Metodo que se encarga de dar la espera actual del objeto
	 * @return espera 
	 */
	public long getEspera() {
		return espera;
	}
	/**
	 * Metodo que se encarga de modificar la espera por una recibida por parametro
	 * @param espera es la espera nueva para el objeto
	 */
	public void setEspera(long espera) {
		this.espera = espera;
	}
	/**
	 * Metodo que se encarga de sumar uno a la imagen actual , para asi avanzar en la secuencia
	 */
	public void sumarImagen(){
		imagenActual++;
	}
	
}
