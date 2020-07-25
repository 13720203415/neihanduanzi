package example.myapp.db;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;

public class DaoSupportFactory {
    private static final String THIS_FILE = "DaoSupportFactory";
    private static DaoSupportFactory mFactory;
    //持有外部数据库的引用
    SQLiteDatabase database;
    private DaoSupportFactory() {
        //把数据放到内存卡里面

        File dbRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "nhdz" + File.separator + "database");
        if(!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot, "neihanduanzi.db");
        database = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }

    public static DaoSupportFactory getFactory() {
        if(mFactory == null) {
            synchronized (DaoSupportFactory.class) {
                if(mFactory == null) {
                    mFactory = new DaoSupportFactory();
                }
            }
        }
        return mFactory;
    }

   public <T> IDaoSupport<T> getDao(Class<T> clazz) {
        IDaoSupport<T> daoSupport = new DaoSupport();
        daoSupport.init(database, clazz);
        return daoSupport;
   }
}
