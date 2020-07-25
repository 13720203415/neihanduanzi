package example.myapp.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;

public class DaoSupport<T> implements IDaoSupport<T>{
    private static final String THIS_FILE = "DaoSupport";
    private SQLiteDatabase mSqLiteDatabase;
    private Class<T> mClazz;
    @Override
    public void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        mSqLiteDatabase = sqLiteDatabase;
        mClazz = clazz;

        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists ").append(DaoUtils.getTableName(mClazz)).append(" (id integer primary key autoincrement, ");
        Field[] declaredFields = mClazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String name = field.getName();
            sb.append(name);
            String type = field.getType().getSimpleName(); // int String boolean
            //需要对type进行转换 int --->integer String ---> text
            sb.append(DaoUtils.getColumnType(type)).append(", ");
        }
        sb.replace(sb.length()-2, sb.length(),")");

        String createSql = sb.toString();
        Log.d(THIS_FILE, createSql);
        //创建表
        mSqLiteDatabase.execSQL(createSql);
    }



    @Override
    public int insert(T t) {

        return 0;
    }
}
