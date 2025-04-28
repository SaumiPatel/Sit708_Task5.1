package com.example.sit708_task5_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sit708_task5_1.R;
import com.example.sit708_task5_1.adapter.NewsAdapter;
import com.example.sit708_task5_1.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment implements NewsAdapter.OnNewsClickListener {
    private ImageView newsImage;
    private TextView newsTitle;
    private TextView newsDescription;
    private RecyclerView relatedNewsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsImage = view.findViewById(R.id.newsImage);
        newsTitle = view.findViewById(R.id.newsTitle);
        newsDescription = view.findViewById(R.id.newsDescription);
        relatedNewsRecyclerView = view.findViewById(R.id.relatedNewsRecyclerView);

        if (getArguments() != null) {
            String title = getArguments().getString("title", "");
            String description = getArguments().getString("description", "");
            int imageResource = getArguments().getInt("imageResource", R.drawable.tech_news);

            newsTitle.setText(title);
            newsDescription.setText(description);
            newsImage.setImageResource(imageResource);
        }

        setupRelatedNews();
    }

    private void setupRelatedNews() {
        List<News> relatedNews = new ArrayList<>();
        relatedNews.add(new News(
            "Industry Impact Analysis",
            "In-depth analysis of how recent developments are reshaping the industry landscape. Experts weigh in on long-term implications.",
            R.drawable.news_icon1,
            News.NewsType.RELATED_NEWS
        ));
        relatedNews.add(new News(
            "Market Response",
            "Markets react positively to recent developments. Analysts predict sustained growth in affected sectors.",
            R.drawable.news_graph_icon,
            News.NewsType.RELATED_NEWS
        ));
        relatedNews.add(new News(
            "Future Outlook",
            "Comprehensive look at future implications and upcoming developments in the field. Industry leaders share their predictions.",
            R.drawable.news_icon2,
            News.NewsType.RELATED_NEWS
        ));

        NewsAdapter adapter = new NewsAdapter(relatedNews, this);
        relatedNewsRecyclerView.setAdapter(adapter);
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