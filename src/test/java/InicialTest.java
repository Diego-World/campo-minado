import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InicialTest {
    @Test
    public void verificarJUnit(){
        Assertions.assertTrue(true);
    }
    @Test
    public void testarSeIgualATres(){
        int a = 1+1;
        Assertions.assertTrue(true);
        assertEquals(3,a);
    }
}
