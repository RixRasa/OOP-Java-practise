package domaci3;

import java.awt.*;

public class Telefon extends Panel {
	
	Imenik i;
	Tastatura t;
	
	Button Kontakt= new Button("Dodaj Kontakt");
	Button Iskljuci= new Button("Iskljuci Telefon");
	
	Label TelefoonMoj;
	Broj b;
	
	int brojKliktanja=1;
	private Broj b1;
	private String text;
	
	public Broj getB() {
		return b;
	}
	public void setB(Broj b) {
		this.b = b;
	}

	public Telefon(Broj broj,Color c) {
		this.b=broj;
		this.setLayout(new BorderLayout());
		this.setBackground(c);
		this.setPreferredSize(new Dimension(244,244));
	
		//Severni panel za imenik i tastaturu
		Panel Sever= new Panel(new GridLayout(0, 1, 0, 0));
		t= new Tastatura();
		Sever.add(t);
		
		i= new Imenik();
		Sever.add(i);
		this.add(Sever,BorderLayout.NORTH);
		
		//Juzni panel za Dugmice i Broj telefona
		Panel DonjiDeo=new Panel(new GridLayout(2, 1, 0, 0));
		Panel Dugmici= new Panel(new GridLayout(1, 2, 0, 0));//Za dugmice
		
		Label TelefoonMoj= new Label();
		TelefoonMoj.setText(broj.toString());
		TelefoonMoj.setAlignment(Label.CENTER);
		
		Dugmici.add(Kontakt);
		Dugmici.add(Iskljuci);
		Iskljuci.setBackground(Color.LIGHT_GRAY);
		Kontakt.setBackground(Color.LIGHT_GRAY);
		
		DonjiDeo.add(Dugmici);
		DonjiDeo.add(TelefoonMoj);
		
		this.add(DonjiDeo,BorderLayout.SOUTH);

		//Action listeneri za dugmice
		Kontakt.addActionListener((ae) -> {
			if(brojKliktanja==1) {//Prvo uzimanje broja
				b1= new Broj(t.labelaBroja.getText());
				brojKliktanja=2;
			}
			else if(brojKliktanja==2) {//Pa uzimanje imena
				text= new String(t.labelaImena.getText());
				brojKliktanja=1;
				Kontakt k= new Kontakt(text,b1);
				t.labelaBroja.setText("");
				t.labelaImena.setText("");
				i.Add(k);
				i.revalidate();
				Sever.revalidate();
				//this.revalidate();
				t.thread.interrupt();//Gasi se nit koju koristimo
				try {t.thread.join();} catch (InterruptedException e) {}
			}
			t.cardLayout.next(t.southPanel);//menjamo panel
		});
		
		
		Iskljuci.addActionListener((ae) -> {
			if(Iskljuci.getBackground()==Color.RED) {
				Iskljuci.setBackground(Color.LIGHT_GRAY);
				t.thread.interrupt();//Gasi se nit koju koristimo
				try {t.thread.join();} catch (InterruptedException e) {}
			}
			else{Iskljuci.setBackground(Color.RED);}
		});
	}
	
	
	public static void main(String args[]) {

	}

}
