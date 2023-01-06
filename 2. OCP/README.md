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
