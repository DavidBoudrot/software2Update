package com.david.software2.daos;

import com.david.software2.helper.JDBC;
import com.david.software2.models.Division;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author David
 * The DivisionDao class is used to access the first_level_divisions table in the database.
 */
public class DivisionDao implements DivisionInterface {
    ObservableList<Division> allDivisions;

    /**
     * The addDivision method adds a division to the database
     * @param division
     * @throws SQLException
     */
    public void addDivision(Division division) throws SQLException {
        String sql = "INSERT INTO first_level_divisions (Division, Country_ID, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, division.getDivisionName());
        ps.setInt(2, division.getCountryID());
        ps.setTimestamp(3, Timestamp.valueOf(division.getCreateDate()));
        ps.setString(4, division.getCreatedBy());
        ps.setTimestamp(5, Timestamp.valueOf(division.getLastUpdate()));
        ps.setString(6, division.getLastUpdatedBy());
        ps.executeUpdate();

    }


    /**
     * The getAllDivisions method returns all divisions in the database.
     * @return
     * @throws SQLException
     */


    public ObservableList<Division> getAllDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        ObservableList<Division> allDivisions = null;
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryID = rs.getInt("Country_ID");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            Division division = new Division(divisionID, divisionName, countryID, createdBy, lastUpdatedBy, createDate.toLocalDateTime(), lastUpdate.toLocalDateTime());
            allDivisions.add(division);
        } return allDivisions;
    }

    /**
     * The getDivisionsByCountry method returns all divisions in the database that are associated with a country.
     * @param countryID
     * @return
     * @throws SQLException
     */
    public ObservableList<Division> getDivisionsByCountry(int countryID) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        ObservableList<Division> allDivisions = null;
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            Division division = new Division(divisionID, divisionName, countryID, createdBy, lastUpdatedBy, createDate, lastUpdate);
            allDivisions.add(division);
        } return allDivisions;

    }

}
