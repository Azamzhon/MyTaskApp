package com.geektech.mytaskapp.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.geektech.mytaskapp.Prefs;
import com.geektech.mytaskapp.R;
import com.geektech.mytaskapp.ui.interfaces.OnViewListener;


public class PageAdapter extends PagerAdapter {

    private  String[] titles = new String[]{"Hello", "Work", "Try it"};
    private  String[] desc = new String[]{"Welcome!", "Simply", "Let's GO!"};
    private int[] img = {R.drawable.pic_hello,R.drawable.pic_working, R.drawable.pic_lets_go};
    protected OnViewListener onViewListener;

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.page_board,container,false);
        container.addView(view);
        TextView textView = view.findViewById(R.id.textTitle);
        TextView textDesc = view.findViewById(R.id.textDesc);
        Button btnStart = view.findViewById(R.id.btnStart);
        ImageView imageView = view.findViewById(R.id.imageView);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Prefs(container.getContext()).setShown();
                onViewListener.onViewPagerClick();
            }
        });
        textView.setText(titles[position]);
        textDesc.setText(desc[position]);
        if (position == 0 || position == 1){
            btnStart.setVisibility(View.GONE);
        }
        imageView.setImageResource(img[position]);
        return view;
    }

    public void setOnViewClickListener(OnViewListener onViewClickListener) {
        this.onViewListener = onViewClickListener;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}