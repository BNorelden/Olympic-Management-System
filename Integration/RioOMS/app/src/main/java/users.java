import com.google.firebase.firestore.Exclude;

public class users implements user {

    private String Id;
    private String pass;
    private String name;
    private String phoneNum;
    private int age;

    //todo
    //default constructor

    @Override
    public void setUserId(String Id) {

        this.Id = Id;

    }

    @Override
    public void setPassword(String pass) {

        this.pass = pass;

    }

    @Override
    public void setName(String name) {

        this.name = name;

    }

    @Override
    public void setPhoneNum(String phoneNum) {

        this.phoneNum = phoneNum;

    }

    @Override
    public void setAge(int age) {

        this.age = age;

    }

    @Override
    public void setTickets() {

    }

    @Override
    public String getUserId() {
        return this.Id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPhoneNum() {
        return this.phoneNum;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
