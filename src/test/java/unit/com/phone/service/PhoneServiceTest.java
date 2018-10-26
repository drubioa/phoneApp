package unit.com.phone.service;

import com.phone.domain.Phone;
import com.phone.model.PhoneDto;
import com.phone.service.PhoneService;
import com.phone.service.dao.PhoneDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceTest {

    @Spy
    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneDao phoneDao;

    private static List<Phone> phoneList;

    private static UUID PHONE_ID = UUID.randomUUID();

    @Test
    public void getPhoneCatalog() {
        preparaTest();
        when(phoneDao.findAll()).thenReturn(phoneList);
        List<PhoneDto> result = phoneService.getPhoneCatalog();
        verify(phoneDao, times(1)).findAll();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 1);
        PhoneDto phoneDto = result.get(0);
        verifyPhoneDto(phoneDto);

    }

    private void verifyPhoneDto(PhoneDto phoneDto) {
        Assert.assertEquals(PHONE_ID.toString(), phoneDto.getId());
        Assert.assertEquals("Example", phoneDto.getName());
        Assert.assertEquals("123.43", phoneDto.getPrice());
        Assert.assertEquals("Example", phoneDto.getReference());
    }

    private void preparaTest() {
        phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setId(PHONE_ID);
        phone.setName("Example");
        phone.setPrice(BigDecimal.valueOf(123.43));
        phone.setReference("Example");
        phoneList.add(phone);
    }
}
