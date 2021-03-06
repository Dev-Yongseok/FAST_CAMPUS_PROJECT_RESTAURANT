package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    // E-mail, Name , Password
    //201
    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    public void create() throws Exception {
        User mockUser = User.builder()
                .id(1004L)
                .email("Tester@example.com")
                .name("Tester")
                .password("Test")
                .build();

        given(userService.registerUser("Tester@example.com", "Tester", "Test")).willReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"Tester@example.com\", \"name\":\"Tester\",\"password\":\"Test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/users/1004"));

        verify(userService)
                .registerUser(eq("Tester@example.com"),eq("Tester"),eq("Test"));
    }
}