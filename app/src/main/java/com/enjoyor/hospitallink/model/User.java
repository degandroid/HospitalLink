package com.enjoyor.hospitallink.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/7/1.
 */
@DatabaseTable(tableName = "tb_user")
public class User {
    @DatabaseField(id = true)
    int id;
    @DatabaseField
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
