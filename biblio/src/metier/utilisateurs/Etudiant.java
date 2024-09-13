package metier.utilisateurs;

public class Etudiant extends Utilisateur {
    private String matricule;

    // Constructeur
    public Etudiant( String nom, String email, String matricule) {
        super(nom, email);
        this.matricule = matricule;
    }

    // Getters et Setters
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
}
