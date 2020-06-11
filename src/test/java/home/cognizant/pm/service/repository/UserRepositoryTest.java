package home.cognizant.pm.service.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import home.cognizant.pm.service.entity.UserObject;
import home.cognizant.pm.service.repository.UserRepository;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Slf4j

@ActiveProfiles("test")

@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    @DisplayName("Setup Before any test")
    public static void beforeAnyTest() {
  //      log.debug("Nothing to setup before the tests");
    }

    @BeforeEach
    @DisplayName("Setup Before every test")
    public void beforeEachTest() {
    }

    @AfterEach
    @DisplayName("Tear down after every test")
    public void afterEachTest() {
    }

    @AfterAll
    @DisplayName("Tear down after all tests")
    public static void afterClass() {
    //    log.debug("Nothing a resource hog to tear down - will garbage collect automatically!");
    }

    @Test
    @DisplayName("UserRepository - Find USERs with Unique Employee Id")
    public void testFindUsersWithUniqueEmployeeId() {
        Set<UserObject> actualUsers = userRepository.findUsersWithUniqueEmployeeId();
        assertThat("Check data.sql", actualUsers.size(), equalTo(9));
    }
}
