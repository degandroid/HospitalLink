package com.enjoyor.hospitallink.db;


import android.content.Context;
import android.util.Log;

import com.enjoyor.hospitallink.model.BDLPoint;
import com.enjoyor.hospitallink.model.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class DBHelper {

    private DatabaseHelper mDBHelper;

    public DBHelper(Context context) {
        mDBHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }


    public boolean saveUser(User user) {
//        clearUser();
        try {
            Dao<User, Integer> dao = mDBHelper.getDao(User.class);
            dao.createOrUpdate(user);
            Log.i("sql", "DBHelper--->>---saveUser success");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser() {

        try {
            Dao<User, Integer> dao = mDBHelper.getDao(User.class);
            User user = dao.queryForId(1);
            if (user == null) {
                Log.i("sql", "DBHelper getUser null");
                return null;
            }
            Log.i("sql", "getUser toString：" + user.toString());
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean clearUser() {
        try {
            Dao<User, Integer> dao = mDBHelper.getDao(User.class);
            dao.delete(getUser());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean saveBDLPoint(BDLPoint user) {
//        clearUser();
        try {
            Dao<BDLPoint, Integer> dao = mDBHelper.getDao(BDLPoint.class);
            dao.createOrUpdate(user);
            Log.i("sql", "DBHelper--->>---saveBDLPoint success");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public BDLPoint getBDLPoint() {

        try {
            Dao<BDLPoint, Integer> dao = mDBHelper.getDao(BDLPoint.class);
            BDLPoint point = dao.queryForId(1);
            if (point == null) {
                Log.i("sql", "DBHelper getBDLPoint null");
                return null;
            }
            Log.i("sql", "getBDLPoint toString：" + point.toString());
            return point;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean clearBDLPoint() {
        try {
            Dao<BDLPoint, Integer> dao = mDBHelper.getDao(BDLPoint.class);
            dao.delete(getBDLPoint());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
