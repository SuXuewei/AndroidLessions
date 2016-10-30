package com.suxw.androidlessions.utils;

import java.util.Arrays;

import android.util.Log;

public class ComTLV {
	static final String TAG = "ComTLV";

	// 传入tag缓存地址，计算tag数据长度
	static private int calcTLen(byte[] tlv, int offset) {
		int nTLen = 1;
	    if(0x1F == (tlv[offset] & 0x1F)) {
	    	nTLen = 2;
	    }
		return nTLen;
	}

	// 传入len缓存地址，计算len数据长度
	static private int calcLLen(byte[] tlv, int offset) {
		int nLenLen = 0;
		if((tlv[offset] & 0x80) != 0)
	    {
	        nLenLen = ((tlv[offset] & 0x7F) + 1);
	    }
	    else
	    {
	        nLenLen = 1;
	    }

		return nLenLen;
	}

	// 组织Len数据
	private static byte[] formLLen(int len) {
		byte[] bufLen;
		if(len >= 0 && len < 128) {
			bufLen = new byte[1];
			bufLen[0] = Byte.valueOf(String.valueOf(len));
		} else if (len < 256) {
			bufLen = new byte[2];
			bufLen[0] = Byte.valueOf(String.valueOf(0x81));
			bufLen[1] = Byte.valueOf(String.valueOf(len));
		} else {
			bufLen = new byte[2];
			bufLen[0] = Byte.valueOf(String.valueOf(0x82));
			bufLen[1] = Byte.valueOf(String.valueOf(len/256));
			bufLen[2] = Byte.valueOf(String.valueOf(len%256));
		}

		return bufLen;
	}

	// Byte[] lenBuf - Len数据缓存
	// nLLen - Len缓存数据长度
	// 计算value数据长度
	private static int calcVLen(byte[] tlv, int offset, int nLLen) {
		int nVLen = 0;
	    switch(nLLen)
	    {
	        case 1:
	        	nVLen   = tlv[offset]  & 0xFF;
	            break;
	        case 2:
	        	nVLen   = tlv[offset + 1] & 0xFF;
	            break;
	        case 3:
	        	nVLen   = (tlv[offset + 1] & 0xFF) * 256 + (tlv[offset + 2] & 0xFF);
	            break;
	        default:
	            break;
	    }

	    return nVLen;
	}

	// 计算tag在TLV串中的偏移
	private static int getTagOft(byte[] tlv, byte[] tag) {
		int i = 0;
		int nTagLen = 0;
		int nValLen = 0;
		int nLenLen = 0;

		if ((null == tlv) || (tlv.length == 0)) {
			Log.i(TAG, "getTagOft tlv.length == 0");
			return -1;
		}
		Log.i(TAG, "getTagOft tlv.length = " + tlv.length + "tag=" + BytesUtil.hex2String(tag));

		i = 0;
		while (i < tlv.length) {
			nTagLen = calcTLen(tlv, i);
			if ((i + nTagLen) >= tlv.length) {
				Log.i(TAG, "getTagOft (i + nTagLen) >= tlv.length");
				Log.i(TAG, "i = " + i + "; nTagLen = " + nTagLen);
				return -1;
			}

			byte[] tmpTag = new byte[nTagLen];
			System.arraycopy(tlv, i, tmpTag, 0, nTagLen);
			Log.i(TAG, "getTagOft tmpTag = " + BytesUtil.hex2String(tmpTag));
			if (!Arrays.equals(tag, tmpTag)) {
				nLenLen = calcLLen(tlv, i + nTagLen);
				if ((i + nTagLen + nLenLen) >= tlv.length) {
					Log.i(TAG, "getTagOft (i + nTagLen + nLenLen) >= tlv.length");
					Log.i(TAG, "i = " + i + "; nTagLen = " + nTagLen + "; nLenLen = " + nLenLen);
					return -1;
				}

				nValLen = calcVLen(tlv, i + nTagLen, nLenLen);
				i += nTagLen + nLenLen + nValLen;
			} else {
				return i;
			}
		}

		Log.i(TAG, "getTagOft no tlv = " + BytesUtil.hex2String(tlv));
		Log.i(TAG, "getTagOft no tag = " + BytesUtil.hex2String(tag));
		return -1;
	}

	public static byte[] getData(byte[] tlv, byte[] tag) {
		int i = 0;
		int nTagLen = 0;
		int nValLen = 0;
		int nLenLen = 0;

		if ((tlv == null) || (tlv.length <= 0))
			return null;

		i = getTagOft(tlv, tag);
		if(i < 0) {
			return null;
		}

		nTagLen = calcTLen(tlv, i);
		nLenLen = calcLLen(tlv, i + nTagLen);
		if ((i + nTagLen + nLenLen) >= tlv.length) {
			Log.i(TAG, "(i + nTagLen + nLenLen) >= tlv.length");
			return null;
		}

		nValLen = calcVLen(tlv, i + nTagLen, nLenLen);
		if ((i + nTagLen + nLenLen + nValLen) > tlv.length) {
			Log.i(TAG, "(i + nTagLen + nLenLen + nValLen) > tlv.length");
			return null;
		}

		byte[] value = new byte[nValLen];
		System.arraycopy(tlv, i + nTagLen + nLenLen, value, 0, nValLen);

		return value;
	}

	public static byte[] makeTLVItem(byte[] tag, byte[] value) {
		byte[] bufLen = formLLen(value.length);
		byte[] tlvItem = new byte[tag.length + bufLen.length + value.length];

		int nCur = 0;
		System.arraycopy(tag, 0, tlvItem, nCur, tag.length);
		nCur += tag.length;
		System.arraycopy(bufLen, 0, tlvItem, nCur, bufLen.length);
		nCur += bufLen.length;
		System.arraycopy(value, 0, tlvItem, nCur, value.length);
		nCur += value.length;

		return tlvItem;
	}

	public static byte[] setData(byte[] tlv, byte[] tag, byte[] value) {
		byte[] tmpTag;
		int nTagLen = 0;
		int nValLen = 0;
		int nLenLen = 0;
		int nCur = 0;

		if (value.length < 1 || value.length > 999)
		{
			return tlv;
		}

		byte[] newTLVItem = makeTLVItem(tag, value);
		if((null == tlv) || (tlv.length == 0)) {
			return newTLVItem;
		}

		nCur = 0;
		while (nCur < tlv.length) {
			nTagLen = calcTLen(tlv, nCur);
			tmpTag = new byte[nTagLen];
			System.arraycopy(tlv, nCur, tmpTag, 0, nTagLen);
			nCur += nTagLen;
			if (nCur >= tlv.length)
				return tlv;

			nLenLen = calcLLen(tlv, nCur);
			if ((nCur + nLenLen) >= tlv.length)
				return tlv;

			nValLen = calcVLen(tlv, nCur, nLenLen);
			nCur += nLenLen;
			if ((nCur + nValLen) > tlv.length)
				return tlv;

			if (tag.equals(tmpTag))
				break;

			nCur += nValLen;
		}

		byte[] tmpBuf;
		if (nCur != tlv.length) {
			// 总体长度减去已存在的tag的tlv数据长度
			int newLen = tlv.length - (nTagLen + nLenLen + nValLen);
			// 加上新value产生的tlv数据长度
			newLen += newTLVItem.length;

			tmpBuf = new byte[newLen];
			// 拷贝已存在tlv数据之前的数据
			System.arraycopy(tlv, 0, tmpBuf, 0, nCur - nTagLen - nLenLen);
			// 拷贝已存在tlv数据之后的数据，这样就将已存在tlv数据从原有tlv串中剔除了
			System.arraycopy(tlv, nCur + nValLen, tmpBuf, nCur - nTagLen
					- nLenLen, tlv.length - nCur - nValLen);
		} else {
			tmpBuf = new byte[tlv.length + newTLVItem.length];
		    System.arraycopy(tlv, 0, tmpBuf, 0, tlv.length);
		}

		nCur = tmpBuf.length - newTLVItem.length;
		System.arraycopy(newTLVItem, 0, tmpBuf, nCur, newTLVItem.length);

		return tmpBuf;
	}

	public static void testAPI() {
		final String TAG_STR_BF01 = "BF01";
		final String TAG_STR_BF02 = "BF02";
		final String TAG_STR_BF03 = "BF03";

		byte[] tlv = new byte[0];
		tlv = ComTLV.setData(tlv, BytesUtil.string2Hex(TAG_STR_BF01), BytesUtil.string2Hex("01"));
		tlv = ComTLV.setData(tlv, BytesUtil.string2Hex(TAG_STR_BF02), BytesUtil.string2Hex("02"));
		tlv = ComTLV.setData(tlv, BytesUtil.string2Hex(TAG_STR_BF03), BytesUtil.string2Hex("0003"));
		Log.i(TAG, BytesUtil.hex2String(tlv));

		byte[] value;
		value = ComTLV.getData(tlv, BytesUtil.string2Hex(TAG_STR_BF01));
		Log.i(TAG, TAG_STR_BF01 + ":" + BytesUtil.hex2String(value));
		value = ComTLV.getData(tlv, BytesUtil.string2Hex(TAG_STR_BF02));
		Log.i(TAG, TAG_STR_BF02 + ":" + BytesUtil.hex2String(value));
		value = ComTLV.getData(tlv, BytesUtil.string2Hex(TAG_STR_BF03));
		Log.i(TAG, TAG_STR_BF03 + ":" + BytesUtil.hex2String(value));		
	}

	public static void main(String[] args) {
		ComTLV.testAPI();
	}
}
