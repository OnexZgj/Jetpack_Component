package com.onexzgj.inspur.pageingsample.pagingpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onexzgj.inspur.pageingsample.R;

/**
 * des：
 * author：onexzgj
 * time：2020/5/4
 */
public class PagingProAdapter extends PagedListAdapter<ResponseArticle.DataBean.Article, PagingProAdapter.ViewHolder> {

    public Context mContext;

    protected PagingProAdapter(Context context) {
        super(new DiffUtil.ItemCallback<ResponseArticle.DataBean.Article>() {

            @Override
            public boolean areItemsTheSame(@NonNull ResponseArticle.DataBean.Article oldItem, @NonNull ResponseArticle.DataBean.Article newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull ResponseArticle.DataBean.Article oldItem, @NonNull ResponseArticle.DataBean.Article newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
        this.mContext= context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.tv_info);
        }

        public void bindData(ResponseArticle.DataBean.Article item) {
            nameView.setText(item.getTitle());
        }
    }

}
