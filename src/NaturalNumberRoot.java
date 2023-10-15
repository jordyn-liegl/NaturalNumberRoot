import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Jordyn Liegl
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        //create NaturalNumbers for the floor and ceiling
        NaturalNumber floor = new NaturalNumber2(0);
        NaturalNumber ceiling = new NaturalNumber2(n);

        //increment ceiling since n is a possible guess
        ceiling.increment();

        //create a NaturalNumber for the difference to ensure there's one Natural Number
        //in the interval
        NaturalNumber diff = new NaturalNumber2();
        diff.copyFrom(ceiling);
        diff.subtract(floor);

        //create NaturalNumbers for the guess of the root
        //(the average of the ceiling and floor)
        NaturalNumber guess = new NaturalNumber2();
        NaturalNumber nn2 = new NaturalNumber2(2);

        //create a NaturalNumber to take the power of the guess
        NaturalNumber guessPow = new NaturalNumber2();

        //create a NaturalNumber of one to compare the difference to
        NaturalNumber nn1 = new NaturalNumber2(1);

        //loop until the difference is 1
        while (diff.compareTo(nn1) >= 1) {
            //take the average of the ceiling and the floor for the guess;
            guess.copyFrom(ceiling);
            guess.add(floor);
            guess.divide(nn2);

            //raise the guess to the power of r
            guessPow.copyFrom(guess);
            guessPow.power(r);

            //see if the guessPow is less than n
            if (guessPow.compareTo(n) <= 0) {
                floor.copyFrom(guess);
            } else {
                ceiling.copyFrom(guess);
            }

            //calculate the new difference
            diff.copyFrom(ceiling);
            diff.subtract(floor);

        }

        //update n to the root
        n.copyFrom(floor);

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}