package hbm.util;

import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Validation {

	public abstract static class Check<T> {
		abstract public boolean test(T value);
		abstract public String getValidDesc();
		abstract public String getInvalidDesc();
	}
	
	// 숫자, 영소문자만 허용, 길이 최소 4 ~ 최대 40
	public static class CheckId extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "유효한 아이디 입니다. 확인 버튼을 눌러주세요.";
		public final static String INVALID_DESC = "아이디는 최소 4글자로, 숫자, 영소문자만 허용됩니다.\n(최대 40글자)";
		@Override
		public boolean test(String id) {
			return Pattern.compile("^[a-z0-9]{4,40}$").matcher(id).find();
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}
	
	// 숫자, 영소문자, 특수문자(', ", \ 제외) 모두 1개 이상 반드시 포함, 길이 최소 8 ~ 최대 60
	public static class CheckPw extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "유효한 비밀번호 입니다.";
		public final static String INVALID_DESC = "비밀번호는 최소 8글자로, 숫자, 영소문자, 특수문자가 모두 1개 이상 반드시 포함되어야 합니다.\n(공백, 한글, 특수문자 \',\",\\ 불가능,  최대 60글자)";
		@Override
		public boolean test(String pw) {
			return Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[`~!@#$%^&*\\(\\)\\-_=+\\[\\]\\{\\};:,.<>?/\\|])(?!.*[ㄱ-ㅎㅏ-ㅣ가-힣])(?=\\S+$).{8,60}$").matcher(pw).find();
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}

	// 영소문자, 영대문자, 한글만 허용, 길이 최소 1 ~ 최대 20
	public static class CheckName extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "유효한 이름 입니다.";
		public final static String INVALID_DESC = "성과 이름은 각각 최소 1 글자로, 영어 대·소문자, 한글만 허용됩니다.\n(각 최대 20글자)";
		@Override
		public boolean test(String name) {
			return Pattern.compile("^[a-zA-Z가-힣]{1,20}$").matcher(name).find();
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}

	// LocalDate null 확인
	public static class CheckLocalDateNotNull extends Check<LocalDate> implements Predicate<LocalDate> {
		public final static String VALID_DESC = "유효한 날짜 입니다.";
		public final static String INVALID_DESC = "날짜를 선택하세요.";
		@Override
		public boolean test(LocalDate localDate) {
			if(localDate != null)
				return true;
			else
				return false;
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}

	// 이메일 형식 (example@domain.com), 길이 최소 4 ~ 최대 60
	public static class CheckEmail extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "유효한 이메일 입니다.";
		public final static String INVALID_DESC = "이메일은 example@domain.com 형식으로 작성해 주세요.\n(최대 60글자)";
		@Override
		public boolean test(String email) {
			return (email.length() <= 60) && Pattern.compile("^[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)").matcher(email).find();
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}
	
	// '-' 기호 제외, 019-999(9)-9999, 길이 최소 10 ~ 최대 11 (-> 8~9)
	public static class CheckPhone extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "유효한 휴대폰 번호 입니다.";
		public final static String INVALID_DESC = "'-' 기호를 제외한 휴대폰 번호를 입력해 주세요.\n(010-1234-5678 → 01012345678)";
		@Override
		public boolean test(String phone) {
			return Pattern.compile("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$").matcher(phone).find();
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}
	
	
	// TODO 사용자 추가 정보
	// 집 전화번호
	public static class CheckHPhone extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "";
		public final static String INVALID_DESC = "'-' 기호를 제외한 전화번호를 입력해 주세요.\n(02-123-4567 → 021234567)";
		@Override
		public boolean test(String hPhone) {
			return (hPhone.length() == 0) || Pattern.compile("^0(?:\\d{1,2})(?:\\d{3}|\\d{4})\\d{4}$").matcher(hPhone).find();
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}
	// 주소
	public static class CheckAddr extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "";
		public final static String INVALID_DESC = "주소는 100글자 이하로 작성해 주세요.";
		@Override
		public boolean test(String addr) {
			return (addr.length() >= 0 && addr.length() <= 100);
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}
	// 소속
	public static class CheckComp extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "";
		public final static String INVALID_DESC = "소속은 60글자 이하로 작성해 주세요.";
		@Override
		public boolean test(String comp) {
			return (comp.length() >= 0 && comp.length() <= 60);
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}
	// 직책	
	public static class CheckPos extends Check<String> implements Predicate<String> {
		public final static String VALID_DESC = "";
		public final static String INVALID_DESC = "직책은 40글자 이하로 작성해 주세요.";
		@Override
		public boolean test(String pos) {
			return (pos.length() >= 0 && pos.length() <= 40);
		}
		@Override
		public String getValidDesc() {
			return VALID_DESC;
		}
		@Override
		public String getInvalidDesc() {
			return INVALID_DESC;
		}
	}
}
