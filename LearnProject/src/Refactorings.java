class ExtractVar{

    final static int a_const = 10000;
    final static int b_const = 500;


    public void calc(){

        (new InnerCalc()).process(a_const, b_const);

    }

    class InnerCalc{

        final static int some_const = 124136;

        int process(int a, int b){
            return  a * b + some_const;
        }
    }

}