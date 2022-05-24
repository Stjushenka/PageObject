package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferMoneyBetweenOwnCardsV1() {
        var dashboardPage = new DashboardPage();
        int balanceFirstCard = dashboardPage.getCardBalance(0);
        int balanceSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.firstCard();
        var infoCard = DataHelper.getCardNumber2();
        String money = "3000";
        transferPage.transferFrom(money, infoCard);
        int expectedCard1 = 13000;
        int expectedCard2 = 7000;
        assertEquals(balanceFirstCard + Integer.parseInt(money), dashboardPage.getCardBalance(0));
        assertEquals(balanceSecondCard - Integer.parseInt(money), dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllV1() {
        var dashboardPage = new DashboardPage();
        int balanceFirstCard = dashboardPage.getCardBalance(0);
        int balanceSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.firstCard();
        var infoCard = DataHelper.getCardNumber2();
        String money = "10000";
        transferPage.transferFrom(money, infoCard);
        int expectedCard1 = 20000;
        int expectedCard2 = 0;
        assertEquals(balanceFirstCard + Integer.parseInt(money), dashboardPage.getCardBalance(0));
        assertEquals(balanceSecondCard - Integer.parseInt(money), dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllOverV1() {
        var dashboardPage = new DashboardPage();
        int balanceFirstCard = dashboardPage.getCardBalance(0);
        int balanceSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.firstCard();
        var infoCard = DataHelper.getCardNumber2();
        String money = "11000";
        transferPage.transferFrom(money, infoCard);
        int expectedCard1 = 21000;
        int expectedCard2 = -1000;
        TransferPage.checkErrorBalance();
    }
    }

