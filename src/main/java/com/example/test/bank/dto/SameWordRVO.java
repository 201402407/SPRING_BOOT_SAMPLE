package com.example.test.bank.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SameWordRVO implements Serializable {
	String resultMsg;
	boolean isSame;
	String resultWord;
}
