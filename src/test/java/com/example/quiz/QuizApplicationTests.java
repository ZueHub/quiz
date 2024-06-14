package com.example.quiz;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuizApplicationTests {

	@Test
	void test() {
		List<String> list = List.of("A","B","C","D","E");
		String str = "AABBBCDDAEEEAACCDD";
		//計算 A, B, C, D, E 個出現了幾次
		Map<String,Integer> map = new HashMap<>();
		for (String item : list) {
		 String newStr = str.replace(item, "");
		  int count = str.length() - newStr.length();
		  map.put(item,count);
		  
		}
		System.out.println(map);
}
}