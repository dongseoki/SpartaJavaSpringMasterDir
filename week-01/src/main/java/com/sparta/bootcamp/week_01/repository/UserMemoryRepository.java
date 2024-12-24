package com.sparta.bootcamp.week_01.repository;

import com.sparta.bootcamp.week_01.exception.ServiceException;
import com.sparta.bootcamp.week_01.exception.ServiceExceptionCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserMemoryRepository implements UserRepository{
  List<Map<String, String>> users = new ArrayList<>();

  @Override
  public int createUser(String name, String email) {
    users.add(Map.of("name", name, "email", email));
    return users.size()-1;
  }

  @Override
  public Map<String, String> getUser(int idx) {
    validateUser(idx);
    return users.get(idx);
  }


  @Override
  public void updateUser(int idx, String name, String email) {
    validateUser(idx);
    users.set(idx, Map.of("name", name, "email", email));
  }

  private void validateUser(int idx) {
    if(idx < 0 || idx >= users.size()) {
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_USER);
    }
  }

}

