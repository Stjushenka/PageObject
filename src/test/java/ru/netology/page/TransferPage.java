package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


import static com.codeborne.selenide.Condition.text;


    public class TransferPage {
        private SelenideElement action = $("[data-test-id=action-transfer]");
        private SelenideElement amount = $("[data-test-id='amount'] input");
        private SelenideElement from = $("[data-test-id='from'] input");
        private static SelenideElement errorNotification = $("[data-test-id=error-notification]");

        public TransferPage() {
            amount.shouldBe(visible);
            from.shouldBe(visible);
            action.shouldBe(visible);
        }

        public DashboardPage transferFrom(String money, DataHelper.CardNumber cardNumber) {
            amount.setValue(money);
            from.setValue(String.valueOf(cardNumber));
            action.click();
            return new DashboardPage();

        }


        public static void checkErrorBalance() {
            errorNotification.shouldHave(text("Не достаточно баланса на карте"));
        }
    }
