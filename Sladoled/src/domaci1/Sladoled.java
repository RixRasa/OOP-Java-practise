	package domaci1;

import java.util.ArrayList;

public class Sladoled {
	
	int velicina;
	ArrayList<UkusMl> Ukusi= new ArrayList<>();
	
	
	public Sladoled(int velicina) {
		this.velicina=velicina;
	}
	
	public void Dodaj(int kolicinaU,Ukus u) {
		//Trazimo koliko stavljamo da li dopunjujemo da vrha ili sve
		int trenutnaKol=0;
		int StaviCemo=0;
		for(UkusMl ukusi:Ukusi) {
			trenutnaKol+=ukusi.kolicina;
		}
		if(trenutnaKol+kolicinaU>velicina) {
			StaviCemo=velicina-trenutnaKol;
		}
		else {
			StaviCemo=kolicinaU;
		}
		//Trazimo da li vec ima tog ukusa ako ga ima povecavamo velicinu
		for(UkusMl ukusi:Ukusi) {
			if(ukusi.ukus.equals(u)) {
				ukusi.kolicina+=StaviCemo;
				return;
			}
		}
		//Ako ga nema addujemo nov ukus
		UkusMl ukusml= new UkusMl(u,StaviCemo);
		Ukusi.add(ukusml);	
	}
	public boolean Pun() {
		int trenutnaKol=0;
		for(UkusMl ukusi:Ukusi) {
			trenutnaKol+=ukusi.kolicina;
		}
		if(trenutnaKol==this.velicina) {return true;}
		else {return false;}
		
	}
	
	@Override
	public String toString() {
		String s="";
		for(UkusMl ukusi:Ukusi) {
			s=s+ukusi.kolicina+"ml"+ukusi.ukus;
		}
		return s;	
	}
	
	

}
