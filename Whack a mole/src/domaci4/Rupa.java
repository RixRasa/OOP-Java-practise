package domaci4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rupa extends Canvas implements Runnable {
	Basta basta;
	Zivotinja zivotinja=null;
	Thread threadR=null;
	int trenutniKorak=1;
	
	public Rupa(Basta basta) {
		this.setBackground(Color.GRAY);
		this.basta = basta;
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(Rupa.this.zivotinja!=null) {
					Rupa.this.zivotinja.efekatUdareneZivotinje();
					zaustavi();
				}
			}
		});
		
		this.setVisible(true);
	}
	
	
	public void stvori() {//Stvaranje niti 
		threadR=new Thread(this);
	}
	public void pokreni() {//Pokretanje niti
		this.threadR.start();
	}
	public synchronized void zaustavi() {//Zavrsavanje niti
		if(threadR!=null) {
			this.zivotinja=null;
			threadR.interrupt();
			try {threadR.join();} catch (InterruptedException e) {}
			threadR=null;
		}
	}
	public boolean Slobodna() {
		if(zivotinja==null)return true;
		else return false;
	}
	
	
	@Override
	public void run() {
		int provera=0;
		try {
		while(!Thread.interrupted()) {
			zivotinja.IscrtajZivotinju();
			this.trenutniKorak++;
			if(this.trenutniKorak>this.basta.getKoraci()) {
				provera=1;
				break;
			}
			Thread.sleep(100);
			} 
		if(provera==1) {
		this.zivotinja.efekatPobegleZivotinje();
		Thread.sleep(2000);
		Graphics g=this.getGraphics();
		g.setColor(Color.GRAY);
		g.fillOval(0,0,this.getSize().width,this.getSize().height );
		zaustavi();
		}
		}catch (InterruptedException e) {}
	}
	/*synchronized*/

}
