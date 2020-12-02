package reflection2;

@MyAnnotation(value = "hi")
public class Person2 extends Creature<String> implements Comparable<String> , MyInterface {

    private String name;
    int age;
    public int id;

    public Person2() {
    }


    @MyAnnotation(value="abc")
    private Person2(String name){
        this.name = name;
    }

    Person2(String name,int age){
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    public String display(String interests,int age) throws NullPointerException,ClassCastException{
        return interests + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    private static void showDesc(){
        System.out.println("我是一个可爱的人");
    }

    @Override
    public void info() {
        System.out.println("我是一个人");
    }



    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
