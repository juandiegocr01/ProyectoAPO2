package mundo;

public class Casilla {
    /**
     * Constante que con el valor del identificador de casilla Metal.
     */
	public final static String METAL = "Metal";
    /**
     *Constante que con el valor del identificador de casilla Arbol.
     */
	public final static String ARBOLES = "Arbol";
    /**
     *Constante que con el valor del identificador de casilla Agua.
     */
	public final static String AGUA = "Agua";
    /**
     *Constante que con el valor del identificador de casilla Ladrillo.
     */
	public final static String LADRILLO = "Ladrillo";
    /**
     *Constante que con el valor del identificador de casilla Camino.
     */
	public final static String CAMINO = "Camino";
    /**
     *Constante que con el valor del identificador de casilla Aguila.
     */
	public final static String AGUILA = "Aguila";
	
	
	/**
	 * Constante con el  valor del "tipo" de casilla Metal
	 */
	public final static char METALID = 'M';
	/**
	 * Constante con el  valor del "tipo" de casilla Arbol
	 */
	public final static char ARBOLESID = 'A';
	/**
	 * Constante con el  valor del "tipo" de casilla Agua
	 */
	public final static char AGUAID = 'G';
	/**
	 * Constante con el  valor del "tipo" de casilla Ladrillo
	 */
	public final static char LADRILLOID = 'L';
	/**
	 * Constante con el  valor del "tipo" de casilla Camino
	 */
	public final static char CAMINOID = 'C';
	/**
	 * Constante con el  valor del "tipo" de casilla Jugador 1
	 */
	public final static char JUGADOR1ID = 'T';
	/**
	 * Constante con el  valor del "tipo" de casilla Jugador 2
	 */
	public final static char JUGADOR2ID = 'U';
	/**
	 * Constante con el  valor del "tipo" de casilla Bot
	 */
	public final static char BOTSID = 'B';
	/**
	 * Constante con el  valor del "tipo" de casilla Aguila
	 */
	public final static char AGUILAID = 'I';
	/**
	 * Constante con el  valor del "tipo" de casilla Aguila Destruida.
	 */
    public final static char AGUILADESTRUIDAID = 'D';
	
	/**
	 * Atributo que representa el identificador de la casilla.
	 */
	private String identificador;
	/**
	 * Atributo que representa el valor en x del plano coordenado de pixeles de la casilla.
	 */
	private int x;
	/**
	 * Atributo que representa el valor en y del plano coordenado de pixeles de la casilla.
	 */
	private int y;
    /**
     * Atributo que representa si la casilla es un camino o no.
     */
	private boolean camino;
	/**
	 * Atributo que representa el tipo de casilla.
	 */
	private char tipo;
	/**
	 * Atributo que representa la ruta hacia la imagen de la casilla.
	 */
	private String ruta;
	
	/**
	 * Constructor de la casilla.
	 * @param tipo el tipo de casilla a crear.
	 */
	   public Casilla (char tipo){
		   this.tipo =tipo;
		   if(tipo == METALID){
			   identificador = METAL;
			   camino =  false;
		   }
		   else if( tipo == ARBOLESID){
			   identificador = ARBOLES; 
			   camino = true;
		   }
		   else if( tipo == AGUAID){
			   identificador = AGUA;
			   camino = false;
		   }
		   else if(tipo == LADRILLOID){
			   identificador = LADRILLO;
			   camino = false;
		   }
		   else if(tipo == CAMINOID){
			   identificador = CAMINO;
			   camino = true;
		   }
		   else if(tipo == JUGADOR1ID){
			   identificador = CAMINO ;
			   camino = true;
		   }
		   else if(tipo == JUGADOR2ID){
			   identificador = CAMINO;
			   camino = true;
		   }
		   else if(tipo == BOTSID ){
			   identificador = CAMINO;
			   camino = true;
		   }
		   else if(tipo == AGUILAID){
			   identificador = AGUILA;
		   }
		   if(identificador == AGUILA){
			   ruta = "./data/" + "imgs/" + "casillas/"+ identificador +".png";
		   }
		   else{
		   ruta = "./data/" + "imgs/" + "casillas/"+ identificador +".jpg";
		   }
   }

/**
 * Metodo que retorna el identificador de la casilla.
 * @return retorna el identificador de la casilla.
 */
public String getIdentificador() {
	return identificador;
}

/**
 * Metodo que modifica el identificador de la casilla.
 * @param identificador nuevo valor a asignar como identificador de la casilla.
 */
public void setIdentificador(String identificador) {
	this.identificador = identificador;
}

/**
 * Metodo que retorna el tipo de casilla.
 * @return el tipo de casilla.
 */
public char getTipo() {
	return tipo;
}

/**
 * Metodo que modifica el tipo de casilla.
 * @param tipo nuevo tipo de casilla que se desea asignar.
 */
public void setTipo(char tipo) {
	this.tipo = tipo;
}

/**
 * Metodo que retorna la ruta de la imagen de la casilla.
 * @return la ruta de la imagen de la casilla actual.
 */
public String getRuta() {
	return ruta;
}

/**
 *  Metodo que modifica la ruta hacia la imagen de la casilla actual.
 * @param ruta
 */
public void setRuta(String ruta) {
	this.ruta = ruta;
}
/**
 * Metodo que retorna la posicion en X donde inicia la casilla actual
 * @return valor en x donde inicia la casilla actual.
 */
public int getX() {
	return x;
}

/**
 * Metodo que modifica la posicion en X donde inicia la casilla actual.
 * @param x nuevo valor de la posicion inicial de X.
 */
public void setX(int x) {
	this.x = x;
}

/**
 * Metodo que retorna la posicion en Y donde inicia la casilla actual
 * @return valor en y donde inicia la casilla actual.
 */
public int getY() {
	return y;
}

/**
 * Metodo que modifica la posicion en Y donde inicia la casilla actual.
 * @param y nuevo valor de la posicion inicial de Y.
 */
public void setY(int y) {
	this.y = y;
} 
/**
 * Metodo que retorna un valor de verdad si la casilla es un camino.
 * @return valor de verdad que determina si la casilla es un camino.
 */
public boolean isCamino() {
	return camino;
}

/**
 * Metodo que modifica el estado de si la casilla actual es un camino.
 * @param camino nuevo valor de verdad a asignar para determinar si es un camino.
 */
public void setCamino(boolean camino) {
	this.camino = camino;
}
/**
 * Metodo que retorna la coordenada en X donde finaliza la casilla.
 * @return valor en X donde finaliza la casilla.
 */
public int coordenadaXFinal(){
	return x+58;
}
/**
 * Metodo que retorna la coordenada en Y donde finaliza la casilla.
 * @return valor en Y donde finaliza la casilla.
 */
public int coordenadaYFinal(){
	return y+58;
}


@Override
public String toString() {
	return "Casilla [tipo=" + tipo + "]";
}
/**
 * Metodo que cambia los valores de la casilla actual si es de tipo ladrillo cuando una bala choca con ella. 
 */
public void colisionLadrillo(){
	tipo = CAMINOID;
	identificador = CAMINO;
	camino = true;
	ruta = "./data/" + "imgs/" + "casillas/"+ "Camino" +".jpg";
	
}
/**
 * Metodo que cambia los valores de la casilla actual si es de tipo Aguila cuando una bala choca con ella. 
 */
public void colisionAguila(){
	tipo =  AGUILADESTRUIDAID;
	identificador = METAL;
	camino = false;
	ruta = "./data/" + "imgs/" + "casillas/"+ identificador +".jpg";
}

}
