package metier.documents;

import java.time.LocalDate;

public class JournalScientifique extends Document implements Empruntable,Reservable {
	private String domaineRecherche;
	public JournalScientifique(String titre, String auteur, LocalDate datePublication, int nombreDePages,String domaineRecherche) {
        super(titre, auteur, datePublication, nombreDePages);
		this.domaineRecherche = domaineRecherche;

	}
	public String getDomaineRecherche() {
		return domaineRecherche;
	}
	public void setDomaineRecherche(String domaineRecherche) {
		this.domaineRecherche = domaineRecherche;
	}
	
	
	
	

}
