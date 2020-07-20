package com.example.user.schedulemanagementapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.schedulemanagementapp.Item.MemoItem;
import com.example.user.schedulemanagementapp.R;

import java.util.ArrayList;

public class MemoListAdapter extends RecyclerView.Adapter<MemoListAdapter.ViewHolder>{
    //맴버변수 선언
    private Context             context;
    private int                 resource;
    private ArrayList<MemoItem> itemList;

    //생성자 구현
    public MemoListAdapter(Context context, int resource, ArrayList<MemoItem> itemList) {
        this.context = context;
        this.resource = resource;
        this.itemList = itemList;
    }

    //사용자 정의 메소드 구현
    public void addItem(MemoItem item){
        this.itemList.add(0, item);

        //아이템 추가된 것을 반영시킨다.
        notifyDataSetChanged();
    }//end addItem()

    //사용자 정의 메소드
    public void addItemList(ArrayList<MemoItem> itemList){
        this.itemList.addAll(itemList);

        //아이템 추가된 것을 반영시킨다.
        notifyDataSetChanged();
    }

    //getItemCount() : 처음 한번만 호출되고
    //onCreateViewHolder() - ViewHolder - onBindViewHolder() 순서로 계속 반복 호출됨

    //1. RecyclerView(리스트)에 보여 줄 객체 개수를 요청 : RecyclerView가 Adapter에게 요청
    @Override
    public int getItemCount() {
        return this.itemList.size();    //전체 아이템(메모) 갯수를 반환
    }//end getItemCount()

    //2.ViewHolder를 생성하여 리턴
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(resource, parent, false);

        return new ViewHolder(v);
    }//end onCreateViewHolder()


    //3.ViewHolder에 Item을 설정하는 메소드
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MemoItem item = itemList.get(position);

        holder.categoryText.setText(item.getCategory());
        holder.memoText.setText(item.getMemo());
        holder.dateText.setText(item.getRegDate());

        //클릭 시 Toast 메시지
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.memo, Toast.LENGTH_LONG).show();
            }
        });

    }//end onBindViewHolder()


    //내부 클래스 구현 : 뷰홀더 상속 및 구현
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryText;
        TextView memoText;
        TextView dateText;

        public ViewHolder(View itemView) {
            super(itemView);

            categoryText = itemView.findViewById(R.id.category);
            memoText = itemView.findViewById(R.id.memo);
            dateText = itemView.findViewById(R.id.regdate);

        }



    }//end ViewHolder 내부 클래스
}
