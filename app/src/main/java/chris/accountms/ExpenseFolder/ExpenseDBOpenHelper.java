/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.ExpenseFolder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpenseDBOpenHelper extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table myexpense(" +                  //表名设置为myexpense
                "ids integer PRIMARY KEY autoincrement," +   //设置id自增
                "money text," +                              //设置金额为文本类型
                "date text," +                              //设置时间为文本类型
                "type text," +                              //设置类型为文本类型
                "payer text," +                              //设置付款方为文本类型
                "remark text)");                              //设置备注为文本类型
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ExpenseDBOpenHelper(Context context) {
        super(context, "myexpense", null, 1);
    }
}
