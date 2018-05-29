package mundo;

@SuppressWarnings("serial")
public class NoExisteLaCasillaException extends Exception{
	
	public NoExisteLaCasillaException(String mensaje){
		super(mensaje);
	}
	
	public NoExisteLaCasillaException(){
		this("No existe la casilla buscada");
	}

}
