public class Main {
    public static void main(String[] args) {
        new Work().talk(new Use() {
            @Override
            public void say() {
                System.out.println("你好");
            }
        });
    }
}
