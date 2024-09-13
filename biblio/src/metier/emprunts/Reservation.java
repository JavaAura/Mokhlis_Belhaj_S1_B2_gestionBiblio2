package metier.emprunts;

import metier.documents.Document;
import metier.utilisateurs.Utilisateur;
import java.time.LocalDate;

public class Reservation {
    private Utilisateur utilisateur;
    private Document document;
    private LocalDate dateReservation;

    // Constructeur
    public Reservation(Utilisateur utilisateur, Document document) {
        this.utilisateur = utilisateur;
        this.document = document;
        this.dateReservation = LocalDate.now();
    }

    // Getters et Setters
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }

    public LocalDate getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDate dateReservation) { this.dateReservation = dateReservation; }
}
