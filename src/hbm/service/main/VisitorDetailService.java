package hbm.service.main;

import hbm.controller.main.VisitorDetailController;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorDAO;
import hbm.visitor.VisitorDetail;
import hbm.visitor.VisitorDetailDAO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class VisitorDetailService {
	
	private VisitorDetailController controller;
	
	// Constructor
	public VisitorDetailService(VisitorDetailController visitorDetailController) {
		this.controller = visitorDetailController;
	}

	/***** Services *****/
	// 
	public void execGetVisitorDetail(Visitor v) { new GetVisitorDetail(v).start(); }
	private class GetVisitorDetail extends Service<VisitorDetail> {
		private Visitor visitor;
		private VisitorDetail visitorDetail;
		public GetVisitorDetail(Visitor visitor) {
			this.visitor = visitor;
		}
		@Override
		protected Task<VisitorDetail> createTask() {
			Task<VisitorDetail> task = new Task<VisitorDetail>() {
				@Override
				protected VisitorDetail call() throws Exception {
					if(Properties.getInstance().isDebugMode())
						Debug.show("========== VisitorDetailService.GetVisitorDetail.call() ==========");
//					VisitorDAO visitorDAO = new VisitorDAO();
					VisitorDetailDAO visitorDetailDAO = new VisitorDetailDAO();
					visitorDetail = visitorDetailDAO.getVisitorDetail(visitor);
					return visitorDetail;
				}
			};
			return task;
		}
		@Override
		protected void succeeded() {
			super.succeeded();
			
			System.out.println("visitorDetail: " + this.visitorDetail);
			
			controller.showVisitorDetail(this.visitorDetail);
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
	

}
