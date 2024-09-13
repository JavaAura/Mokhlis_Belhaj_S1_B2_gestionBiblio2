package Persistance.reservation;

import metier.emprunts.Reservation;

public interface ReservationDAO {

    public boolean estReserve(int documentId);
    public boolean ajouterReservation(Reservation reservation);
    public boolean annulerReservation(int documentId , int utilisateurId);


}
