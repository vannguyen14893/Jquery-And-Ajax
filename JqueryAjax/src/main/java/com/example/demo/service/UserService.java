package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.UserFilterKeyword;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	@PersistenceContext
	EntityManager entityManager;
	public static final String LIST = "select u from User u ";
	public static final String SORT = "ORDER BY ";

	public List<User> fillAll(UserFilterKeyword filter) {
		StringBuilder builder = new StringBuilder(LIST);
		if (filter.getRoleId() != null) {
			builder.append("join u.roles r ");
		}

		if (filter.getRoleIds().length > 0) {
			builder.append("join u.roles r ");
		}

		builder.append(" WHERE 1=1 ");

		if (filter.getRoleId() != null) {
			builder.append(" AND r.roleId = '" + filter.getRoleId() + "' ");
		}
		if (filter.getRoleIds().length > 0) {
			builder.append(" AND r.roleId in :'(" + Arrays.asList(filter.getRoleIds()) + ")' ");
		}

		if (filter.getStatus() != null) {
			builder.append(" AND u.status = '" + filter.getStatus() + "'");
		}
		if (StringUtils.isNotBlank(filter.getFullName())) {
			builder.append(" AND LOWER(u.email) like '%" + filter.getFullName().toLowerCase()
					+ "%' or cast(u.status AS string) like '%" + filter.getFullName().toLowerCase() + "%' ");
		}
		if (!StringUtils.isNotBlank(filter.getSortName())) {
			builder.append(SORT + "u.userId");
		} else {
			builder.append(SORT + "u." + filter.getSortName() + "");
			builder.append(filter.getSort() ? " ASC" : " DESC");
		}
		Query query = entityManager.createQuery(builder.toString(), User.class);
		query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
		query.setMaxResults(filter.getPageSize());
		return query.getResultList();
	}

	public void delete(Integer userId) {
		repository.deleteById(userId);
	}

	public void addUser(User user) {
		User user2 = new User();
		user2.setEmail(user.getEmail());
		user2.setPassword(user.getPassword());
		repository.save(user2);
	}

	public User getUser(Integer userId) {
		return repository.getOne(userId);
	}

	public void updateUser(User user) {
		User user2 = repository.getOne(user.getUserId());
		if (user2 != null) {
			user2.setUserId(user.getUserId());
			user2.setEmail(user.getEmail());
			user2.setPassword(user.getPassword());
			repository.save(user2);
		}
	}

	public List<Role> fillAllByUserId() {
		return roleRepository.findAll();

	}
}
