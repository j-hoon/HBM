package hbm.service;

import hbm.controller.JoinController;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.visitor.VisitorDAO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

// TODO: convert to Singleton?
public class JoinService {
	
	private JoinController controller;
	private CheckJoinIdService checkJoinId;
	
	// Constructor
	public JoinService(JoinController controller) {
		this.controller = controller;
	}


	/***** Services *****/
	// CheckJoinId
	public class CheckJoinIdService extends Service<Boolean> {
		private String id;
		private boolean isValidId;
		private CheckJoinIdService(String id) { this.id = id; }
		
		@Override
		protected Task<Boolean> createTask() {
			Task<Boolean> task = new Task<Boolean>() {
				@Override
				protected Boolean call() throws Exception {
					if(Properties.getInstance().isDebugMode())
						Debug.show("========== CheckJoinIdService.call() ==========");
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
			if(isValidId) {
				controller.getBtnCheckJoinId().setVisible(false);	// 학인 버튼 숨기기
				controller.getBtnReCheckJoinId().setVisible(true);	// 재확인 버튼 보이기
				controller.getTxtJoinStatus().setText("사용가능한 아이디 입니다.");
			} else {
				controller.getTfJoinId().setDisable(false);			// 아이디 필드 잠금 해제
				controller.getBtnCheckJoinId().setDisable(false);	// 확인 버튼 잠금 해제
				controller.getTfJoinId().requestFocus();			// 아이디 필드로 포커스 변경
				controller.getTxtJoinStatus().setText("이미 사용중인 아이디 입니다.");
			}
		}

		@Override
		protected void cancelled() {
			// TODO Auto-generated method stub
			super.cancelled();
		}

		@Override
		protected void failed() {
			// TODO Auto-generated method stub
			super.failed();
		}
	}
	// 
	//----- Services -----/
	
	
	// Getter
	public CheckJoinIdService getCheckJoinId() {
		return this.checkJoinId;
	}
	
	// Setter
	public void setCheckJoinId(String id) {
		this.checkJoinId = new CheckJoinIdService(id);
	}
	
}
