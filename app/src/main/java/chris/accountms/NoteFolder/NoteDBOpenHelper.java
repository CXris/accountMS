/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.NoteFolder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDBOpenHelper extends SQLiteOpenHelper {

    public NoteDBOpenHelper(Context context) {
        super(context, "mynote", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mybook(" +                  //表名设置为mybook
                "ids integer PRIMARY KEY autoincrement," +   //设置id自增
                "title text," +                              //设置标题为文本类型
                "content text," +                            //设置内容为文本类型
                "times text)");                              //设置时间为文本类型
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
