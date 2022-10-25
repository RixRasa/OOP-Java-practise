package domaci3;

public class Kontakt extends Stavka {
	String ime;
	Broj broj;
	public Kontakt(String ime, Broj broj) {
		super(ime,broj.toString());
		this.ime=ime;
		this.broj=broj;
	}

}
