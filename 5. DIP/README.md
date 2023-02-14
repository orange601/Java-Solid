# DIP (Dependency Inversion Principle: 의존관계 역전 원칙) #
- 고차원 모듈은 저차원 모듈에 의존하면 안된다. 이 두 모듈 모두 다른 추상화된 것에 의존해야 한다.
- 추상화된 것은 구체적인 것에 의존하면 안된다. 구체적인 것이 추상화된 것에 의존해야 한다.
- 자주 변경되는 구체(Concrete) 클래스에 의존하지 마라.

## :-1: BAD ##

````java
class Truck {
    String drive() {
        return "Truck";
    }
}
class Van {
    String drive() {
        return "Van";
    }
}
// 차량의 종류가 추가될 때마다 setCar의 함수가 늘어날 수 밖에 없는 구조 OCP 위반
public class CarService {
    private Truck car1;
    private Van car2;
    
    public void setCar1(Truck car1) {    
        this.car1 = car1;
    }

    public void setCar2(Van car2) {
        this.car2 = car2;
    }

    public String drive1() {
        return car1.drive();
    }
    
    public String drive2() {
        return car2.drive();
    }
}
````

## :+1: GOOD ##

````java
public interface Car {
    String drive();
}
public class Truck implements Car {
    @Override
    public String drive() {
        return "Truck";
    }
}
public class Van implements Car {
    @Override
    public String drive() {
        return "Van";
    }
}
public class CarService {
    private Car car;	
    
    public void setCar(Car car) {    
        this.car = car;
    }

    public String drive() {
        return car.drive();
    }
    
    public void print() {
        System.out.println(car.drive());
    }
}
````
