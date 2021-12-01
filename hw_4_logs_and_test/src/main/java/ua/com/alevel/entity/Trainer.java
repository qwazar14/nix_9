package ua.com.alevel.entity;

public class Trainer extends BaseEntity {

    private String name;
    private String email;
    private String telefone;
    private int age;
    private Gym gym;

    private boolean gender;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", age=" + age +
                ", gym=" + gym +
                ", gender=" + gender +
                '}';
    }
}
