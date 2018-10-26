package unit.com.phone.service;

import com.phone.service.ValidatorComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorComponentTest {

    @Spy
    @InjectMocks
    ValidatorComponent validatorComponent;

    @Test
    public void validateEmail() {
        String validEmail = "aaa@bbb.com";
        assertTrue(validatorComponent.validateEmailAddress(validEmail));
        String invalidEmail = "aaa";
        assertFalse(validatorComponent.validateEmailAddress(invalidEmail));
        assertFalse(validatorComponent.validateEmailAddress(""));
    }

    @Test
    public void validatePhoneNumber() {
        String[] validPhoneNumbers = {"666666666","966666666","866666666"};
        for(String phonenumber : validPhoneNumbers) {
            assertTrue(validatorComponent.validatePhoneNumber(phonenumber));
        }

        String[] invalidPhoneNumbers = {"166666666","9666",""};
        for(String invalidPhoneNumber : invalidPhoneNumbers) {
            assertFalse(validatorComponent.validatePhoneNumber(invalidPhoneNumber));
        }
    }
}
