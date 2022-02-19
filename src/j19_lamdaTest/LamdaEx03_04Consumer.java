package j19_lamdaTest;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

// ** Consumer <T>
// => 인자를 전달 받지만 반환하지 않고 단지 활용(소비)하는 경우 유용
//    void accept(T t); 

class LamdaEx03_04Consumer {
    public static void main(String[] args) {
    	// 1. Consumer Test
        Consumer<String> c = s -> System.out.println(s);
        
        c.accept("Pineapple");    // 출력이라는 결과를 보임
        c.accept("Strawberry");
        
        // 2. ObjIntConsumer Test
        ObjIntConsumer<String> oc = (s, i) -> System.out.println(i + ". " + s);

        int n = 1;
        oc.accept("Toy", n++);
        oc.accept("Book", n++);
        oc.accept("Candy", n);
        
    } //main
} //class