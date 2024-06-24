package org.example;

import org.example.duck.Duck;
import org.example.duck.FlyRocketPowered;
import org.example.duck.MallardDuck;
import org.example.duck.ModelDuck;

/**
 * Hello world!
 *
 */
public class DuckTest
{
    public static void main( String[] args )
    {
        // @desc 创建一个绿头鸭对象 在类创建的时候已经添加好属性和功能了
        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();
        // @desc 创建一个模型鸭 更改不会飞的属性
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
