package hbm.service;

import hbm.controller.MainController;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorDAO;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MainService {
	
	private MainController controller;
	
	// Constructor
	public MainService(MainController mainController) {
		this.controller = mainController;
	}

	/***** Services *****/
	// GetAllVisitor
	public void execGetAllVisitor() { new GetAllVisitor().start(); }
	private class GetAllVisitor extends Service<ObservableList<Visitor>> {
		private ObservableList<Visitor> visitorList;
		@Override
		protected Task<ObservableList<Visitor>> createTask() {
			Task<ObservableList<Visitor>> task = new Task<ObservableList<Visitor>>() {
				@Override
				protected ObservableList<Visitor> call() throws Exception {
					if(Properties.getInstance().isDebugMode())
						Debug.show("========== MainService.GetAllVisitor.call() ==========");
					VisitorDAO visitorDAO = new VisitorDAO();
					visitorList = visitorDAO.getAllVisitor();
					return visitorList;
				}
			};
			return task;
		}
		@Override
		protected void succeeded() {
			super.succeeded();
//			System.out.println("visitorList: " + this.visitorList);
//			controller.getTvVisitor().setItems(this.visitorList);
			controller.setVisitorList(this.visitorList);
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
