package helpers;

import PagesTest.TestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserFactory extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("UserFactory.class");

    public static User getAlreadyRegisteredUser() {
        User alreadyRegisteredUser = new User.UserBuilder()
                .firstName(System.getProperty("firstName"))
                .lastName(System.getProperty("lastName"))
                .email(System.getProperty("email"))
                .password(System.getProperty("password"))
                .build();
        logger.info("Already Registered User data is: \nfirstname: " + alreadyRegisteredUser.getFirstName()
                + " \nlastname: " + alreadyRegisteredUser.getLastName()
                + " \nemail: " + alreadyRegisteredUser.getEmail()
                + "\npassword: " + alreadyRegisteredUser.getPassword());
        return alreadyRegisteredUser;
    }

    public static User getRandomUser(int firstNameLength, int lastNameLength, int passwordLength) {
        User user = new User.UserBuilder()
                .firstName(FakeDataCreator.generateFakeString(firstNameLength))
                .lastName(FakeDataCreator.generateFakeString(lastNameLength))
                .email(FakeDataCreator.generateFakeEmail())
                .password(FakeDataCreator.generateFakePassword(passwordLength))
                .build();
        logger.info("New random generated user data is: \nfirstname: " + user.getFirstName()
                + " \nlastname: " + user.getLastName()
                + " \nemail: " + user.getEmail()
                + "\npassword: " + user.getPassword());
        return user;
    }
}
