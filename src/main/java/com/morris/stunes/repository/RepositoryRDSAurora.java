package com.morris.stunes.repository;

import com.morris.stunes.model.Album;
import com.morris.stunes.model.Artist;
import com.morris.stunes.model.Customer;
import com.morris.stunes.util.RDSAuroraConnectionHelper;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryRDSAurora {

    public List<Album> getAllAlbums() {
        Connection connection;
        List<Album> albums = null;

        try {
            connection = RDSAuroraConnectionHelper.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("SELECT AlbumId, Title, ArtistId FROM albums;");
                ResultSet results = statement.executeQuery();
                albums = getPreparedStatementAlbumResults(results);
            }
            RDSAuroraConnectionHelper.close(connection);
            return albums;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return albums;
    }

    public List<Album> getAlbumsLikeTitle(String title) {
        Connection connection;
        List<Album> albums = null;

        try {
            connection = RDSAuroraConnectionHelper.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT AlbumId, Title, ArtistId FROM albums WHERE Title LIKE ? OR Title LIKE ?;"
                );
                statement.setString(1, "%" + title + "%");
                statement.setString(2, title + "%");
                ResultSet results = statement.executeQuery();
                albums = getPreparedStatementAlbumResults(results);
            }
            RDSAuroraConnectionHelper.close(connection);
            return albums;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return albums;
    }

    public List<Album> getAllAlbumsWithArtistId(int id) {
        Connection connection;
        List<Album> albums = null;

        try {
            connection = RDSAuroraConnectionHelper.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT AlbumId, Title, ArtistId FROM albums WHERE ArtistId = ?;"
                );
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                albums = getPreparedStatementAlbumResults(results);
            }
            RDSAuroraConnectionHelper.close(connection);
            return albums;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return albums;
    }

    public List<Artist> getArtistsLikeName(String name) {
        Connection connection;
        List<Artist> artists = null;

        try {
            connection = RDSAuroraConnectionHelper.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT ArtistId, Name FROM artists WHERE Name LIKE ? OR Name LIKE ?;"
                );
                statement.setString(1, "%" + name + "%");
                statement.setString(2, name + "%");
                ResultSet results = statement.executeQuery();
                artists = getPreparedStatementArtistResults(results);
            }
            RDSAuroraConnectionHelper.close(connection);
            return artists;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return artists;
    }

    public List<Customer> getTopFiveCustomers() {
        Connection connection;
        List<Customer> customers = null;

        try {
            connection = RDSAuroraConnectionHelper.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT c.CustomerId, c.FirstName, c.LastName, c.Company, c.Address, c.City, c.State, c.Country, c.PostalCode, c.Phone, c.Fax, c.Email, c.SupportRepId FROM customers as c JOIN invoices as i ON c.CustomerId = i.CustomerId GROUP BY c.CustomerId ORDER BY SUM(i.Total) DESC LIMIT 5;"
                );
                ResultSet results = statement.executeQuery();
                customers = getPreparedStatementCustomerResults(results);
            }
            RDSAuroraConnectionHelper.close(connection);
            return customers;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return customers;
    }

    private List<Album> getPreparedStatementAlbumResults(ResultSet results) {
        List<Album> queryResults = new ArrayList<>();

        try {
            while (results.next()) {
                Album album = new Album();
                album.setAlbumId(results.getInt(1));
                album.setTitle(results.getString(2));
                album.setArtistId(results.getInt(3));

                queryResults.add(album);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return queryResults;
    }

    private List<Artist> getPreparedStatementArtistResults(ResultSet results) {
        List<Artist> queryResults = new ArrayList<>();
        try {
            while (results.next()) {
                Artist artist = new Artist();
                artist.setArtistId(results.getInt(1));
                artist.setName(results.getString(2));

                queryResults.add(artist);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return queryResults;
    }

    private List<Customer> getPreparedStatementCustomerResults(ResultSet results) {
        List<Customer> queryResults = new ArrayList<>();
        try {
            System.out.println("preparing customer results");
            while (results.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(results.getInt(1));
                customer.setFirstName(results.getString(2));
                customer.setLastName(results.getString(3));
                customer.setCompany(results.getString(4));
                customer.setAddress(results.getString(5));
                customer.setCity(results.getString(6));
                customer.setState(results.getString(7));
                customer.setCountry(results.getString(8));
                customer.setPostalCode(results.getString(9));
                customer.setPhone(results.getString(10));
                customer.setFax(results.getString(11));
                customer.setEmail(results.getString(12));
                customer.setSupportRepId(results.getInt(13));

                queryResults.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("shutting down");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return queryResults;
    }
}
