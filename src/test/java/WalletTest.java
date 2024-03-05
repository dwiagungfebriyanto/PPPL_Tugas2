import org.example.Wallet;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WalletTest {

    private Wallet myWallet;

    @BeforeEach
    public void initMethod() {
        myWallet = new Wallet(null);
    }

    @AfterEach
    public void cleanMethod() {
        myWallet = null;
    }

    @Test
    void testWallet() {
        myWallet.setOwner("DAF");
        int moneyTotal = myWallet.getMoneys().keySet().stream().mapToInt(key -> myWallet.getMoneys().get(key)).sum();
        int coinTotal = myWallet.getCoins().keySet().stream().mapToInt(key -> myWallet.getCoins().get(key)).sum();

        assertAll(
                () -> assertEquals("DAF", myWallet.getOwner()),
                () -> assertEquals(3, myWallet.getCoins().size()),
                () -> assertEquals(7, myWallet.getMoneys().size()),
                () -> assertEquals(0, moneyTotal),
                () -> assertEquals(0, coinTotal),
                () -> assertEquals(0, myWallet.getCards().size())
        );
    }

    @Test
    void testAddCard() {
        myWallet.addCard("KTM");
        myWallet.addCard("Credit Card");

        assertAll(
                () -> assertNotEquals(0, myWallet.getCards().size()),
                () -> assertEquals(2, myWallet.getCards().size()),
                () -> assertTrue(myWallet.getCards().contains("KTM")),
                () -> assertTrue(myWallet.getCards().contains("Credit Card"))
        );
    }

    @Test
    void testTakeCard() {
        myWallet.addCard("KTM");
        myWallet.takeCard("KTM");

        assertAll(
                () -> assertEquals(0, myWallet.getCards().size()),
                () -> assertFalse(myWallet.getCards().contains("KTM"))
        );
    }

    @Test
    void testAddMoney() {
        myWallet.addMoney(1000);
        myWallet.addMoney(2000);
        myWallet.addMoney(5000);
        myWallet.addMoney(10000);
        myWallet.addMoney(20000);
        myWallet.addMoney(50000);
        myWallet.addMoney(100000);
        myWallet.addMoney(1);

        assertAll(
                () -> assertEquals(1, myWallet.getMoneys().get(1000)),
                () -> assertEquals(1, myWallet.getMoneys().get(2000)),
                () -> assertEquals(1, myWallet.getMoneys().get(5000)),
                () -> assertEquals(1, myWallet.getMoneys().get(10000)),
                () -> assertEquals(1, myWallet.getMoneys().get(20000)),
                () -> assertEquals(1, myWallet.getMoneys().get(50000)),
                () -> assertEquals(1, myWallet.getMoneys().get(100000)),
                () -> assertNull(myWallet.getMoneys().get(1))
        );
    }

    @Test
    void testAddCoin() {
        myWallet.addCoin(100);
        myWallet.addCoin(500);
        myWallet.addCoin(1000);
        myWallet.addCoin(1);

        assertAll(
                () -> assertEquals(1, myWallet.getCoins().get(100)),
                () -> assertEquals(1, myWallet.getCoins().get(500)),
                () -> assertEquals(1, myWallet.getCoins().get(1000)),
                () -> assertNull(myWallet.getCoins().get(1))
        );
    }

    @Test
    void testTakeCoins() {
        myWallet.addCoin(100);
        myWallet.addCoin(1000);
        myWallet.takeCoins(500);
        myWallet.takeCoins(1000);

        assertAll(
                () -> assertEquals(0, myWallet.getCoins().get(500),
                        "Koin tetap berkurang walaupun di dalam wallet jumlahnya 0"),
                () -> assertEquals(1, myWallet.getCoins().get(100)),
                () -> assertEquals(0, myWallet.getCoins().get(1000))
        );
    }

    @Test
    void testTakeMoneys() {
        myWallet.addMoney(1000);
        myWallet.addMoney(2000);
        myWallet.addMoney(5000);
        myWallet.addMoney(10000);
        myWallet.addMoney(20000);
        myWallet.addMoney(50000);
        myWallet.takeMoneys(100000);
        myWallet.takeMoneys(5000);

        assertAll(
                () -> assertEquals(0, myWallet.getMoneys().get(100000),
                        "Uang tetap berkurang walaupun di dalam wallet jumlahnya 0"),
                () -> assertEquals(0, myWallet.getMoneys().get(5000)),
                () -> assertEquals(1, myWallet.getMoneys().get(1000)),
                () -> assertEquals(1, myWallet.getMoneys().get(2000)),
                () -> assertEquals(1, myWallet.getMoneys().get(10000)),
                () -> assertEquals(1, myWallet.getMoneys().get(20000)),
                () -> assertEquals(1, myWallet.getMoneys().get(50000))
        );
    }

    @Test
    void testCalculateCoins() {
        myWallet.addCoin(100);
        myWallet.addCoin(500);
        myWallet.addCoin(1000);

        assertAll(
                () -> assertNotEquals(0, myWallet.calculateCoins()),
                () -> assertEquals(1600, myWallet.calculateCoins())
        );
    }

    @Test
    void testCalculateMoneys() {
        myWallet.addMoney(1000);
        myWallet.addMoney(5000);
        myWallet.addMoney(20000);
        myWallet.addMoney(100000);

        assertAll(
                () -> assertNotEquals(0, myWallet.calculateMoneys()),
                () -> assertEquals(126000, myWallet.calculateMoneys())
        );
    }

    @Test
    void testGetMoneyAvailable() {
        myWallet.addMoney(1000);
        myWallet.addMoney(2000);
        myWallet.addMoney(5000);
        myWallet.addMoney(10000);
        myWallet.addMoney(20000);
        myWallet.addMoney(50000);
        myWallet.addMoney(100000);
        myWallet.addCoin(100);
        myWallet.addCoin(500);
        myWallet.addCoin(1000);

        assertAll(
                () -> assertNotEquals(0, myWallet.getMoneyAvailable()),
                () -> assertEquals(189600, myWallet.getMoneyAvailable())
        );
    }
}