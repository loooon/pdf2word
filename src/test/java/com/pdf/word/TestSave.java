package com.pdf.word;

import com.pdf.word.entity.User;
import com.pdf.word.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSave {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("john");
        user.setAddress("earth");
        userRepository.save(user);
    }

}