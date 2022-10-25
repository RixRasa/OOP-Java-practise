package domaci4;

public abstract class Zivotinja {
	Rupa rupa;

	public Zivotinja(Rupa rupa) {
		this.rupa = rupa;
	}
	
	public abstract void efekatPobegleZivotinje();
	
	public abstract void IscrtajZivotinju();
	
	public abstract void efekatUdareneZivotinje();
}
