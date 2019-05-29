package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByParentId(Integer parentId);

	Comment findByUser_UserId(Integer userId);

	Comment findByIdAndUser_UserId(Integer id, Integer userId);

	Comment findByParentIdAndUser_UserId(Integer parentId, Integer userId);
}
