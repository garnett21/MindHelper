package com.example.mindhelper.mindhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Tcr_Activity extends AppCompatActivity {

    // ListView顯示資料用的畫面元件陣列
    private static final int[] IDS =
            { R.id.id_listview, R.id.datetime_listview, R.id.note_listview };

    private static final int INSERT_REQUEST_CODE = 0;
    private static final int UPDATE_REQUEST_CODE = 1;
    private static final int SEARCH_REQUEST_CODE = 2;
    private static final int SHOW_REQUEST_CODE = 2;
    private TcrDAO tcrDAO;
    private ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcr);

        // 讀取顯示資料用的ListView物件
        list_view = (ListView)findViewById(R.id.list_view);
        // 為ListView物件註冊Context Menu
        registerForContextMenu(list_view);

        // 建立資料存取物件
        tcrDAO = new TcrDAO(this);

        // 處理範例資料
        tcrDAO.sampleData(this);

        // 讀取與顯示資料
        refresh();

        processControllers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 取得載入選單用的MenuInflater物件
        MenuInflater menuInflater = getMenuInflater();
        // 呼叫inflate方法載入指定的選單資源，第二個參數是這個方法的Menu物件
        menuInflater.inflate(R.menu.menu_tcr, menu);
        // 回傳true選單才會顯示
        return true;
    }

    // 參數MenuItem是使用者選擇的選單項目物件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 取得選單項目的資源編號
        int id = item.getItemId();
        Intent intent = getIntent();
        final String name = intent.getStringExtra("p_name");
        final String username = intent.getStringExtra("p_account");
        final String password = intent.getStringExtra("p_password");
        final String age = intent.getStringExtra("p_auth");
        final int p_id = intent.getIntExtra("p_index", -1);
        final String doctor = intent.getStringExtra("p_doctor");
        final String sex = intent.getStringExtra("p_gender");
        final String email = intent.getStringExtra("p_email");
        final String address = intent.getStringExtra("p_address");

        switch (id) {
            // 讀取全部資料與顯示
            case R.id.refresh_menu:
                refresh();
                break;
            // 新增
            case R.id.insert_menu:
                Intent intentInsert = new Intent(this, Insert_Tcr_Q12.class);
                intentInsert.putExtra("p_name", name);
                intentInsert.putExtra("p_password", password);
                intentInsert.putExtra("p_account", username);
                intentInsert.putExtra("p_auth", age);
                intentInsert.putExtra("p_index", p_id);
                intentInsert.putExtra("p_doctor", doctor);
                intentInsert.putExtra("p_gender", sex);
                intentInsert.putExtra("p_email", email);
                intentInsert.putExtra("p_address", address);
                startActivityForResult(intentInsert, INSERT_REQUEST_CODE);
                break;
            // 搜尋
            case R.id.search_menu:
                Intent intentSearch = new Intent(this, Search_Tcr.class);
                startActivityForResult(intentSearch, SEARCH_REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenuInfo menuInfo) {
        // 取得讀取選項資訊的AdapterContextMenuInfo物件
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)menuInfo;
        // 使用AdapterContextMenuInfo物件的編號取得資料
        Tcr selected = tcrDAO.get(info.id);
        // 設定選單標題
        menu.setHeaderTitle(selected.getNote());

        // 如果是ListView元件
        if (view == list_view) {
            // 取得載入選單用的MenuInflater物件
            MenuInflater menuInflater = getMenuInflater();
            // 呼叫inflate方法載入指定的選單資源，第二個參數是這個方法的Menu物件
            menuInflater.inflate(R.menu.menu_tcr_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 取得讀取選項資訊的AdapterContextMenuInfo物件
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        // 使用AdapterContextMenuInfo物件的編號取得資料
        final Tcr selected = tcrDAO.get(info.id);

        // 取得選單項目的資源編號
        int id = item.getItemId();

        switch (id) {
            // 修改資料
            case R.id.update_menu:
                Intent intentUpdate = new Intent(this, Update_Tcr.class);
                // 設定資料編號
                intentUpdate.putExtra("id", selected.getId());
                startActivityForResult(intentUpdate, UPDATE_REQUEST_CODE);
                break;
            // 刪除資料
            case R.id.delete_menu:
                // 建立詢問是否刪除的對話框
                final AlertDialog.Builder d = new AlertDialog.Builder(this);
                // 設定標題、訊息與不可取消
                d.setTitle("DELETE?")
                        .setMessage("Delete " + selected.getNote() + "?");

                // 加入按鈕
                d.setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // 刪除
                                tcrDAO.delete(selected.getId());
                                // 重新讀取與顯示
                                refresh();
                            }
                        });

                d.setNegativeButton(getString(android.R.string.cancel), null);

                // 顯示對話框
                d.show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 如果執行確定
        if (resultCode == Activity.RESULT_OK) {
            // 如果是搜尋資料
            if (requestCode == SEARCH_REQUEST_CODE) {
                // 取得搜尋日期
                String dateValue = data.getStringExtra("dateValue");
                // 查詢指定的日期
                Cursor cursor = tcrDAO.getSearchCursor(dateValue);
                // 建立給ListView元件使用的Adapter物件
                // 第一個參數是Context物件
                // 第二個參數是項目使用的畫面配置資源
                // 第三個參數是資料查詢後的Cursor物件
                // 第四個參數是項目顯示資料的欄位名稱陣列
                // 第五個參數是項目顯示資料的元件資源編號陣列
                // 第六個參數指定為CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
                SimpleCursorAdapter sca = new SimpleCursorAdapter(
                        this, R.layout.listview_tcr_activity,
                        cursor, TcrDAO.SHOW_COLUMNS, IDS,
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                // 設定ListView元件使用的Adapter物件
                list_view.setAdapter(sca);
            }
            else {
                refresh();
            }
        }
    }

    private void refresh() {
        // 查詢全部資料
        Cursor cursor = tcrDAO.getAllCursor();
        // 建立給ListView元件使用的Adapter物件
        // 第一個參數是Context物件
        // 第二個參數是項目使用的畫面配置資源
        // 第三個參數是資料查詢後的Cursor物件
        // 第四個參數是項目顯示資料的欄位名稱陣列
        // 第五個參數是項目顯示資料的元件資源編號陣列
        // 第六個參數指定為CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        SimpleCursorAdapter sca = new SimpleCursorAdapter(
                this, R.layout.listview_tcr_activity,
                cursor, TcrDAO.SHOW_COLUMNS, IDS,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 設定ListView元件使用的Adapter物件
        list_view.setAdapter(sca);
    }

    private void processControllers() {
        // 為ListView元件註冊項目點擊事件
        list_view.setOnItemClickListener(
                new OnItemClickListener() {
                    // 使用SimpleCursorAdapter的ListView元件，
                    // 在點擊項目後，第四個參數是資料的編號
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // 取得使用者點擊的物件
                        Tcr tcr = tcrDAO.get(id);
                        Intent intent = new Intent();
                        intent.putExtra("id", tcr.getId());
                        intent.setClass(Tcr_Activity.this, Show_Tcr.class);
                        startActivityForResult(intent, SHOW_REQUEST_CODE);
                        Toast.makeText(Tcr_Activity.this, tcr.getNote(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        // 關閉資料庫
        tcrDAO.close();
        super.onDestroy();
    }

}
