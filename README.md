# Java-Solid
:coffee: 객체지향 설계 5원칙 - SOLID


## 객체 지향 설계의 5원칙 (SOLID 원칙) ##
- 스프링은 제어의 역전, 의존관계 주입을 통해 다형성을 극대화해서 이용할 수 있게 도와준다.
- 스프링을 사용하면 역할과 구현을 편리하게 다룰 수 있다. 

### 1. SRP (Single Responsibility Principle: 단일 책임 원칙) ###
````
한 클래스는 하나의 책임만 가져야 한다.
````
- 책임이란 것에 대한 중요한 기준은 바로 "변경"이다. 변경이 생겼을 때 그에 따른 파급 효과가 적으면 SRP를 잘 따른 것이라고 볼 수 있다.

### 2. OCP (Open-Closed Principle: 개방 폐쇄 원칙) ###
````
소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
````
- 확장을 하는데 기존 코드를 변경하지 않을 수 있을까?
- 이를 위해서 다형성을 활용해야 한다.
- 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현한다면, 확장은 했지만 변경은 생기지 않는다.
- **역할**과 **구현**을 분리해서 생각해야 한다.

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


[출처](https://dct-wonjung.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9B%90%EB%A6%AC-%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5-SOLID)


