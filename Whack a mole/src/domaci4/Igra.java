package domaci4;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	
	
	Basta basta;
	Button Pokretanje;
	boolean pokrenuta;
	
	private Checkbox Lako;//Dodavanje checkbox
	private Checkbox Srednje;
	private Checkbox Tesko;
	
	private Label povrce;//Broj povrca
	private static Igra singlton=null;
	
	public Igra() {
		setBounds(700, 200, 500, 400);
		setVisible(true);
		this.pokrenuta=false;
		basta=new Basta(4,4,this);
		Pokretanje= new Button("Kreni");
		
		populateWindow();//Sredjivanje Frama
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(Igra.this.basta.threadB!=null) {
					if(Igra.this.basta.threadB!=null) {//Prvo zaustavljamo glvnu nit
						Igra.this.basta.threadB.interrupt();
						try {Igra.this.basta.threadB.join();} catch (InterruptedException e) {}
						Igra.this.basta.threadB=null;
					}
					basta.GasiOstaleNiti();//pa onda posle sporedene
				}
				dispose();
			}
		});
	}
	
	public static void SingltonIgra() {
		if(singlton==null) {
			singlton=new Igra();
		}
	}
	
	public synchronized void PostaviPovrce() {
		povrce.setText("Povrce" + basta.getPovrce());
		povrce.revalidate();
	}
	
	private void populateWindow() {
		//Paneli
		Panel Istocni= new Panel(new GridLayout(2, 1));
		Panel Opcije= new Panel(new GridLayout(5, 1));
		
		
		//Labele
		Label tesko= new Label("Tezina");
		tesko.setFont(new Font("plain", Font.BOLD, 22));
		tesko.setAlignment(Label.CENTER);
		povrce=new Label("Povrce:" + basta.getPovrce());
		povrce.setFont(new Font("plain", Font.BOLD, 22));
		
		
		//CheckBOX
		CheckboxGroup tezinaGroup = new CheckboxGroup();
		Lako = new Checkbox("Lako", true, tezinaGroup);
		Srednje = new Checkbox("Srednje", false, tezinaGroup);
		Tesko = new Checkbox("Tesko", false, tezinaGroup);
		
		
		//Dodati na panele
		Opcije.add(tesko);
		Opcije.add(Lako);
		Opcije.add(Srednje);
		Opcije.add(Tesko);
		Opcije.add(Pokretanje);
		Istocni.add(Opcije);
		Istocni.add(povrce);
		
		
		//Dodati na frame
		this.add(Istocni,BorderLayout.EAST);
		this.add(basta,BorderLayout.CENTER);
		revalidate();
		
		
		//POSTAVLJANJE LISTENERA
		Pokretanje.addActionListener(e -> {
			if (!pokrenuta) {
				Pokretanje.setLabel("Stani");
				Igra.this.Lako.setEnabled(false);
				Igra.this.Srednje.setEnabled(false);
				Igra.this.Tesko.setEnabled(false);
				
				basta.threadB=new Thread(basta);
				basta.threadB.start();
			} else {
				Pokretanje.setLabel("Kreni");
				Igra.this.Lako.setEnabled(true);
				Igra.this.Srednje.setEnabled(true);
				Igra.this.Tesko.setEnabled(true);
				
				if(Igra.this.basta.threadB!=null) {//Prvo zaustavljamo glvanu nit
					Igra.this.basta.threadB.interrupt();
					try {Igra.this.basta.threadB.join();} catch (InterruptedException m) {}
					Igra.this.basta.threadB=null;
				}
				
				basta.GasiOstaleNiti();//pa onda posle sporedene
				basta.setPovrce(100);
				PostaviPovrce();
			}
			pokrenuta=!pokrenuta;
		});
		
		Lako.addItemListener(e -> {
			if (Lako.getState()) {
				basta.setKoraci(10);
				basta.setIntervalCekanja(1000);
			}
		});
		Srednje.addItemListener(e -> {
			if (Srednje.getState()) {
				basta.setKoraci(8);
				basta.setIntervalCekanja(750);
			}
		});
		Tesko.addItemListener(e -> {
			if (Tesko.getState()) {
				basta.setKoraci(6);
				basta.setIntervalCekanja(500);
			}
		});
	}


	public static void main(String args[]) {
		Igra.SingltonIgra();
	}

}
