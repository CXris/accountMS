/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

import chris.accountms.NoteFolder.Note;
import chris.accountms.NoteFolder.NoteDB;
import chris.accountms.R;

public class NoteActivity extends AppCompatActivity {
    EditText ed_title;
    EditText ed_content;
    NoteDB noteDB;
    Note note;
    int ids;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ed_title = (EditText) findViewById(R.id.title);
        ed_content = (EditText) findViewById(R.id.content);
        noteDB = new NoteDB(this);
        Intent intent = this.getIntent();
        ids = intent.getIntExtra("ids", 0);
        if (ids != 0) {
            note = noteDB.getTiandCon(ids);
            ed_title.setText(note.getTitle());
            ed_content.setText(note.getContent());
        }
    }

    public void savenote(View v) {
        isSave();
    }

    @Override
    public void onBackPressed() {     //重写返回建方法，如果是属于新建则插入数据表并返回主页面，如果是修改，修改表中数据并返回主页面
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        String title = ed_title.getText().toString();
        String content = ed_content.getText().toString();
        if (ids != 0) {
            note = new Note(title, ids, content, time);
            noteDB.toUpdate(note);
            Intent intent = new Intent(NoteActivity.this, MyNoteActivity.class);
            startActivity(intent);
            NoteActivity.this.finish();
        }
        //新建日记
        else {
            note = new Note(title, content, time);
            noteDB.toInsert(note);
            Intent intent = new Intent(NoteActivity.this, MyNoteActivity.class);
            startActivity(intent);
            NoteActivity.this.finish();
        }
    }

    private void isSave() {   //写一个方法进行调用，如果是属于新建则插入数据表并返回主页面，如果是修改，修改表中数据并返回主页面
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH：mm");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        Log.d("new_note", "isSave: " + time);
        String title = ed_title.getText().toString();
        String content = ed_content.getText().toString();
        if (ids != 0) {
            note = new Note(title, ids, content, time);
            noteDB.toUpdate(note);
            Intent intent = new Intent(NoteActivity.this, MyNoteActivity.class);
            startActivity(intent);
            NoteActivity.this.finish();
        }
        //新建日记
        else {
            note = new Note(title, content, time);
            noteDB.toInsert(note);
            Intent intent = new Intent(NoteActivity.this, MyNoteActivity.class);
            startActivity(intent);
            NoteActivity.this.finish();
        }
    }

    public void backtomain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
