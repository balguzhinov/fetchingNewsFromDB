package com.example.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.news.exception.ResourceNotFoundException;
import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import com.example.news.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	private NewsRepository newsRepository;
	
	public NewsServiceImpl(NewsRepository newsRepository) {
		super();
		this.newsRepository = newsRepository;
	}

	@Override
	public News saveNews(News news) {
		return newsRepository.save(news);
	}

	@Override
	public List<News> getAllNews() {
		return newsRepository.findAll();
	}

	@Override
	public News getNewsById(long id) {
//		Optional<News> news = newsRepository.findById(id);
//		if(news.isPresent()) {
//			return news.get();
//		}else {
//			throw new ResourceNotFoundException("News", "Id", id);
//		}
		return newsRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("News", "Id", id));
		
	}

	@Override
	public News updateNews(News news, long id) {
		
		// we need to check whether news with given id is exist in DB or not
		News existingNews = newsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("News", "Id", id));

		existingNews.setTitle(news.getTitle());
		existingNews.setBody(news.getBody());
		// save existing news to DB
		newsRepository.save(existingNews);
		return existingNews;
	}

	@Override
	public void deleteNews(long id) {
		
		// check whether a news exist in a DB or not
		newsRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("News", "Id", id));
		newsRepository.deleteById(id);
	}
	
}
