/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import chris.accountms.ExpenseFolder.Expense;
import chris.accountms.ExpenseFolder.ExpenseDB;
import chris.accountms.R;

public class ExpenseActivity extends AppCompatActivity {
    EditText ed_money;
    EditText ed_date;
    EditText ed_type;
    EditText ed_payer;
    EditText ed_remark;
    ExpenseDB expenseDB;
    Expense expense;
    int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        ed_money = (EditText) findViewById(R.id.emoney);
        ed_date = (EditText) findViewById(R.id.edate);
        ed_type = (EditText) findViewById(R.id.etype);
        ed_payer = (EditText) findViewById(R.id.epayer);
        ed_remark = (EditText) findViewById(R.id.eremark);
        expenseDB = new ExpenseDB(this);
        Intent intent = this.getIntent();
        ids = intent.getIntExtra("ids", 0);
        if (ids != 0) {
            expense = expenseDB.getCon(ids);
            ed_money.setText(expense.getMoney());
            ed_date.setText(expense.getDate());
            ed_type.setText(expense.getType());
            ed_payer.setText(expense.getPayer());
            ed_remark.setText(expense.getRemark());
        }
    }

    private void isSave() {//写一个方法进行调用，如果是属于新建则插入数据表并返回主页面，如果是修改，修改表中数据并返回主页面
        String money = ed_money.getText().toString();
        String date = ed_date.getText().toString();
        String type = ed_type.getText().toString();
        String payer = ed_payer.getText().toString();
        String remark = ed_remark.getText().toString();

        if (ids != 0) {
            expense = new Expense(money, ids, date, type, payer, remark);
            expenseDB.toUpdate(expense);
            Intent intent = new Intent(ExpenseActivity.this, MyExpenseActivity.class);
            startActivity(intent);
            ExpenseActivity.this.finish();
        }
        //新建
        else {
            expense = new Expense(money, date, type, payer, remark);
            expenseDB.toInsert(expense);
            Intent intent = new Intent(ExpenseActivity.this, MyExpenseActivity.class);
            startActivity(intent);
            Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
            ExpenseActivity.this.finish();
        }
    }

    public void saveexpense(View v) {
        isSave();
    }

    public void backtoex(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
