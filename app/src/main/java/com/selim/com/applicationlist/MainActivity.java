package com.selim.com.applicationlist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList <AppData> arrayList;
    private String resultStr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.resultText);

        arrayList = getInstalledApps();
        AppData appData;
        Iterator <AppData> i = arrayList.iterator();
        while ((i.hasNext())){
            appData = i.next();
            resultStr+=appData.appName;
            resultStr+="  "+appData.packageName+"\n";
            System.out.println("Application Name: "+(appData.appName));
            System.out.println("Application Package: "+(appData.packageName));

        }

        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(resultStr);
    }

    public String getResultStr(){
        return "";
    }

    public ArrayList<AppData> getInstalledApps(){
        ArrayList<AppData> appsList = new ArrayList<>();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue;
                //this is to ignore the system apps. If you want to include the systems app, then comment out the if
            }
            AppData appData = new AppData();
            appData.appName = packageInfo.loadLabel(pm);
            appData.packageName = packageInfo.packageName;
            appData.appLogo = packageInfo.loadIcon(pm);
            appsList.add(appData);

        }
        return appsList;

    }

    private class AppData{
        public CharSequence appName;
        public String packageName;
        public Drawable appLogo;
    }
}
