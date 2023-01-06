# Java-Solid
:coffee: 객체지향 설계 5원칙 - SOLID


## SOLID 원칙 ##
- 객체 지향 설계의 5원칙

### 1. SRP (Single Responsibility Principle: 단일 책임 원칙) ###
````
한 클래스는 하나의 책임만 가져야 한다.
````
- 책임이란 것에 대한 중요한 기준은 바로 "변경"이다. 변경이 생겼을 때 그에 따른 파급 효과가 적으면 SRP를 잘 따른 것이라고 볼 수 있다.


### 2. OCP (Open-Closed Principle: 개방 폐쇄 원칙) ###
````
소프트웨어 구성 요소(컴포넌트, 클래스, 모듈, 함수)는 확장에 대해서는 개방(OPEN)되어야 하지만 변경에 대해서는 폐쇄(CLOSE)되어야 한다.
````
- 기존의 코드를 변경하지 않으면서 기능을 추가할 수 있도록 설계가 되어야 한다.

#### OCP 적용방법 ####
1. 상속 (is-a)
	- 코드 재사용이 편하지만, 상위클래스가 바뀌면 하위클래스에 끼치는 영향이 매우 크다는 의미다.
	- 설계가 유연하지 못하다. 컴파일 시점에 객체의 Type이 정해지기 때문이다.
	- 자바는 다중상속이 불가능. 다른 클래스를 상속받고있다면 추가적으로 상속을 받을 수 없다.
	- 캡슐화(속성과 행위를 하나로 묶고 구현 내용을 외부에 감춘다)를 위반할 수 있는여지가 있음

2. 컴포지션 (has-a)
	- 다른객체의 인스턴스를 자신의 인스턴스 변수로 포함해서 메서드를 호출하는 기법
	- 해당 인스턴스의 내부 구현이 바뀌더라도 영향을 받지 않는다.
	- 다른객체의 인스턴스이므로 인터페이스를 이용하면 Type을 바꿀 수 있다.

[참조:Effective-Java:상속보다는 컴포지션을 사용하라](https://github.com/orange601/Effective-Java/blob/main/%5Bitem-18%5D%20%EC%83%81%EC%86%8D%EB%B3%B4%EB%8B%A4%EB%8A%94%20%EC%BB%B4%ED%8F%AC%EC%A7%80%EC%85%98%EC%9D%84%20%EC%82%AC%EC%9A%A9%ED%95%98%EB%9D%BC./README.md)

#### 👎 BAD ####
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
- 다형성을 지원하기 위한 원칙이다. 
- 하위 클래스는 인터페이스의 규약을 모두 지켜야 한다.
- 단순히 컴파일을 성공한다고 LSP를 지켰다고 볼 수 없다. 
- 가격 책정 인터페이스의 '할인'이라는 기능을 가격이 상승하도록 구현하면 이 원칙을 위반하는 것이다.
- 아주 조금이라도 가격이 낮춰져야 한다.

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
프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.
````
- 클라이언트는 구현 클래스에 의존하지 말고, 인터페이스에만 의존해야 한다. 
- 그래서 유연하게 구현체를 변경할 수 있다. 구현제에 의존하게 되면 변경이 어려워진다.

#### :-1: BAD ####
````java
// Main함수는 Client이므로 핵심은 Driver 클래스이다.
public class Application {
	public static void main(String[] args) {
		// 어떤차를 탈지 마음대로 결정할 수 있음
		Driver driver = new Driver();
		driver.drive("트럭");
	}
}
````
````java
// 만약 캠핑카가 추가 된다면 if문에 추가 돼야한다.
public class Driver {
	private Truck truck;
	private Sports sports;
	public void drive(String type) {
		if(type.equals("트럭")) {
			truck = new Truck();
			System.out.println(truck.drive());
		} else if(type.equals("스포츠카")) {
			sports = new Sports();
			System.out.println(sports.drive());
		}
	}
}
````
````java
public class Truck {
	public String drive() {
		return "트럭운전";
	}
}
public class Sports {
	public String drive() {
		return "스포츠카운전";
	}
}
````

#### 물론 아래와 같이 제너릭을 이용해서 구현 할 수 있다. ####
````java
public class Application {
	public static void main(String[] args) {
		Driver<?> driver = new Driver<>(new Truck());
		String driving = driver.drive();
		System.out.println(driving);
	}
}
````
````java
public class Driver<T> {
	private Truck truck;
	private Sports sports;
	private String driving;
	public Driver(T t) {
		if(t instanceof Truck) {
			truck = new Truck();
			driving = truck.drive();
		} else if(t instanceof Sports) {
			sports = new Sports();
			driving = sports.drive();
		}
	}
	public String drive() {
		return driving;
	}
}
````

#### :+1: GOOD ####
````java
public class Application {
	public static void main(String[] args) {
		Driver driver = new Driver(new Truck());
		String driving = driver.drive();
		System.out.println(driving);
	}
}
````
````java
// 드라이버는 구현체가 아닌 추상화에 의존한다. DIP 만족
// 캠핑카가 추가 되어도(확장) Driver는 변경되지 않는다. OCP 만족
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

````java
// 구현체
public class Truck implements Car {
	public String drive() {
		return "트럭운전중입니다.";
	}
}
public class Sports implements Car {
	public String drive() {
		return "스포츠카운전중입니다.";
	}
}
````




