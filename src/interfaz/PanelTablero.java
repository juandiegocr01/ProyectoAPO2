package interfaz;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import mundo.Bala;
import mundo.Casilla;
import mundo.Explosion;
import mundo.Jugador;
import mundo.NoExisteLaCasillaException;
import mundo.Orientacion;
import mundo.Tanque;

@SuppressWarnings("serial")
public class PanelTablero extends JPanel implements KeyListener{
	private ImageIcon[][] imagenes;
	private InterfazPrincipal principal;
	private Casilla baseArbol;
	public PanelTablero(InterfazPrincipal p){
		principal = p;
		Casilla [][] escenario =  p.darTablero().darTablero();
		imagenes =  new ImageIcon[escenario.length][escenario[0].length];
		setPreferredSize(new Dimension(escenario[0].length * 58,escenario.length*58));
		boolean encontro =  false;
		for(int i = 0; i < escenario.length; i++){
			for(int j = 0; j < escenario[0].length; j++){
				if(escenario[i][j].getTipo() == Casilla.ARBOLESID && !encontro){
					baseArbol = escenario[i][j];
					encontro = true;
				}
				imagenes[i][j] = new ImageIcon(escenario[i][j].getRuta());
			}
		}
		
		addKeyListener(this );
		
		
	}
	
	public void paintComponent(Graphics g){
		int x = 0;
		int y = 0;
		for(int i = 0; i < imagenes.length; i++){
			x= 0;
			if(i!= 0){
				y+= 58;	
			}
			for(int j  = 0; j < imagenes[0].length; j++){
				if(baseArbol != null && imagenes[i][j].getDescription().equals(baseArbol.getRuta())){
					x+= 58;
				}
				else{
					g.drawImage(imagenes[i][j].getImage(), x, y, null);
					x+= 58;	
				}
			}
		}
		
		if( principal.darTanque().getEstado()== Orientacion.ESTADO_ACTIVO){
			if(principal.darTanque().getExplosion()!=null && principal.darTanque().getExplosion().getEstado()!= Explosion.ESTADO_INACTIVO){
			ImageIcon img3 = new ImageIcon(principal.darTanque().getExplosion().getRutaImagen());
			g.drawImage(img3.getImage(),principal.darTanque().getExplosion().getX()-40,principal.darTanque().getExplosion().getY()-60, null);
			}
			else {
		ImageIcon img5 = new ImageIcon(principal.darTanque().getRutaImagen());
		g.drawImage(img5.getImage(),principal.darTanque().getX(),principal.darTanque().getY(), null);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(principal.darTanque().getX(), principal.darTanque().getY() -10,  30, 4);
		
		g.setColor(Color.GREEN);
		g.fillRect(principal.darTanque().getX(), principal.darTanque().getY() -10,  principal.darTanque().conversionVida(), 4);
		
		if(principal.darTanque().getBalaNormal().size()!= 0 ){
			for (int i = 0; i < principal.darTanque().getBalaNormal().size(); i++) {
				if(principal.darTanque().getBalaNormal().get(i).getEstado()== Bala.ESTADO_ACTIVO){
					ImageIcon img2 = new ImageIcon(principal.darTanque().getBalaNormal().get(i).getRutaImagen());
					g.drawImage(img2.getImage(),principal.darTanque().getBalaNormal().get(i).getX(),principal.darTanque().getBalaNormal().get(i).getY(), null);
					setOpaque(false);
					}
					Boolean estadoBala= principal.darTanque().getBalaNormal().get(i).getEstado();
					if(estadoBala== Bala.ESTADO_INACTIVO){
						Explosion explo= principal.darTanque().getBalaNormal().get(i).getExplosion();
						if( explo!=null){
						ImageIcon img3 = new ImageIcon(explo.getRutaImagen());
						g.drawImage(img3.getImage(),explo.getX(),explo.getY(), null);
						setOpaque(false);
						}
					}
			}
		}
		if( principal.darTanque().getBalaEspecial()!= null){
			if(principal.darTanque().getBalaEspecial().getEstado()== Bala.ESTADO_ACTIVO){
				ImageIcon img2 = new ImageIcon(principal.darTanque().getBalaEspecial().getRutaImagen());
				g.drawImage(img2.getImage(),principal.darTanque().getBalaEspecial().getX(),principal.darTanque().getBalaEspecial().getY(), null);
				setOpaque(false);
				}
				Boolean estadoBala= principal.darTanque().getBalaEspecial().getEstado();
				if(estadoBala== Bala.ESTADO_INACTIVO){
					Explosion explo= principal.darTanque().getBalaEspecial().getExplosion();
					if( explo!=null){
					ImageIcon img3 = new ImageIcon(explo.getRutaImagen());
					g.drawImage(img3.getImage(),explo.getX(),explo.getY(), null);
					setOpaque(false);
					}
				}
		}
			}
		}
		else {
			principal.generarExplosionJugador(Jugador.ID_JUGADOR1);
     		principal.reiniciarElTanque(Jugador.ID_JUGADOR1);
		}
		//La Tanque
		if (principal.darLaTanque()!=null){
		if( principal.darLaTanque().getEstado()== Orientacion.ESTADO_ACTIVO){
			if(principal.darLaTanque().getExplosion()!=null && principal.darLaTanque().getExplosion().getEstado()!= Explosion.ESTADO_INACTIVO){
			ImageIcon img3 = new ImageIcon(principal.darLaTanque().getExplosion().getRutaImagen());
			g.drawImage(img3.getImage(),principal.darLaTanque().getExplosion().getX()-40,principal.darLaTanque().getExplosion().getY()-60, null);
			}
			else {
		ImageIcon img5 = new ImageIcon(principal.darLaTanque().getRutaImagen());
		g.drawImage(img5.getImage(),principal.darLaTanque().getX(),principal.darLaTanque().getY(), null);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(principal.darLaTanque().getX(), principal.darLaTanque().getY() -10,  30, 4);
		
		g.setColor(Color.GREEN);
		g.fillRect(principal.darLaTanque().getX(), principal.darLaTanque().getY() -10,  principal.darLaTanque().conversionVida(), 4);
		
		x=0;
		y=0;

		if(principal.darLaTanque().getBalaNormal().size()!= 0 ){
			for (int i = 0; i < principal.darLaTanque().getBalaNormal().size(); i++) {
				if(principal.darLaTanque().getBalaNormal().get(i).getEstado()== Bala.ESTADO_ACTIVO){
					ImageIcon img2 = new ImageIcon(principal.darLaTanque().getBalaNormal().get(i).getRutaImagen());
					g.drawImage(img2.getImage(),principal.darLaTanque().getBalaNormal().get(i).getX(),principal.darLaTanque().getBalaNormal().get(i).getY(), null);
					setOpaque(false);
					}
					Boolean estadoBala= principal.darLaTanque().getBalaNormal().get(i).getEstado();
					if(estadoBala== Bala.ESTADO_INACTIVO){
						Explosion explo= principal.darLaTanque().getBalaNormal().get(i).getExplosion();
						if( explo!=null){
						ImageIcon img3 = new ImageIcon(explo.getRutaImagen());
						g.drawImage(img3.getImage(),explo.getX(),explo.getY(), null);
						setOpaque(false);
						}
					}
			}
		}
		}
		}
		else {
			principal.generarExplosionJugador(Jugador.ID_JUGADOR2);
     		principal.reiniciarElTanque(Jugador.ID_JUGADOR2);
		}
		}
		//
		for (int i = 0; i < principal.darBots().size(); i++) {
			if( principal.darBots().get(i).getEstado()== Orientacion.ESTADO_ACTIVO){
			if(principal.darBots().get(i).getExplosion()!=null && principal.darBots().get(i).getExplosion().getEstado()!= Explosion.ESTADO_INACTIVO){	
			ImageIcon img6 = new ImageIcon(principal.darBots().get(i).getExplosion().getRutaImagen());
			g.drawImage(img6.getImage(),principal.darBots().get(i).getExplosion().getX()-40,principal.darBots().get(i).getExplosion().getY()-60, null);
			}
			else{
				if( principal.darBots().get(i).getVidas()>0 ){
			ImageIcon img3 = new ImageIcon(principal.darBots().get(i).getRutaImagen());
			g.drawImage(img3.getImage(),principal.darBots().get(i).getX(),principal.darBots().get(i).getY(), null);
			setOpaque(false);
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(principal.darBots().get(i).getX(), principal.darBots().get(i).getY() -10,  30, 4);
			
			g.setColor(Color.GREEN);
			g.fillRect(principal.darBots().get(i).getX(), principal.darBots().get(i).getY() -10,  principal.darBots().get(i).conversionVida(), 4);
			
			if(principal.darBots().get(i).getBalaNormal().size()!= 0 ){
				for (int j = 0; j < principal.darBots().get(i).getBalaNormal().size(); j++) {
					if(principal.darBots().get(i).getBalaNormal().get(j).getEstado()== Bala.ESTADO_ACTIVO){
						ImageIcon img2 = new ImageIcon(principal.darBots().get(i).getBalaNormal().get(j).getRutaImagen());
						g.drawImage(img2.getImage(),principal.darBots().get(i).getBalaNormal().get(j).getX(),principal.darBots().get(i).getBalaNormal().get(j).getY(), null);
						setOpaque(false);
						}
						Boolean estadoBala= principal.darBots().get(i).getBalaNormal().get(j).getEstado();
						if(estadoBala== Bala.ESTADO_INACTIVO){
							Explosion explo= principal.darBots().get(i).getBalaNormal().get(j).getExplosion();
							if( explo!=null){
							ImageIcon img4 = new ImageIcon(explo.getRutaImagen());
							g.drawImage(img4.getImage(),explo.getX(),explo.getY(), null);
							setOpaque(false);
							}
						}
				}
			}
			}
			}
			}
			else {
				principal.generarExplosionBot(i);
				principal.reinciarBot(i);
			}
		}
		x=0;
		y=0;
		for(int i = 0; i < imagenes.length; i++){
			x= 0;
			if(i!= 0){
				y+= 58;	
			}
			for(int j  = 0; j < imagenes[0].length; j++){
				if(baseArbol != null && imagenes[i][j].getDescription().equals(baseArbol.getRuta())){
					g.drawImage(imagenes[i][j].getImage(), x, y, null);
					x+= 58;
				}
				else{
					x+= 58;	
				}
			}
		}
		setOpaque(false);
		revalidate();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Casilla siguiente = null;
				                                             
		int code=e.getKeyCode();
		if(principal.darTanque().getEstado()== Tanque.ESTADO_ACTIVO){
		if (KeyEvent.VK_A==code){
			principal.darTanque().setOrientacion(Tanque.ORIENTACION_OESTE);
			try {
				siguiente = principal.darTablero().buscarCasilla(principal.darTanque().getX() - 30, principal.darTanque().getY()+14);
				if(principal.darTanque().getX()  >= 0){
					if(siguiente.isCamino() != true){
					  if(siguiente.coordenadaXFinal() <= principal.darTanque().getX()  ){
								principal.darTanque().avanceAdelante();
							}
						
					}else{		
						principal.darTanque().avanceAdelante();
					}
				}
			} catch (NoExisteLaCasillaException e1) {
			}
			
		}
		else if(KeyEvent.VK_W== code){
			principal.darTanque().setOrientacion(Tanque.ORIENTACION_NORTE);
			try {
				siguiente = principal.darTablero().buscarCasilla(principal.darTanque().getX() + 13, principal.darTanque().getY() - 34);
				if(principal.darTanque().getY() >= 0){
					if(siguiente.isCamino() != true){
						if(siguiente.coordenadaYFinal() < principal.darTanque().getY()){
							principal.darTanque().avanceAdelante();
						}
					}
				   else{		
					      principal.darTanque().avanceAdelante();
				       }
				}
			} catch (NoExisteLaCasillaException e1) {
			}
			
		}	
		else if(KeyEvent.VK_S== code){
			principal.darTanque().setOrientacion(Tanque.ORIENTACION_SUR);
			try {
				siguiente = principal.darTablero().buscarCasilla(principal.darTanque().getX() +13, principal.darTanque().getY() + 34);
				if(principal.darTanque().getYFinal() <= darLimitesAlto()){
					if (siguiente!= null){
					if(siguiente.isCamino() != true){
						if(siguiente.getY() > principal.darTanque().getYFinal()){
							principal.darTanque().avanceAdelante();
						}
					}
				   else{		
					      principal.darTanque().avanceAdelante();
				       }
					}
				}
			} catch (NoExisteLaCasillaException e1) {
			}
			
		}
		else if(KeyEvent.VK_D== code){
			principal.darTanque().setOrientacion(Tanque.ORIENTACION_ESTE);
			try {
				siguiente = principal.darTablero().buscarCasilla(principal.darTanque().getX() + 30, principal.darTanque().getY()+14);
				if(principal.darTanque().getXFinal()  <= darLimitesAncho()){
					if(siguiente.isCamino() != true){
					  if(siguiente.coordenadaXFinal() <= principal.darTanque().getX()  ){
								principal.darTanque().avanceAdelante();
							}
						
					}else{		
						principal.darTanque().avanceAdelante();
					}
				}
			} catch (NoExisteLaCasillaException e1) {
			}
			
		}
		else if(KeyEvent.VK_SPACE== code){
			principal.generarBala(Jugador.ID_JUGADOR1, Bala.BALA_NORMAL);
		}
		
		}
		// La tanque
		if(principal.darLaTanque()!= null){
		if(principal.darLaTanque().getEstado()== Tanque.ESTADO_ACTIVO){
			if (KeyEvent.VK_LEFT==code){
				principal.darLaTanque().setOrientacion(Tanque.ORIENTACION_OESTE);
				try {
					siguiente = principal.darTablero().buscarCasilla(principal.darLaTanque().getX() - 30, principal.darLaTanque().getY()+14);
					if(principal.darLaTanque().getX()  >= 0){
						if(siguiente.isCamino() != true){
						  if(siguiente.coordenadaXFinal() <= principal.darLaTanque().getX()  ){
									principal.darLaTanque().avanceAdelante();
								}
							
						}else{		
							principal.darLaTanque().avanceAdelante();
						}
					}
				} catch (NoExisteLaCasillaException e1) {
				}
				
			}
			else if(KeyEvent.VK_UP== code){
				principal.darLaTanque().setOrientacion(Tanque.ORIENTACION_NORTE);
				try {
					siguiente = principal.darTablero().buscarCasilla(principal.darLaTanque().getX() + 13, principal.darLaTanque().getY() - 34);
					if(principal.darLaTanque().getY() >= 0){
						if(siguiente.isCamino() != true){
							if(siguiente.coordenadaYFinal() < principal.darLaTanque().getY()){
								principal.darLaTanque().avanceAdelante();
							}
						}
					   else{		
						      principal.darLaTanque().avanceAdelante();
					       }
					}
				} catch (NoExisteLaCasillaException e1) {
				}
				
			}	
			else if(KeyEvent.VK_DOWN== code){
				principal.darLaTanque().setOrientacion(Tanque.ORIENTACION_SUR);
				try {
					siguiente = principal.darTablero().buscarCasilla(principal.darLaTanque().getX() +13, principal.darLaTanque().getY() + 34);
					if(principal.darLaTanque().getYFinal() <= darLimitesAlto()){
						if( siguiente != null){
						if(siguiente.isCamino() != true){
							if(siguiente.getY() > principal.darLaTanque().getYFinal()){
								principal.darLaTanque().avanceAdelante();
							}
						}
					   else{		
						      principal.darLaTanque().avanceAdelante();
					       }
					}
					}
				} catch (NoExisteLaCasillaException e1) {
				}
				
		
			}
			else if(KeyEvent.VK_RIGHT== code){
				principal.darLaTanque().setOrientacion(Tanque.ORIENTACION_ESTE);
				try {
					siguiente = principal.darTablero().buscarCasilla(principal.darLaTanque().getX() + 30, principal.darLaTanque().getY()+14);
					if(principal.darLaTanque().getXFinal()  <= darLimitesAncho()){
						if(siguiente.isCamino() != true){
						  if(siguiente.coordenadaXFinal() <= principal.darLaTanque().getX()  ){
									principal.darLaTanque().avanceAdelante();
								}
							
						}else{		
							principal.darLaTanque().avanceAdelante();
						}
					}
				} catch (NoExisteLaCasillaException e1) {
				}

			}
			else if(KeyEvent.VK_ENTER== code){
				principal.generarBala(Jugador.ID_JUGADOR2, Bala.BALA_NORMAL);
			}
			}
		}
	}
	
	public int darLimitesAncho(){
		return this.getWidth();
	}
	public int darLimitesAlto(){
		return this.getHeight();
	}
	
	public ImageIcon[][] getImagenes(){
		return imagenes;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
