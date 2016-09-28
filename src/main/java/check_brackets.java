import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int p = 0;
        boolean isBalanced = true;
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (opening_brackets_stack.isEmpty()) {
                    isBalanced = false;
                    p = position;
                    break;
                }
                Bracket bracket = opening_brackets_stack.pop();
                if(! bracket.Match(next)) {
                    isBalanced = false;
                    p = position;
                    break;
                }
            }
        }

        if(opening_brackets_stack.isEmpty() && isBalanced){
            System.out.println("Success");
        }
        else {
            if(!isBalanced) {
                System.out.println(p + 1);
            }
            else {
                Bracket bracket = opening_brackets_stack.get(opening_brackets_stack.size() - 1);
                System.out.println(bracket.position + 1);
            }
        }
        // Printing answer, write your code here
    }
}
