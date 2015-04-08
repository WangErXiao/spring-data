package com.yao.spring.redis.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yaozb on 15-4-8.
 */
public class IpUtils {
    public static long ipToInt(String strIp){
        if(StringUtils.isBlank(strIp))
            return 0;
        String [] int_array=strIp.split("\\.");
        if(int_array.length<4){
            return 0;
        }
        long sum=0;
        for (int i=3; i>-1;i--){
            sum+=(Long.parseLong(int_array[3-i])<<(8*i));
        }
        return sum;
    }
    public static void main(String[]args){
        System.out.println(IpUtils.ipToInt("47.153.128.0"));
        System.out.println(IpUtils.ipToInt("202.109.0.0"));

    }
}
