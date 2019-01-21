package service;

import testRPC.Product;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.List;


public class ReadDataUtil {
	
	
	public ReadData getInstance() {
		
		ReadData readData = (ReadData) Proxy.newProxyInstance(ReadData.class.getClassLoader(), 
				new Class<?>[] {ReadData.class}, 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//方法的名字
						String name = method.getName();
						if(name.equals("findProductById")) {
							Socket sc = new Socket("127.0.0.1", 10000);
							OutputStream out = sc.getOutputStream();
							InputStream in = sc.getInputStream();
							ObjectInputStream ois = new ObjectInputStream(in);
							Product p = (Product) ois.readObject();
							in.close();
							ois.close();
							out.close();
							sc.close();
							return p;
						}else {
							Socket sc = new Socket("127.0.0.1", 10000);
							OutputStream out = sc.getOutputStream();
							out.write(("SELECT * FROM p.dat WHERE name like "+args[0]).getBytes());
							InputStream in = sc.getInputStream();
							ObjectInputStream ois = new ObjectInputStream(in);
							List<Product> pList = (List<Product>) ois.readObject();
							in.close();
							ois.close();
							out.close();
							sc.close();
							
							return pList;
						}
					}
				});
		
		return readData;
	}
	

}
