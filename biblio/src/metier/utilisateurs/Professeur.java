package metier.utilisateurs;

public class Professeur extends Utilisateur {
    private String specialite;

    // Constructeur
    public Professeur( String nom, String email, String specialite) {
        super( nom, email);
        this.specialite = specialite;
    }

    // Getters et Setters
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
}
