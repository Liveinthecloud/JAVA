public class Man implements Person{
    public String name;
    public int age;


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

    @Override
    public void say() {
        System.out.println("对象");
    }
    public  void eat(){
        System.out.println("吃饭");
    }
    public void eat(String food){
        System.out.println("吃的："+food);
    }
}
