package domaciZadatak2ZarkoBabic;

public class Generator {
	public int generisiSlucajniBr(int donja, int gornja) {
		gornja++;
		return (int)(Math.random()*(gornja - donja) + donja) ;
	}
	//Math.random vraca opseg [donja,gornja) pa transliramo na [donja, gornja]
}