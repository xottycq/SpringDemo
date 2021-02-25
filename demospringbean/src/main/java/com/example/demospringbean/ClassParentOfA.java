/**1.bean的父子继承主要是为了统一定义Spring Bean的公共属性、作业范围scope，并避免了冗余和修改的繁琐。
 *   如果希望父类只作模版用，不在容器中创建实例，可以在父类Bean中加入abstract=true属性或者定义lazy-init=true，也就是不让bean工厂实例化该bean
 *   子bean可以补充定义新的属性，也可以覆盖父bean定义的属性
 * 2.如果父bean有class属性，而子bean没有，那么子bean的class就和父bean相同；
 * 3.如果父bean没有定义class属性，子bean必须定义class属性，这时候父bean实际上仅仅是一个纯模版或抽象bean，仅仅充当子定义的父定义,
 * 不可以单独使用父bean
 */
package com.example.demospringbean;

public class ClassParentOfA {
    private String message;
    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("Parent Message : " + message);
    }

    public void init() {
        // do some initialization work
        System.out.println("Parent Initializing......");
    }

    public void destroy() {
        // do some destruction work
        System.out.println("Parent Destroying......");
    }
}
