package be.vdab.cultuurhuis.entities;

public class Boeking {
	final Voorstelling voorstelling;
	final int aantalPlaatsen;

	public Boeking(Voorstelling voorstelling, int aantalPlaatsen){
		this.voorstelling = voorstelling;
		this.aantalPlaatsen = aantalPlaatsen;
	}

	public Voorstelling getVoorstelling() {
		return voorstelling;
	}

	public int getAantalPlaatsen() {
		return aantalPlaatsen;
	}



}
