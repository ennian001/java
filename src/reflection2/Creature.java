package reflection2;

import java.io.Serializable;

public class Creature<T> implements Serializable {

    public char gender ;
    private double weight ;



    public void breath(){
        System.out.println("生物呼吸");
    }

    public void eat() {
        System.out.println("生物吃东西");
    }
}
