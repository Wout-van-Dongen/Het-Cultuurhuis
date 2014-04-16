package be.vdab.cultuurhuis.entities;

public class Persoon {
	

private final String voornaam,
familienaam,
straat,
huisnr,
postcode,
gemeente,
gebruikersnaam,
wachtwoord;

public Persoon(String voornaam, String familienaam, String straat,
		String huisnr, String postcode, String gemeente, String gebruikersnaam,
		String wachtwoord) {
	this.voornaam = voornaam;
	this.familienaam = familienaam;
	this.straat = straat;
	this.huisnr = huisnr;
	this.postcode = postcode;
	this.gemeente = gemeente;
	this.gebruikersnaam = gebruikersnaam;
	this.wachtwoord = wachtwoord;
}

public String getVoornaam() {
	return voornaam;
}

public String getFamilienaam() {
	return familienaam;
}

public String getStraat() {
	return straat;
}

public String getHuisnr() {
	return huisnr;
}

public String getPostcode() {
	return postcode;
}

public String getGemeente() {
	return gemeente;
}

public String getGebruikersnaam() {
	return gebruikersnaam;
}

public String getWachtwoord() {
	return wachtwoord;
}




}
