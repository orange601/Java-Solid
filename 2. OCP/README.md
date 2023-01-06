# OCP (Open-Closed Principle: 개방 폐쇄 원칙) #
- 확장에 대해서는 개방(OPEN)되어야 하지만 변경에 대해서는 폐쇄(CLOSE)되어야 한다.

````java
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
