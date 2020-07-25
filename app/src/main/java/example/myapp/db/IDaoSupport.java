package example.myapp.db;

import android.database.sqlite.SQLiteDatabase;

public interface IDaoSupport<T> {
    void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz);
    public int insert(T t);
}
