package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MenuSalida extends JMenu implements ActionListener {
	
	public final static String SALIR = "SALIR";
	
	private InterfazPrincipal principal;
	private JMenuItem itmSalir;
	
	public MenuSalida(InterfazPrincipal p){
		
		super("Salir");
		principal = p;
		
		itmSalir = new JMenuItem("Salir");
		itmSalir.addActionListener(this);
		itmSalir.setActionCommand(SALIR);
		
		add(itmSalir);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		if(comando.equals(SALIR)){
			principal.salir();
		}
		
	}

}
