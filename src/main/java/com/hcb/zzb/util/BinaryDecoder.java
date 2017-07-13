package com.hcb.zzb.util;

public interface BinaryDecoder extends Decoder{

	 byte[] decode(byte[] pArray) throws DecoderException;
}
