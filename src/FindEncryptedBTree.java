public class FindEncryptedBTree {

    public static boolean findElement(int[] bt, int t) {
        // empty tree
        if (bt == null || bt.length == 0) {
            return false;
        }

        // if root itself is empty, no valid tree
        if (bt[0] == -1) {
            return false;
        }

        // root always has value 1 in the unencrypted tree
        return dfs(bt, 0, 1, t);
    }

    public static boolean dfs(int[] bt, int index, int currentValue, int target) {
        // out of bounds
        if (index >= bt.length) {
            return false;
        }


        if (bt[index] == -1) {
            return false;
        }


        if (currentValue == target) {
            return true;
        }

        // left child value = 3*x + 1
        boolean leftFound = dfs(bt, 2 * index + 1, 3 * currentValue + 1, target);

        // if found on left, stop early
        if (leftFound) {
            return true;
        }

        // right child value = 2*x + 5
        boolean rightFound = dfs(bt, 2 * index + 2, 2 * currentValue + 5, target);

        return rightFound;
    }

    public static void main(String[] args) {
        int[] bt = { -2, -2, -1, -2, -1 };
        int t = 1;

        System.out.println(findElement(bt, t));
    }
}
