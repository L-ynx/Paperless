package at.fhtw.swen3.persistence.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInfoValidationTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUserInfo() {
        UserInfo userInfo = UserInfo.builder()
                .username("ValidUser")
                .password("Password1")
                .build();

        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
        assertEquals(0, violations.size(), "Validation should pass for a valid UserInfo");
    }

    @Test
    public void testInvalidUsername() {
        UserInfo userInfo = UserInfo.builder()
                .password("Password1")
                .build();

        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
        assertEquals(2, violations.size(), "Validation should fail for UserInfo with missing username");
    }

    @Test
    public void testInvalidPassword() {
        UserInfo userInfo = UserInfo.builder()
                .username("InvalidUser")
                .password("pass") // Invalid password, less than 8 characters
                .build();

        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
        assertEquals(1, violations.size(), "Validation should fail for UserInfo with invalid password");
    }

    @Test
    public void testInvalidPasswordPattern() {
        UserInfo userInfo = UserInfo.builder()
                .username("InvalidUser")
                .password("invalidpassword") // Invalid password pattern
                .build();

        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
        assertEquals(1, violations.size(), "Validation should fail for UserInfo with invalid password pattern");
    }
}
