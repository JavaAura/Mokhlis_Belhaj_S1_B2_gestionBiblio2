package metier.documents;

import java.time.LocalDate;

public abstract class Document {
    private int id;
    private String titre;
    private String auteur;
    private LocalDate datePublication;
    private int nombreDePages;

  
    public Document(String titre, String auteur, LocalDate datePublication, int nombreDePages) {
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.nombreDePages = nombreDePages;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate localDate) { this.datePublication = localDate; }

    public int getNombreDePages() { return nombreDePages; }
    public void setNombreDePages(int nombreDePages) { this.nombreDePages = nombreDePages; }

    
}

