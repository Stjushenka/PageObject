package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MoneyTransferTest {
    private String card1 = DataHelper.getCardNumber1().getNumber();
    private String card2 = DataHelper.getCardNumber2().getNumber();

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        String money = "3000";
        new DashboardPage().firstCard();
        new TransferPage().transferMoney(money, card2);
        int actualCard1 = new DashboardPage().getCardBalance(0);
        int actualCard2 = new DashboardPage().getCardBalance(1);
        int expectedCard1 = 13000;
        int expectedCard2 = 7000;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
        new DashboardPage().secondCard();
        new TransferPage().transferMoney(money, card1);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        String money = "10000";
        new DashboardPage().firstCard();
        new TransferPage().transferMoney(money, card2);
        int actualCard1 = new DashboardPage().getCardBalance(0);
        int actualCard2 = new DashboardPage().getCardBalance(1);
        int expectedCard1 = 20000;
        int expectedCard2 = 0;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
        new DashboardPage().secondCard();
        new TransferPage().transferMoney(money, card1);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllOverV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        String money = "11000";
        new DashboardPage().firstCard();
        new TransferPage().transferMoney(money, card2);
        int actualCard1 = new DashboardPage().getCardBalance(0);
        int actualCard2 = new DashboardPage().getCardBalance(1);
        int expectedCard1 = 21000;
        int expectedCard2 = -1000;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
        new DashboardPage().secondCard();
        new TransferPage().transferMoney(money, card1);
    }
}
