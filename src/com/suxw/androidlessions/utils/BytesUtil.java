package com.suxw.androidlessions.utils;

public class BytesUtil {
	public static enum AlignType{
		LEFT,		//左对齐
		RIGHT,		//右对齐
	}

    /**
     * 根据byte低四位数值转换成对应的char字符
     * eg. 0000 -> '0', 1111->'F'
     * @param byteVal
     * @return 对应的字符
     */
    public static char byte2char(byte byteVal){
    	char cVal	= 0;
    	byteVal = (byte)(byteVal & 0x0F);

    	if((byteVal >= 0) && (byteVal <= 9)) {
			cVal	= (char)(byteVal + '0');
    	} else if((byteVal >= 10) && (byteVal <= 15)) {
			cVal	= (char)(byteVal - 10 + 'A');
    	} else {
			cVal	= 'X';
    	}
    	return cVal;
    }

    /**
     * 将字符转换成4位数据标示的二进制数据
     * @param cVal 字符数据（['0', '9'] 或 ['A', 'F'] 或 ['a', 'f']）
     * @return 二进制数据，低四位有效
     */
    public static byte char2byte(char cVal) {
    	byte byteVal = 0;
    	cVal = Character.toUpperCase(cVal);
    	if(cVal >= '0' && cVal <= '9') {
    		byteVal = (byte)(cVal - '0');
    	} else  if (cVal >= 'A' && cVal <= 'F'){
    		byteVal = (byte)(10 + cVal - 'A');
    	} else {
    		byteVal = 0;
    	}
    	return byteVal;
    }

    /**
     * 将二进制数据转换成字符类型
     * @param hex		原始数据
     * @param strCon 转换后的看见子串
     * eg. \x0123456789 -> "0123456789"
     */
    public static String hex2String(byte[] hex) {
    	char[] dataInfo	= new char[hex.length * 2];

    	for(int i=0; i < hex.length; i++) {
    		dataInfo[i * 2]		= byte2char((byte)((byte)(hex[i] >>> 4) & 0x0f));
    		dataInfo[i * 2 + 1]	= byte2char((byte)((byte)(hex[i] & 0x0f)));
    	}

    	return String.valueOf(dataInfo);
    }

    /**
     * 将string数据转换成hex数据，默认左对齐
     * eg. "12ce4f5d" -> {0x12, 0xce, 0x4f, 0x5d}
     * @param strCon 字符数据
     * @return
     */
    public static byte[] string2Hex(String strCon) {
    	return string2Hex(strCon, AlignType.LEFT);
    }

    /**
     * 将string数据转换成hex数据
     * eg. "12ce4f5d" -> {0x12, 0xce, 0x4f, 0x5d}
     * @param strCon 字符数据
     * @param atType 对齐类型（左对齐，右对齐）
     * @return
     */
    public static byte[] string2Hex(String strCon, AlignType atType) {
    	byte[] hex = new byte[(strCon.length() + 1) / 2];

    	//奇数个字符
    	if((strCon.length() & 1) == 1) {
        	if(AlignType.RIGHT == atType) {
        		strCon = "0" + strCon;
        	} else {
        		strCon = strCon + "0";
        	}	
    	}

    	//string 2 hex
    	for(int i=0; i<hex.length; i++) {
    		hex[i] = (byte)((char2byte(strCon.charAt(i * 2)) << 4)
    				        | char2byte(strCon.charAt(i * 2 + 1)));
    	}

    	return hex;
    }

    /**
     * 将bcd数据转换成对应的整数值
     * @param bcd 数字字符压缩的bcd码数据
     * @return 
     */
    public static int bcd2int(byte[] bcd) {
    	return Integer.parseInt(hex2String(bcd));
    }
}
