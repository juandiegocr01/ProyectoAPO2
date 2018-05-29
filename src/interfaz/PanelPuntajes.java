package interfaz;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class PanelPuntajes extends JPanel implements ActionListener{
	
	public final static String MENU = "MENU";
	public final static String PUNTAJE_UN_JUGADOR = "PUNTAJE_UN_JUGADOR";
	public final static String PUNTAJE_DOS_JUGADORES = "PUNTAJE_DOS_JUGADORES";
	public final static String BUSCAR_PUNTAJE_DOS = "BUSCAR_PUNTAJE_DOS";
	public final static String BUSCAR_PUNTAJE_UNO = "BUSCAR_PUNTAJE_UNO";
	
	private InterfazPrincipal principal;
	private JTextArea txtAreaPuntaje;
	private JLabel labImagen;
	private JButton btnMenu;
	private JButton btnPuntaje;
	private JButton btnPuntajeDos;
	private JButton btnBuscarPuntaje;
	private JButton btnBuscarPuntaje2;
	
	public PanelPuntajes(InterfazPrincipal p, String imagen){
		
		principal = p;
		
		
		txtAreaPuntaje = new JTextArea();
		btnMenu = new JButton("Menú");
		btnMenu.addActionListener(this);
		btnMenu.setActionCommand(MENU);
		
		btnPuntaje = new JButton("Puntajes Un Jugador");
		btnPuntaje.addActionListener(this);
		btnPuntaje.setActionCommand(PUNTAJE_UN_JUGADOR);
		
		btnPuntajeDos = new JButton("Puntajes Dos Jugadores");
		btnPuntajeDos.addActionListener(this);
		btnPuntajeDos.setActionCommand(PUNTAJE_DOS_JUGADORES);
		
		btnBuscarPuntaje = new JButton("Buscar Puntaje Un Jugador");
		btnBuscarPuntaje.addActionListener(this);
		btnBuscarPuntaje.setActionCommand(BUSCAR_PUNTAJE_UNO);
		
		btnBuscarPuntaje2 = new JButton("Buscar Puntaje Dos Jugadores");
		btnBuscarPuntaje2.addActionListener(this);
		btnBuscarPuntaje2.setActionCommand(BUSCAR_PUNTAJE_DOS);
		
		labImagen = new JLabel();
		ImageIcon icono = new ImageIcon(imagen);
		labImagen.setBackground(Color.BLACK);
		labImagen.setIcon(icono);
		
		JPanel aux = new JPanel();
		
		aux.setLayout(new GridLayout(7,1));
		aux.setBackground(Color.BLACK);
		aux.add(labImagen);
		aux.add(txtAreaPuntaje);
		aux.add(btnPuntaje);
		aux.add(btnPuntajeDos);
		aux.add(btnBuscarPuntaje);
		aux.add(btnBuscarPuntaje2);
		aux.add(btnMenu);
		add(aux, BorderLayout.CENTER);
	}
	
	public void mostrarPuntaje(String puntaje){
		txtAreaPuntaje.setText(puntaje);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(MENU)){
			principal.volverAnterior();
		}
		else if(comando.equals(PUNTAJE_UN_JUGADOR)){
			principal.mostrarPuntajesUnJugador();
		}
		else if(comando.equals(PUNTAJE_DOS_JUGADORES)){
			principal.mostrarPuntajesDosJugadores();
		}
		else if(comando.equals(BUSCAR_PUNTAJE_UNO)){
			principal.buscarPuntajeUnJugador();
		}
		else if(comando.equals(BUSCAR_PUNTAJE_DOS)){
			principal.buscarPuntajeDosJugadores();
		}
	}

}
