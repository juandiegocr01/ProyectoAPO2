package mundo;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Tanque extends Orientacion implements InterfaceAvance{
	/**
	 * Atributo de tipo constante que indica el color del tanque , en este caso de color verde del jugador uno
	 */
	public static final String COLOR_VERDE="VERDE";
	/**
	 * Atributo de tipo constante que indica el color del tanque , en este caso de color rojo del jugador dos
	 */
	public static final String COLOR_ROJO="ROJO";
	/**
	 * Atributo de tipo constante que indica el color del tanque , en este caso de color azul del bot
	 */
	public static final String COLOR_AZUL="AZUL";
	/**
	 * Atributo de tipo constante que indica el avance del tanque , en este caso del tanque de los jugadores
	 */
	public static final int AVANCE_TANQUE=10;
	/**
	 * Atributo de tipo constante que indica el avance del bot , en este caso de los bots 
	 */
	public static final int AVANCE_TANQUE_BOT=4;
	/**
	 * Atributo de tipo constante que indica la espera entre imagenes del tanque, en este caso de cualquier tipo de tanque
	 */
	public static final long ESPERA_TANQUE=15;
	/**
	 * Atributo de tipo int que indica la vida del tanque en cuestion
	 */
	private int vida;
	/**
	 * Atributo que indica el color asignado al tanque
	 */
	private String color;
	/**
	 * Atributo de tipo int que indica el Xfinal en la imagen del tanque
	 */
	private int xFinal;
	/**
	 * Atributo de tipo int que indica el YFinal en la imagen del tanque
	 */
	private int yFinal;
	/**
	 * Relacion de tipo explosion que indica la explosion del tanque a la hora de estar inactivo
	 */
	private Explosion explosion;
	/**
	 * Relacion de tipo bala que indica la bala recien creada por el tanque
	 */
	private Bala recienCreada;
	/**
	 * Arreglo de bala en el cual se guardara las balas disparadas por el tanque
	 */
	private ArrayList<Bala> balaNormal;
	/**
	 * Relacion de tipo bala que indica la bala especial creada
	 */
	private Bala balaEspecial;
	/**
	 * Atributo que indica el int inicial del tanque sobre el eje x, para guardarla y aparecer en el mismo lugar
	 */
	private int xInicial;
	/**
	 * Atributo que indica el int inicial del tanque sobre el eje y, para guardarla y aparecer en el mismo lugar
	 */
	private int yInicial;
	/**
	 * Constructor del tanque 
	 * @param x es el x inicial asignado al tanque
	 * @param y es el y inicial asingado al tanque
	 * @param estado es el estado del tanque creado
	 * @param vida es la vida del tanque asignada
	 * @param orientacion es la orientacion inicial al ser creado
	 */
	public Tanque(int x, int y, boolean estado,int vida, String orientacion) {
		super(x, y, estado, orientacion,ESPERA_TANQUE);
		this.vida= vida;
		balaNormal = new ArrayList<Bala>();
	}
	/**
	 * Este metodo se encarga de retornar la vida del tanque
	 * @return vida
	 */
	public int getVida() {
		return vida;
	}
	/**
	 * Este metodo se encarga de modificar la vida del tanque por una recibida por parametro
	 * @param vida es la vida nueva para el tanque
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}
	/**
	 * Este metodo se encarga de retornar el color del tanque actual
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Este metodo se encarga de modificar el color del tanque por uno recibida por parametro
	 * @param color es el color nuevo del tanque
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * Este metodo se encarga de retornar el arreglo de balas del tanque
	 * @return balaNormal
	 */
	public ArrayList<Bala> getBalaNormal() {
		return balaNormal;
	}
	/**
	 * Este metodo se enarga de modifica el arreglo de balas de tipo normal del tanque por una recibida por 
	 * parametro
	 * @param balaNormal es el nuevo arreglo de balas que recibira
	 */
	public void setBalaNormal(ArrayList<Bala> balaNormal) {
		this.balaNormal = balaNormal;
	}
	/**
	 * Este metodo se encarga de retornar la bala especial del tanque
	 * @return balaEspecial
	 */
	public Bala getBalaEspecial() {
		return balaEspecial;
	}
	/**
	 * Este metodo se encarga de modificar la bala especial por una recibida por parametro
	 * @param balaEspecial es el parametro el cual cambiara la bala especial
	 */
	public void setBalaEspecial(Bala balaEspecial) {
		this.balaEspecial = balaEspecial;
	}
	/**
	 * Este metodo se encarga de avanzar la imagen en la secuencia de imagen del movimiento del tanque
	 */
	@Override
	public void avanceImagen(){
		sumarImagen();
		if(getImagenActual()>8)setImagenActual(1);
		setRutaImagen("./data/imgs/tanques/"+color+"/"+color+"_"+getOrientacion()+"_"+getImagenActual()+".png");
	}
	/**
	 * Este metodo se encarga de calcular los finales de la imagen del tanque , este metodo modifica los atributos xInicial y y final siempre que se mueve
	 */
	public void calcularFinales(){
		if( getOrientacion().equals(ORIENTACION_ESTE) || getOrientacion().equals(ORIENTACION_OESTE)){
			xFinal = 0;
			yFinal = 0;
			xFinal += getX()+29;
			yFinal+= getY()+24;
		}
		else if(getOrientacion().equals(ORIENTACION_NORTE) || getOrientacion().equals(ORIENTACION_SUR)){
			xFinal = 0;
			yFinal = 0;
			xFinal += getX()+24;
			yFinal+= getY()+29;
		}
	}
	/**
	 * Este metodo se encarga de retornar el Xfinal de la bala 
	 * @return xFinal
	 */
	public int getXFinal(){
		return xFinal;
	}
	/**
	 * Este metodo se encarga de retornar el yFinal de la bala 
	 * @return yFinal
	 */
	public int getYFinal(){
		return yFinal;
	}	
	/**
	 * Este metodo se encarga de generar una nueva bala para el tanque , esta la crea y lo agrega al arreglo de balas creadas
	 * @param tipo es el tipo de bala a crear
	 */
	public void generarBala(String tipo){
		if( tipo.equals(Bala.BALA_NORMAL)){			
			Bala nueva = new Bala(getX(),getY(),Orientacion.ESTADO_ACTIVO,getOrientacion(),tipo);
		    recienCreada= nueva;
			balaNormal.add(nueva);
		}
		else {
			balaEspecial = new Bala(getX(),getY(),Orientacion.ESTADO_ACTIVO,getOrientacion(),tipo);
		}
	}
	/**
	 * Este metodo se encarga de retornar la bala recienCreada
	 * @return recienCreada
	 */
	public Bala getRecienCreada() {
		return recienCreada;
	}
	/**
	 * Metodo que se encarga de modificar la bala recien creada por una recibida por parametro
	 * @param recienCreada es la nueva bala recien creada
	 */
	public void setRecienCreada(Bala recienCreada) {
		this.recienCreada = recienCreada;
	}
	/**
	 * Este metodo se encarga de convertir la vida en los pixeles  de la barra de vida creada
	 * @return el numero de pixeles que medira la barra de vida
	 */
	public int conversionVida(){
		int resultado=0;
		resultado=(this.getVida()*30)/100;
		return resultado;
	}
	/**
	 * Metodo que se encarga de retornar una evaliacion si una bala toca el tanque actual
	 * @param evaluacion es la bala la cual se verificara si tocara el tanque
	 * @return un boolean indicando la evaluacion creada 
	 */
	public boolean toco(Bala evaluacion){
		boolean toco= false;
		int x1= evaluacion.getX();
		int y1= evaluacion.getY();
		int x2= getX();
		int y2= getY();
		
		Rectangle r1= new Rectangle(x1, y1, 30, 30);
		Rectangle r2= new Rectangle();
		if( getOrientacion().equals(Tanque.ORIENTACION_ESTE) || getOrientacion().equals(Tanque.ORIENTACION_OESTE)){
			r2= new Rectangle(x2, y2, 29, 24);
		}
		else if(getOrientacion().equals(Tanque.ORIENTACION_NORTE) || getOrientacion().equals(Tanque.ORIENTACION_SUR)){
			r2= new Rectangle(x2, y2, 24, 29);
		}
		toco= r1.intersects(r2);
		return toco;
	}
	public void hacerDano(int dano) {
		setVida(getVida()-dano);
	}
	/**
	 * Metodo que se encarga de generar una explosion
	 */
	public void generarExplosion(){
		explosion= new Explosion(getX(), getY(), Orientacion.ESTADO_ACTIVO, getOrientacion(), Explosion.EXPLOSION_TANQUE);
	}
	/**
	 * Este metodo se encarga de retornar la explosion del tanque actual
	 * @return explosion
	 */
	public Explosion getExplosion() {
		return explosion;
	}
	/**
	 * Metodo que se encarga de modifica la explosion por una recibida por parametro
	 * @param explosion es la explosion nueva asignada
	 */
	public void setExplosion(Explosion explosion) {
		this.explosion = explosion;
	}
	/**
	 * Este metodo se encarga de retornar el xInicial
	 * @return xInicial
	 */
	public int getxInicial() {
		return xInicial;
	}
	/**
	 * Metodo que se encarga de modificar le XInicial por una recibida por parametro
	 * @param XInicial el nuevo y inicial al cual se va asignar al tanque
	 */
	public void setxInicial(int xInicial) {
		this.xInicial = xInicial;
	}
	/**
	 * Este metodo se encarga de retornar el yInicial  
	 * @return yInicial
	 */
	public int getyInicial() {
		return yInicial;
	}
	/**
	 * Metodo que se encarga de modificar le yInicial por una recibida por parametro
	 * @param yInicial el nuevo y inicial al cual se va asignar al tanque
	 */
	public void setyInicial(int yInicial) {
		this.yInicial = yInicial;
	}
}