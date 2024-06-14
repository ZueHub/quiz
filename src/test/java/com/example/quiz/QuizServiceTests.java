package com.example.quiz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.quiz.constants.OptionType;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.CreateOrUpdateReq;
import com.example.quiz.vo.Question;

@SpringBootTest
public class QuizServiceTests {

	@Autowired
	QuizService quizService;

	@Autowired
	QuizDao quizDao;

	@Test
	public void createTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松版豬;炸豬排;煎魚;烤雞腿,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		questionList.add(new Question(2, "丹丹", "1號餐;2號餐;3號餐;4號餐,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		questionList.add(new Question(3, "炒飯", "豬肉炒飯;干貝炒飯;干貝馬林薯(推);綜合炒飯,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		CreateOrUpdateReq req = new CreateOrUpdateReq("午餐吃啥?", "午餐吃啥?", LocalDate.of(2024, 6, 1), //
				LocalDate.of(2024, 6, 1), questionList, true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getStatsCode() == 200, "create test false!!");
		// 刪除測試資料 TODO

	}

	@Test
	public void createNameErrorTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松版豬;炸豬排;煎魚;烤雞腿,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		questionList.add(new Question(2, "丹丹", "1號餐;2號餐;3號餐;4號餐,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		questionList.add(new Question(3, "炒飯", "豬肉炒飯;干貝炒飯;干貝馬林薯(推);綜合炒飯,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		CreateOrUpdateReq req = new CreateOrUpdateReq("", "午餐吃啥?", LocalDate.of(2024, 6, 1), //
				LocalDate.of(2024, 6, 1), questionList, true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param nmae error!!"), "create test false!!");

	}

	@Test
	public void createStartDateErrorTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松版豬;炸豬排;煎魚;烤雞腿,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		questionList.add(new Question(2, "丹丹", "1號餐;2號餐;3號餐;4號餐,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		questionList.add(new Question(3, "炒飯", "豬肉炒飯;干貝炒飯;干貝馬林薯(推);綜合炒飯,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		// 今天是 2024/5/30, 所以開始日期不能是當天或是之前
		CreateOrUpdateReq req = new CreateOrUpdateReq("午餐吃啥?", "午餐吃啥?", LocalDate.of(2024, 5, 30), //
				LocalDate.of(2024, 6, 1), questionList, true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param start_date error!!"), //
				"create test false!!");

	}

	@Test
	public void updateTest() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "健康餐?", "松版豬;炸豬排;煎魚;烤雞腿,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		questionList.add(new Question(2, "丹丹", "1號餐;2號餐;3號餐;4號餐,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		// 測試 name error
		questionList.add(new Question(3, "炒飯", "豬肉炒飯;干貝炒飯;干貝馬林薯(推);綜合炒飯,", //
				OptionType.SINGLE_CHOICE.getType(), true));
		CreateOrUpdateReq req = new CreateOrUpdateReq("", "午餐吃啥?", LocalDate.of(2024, 6, 1), //
				LocalDate.of(2024, 6, 1), questionList, true);
		BasicRes res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param nmae error!!"), "create test false!!");

		// 測試 start date error : 假設今天是 2024/5/30, 所以開始日期不能是當天或是之前
		req = new CreateOrUpdateReq("午餐吃啥?", "午餐吃啥?", LocalDate.of(2024, 5, 30), //
				LocalDate.of(2024, 6, 1), questionList, true);
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param start date error!!"), //
				"create test false!!");
		// 測試 end date error : 結束日期不能比開始日期早
		req = new CreateOrUpdateReq("午餐吃啥?", "午餐吃啥?", LocalDate.of(2024, 6, 30), //
				LocalDate.of(2024, 6, 1), questionList, true);
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getMessage().equalsIgnoreCase("Param end date error!!"), //
				"Param end date error!!");
		// 剩餘的邏輯全部判斷完之後,最後才會是測試成功的情境
		req = new CreateOrUpdateReq("午餐吃啥?", "午餐吃啥?", LocalDate.of(2024, 6, 1), //
				LocalDate.of(2024, 6, 1), questionList, true);
		res = quizService.createOrUpdate(req);
		Assert.isTrue(res.getStatsCode() == 200, "create test false!!");
	}
	
	@Test
	public void updateTest1() {
		System.out.println("============================");
	}
}
