package 栈;

public class Test {
    public static void main(String[] arge) {
        String expression = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+-+r+u--+l+e+s---";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '+':
                    Character c = expression.charAt(++i);
                    if (c != '-' && c != '+') {
                        stack.push(expression.charAt(i));
                        System.out.print("元素【 "+expression.charAt(i)+"】入站 ：");
                        System.out.println(stack.toString());
                    }
                    break;
                case '-':
                    System.out.print("元素【 "+stack.pop()+"】出站 ：");
                    System.out.println(stack.toString());
                    break;
                default:
                    break;
            }

        }
    }
}