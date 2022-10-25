package domaci3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Imenik extends ListaStavki {
	ArrayList<Kontakt> Kontakti = new ArrayList<>();
	
	public Imenik() {
		this.setLayout(new GridLayout(0, 1, 0, 0));
	}
	
	public void Add(Component b) {
		if(b instanceof Kontakt) {
			Kontakt i= (Kontakt) b;
			this.add(i);
			Kontakti.add(i);
			//Stavke.add(i); Ako imamo ArrayList stavki
		}
	}
	
	public String DohvatiBroj(Broj b) throws GNePostoji {
		//Iterator<Kontakt> iter=Kontakti.iterator();
		int provera=0;
		for(Kontakt k:Kontakti) {
			if(k.broj.equals(b)) {
				provera=1;
				return k.ime;
			}
		}
		/*for(Stavka k:Stavke) {
			if(k.labelaT.getText().equals(b.toString())) {
				provera=1;
				return k.labelaN.getText();
			}
		}*/ //TAKODJE AKO JE ARRAYLIST STAVKI
		if(provera==0) {
			throw new GNePostoji();
		}
		return null;
	}
	
	public Broj DohvatiIme(String s) throws GNePostoji {
		Iterator<Kontakt> iter=Kontakti.iterator();
		int provera=0;
		for(Kontakt k:Kontakti) {
			if(k.ime.equals(s)) {
				provera=1;
				return k.broj;
			}
		}
		/*for(Stavka k:Stavke) {
			if(k.labelaN.getText().equals(s)) {
				provera=1;
				return k.labelaT.getText();
			}
		}*/ //TAKODJE AKO JE ARRAYLIST
		if(provera==0) {
			throw new GNePostoji();
		}
		return null;
	}
	
	
	
	

}
