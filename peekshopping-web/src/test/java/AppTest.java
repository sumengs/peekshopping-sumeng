import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2020/6/6 14:54
 * @author: sumeng
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    @Test
    public void test() {
//        int[] nums = {3, 2, 5};
//        List<List<Integer>> lists = permute(nums);
//        System.out.println(lists);

        boolean string = isFlipedString("adqwet", "ewqdat");
        System.out.println(string);


    }


    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();

//        List<Integer> tempList = new ArrayList<>();
//        tempList.add(nums[1]);
//        tempList.add(nums[0]);
//        tempList.add(nums[2]);
//
//        lists.add(tempList);
//
//        List<Integer> tempList1 = new ArrayList<>();
//        tempList1.add(nums[2]);
//        tempList1.add(nums[1]);
//        tempList1.add(nums[0]);
//        lists.add(tempList1);

        for (int i = 0; i < nums.length; i++) {

        }
        return lists;
    }

    private boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        for (int i = 0, j = s2.length()-1; i < s1.length(); i++, j--) {
            if (s1.charAt(i) != s2.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
