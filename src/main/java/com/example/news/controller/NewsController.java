package com.example.news.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.model.News;
import com.example.news.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {
	
	private NewsService newsService;

	public NewsController(NewsService newsService) {
		super();
		this.newsService = newsService;
	}
	
	// build create news REST API
	@PostMapping()
	public ResponseEntity<News> saveNews(@RequestBody News news){
		return new ResponseEntity<News>(newsService.saveNews(news), HttpStatus.CREATED);
	}
	
	// build get all news REST API
	@GetMapping
	public List<News> getAllNews(){
		return newsService.getAllNews();
	}
	
	// build get news by id REST API
	// http://localhost:8080/api/news/1
	@GetMapping("{id}")
	public ResponseEntity<News> getNewsById(@PathVariable("id") long newsId){
		return new ResponseEntity<News>(newsService.getNewsById(newsId), HttpStatus.OK);
	}
	
	// build update news REST API
	// http://localhost:8080/api/news/1
	@PutMapping("{id}")
	public ResponseEntity<News> updateNews(@PathVariable("id") long id
												  , @RequestBody News news){
		return new ResponseEntity<News>(newsService.updateNews(news, id), HttpStatus.OK);
	}
	
	// build delete news REST API
	// http://localhost:8080/api/news/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteNews(@PathVariable("id") long id){
		
		// delete news from DB
		newsService.deleteNews(id);
		
		return new ResponseEntity<String>("News deleted successfully!.", HttpStatus.OK);
	}
	
}
