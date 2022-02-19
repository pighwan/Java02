package j19_lamdaTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

class LamdaEx03_03Supplier {
	// ** Supplier Test 
    public static List<Integer> makeList(Supplier<Integer> s, int n) {
    	List<Integer> list = new ArrayList<>(); 
       
    	// s 의 get() 이 return 하는 값을 list 에 add -> n번 반복
        for(int i = 0; i < n; i++)
            list.add(s.get());
        
        return list;
    } //makeList
    
    // ** IntSupplier Test
    public static List<Integer> makeIntList(IntSupplier is, int n) {
        List<Integer> list = new ArrayList<>();  
        
        for(int i = 0; i < n; i++)
            list.add(is.getAsInt());
        
        return list;
    } //makeIntList
    
    public static void main(String[] args) {
    	
    	// 1. Supplier Test 
    	// ** get()
    	// =>0 ~ 50 의 random 값을 return 하도록 구현 
        Supplier<Integer> spr = () -> {
            Random rand = new Random();
            return rand.nextInt(50);
        };
        
        // ** get()을 5 회 반복하여 list 생성
        System.out.println("** Supplier 5회 반복 list **");
        List<Integer> list = makeList(spr, 5);
        System.out.println(list);

        System.out.println("** Supplier 10회 반복 list **");
        list = makeList(spr, 10);
        System.out.println(list);
        
        // 2. IntSupplier Test
        IntSupplier  ispr = () -> {
            Random rand = new Random();
            return rand.nextInt(50);
        };
        System.out.println("\n** IntSupplier 5회 반복 list **");
        List<Integer> ilist = makeIntList(ispr, 5);
        System.out.println(ilist);
        System.out.println("** IntSupplier 10회 반복 list **");
        ilist = makeIntList(ispr, 10);
        System.out.println(ilist);
        
    } //main
} //class