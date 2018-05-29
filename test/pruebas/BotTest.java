package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Bot;
import mundo.Casilla;
import mundo.Orientacion;

public class BotTest {
	private Bot botNecesario;
	
	public void escenario1(){
		String[] secuencia= new String[2];
		botNecesario=new Bot(10, 10, Bot.ESTADO_ACTIVO, 100, Orientacion.ORIENTACION_ESTE, secuencia);
	}
	
	@Test
	public void avanzarSecuenciaTest() {
		this.escenario1();
		assertTrue(botNecesario.getContadorSecuencia()==0);
		botNecesario.avanzarSecuencia();
		assertTrue(botNecesario.getContadorSecuencia()==1);
		botNecesario.avanzarSecuencia();
		assertTrue(botNecesario.getContadorSecuencia()==-1);
	}
	@Test 
	public void restarVidaTest(){
		this.escenario1();
		assertTrue(botNecesario.getVidas()==2);
		botNecesario.restarVida();
		assertTrue(botNecesario.getVidas()==1);
		botNecesario.restarVida();
		assertTrue(botNecesario.getVidas()==0);
	}
	@Test
	public void avanceAdelanteTest(){
		this.escenario1();
		assertTrue(botNecesario.getOrientacion().equals(Orientacion.ORIENTACION_ESTE));
		assertTrue(botNecesario.getX()==10);
		assertTrue(botNecesario.getY()==10);
		botNecesario.avanceAdelante();
		assertTrue(botNecesario.getX()==14 );
		assertTrue(botNecesario.getY()==10 );
		botNecesario.setOrientacion(Orientacion.ORIENTACION_NORTE);
		botNecesario.avanceAdelante();
		assertTrue(botNecesario.getX()==14 );
		assertTrue(botNecesario.getY()==6 );
		
	}
	 @Test
	public void tanqueEnRangoTest(){
		escenario1();
		Casilla posicionTanque = new Casilla(Casilla.CAMINOID);
		posicionTanque.setX(120);
		posicionTanque.setY(10);
		Casilla posicionVacia = new Casilla(Casilla.CAMINOID);
		posicionVacia.setX(500);
		posicionVacia.setY(210);
		
		
		boolean enRango = botNecesario.tanqueEnRango(posicionTanque);
		assertTrue(enRango);
		enRango =botNecesario.tanqueEnRango(posicionVacia);
		assertFalse(enRango);
		botNecesario.setX(120);
		botNecesario.setY(10);
		posicionTanque.setX(0);
		posicionTanque.setY(9);
		enRango = botNecesario.tanqueEnRango(posicionTanque);
		assertFalse(enRango);
		
		botNecesario.setOrientacion(Orientacion.ORIENTACION_OESTE);
		enRango = botNecesario.tanqueEnRango(posicionTanque);
		assertTrue(enRango);		
	}
	 @Test
	public void aguilaEnRangoTest(){
		escenario1();
		Casilla nula = null;
		Casilla aguila = new Casilla(Casilla.AGUILAID);
		aguila.setX(12);
		aguila.setY(9);
		
		boolean encontro = botNecesario.aguilaEnRango(nula);
		assertFalse(encontro);
		encontro = botNecesario.aguilaEnRango(aguila);
		
		assertTrue(encontro);
		
		aguila.setX(12);
		aguila.setY(50);
		
		encontro = botNecesario.aguilaEnRango(aguila);
		
		assertFalse(encontro);
	}

}
