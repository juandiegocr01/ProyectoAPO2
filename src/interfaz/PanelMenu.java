package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelMenu extends JPanel implements ActionListener{
	
	public final static String UN_JUGADOR = "UN JUGADOR";
	public final static String MULTIJUGADOR = "MULTIJUGADOR";
	public final static String PUNTAJE = "PUNTAJE";
	public final static String INSTRUCCION = "INSTRUCCIÓN";
	
	
	private JLabel labImagen;
	private JButton btnUnJugador;
	private JButton btnMultijugador;
	private JButton btnPuntaje;
	private JButton btnInstruccion;
	private InterfazPrincipal principal;
	
	public PanelMenu(InterfazPrincipal p, String imagen){
		
		principal = p;
		
		labImagen = new JLabel();
		ImageIcon icono5 = new ImageIcon(imagen);
		labImagen.setIcon(icono5);
		
		btnUnJugador = new JButton();
		btnUnJugador.setActionCommand(UN_JUGADOR);
		btnUnJugador.addActionListener(this);
		ImageIcon icono = new ImageIcon("./imagenes/jugador.jpg");
		btnUnJugador.setIcon(icono);
		btnUnJugador.setBackground(Color.DARK_GRAY);
		
		
		btnMultijugador = new JButton();
		btnMultijugador.setActionCommand(MULTIJUGADOR);
		btnMultijugador.addActionListener(this);
		ImageIcon icono2 = new ImageIcon("./imagenes/multijugador1.jpg");
		btnMultijugador.setIcon(icono2);
		btnMultijugador.setBackground(Color.DARK_GRAY);
		
		
		btnPuntaje = new JButton();
		btnPuntaje.setActionCommand(PUNTAJE);
		btnPuntaje.addActionListener(this);
		ImageIcon icono4 = new ImageIcon("./imagenes/Puntaje1.jpg");
		btnPuntaje.setIcon(icono4);
		btnPuntaje.setBackground(Color.DARK_GRAY);
		
		
		btnInstruccion = new JButton();
		btnInstruccion.setActionCommand(INSTRUCCION);
		btnInstruccion.addActionListener(this);
		ImageIcon icono3 = new ImageIcon("./imagenes/instrucciones1.jpg");
		btnInstruccion.setIcon(icono3);
		btnInstruccion.setBackground(Color.DARK_GRAY);
		
		JPanel aux1 = new JPanel();
		aux1.setLayout(new GridLayout(1,1));
		aux1.setBackground(Color.BLACK);
		aux1.add(labImagen, BorderLayout.CENTER);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(5,1));
		aux.setBackground(Color.BLACK);
		aux.add(aux1);
		aux.add(btnUnJugador);
		aux.add(btnMultijugador);
		aux.add(btnPuntaje);
		aux.add(btnInstruccion);
		add(aux, BorderLayout.CENTER);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		
		if(comando.equals(UN_JUGADOR)){
			principal.cambiarAJuego();
		}
		if(comando.equals(MULTIJUGADOR)){
			principal.cambiarAMultijugador();
		}
		if(comando.equals(PUNTAJE)){
			principal.cambiarAPuntaje();
			
		}
		if(comando.equals(INSTRUCCION)){
			 principal.cambiarAInstrucciones();
		}
	}

}
