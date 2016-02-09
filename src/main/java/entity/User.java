package entity;

import org.joda.time.DateTime;

/**
 * Created by xkey on 07.02.2016.
 */
public class User {

    private long id;
    private String email;
    private String name;
    private DateTime birthDay;

    public User(long id, String email, String name, DateTime birthDay) {
        this.id = id;
        this.email = email;
        this.name = name;
        setBirthDay(birthDay);
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(DateTime birthDay) {
        this.birthDay = birthDay.withMillisOfDay(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return !(birthDay != null ? !birthDay.equals(user.birthDay) : user.birthDay != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        return result;
    }
}
