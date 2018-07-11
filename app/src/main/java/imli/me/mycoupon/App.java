package imli.me.mycoupon;

import android.app.Application;

import imli.me.mycoupon.data.Student;

public class App extends Application {

    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
