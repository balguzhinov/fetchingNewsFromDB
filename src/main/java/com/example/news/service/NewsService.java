package com.example.news.service;

import java.util.List;

import com.example.news.model.News;

public interface NewsService {
	News saveNews(News news);
	List<News> getAllNews();
	News getNewsById(long id);
	News updateNews(News news, long id);
	void deleteNews(long id);
}
