import java.math.BigInteger;
import java.util.BitSet;

public class FermatDecrypt {
    public static void main(String[] args) {
        String keyStr = "40;832454397559;704934039473";
        String cipherStr = "655226806399";


        String[] parts = keyStr.split(";");
        int bitLength = Integer.parseInt(parts[0]);
        BigInteger n = new BigInteger(parts[1]);
        BigInteger e = new BigInteger(parts[2]);

        BigInteger ciphertext = new BigInteger(cipherStr);

        BigInteger[] factors = fermatFactor(n);
        if (factors == null || factors.length < 2) {
            System.out.println("Error: Unable to factor n using Fermat's algorithm.");
            return;
        }
        BigInteger p = factors[0];
        BigInteger q = factors[1];
        System.out.println("Fermat's factors:");
        System.out.println("p = " + p);
        System.out.println("q = " + q);

        BigInteger phi = n.subtract(p).subtract(q).add(BigInteger.ONE);
        System.out.println("phi(n) = " + phi);

        BigInteger d = e.modInverse(phi);
        System.out.println("Recovered private exponent d = " + d);

        BigInteger plaintext = ciphertext.modPow(d, n);
        System.out.println("Decrypted BigInteger plaintext: " + plaintext);

        long decryptedLong = plaintext.longValue();
        long[] longArray = new long[] { decryptedLong };
        BitSet bs = BitSet.valueOf(longArray);
        byte[] decryptedBytes = bs.toByteArray();
        String message = new String(decryptedBytes);
        System.out.println("Decrypted message: " + message);
    }

    public static BigInteger sqrt(BigInteger x) {
        if (x.compareTo(BigInteger.ZERO) < 0)
            throw new IllegalArgumentException("Negative argument.");
        if (x.equals(BigInteger.ZERO) || x.equals(BigInteger.ONE))
            return x;
        BigInteger two = BigInteger.valueOf(2);
        BigInteger guess = x.shiftRight(x.bitLength() / 2);
        while (true) {
            BigInteger next = guess.add(x.divide(guess)).divide(two);
            if (next.equals(guess) || next.equals(guess.subtract(BigInteger.ONE)))
                return next;
            guess = next;
        }
    }

    public static boolean isPerfectSquare(BigInteger x) {
        BigInteger s = sqrt(x);
        return s.multiply(s).equals(x);
    }

    public static BigInteger[] fermatFactor(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return new BigInteger[]{ BigInteger.TWO, n.divide(BigInteger.TWO) };
        }
        BigInteger a = sqrt(n);
        if (a.multiply(a).compareTo(n) < 0)
            a = a.add(BigInteger.ONE);
        BigInteger b2 = a.multiply(a).subtract(n);
        while (!isPerfectSquare(b2)) {
            a = a.add(BigInteger.ONE);
            b2 = a.multiply(a).subtract(n);
        }
        BigInteger b = sqrt(b2);
        BigInteger p = a.subtract(b);
        BigInteger q = a.add(b);
        return new BigInteger[] { p, q };
    }
}