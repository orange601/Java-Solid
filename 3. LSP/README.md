### LSP (Liskov Subsititution Principle: 리스코프 치환 원칙) ###
- 하위클래스 is a kind of 상위 클래스

## 잘못된 상속 ##
![010](https://user-images.githubusercontent.com/24876345/211248578-894749e4-6dc0-4986-a490-00a78a189c61.jpg)

### 👎 BAD ###
````java
// 직사각형
public class Rectangle {
    protected int width;
    protected int height;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getArea() {
        return width * height;
    }
}
````
````java
// 정사각형 가로세로 길이가 같다.
public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(getWidth());
    }
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(getHeight());
    }
}

````
````java
public class Application {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(); // 직사각형 넓이;
        rectangle.setWidth(10);
        rectangle.setHeight(5);
        System.out.println(rectangle.getArea()); // 50 예상과같음
	
	Rectangle square = new Square(); // 정사각형 넓이
        square.setWidth(10);
        square.setHeight(5);
        System.out.println(square.getArea()); // 25 예상과다름
    }
}
````

## 상속관계를 잘 표현한 예제 ##

![011](https://user-images.githubusercontent.com/24876345/211248584-992fe733-f0f4-41a9-a830-3daf06d0f380.jpg)

### 👍 GOOD ###
````java
public class Shape {
    protected int width;
    protected int height;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getArea() {
        return width * height;
    }
}
````
````java
public class Rectangle extends Shape {
    public Rectangle(int width, int height) {
        super.setWidth(width);
        super.setHeight(height);
    }
}
public class Square extends Shape {
    public Square(int length) {
    	super.setWidth(length);
        super.setHeight(length);
    }
}
````
````java
public class Application {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 5);
        Shape square = new Square(5);
        System.out.println(rectangle.getArea()); // 50
        System.out.println(square.getArea()); // 25
    }
}
````
