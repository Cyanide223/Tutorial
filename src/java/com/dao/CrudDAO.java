/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.Games;
import com.util.DataConnect;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class CrudDAO {

    private Connection con;

    public CrudDAO() {
        con = DataConnect.getConnection();
    }

    public void addGame(Games game) {
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO `games`(`id`, `name`, `release_date`, `price`, `picture`) VALUES (?,?,?,?,?)");
            ps.setInt(1, game.getId());
            ps.setString(2, game.getName());
            ps.setDate(3, (Date) game.getReleaseDate());
            ps.setInt(4, game.getPrice());
            ps.setString(5, game.getPicture());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGame(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELEE FROM `games` WHERE id =?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGame(Games game) {
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE `games` SET `name`=?,`release_date`=?,`price`=?,`picture`=? WHERE `id`=?");
            ps.setString(1, game.getName());
            ps.setDate(2, (Date) game.getReleaseDate());
            ps.setInt(3, game.getPrice());
            ps.setString(4, game.getPicture());
            ps.setInt(5, game.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Games> getAllGames() {
        List<Games> games = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `games`");
            while (rs.next()) {
                Games game = new Games();
                game.setId(rs.getInt("id"));
                game.setName(rs.getString("name"));
                game.setPrice(rs.getInt("price"));
                game.setPicture(rs.getString("picture"));
                game.setReleaseDate(rs.getDate("release_date"));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

}
