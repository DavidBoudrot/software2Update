package com.david.software2.daos;

import com.david.software2.helper.JDBC;
import com.david.software2.models.Country;
import com.david.software2.models.Division;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;




public class CountryDao implements CountryInterface {
    private ObservableList<Country> countries;


    /**
     * Gets a divisionID from a divisionName
     * @param divisionName
     * @return
     */
    public static int getDivisionID(String divisionName) {
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, divisionName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Division_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * gets all countries from the database
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countries = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            countries.add(new Country(countryID, countryName, lastUpdate, lastUpdatedBy, createDate, createdBy));
        }
        return countries;
    }

    String countryID;


    /**
     * gets all divisions from a country name
     * @param countryName
     * @return
     */
    public ObservableList<Division> getAllDivisions(String countryName) {
        //get all divisions associated with country name
        ObservableList<Division> divisions = javafx.collections.FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            if (countryName.equals("U.S")) {
                countryID = "1";
            } else if (countryName.equals("UK")) {
                countryID = "2";
            } else if (countryName.equals("Canada")) {
                countryID = "3";
            }
            ps.setString(1, countryID);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                divisions.add(new Division(divisionID, divisionName, Integer.parseInt(countryID), createdBy, lastUpdatedBy, createDate, lastUpdate));
            } return divisions;
        } catch (SQLException e) {
            e.printStackTrace();
            return divisions;
        }
    }


    /**
     * gets a division name from a divisionID
     * @param divisionID
     * @return
     */
    public String getDivisionName(int divisionID) {
        //get all divisions
        ObservableList<Division> divisions = javafx.collections.FXCollections.observableArrayList();
        for (Division division : getAllDivisions("U.S")) {
            if (division.getDivisionID() == divisionID) {
                return division.getDivisionName();
            }
        }
        for (Division division : getAllDivisions("UK")) {
            if (division.getDivisionID() == divisionID) {
                return division.getDivisionName();
            }
        }
    for (Division division : getAllDivisions("Canada")) {
            if (division.getDivisionID() == divisionID) {
                return division.getDivisionName();
            }
        } return null;
    }


    /**
     * gets a country name from a division name
     * @param divisionName
     * @return
     */
    public String getCountryName(String divisionName) {
        //get all divisions
        ObservableList<Division> divisions = javafx.collections.FXCollections.observableArrayList();
        for (Division division : getAllDivisions("U.S")) {
            if (division.getDivisionName().equals(divisionName)) {
                return "U.S";
            }
        }
        for (Division division : getAllDivisions("UK")) {
            if (division.getDivisionName().equals(divisionName)) {
                return "UK";
            }
        }
        for (Division division : getAllDivisions("Canada")) {
            if (division.getDivisionName().equals(divisionName)) {
                return "Canada";
            }
        } return null;
    }
}
