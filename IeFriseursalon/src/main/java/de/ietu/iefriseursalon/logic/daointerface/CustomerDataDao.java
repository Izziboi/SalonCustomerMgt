package de.ietu.iefriseursalon.logic.daointerface;

import java.sql.Connection;
import java.util.List;

/**
 * Eine Schnittstelle für das Hinzufügen, Lesen, Aktualisieren und Löschen von Kundendaten
 * @param <T>: Eine Klasse
 */
public interface CustomerDataDao<T> {

    void createCustomerData(Connection connection, T object);

    List<T> readCustomerData(Connection connection);

    void updateCustomerData(Connection connection, T object);

    void deleteCustomerData(Connection connection, T object);

}
