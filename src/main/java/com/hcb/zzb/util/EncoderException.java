package com.hcb.zzb.util;

public class EncoderException extends Exception{

	private static final long serialVersionUID = 639276068312354225L;

	/**
     * Creates a new instance of this exception with an useful message.
     * 
     * @param pMessage a useful message relating to the encoder specific error.
     */
    public EncoderException(String pMessage) {
        super(pMessage);
    }
}
