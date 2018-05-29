package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Jugador;
import mundo.Orientacion;

public class JugadorTest {
	
	private Jugador jugador1;
	
	public void escenario1(){
		jugador1=new Jugador(10, 10, Jugador.ESTADO_ACTIVO, 100, Orientacion.ORIENTACION_ESTE, Jugador.ID_JUGADOR1);
	}

	@Test
	public void avanceAdelanteTest() {
		this.escenario1();
		assertTrue(jugador1.getX()==10);
		assertTrue(jugador1.getY()==10);
		jugador1.avanceAdelante();
		assertTrue(jugador1.getX()==20);
		assertTrue(jugador1.getY()==10);
		jugador1.setOrientacion(Orientacion.ORIENTACION_NORTE);
		jugador1.avanceAdelante();
		assertTrue(jugador1.getX()==20);
		assertTrue(jugador1.getY()==0);
		jugador1.setOrientacion(Orientacion.ORIENTACION_SUR);
		jugador1.avanceAdelante();
		jugador1.avanceAdelante();
		jugador1.avanceAdelante();
		assertTrue(jugador1.getX()==20);
		assertTrue(jugador1.getY()==30);
		jugador1.setOrientacion(Orientacion.ORIENTACION_OESTE);
		jugador1.avanceAdelante();
		assertTrue(jugador1.getX()==10);
		assertTrue(jugador1.getY()==30);	
	}

}
