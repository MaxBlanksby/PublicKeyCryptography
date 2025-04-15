import java.math.BigInteger;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nRSA Key Generation Test ");
        RSAKey[] generatedKeys = KeyGenerator.generateKeys(100);
        RSAKey generatedPublicKey = generatedKeys[0];
        RSAKey generatedPrivateKey = generatedKeys[1];
        System.out.println("Generated Public Key: " + generatedPublicKey.bitLength + ";" + generatedPublicKey.n + ";" + generatedPublicKey.exp);
        System.out.println("Generated Private Key: " + generatedPrivateKey.bitLength + ";" + generatedPrivateKey.n + ";" + generatedPrivateKey.exp);

        System.out.println("\nRSA Encryption/Decryption Test ");
        String sampleMessage = "test";
        String encryptedSample = RSA.encrypt(sampleMessage, generatedPublicKey);
        System.out.println("Sample Message: " + sampleMessage);
        System.out.println("Encrypted Message: " + encryptedSample);
        String decryptedSample = RSA.decrypt(encryptedSample, generatedPrivateKey);
        System.out.println("Decrypted Message: " + decryptedSample);

        System.out.println("\nFermat Decryption Test");
        String teacherKeyStr = "40;832454397559;704934039473";
        String teacherCipherStr = "655226806399";

        RSAKey teacherKey = RSA.parseKey(teacherKeyStr);
        BigInteger teacherN = teacherKey.n;
        BigInteger teacherE = teacherKey.exp;
        BigInteger teacherCiphertext = new BigInteger(teacherCipherStr);

        BigInteger[] factors = FermatDecrypt.fermatFactor(teacherN);
        if (factors == null || factors.length < 2) {
            System.out.println("Fermat factorization failed.");
        } else {
            BigInteger p = factors[0];
            BigInteger q = factors[1];
            System.out.println("Fermat factors: p = " + p + ", q = " + q);

            BigInteger phi = teacherN.subtract(p).subtract(q).add(BigInteger.ONE);
            BigInteger teacherD = teacherE.modInverse(phi);
            System.out.println("private exponent d = " + teacherD);

            BigInteger decryptedTeacherBI = teacherCiphertext.modPow(teacherD, teacherN);
            System.out.println("Decrypted BigInteger plaintext: " + decryptedTeacherBI);


            long decryptedLong = decryptedTeacherBI.longValue();
            long[] longArray = new long[] { decryptedLong };
            BitSet bs = BitSet.valueOf(longArray);
            String DecryptedMessage = new String(bs.toByteArray());
            System.out.println("Decrypted Message: " + DecryptedMessage);
        }
    }
}