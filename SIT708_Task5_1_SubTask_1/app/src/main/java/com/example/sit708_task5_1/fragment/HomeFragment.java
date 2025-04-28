package com.example.sit708_task5_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sit708_task5_1.R;
import com.example.sit708_task5_1.adapter.NewsAdapter;
import com.example.sit708_task5_1.model.News;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements NewsAdapter.OnNewsClickListener {
    private RecyclerView topStoriesRecyclerView;
    private RecyclerView newsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        topStoriesRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        newsRecyclerView = view.findViewById(R.id.newsRecyclerView);
        
        setupTopStories();
        setupNews();
    }

    private void setupTopStories() {
        List<News> topStories = new ArrayList<>();
        topStories.add(new News(
            "AI Revolution in Tech",
            "Revolutionary AI breakthrough changes how we interact with technology. New developments in machine learning promise to transform various industries.",
            R.drawable.news_icon1,
            News.NewsType.TOP_STORY
        ));
        topStories.add(new News(
            "Global Climate Summit",
            "World leaders gather to address urgent climate change measures. New agreements reached on reducing carbon emissions globally.",
            R.drawable.news_icon2,
            News.NewsType.TOP_STORY
        ));
        topStories.add(new News(
            "Medical Breakthrough",
            "Scientists announce major breakthrough in cancer research. New treatment shows promising results in early clinical trials.",
            R.drawable.news_graph_icon,
            News.NewsType.TOP_STORY
        ));

        NewsAdapter adapter = new NewsAdapter(topStories, this);
        topStoriesRecyclerView.setAdapter(adapter);
    }

    private void setupNews() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(
            "Championship Finals",
            "Unexpected victory in championship finals leaves fans stunned. Underdog team makes historic comeback in final minutes.",
            R.drawable.sport_news_icon,
            News.NewsType.REGULAR_NEWS
        ));
        newsList.add(new News(
            "Box Office Record",
            "Latest blockbuster breaks all-time box office records. Movie exceeds expectations with groundbreaking special effects.",
            R.drawable.news_icon2,
            News.NewsType.REGULAR_NEWS
        ));
        newsList.add(new News(
            "Startup Success",
            "Local tech startup receives record-breaking funding. Innovative solution attracts major investor interest.",
            R.drawable.news_icon1,
            News.NewsType.REGULAR_NEWS
        ));
        newsList.add(new News(
            "Space Discovery",
            "Mars mission reveals surprising discoveries. Scientists analyze new data from recent space exploration.",
            R.drawable.news_graph_icon,
            News.NewsType.REGULAR_NEWS
        ));

        NewsAdapter adapter = new NewsAdapter(newsList, this);
        newsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onNewsClick(News news) {
        NewsDetailFragment detailFragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", news.getTitle());
        args.putString("description", news.getDescription());
        args.putInt("imageResource", news.getImageResource());
        detailFragment.setArguments(args);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack(null)
                .commit();
    }
} 