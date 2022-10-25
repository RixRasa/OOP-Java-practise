package domaci3;



public class Broj {
	int kodD;
	int pozB;
	int Bpret;
	
	public Broj(int k,int p,int B) {
		this.kodD=k;
		this.pozB=p;
		this.Bpret=B;
	}
	
	public Broj(String text) {
		String text1=text.substring(1, 4);
		String text2=text.substring(4, 6);
		String text3=text.substring(6);
		kodD=Integer.parseInt(text1);
		pozB=Integer.parseInt(text2);
		Bpret=Integer.parseInt(text3);
		
	}
	
	public String toString() {
		return "+" + this.kodD + " " + this.pozB + " " + this.Bpret;
	}
	
	public boolean istaDrz(Broj b) {
		return (this.kodD==b.kodD );
	}
	
	public boolean istaMreza(Broj b) {
		return (this.kodD==b.kodD && this.pozB==b.pozB);
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Broj)) {
			return false;
		}
		Broj b = (Broj)obj;
		return (this.kodD==b.kodD && this.pozB==b.pozB && this.Bpret==b.Bpret);
	}
	
	public static void main(String[] args) {
		
	}

}
