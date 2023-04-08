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
        Connection connection = null;
        List<Album> albums = new ArrayList<>();

        try {
            connection = RDSAuroraConnectionHelper.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM albums;");
                ResultSet results = statement.executeQuery();


                while (results.next()) {
                    Album album = new Album();
                    album.setAlbumId(results.getInt(1));
                    album.setTitle(results.getString(2));
                    album.setArtistId(results.getInt(3));

                    albums.add(album);
                }
            }
            RDSAuroraConnectionHelper.close(connection);
            return albums;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return albums;
    }
}
