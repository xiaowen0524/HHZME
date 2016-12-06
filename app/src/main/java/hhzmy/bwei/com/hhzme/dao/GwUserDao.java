package hhzmy.bwei.com.hhzme.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/11/17.
 */
public class GwUserDao {
    // 找到数据库
    private GwUserHelper helper;

    public GwUserDao(Context context) {
        helper = new GwUserHelper(context);
    }

    // 数据库的添加方法
    public boolean insert(String name, String price, String image) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("image", image);
        long insert = db.insert("gwusers", null, values);
        if (insert != -1) {
            return true;
        } else {
            return false;
        }
    }

    // 数据库的查看方法
    public List<GwUser> select() {
        List<GwUser> list = new ArrayList<GwUser>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor rs = db.query("gwusers", null, null, null, null, null, null);
        while (rs.moveToNext()) {
            GwUser user = new GwUser();
            int id = rs.getInt(rs.getColumnIndex("id"));
            String name = rs.getString(rs.getColumnIndex("name"));
            String price = rs.getString(rs.getColumnIndex("price"));
            String image = rs.getString(rs.getColumnIndex("image"));
            user.setId(id);
            user.setName(name);
            user.setPrice(price);
            user.setImage(image);
            list.add(user);
        }
        return list;
    }

    // 数据库的删除方法
    public boolean delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int delete = db.delete("gwusers", "id = ?", new String[]{id + ""});
        if (delete > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 数据库的修改方法
    public boolean update(String name, String price, String image,
                          int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("image", image);
        int update = db.update("gwusers", values, "id = ?", new String[]{id + ""});
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }
}
