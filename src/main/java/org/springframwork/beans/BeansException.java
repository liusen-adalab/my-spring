package org.springframwork.beans;

public class BeansException extends RuntimeException {

    public BeansException(String msg, Throwable e) {
        super(msg, e);
    }

    public BeansException(String msg){
        super(msg);
    }
}
