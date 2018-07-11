package imli.me.mycoupon.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import imli.me.mycoupon.data.Student;


public class StudentDAO {
    private DBHelper helper;
    private SQLiteDatabase db;

    public StudentDAO(Context context) {
        helper = new DBHelper(context);
    }

    /**
     * 添加新的学生信息
     *
     * @param student
     */
    public void add(Student student) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into t_student (name, password,age) values (?,?,?)", new Object[]
                {student.getName(), student.password, student.getAge()});
    }

    /**
     * 更新学生信息
     *
     * @param student
     */
    public void update(Student student) {
        db = helper.getWritableDatabase();
        db.execSQL("update t_student set age = ?, password = ? where name = ?", new Object[]
                {student.password, student.getAge(), student.getName()});
    }

    /**
     * 查找学生信息
     *
     * @param name
     * @return
     */
    public Student find(String name) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name, password, age from t_student where name = ?", new String[] {name});
        if (cursor.moveToNext()) {
            return new Student(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("password")), cursor.getShort(cursor.getColumnIndex("age")));
        }
        return null;
    }

//    /**
//     * 刪除学生信息
//     *
//     * @param sids
//     */
//    public void detele(Integer... sids) {
//        if (sids.length > 0) {
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < sids.length; i++) {
//                sb.append('?').append(',');
//            }
//            sb.deleteCharAt(sb.length() - 1);
//            SQLiteDatabase database = helper.getWritableDatabase();
//            database.execSQL("delete from t_student where sid in (" + sb + ")", (Object[]) sids);
//        }
//    }
//
//    /**
//     * 获取学生信息
//     *
//     * @param start 其实位置
//     * @param count 学生数量
//     * @return
//     */
//    public List<Student> getScrollData(int start, int count) {
//        List<Student> students = new ArrayList<Student>();
//        db = helper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from t_student limit ?,?", new String[]{String.valueOf(start), String.valueOf(count)});
//        while (cursor.moveToNext()) {
//            students.add(new Student(cursor.getInt(cursor.getColumnIndex("sid")), cursor.getString(cursor.getColumnIndex("name")), cursor.getShort(cursor.getColumnIndex("age"))));
//        }
//        return students;
//    }
//
//    /**
//     * 获取学生数量
//     *
//     * @return
//     */
//    public long getCount() {
//        db = helper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select count(sid) from t_student", null);
//        if (cursor.moveToNext()) {
//            return cursor.getLong(0);
//        }
//        return 0;
//    }
}
