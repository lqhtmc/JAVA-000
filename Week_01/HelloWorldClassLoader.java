import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义类加载器加载字节码文件
 */
public class HelloWorldClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Class kclass = new HelloWorldClassLoader().findClass("Hello");
            Method method = kclass.getMethod("hello");
            method.invoke(kclass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("E:/Forstudy/JAVA-000/Week_01/Hello.xlass");
        InputStream is = null;
        byte[] bytes = null;
        int length = 0;
        try {
            is = new FileInputStream(file);
            length = is.available();
            bytes = new byte[length];
            is.read(bytes);
            for(int i = 0; i < length; i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.defineClass(name, bytes, 0, length);
    }
}
