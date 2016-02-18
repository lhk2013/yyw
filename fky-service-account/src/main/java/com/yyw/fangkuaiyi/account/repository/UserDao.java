package com.yyw.fangkuaiyi.account.repository;

import com.yyw.fangkuaiyi.account.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * CrudRepository默认有针对实体对象的CRUD方法.
 * PagingAndSortingRepository默认有针对实体对象的CRUD与分页查询函数.
 *
 */
//public interface UserDao extends PagingAndSortingRepository<User, Long> {
public interface UserDao extends JpaRepository<User, Long> {

	User findById(Long id);
	User findByLoginName(String loginName);
}
