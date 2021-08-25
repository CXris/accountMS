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

import chris.accountms.ExpenseFolder.Expense;
import chris.accountms.ExpenseFolder.ExpenseDB;
import chris.accountms.ExpenseFolder.ExpenseDBHelper;
import chris.accountms.R;

public class MyExpenseActivity extends AppCompatActivity {
    ListView listView;
    LayoutInflater layoutInflater;
    ArrayList<Expense> arrayList;
    ExpenseDB expenseDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expense);
        listView = (ListView) findViewById(R.id.layout_listview);
        layoutInflater = getLayoutInflater();

        expenseDB = new ExpenseDB(this);
        arrayList = expenseDB.getarray();
        ExpenseDBHelper adapter = new ExpenseDBHelper(layoutInflater, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击一下跳转到编辑页面（编辑页面与新建页面共用一个布局）
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ExpenseActivity.class);
                intent.putExtra("ids", arrayList.get(position).getIds());
                startActivity(intent);
                MyExpenseActivity.this.finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按删除
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MyExpenseActivity.this)
                        .setMessage("确定要删除此支出信息？")

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })

                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                expenseDB.toDelete(arrayList.get(position).getIds());
                                ExpenseDBHelper expenseDBHelper = new ExpenseDBHelper(layoutInflater, arrayList);
                                listView.setAdapter(expenseDBHelper);
                                MyExpenseActivity.this.finish();
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });
    }

    public void addexpense(View v) {
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
        finish();
    }

    public void deleteallexpense(View v) {
        new AlertDialog.Builder(MyExpenseActivity.this)
                .setMessage("确定删除所有信息？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })

                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        expenseDB.toDeleteAllData();
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