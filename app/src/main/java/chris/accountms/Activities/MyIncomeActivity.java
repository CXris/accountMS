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

import chris.accountms.IncomeFolder.Income;
import chris.accountms.IncomeFolder.IncomeDB;
import chris.accountms.IncomeFolder.IncomeDBHelper;
import chris.accountms.R;

public class MyIncomeActivity extends AppCompatActivity {
    ListView listView;
    LayoutInflater layoutInflater;
    ArrayList<Income> arrayList;
    IncomeDB incomeDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_income);
        listView = (ListView) findViewById(R.id.layout_listview);
        layoutInflater = getLayoutInflater();

        incomeDB = new IncomeDB(this);
        arrayList = incomeDB.getarray();
        IncomeDBHelper adapter = new IncomeDBHelper(layoutInflater, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击一下跳转到编辑页面（编辑页面与新建页面共用一个布局）
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), IncomeActivity.class);
                intent.putExtra("ids", arrayList.get(position).getIds());
                startActivity(intent);
                MyIncomeActivity.this.finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按删除
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MyIncomeActivity.this)
                        .setMessage("确定删除此条收入记录？")

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })

                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                incomeDB.toDelete(arrayList.get(position).getIds());
                                IncomeDBHelper incomeDBHelper = new IncomeDBHelper(layoutInflater, arrayList);
                                listView.setAdapter(incomeDBHelper);
                                MyIncomeActivity.this.finish();
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });
    }

    public void addincome(View v) {
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void deleteallincome(View v) {
        new AlertDialog.Builder(MyIncomeActivity.this)
                .setMessage("确定删除所有信息？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })

                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        incomeDB.toDeleteAllData();
                        finish();
                    }
                })
                .create()
                .show();
    }

    public void backtomain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}