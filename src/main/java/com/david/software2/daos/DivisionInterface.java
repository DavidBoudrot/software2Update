package com.david.software2.daos;

import com.david.software2.models.Division;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DivisionInterface {
    public void addDivision(Division division) throws SQLException;
    public ObservableList<Division> getAllDivisions() throws SQLException;
    public ObservableList<Division> getDivisionsByCountry(int countryID) throws SQLException;
}
