
 
 
package com.computer.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 
public class MD5Encryption {

	/**
	 * 创建一个新的实例 MD5Encryption.
	 *
	 */

	public MD5Encryption() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getMD5(String source) {
		String s = null;
		char hexChar[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());// 使用指定的byte数组更新摘要
			byte[] hashCalc = md.digest();// 完成哈希计算
			char result[] = new char[16 * 2];// MD5结果返回的是32位字符串，每位是16进制表示的
			int k = 0;
			for (int i = 0; i < 16; i++) {// 循环16次，对每个字节进行操作转换
				byte everyByte = hashCalc[i];
				//System.out.println(everyByte);
				result[k++] = hexChar[everyByte >>> 4 & 0xf];// 对每个字节的高4位进行处理，逻辑右移，再相与
				result[k++] = hexChar[everyByte & 0xf];// 低4位转换
			}
			s = new String(result);
		} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
		}
		return s;
	}

	/**
	 * @function main
	 * 
	 * @param args
	 * @author Dayong.Shen
	 * @date 2014-2-27-下午12:32:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMD5("a"));
	}

}
