package Persistance.utilisateurs;


import metier.utilisateurs.Utilisateur;
import java.util.List;

/**
 * Interface définissant les opérations de persistance pour les utilisateurs.
 */
public interface UtilisateurDAO {
    /**
     * Ajoute un nouvel utilisateur à la base de données.
     * @param utilisateur L'utilisateur à ajouter
     */
    void ajouter(Utilisateur utilisateur);

    /**
     * Récupère tous les utilisateurs de la base de données.
     * @return Une liste contenant tous les utilisateurs
     */
    List<Utilisateur> getTousLesUtilisateurs();

    /**
     * Supprime un utilisateur de la base de données.
     * @param id L'identifiant de l'utilisateur à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    boolean supprimer(int id);

    /**
     * Trouve un utilisateur par son identifiant.
     * @param id L'identifiant de l'utilisateur à trouver
     * @return L'utilisateur trouvé ou null si non trouvé
     */
    Utilisateur trouverParId(int id);

    /**
     * Recherche des utilisateurs selon un critère donné.
     * @param critere Le critère de recherche
     * @return Une liste d'utilisateurs correspondant au critère
     */
    List<Utilisateur> rechercher(String critere);

    /**
     * Modifie les informations d'un utilisateur existant.
     * @param utilisateur L'utilisateur avec les informations mises à jour
     */
    void modifier(Utilisateur utilisateur);
}
