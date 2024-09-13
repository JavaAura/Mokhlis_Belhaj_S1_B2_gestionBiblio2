package Persistance.emprunts;


import metier.emprunts.Emprunt;
import java.util.List;

public interface EmpruntDAO {
    List<Emprunt>  findByDocumentId(int documentId);
    boolean disponible(int documentId);
    int getNombreEmprunts(int utilisateurId);
    void ajouter(Emprunt emprunt);
    boolean retournerDocument(int documentId);
}
