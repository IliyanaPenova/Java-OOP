package p02ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

public class ExtendedDataBaseTest {
    private Database database;
    private static final Person[] PEOPLE = {new Person(1, "Boris"), new Person(2, "Peter"), new Person(3, "Miroslav")};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    //1.конструктор
    //1.1 дали създава правилен обект
    @Test
    public void testConstructorHasCreateValidObject() {
// проверка на елементите -> елементите = зададените
        Person[] elements = database.getElements();
        //СРАВНЯВАМЕ МАСИВИТЕ
        Assert.assertArrayEquals(elements, PEOPLE);
        //дали броя на елементите, които взех от database, е същия като броя на ел, които подадох, за да се създаде Database
        Assert.assertEquals("Count of elements is incorrect!", elements.length, PEOPLE.length);
//стойностите на елементите
        for (int index = 0; index < elements.length; index++) {
            Assert.assertEquals("Arrays are not the same!", elements[index], PEOPLE[index]);
        }

    }

    //1.2 елементите > 16
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMoreThanSixteenElements() throws OperationNotSupportedException {
        Person[] people = new Person[17]; // създаваме масив със 17 елемента
        new Database(people);

    }

    //1.3 елементите <1
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenLessThanOneElement() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        new Database(people);
    }

    //2.аdd
    //2.1 успешно добавяме елемент
    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        database.add(new Person(4, "Desi"));
        Person[] people = database.getElements();
        Assert.assertEquals("Invalid add operation", people.length, PEOPLE.length + 1);
        Assert.assertEquals(Integer.valueOf(people[people.length - 1].getId()), Integer.valueOf(4));
        Assert.assertEquals(people[people.length - 1].getUsername(), "Desi");
    }

    //2.2 добавяне на null
    @Test(expected = OperationNotSupportedException.class)
    public void TestAddShouldThrowNullParam() throws OperationNotSupportedException {
        database.add(null);
    }

    //3.remove
    //3.1 успешно премахване на елемент
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Person[] people = database.getElements();
        Assert.assertEquals(people.length, PEOPLE.length - 1);
        Assert.assertEquals(Integer.valueOf(people[people.length - 1].getId()), Integer.valueOf(2));
        Assert.assertEquals(people[people.length - 1].getUsername(), "Peter");
    }

    //3.2 премахване на елемент от празна database(elements = [])
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowEmptyDatabase() throws OperationNotSupportedException {
        //премахване всички елементи

        database = new Database();

        //празна database - 0 елемента
        database.remove();
    }

    //4.findByUsername
    //4.1 ако подаваме null
    @Test(expected = OperationNotSupportedException.class)

    public void testFindByUsernameThrowNullParam() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    //4.2 ако подам валиден username
    @Test
    public void testFindByUsername() throws OperationNotSupportedException {
        Person person = database.findByUsername("Peter");
        Assert.assertEquals(2, person.getId());
        Assert.assertEquals("Peter", person.getUsername());
    }


    //4.3 ако нямаме хора в database

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowEmptyDatabase() throws OperationNotSupportedException {
        database = new Database();
        database.findByUsername("Peter");
    }

    //4.4 ако намерим повече от 2 човека с това име
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameMoreThanOnePerson() throws OperationNotSupportedException {
        database.add(new Person(4, "Miroslav"));
        database.findByUsername("Miroslav");

    }

    //4.5 ако нямаме човек с това име
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameInvalidName() throws OperationNotSupportedException {
        database.findByUsername("Pesho");

    } //5.findById

    // 5.1 ако подадем валидно id
    @Test
    public void testGetById() throws OperationNotSupportedException {
        Person person = database.findById(1);
        Assert.assertEquals(person.getId(), 1);
        Assert.assertEquals(person.getUsername(), "Boris");

    }

    //5.2 ако намерим повече от 1 човек с това id
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdMoreThanOneId() throws OperationNotSupportedException {
        database.add(new Person(2, "Pesho"));
        database.findById(2);
    }

    //5.3 празен database
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdEmptyData() throws OperationNotSupportedException {
        database = new Database();
        database.findById(2);
    }
}
