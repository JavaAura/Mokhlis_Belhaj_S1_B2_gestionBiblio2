package metier.documents;

import java.time.LocalDate;

public class Magazine extends Document implements Empruntable, Reservable {
    private int numero;

    // Constructeur
    public Magazine( String titre, String auteur, LocalDate datePublication, int nombreDePages, int numero) {
        super( titre, auteur, datePublication, nombreDePages);
        this.numero = numero;
    }

    // Getters et Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

  

  
}
