/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf;

/**
 *
 * @author James
 */
import com.entity.Games;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.dao.CrudDAO;
import java.sql.Statement;

@ManagedBean
@SessionScoped
public class SimpleCrudBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Games> list;
    private Games item = new Games();
    private Games beforeEditItem = null;
    private boolean edit;
    private CrudDAO dao;

    @PostConstruct
    public void init() {
        dao = new CrudDAO();
        list = new ArrayList<>();
    }

    public void add() {
        dao.addGame(item);
        item.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        list.add(item);
        item = new Games();
    }

    public void resetAdd() {
        item = new Games();
    }

    public void edit(Games item) {
        beforeEditItem = (Games) item.clone();
        this.item = item;
        edit = true;
    }

    public void cancelEdit() {
        this.item.restore(beforeEditItem);
        this.item = new Games();
        edit = false;
    }

    public void saveEdit() {
        dao.updateGame(item);
        this.item = new Games();
        edit = false;
    }

    public void delete(Games item) throws IOException {
        dao.deleteGame(item.getId());
        list.remove(item);
    }

    public List<Games> getList() {
        list = dao.getAllGames();
        return list;
    }

    public Games getItem() {
        return this.item;
    }

    public boolean isEdit() {
        return this.edit;
    }

}
