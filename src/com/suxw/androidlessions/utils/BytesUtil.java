package com.suxw.androidlessions.utils;

public class BytesUtil {
	public static enum AlignType{
		LEFT,		//�����
		RIGHT,		//�Ҷ���
	}

    /**
     * ����byte����λ��ֵת���ɶ�Ӧ��char�ַ�
     * eg. 0000 -> '0', 1111->'F'
     * @param byteVal
     * @return ��Ӧ���ַ�
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
     * ���ַ�ת����4λ���ݱ�ʾ�Ķ���������
     * @param cVal �ַ����ݣ�['0', '9'] �� ['A', 'F'] �� ['a', 'f']��
     * @return ���������ݣ�����λ��Ч
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
     * ������������ת�����ַ�����
     * @param hex		ԭʼ����
     * @param strCon ת����Ŀ����Ӵ�
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
     * ��string����ת����hex���ݣ�Ĭ�������
     * eg. "12ce4f5d" -> {0x12, 0xce, 0x4f, 0x5d}
     * @param strCon �ַ�����
     * @return
     */
    public static byte[] string2Hex(String strCon) {
    	return string2Hex(strCon, AlignType.LEFT);
    }

    /**
     * ��string����ת����hex����
     * eg. "12ce4f5d" -> {0x12, 0xce, 0x4f, 0x5d}
     * @param strCon �ַ�����
     * @param atType �������ͣ�����룬�Ҷ��룩
     * @return
     */
    public static byte[] string2Hex(String strCon, AlignType atType) {
    	byte[] hex = new byte[(strCon.length() + 1) / 2];

    	//�������ַ�
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
     * ��bcd����ת���ɶ�Ӧ������ֵ
     * @param bcd �����ַ�ѹ����bcd������
     * @return 
     */
    public static int bcd2int(byte[] bcd) {
    	return Integer.parseInt(hex2String(bcd));
    }
}
