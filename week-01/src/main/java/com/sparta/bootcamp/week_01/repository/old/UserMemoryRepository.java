package com.sparta.bootcamp.week_01.repository.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.bootcamp.week_01.dto.UserRequest;
import com.sparta.bootcamp.week_01.exception.ServiceException;
import com.sparta.bootcamp.week_01.exception.ServiceExceptionCode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserMemoryRepository implements UserRepository {

  private final ObjectMapper objectMapper;
  List<Map<String, Object>> users = new ArrayList<>();

  @Override
  public int createUser(String name, String email) {
    users.add(Map.of("name", name, "email", email));
    return users.size() - 1;
  }

  @Override
  public int createUser(UserRequest userRequest) {
    // change class to Map
    Map map = objectMapper.convertValue(userRequest, Map.class);
    // add createDt to map as  a LocalDateTime
    LocalDateTime now = LocalDateTime.now();
    map.put("createDt", now);
    users.add(map);
    return users.size() - 1;
  }

  @Override
  public Optional<Map<String, Object>> getUser(int idx) {
    validateUser(idx);
    return Optional.ofNullable(users.get(idx));
  }


  @Override
  public void updateUser(int idx, String name, String email) {
    validateUser(idx);
    users.set(idx, Map.of("name", name, "email", email));
  }


  private void validateUser(int idx) {
    if (idx < 0 || idx >= users.size()) {
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_USER);
    }
  }

}

