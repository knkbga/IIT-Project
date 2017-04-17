package authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.om.mygame.Network;
import com.example.om.mygame.R;


public class Authenticate extends FragmentActivity {

    public static int reg_log;
    public static String url;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        viewPager = (ViewPager) findViewById(R.id.authenticate_pager);

        PagerAdapterAuthentication pagerAdapterAuthentication = new PagerAdapterAuthentication(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapterAuthentication);

        Button register = (Button) findViewById(R.id.RegisterButton);
        Button LogIn = (Button) findViewById(R.id.LogInButton);

        register.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                reg_log=0;
                Intent myIntent = new Intent(Authenticate.this, Network.class);
                startActivity(myIntent);
            }
        });
        LogIn.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                reg_log=1;
                Intent myIntent = new Intent(Authenticate.this, Network.class);
                startActivity(myIntent);
            }
        });

    }
}
