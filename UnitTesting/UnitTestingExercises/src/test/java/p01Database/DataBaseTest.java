package p01Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;

public class DataBaseTest {

    public Database database;
    private static final Integer[] NUMBERS = {7, 45, 34, 12, 98, 23};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    //1.конструктор
    //1.1 дали създава правилен обект
    @Test
    public void testConstructorHasCreateValidObject() {
// проверка на елементите -> елементите = зададените
        Integer[] elements = database.getElements();
        //СРАВНЯВАМЕ МАСИВИТЕ
        Assert.assertArrayEquals(elements, NUMBERS);
        //дали броя на елементите, които взех от database, е същия като броя на ел, които подадох, за да се създаде Database
        Assert.assertEquals("Count of elements is incorrect!", elements.length, NUMBERS.length);
//стойностите на елементите
        for (int index = 0; index < elements.length; index++) {
            Assert.assertEquals("Arrays are not the same!", elements[index], NUMBERS[index]);
        }

    }

    //1.2 елементите > 16
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMoreThanSixteenElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17]; // създаваме масив със 17 елемента
        new Database(numbers);

    }

    //1.3 елементите <1
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenLessThanOneElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    //2.аdd
    //2.1 успешно добавяме елемент
    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        database.add(67);
        Integer[] elements = database.getElements();
        Assert.assertEquals("Invalid add operation", elements.length, NUMBERS.length + 1);
        Assert.assertEquals(elements[elements.length - 1], Integer.valueOf(67));
    }

    //2.2 добавяне на null елемент
    @Test(expected = OperationNotSupportedException.class)
    public void TestAddShouldThrowNullParam() throws OperationNotSupportedException {
        database.add(null);
    }

    //3.remove
    //3.1 успешно премахване на елемент
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Integer[] elements = database.getElements();
        Assert.assertEquals(elements.length, NUMBERS.length - 1);
        Assert.assertEquals(elements[elements.length - 1], Integer.valueOf(98));
    }

    //3.2 премахване на елемент от празна database(elements = [])
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowEmptyDatabase() throws OperationNotSupportedException {
        //премахване всички елементи
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        //празна database - 0 елемента
        database.remove();
    }
}
