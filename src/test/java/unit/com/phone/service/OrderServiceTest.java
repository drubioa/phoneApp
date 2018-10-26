package unit.com.phone.service;

import com.phone.domain.Order;
import com.phone.domain.Phone;
import com.phone.model.CustomerDto;
import com.phone.model.OrderItem;
import com.phone.model.OrderRequest;
import com.phone.service.OrderService;
import com.phone.service.dao.OrderDao;
import com.phone.service.dao.PhoneDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Spy
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private PhoneDao phoneDao;

    private OrderRequest orderRequest;

    private CustomerDto customerDto;

    private static final String NAME_EXAMPLE = "Anonymous";

    private static final String EXAMPLE = "Example";

    private static final String EMAIL_EXAMPLE = "aaa@aaa.com";

    private static final String PHONE_EXAMPLE = "666666666";

    private static final UUID INVALID_PHONE_ID = UUID.randomUUID();

    private static final UUID VALID_PHONE_ID_1 = UUID.randomUUID();

    private static final UUID VALID_PHONE_ID_2 = UUID.randomUUID();

    private static final BigDecimal PRICE_1 = BigDecimal.valueOf((long) Math.random());

    private static final BigDecimal PRICE_2 = BigDecimal.valueOf((long) Math.random());

    @Test
    public void createOrder() {
        OrderRequest orderRequest = prepareValidTest();
        Phone phone1 = new Phone();
        phone1.setId(VALID_PHONE_ID_1);
        phone1.setPrice(PRICE_1);
        phone1.setName(EXAMPLE);
        phone1.setReference(EXAMPLE);
        when(phoneDao.findById(VALID_PHONE_ID_1)).thenReturn(phone1);

        Phone phone2 = new Phone();
        phone2.setId(VALID_PHONE_ID_2);
        phone2.setPrice(PRICE_2);
        phone2.setName(EXAMPLE);
        phone2.setReference(EXAMPLE);
        when(phoneDao.findById(VALID_PHONE_ID_2)).thenReturn(phone2);
        Order order = new Order();
        order.setId(UUID.randomUUID());
        when(orderDao.insertOrder(any(Order.class))).thenReturn(order);

        orderService.createOrder(orderRequest);

        verify(orderDao, times(1)).insertOrder(any());
    }

    /**
     * Try to create order with invalid phone identifier.
     */
    @Test( expected = EntityNotFoundException.class)
    public void createOrderWithInvalidPhone() {
        OrderRequest orderRequest = prepareTestOfInvalidItem();
        when(phoneDao.findById(INVALID_PHONE_ID)).thenReturn(null);
        orderService.createOrder(orderRequest);
    }

    private OrderRequest prepareValidTest() {
        orderRequest = new OrderRequest();

        List<OrderItem> items = new ArrayList<OrderItem>();
        OrderItem item1 = new OrderItem();
        item1.setNum(1);
        item1.setPhoneId(VALID_PHONE_ID_1.toString());
        items.add(item1);

        OrderItem item2 = new OrderItem();
        item2.setNum(1);
        item2.setPhoneId(VALID_PHONE_ID_2.toString());
        items.add(item2);

        addExampleCustomerToOrderRequest();
        orderRequest.setItems(items);
        return  orderRequest;
    }

    private OrderRequest prepareTestOfInvalidItem() {
        orderRequest = new OrderRequest();

        List<OrderItem> items = new ArrayList<OrderItem>();
        OrderItem item = new OrderItem();
        item.setNum(1);
        item.setPhoneId(INVALID_PHONE_ID.toString());
        items.add(item);

        addExampleCustomerToOrderRequest();
        orderRequest.setItems(items);
        return  orderRequest;
    }

    private void addExampleCustomerToOrderRequest(){
        customerDto = new CustomerDto();
        customerDto.setEmail(EMAIL_EXAMPLE);
        customerDto.setFirstSurname(NAME_EXAMPLE);
        customerDto.setSecondSurname(NAME_EXAMPLE);
        customerDto.setName(NAME_EXAMPLE);
        customerDto.setPhoneNumber(PHONE_EXAMPLE);
        orderRequest.setCustomerDto(customerDto);
    }
}
