package com.ucap.ms.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BaseSerializationUtil {
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		byte[] bytes=null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			bytes=baos.toByteArray();
			
			baos.close();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		Object object=null;
		ObjectInputStream ois=null;
		try {
			if(bytes==null)return null;
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			object= ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(bais!=null){
					bais.close();
				}if(ois!=null){
					ois.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return object;
	}
}