# Java-Solid
:coffee: 객체지향 설계 5원칙 - SOLID


## SOLID 원칙 ##
- 객체 지향 설계의 5원칙



### 1. SRP (Single Responsibility Principle: 단일 책임 원칙) ###
````
한 클래스는 하나의 책임만 가져야 한다.
````
- 책임이란 것에 대한 중요한 기준은 바로 "변경"이다. 변경이 생겼을 때 그에 따른 파급 효과가 적으면 SRP를 잘 따른 것이라고 볼 수 있다.
- [Sample-예제](https://github.com/orange601/Java-Solid/tree/main/1.%20SRP)




### 2. OCP (Open-Closed Principle: 개방 폐쇄 원칙) ###
````
확장에 대해서는 개방(OPEN)되어야 하지만 변경에 대해서는 폐쇄(CLOSE)되어야 한다.
````
- 기존의 코드를 변경하지 않으면서 기능을 추가할 수 있도록 설계가 되어야 한다.
- 전략패턴: OCP를 준수하기 위한 패턴이 전략 패턴이다.
- 참조해서 보면 좋은글: [상속보다는 컴포지션을 사용하라](https://github.com/orange601/Effective-Java/blob/main/%5Bitem-18%5D%20%EC%83%81%EC%86%8D%EB%B3%B4%EB%8B%A4%EB%8A%94%20%EC%BB%B4%ED%8F%AC%EC%A7%80%EC%85%98%EC%9D%84%20%EC%82%AC%EC%9A%A9%ED%95%98%EB%9D%BC./README.md)
- [자세한예제](https://github.com/orange601/Java-Solid/blob/main/2.%20OCP/README.md)

### 👎 BAD ###
````java
public class Application {
	public static void main(String[] args) {
		Driver driver = new Driver();
		String car = driver.drive("스포츠카");
		System.out.println(car);
	}
}
````
````java
// 차 종류가 생기면 if문이 추가된다. (변경이 발생)
public class Driver {
	public String drive(String type) {
		if(type.equals("트럭")) {
			return "수동운전";
		} else if(type.equals("스포츠카")) {
			return "자동운전";
		}
		return "그런종류의차는없어";
	}
}
````

### 👍 GOOD ###
````java
// client 구간이므로 여기서의 변경은 OCP와 무관하다.
public class Application {
	public static void main(String[] args) {
		Driver car = new Driver(new Truck()); // Client가 원하는 차량을 고른다.
		String drive = car.drive();
		System.out.println(drive);
	}
}
````

````java
// 구현체가 아닌 추상화에 의존할 수 있도록 Car 인터페이스를 추가한다.
public interface Car {
	public String drive();
}
````

````java
// 차량 종류가 추가 되어도(OPEN), 비즈니스 구간에는 변경(CLOSE)이 없다.
public class Driver {
	private Car car;
	public Driver(Car car) {
		this.car = car;
	}
	public String drive() {
		return car.drive();
	}
}
````

### 3. LSP (Liskov Subsititution Principle: 리스코프 치환 원칙) ###
````
프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
````
- 하위클래스 is a kind of 상위 클래스
- [sample-예제](https://github.com/orange601/Java-Solid/blob/main/3.%20LSP/README.md)


### 4. ISP (Interface Segregation Principle: 인터페이스 분리 원칙) ###
````
특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
````
- 추상화된 인터페이스라고 해서 너무 큰 단위로 만드는 것도 좋지 않다. 
- 인터페이스도 기능에 맞게 적당한 크기로 쪼개는 것이 중요하다.
- 예를 들어 자동차 인터페이스는 운전, 정비 인터페이스로 분리하고 사용자 클라이언트는 운전자, 정비사 클라이언트로 분리하게 되면, 정비 인터페이스가 변경되어도 운전자 클라이언트에 영향을 주지 않게 된다. 
- 이렇게 되면 인터페이스가 명확해지고, 대체 가능성이 높아진다(확장성이 좋아진다).


### 5. DIP (Dependency Inversion Principle: 의존관계 역전 원칙) ###
````
클라이언트는 구현 클래스에 의존하지 말고, 인터페이스에만 의존해야 한다.
````
- 유연하게 구현체를 변경할 수 있다. 구현체에 의존하게 되면 변경이 어려워진다.





