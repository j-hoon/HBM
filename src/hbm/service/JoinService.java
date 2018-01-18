package hbm.service;

import hbm.Main;
import hbm.controller.JoinController;
import hbm.gui.AlertManager;
import hbm.gui.StageManager;
import hbm.gui.StageManager.VIEW;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorDAO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class JoinService {
	
	private JoinController controller;
	
	// Constructor
	public JoinService(JoinController controller) {
		this.controller = controller;
	}

	/***** Services *****/
	// CheckJoinId
	public void execCheckJoinId(String id) { new CheckJoinId(id).start(); }
	private class CheckJoinId extends Service<Boolean> {
		private String id;
		private boolean isValidId;
		private CheckJoinId(String id) { this.id = id; }
		@Override
		protected Task<Boolean> createTask() {
			Task<Boolean> task = new Task<Boolean>() {
				@Override
				protected Boolean call() throws Exception {
					if(Properties.getInstance().isDebugMode())
						Debug.show("========== JoinService.CheckJoinId.call() ==========");
					VisitorDAO visitorDAO = new VisitorDAO();
					boolean ret = visitorDAO.isVaildId(id);
					isValidId = ret;
					return ret;
				}
			};
			return task;
		}
		@Override
		protected void succeeded() {
			super.succeeded();
			// 사용 가능한 아이디
			if(isValidId) {
				// 아이디 확인 버튼을 다시 작성 버튼으로 변경
				controller.getBtnCheckJoinId().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						controller.getBtnCheckJoinId().setText("확인");		// 버튼 텍스트 변경
						controller.getTfJoinId().setDisable(false);			// 아이디 필드 잠금 해제
						controller.getBtnCheckJoinId().setDisable(false);	// 확인 버튼 잠금 해제
						controller.getTfJoinId().requestFocus();			// 아이디 필드로 포커스 변경
						controller.getTxtJoinIdDesc().setFill(Color.RED);
						controller.getTxtJoinIdDesc().setText("아이디를 다시 입력해 주세요.");			// 아이디 확인 텍스트 출력
						controller.getBtnCheckJoinId().setOnAction((e) -> controller.checkJoinId(e));	// 아이디 확인 버튼을 원래대로 변경
					}
				});
				controller.getBtnCheckJoinId().setText("다시 작성");	// 버튼 텍스트 변경
				controller.getBtnCheckJoinId().setDisable(false);		// 다시 작성 버튼 잠금 해제 
				controller.getTxtJoinIdDesc().setFill(Color.BLUE);
				controller.getTxtJoinIdDesc().setText("사용가능한 아이디 입니다.");	// 아이디 확인 텍스트 출력
			}
			// 중복된 아이디
			else {
				controller.getTfJoinId().setDisable(false);			// 아이디 필드 잠금 해제
				controller.getBtnCheckJoinId().setDisable(false);	// 확인 버튼 잠금 해제
				controller.getTfJoinId().requestFocus();			// 아이디 필드로 포커스 변경
				controller.getTxtJoinIdDesc().setFill(Color.RED);
				controller.getTxtJoinIdDesc().setText("이미 사용중인 아이디 입니다.");	// 아이디 확인 텍스트 출력
			}
		}
		@Override
		protected void cancelled() {
			super.cancelled();
			Debug.error("JoinService.CheckJoinId was cancelled.");
		}
		@Override
		protected void failed() {
			super.failed();
			Debug.error("JoinService.CheckJoinId was failed.");
		}
		
	}
	// Join
	public <T extends Visitor> void execJoin(T visitor) { new Join(visitor).start(); }
	private class Join extends Service<Integer> {
		private Visitor visitor;
		private int isJoined;
		private Join(Visitor visitor) { this.visitor = visitor; }
		@Override
		protected Task<Integer> createTask() {
			Task<Integer> task = new Task<Integer>() {
				@Override
				protected Integer call() throws Exception {
					if(Properties.getInstance().isDebugMode()) {
						Debug.show("========== JoinService.Join.call() ==========");
						System.out.println("visitor: " + visitor);
					}
					VisitorDAO visitorDAO = new VisitorDAO();
					int ret = visitorDAO.join(visitor);
					isJoined = ret;
					return ret;
				}
			};
			return task;
		}
		@Override
		protected void succeeded() {
			super.succeeded();
			// 중복된 아이디
			if(isJoined == 0) {
				Debug.error("회원 가입 중 중복된 아이디 발견됨.");
				AlertManager.show(AlertType.WARNING, "회원 가입", null,
						"회원 가입 중 중복된 아이디가 발견되었습니다. 아이디 중복 확인을 다시 해주세요.");
			}
			// 회원 가입 정상적으로 완료
			else if(isJoined == 1) {
				Debug.show("회원 가입 완료!");
				AlertManager.show(AlertType.INFORMATION, "회원 가입", null,
						"회원 가입이 완료되었습니다. 로그인 화면으로 이동합니다.");
				StageManager.changeStage(VIEW.LOGIN);	// 로그인 화면으로 돌아가기
			}
			// 오류
			else {
				Debug.error("회원 가입 중 오류 발생. (errCode "+isJoined+")");
				AlertManager.show(AlertType.ERROR, "회원 가입", null,
						"회원 가입 중 오류가 발생했습니다. (errCode " + isJoined + ")");
			}
			Main.getPrimaryStage().getScene().getRoot().setDisable(false);	// 루트 Scene 잠금 해제
		}
		@Override
		protected void cancelled() {
			super.cancelled();
			Debug.error("JoinService.Join was cancelled.");
		}
		@Override
		protected void failed() {
			super.failed();
			Debug.error("JoinService.Join was failed.");
		}
	}
	//----- Services -----/
	
}
