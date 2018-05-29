package pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import mundo.Puntaje;
import mundo.PuntajeDosJugadores;

public class PuntajeDosJugadoresTest {

	private PuntajeDosJugadores raiz;
	private void escenario1(){
		PuntajeDosJugadores uno = new PuntajeDosJugadores(300,"Manuel");
		 raiz = uno;
	}
	
	
	@Test
	public void insercionTest() {
		escenario1();
		PuntajeDosJugadores dos = new PuntajeDosJugadores(200,"Gallo");
		PuntajeDosJugadores tres = new PuntajeDosJugadores(450,"Caicedo");
		PuntajeDosJugadores cuatro = new PuntajeDosJugadores(100,"Nathalia");
		PuntajeDosJugadores cinco = new PuntajeDosJugadores(700,"Ana");
		PuntajeDosJugadores seis = new PuntajeDosJugadores(150,"German");
		PuntajeDosJugadores siete = new PuntajeDosJugadores(350,"Lopez");
		
		assertNull(raiz.getDerecha());
		assertNull(raiz.getIzquierda());
		
		raiz.insertarPuntaje(dos);
		raiz.insertarPuntaje(tres);
		
		assertNotNull(raiz.getDerecha());
		assertNotNull(raiz.getIzquierda());
		
		assertNull(raiz.getDerecha().getIzquierda());
		assertNull(raiz.getDerecha().getDerecha());
		assertNull(raiz.getIzquierda().getIzquierda());
		assertNull(raiz.getIzquierda().getDerecha());
		
		raiz.insertarPuntaje(cuatro);
		raiz.insertarPuntaje(cinco);
		raiz.insertarPuntaje(seis);
		raiz.insertarPuntaje(siete);
		
		assertNotNull(raiz.getDerecha().getDerecha());
		assertNotNull(raiz.getDerecha().getIzquierda());
		assertNotNull(raiz.getIzquierda().getIzquierda());
		assertNotNull(raiz.getIzquierda().getIzquierda());
		
	}
	@Test
	public void inOrderTest(){
		escenario1();
		PuntajeDosJugadores dos = new PuntajeDosJugadores(200,"Gallo");
		PuntajeDosJugadores tres = new PuntajeDosJugadores(450,"Caicedo");
		PuntajeDosJugadores cuatro = new PuntajeDosJugadores(100,"Nathalia");
		raiz.insertarPuntaje(dos);
		raiz.insertarPuntaje(tres);
		raiz.insertarPuntaje(cuatro);
		
		
		
		
		ArrayList <Puntaje> inOrder = new ArrayList <Puntaje>();
		
		raiz.inOrder(inOrder);
		assertTrue(inOrder.size() != 0);
		assertTrue(inOrder.get(0).compareTo(inOrder.get(1)) < 0);
		assertTrue(inOrder.get(1).compareTo(inOrder.get(2)) < 0);
		assertTrue(inOrder.get(2).compareTo(inOrder.get(3)) < 0);
	}
}