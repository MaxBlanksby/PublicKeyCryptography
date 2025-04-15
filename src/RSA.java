import java.util.BitSet;
import java.math.BigInteger;


public class RSA {

    public static RSAKey parseKey(String keyStr) {
        String[] parts = keyStr.split(";");
        int bitLength = Integer.parseInt(parts[0]);
        BigInteger n = new BigInteger(parts[1]);
        BigInteger exp = new BigInteger(parts[2]);
        return new RSAKey(bitLength, n, exp);
    }


    public static String encrypt(String message, RSAKey key) {
        byte[] messageBytes = message.getBytes();
        BitSet bitSet = BitSet.valueOf(messageBytes);
        long[] longArray = bitSet.toLongArray();

        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < longArray.length; i++) {
            BigInteger m = BigInteger.valueOf(longArray[i]);
            BigInteger c = m.modPow(key.exp, key.n);
            encryptedMessage.append(c.toString());
            if (i < longArray.length - 1) {
                encryptedMessage.append(",");
            }
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMessage, RSAKey key) {
        String[] parts = encryptedMessage.split(",");
        long[] decryptedLongs = new long[parts.length];

        for (int i = 0; i < parts.length; i++) {
            BigInteger c = new BigInteger(parts[i]);
            BigInteger m = c.modPow(key.exp, key.n);
            decryptedLongs[i] = m.longValueExact();
        }
        BitSet bitSet = BitSet.valueOf(decryptedLongs);
        byte[] messageBytes = bitSet.toByteArray();
        return new String(messageBytes);
    }
}