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
public class ScUserDao {
    // 找到数据库
    private ScUserHelper helper;

    public ScUserDao(Context context) {
        helper = new ScUserHelper(context);
    }

    // 数据库的添加方法
    public boolean insert(String name, String price, String image) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("image", image);
        long insert = db.insert("scusers", null, values);
        if (insert != -1) {
            return true;
        } else {
            return false;
        }
    }

    // 数据库的查看方法
    public List<ScUser> select() {
        List<ScUser> list = new ArrayList<ScUser>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor rs = db.query("scusers", null, null, null, null, null, null);
        while (rs.moveToNext()) {
            ScUser user = new ScUser();
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
        int delete = db.delete("scusers", "id = ?", new String[]{id + ""});
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
        int update = db.update("scusers", values, "id = ?", new String[]{id + ""});
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }
}
