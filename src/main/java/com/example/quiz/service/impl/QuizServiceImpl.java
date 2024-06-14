package com.example.quiz.service.impl;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.example.quiz.constants.OptionType;
import com.example.quiz.constants.ResMessage;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.CreateOrUpdateReq;
import com.example.quiz.vo.DeleteReq;
import com.example.quiz.vo.Question;
import com.example.quiz.vo.SearchReq;
import com.example.quiz.vo.SearchRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizDao quizDao;

	@Override
	public BasicRes createOrUpdate(CreateOrUpdateReq req) {
		// �ˬd�Ѽ�
		BasicRes checkResult = checkParams(req);
		// checkResult == null ��,��ܰѼ��ˬd�����T
		if (checkResult != null) {
			return checkResult;
		}

//�]�� Quiz �� question ����Ʈ榡�O String , �ҥH�n�N req �� List<Question> �ন String
//�z�L ObjectMapper �i�H�⪫��(���O)�ন JSON �榡�r��	
		ObjectMapper mapper = new ObjectMapper();
		try {
			String questionStr = mapper.writeValueAsString(req.getQuestionList());

			// �Y req ���� id > 0 ,��ܧ�s�w�s�b���: �Y id = 0:�h��ܭn�s�W
			if (req.getId() > 0) {

				// �H�U��ؤ�k�ܤ@
				// �ϥΤ�k1,�z�L findById �Y�����,�N�|�^�Ǥ@�㵧���(�i���ƶq�|���j)
				// �ϥΤ�k2, �]���O�z�L existsById �ӧP�_��ƬO�_�s�b,�ҥH�^�Ǫ���ƥû����u���@��bit(0��1)
				// ��k1:�z�L findById :�Y�����,�^�Ǿ����
//				Optional<Quiz> op = quizDao.findById(req.getId());
//				//�P�_�O�_�����
//				if(op.isEmpty()) {//isEmpty() ��ܨS���
//					return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(), //
//							ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
//				}
//				Quiz quiz = op.get();
//				//�]�w�s��(�ȱq req ��)			
//				//�N req �����s�ȳ]�w���ª� quiz ��,���]�w id ,�]��id�@��
//				quiz.setName(req.getName());
//				quiz.setDescription(req.getDescription());
//				quiz.setStartDate(req.getStartDate());
//				quiz.setEndDate(req.getEndDate());
//				quiz.setQuestions(questionStr);
//				quiz.setPublished(req.isPublished());
				// ��k2. �z�L existsById :�^�Ǥ@�� bit ����
				// �o��O�n�P�_�q req �a�i�Ӫ� id �O�_�u�����s�b��DB��,
				// �]���Y id ���s�b,�S���ˬd ����{���X�b�I�s JPA ��save ��k��,�|�ܦ��s�W
				boolean boo = quizDao.existsById(req.getId());
				if (!boo) {// !boo ��ܸ�Ƥ��s�b
					return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(), //
							ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
				}

				// �ҥH�n�� id ��i�h
//				Quiz quiz = new Quiz(req.getId(), req.getName(), req.getDescription(), req.getStartDate(), //
//						req.getEndDate(), questionStr, req.isPublished());

			}
			// ==========================================
			// �W�z�����q if �{���X�i�H�Y��H�U�o�q
			// �W�z�@��q if �{���X�i�H�Y��H�U�o�q
//			   if(req.getId() > 0 && !quizDao.existsById(req.getId())) {   
//			    return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(), 
//			      ResMessage.UPDATE_ID_NOT_FOUND.getMessage());   
//			   }
			// =========================================

			// quizDao.save(quiz);
			// �]���ܼ� quiz ���ϥΤ@�� ,�]���i�H�ϥΰΦW���O�覡���g(���ݭn�ܼƱ�)
			// new Quiz() ���a�J req.getId()��PK , �b�G��save�ɷ|���h�ˬdPK�O�_���s�b��DB��
			// �Y�s�b --> ��s: ���s�b --> �s�W
			// req ���S�������� , �w�]�O0 �]�� id ����ƫ��A�O int
			quizDao.save(new Quiz(req.getId(), req.getName(), req.getDescription(), req.getStartDate(),
					req.getEndDate(), questionStr, req.isPublished()));

		} catch (JsonProcessingException e) {
			return new BasicRes(ResMessage.JSON_PROCESSING_EXCEPTION.getCode(), //
					ResMessage.JSON_PROCESSING_EXCEPTION.getMessage());

		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), //
				ResMessage.SUCCESS.getMessage());
	}

	private BasicRes checkParams(CreateOrUpdateReq req) {
//�ˬd�ݨ�Ѽ�
// (StringUtils.hasText(�r��):�|�ˬd�r��O�_�� null , �Ŧr�� , ���ťզr�� ,
// �Y�O�ŦX3�ب䤤�@���|�^false
// �e���[����ĸ���ܤϦV���N�� , �Y�O���ˬd���G�O false ���� , �N�|�i�J if ����@�϶�
// !StringUtils.hasText(req.getName) ���P�� StringUtils.hasText(req.getName) ==false
// ����ĸ� �S��ĸ�
		if (!StringUtils.hasText(req.getName())) {
			return new BasicRes(ResMessage.PARAM_QUIZ_NAME_ERROR.getCode(), //
					ResMessage.PARAM_QUIZ_NAME_ERROR.getMessage());
		}
		if (!StringUtils.hasText(req.getDescription())) {
			return new BasicRes(ResMessage.PARAM_DESCRIPTION_ERROR.getCode(), //
					ResMessage.PARAM_DESCRIPTION_ERROR.getMessage());
		}
//1.�}�l�ɶ�����p�󵥩��e�ɶ�		
// LocalDate.now(): ���o�t�η�e�ɶ�
// req.getStartDate().isAfter(LocalDate.now()): �Y req �����}�l�ɶ����e�ɶ����e�ɶ��A�|�o�� true
// !req.getStartDate().isAfter(LocalDate.now()): �e�����[��ĸ�,��ܷ|�o��ۤϵ��G,�N�O�}�l�ɶ�
//                                               �|����p���e�ɶ�
		if (req.getStartDate() == null || !req.getStartDate().isAfter(LocalDate.now()))//
		{
			return new BasicRes(ResMessage.PARAM_START_DATE_ERROR.getCode(), //
					ResMessage.PARAM_START_DATE_ERROR.getMessage());
		}
		// �{���X�������o���,��ܶ}�l�ɶ��@�w�j�󵥩��e�ɶ�
		// �ҥH�����ˬd�����ɶ�,�u�n�T�w�����ɶ��O�j�󵥩�}�l�ɶ��Y�i,�]���u�n�����ɶ��O�j�󵥩�}�l�ɶ�,
		// �N�@�w�|�O�j�󵥩��e�ɶ�
		// �}�l�ɶ� ==> ��e�ɶ� : �����ɶ� >= �}�l�ɶ� ==> �����ɶ� >= �}�l�ɶ� >= ��e�ɶ�
		// �ҥH���ݭn�P�_ !req.getEndDate().isAfter(req.getStartDate()
		// 1.�����ɶ�����p�󵥩��e�ɶ� 2. �����ɶ�����p��}�l�ɶ�

		if (req.getEndDate() == null || req.getEndDate().isBefore(req.getStartDate()))//
		{
			return new BasicRes(ResMessage.PARAM_END_DATE_ERROR.getCode(), //
					ResMessage.PARAM_END_DATE_ERROR.getMessage());
		}
		// �ˬd���D�Ѽ�
		if (CollectionUtils.isEmpty(req.getQuestionList())) {
			return new BasicRes(ResMessage.PARAM_QUESTION_LIST_NOT_FOUND.getCode(), //
					ResMessage.PARAM_QUESTION_LIST_NOT_FOUND.getMessage());
		}
		// �@�i�ݨ�i��|���h�Ӱ��D,�ҥH�n�v���ˬd�C�w�����Ѽ�
		for (Question item : req.getQuestionList()) {
			if (item.getId() <= 0) {
				return new BasicRes(ResMessage.PARAM_QUESTION_ID_ERROR.getCode(), //
						ResMessage.PARAM_QUESTION_ID_ERROR.getMessage());
			}
			if (!StringUtils.hasText(item.getTitle())) {
				return new BasicRes(ResMessage.PARAM_TITLE_ERROR.getCode(), //
						ResMessage.PARAM_TITLE_ERROR.getMessage());
			}

			if (!StringUtils.hasText(item.getType())) {
				return new BasicRes(ResMessage.PARAM_TYPE_ERROR.getCode(), //
						ResMessage.PARAM_TYPE_ERROR.getMessage());
			}
			// �� option_type �O���Φh���,options �N����O�Ŧr��
			// �� option_type �O��r��,options ���\�O�Ŧr��
			// �H�U�����ˬd : �� option_type �O���Φh���,�B options �O�Ŧr�� , ��^���~
			if (item.getType().equals(OptionType.SINGLE_CHOICE.getType())
					|| item.getType().equals(OptionType.MULTI_CHOICE.getType())) //
			{
				if (!StringUtils.hasText(item.getOptions())) {
					return new BasicRes(ResMessage.PARAM_OPTIONS_ERROR.getCode(), //
							ResMessage.PARAM_OPTIONS_ERROR.getMessage());
				}
			}
			// �H�U�O�W�z2�� if �X�ּg�k: (����1 || ����2) && ����3
			// �Ĥ@�� if ���� && �ĤG�� if ����
			// if((item.getType().equals(OptionType.SINGLE_CHOICE.getType())
//			     || item.getType().equals(OptionType.MULTI_CHOICE.getType())) 
//			     && !StringUtils.hasText(item.getOptions())) {
//			    return new BasicRes(ResMessage.PARAM_OPTIONS_ERROR.getCode(), 
//			      ResMessage.PARAM_OPTIONS_ERROR.getMessage());
//   }
		}
		return null;

	}

	@Override
	public SearchRes search(SearchReq req) {
		String name = req.getName();
		LocalDate start = req.getStartDate();
		LocalDate end = req.getEndDate();
		// ���] name �O null �άO���ťժ��r�� , �i�H�����S����J�����,�N��ܭn���o����
		// JPA��containing ��k,����ȬO�Ŧr���,�|�j�M����
		// �ҥH�n��name ���ȬOnull �άO���ťզr���,�ഫ���Ŧr��
		if (!StringUtils.hasText(name)) {
			name = "";
		}
		if (start == null) {
			start = LocalDate.of(1970, 1, 1);
		}
		if (end == null) {
			end = LocalDate.of(2999, 12, 31);
		}
//List<Quiz> res = quizDao.findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual //
//(name, start, end);
//return new SearchRes(ResMessage.SUCCESS.getCode(), //
//ResMessage.SUCCESS.getMessage(), res);//
		return new SearchRes(ResMessage.SUCCESS.getCode(), //
				ResMessage.SUCCESS.getMessage(), //
				quizDao.findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual //
				(name, start, end));
	}

	@Override
	public BasicRes delete(DeleteReq req) {
		// �ˬd�Ѽ�
		if (!CollectionUtils.isEmpty(req.getIdlist())) {
			// �R���ݨ�
			try {
				quizDao.deleteAllById(req.getIdlist());
			} catch (Exception e) {
				// �� deleteAllById ��k���Aid ���Ȥ��s�b�ɡAJPA �|����
				// �]���b�R�����e�AJPA �|���j�M�a�J�� id �ȡA�Y�S���G�N�|����
				// �ѩ��ڤW�S�R�������ơA�]���N���ݭn��o�� Exception �@�B�z
			}

		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), //
				ResMessage.SUCCESS.getMessage());
	}

}
