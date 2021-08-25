/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import chris.accountms.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newin(View v) {
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
    }

    public void newout(View v) {
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
    }

    public void sumin(View v) {
        Intent intent = new Intent(this, MyIncomeActivity.class);
        startActivity(intent);
    }

    public void sumout(View v) {
        Intent intent = new Intent(this, MyExpenseActivity.class);
        startActivity(intent);
    }

    public void data(View v) {
        Intent intent = new Intent(this, IncomeChartActivity.class);
        startActivity(intent);
    }

    public void note(View v) {
        Intent intent = new Intent(this, MyNoteActivity.class);
        startActivity(intent);
    }

    public void help(View v) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void set(View v) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void logout(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
    }
}