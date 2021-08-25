/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.NoteFolder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NoteDB {
    Context context;
    NoteDBOpenHelper noteDBOpenHelper;
    SQLiteDatabase mydatabase;

    public NoteDB(Context context) {
        this.context = context;
        noteDBOpenHelper = new NoteDBOpenHelper(context);
    }

    public ArrayList<Note> getarray() {            //获取listview中要显示的数据
        ArrayList<Note> arr = new ArrayList<Note>();
        ArrayList<Note> arr1 = new ArrayList<Note>();
        mydatabase = noteDBOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select ids,title,times from mybook", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("ids"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String times = cursor.getString(cursor.getColumnIndex("times"));
            Note note = new Note(id, title, times);
            arr.add(note);
            cursor.moveToNext();
        }
        mydatabase.close();
        for (int i = arr.size(); i > 0; i--) {
            arr1.add(arr.get(i - 1));
        }
        return arr1;
    }

    public Note getTiandCon(int id) {           //获取要修改数据（就是当选择listview子项想要修改数据时，获取数据显示在新建页面）
        mydatabase = noteDBOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select title,content from mybook where ids='" + id + "'", null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        Note note = new Note(title, content);
        mydatabase.close();
        return note;
    }

    public void toUpdate(Note note) {           //修改表中数据
        mydatabase = noteDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL(
                "update mybook set title='" + note.getTitle() +
                        "',times='" + note.getTimes() +
                        "',content='" + note.getContent() +
                        "' where ids='" + note.getIds() + "'");
        mydatabase.close();
    }

    public void toInsert(Note note) {           //在表中插入新建的便签的数据
        mydatabase = noteDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("insert into mybook(title,content,times)values('"
                + note.getTitle() + "','"
                + note.getContent() + "','"
                + note.getTimes()
                + "')");
        mydatabase.close();
    }

    public void toDelete(int ids) {            //在表中删除数据
        mydatabase = noteDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("delete from mybook where ids=" + ids + "");
        mydatabase.close();
    }
}
