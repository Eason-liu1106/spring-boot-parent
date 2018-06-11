package com.file;

import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/6/5 0005 18:49
 */
@Service
public class ServiceImpl implements Servie {

    @Override
    public void set() {
        System.out.println(1);
    }
}
