package com.example.user.schedulemanagementapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.schedulemanagementapp.Item.MemoItem;
import com.example.user.schedulemanagementapp.adapter.MemoListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //맴버변수 선언
    Context             context;
    RecyclerView        memoList;
    MemoListAdapter     memoListAdapter;
    LinearLayoutManager layoutManager;

    Spinner             categorySpinner;
    EditText            memoEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        setView();

    }//end onCreate()

    //사용자 정의 메소드 : View 설정
    private void setView() {
        categorySpinner = findViewById(R.id.category);
        memoEdit = findViewById(R.id.memo);
        Button registerButton = findViewById(R.id.register);

        //버튼 이벤트 연결
        registerButton.setOnClickListener(this);

        memoList = findViewById(R.id.recyclerView);
        setRecyclerView();  //사용자 정의 메소드 : recyclerView 설정
        setMemoListItem();  //사용자 정의 메소드 :
    }//end setView()


    //사용자 정의 메소드 : recyclerView 설정
    private void setRecyclerView(){
        layoutManager
                = new LinearLayoutManager(context,
                                          LinearLayoutManager.VERTICAL,
                               false);

        memoList.setLayoutManager(layoutManager);

        memoListAdapter
                = new MemoListAdapter(context, R.layout.row_memo, new ArrayList<MemoItem>());

        memoList.setAdapter(memoListAdapter);
    }//end setRecyclerView()

    //사용자 정의 메소드 : MemoListItem 설정
    private void setMemoListItem() {
        ArrayList<MemoItem> list = getMemoDummyList();
        memoListAdapter.addItemList(list);
    }//end setMemoListItem()

    private ArrayList<MemoItem> getMemoDummyList() {
        ArrayList<MemoItem> list = new ArrayList<>();

        MemoItem item1 = new MemoItem("일상","오늘 점심은 홍길동과 함께할 것");
        MemoItem item2 = new MemoItem("회사","오후 2시에 팀 회의 준비할 것");

        list.add(item1); list.add(item2);

        return list;
    }//end getMemoDummyList()

    @Override
    public void onClick(View v) {
        registerMemo();
    }//end onClick()



    private void registerMemo() {
        //getSelectedItem()메소드는 Spinner 객체에서 사용자가 선택한 item을 얻어온다.
        String category = (String)categorySpinner.getSelectedItem();
        String memo = memoEdit.getText().toString();

        if(TextUtils.isEmpty(memo)){
            Toast.makeText(context, "메모를 입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        addMemoItem(category, memo);

        //사용자 UX : 저장 후 초기화
        categorySpinner.setSelection(0);    //spinner 객체 "일상"으로 다시 설정
        memoEdit.setText("");               //memo 입력란 비우기

        //키보드 숨기기
        hideKeyboard();


    }//end registerMemo()

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm
                    = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }//end hideKeyboard()

    /*
        사용자가 앱을 다 사용하고 핸드폰에 있는 <- Back Button 키를 누르면
        종료 확인 다이얼로그 창 띄우기
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK :
                new AlertDialog.Builder(this)
                               .setTitle("일정관리 메모")
                               .setMessage("종료하시겠습니까?")
                               .setNegativeButton("예", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       finish();
                                   }
                               })
                               .setPositiveButton("아니오", null).show();
                return false;
            default:
                return false;
        }
    }//end onKeyDown()

    private void addMemoItem(String category, String memo) {
        MemoItem item = new MemoItem(category, memo);
        memoListAdapter.addItem(item);
    }//end addMemoItem()

}
