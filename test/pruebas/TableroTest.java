package pruebas;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import mundo.*;

public class TableroTest {
private Tablero tab;

  private void escenario1(){
	  tab = new Tablero();
  }
  private void escenario2(){
	  tab = new Tablero();
	  Casilla[][] escenario = new Casilla [3][3];
	  Casilla uno = new Casilla(Casilla.AGUAID);
	  escenario[0][0] = uno;
	  Casilla dos = new Casilla(Casilla.AGUILAID);
	  escenario[0][1] = dos;
	  Casilla tres = new Casilla(Casilla.ARBOLESID);
	  escenario[0][2] = tres;
	  Casilla cuatro = new Casilla (Casilla.BOTSID);
	  escenario[1][0] = cuatro;
	  Casilla cinco =  new Casilla (Casilla.CAMINOID);
	  escenario[1][1] = cinco;
	  Casilla seis = new Casilla(Casilla.JUGADOR1ID);
	  escenario[1][2] = seis;
	  Casilla siete = new Casilla(Casilla.JUGADOR2ID);
	  escenario[2][0] = siete;
	  Casilla ocho = new Casilla(Casilla.LADRILLOID);
	  escenario[2][1] = ocho;
	  Casilla nueve = new Casilla(Casilla.METALID);
	  escenario[2][2] = nueve;
	  tab.modificarEscenario(escenario);
	  
	  
  }
  private void escenario3(){
	  escenario2();
		 tab.asignarCoordenadasCasillas();
	  Jugador elTanque = new Jugador(87, 104,Tanque.ESTADO_ACTIVO,100, Orientacion.ORIENTACION_SUR, Jugador.ID_JUGADOR1);
      tab.modificarElTanque(elTanque);
  }
  private void escenario4(){
	 escenario3();

	  Jugador laTanque = new Jugador(0, 128,Tanque.ESTADO_ACTIVO,100, Orientacion.ORIENTACION_SUR, Jugador.ID_JUGADOR2);
      tab.modificarLaTanque(laTanque);
  }
  private void escenario5(){
	 escenario1();
    ArrayList<Bot> bots = new ArrayList <Bot>();
    Bot uno =  new Bot(10,10,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
    Bot dos =  new Bot(10,30,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
    Bot tres =  new Bot(109,21,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
    Bot cuatro =  new Bot(80,191,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
     bots.add(uno);
     bots.add(dos);
     bots.add(tres);
     bots.add(cuatro);
     tab.setBots(bots);
  }
  private void escenario6(){
	  escenario1();
	    ArrayList<Bot> bots = new ArrayList <Bot>();
	    Bot uno =  new Bot(10,10,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
	    Bot dos =  new Bot(10,30,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
	    Bot tres =  new Bot(109,21,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
	    Bot cuatro =  new Bot(80,191,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_ESTE,null);
	     bots.add(uno);
	     bots.add(dos);
	     bots.add(tres);
	     bots.add(cuatro);
	     tab.setBots(bots);
		  Jugador elTanque = new Jugador(87, 104,Tanque.ESTADO_ACTIVO,100, Orientacion.ORIENTACION_SUR, Jugador.ID_JUGADOR1);
	      tab.modificarElTanque(elTanque);
  }
	@Test
	public void asignarCoordenadasTest() {
		escenario2();
		int tamañoCasilla = 58;
		tab.asignarCoordenadasCasillas();
		assertTrue(tab.darTablero()[0][0].getX() == tamañoCasilla*0 && tab.darTablero()[0][0].getY() == tamañoCasilla*0);
		assertTrue(tab.darTablero()[0][1].getX() == tamañoCasilla*1 && tab.darTablero()[0][1].getY() == tamañoCasilla*0);
		assertTrue(tab.darTablero()[0][2].getX() == tamañoCasilla*2 && tab.darTablero()[0][2].getY() == tamañoCasilla*0);
		assertTrue(tab.darTablero()[1][0].getX() == tamañoCasilla*0 && tab.darTablero()[1][0].getY() == tamañoCasilla*1);
		assertTrue(tab.darTablero()[1][1].getX() == tamañoCasilla*1 && tab.darTablero()[1][1].getY() == tamañoCasilla*1);
		assertTrue(tab.darTablero()[1][2].getX() == tamañoCasilla*2 && tab.darTablero()[1][2].getY() == tamañoCasilla*1);
		assertTrue(tab.darTablero()[2][0].getX() == tamañoCasilla*0 && tab.darTablero()[2][0].getY() == tamañoCasilla*2);
		assertTrue(tab.darTablero()[2][1].getX() == tamañoCasilla*1 && tab.darTablero()[2][1].getY() == tamañoCasilla*2);
		assertTrue(tab.darTablero()[2][2].getX() == tamañoCasilla*2 && tab.darTablero()[2][2].getY() == tamañoCasilla*2);
	}
	
	@Test
	public void buscarCasillaEscenarioTest() throws NoExisteLaCasillaException{
		escenario2();
		tab.asignarCoordenadasCasillas();
		
		Casilla buscada = tab.buscarCasilla(20, 35);
		assertTrue(buscada.getTipo() == Casilla.AGUAID);
		
		buscada = tab.buscarCasilla(117, 87);
		assertTrue(buscada.getTipo() == Casilla.JUGADOR1ID);
	}
	
 @Test (expected = NoExisteLaCasillaException.class)
 public void buscarCasillaEscenarioTest2() throws NoExisteLaCasillaException{
	 escenario2();
	 tab.asignarCoordenadasCasillas();
	 
	 @SuppressWarnings("unused")
	Casilla buscada = tab.buscarCasilla(58*7, 17);
	 
	 fail();
	 
 }
 
 @Test
 public void buscarCasillaEnMatrizTest(){
	 escenario2();
	 tab.asignarCoordenadasCasillas();
	 
	 int[] coordenadas = tab.buscarCasillaEnMatriz(58*1, 58*2);
	 
	 Casilla buscada = tab.getEscenario()[coordenadas[0]][coordenadas[1]];
	 
	 assertTrue(buscada.getTipo() == Casilla.LADRILLOID);
 }
 @Test (expected = NullPointerException.class)
 public void buscarCasillaEnMatrizTest2(){
	 escenario2();
	 tab.asignarCoordenadasCasillas();
	 
	 int[] coordenadas = tab.buscarCasillaEnMatriz(58*3, 58*2);
	 
	 @SuppressWarnings("unused")
	Casilla buscada = tab.getEscenario()[coordenadas[0]][coordenadas[1]];
	 
	 fail();
 }
 @Test
 public void buscarElTanqueTest() throws NoExisteLaCasillaException{
	 escenario3();
	 Casilla laCasilla = tab.buscarElTanque();

	 
	 assertTrue(laCasilla.getTipo() == Casilla.CAMINOID);
 }
 @Test
 public void buscarLaTanqueTest() throws NoExisteLaCasillaException{
	 escenario4();
	 Casilla laCasilla = tab.buscarLaTanque();
	 assertTrue(laCasilla.getTipo() == Casilla.JUGADOR2ID);
 }
 
 @Test
 public void buscarElAguilaTest(){
	 escenario2();
	 tab.asignarCoordenadasCasillas();
	 Casilla laCasilla = tab.darElAguila();
	 assertTrue(laCasilla.getTipo() == Casilla.AGUILAID);
 }
 
 @Test
 public void cargarNivelTest() throws FileNotFoundException, IOException{
	 File f = new File("./niveles/Un Jugador/Nivel_2.txt");
	 escenario1();
	 tab.cargarNivel(f);
	 
	 assertTrue(tab.darTanque() != null);
	 assertTrue(tab.darTablero().length != 0 && tab.darTablero()[0].length != 0);
	 assertTrue(tab.getBots().size() != 0);
 }
 @Test 
 public void buscarBotTest() throws NoExisteBotException{
	 escenario5();
	  Bot cinco =  new Bot(60,191,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_OESTE,null);
	  Bot seis =  new Bot(230,191,Bot.ESTADO_ACTIVO,100,Orientacion.ORIENTACION_OESTE,null);
	  
	  tab.getBots().add(cinco);
	  
	  
	  int indice = tab.BuscarBot(cinco);
	  assertTrue (indice == 4);
	  assertSame(tab.getBots().get(indice), cinco);
	  
	  try{
		  indice = tab.BuscarBot(seis);
		  fail();
	  }catch(NoExisteBotException e){
		  assertTrue(true);
	  }
	  
 }
 @Test
 public void indiceBalaEnBotTest(){
	 escenario5();
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	 tab.getBots().get(1).generarBala(Bala.BALA_NORMAL);
	 tab.getBots().get(2).generarBala(Bala.BALA_NORMAL);
	 
	 Bala buscada = tab.getBots().get(0).getBalaNormal().get(0);
	 
	 int indiceBala = tab.indiceBalaEnBot(buscada);
	 
	 assertTrue(indiceBala == 0);
 }
 @Test
 public void buscarBalaEnBotsTest(){	 
	
	 escenario5();
 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
 tab.getBots().get(1).generarBala(Bala.BALA_NORMAL);
 tab.getBots().get(1).generarBala(Bala.BALA_NORMAL);
 tab.getBots().get(2).generarBala(Bala.BALA_NORMAL);
 
 Bala buscada = tab.getBots().get(1).getBalaNormal().get(1);
 
 int indiceBot = tab.indiceBalaEnBot(buscada);
 int indiceBala = tab.buscarBalaEnBot(indiceBot, buscada);
 
 assertTrue(indiceBala == 1);
 }
 
 @Test
 public void buscarBalaEnJugador1Test(){
	 escenario3();
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 
	 Bala buscada = tab.darTanque().getBalaNormal().get(2);
	 
	 int indiceBala = tab.BuscarBalaEnJugador1(buscada);
	 
	 assertTrue (indiceBala == 2);

 }
 @Test
 public void buscarBalaEnJugador2Test(){
	 escenario4();
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darLaTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darLaTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darLaTanque().generarBala(Bala.BALA_NORMAL);
	 
	 Bala buscada = tab.darLaTanque().getBalaNormal().get(0);
	 
	 int indiceBala = tab.BuscarBalaEnJugador2(buscada);
	 
	 assertTrue (indiceBala == 0);
	 
	 indiceBala = tab.BuscarBalaEnJugador1(buscada);
	 
	 assertTrue( indiceBala == -1);
 }
 @Test
 public void tocaBotTest(){
	 this.escenario6();
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	    Bala disparada= new Bala(10, 10, Bala.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Bala.BALA_NORMAL);
	    Bala disparada2= new Bala(200, 200, Bala.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Bala.BALA_NORMAL);
	    Bala disparada3= new Bala(10, 30, Bala.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Bala.BALA_NORMAL);
	    tab.darTanque().getBalaNormal().set(0, disparada);
	    tab.darTanque().getBalaNormal().set(1, disparada2);
	    tab.darTanque().getBalaNormal().set(2, disparada3);
		assertTrue(tab.TocaBot(disparada));
		assertFalse(tab.TocaBot(disparada2));
		assertTrue(tab.TocaBot(disparada3));
 }
 @Test
 public void tocaJugadorTest(){
	 this.escenario6();
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	    Bala disparada= new Bala(88, 104, Bala.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Bala.BALA_NORMAL);
	    Bala disparada2= new Bala(200, 200, Bala.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Bala.BALA_NORMAL);
	    Bala disparada3= new Bala(90, 100, Bala.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Bala.BALA_NORMAL);
	    tab.getBots().get(0).getBalaNormal().set(0, disparada);
	    tab.getBots().get(0).getBalaNormal().set(1, disparada2);
	    tab.getBots().get(0).getBalaNormal().set(2, disparada3);
	    
		assertTrue(tab.TocaJugador(disparada));
		assertFalse(tab.TocaJugador(disparada2));
		assertTrue(tab.TocaJugador(disparada3));
 }
 @Test
 public void hacerDanoBotTest(){
	 this.escenario6();
	 tab.setRecienPegado(0);
	 tab.darTanque().generarBala(Bala.BALA_NORMAL);
	 assertTrue(tab.getBots().get(0).getVida()==100);
	 tab.hacerDanoBot(tab.darTanque().getBalaNormal().get(0));
	 assertTrue(tab.getBots().get(0).getVida()==94);
	 tab.hacerDanoBot(tab.darTanque().getBalaNormal().get(0));
	 assertTrue(tab.getBots().get(0).getVida()==88);
	 tab.hacerDanoBot(tab.darTanque().getBalaNormal().get(0));
	 assertTrue(tab.getBots().get(0).getVida()==82);
	 tab.hacerDanoBot(tab.darTanque().getBalaNormal().get(0));
 }
 @Test
 public void hacerDanoJugadorTest(){
	 this.escenario6();
	 tab.setIdPegado(Jugador.ID_JUGADOR1);
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	 tab.getBots().get(0).generarBala(Bala.BALA_NORMAL);
	 assertTrue(tab.darTanque().getVida()==100);
	 tab.hacerDanoJugador(tab.getBots().get(0).getBalaNormal().get(0));
	 assertTrue(tab.darTanque().getVida()==94);
	 tab.hacerDanoJugador(tab.getBots().get(0).getBalaNormal().get(1));
	 assertTrue(tab.darTanque().getVida()==88);
	 tab.hacerDanoJugador(tab.getBots().get(0).getBalaNormal().get(2));
	 assertTrue(tab.darTanque().getVida()==82);
	 tab.hacerDanoJugador(tab.getBots().get(0).getBalaNormal().get(3));
	 assertTrue(tab.darTanque().getVida()==76);
 }
 
 @Test
 public void reinciarTanqueTest(){
	 escenario4();
	 
	 tab.darTanque().setVida(-300);
	 tab.darTanque().setEstado(Tanque.ESTADO_INACTIVO);
	 
	 tab.darLaTanque().setVida(-100000);
	 tab.darLaTanque().setEstado(Tanque.ESTADO_INACTIVO);
	 
	 tab.reinciarTanque(Jugador.ID_JUGADOR1);
	 assertTrue(tab.darTanque().getVida() == 100);
	 assertTrue(tab.darTanque().getEstado() == Tanque.ESTADO_ACTIVO);
	 
	 tab.reinciarTanque(Jugador.ID_JUGADOR2);
	 assertTrue(tab.darLaTanque().getVida() == 100);
	 assertTrue(tab.darLaTanque().getEstado() == Tanque.ESTADO_ACTIVO);
 }
 @Test
 public void reiniciarBotTest(){
	 escenario5();
	 tab.getBots().get(0).setEstado(Bot.ESTADO_INACTIVO);
	 tab.getBots().get(2).setEstado(Bot.ESTADO_INACTIVO);
	 tab.getBots().get(0).setVida(-1000);
	 tab.getBots().get(2).setVida(-600);
	 
	 
	 tab.reinciarBot(0);
	 assertTrue( tab.getBots().get(0).getEstado() == Bot.ESTADO_ACTIVO);
	 assertTrue(tab.getBots().get(0).getVida() == 100);
	 tab.reinciarBot(2);
	 assertTrue( tab.getBots().get(2).getEstado() == Bot.ESTADO_ACTIVO);
	 assertTrue( tab.getBots().get(2).getVida() == 100);
	 
	 
 }

}
