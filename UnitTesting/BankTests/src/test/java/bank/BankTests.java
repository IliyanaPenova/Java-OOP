package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.TestCase.*;

public class BankTests {

        private Bank bank;
        private Client client1;
        private Client client2;

        @Before
        public void setUp() {
            bank = new Bank("Test Bank", 2);
            client1 = new Client("John");
            client2 = new Client("Alice");
        }

        @Test
        public void testGetName() {
            Assert.assertEquals("Test Bank", bank.getName());
        }

        @Test(expected = NullPointerException.class)
        public void testSetNameNull() {
           Bank bank=new Bank(null,5);

        }

        @Test(expected = NullPointerException.class)
       public void testSetNameEmpty() {
            Bank bank = new Bank("", 5);
        }
        @Test
        public void testGetCapacity() {
            Assert.assertEquals(2, bank.getCapacity());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testSetCapacityNegative() {
            Bank bank=new Bank("Bank",-1);
        }

        @Test
        public void testGetCount() {
            Assert.assertEquals(0, bank.getCount());
        }

        @Test
        public void testAddClient() {
            bank.addClient(client1);
            Assert.assertEquals(1, bank.getCount());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testAddClientExceedCapacity() {
            bank.addClient(client1);
            bank.addClient(client2);
            bank.addClient(new Client("Bobi"));
        }

        @Test
        public void testRemoveClient() {
            bank.addClient(client1);
            bank.removeClient("John");
            Assert.assertEquals(0, bank.getCount());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testRemoveClientNonExistent() {
            bank.removeClient("Alice");
        }

        @Test
        public void testLoanWithdrawal() {
            bank.addClient(client1);
            Client result = bank.loanWithdrawal("John");
            Assert.assertFalse(result.isApprovedForLoan());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testLoanWithdrawalNonExistent() {
            bank.loanWithdrawal("Alice");
        }

        @Test
        public void testStatistics() {
            bank.addClient(client1);
            bank.addClient(client2);
        }
    }




