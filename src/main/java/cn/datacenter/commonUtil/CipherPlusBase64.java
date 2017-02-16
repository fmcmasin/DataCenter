package cn.datacenter.commonUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 数据加密工具，使用“DES/CBC/NoPadding”作为算法
 * 
 * @author meisheng
 * 
 */
public class CipherPlusBase64 {

	public CipherPlusBase64() {
	}

	/**
	 * 加密
	 * 
	 * @param input
	 *            明文
	 * @param doURLEncoding
	 *            是否是对URL进行编码
	 * @return 密文
	 * @throws Exception
	 */
	public static String encrypt(String input, boolean doURLEncoding) throws Exception {
		if (input == null)
			return null;
		if (cipherEncrypt == null || cipherDecrypt == null)
			buildCiphers();
		byte temp[] = input.getBytes();
		temp = pad(temp);
		byte encValBytes[] = cipherEncrypt.doFinal(temp);
		String encValStr = "";
		for (int xx = 0; xx < encValBytes.length; xx++)
			encValStr = encValStr + Character.toString((char) encValBytes[xx]);

		if (doURLEncoding)
			encValStr = URLEncoder.encode(encValStr, "UTF-8");
		return encValStr;
	}

	/**
	 * 解密
	 * 
	 * @param input
	 *            密文
	 * @param doURLDecoding
	 *            是否是对URL进行编码
	 * @return 明文
	 * @throws Exception
	 */
	public static String decrypt(String input, boolean doURLDecoding) throws Exception {
		if (input == null)
			return null;
		if (cipherEncrypt == null || cipherDecrypt == null)
			buildCiphers();
		if (doURLDecoding && input.indexOf("%") != -1)
			input = URLDecoder.decode(input, "UTF-8");
		char charArray[] = input.toCharArray();
		byte encValBytes[] = new byte[charArray.length];
		for (int xx = 0; xx < charArray.length; xx++)
			encValBytes[xx] = (byte) charArray[xx];

		encValBytes = pad(encValBytes);
		byte temp2[] = cipherDecrypt.doFinal(encValBytes);
		String decryptVal = new String(temp2);
		decryptVal = decryptVal.trim();
		return decryptVal;
	}

	private static void buildCiphers() throws Exception {
		String key = "j3*9vk0e8rjvc9fj(*KFikd#";
		String spec = "kE*(RKc%";
		DESKeySpec keyspec = new DESKeySpec(key.getBytes());
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
		javax.crypto.SecretKey secretKey = factory.generateSecret(keyspec);
		IvParameterSpec ivSpec = new IvParameterSpec(spec.getBytes());
		cipherEncrypt = Cipher.getInstance("DES/CBC/NoPadding");
		cipherEncrypt.init(1, secretKey, ivSpec);
		cipherDecrypt = Cipher.getInstance("DES/CBC/NoPadding");
		cipherDecrypt.init(2, secretKey, ivSpec);
	}

	private static byte[] pad(byte in[]) {
		int padLen = 8;
		if (padLen == 0)
			return in;
		int inlen = in.length;
		int outlen = inlen;
		int rem = inlen % padLen;
		if (rem > 0)
			outlen = inlen + (padLen - rem);
		byte out[] = new byte[outlen];
		for (int xx = 0; xx < inlen; xx++)
			out[xx] = in[xx];

		return out;
	}

	private static Cipher cipherEncrypt = null;
	private static Cipher cipherDecrypt = null;

}
