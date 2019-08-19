package com.skyworth.rxqwelibrary.managers;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by skyworth on 15/7/21.
 * 管理所有的Activity
 */
public class AppManagers {

    private static AppManagers instance = null;

    //--------------------------------------------------//
    //多线程时 不安全
    /* 私有构造方法，防止被实例化 */
    private AppManagers() {
        initStack();
    }

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static AppManagers getInstance() {
        if (instance == null) {
            instance = new AppManagers();
        }
        return instance;
    }
    //--------------------------------------------------//

    private Stack<Activity> stack;

    public void initStack(){
        stack = new Stack<Activity>();
    }

    //添加activity
    public void addActivity(Activity activity){
        stack.add(activity);
    }

    //获取顶层activity
    public Activity getTopActivity(){
        return stack.lastElement();
    }

    //杀死顶层activity
    public void KissTopActivity(){
        Activity activity = stack.lastElement();
        killActivity(activity);
    }

    //关闭指定activity
    public void killActivity(Activity activity){
        if(activity != null){
            stack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    //关闭指定类名的activity
    public void killClassActivity(Class<?> cla){
        for(Activity activity : stack){
            if(activity.getClass().equals(cla)){
                killActivity(activity);
            }
        }
    }

    //关闭所有activity
    public void killAllActivity(){
        int size = stack.size();

        for (int i=0;i<size;i++)
        {
            Activity activity = stack.get(0);
            killActivity(activity);
        }

        stack.clear();
    }

    //退出app
    public void exit(Context context){
        try {
            killAllActivity();

            ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

            activityManager.restartPackage(context.getPackageName());

            System.exit(0);

        }catch (Exception o){

        }
    }
}
