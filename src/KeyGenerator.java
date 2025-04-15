import java.math.BigInteger;
import java.security.SecureRandom;

public class KeyGenerator {

    public static RSAKey[] generateKeys(int bitLength) {
        SecureRandom random = new SecureRandom();
        while (true) {
            BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
            BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
            BigInteger n = p.multiply(q);
            if (n.bitLength() != bitLength)
                continue;

            BigInteger phi = n.subtract(p).subtract(q).add(BigInteger.ONE);
            BigInteger e;
            do {
                e = new BigInteger(phi.bitLength(), random);
            } while (!(e.compareTo(BigInteger.ONE) > 0 &&
                    e.compareTo(phi) < 0 &&
                    e.gcd(phi).equals(BigInteger.ONE)));

            BigInteger d;
            try {
                d = e.modInverse(phi);
            } catch (ArithmeticException ex) {
                continue;
            }
            return new RSAKey[] { new RSAKey(bitLength, n, e), new RSAKey(bitLength, n, d) };
        }
    }
}