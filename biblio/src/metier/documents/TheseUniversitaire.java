package metier.documents;

import java.time.LocalDate;

public class TheseUniversitaire extends Document implements Empruntable, Reservable  {
    private String universite;
    private String domaine;

    public TheseUniversitaire( String titre, String auteur, LocalDate datePublication, int nombreDePages, String universite, String domaine) {
        super( titre, auteur, datePublication, nombreDePages);
        this.setUniversite(universite);
        this.setDomaine(domaine);
    }

	public String getDomaine() {
		return domaine;
	}


	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public String getUniversite() {
		return universite;
	}

	public void setUniversite(String universite) {
		this.universite = universite;
	}

}

