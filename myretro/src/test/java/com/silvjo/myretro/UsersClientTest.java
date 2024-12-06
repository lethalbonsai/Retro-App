package com.silvjo.myretro;

import com.silvjo.myretro.client.User;
import com.silvjo.myretro.client.UsersClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class UsersClientTest {
    @Autowired
    UsersClient usersClient;

    @Test
    public void findUserTest() {
        User user = usersClient.findUserByEmail("nora@email.com");
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("Nora");
        assertThat(user.getEmail()).isEqualTo("nora@email.com");
    }
}
