import java.lang.reflect.InvocationHandler;
        import java.lang.reflect.Method;
        import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] arge) {
        //构造一个Person 接口的动态代理对象

        Person p1 = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("say")) {
                    System.out.println("我是动态代理");
                }
                if(method.getName().equals("eat")){
                    if(method.getParameterTypes().length>0){
                        System.out.println("吃的："+args[0]);
                    }else {
                        System.out.println("什么也没吃！");
                    }
                }
                return null;
            }
        });
        p1.say();
        p1.eat();
        p1.eat("火锅");


    }
}
