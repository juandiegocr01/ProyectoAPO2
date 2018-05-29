package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelInstruccion extends JPanel implements ActionListener{
	
	public final static String MENU = "menu";
	
	private InterfazPrincipal principal;
	private JButton btnMenu;
	private JTextArea txtAreaInstrucciones;
	private JLabel labImagen;
	
	//INSTRUCCIONES.png
	
	public PanelInstruccion(InterfazPrincipal p, String imagen){
		
		principal = p;
		
		labImagen = new JLabel();
		ImageIcon icono = new ImageIcon(imagen);
		labImagen.setIcon(icono);
		
		txtAreaInstrucciones = new JTextArea("Este juego consiste en entretener a las personas aventurandose " +"\n"+"en el mundo de los tanques de guerra , saquele el mayor gusto posible ya que es de nuestro agrado divertir al usuario"
				+"\n"+" En cuanto a intrucciones son faciles , el movimiento del tanque verde que corresponde al Jugador1 es :"+
				"\n"+ "Arriba=W, Izquierda=A, Abajo=S, Derecha=D, Disparo=SPACE"+"\n"+"al igual que los del Jugador2 correspondiente al tanque de color rojo son :"+"\n"+
				"Arriba=Flecha Arriba, Izquierda=Fecha Izquierda, Abajo=Fecha Abajo, Derecha=Fecha Derecha, Disparo=ENTER");
		btnMenu = new JButton ("Menu");
		btnMenu.addActionListener(this);
		btnMenu.setActionCommand(MENU);
		
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(3,1));
		aux.setBackground(Color.BLACK);
		aux.add(labImagen);
		aux.add(txtAreaInstrucciones);
		aux.add(btnMenu);
		add(aux, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		
		if(comando.equals(MENU)){
			principal.volverAnterior();
		}
		
	}

	
}
