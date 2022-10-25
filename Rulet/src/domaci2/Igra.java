package domaci2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import domaci2.Polje.Status;



public class Igra extends Frame {
	
	private Generator Randomcina=new Generator();
	private Mreza mreza;
	private Panel rightPanel;
	private Panel southPanel;
	private Button igraj = new Button("Igraj");
	private Label dobitak;
	private Label kvota=new Label("5");
	private Label balans=new Label("0");
	private TextField ulog= new TextField("100");
	
	public void populateWindow() { 
		//Postavljanje dobitka tj pocetnu vrednost dobitka
		double dobitakica=Integer.parseInt(ulog.getText())*Double.parseDouble(kvota.getText());
		dobitak= new Label((Double. toString(dobitakica)));
		dobitak.revalidate();
		
		//pravljenje ISTOCNOG(DESNOG) PANELA gde cemo staviti sve labele i dugme
		rightPanel=new Panel(new GridLayout(0,1,0,0));
		Mreza mreza= new Mreza(4,5,this);//Stavljamo Mrezi dimenzije
		
		//Pravljenje Panela balans u flowLayout kako bi dodao tekst i labelu balans
		Panel Balans= new Panel();
		Balans.add(new Label("Balans: "));
		Balans.add(balans);
		rightPanel.add(Balans);
		
		//Pravljenje Panela Ulog u flowLayout kako bi dodao tekst i labelu ulog
		Panel Ulog= new Panel();
		Ulog.add(new Label("Ulog: "));
		Ulog.add(ulog);
		rightPanel.add(Ulog);
		
		//Pravljenje Panela Kvota u flowLayout kako bi dodao tekst i labelu kvota
		Panel Kvota= new Panel();
		Kvota.add(new Label("Kvota: "));
		Kvota.add(kvota);
		rightPanel.add(Kvota);
		
		//Pravljenje Panela Dobitak u flowLayout kako bi dodao tekst i labelu Dobitak
		Panel Dobitak=new Panel();
		Dobitak.add(new Label("Dobitak: "));
		Dobitak.add(dobitak);
		rightPanel.add(Dobitak);
		
		//Pravljenje Panela Igra u flowLayout kako bi dodao dugme Igra
		Panel Igraj=new Panel();
		Igraj.add(igraj);
		rightPanel.add(Igraj);
		
		rightPanel.setBackground(Color.LIGHT_GRAY);
		this.add(rightPanel,BorderLayout.EAST);
		
		//Pravljenje JUZNOG PANELA
		southPanel=new Panel();
		Label statusBar=new Label();
		southPanel.add(statusBar);
		southPanel.setBackground(Color.GRAY);
		
		this.add(southPanel,BorderLayout.SOUTH);
		
		//Adujemo mrezu u centar
		this.add(mreza,BorderLayout.CENTER);
		
		
		//ACTION listeneri za dugmice i text field
		igraj.addActionListener((ae) -> {
			//Generisanje random broja
			int gornja=mreza.getRed()*mreza.getKolona()-1;
			int randomBr=Randomcina.GetRandom(0, gornja);
			
			if(mreza.getSet().contains(randomBr)) {
				southPanel.setBackground(Color.GREEN);//Postavljamo status bar na zeleno
				double dobitakIulog=Double.parseDouble(dobitak.getText())+Double.parseDouble(balans.getText());//Sabiramo dobitak i ulog
				balans.setText(Double. toString(dobitakIulog));
				balans.revalidate();
			}
			else {
				southPanel.setBackground(Color.RED);//Postavljamo bar na crveno
				double izgubljeno=Double.parseDouble(balans.getText())-Integer.parseInt(ulog.getText());//Racunamo koliko bi trebalo novi balans da bude
				balans.setText(Double. toString(izgubljeno));
				balans.revalidate();
				
			}
			statusBar.setText(Integer. toString(randomBr));
			statusBar.revalidate();
		});
		
		ulog.addTextListener((te) -> {
			int ulogica=Integer.parseInt(ulog.getText());//uzimanje trneutnog uloga
			double kvotica=Double.parseDouble(kvota.getText());//uzimanje trenutne kvota
			double dobitak1=ulogica*kvotica;
			dobitak.setText(Double. toString(dobitak1));//menjanje dobitka 
			
		});
		
		
		
		
	}
	
	
	
	public Mreza getMreza() {
		return mreza;
	}
	public void setMreza(Mreza mreza) {
		this.mreza = mreza;
	}
	public Label getDobitak() {
		return dobitak;
	}
	public Label getKvota() {
		return kvota;
	}
	public TextField getUlog() {
		return ulog;
	}


	public Igra() {
		
		setResizable(true);
		setBounds(700,200,400,400);
		setTitle("Igra");
		populateWindow();
		pack();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}	
		});
		setVisible(true);
	}


	public static void main(String args[]) {
		new Igra();
	}
}
