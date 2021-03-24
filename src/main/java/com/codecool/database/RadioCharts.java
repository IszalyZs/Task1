package com.codecool.database;


import java.sql.*;

public class RadioCharts {
    private String db_url;
    private String userName;
    private String password;

    public RadioCharts(String db_url, String userName, String password) {
        this.db_url = db_url;
        this.userName = userName;
        this.password = password;
    }


    public String getMostPlayedSong() {
        String sql = "SELECT SUM(times_aired) as total_times_aired, song FROM music_broadcast GROUP BY song ORDER BY total_times_aired DESC LIMIT 1";
        try (Connection connection = DriverManager.getConnection(db_url, userName, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getString("song");
        } catch (SQLException throwables) {
            return "";
        }
    }

    public String getMostActiveArtist() {
        String sql = "SELECT COUNT(DISTINCT song) as total_song, artist FROM music_broadcast GROUP BY artist ORDER BY total_song DESC LIMIT 1";
        try (Connection connection = DriverManager.getConnection(db_url, userName, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getString("artist");
        } catch (SQLException throwables) {
            return "";
        }
    }
}
