package com.citytelecoin.basic;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        loadApplication();
        loadListView();
        Click();



    }




    public class AppDetail {
        CharSequence label;
        CharSequence name;
        Drawable icon;
    }



    private PackageManager manager;
    private List<AppDetail> apps;
    private void loadApplication(){
        manager = getPackageManager();
        apps = new ArrayList<AppDetail>();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }
    }


    private ListView list;
    private void loadListView(){
        list = (ListView)findViewById(R.id.apps_list);
        ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this,
                R.layout.list_item,
                apps) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                }
                ImageView appIcons = (ImageView)convertView.findViewById(R.id.item_app_icon);
                appIcons.setImageDrawable(apps.get(position).icon);
                TextView appLabels = (TextView)convertView.findViewById(R.id.item_app_label);
                appLabels.setText(apps.get(position).label);
                TextView appNames = (TextView)convertView.findViewById(R.id.item_app_name);
                appNames.setText(apps.get(position).name);
                return convertView;
            }
        };
        list.setAdapter(adapter);
    }




    private void Click(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos ,long id) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(pos).name.toString());
                Main2Activity.this.startActivity(i);
            }
        }); }












}
