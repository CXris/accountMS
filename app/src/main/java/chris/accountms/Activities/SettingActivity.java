/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import chris.accountms.R;
import chris.accountms.UserFolder.User;
import chris.accountms.UserFolder.UserOperator;

public class SettingActivity extends AppCompatActivity {
    UserOperator settingUserOperator = null;
    EditText uname = null, psw = null, npsw1 = null, npsw2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void settingok(View v) {
        settingUserOperator = new UserOperator(SettingActivity.this);
        uname = (EditText) findViewById(R.id.edt_username);
        psw = (EditText) findViewById(R.id.edt_psw);
        npsw1 = (EditText) findViewById(R.id.edt_npsw1);
        npsw2 = (EditText) findViewById(R.id.edt_npsw2);

        String username = uname.getText().toString().trim();
        String password = psw.getText().toString().trim();
        String npassword1 = npsw1.getText().toString().trim();
        String npassword2 = npsw2.getText().toString().trim();

        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(npassword1) && !TextUtils.isEmpty(npassword2)) {
            if (npassword1.equals(npassword2)) {
                User bean = settingUserOperator.isExit(username);
                if (bean.password.equals(password)) {
                    bean.setPassword(npassword1);
                    settingUserOperator.updateUser(bean);
                    Toast.makeText(SettingActivity.this, "修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(SettingActivity.this, "原密码不正确", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SettingActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SettingActivity.this, "请补充完信息", Toast.LENGTH_SHORT).show();
        }
    }

    public void backtomain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
