/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import chris.accountms.NoteFolder.Note;
import chris.accountms.NoteFolder.NoteDB;
import chris.accountms.NoteFolder.NoteDBHelper;
import chris.accountms.R;

public class MyNoteActivity extends AppCompatActivity {
    ListView listView;
    LayoutInflater layoutInflater;
    ArrayList<Note> arrayList;
    NoteDB noteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);
        listView = (ListView) findViewById(R.id.layout_listview);
        layoutInflater = getLayoutInflater();

        noteDB = new NoteDB(this);
        arrayList = noteDB.getarray();
        NoteDBHelper adapter = new NoteDBHelper(layoutInflater, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击一下跳转到编辑页面（编辑页面与新建页面共用一个布局）
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("ids", arrayList.get(position).getIds());
                startActivity(intent);
                MyNoteActivity.this.finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按删除
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MyNoteActivity.this)
                        .setMessage("确定要删除此便签？")

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })

                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                noteDB.toDelete(arrayList.get(position).getIds());
                                NoteDBHelper noteDBHelper = new NoteDBHelper(layoutInflater, arrayList);
                                listView.setAdapter(noteDBHelper);
                                MyNoteActivity.this.finish();
                            }
                        })

                        .create()
                        .show();
                return true;
            }
        });
    }

    public void addnote(View v) {
        Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
        startActivity(intent);
        MyNoteActivity.this.finish();
    }

    public void backtomain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
