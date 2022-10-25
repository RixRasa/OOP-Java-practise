package domaci4;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {
	
	public Krtica(Rupa rupa) {
		super(rupa);
	}

	@Override
	public synchronized void efekatUdareneZivotinje() {
		Graphics g=this.rupa.getGraphics();
		g.setColor(Color.GRAY);
		g.fillOval(0,0,this.rupa.getSize().width,this.rupa.getSize().height );
		this.rupa.trenutniKorak=1;
		
		
	}

	@Override
	public synchronized void efekatPobegleZivotinje() {
		this.rupa.trenutniKorak=1;
		this.rupa.basta.SmanjiJedan();
		this.rupa.basta.getIgra().PostaviPovrce();
		
	}

	@Override
	public synchronized void IscrtajZivotinju() {
		Graphics g=this.rupa.getGraphics();
		g.setColor(Color.BLACK);
		int korak=this.rupa.trenutniKorak;
		double procenat=(double)korak/(double)this.rupa.basta.getKoraci();
		int rupaX=rupa.getSize().width;
		int rupaY=rupa.getSize().height;
		int Sirina=(int)(rupaX*procenat);
		int Visina=(int)(rupaY*procenat);
		int X=rupaX/2-Sirina/2;
		int Y=rupaY/2-Visina/2;
		g.fillOval(X,Y,Sirina,Visina );
		
	}

}
