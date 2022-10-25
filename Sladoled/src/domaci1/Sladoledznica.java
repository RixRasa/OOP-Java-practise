package domaci1;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sladoledznica extends Frame {
	
	private Button DodajUkus;
	private AparatZaTocenje mesto= new AparatZaTocenje();
	
	public Sladoledznica(){
		setBounds(700, 200, 500, 400);
		setTitle("Sladoledznica");
		
		populateWindow();
		//pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// setVisible(false);
				dispose();
			}
		});
		
		
		setVisible(true);
	}
	
	void populateWindow() {
		Panel Centralni=new Panel();//Flow layout
		Panel Juzni= new Panel();//Flow layout
		
		//Juzni panel
		Label naziv=new Label("Naziv");//Naziv labela i text se dodaje
		naziv.setFont(new Font("Plain",Font.BOLD,20));
		Juzni.add(naziv);
		
		TextField nazivT= new TextField(10);
		Juzni.add(nazivT);
		
		
		Label boja=new Label("Boja");//Boja labela i text se dodaje
		boja.setFont(new Font("Plain",Font.BOLD,20));
		Juzni.add(boja);
		
		TextField bojaT= new TextField(10);
		Juzni.add(bojaT);
		
		
		DodajUkus= new Button("Dodaj ukus");
		Juzni.add(DodajUkus);
		DodajUkus.addActionListener((ae) -> {
			String text="#"+bojaT.getText();
			Ukus u= new Ukus(nazivT.getText(),Color.decode(text));
			try {
				Sladoledznica.this.mesto.DodajUkus(u);
			} catch (GVecPostoji e) {
				System.out.println("VEC POSTOJI UKUS");
			}
			
		});
	
		
		Juzni.setBackground(Color.CYAN);
		add(Juzni,BorderLayout.SOUTH);
		
		//Centralni panel
		//AparatZaTocenje mesto= new AparatZaTocenje();
		//Centralni.add(mesto);
		//Centralni.setBackground(Color.GRAY);
		add(mesto,BorderLayout.CENTER);
		
		
	}

	public static void main(String args[]) {
		new Sladoledznica();	
	}
}
