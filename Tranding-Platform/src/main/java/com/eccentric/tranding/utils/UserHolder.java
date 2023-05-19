/*
 * author:eccentric
 * time:2023/5/19
 */
package com.eccentric.tranding.utils;

import com.eccentric.tranding.pojo.User;

public class UserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user){
        tl.set(user);
    }

    public static User getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
