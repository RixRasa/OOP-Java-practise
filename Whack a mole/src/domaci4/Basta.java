package domaci4;

import java.awt.*;

public class Basta extends Panel implements Runnable {

	
	private Rupa[][] Rupe;
	private int povrce=100;
	private int Koraci=10;
	private int intervalCekanja=1000;
	private int red;
	private int kolone;
	private Igra Igra;
	Thread threadB;
	
	public Basta(int red,int kolone,Igra Igra) {
		
		//Inicijalizacija polja
		this.Igra=Igra;
		this.red=red;
		this.kolone=kolone;
		this.Rupe=new Rupa[red][kolone];
		
		//Setovanje backgrounda
		this.setBackground(Color.GREEN);
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new GridLayout(red, kolone,10,10));
		
		//Dodavanje rupa na Panel
		DodajRupe();
	
	}
	
	
	private void DodajRupe() {
		for(int i=0;i<red;i++) {
			for(int j=0;j<kolone;j++) {
				Rupe[i][j]=new Rupa(this);
				this.add(Rupe[i][j]);
			}
		}
		
	}


	public synchronized void SmanjiJedan() {
		this.povrce=this.povrce-1;
		if(this.povrce==0) {//Zavrsavamo igru kad je povrce=0
			GasiOstaleNiti();
			threadB.interrupt();
			try {threadB.join();} catch (InterruptedException e) {}
			threadB=null;
			System.out.println("izgubio si");
		}
	}
	
	
	public int getKoraci() {
		return Koraci;
	}
	public void setKoraci(int Koraci) {
		this.Koraci = Koraci;
	}
	public int getIntervalCekanja() {
		return intervalCekanja;
	}
	public void setIntervalCekanja(int intervalCekanja) {
		this.intervalCekanja = intervalCekanja;
	}
	public int getPovrce() {
		return this.povrce;
	}
	public int setPovrce(int povrce) {
		return this.povrce=povrce;
	}
	
	public Igra getIgra() {
		return this.Igra;
	}
	public synchronized void GasiOstaleNiti() {
		for(int i=0;i<red;i++) {
			for(int j=0;j<kolone;j++) {
				if(Rupe[i][j].threadR!=null) {
					//Rupe[i][j].zivotinja.efekatUdareneZivotinje();
					Rupe[i][j].trenutniKorak=1;//Ovo bojenje ovalno u sivo i koraci na jedan da bi se sve ugasilo
					Graphics g=this.Rupe[i][j].getGraphics();
					g.setColor(Color.GRAY);
					g.fillOval(0,0,this.Rupe[i][j].getSize().width,this.Rupe[i][j].getSize().height );
					Rupe[i][j].zaustavi();
				}
			}
		}
	}

	@Override
	public void run() {
		int vreme=intervalCekanja;
		try {
			while(!Thread.interrupted()) {
				int x=(int)(Math.random()*red);
				int y=(int)(Math.random()*kolone);
				if(Rupe[x][y].threadR==null) {//Ovako proveravamo dal je rupa slobodna
					Zivotinja z=new Krtica(Rupe[x][y]);
					Rupe[x][y].zivotinja=z;
					Rupe[x][y].threadR=new Thread(Rupe[x][y]);
					Rupe[x][y].threadR.start();
					Thread.sleep(vreme);
					vreme=vreme-vreme*1/100;
				}
			} 
		}catch (InterruptedException e) {}	
	}
}

