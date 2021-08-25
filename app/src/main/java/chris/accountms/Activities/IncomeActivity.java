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

import chris.accountms.IncomeFolder.Income;
import chris.accountms.IncomeFolder.IncomeDB;
import chris.accountms.R;

public class IncomeActivity extends AppCompatActivity {
    EditText ed_money;
    EditText ed_date;
    EditText ed_type;
    EditText ed_payer;
    EditText ed_remark;
    IncomeDB incomeDB;
    Income income;
    int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        ed_money = (EditText) findViewById(R.id.money);
        ed_date = (EditText) findViewById(R.id.date);
        ed_type = (EditText) findViewById(R.id.type);
        ed_payer = (EditText) findViewById(R.id.payer);
        ed_remark = (EditText) findViewById(R.id.remark);
        incomeDB = new IncomeDB(this);
        Intent intent = this.getIntent();
        ids = intent.getIntExtra("ids", 0);
        if (ids != 0) {
            income = incomeDB.getCon(ids);
            ed_money.setText(income.getMoney());
            ed_date.setText(income.getDate());
            ed_type.setText(income.getType());
            ed_payer.setText(income.getPayer());
            ed_remark.setText(income.getRemark());
        }
    }

    private void isSave() {//写一个方法进行调用，如果是属于新建则插入数据表并返回主页面，如果是修改，修改表中数据并返回主页面
        String money = ed_money.getText().toString();
        String date = ed_date.getText().toString();
        String type = ed_type.getText().toString();
        String payer = ed_payer.getText().toString();
        String remark = ed_remark.getText().toString();

        if (ids != 0) {
            income = new Income(money, ids, date, type, payer, remark);
            incomeDB.toUpdate(income);
            Intent intent = new Intent(IncomeActivity.this, MyIncomeActivity.class);
            startActivity(intent);
            IncomeActivity.this.finish();
        }
        //新建
        else {
            income = new Income(money, date, type, payer, remark);
            incomeDB.toInsert(income);
            Intent intent = new Intent(IncomeActivity.this, MyIncomeActivity.class);
            startActivity(intent);
            Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
            IncomeActivity.this.finish();
        }
    }

    public void saveincome(View v) {
        isSave();
    }

    public void backtoin(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
