package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Casilla;

public class CasillaTest {
 private Casilla laCasilla;
 
 private void escenarioCasillaCamino(){
	 laCasilla = new Casilla(Casilla.CAMINOID);
	 laCasilla.setX(20);
	 laCasilla.setY(37);
 }
 private void escenarioCasillaLadrillo(){
	 laCasilla = new Casilla(Casilla.LADRILLOID);
	 laCasilla.setX(154);
	 laCasilla.setY(81);
 }
 private void escenarioCasillaAguila(){
	 laCasilla = new Casilla(Casilla.AGUILAID);
	 laCasilla.setX(107);
	 laCasilla.setY(9);
 }
	@Test
	public void coordenadaFinalXTest() {
		escenarioCasillaCamino();
		int xFinal = laCasilla.coordenadaXFinal();
		assertTrue (xFinal == 78);
		
		escenarioCasillaLadrillo();
		xFinal = laCasilla.coordenadaXFinal();
		assertTrue(xFinal == 212);
		
		escenarioCasillaAguila();
		xFinal = laCasilla.coordenadaXFinal();
		assertTrue(xFinal == 165);
	}
	@Test
	public void coordenadaFinalYTest() {
		escenarioCasillaCamino();
		int yFinal = laCasilla.coordenadaYFinal();
		assertTrue (yFinal == 95);
		
		escenarioCasillaLadrillo();
		yFinal = laCasilla.coordenadaYFinal();
		assertTrue(yFinal == 139);
		
		escenarioCasillaAguila();
		yFinal = laCasilla.coordenadaYFinal();
		assertTrue(yFinal == 67);
	}
	@Test
	public void colisionLadrilloTest(){
		escenarioCasillaLadrillo();
		laCasilla.colisionLadrillo();
		assertTrue(laCasilla.getIdentificador() == Casilla.CAMINO);
		assertTrue(laCasilla.getTipo() == Casilla.CAMINOID);
		assertTrue(laCasilla.isCamino() == true);
		assertTrue(laCasilla.getRuta().equals("./data/" + "imgs/" + "casillas/"+ Casilla.CAMINO +".jpg"));
	}
	
	@Test
	public void colisionAguilaTest(){
		escenarioCasillaAguila();
		laCasilla.colisionAguila();
		assertTrue(laCasilla.getIdentificador() == Casilla.METAL);
		assertTrue(laCasilla.getTipo() == Casilla.AGUILADESTRUIDAID);
		assertTrue(laCasilla.isCamino() == false);
		assertTrue(laCasilla.getRuta().equals("./data/" + "imgs/" + "casillas/"+ Casilla.METAL +".jpg"));
	}
	
	

}
