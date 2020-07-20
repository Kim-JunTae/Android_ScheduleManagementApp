package com.example.user.schedulemanagementapp.Item;

import java.text.SimpleDateFormat;
//[주의] java.util.Date로 import
import java.util.Date;
import java.util.Locale;

public class MemoItem {
    public String category;
    public String memo;
    public String regDate;

    //생성자 구현
    public MemoItem(String category, String memo){
        //날짜 패턴 설정하여 작성시간 regDate에 초기화
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date date = new Date();
        regDate = formatter.format(date);

        this.category = category;
        this.memo = memo;
    }//end MemoItem 생성자


    //CRUD하려면 필요할 것 같은데? 안넣으심
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
