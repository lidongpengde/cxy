package com.cxy.test;

import com.alibaba.fastjson.JSONObject;
import com.cxy.dao.UserMapper;
import com.cxy.entity.*;

import com.cxy.service.Impl.JestService;
import org.elasticsearch.common.util.concurrent.PrioritizedEsThreadPoolExecutor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
    private ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    /**
     * 根据url获取domain
     *
     * @return
     * @throws IOException
     */

    public int parseDomain(Boolean b) {
        return b ? 1 : 2;

    }

    @Test
    public void main() throws IOException {
        for (int i = 0; i < 10; i++) {
            System.out.println(random01());
        }

    }

    public boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        ClassLoader loader = null;
        try {
            Class<?> userMapper = loader.loadClass("UserMapper");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return m.matches();
    }

    public int random01() {
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt(10);
        int result = 3;
        while (true) {
            if (i == 0 && j == 1) {
                return 1;
            } else if (i == 1 && j == 0) {
                return 0;
            } else {
                i = random.nextInt(10);
                j = random.nextInt(10);
                continue;
            }
        }
    }

    @Test
    public void testEs() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10, true);
        ExecutorService executorService = new ThreadPoolExecutor(2, 3, 1000, TimeUnit.SECONDS, arrayBlockingQueue);
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        // 创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            // 将任务执行结果存储到List中
            resultList.add(future);
        }
        executorService.shutdown();

        // 遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println(fs.get()); // 打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                executorService.shutdownNow();
                e.printStackTrace();
                return;
            }
        }

    }

    class TaskWithResult implements Callable<String> {
        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());

            return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
        }
    }
    class TaskException extends Exception {
        public TaskException(String message) {
            super(message);
        }
    }


    @Test
      public void testAop() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:aoptext.xml");
        JestService jestService = (JestService)ac.getBean("daoImpl");
        try {
            jestService.count(null,null,null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRevert() {
        Person person=new Person();
        person.setName("123");
        person.setAttachAddress("lidp");
        revertObject(person,PersonA.class, PersonB.class);
        PersonA personA=new PersonA();
        personA.setAttachAddress1("lidp");
        PersonB personB=new PersonB();
        personB.setName2("123");
        try {
            Object o=mergeObject(Person.class,personA,personB);
            Person p=(Person)o;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 一个转为多个对象
     * @param object
     * @param classes
     * @return
     */
    public List<Object> revertObject(Object object,Class ...classes){

        List<Object> list=new LinkedList<>();
        //将大对象转为map，存储值
        Map<String,String> map=objectToMap(object);
        //把大对象对应的key映射为小对象的key
        String className=object.getClass().getSimpleName();
        Map<String,String> newkeymap=new HashMap<>();
        for (String key:map.keySet()) {
            String newkey=new StringBuilder(className).append("-").append(key).toString();
            newkey=ColumnChangeService.getColumnChangeService("111","1","2").getObserveColumnName(className,key);
            newkeymap.put(newkey,map.get(key));

        }
        Set<Class> set=new HashSet<>();
/*        set.add(t1Class);
        set.add(t2Class);*/
        //遍历转化后的大对象key
            for (Class obj:classes){
            try {
                Object objectInstance =obj.newInstance();
                //遍历第一个小对象的key，如果相同，写该属性值进入第一个小对象
                //遍历第2个小对象的key，如果相同，写该属性值进入第2个小对象
                //遍历第3个小对象的key，如果相同，写该属性值进入第3个小对象
                BeanInfo beanInfo = Introspector.getBeanInfo(obj);
                PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
                for (int i = 0; i< propertyDescriptors.length; i++) {
                    PropertyDescriptor descriptor = propertyDescriptors[i];
                    String propertyName = descriptor.getName();
                    for (String key: newkeymap.keySet()) {
                        if (Objects.equals(key, propertyName)) {
                            Object value = newkeymap.get(key);
                            Object[] args = new Object[1];
                            args[0] = value;
                            try {
                                descriptor.getWriteMethod().invoke(objectInstance, args);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                list.add(objectInstance);

            } catch (Exception e) {
                e.printStackTrace();
            }
            }

        return list;
    }


    /**
     * 多个对象转为一个对象
     * @param cla
     * @param objects
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object mergeObject(Class cla,Object ...objects) throws Exception {
        Map map = new HashMap();
            Object bigInstance=cla.newInstance();
        for (Object obj:objects) {

                String className=obj.getClass().getSimpleName();
                BeanInfo beanInfo = null;
                try {
                    beanInfo = Introspector.getBeanInfo(obj.getClass());
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
                PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();

            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                String bigObjFieldName=ColumnChangeService.getColumnChangeService("111","1","2").getObserveColumnName(className,propertyName);
                //遍历大对象属性与bigObjFieldName对比，如果相同，则添加，如果不通，则不写

                BeanInfo bigBeanInfo = Introspector.getBeanInfo(cla);
                PropertyDescriptor[] bigPropertyDescriptors =  bigBeanInfo.getPropertyDescriptors();
                for (PropertyDescriptor des:bigPropertyDescriptors) {
                    if (des.getName().equals(bigObjFieldName)&&!propertyName.equals("class")){
                        Method readMethod = descriptor.getReadMethod();
                            //读出小对象对应属性的值
                            Object result = readMethod.invoke(obj, new Object[0]);
                            //将返回值写入大对象对应属性
                            des.getWriteMethod().invoke(bigInstance, result);
                    }
                }
            }
        }

        return bigInstance;
    }

    /**
     * 实体类转换为map集合
     * @param object
     * @return
     */
    public static Map objectToMap(Object object){

        Class clazz = object.getClass();
        Map map = new HashMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();

            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(object, new Object[0]);
                    if (result != null) {
                        map.put(propertyName, result);
                    } else {
                        map.put(propertyName, "");
                    }
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return map;
    }
}

