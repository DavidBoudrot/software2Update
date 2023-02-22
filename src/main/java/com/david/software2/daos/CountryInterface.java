package com.david.software2.daos;

import com.david.software2.models.Country;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CountryInterface {

    public ObservableList<Country> getAllCountries() throws SQLException;
}
