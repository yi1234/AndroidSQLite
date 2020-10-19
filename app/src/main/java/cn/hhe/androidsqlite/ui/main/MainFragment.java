package cn.hhe.androidsqlite.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.hhe.androidsqlite.R;

public class MainFragment extends Fragment implements View.OnClickListener {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private FeedReadAdapter feedReadAdapter;
    private FeedReaderDao feedReaderDao;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        feedReaderDao = new FeedReaderDao(getContext());
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Log.d("onActivityCreated", "onActivityCreated");
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.insert).setOnClickListener(this);
        view.findViewById(R.id.delete).setOnClickListener(this);
        view.findViewById(R.id.update).setOnClickListener(this);
        view.findViewById(R.id.query).setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        feedReadAdapter = new FeedReadAdapter(null);
        recyclerView.setAdapter(feedReadAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                feedReaderDao.insert("Ok","666","18");
                break;
            case R.id.delete:
                feedReaderDao.delete();
                break;
            case R.id.update:
                feedReaderDao.update();
                break;
            default:
                break;
        }
        List<FeedBean> query = feedReaderDao.query();
        Log.d("size","size = "+query.size());
        feedReadAdapter.setNewInstance(query);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        feedReaderDao.close();
    }
}