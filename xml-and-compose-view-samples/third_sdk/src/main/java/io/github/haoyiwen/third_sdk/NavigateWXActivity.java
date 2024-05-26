package io.github.haoyiwen.third_sdk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class NavigateWXActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigate_wxactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.wx_button);
        button.setOnClickListener(v -> {
            // 此处需要自己申请的微信小程序ID 因为没有申请微信小程序id 所以无法跳转测试
            String appId = "wx5f0297d2af7b2920"; // 你的微信应用ID
            IWXAPI api = WXAPIFactory.createWXAPI(this, appId);

            if (!api.isWXAppInstalled()) {
                Toast.makeText(this, "未安装微信", Toast.LENGTH_SHORT).show();
                return;
            }

            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = "gh_72a4eb2d4324"; // 小程序原始ID
            req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE; // 打开正式版
            api.sendReq(req);
        });

        Button new_activity = findViewById(R.id.new_activity);
        new_activity.setOnClickListener(v -> {
            Intent intent = new Intent(this, io.github.haoyiwen.third_sdk.NavigateWXActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}