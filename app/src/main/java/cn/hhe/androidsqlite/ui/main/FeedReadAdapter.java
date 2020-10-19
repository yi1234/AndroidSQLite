package cn.hhe.androidsqlite.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.hhe.androidsqlite.R;
import cn.hhe.androidsqlite.databinding.FeedReadItemBinding;

/**
 * Create By zhongwen
 * on 2020/10/19
 * DataBinding 在 适配器中使用
 */
public class FeedReadAdapter extends BaseQuickAdapter<FeedBean, BaseViewHolder> {
    public FeedReadAdapter( @Nullable List<FeedBean> data) {
        super(R.layout.feed_read_item, data);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
        super.onItemViewHolderCreated(viewHolder, viewType);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FeedBean item) {
        if(item == null){
            return;
        }
        FeedReadItemBinding binding = helper.getBinding();
        if (binding != null) {
            binding.setModel(item);
            binding.executePendingBindings();
        }
    }
}
