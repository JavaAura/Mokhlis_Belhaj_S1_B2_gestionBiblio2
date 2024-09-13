package Persistance.documents;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Persistance.connexion.DatabaseConnection;
import metier.documents.Document;
import metier.documents.JournalScientifique;
import metier.documents.Livre;
import metier.documents.Magazine;
import metier.documents.TheseUniversitaire;

public class DocumentDAOImpl implements DocumentDAO {
    private Connection connection;

    public DocumentDAOImpl(Connection connection) {
        this.connection = connection;
    }
    public DocumentDAOImpl() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override	
    public void ajouter(Document document) {
		try {
            // Insertion dans la table document
            String insertDocumentSql = "INSERT INTO document (titre, auteur, date_publication, nombre_de_pages) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement pstmt = connection.prepareStatement(insertDocumentSql);
            pstmt.setString(1, document.getTitre());
            pstmt.setString(2, document.getAuteur());
            pstmt.setDate(3, Date.valueOf(document.getDatePublication()));
            pstmt.setInt(4, document.getNombreDePages());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int documentId = rs.getInt(1);
                document.setId(documentId); 

                // Insertion dans les tables spécifiques
                if (document instanceof Livre) {
                    Livre livre = (Livre) document;
                    String insertLivreSql = "INSERT INTO livre (document_id, isbn) VALUES (?, ?)";
                    PreparedStatement livreStmt = connection.prepareStatement(insertLivreSql);
                    livreStmt.setInt(1, documentId);
                    livreStmt.setString(2, livre.getIsbn());
                    livreStmt.executeUpdate();
                } else if (document instanceof Magazine) {
                    Magazine magazine = (Magazine) document;
                    String insertMagazineSql = "INSERT INTO magazine (document_id, numero) VALUES (?, ?)";
                    PreparedStatement magazineStmt = connection.prepareStatement(insertMagazineSql);
                    magazineStmt.setInt(1, documentId);
                    magazineStmt.setInt(2, magazine.getNumero());
                    magazineStmt.executeUpdate();
                } else if (document instanceof JournalScientifique) {
                    JournalScientifique journalScientifique = (JournalScientifique) document;
                    String insertJournalScientifiqueSql = "INSERT INTO journal_scientifique (document_id, domaine_recherche) VALUES (?, ?)";
                    PreparedStatement journalScientifiqueStmt = connection.prepareStatement(insertJournalScientifiqueSql);
                    journalScientifiqueStmt.setInt(1, documentId);
                    journalScientifiqueStmt.setString(2, journalScientifique.getDomaineRecherche());
                    journalScientifiqueStmt.executeUpdate();
                } else if (document instanceof TheseUniversitaire) {
                    TheseUniversitaire theseUniversitaire = (TheseUniversitaire) document;
                    String insertTheseUniversitaireSql = "INSERT INTO these_universitaire (document_id, universite, domaine) VALUES (?, ?, ?)";
                    PreparedStatement theseUniversitaireStmt = connection.prepareStatement(insertTheseUniversitaireSql);
                    theseUniversitaireStmt.setInt(1, documentId);
                    theseUniversitaireStmt.setString(2, theseUniversitaire.getUniversite());
                    theseUniversitaireStmt.setString(3, theseUniversitaire.getDomaine());
                    theseUniversitaireStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

		
	}

	@Override
	public List<Document> getTousLesDocuments() {
	    List<Document> documentsList = new ArrayList<>();
	    String query = "SELECT * FROM all_documents";
	    try (PreparedStatement pstmt = connection.prepareStatement(query);
	         ResultSet resultSet = pstmt.executeQuery()) {
	        
	    	  if (resultSet != null) { // Check if resultSet is not null
	                while (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String title = resultSet.getString("title");
	                    String author = resultSet.getString("author");
	                    String publicationDate = resultSet.getString("publication_date");
	                    String docType = resultSet.getString("doc_type");

	                    System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author +
	                            ", Date: " + publicationDate + ", Type: " + docType);
	                }
	            } else {
	                System.out.println("No results found."); // Handle null resultSet
	            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return documentsList;
	}
	
	@Override
	public void supprimer(int id) {
		 try {
	            // Suppression du document
	            String deleteDocumentSql = "DELETE FROM document WHERE id = ?";
	            PreparedStatement pstmt = connection.prepareStatement(deleteDocumentSql);
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	}


	@Override
	public List<Document> rechercher(String critere) { 
		List<Document> documentsList = new ArrayList<>(); // Changed from HashMap to List
		
			String query = "SELECT * FROM all_documents WHERE title ILIKE ? OR author ILIKE ?";
			PreparedStatement pstmt;
			try {
				pstmt = connection.prepareStatement(query);
				String searchPattern = "%" + critere + "%";
				pstmt.setString(1, searchPattern);
				pstmt.setString(2, searchPattern);
				ResultSet resultSet = pstmt.executeQuery();

		           
				if (resultSet != null) { // Check if resultSet is not null
					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						String title = resultSet.getString("title");
						String author = resultSet.getString("author");
						String publicationDate = resultSet.getString("publication_date");
						String docType = resultSet.getString("doc_type");
	
						System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author +
								", Date: " + publicationDate + ", Type: " + docType);
					}
				} else {
					System.out.println("No results found."); // Handle null resultSet
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
            
		return documentsList; // Return the List
	

	}
	
	@Override
	public void modifierDocument() {
		System.out.println("hello word ");
		
	}
	
	@Override
	public Document getById(int id) {
	    String query = "SELECT d.*, l.isbn, m.numero, t.universite, t.domaine, j.domaine_recherche " +
                   "FROM document d " +
                   "LEFT JOIN livre l ON d.id = l.document_id " +
                   "LEFT JOIN magazine m ON d.id = m.document_id " +
                   "LEFT JOIN these_universitaire t ON d.id = t.document_id " +
                   "LEFT JOIN journal_scientifique j ON d.id = j.document_id " +
                   "WHERE d.id = ?";
    
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return createDocumentFromResultSet(rs);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

private Document createDocumentFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String titre = rs.getString("titre");
    String auteur = rs.getString("auteur");
    Date datePublication = rs.getDate("date_publication");
    int nombreDePages = rs.getInt("nombre_de_pages");

    Document document;

    if (rs.getString("isbn") != null) {
        document = new Livre(titre, auteur, datePublication.toLocalDate(), nombreDePages, rs.getString("isbn"));
    } else if (rs.getInt("numero") != 0) {
        document = new Magazine(titre, auteur, datePublication.toLocalDate(), nombreDePages, rs.getInt("numero"));
    } else if (rs.getString("domaine_recherche") != null) {
        document = new JournalScientifique(titre, auteur, datePublication.toLocalDate(), nombreDePages, rs.getString("domaine_recherche"));
    } else if (rs.getString("universite") != null) {
        document = new TheseUniversitaire(titre, auteur, datePublication.toLocalDate(), nombreDePages, rs.getString("universite"), rs.getString("domaine"));
    } else {
        throw new SQLException("Type de document inconnu");
    }

    document.setId(id);
    return document;
}

	@Override
	public void update(Document document) {
	    try {
	        // Update the document table
	        String updateDocumentSql = "UPDATE document SET titre = ?, auteur = ?, date_publication = ?, nombre_de_pages = ? WHERE id = ?";
	        PreparedStatement pstmt = connection.prepareStatement(updateDocumentSql);
	        pstmt.setString(1, document.getTitre());
	        pstmt.setString(2, document.getAuteur());
	        pstmt.setDate(3, Date.valueOf(document.getDatePublication()));
	        pstmt.setInt(4, document.getNombreDePages());
	        pstmt.setInt(5, document.getId());
	        pstmt.executeUpdate();

	        // Update specific tables based on document type
	        if (document instanceof Livre) {
	            Livre livre = (Livre) document;
	            String updateLivreSql = "UPDATE livre SET isbn = ? WHERE document_id = ?";
	            PreparedStatement livreStmt = connection.prepareStatement(updateLivreSql);
	            livreStmt.setString(1, livre.getIsbn());
	            livreStmt.setInt(2, document.getId());
	            livreStmt.executeUpdate();
	        } else if (document instanceof Magazine) {
	            Magazine magazine = (Magazine) document;
	            String updateMagazineSql = "UPDATE magazine SET numero = ? WHERE document_id = ?";
	            PreparedStatement magazineStmt = connection.prepareStatement(updateMagazineSql);
	            magazineStmt.setInt(1, magazine.getNumero());
	            magazineStmt.setInt(2, document.getId());
	            magazineStmt.executeUpdate();
	        } else if (document instanceof JournalScientifique) {
	            JournalScientifique journalScientifique = (JournalScientifique) document;
	            String updateJournalSql = "UPDATE journal_scientifique SET domaine_recherche = ? WHERE document_id = ?";
	            PreparedStatement journalStmt = connection.prepareStatement(updateJournalSql);
	            journalStmt.setString(1, journalScientifique.getDomaineRecherche());
	            journalStmt.setInt(2, document.getId());
	            journalStmt.executeUpdate();
	        } else if (document instanceof TheseUniversitaire) {
	            TheseUniversitaire these = (TheseUniversitaire) document;
	            String updateTheseSql = "UPDATE these_universitaire SET universite = ?, domaine = ? WHERE document_id = ?";
	            PreparedStatement theseStmt = connection.prepareStatement(updateTheseSql);
	            theseStmt.setString(1, these.getUniversite());
	            theseStmt.setString(2, these.getDomaine());
	            theseStmt.setInt(3, document.getId());
	            theseStmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
