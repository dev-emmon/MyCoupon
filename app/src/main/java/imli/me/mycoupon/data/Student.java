package imli.me.mycoupon.data;


public class Student {
    public String name;
    public String password;
    public short age;

    public Student() {
        super();
    }

    public Student(String name, String password, short age) {
        super();
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
