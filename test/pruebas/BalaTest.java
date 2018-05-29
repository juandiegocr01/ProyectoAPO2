package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Bala;
import mundo.Explosion;
import mundo.Orientacion;

public class BalaTest {
	private Bala balaNecesaria;
	
	public void escenario1(){
		balaNecesaria= new Bala(10, 10, Bala.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Bala.BALA_NORMAL);
	}
	
	@Test
	public void generarExplosionTest() {
		this.escenario1();
		assertNull(balaNecesaria.getExplosion());
		balaNecesaria.generarExplosion();
		assertTrue(balaNecesaria.getExplosion()!=null);
		Explosion generada=balaNecesaria.getExplosion();
		assertTrue(generada.getX()== balaNecesaria.getX());
		assertTrue(generada.getY()== balaNecesaria.getY());
	}
	@Test
	public void avanceImagenTest(){
		this.escenario1();
		assertTrue(balaNecesaria.getRutaImagen().equals("./data/imgs/balas/"+balaNecesaria.getTipoBala()+"/"+balaNecesaria.getTipoBala()+"_"+1+".png"));
		assertTrue(balaNecesaria.getImagenActual()==1);
		balaNecesaria.avanceImagen();
		assertTrue(balaNecesaria.getRutaImagen().equals("./data/imgs/balas/"+balaNecesaria.getTipoBala()+"/"+balaNecesaria.getTipoBala()+"_"+2+".png"));
		assertTrue(balaNecesaria.getImagenActual()==2);
		balaNecesaria.avanceImagen();
		assertTrue(balaNecesaria.getRutaImagen().equals("./data/imgs/balas/"+balaNecesaria.getTipoBala()+"/"+balaNecesaria.getTipoBala()+"_"+3+".png"));
		assertTrue(balaNecesaria.getImagenActual()==3);
		balaNecesaria.avanceImagen();
		assertTrue(balaNecesaria.getRutaImagen().equals("./data/imgs/balas/"+balaNecesaria.getTipoBala()+"/"+balaNecesaria.getTipoBala()+"_"+1+".png"));
		assertTrue(balaNecesaria.getImagenActual()==1);	
	}
	@Test
	public void avanceAdelanteTest(){
		this.escenario1();
		assertTrue(balaNecesaria.getOrientacion().equals(Orientacion.ORIENTACION_ESTE));
		assertTrue(balaNecesaria.getX()==10);
		assertTrue(balaNecesaria.getY()==10);
		balaNecesaria.avanceAdelante();
		assertTrue(balaNecesaria.getX()==16 );
		assertTrue(balaNecesaria.getY()==10 );
	}
	@Test
	public void calcularFinalesTest(){
		this.escenario1();
		assertTrue(balaNecesaria.getOrientacion().equals(Orientacion.ORIENTACION_ESTE));
		assertTrue(balaNecesaria.getX()==10);
		assertTrue(balaNecesaria.getY()==10);
		balaNecesaria.avanceAdelante();
		assertTrue(balaNecesaria.getX()==16 );
		assertTrue(balaNecesaria.getY()==10 );
		assertTrue(balaNecesaria.getXFinal()==46);
		assertTrue(balaNecesaria.getYFinal()==40);	
	}
}
