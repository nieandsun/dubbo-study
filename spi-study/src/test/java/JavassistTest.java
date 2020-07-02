import javassist.*;
import org.apache.dubbo.common.compiler.support.JavassistCompiler;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * javassist的使用示例
 */
public class JavassistTest {

    /**
     * javassist动态生成类示例
     */
    @Test
    public void createClassObjByJavassist() throws NotFoundException, CannotCompileException,
            IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException {

        // ClassPool：Class对象的容器
        ClassPool pool = ClassPool.getDefault();

        // 通过ClassPool生成一个public类
        CtClass ctClass = pool.makeClass("com.enjoy.service.DemoImpl");

        // 添加属性 private String name
        CtField nameFild = new CtField(pool.getCtClass("java.lang.String"), "name", ctClass);
        nameFild.setModifiers(Modifier.PRIVATE);
        ctClass.addField(nameFild);

        // 添加属性 private int age
        CtField ageField = new CtField(pool.getCtClass("int"), "age", ctClass);
        ageField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ageField);

        // 为属性name和age添加getXXX和setXXX方法
        ctClass.addMethod(CtNewMethod.getter("getName", nameFild));
        ctClass.addMethod(CtNewMethod.setter("setName", nameFild));
        ctClass.addMethod(CtNewMethod.getter("getAge", ageField));
        ctClass.addMethod(CtNewMethod.setter("setAge", ageField));

        // 添加方法  void sayHello(String name) {...}
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "sayHello", new CtClass[]{}, ctClass);
        // 方法设置为PUBLIC
        ctMethod.setModifiers(Modifier.PUBLIC);
        // 方法体
        ctMethod.setBody("{\nSystem.out.println(\"hello \" + getName() + \" !!!\");\n}");
        ctClass.addMethod(ctMethod);

        //生成class类
        Class<?> clazz = ctClass.toClass();
        //创建对象
        Object obj = clazz.newInstance();
        //反射 执行方法sayHello
        obj.getClass().getMethod("setName", new Class[]{String.class})
                .invoke(obj, new Object[]{"nrsc"});
        obj.getClass().getMethod("sayHello", new Class[]{})
                .invoke(obj, new Object[]{});
    }

    /**
     * 通过JavassistCompiler动态生成类示例
     */
    @Test
    public void createClassByJavassistCompiler()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException {
        JavassistCompiler compiler = new JavassistCompiler();

        //(1)第一个参数为一个类组成的字符串
        //(2)第二个参数为一个加载器
        //直接通过compiler方法就可以获取字符串对应的类的class类型
        Class<?> clazz = compiler.compile(
                "public class DemoImpl implements DemoService {     " +
                        "public String sayHello(String name) {" +
                        "System.out.println(\"hello \" + name);     " +
                        "return \"Hello, \" + name ;" +
                        "}}",
                this.getClass().getClassLoader());

        //通过class类型创建实例对象
        Object obj = clazz.newInstance();
        //反射 执行方法sayHello
        obj.getClass().getMethod("sayHello", new Class[]{String.class}).invoke(obj, "yoyo");
    }

}
