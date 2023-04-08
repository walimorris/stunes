package com.morris.stunes.repository;

import com.morris.stunes.model.Album;
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
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM albums;");
                ResultSet results = statement.executeQuery();
                albums = getPreparedStatementResults(results);
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
                        "SELECT * FROM albums WHERE Title LIKE ? OR Title LIKE ?;"
                );
                statement.setString(1, "%" + title + "%");
                statement.setString(2, title + "%");
                ResultSet results = statement.executeQuery();
                albums = getPreparedStatementResults(results);
            }
            RDSAuroraConnectionHelper.close(connection);
            return albums;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return albums;
    }

    private List<Album> getPreparedStatementResults(ResultSet results) {
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
}
