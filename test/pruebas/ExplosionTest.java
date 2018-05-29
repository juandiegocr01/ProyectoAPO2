package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Explosion;
import mundo.Orientacion;

public class ExplosionTest {

	private Explosion explosionNecesaria;
	
	public void escenario1(){
		explosionNecesaria= new Explosion(10, 10, Explosion.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Explosion.EXPLOSION_BALA);
	}
	public void escenario2(){
		explosionNecesaria= new Explosion(10, 10, Explosion.ESTADO_ACTIVO, Orientacion.ORIENTACION_ESTE, Explosion.EXPLOSION_TANQUE);
	}
	
	@Test
	public void avanceImagenTest() {
		this.escenario1();
		assertTrue(explosionNecesaria.getImagenActual()==1);
		assertTrue(explosionNecesaria.getRutaImagen().equals("./data/imgs/explosiones/"+Explosion.EXPLOSION_BALA+"/"+1+".png"));
		explosionNecesaria.avanceImagen();
		assertTrue(explosionNecesaria.getRutaImagen().equals("./data/imgs/explosiones/"+Explosion.EXPLOSION_BALA+"/"+2+".png"));
		assertTrue(explosionNecesaria.getImagenActual()==2);
		this.escenario2();
		assertTrue(explosionNecesaria.getImagenActual()==1);
		assertTrue(explosionNecesaria.getRutaImagen().equals("./data/imgs/explosiones/"+Explosion.EXPLOSION_TANQUE+"/"+1+".png"));
		explosionNecesaria.avanceImagen();
		assertTrue(explosionNecesaria.getRutaImagen().equals("./data/imgs/explosiones/"+Explosion.EXPLOSION_TANQUE+"/"+2+".png"));
		assertTrue(explosionNecesaria.getImagenActual()==2);
	}

}
