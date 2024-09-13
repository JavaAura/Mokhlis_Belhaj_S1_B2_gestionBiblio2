package Utilitaire;

 

public class Validator {
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isValidMatricule(String matricule) {
        return matricule != null && !matricule.trim().isEmpty();
    }

    public static boolean droitEmprunt(int nombreEmprunts , String typeUtilisateur) {
    	System.out.println(nombreEmprunts);
    	System.out.println(typeUtilisateur);
       if (nombreEmprunts >=3 && typeUtilisateur.equals("etudiant")) {
    	   System.out.println(nombreEmprunts);
    	   
        return false;
       }
       else if (nombreEmprunts >= 5 && typeUtilisateur.equals("professeur")) {
        return false;
       }
       return true;
    }
}
