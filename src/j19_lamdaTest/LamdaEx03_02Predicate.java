package j19_lamdaTest;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

// ** Predicate Test
// => 제네릭 타입이므로 필요시 원하는 타입으로 정의히여 사용함. 
class LamdaEx03_02Predicate {
    public static int sum1(Predicate<Integer> p, List<Integer> lst) {
        int s = 0;
        // lst 의 자료를 n 으로 받아 test(n) 이 return true 하면 s 에 추가함.
        for(int n : lst) {
            if(p.test(n)) s += n;
        } // for      
        return s;
    } // sum1
   
 // ** IntPredicate Test  
 // => int Type 전용
    public static int sum2(IntPredicate ip, List<Integer> lst) {
        int s = 0;
        for(int n : lst) {
            if(ip.test(n)) s += n;
        } // for       
        return s;
    } // sum2
    
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 7, 9, 11, 12);
        int s;
        
        // ** Predicate Test
        System.out.println("** Predicate Test **");
        s = sum1(n -> n%2 == 0, list);
        System.out.println("짝수 합: " + s);
        
        s = sum1(n -> n%2 != 0, list);
        System.out.println("홀수 합: " + s);
        
        // ** IntPredicate Test
        System.out.println("** IntPredicate Test **");
        s = sum2(n -> n%2 == 0, list);
        System.out.println("짝수 합: " + s);
        
        s = sum2(n -> n%2 != 0, list);
        System.out.println("홀수 합: " + s);
        
        // ** BiPredicate
        // => 두개의 인자를 받아 true, false 를 결정할 수 있음
        //    boolean test(T t, U u);
        System.out.println("** BiPredicate Test **");
        BiPredicate<String, Integer> conv = (str, i) -> { 
            if(str.length() > i)
                return true;
            else
                return false; 
        };

        if(conv.test("Robot", 3))
            System.out.println("문자열 길이 3 초과");
        else
            System.out.println("문자열 길아 3 이하");

        if(conv.test("Box", 5))
            System.out.println("문자열 길이 5 초과");
        else
            System.out.println("문자열 길아 5 이하");

    } // main
} // class