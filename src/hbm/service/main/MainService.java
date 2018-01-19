package hbm.service.main;

import java.util.concurrent.Executor;

import hbm.controller.main.MainController;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.visitor.Visitor;
import hbm.visitor.VisitorDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

public class MainService {
	
	// 
	private MainController controller;
	private Executor executor;
	
	// Constructor
	public MainService(MainController mainController, Executor executor) {
		this.controller = mainController;
		this.executor = executor;
	}

	/***** Services *****/
	// GetAllVisitor
	public void execGetAllVisitor() { this.executor.execute(getAllVisitorTask()); }
	private Task<ObservableList<Visitor>> getAllVisitorTask() {
		return new Task<ObservableList<Visitor>>() {
			private ObservableList<Visitor> visitorList;
			@Override
			protected ObservableList<Visitor> call() throws Exception {
				if(Properties.getInstance().isDebugMode())
					Debug.show("========== MainService.getAllVisitorTask.call() ==========");
				VisitorDAO visitorDAO = new VisitorDAO();
				this.visitorList = visitorDAO.getAllVisitor();
				return visitorList;
			}
			@Override
			protected void succeeded() {
				super.succeeded();
				if(Properties.getInstance().isDebugMode())
					Debug.show("========== MainService.getAllVisitorTask.succeeded() ==========");
				controller.setVisitorList(this.visitorList);
				controller.showPiVisitor(false);
			}
			@Override
			protected void cancelled() {
				super.cancelled();
				Debug.error("========== MainService.getAllVisitorTask.cancelled() ==========");
				controller.showPiVisitor(false);
			}
			@Override
			protected void failed() {
				super.failed();
				Debug.error("========== MainService.getAllVisitorTask.failed() ==========");
				controller.showPiVisitor(false);
			}
		};
	}
	
}
