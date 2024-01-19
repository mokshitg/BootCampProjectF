package com.Stocks.BootCampProject.service.user;

import static org.junit.jupiter.api.Assertions.*;

import com.Stocks.BootCampProject.entity.User;
import com.Stocks.BootCampProject.error.UserNotFoundException;
import com.Stocks.BootCampProject.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTest {
  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;


  private User user;

  @BeforeEach
  void setUp() {


    user = User.builder()
        .userName("Mokshit")
        .userId(1)
        .build();
  }

  @Test
  void whenUserSaved_thenReturnSavedUser() {
    Mockito.when(userRepository.save(user)).thenReturn(user);

    User found = userService.saveUser(user);

    assertEquals(user,found);
  }

  @Test
  void whenRightIdProvided_thenReturnUser() throws UserNotFoundException {
    Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

    User found = userService.fetchUserById(1);

    assertEquals(found,user);
  }

  @Test
  void whenWrongIdProvided_thenThrowUserNotFoundException() throws UserNotFoundException {
    Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
//    Mockito.when(userRepository.findById(2)).thenReturn(Optional.ofNullable(user));

    assertThrows(UserNotFoundException.class,()->userService.fetchUserById(1));
  }


}