package be.vdab.cultuurhuis.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Voorstelling {
	private final String genre,	title,	uitvoerders;
	private final BigDecimal prijs;
	private final Long vrijePlaatsen, voorstellingsNr;
	private final Timestamp datum;

	public Voorstelling(Long voorstellingsNr, String genre, String title, String uitvoerders, BigDecimal prijs, Long vrijePlaatsen, Timestamp datum){
		this.genre = genre;
		this.title = title;
		this.uitvoerders = uitvoerders;
		this.prijs = prijs;
		this.vrijePlaatsen = vrijePlaatsen;	
		this.voorstellingsNr = voorstellingsNr;
		this.datum = datum;
	}

	public String getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public String getUitvoerders() {
		return uitvoerders;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public Long getVrijePlaatsen() {
		return vrijePlaatsen;
	}

	public Long getVoorstellingsNr() {
		return voorstellingsNr;
	}

	public Timestamp getDatum() {
		return datum;
	}	
	
	
}
