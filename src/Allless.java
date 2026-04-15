import java.util.ArrayList;
import java.util.List;


public class Allless {

    public static List<String> allLess(String[] s, int x) {
        List<String> result = new ArrayList<>();

        // If heap is empty or null, return empty list
        if (s == null || s.length == 0) {
            return result;
        }


        for (int i = 0; i < s.length; i++) {

            if (s[i] != null) {

                if (s[i].length() < x) {
                    result.add(s[i]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] s = {"zero", "size", "nutella", "jojo", "luna", "isse", "astor", "as", "entretien", "", "cal"};
        int x = 3;

        List<String> answer = allLess(s, x);
        System.out.println(  answer  );
    }
}