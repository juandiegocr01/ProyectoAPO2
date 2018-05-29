package pruebas;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import mundo.Bot;
import mundo.Casilla;
import mundo.Juego;
import mundo.Orientacion;
import mundo.Puntaje;
import mundo.PuntajeDosJugadores;
import mundo.PuntajeUnJugador;
import mundo.Tablero;

public class JuegoTest {

	private Juego elJuego;
	
	private void escenario1(){
		elJuego = new Juego();
	}
	private void escenario2(){
		elJuego = new Juego();
		Tablero n = new Tablero();
		ArrayList <Bot> bots = new ArrayList <Bot>();
	   n.setBots(bots);
		Bot x = new Bot(12, 23, Bot.ESTADO_ACTIVO, 10, Orientacion.ORIENTACION_SUR, null);
		n.getBots().add(x);
		
		Casilla[][] escenario = new Casilla[1][3];
		escenario [0][1] = new Casilla(Casilla.AGUILAID);
		escenario [0][0] = new Casilla(Casilla.LADRILLOID);
		escenario [0][2] = new Casilla (Casilla.CAMINOID);
		n.modificarEscenario(escenario);
		elJuego.setElTablero(n);
	}
	
	
	@Test
	public void  cargarJuegoTest() throws FileNotFoundException, IOException {
		escenario1();
		File f = new File("./niveles/Un Jugador/nivel_1.txt");
		elJuego.cargarJuego(f);
		
		assertTrue(elJuego.getEnJuego() == true);
		assertTrue(elJuego.getElTablero() != null);
		assertTrue(elJuego.getElTablero().darTanque() != null);
		assertTrue(elJuego.getElTablero().getBots().size() != 0);
	}
	@Test
	public void cargarPuntajes1Test(){
		escenario1();
		try {
			elJuego.cargarPuntajes1();
			assertTrue(elJuego.getPrimero() != null);
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void cargarPuntajes2Test(){
		escenario1();
		try {
			elJuego.cargarPuntajes2();
			assertTrue(elJuego.getRaizDosJugadores() != null);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void juegoTerminadoPerdidoTest(){
		escenario2();
		elJuego.setGanar(true);
		elJuego.setEnJuego(true);
		
	assertNotNull(elJuego.getElTablero().darElAguila());
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	elJuego.getElTablero().darElAguila().colisionAguila();
		
	boolean termino = elJuego.juegoTerminadoPerdido();
	
	assertTrue(elJuego.getGanar() == false);
	assertTrue(elJuego.getEnJuego() == false);
	assertTrue(elJuego.getPerder() == true);
	assertTrue(termino == true);
	
	}
	@Test
	public void juegoTerminadoGanadoTest(){
		escenario2();
		elJuego.setEnJuego(true);
		
	assertFalse(elJuego.juegoTerminadoGanado());
		
	elJuego.getElTablero().getBots().get(0).setVidas(0);
	boolean acabo = elJuego.juegoTerminadoGanado();
	
	assertTrue(elJuego.juegoTerminadoGanado());
	assertTrue(elJuego.getEnJuego() == false);
	assertTrue(elJuego.getPerder() == false);
	assertTrue(acabo == true);
	
	}
	@Test
	public void agregarPuntajeUnJugadorTest(){
		escenario1();
		PuntajeUnJugador uno = new PuntajeUnJugador(400, "Caicedo");
		PuntajeUnJugador dos = new PuntajeUnJugador(510, "Gallo");
		
		assertNull(elJuego.getPrimero());
		elJuego.agregarPuntaje(uno);
		assertNotNull(elJuego.getPrimero());
		assertSame(elJuego.getPrimero(),uno);
		assertNull(elJuego.getPrimero().darSiguiente());
		
		elJuego.agregarPuntaje(dos);
		assertTrue(elJuego.contarPuntajeUnJugador() == 2);
		assertNotNull(elJuego.getPrimero().darSiguiente());
		assertSame(elJuego.getPrimero().darSiguiente(), dos);
	
	}
	@Test
	public void contarPuntajeUnJugadorTest(){
		escenario1();
		assertNull(elJuego.getPrimero());
		
		PuntajeUnJugador uno = new PuntajeUnJugador(400, "Caicedo");
		elJuego.agregarPuntaje(uno);
		int contar = elJuego.contarPuntajeUnJugador();
		assertTrue(contar == 1);
		PuntajeUnJugador dos = new PuntajeUnJugador(510, "Gallo");
		elJuego.agregarPuntaje(dos);
		contar =  elJuego.contarPuntajeUnJugador();
		assertTrue(contar == 2);
		PuntajeUnJugador tres = new PuntajeUnJugador(400, "N");
		PuntajeUnJugador cuatro = new PuntajeUnJugador(400, "M");
		elJuego.agregarPuntaje(tres);
		elJuego.agregarPuntaje(cuatro);
		
		contar = elJuego.contarPuntajeUnJugador();
		assertTrue(contar == 4);
		
	}
	@Test
	public void darUltimoTest(){
		escenario1();
		assertNull(elJuego.getPrimero());
		
		PuntajeUnJugador uno = new PuntajeUnJugador(400, "Caicedo");
		elJuego.agregarPuntaje(uno);
		PuntajeUnJugador ultimo = elJuego.darUltimo();
		assertSame(ultimo, uno);
		
		PuntajeUnJugador dos = new PuntajeUnJugador(510, "Gallo");
		elJuego.agregarPuntaje(dos);
		PuntajeUnJugador tres = new PuntajeUnJugador(400, "M");
		PuntajeUnJugador cuatro = new PuntajeUnJugador(400, "N");
		elJuego.agregarPuntaje(tres);
		elJuego.agregarPuntaje(cuatro);
		
		ultimo = elJuego.darUltimo();
		assertSame(ultimo, cuatro);		
	}
	
	@Test
	public void arbolVacioTest(){
		escenario1();
		
		assertNull(elJuego.getRaizDosJugadores());
		assertTrue(elJuego.arbolVacio());
		
		PuntajeDosJugadores n = new PuntajeDosJugadores(456, "K");
		elJuego.insercion(n);
		
		assertNotNull(elJuego.getRaizDosJugadores());
		assertFalse(elJuego.arbolVacio());
	}
	
	@Test
	public void insercionTest(){
		escenario1();
		assertNull(elJuego.getRaizDosJugadores());
		PuntajeDosJugadores uno = new PuntajeDosJugadores(400, "Caicedo");
		PuntajeDosJugadores dos = new PuntajeDosJugadores(510, "Gallo");
		PuntajeDosJugadores tres = new PuntajeDosJugadores(150, "M");
		PuntajeDosJugadores cuatro = new PuntajeDosJugadores(400, "N");
		elJuego.insercion(uno);
		assertNotNull(elJuego.getRaizDosJugadores());
		elJuego.insercion(dos);
		elJuego.insercion(tres);
		assertNotNull(elJuego.getRaizDosJugadores().getDerecha());
		assertNotNull(elJuego.getRaizDosJugadores().getIzquierda());
		elJuego.insercion(cuatro);
		assertNotNull(elJuego.getRaizDosJugadores().getDerecha().getIzquierda());
	}
	@Test
	public void darPuntajeDosJugadores(){
		escenario1();
		PuntajeDosJugadores uno = new PuntajeDosJugadores(400, "Caicedo");
		PuntajeDosJugadores dos = new PuntajeDosJugadores(510, "Gallo");
		PuntajeDosJugadores tres = new PuntajeDosJugadores(150, "M");
		PuntajeDosJugadores cuatro = new PuntajeDosJugadores(400, "N");
		elJuego.insercion(uno);
		elJuego.insercion(dos);
		elJuego.insercion(tres);
		elJuego.insercion(cuatro);
		
		ArrayList<Puntaje> puntajes = elJuego.darPuntajesDosJugadores();
		
		assertTrue(puntajes.size() != 0);
		assertTrue(puntajes.get(0).compareTo(puntajes.get(1))<0); 
		assertTrue(puntajes.get(1).compareTo(puntajes.get(2))== 0);
		assertTrue(puntajes.get(2).compareTo(puntajes.get(3))<0); 
		
	}
	@Test
	public void buscarPuntajeDosJugadores(){
		this.escenario1();
		
		PuntajeDosJugadores uno = new PuntajeDosJugadores(400, "Caicedo");
		PuntajeDosJugadores dos = new PuntajeDosJugadores(510, "Gallo");
		PuntajeDosJugadores tres = new PuntajeDosJugadores(150, "M");
		PuntajeDosJugadores cuatro = new PuntajeDosJugadores(400, "N");
		elJuego.insercion(uno);
		elJuego.insercion(dos);
		elJuego.insercion(tres);
		elJuego.insercion(cuatro);
		try {
			String buscar=elJuego.buscarPuntajeDosJugadores("Caicedo");
			assertTrue(buscar.equals("Nombre  puntaje"+ "\n"+"Caicedo  400"+"\n"));
		} catch (Exception e) {
			fail();
		}
		try {
			@SuppressWarnings("unused")
			String buscar=elJuego.buscarPuntajeDosJugadores("Maria");
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void buscarPuntajeUnJugadores(){
        this.escenario1();
		
		PuntajeUnJugador uno = new PuntajeUnJugador(400, "Caicedo");
		PuntajeUnJugador dos = new PuntajeUnJugador(510, "Gallo");
		PuntajeUnJugador tres = new PuntajeUnJugador(150, "M");
		PuntajeUnJugador cuatro = new PuntajeUnJugador(400, "N");
		elJuego.agregarPuntaje(uno);
		elJuego.agregarPuntaje(dos);
		elJuego.agregarPuntaje(tres);
		elJuego.agregarPuntaje(cuatro);
		try {
			String buscar=elJuego.buscarPuntajeUnJugador("Caicedo");
			assertTrue(buscar.equals("Nombre  puntaje"+ "\n"+"Caicedo  400"+"\n"));
		} catch (Exception e) {
			fail();
		}
		try {
			@SuppressWarnings("unused")
			String buscar=elJuego.buscarPuntajeUnJugador("Maria");
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void buscarPuntajePrueba(){
        this.escenario1();
		PuntajeUnJugador uno = new PuntajeUnJugador(400, "Caicedo");
		PuntajeUnJugador dos = new PuntajeUnJugador(510, "Gallo");
		PuntajeUnJugador tres = new PuntajeUnJugador(150, "M");
		PuntajeUnJugador cuatro = new PuntajeUnJugador(400, "N");
		elJuego.agregarPuntaje(uno);
		elJuego.agregarPuntaje(dos);
		elJuego.agregarPuntaje(tres);
		elJuego.agregarPuntaje(cuatro);
		int buscado=elJuego.buscarPuntaje(elJuego.darPuntajesUnJugador(), "Gallo");
		assertTrue(buscado==1);
		buscado= elJuego.buscarPuntaje(elJuego.darPuntajesUnJugador(), "Maria");
		assertTrue(buscado==-1);
		PuntajeDosJugadores uno1 = new PuntajeDosJugadores(400, "Caicedo");
		PuntajeDosJugadores dos2 = new PuntajeDosJugadores(510, "Gallo");
		PuntajeDosJugadores tres3 = new PuntajeDosJugadores(150, "M");
		PuntajeDosJugadores cuatro4 = new PuntajeDosJugadores(400, "N");
		elJuego.insercion(uno1);
		elJuego.insercion(dos2);
		elJuego.insercion(tres3);
		elJuego.insercion(cuatro4);
		buscado= elJuego.buscarPuntaje(elJuego.darPuntajesDosJugadores(),"Caicedo");
		System.out.println(buscado);
		assertTrue(buscado==1);
	}
}