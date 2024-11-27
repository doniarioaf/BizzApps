package com.servlet.historyapps.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "history_apps", schema = "public")
public class HistoryApps implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="history_apps_id_seq")
    private Long id;
    private String action;
    private String menu;
    private String databefore;
    private String dataafter;
    private String data;
    private Timestamp datetime;
    private Long iduser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDatabefore() {
        return databefore;
    }

    public void setDatabefore(String databefore) {
        this.databefore = databefore;
    }

    public String getDataafter() {
        return dataafter;
    }

    public void setDataafter(String dataafter) {
        this.dataafter = dataafter;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }
}
