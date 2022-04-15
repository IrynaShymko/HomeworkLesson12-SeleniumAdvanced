package helpers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class FakeDataCreator {
    private static Logger logger = LoggerFactory.getLogger("FakeDataCreator.class");

    public static String generateFakeString(int length){
        Faker usFaker = new Faker(new Locale("en-US"));
        return usFaker.regexify("[A-Za-z]{" +length+"}");
    }

    public static String generateFakeEmail(){
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return fakeValuesService.bothify("????##@?????.com");
    }

    public static String generateFakePassword(int length){
        Faker usFaker = new Faker(new Locale("en-US"));
        return usFaker.regexify("[a-z1-9]{" +length+"}");
    }
}
