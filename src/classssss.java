import java.util.ArrayList;

public class classssss {

    public static int indexSmallest(ArrayList<Integer> list) {
        int small = list.get(0);
        for(Integer num : list) if(num < small) small = num;
        return list.indexOf(small);
    }
}
