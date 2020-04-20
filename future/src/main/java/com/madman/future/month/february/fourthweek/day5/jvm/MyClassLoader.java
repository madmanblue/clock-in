package com.madman.future.month.february.fourthweek.day5.jvm;

import java.io.*;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String path = name.replace(".", "/").concat(".class");
        String jarPath = String.format("C:/Users/sunjianfeng/IdeaProjects/clock-in/future/target/classes/%s", path);
        File file = new File(jarPath);

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b = 0;
            while((b = in.read()) != 0){
                out.write(b);
            }
            byte[] bytes = out.toByteArray();
            out.close();
            in.close();
            return defineClass(path, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
