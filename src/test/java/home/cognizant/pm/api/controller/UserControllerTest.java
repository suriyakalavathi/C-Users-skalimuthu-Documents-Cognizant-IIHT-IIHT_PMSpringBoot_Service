package home.cognizant.pm.api.controller;

import home.cognizant.pm.api.mapper.UserMapper;
import home.cognizant.pm.api.model.UserResponse;
import home.cognizant.pm.service.api.UserService;
import home.cognizant.pm.service.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@Slf4j

@ActiveProfiles("test")

@WebMvcTest(UserController.class)
@MockBean({UserMapper.class, UserService.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class UserControllerTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private static User user;
    private static UserResponse userResponse;
    private static Set<User> users;
    private static List<UserResponse> userResponses;

    @BeforeAll
    @DisplayName("Setup Before any test")
    public void beforeAnyTest() {
        assertThat("userMapper is NOT injected", userMapper, is(notNullValue()));
        assertThat("userService is NOT injected", userService, is(notNullValue()));

        user = new User(1, 1, "Demo", "User");
        userResponse = new UserResponse(1, 1, "Demo", "User");
        users = Stream.of(user).collect(Collectors.toSet());
        userResponses = Arrays.asList(new UserResponse[]{userResponse});
    }

    @BeforeEach
    @DisplayName("Setup Before every test")
    public void beforeEachTest() {}

    @AfterEach
    @DisplayName("Tear down after every test")
    public void afterEachTest()  {}

    @AfterAll
    @DisplayName("Tear down after all tests")
    public void afterClass() {
      //  log.debug("Nothing a resource hog to tear down - will garbage collect automatically!");
    }

    @Test
    @DisplayName("UserController - GET - /users/{userId}")
    public void testGet() throws Exception {
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);
        when(userService.get(1)).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertThat("Expected Results", mvcResult.getResponse().getStatus(), is(200));
        assertThat("Invalid UserResponse", mvcResult.getResponse().getContentAsString(), is("{\"userId\":1,\"employeeId\":1,\"firstName\":\"Test\",\"lastName\":\"User\"}"));
    }

    @Test
    @DisplayName("UserController - GET - /users")
    public void testGetAll() throws Exception {
        when(userMapper.toUserResponse(users)).thenReturn(userResponses);
        when(userService.getAllWithUniqueEmployeeId()).thenReturn(users);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertThat("Expected Results", mvcResult.getResponse().getStatus(), is(200));
        assertThat("Invalid List<UserResponse>", mvcResult.getResponse().getContentAsString(), is("[{\"userId\":1,\"employeeId\":1,\"firstName\":\"Test\",\"lastName\":\"User\"}]"));
    }

    @Test
    @Disabled
    @DisplayName("UserController - POST - /users")
    public void testAdd() {
    }

    @Test
    @Disabled
    @DisplayName("UserController - PUT - /users/{userId}")
    public void testEdit() {
    }

    @Test
    @Disabled
    @DisplayName("UserController - DELETE - /users/{userId}")
    public void testDelete() {
    }

    @Test
    @Disabled
    @DisplayName("UserController - GET - /users/{managerId}")
    public void testGetManagers() {
    }
}
