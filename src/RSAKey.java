import java.math.BigInteger;

public class RSAKey {
    public int bitLength; // The bit length b of n.
    public BigInteger n; // The modulus n
    public BigInteger exp; // The exponent (e for pub keys d for priv keys)

    public RSAKey(int bitLength, BigInteger n, BigInteger exp) {
        this.bitLength = bitLength;
        this.n = n;
        this.exp = exp;
    }
}