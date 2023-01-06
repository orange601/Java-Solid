# OCP (Open-Closed Principle: 개방 폐쇄 원칙) #
- 확장에 대해서는 개방(OPEN)되어야 하지만 변경에 대해서는 폐쇄(CLOSE)되어야 한다.
- [출처:우아한테크코스](https://www.youtube.com/watch?v=90ZDvHl8ROE)

## 👎 BAD ##
````java
// 로또 번호 추출기
public class LottoNumbersAutoGenerator {
	public List<Integer> generate(String shuffle) {
		// 변경이 되지 않는 코드
		List<Integer> numbers = new ArrayList<>();
	    	for(int i = 1; i <= 45; i++) {
	    		numbers.add(i);
	    	}
	    
		/** 변경이 발생하는 코드 */
	    	Collections.shuffle(numbers);

		// 변경이 되지 않는 코드
		return numbers.subList(0, 6);
	}
}
````
:no_entry_sign: Close 위반
````java
// 정렬 기능과 역순 기능을 추가하게 되면 비즈니스구간에 변경(CLOSE)이 발생한다.
public class LottoNumbersAutoGenerator {
	public List<Integer> generate(String shuffle) {
		// 변경이 되지 않는 코드
		List<Integer> numbers = new ArrayList<>();
	    	for(int i = 1; i <= 45; i++) {
	    		numbers.add(i);
	    	}
	  
		/** 변경이 발생하는 코드 */
		if(shuffle.equals("RANDOM")){
			Collections.shuffle(numbers);
		} else if(shuffle.equals("NOTHING")){ // 1 ~ 6
			Collections.sort(numbers);
		} else if(shuffle.equals("REVERSE")){ // 45 ~ 40
			Collections.reverse(numbers);
		}
			
		// 변경이 되지 않는 코드
		return numbers.subList(0, 6);
	}
}
````

## 👍 GOOD ## 

#### 모듈이 만나는 지점에 인터페이스 정의 ####
<img width="832" alt="98773008-5b5dce00-242b-11eb-9f09-149c6710d370" src="https://user-images.githubusercontent.com/24876345/210932368-1eb2116b-9810-4950-bebe-69fdf8cc475e.png">

````java
// 인터페이스 추가
@FunctionalInterface
public interface ShuffleStrategy {
	public List<Integer> shuffle(final List<Integer> numbers);
}
````
````java
// 인터페이스를 의존하도록 코드 작성 후 변경되는 부분을 인터페이스로 추출한다.
public class LottoNumbersAutoGenerator {
	private ShuffleStrategy shuffleStrategy; // 구현체에 의존하지 않도록 조심해야 한다.(DIP)
	
	public LottoNumbersAutoGenerator(ShuffleStrategy shuffleStrategy) {
		this.shuffleStrategy = shuffleStrategy;
	}
	
	public List<Integer> generate(String shuffle) {
		List<Integer> numbers = new ArrayList<>();
	    	for(int i = 1; i <= 45; i++) {
	    		numbers.add(i);
	    	}
		
		// 변경되는 부분을 인터페이스 함수로 사용한다.
	    	numbers = shuffleStrategy.shuffle(numbers);
			
		return numbers.subList(0, 6);
	}
}
````
````java
// 랜덤-구현체
public class ShuffleRandomStrategy implements ShuffleStrategy {
	@Override
	public List<Integer> shuffle(List<Integer> numbers) {
		Collections.shuffle(numbers);
		return numbers;
	}
}
````
````java
// 역순-구현체
public class ShuffleReverseStrategy implements ShuffleStrategy {
	@Override
	public List<Integer> shuffle(List<Integer> numbers) {
		Collections.reverse(numbers);
		return numbers;
	}
}
````
````java
// 메인
public class Application {
	public static void main(String[] args) {
		// client에서는 원하는 Shuffle(기능)을 추가하면 된다.
		LottoNumbersAutoGenerator lnag = new LottoNumbersAutoGenerator(new ShuffleRandomStrategy()); 
		List<Integer> numbers = lnag.generate();
		System.out.println(numbers);
	}
}
````

## 전략패턴 ##
- OCP를 준수하기 위해 여태 한 패턴이 전략 패턴이다.
- 디자인 패턴의 꽃
- Context, Strategy, ConcreteStrategy를 나누어서 별도로 분리하는 설계 방법

#### 1. Context ####
- Strategy 패턴을 이용하는 역할 수행
- 필요에 따라 동적으로 구체적인 전략을 바꿀수 있도록 한다.
- 여기에서 LottoNumbersAutoGenerator를 가리킨다.

#### 2. Strategy ####
- 인터페이스나 추상클래스로 외부에서 동일한 방식으로 알고리즘을 호출하는 방법을 명시한다.
- 여기에서 ShuffleStrategy 카리킨다.

#### 3. ConcreteStrategy ####
- 전략패턴에서 명시한 알고리즘을 실제로 구현한 클래스
- 여기에서 ShuffleRandomStrategy, ShuffleReverseStrategy 등의 구현체를 가리킨다.

![0_k-vRRyR0Ncx7NVSy](https://user-images.githubusercontent.com/24876345/210935137-2178dc58-2ec3-4076-a359-4aadde2383e1.png)


### 템플릿메서드패턴 VS 전략 패턴 ####
- 둘다 OCP를 준수하기 위한 패턴
- 상속을 사용한 방법이 템플릿메서드패턴이다.
- 컴포지션을 사용한 방법이 전략패턴이다.
