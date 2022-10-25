package domaci1;

import java.awt.*;


public class Ukus {

	private String naziv;
	private Color boja;
	public Ukus(String naziv, Color boja) {
		this.naziv = naziv;
		this.boja = boja;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Color getBoja() {
		return boja;
	}
	public void setBoja(Color boja) {
		this.boja = boja;
	}
	 @Override
	public boolean equals(Object obj) {
		if(obj==this)return true;
		if(obj==null)return false;
		if(!(obj instanceof Ukus)) {
			return false;
		}
		Ukus u = (Ukus)obj;
		return this.naziv.equals(u.naziv);	
	}
	 @Override
	public String toString() {
		
		return "["+ this.naziv + "]";
	}
	

}
