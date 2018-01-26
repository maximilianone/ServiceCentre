package application.controller.validation;

import application.model.entity.Order;
import application.model.exception.ModelException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class OrderStatusChangeValidatorTest {
    private static final String NEW = Order.Status.NEW.name();
    private static final String ACCEPTED = Order.Status.ACCEPTED.name();
    private static final String INVALID_STATUS = "INVALID";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionInvalidStatus() throws Exception{
        //Given
        thrown.expect(ModelException.class);
        //When
        OrderStatusChangeValidator.validateStatusChange(INVALID_STATUS, ACCEPTED);
    }

    @Test
    public void shouldThrowExceptionInvalidStatusChange() throws Exception{
        //Given
        thrown.expect(ModelException.class);
        //When
        OrderStatusChangeValidator.validateStatusChange(ACCEPTED, NEW);
    }

    @Test
    public void shouldVerifyChange() throws Exception{
        assertEquals(OrderStatusChangeValidator.validateStatusChange(NEW, ACCEPTED), true);
    }


}