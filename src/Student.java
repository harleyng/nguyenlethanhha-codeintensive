public class Student {
    // thuoc tinh
    String name;
    int id;
    String birthdate;
    String classroom;

    // ham tao
    public Student() {
        name = "John";
        id = 1;
        birthdate = "28/03/95";
        classroom = "KTPM";
    }

    public Student(String name, int id, String birthdate, String classroom) {
        this.name = name;
        this.id = id;
        this.birthdate = birthdate;
        this.classroom = classroom;
    }

    // phuong thuc
    public void eat() {
        System.out.println("Eating..");
    }

    public void learn() {
        System.out.println("Learning..");
    }
}
