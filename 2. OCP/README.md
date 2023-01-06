# OCP (Open-Closed Principle: 개방 폐쇄 원칙) #
- 확장에 대해서는 개방(OPEN)되어야 하지만 변경에 대해서는 폐쇄(CLOSE)되어야 한다.

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
:no_entry_sign: Close 위반
