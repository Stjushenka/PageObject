package ru.netology.data;
import lombok.Value;
import java.util.Random;

    public class DataHelper {
        private DataHelper() {}

        @Value
        public static class AuthInfo {
            private String login;
            private String password;
        }

        public static AuthInfo getAuthInfo() {
            return new AuthInfo("vasya", "qwerty123");
        }


        @Value
        public static class VerificationCode {
            private String code;
        }

        public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
            return new VerificationCode("12345");
        }

        @Value
        public static class CardInfo {
            private String cardNumber;
        }

        public static CardInfo getFirstCardNumber() {
            return new CardInfo("5559 0000 0000 0001");
        }

        public static CardInfo getSecondCardNumber() {
            return new CardInfo("5559 0000 0000 0002");
        }

        public static int getAmountOfMoney (int cardBalance) {
            Random random = new Random();
            int transferAmount = random.nextInt(cardBalance);
            return transferAmount;
        }
    }
