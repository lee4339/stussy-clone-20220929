package com.stussy.stussyclone20220929.repository;

import com.stussy.stussyclone20220929.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {
    public int save(User user);
    public User findUserByEmail(String Email);
}
