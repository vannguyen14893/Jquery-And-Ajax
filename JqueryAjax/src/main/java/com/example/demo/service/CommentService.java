package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentRepository repository;
	@Autowired
	private UserRepository userRepository;

	public List<Comment> fillAll() {
		List<Comment> comments = new ArrayList<Comment>();
		List<Comment> commentDad = repository.findByParentId(0);
		for (Comment comment : commentDad) {
			comments.add(comment);
			List<Comment> commentsChild = repository.findByParentId(comment.getId());
			comments.addAll(commentsChild);
		}
		return comments;
	}


	public void addComment(Comment comment) {
		comment.setParentId(0);
		comment.setUser(userRepository.getOne(comment.getId()));
		repository.save(comment);
	}

	public void addCommentChild(Comment comment, Integer id) {
		comment.setParentId(id);
		comment.setUser(userRepository.getOne(comment.getId()));
		repository.save(comment);
	}

	public boolean checkButtonComment(Integer userId) {
		Comment comment = repository.findByUser_UserId(userId);
		if (comment != null) {
			return true;
		}
		return false;
	}

	public void editComment(Integer userId, Comment comment) {
		Comment comment2 = repository.findByIdAndUser_UserId(comment.getId(), userId);
		if (comment2 != null) {
			comment2.setId(comment.getId());
			comment2.setMessage(comment.getMessage());
			comment2.setParentId(comment.getParentId());
			comment2.setUser(userRepository.getOne(userId));
			repository.save(comment2);
		}
	}

	public void editCommentChild(Integer userId, Comment comment) {
		Comment comment2 = repository.findByParentIdAndUser_UserId(comment.getParentId(), userId);
		if (comment2 != null) {
			comment2.setId(comment.getId());
			comment2.setMessage(comment.getMessage());
			comment2.setParentId(comment.getParentId());
			comment2.setUser(userRepository.getOne(userId));
			repository.save(comment2);
		}
	}

	public void deleteComment(Integer id, Integer userId) {
		Comment comment = repository.findByIdAndUser_UserId(id, userId);
		if (comment != null) {
			List<Comment> comments = repository.findByParentId(comment.getId());
			for (Comment comment2 : comments) {
				repository.delete(comment2);
			}
			repository.delete(comment);
		}
	}

	public void deleteCommentChild(Integer id, Integer userId) {
		Comment comment = repository.findByIdAndUser_UserId(id, userId);
		if (comment != null) {
			repository.delete(comment);
		}
	}
}
