package metier.documents;

import java.time.LocalDate;

public class Livre extends Document implements Empruntable, Reservable {
    private String isbn;

    // Constructeur
    public Livre(String titre, String auteur, LocalDate datePublication, int nombreDePages, String isbn) {
        super(titre, auteur, datePublication, nombreDePages);
        this.isbn = isbn;
    }


	// Getters et Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

 

 

  
}
