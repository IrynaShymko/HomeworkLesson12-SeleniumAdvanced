package Helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private static Logger logger = LoggerFactory.getLogger("User.class");

    public static class UserBuilder{
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public UserBuilder firstName(String firsName) {
            this.firstName = firsName;
            return this;
        }

        public UserBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email){
            this.email = email;
            return this;
        }

        public UserBuilder password(String password){
            this.password = password;
            return this;
        }

        public User build(){
           User user=new User();
           user.firstName = this.firstName;
           user.lastName = this.lastName;
           user.email = this.email;
           user.password = this.password;
           return user;
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
