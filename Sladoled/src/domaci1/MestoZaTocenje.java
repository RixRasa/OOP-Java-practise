package domaci1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MestoZaTocenje extends Canvas implements Runnable {
	private Ukus u;
	private int FLAG=0;
	private AparatZaTocenje owner;
	private Sladoled sladoled= new Sladoled(200);
	Thread thread;
	
	public MestoZaTocenje(AparatZaTocenje owner) {
		this.owner = owner;
		
	}
	
	
	public Sladoled setSladoled(Sladoled sladoled) {
		return this.sladoled=sladoled;
	}
	public Sladoled getSladoled() {
		return sladoled;
	}

	@Override
	public void paint(Graphics g) {
		run();
	}
	
	public synchronized void Startuj(Ukus u) {//Startovanje niti sa ukusom koji dodajemo tekucim
		if(FLAG==0) {FLAG=1;}
		this.u=u;
		thread= new Thread(this);
		thread.start();
		
	}
	
	public synchronized void Prekini() {//Prekidanje niti
		thread.interrupt();
		try {thread.join();} catch (InterruptedException e1) {}
	}
	
	public void PunSladoled() {
		owner.prodaj.setEnabled(true);//Postavljamo button na true
	}
	
	@Override
	public void run() {
		Graphics g=getGraphics();
		if(FLAG==1) {
			try {
				while(!thread.interrupted()) {
					sladoled.Dodaj(20, u);//Dodajemo u sladoled 
					
					owner.sladoled.setText(sladoled.toString());//Menjamo labelu sladoled
					owner.sladoled.revalidate();
					
					int y=this.getSize().height;
					for(UkusMl ukusi:sladoled.Ukusi) {//Iteriramo kroz sladoled da vidimo koje i koliko ukusa ima
						g.setColor(ukusi.ukus.getBoja());
						int velicinaSladoleda=sladoled.velicina;//Velicinu sladoled
						int velicinaTocenja=ukusi.kolicina;//Kolicinu ukusa koju tocimo
						int visina=this.getSize().height*velicinaTocenja/velicinaSladoleda;//visinu DODUSE u int koliko se toci
						int sirina=this.getSize().width;
						y=y-visina;//kordinata odakle tocimo
						g.fillRect(0,y , sirina, visina);//tocenje
						//sipano+=visina;//if(sipano>this.getSize().height) {sipano=this.getSize().height;}
					}
					if(this.sladoled.Pun()) {this.PunSladoled();}
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {}
		}
	}
		
}

