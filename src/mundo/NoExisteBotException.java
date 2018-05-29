package mundo;

@SuppressWarnings("serial")
public class NoExisteBotException extends Exception{
	
	public NoExisteBotException(String mensaje){
		super(mensaje);
	}
	
	public NoExisteBotException(){
		this("No existe el bot buscado");
	}
}
