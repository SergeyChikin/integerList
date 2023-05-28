import exception.ArrayIsFullException;
import exception.ElementNotFoundException;
import exception.IndexNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerListTest {

    private IntegerListImpl integerList = new IntegerListImpl(10);

    @BeforeEach
    public void fillList() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
    }

    @AfterEach
    public void clearList() {
        integerList.clear();
    }

    @Test
    public void addTest() {
        int size = integerList.size();
        Assertions.assertEquals(6, integerList.add(6));
        Assertions.assertEquals(size + 1, integerList.size());
    }

    @Test
    public void indexAddTest() {
        int size = integerList.size();
        int index = 1;
        Assertions.assertEquals(6, integerList.add(index, 6));
        Assertions.assertEquals(index, integerList.indexOf(6));
        Assertions.assertEquals(size + 1, integerList.size());
    }

    @Test
    public void indexAddNegativeTest() {
        Assertions.assertThrows(IndexNotFoundException.class, () -> integerList.add(6, 6));
    }

    @Test
    public void setTest() {
        int size = integerList.size();
        int index = 1;
        Assertions.assertEquals(6, integerList.set(index, 6));
        Assertions.assertEquals(index, integerList.indexOf(6));
        Assertions.assertEquals(size, integerList.size());
    }

    @Test
    public void setNegativeTest() {
        Assertions.assertThrows(IndexNotFoundException.class, () -> integerList.set(6, 7));
    }

    @Test
    public void removeTest() {
        int size = integerList.size();
        Assertions.assertEquals(1, integerList.remove(Integer.valueOf(1)));
        Assertions.assertEquals(size - 1, integerList.size());
    }

    @Test
    public void removeNegativeTest() {
        Assertions.assertThrows(ElementNotFoundException.class, () -> integerList.remove(Integer.valueOf(7)));
    }

    @Test
    public void removeByIndexTest() {
        int size = integerList.size();
        Assertions.assertEquals(null, integerList.remove(0));
        Assertions.assertEquals(size , integerList.size());
    }
}
