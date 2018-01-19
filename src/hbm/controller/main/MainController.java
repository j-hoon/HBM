package hbm.controller.main;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import hbm.gui.ViewManager;
import hbm.gui.ViewManager.VIEW;
import hbm.service.main.MainService;
import hbm.util.Binding;
import hbm.util.Debug;
import hbm.util.Properties;
import hbm.util.wrapper.Controller;
import hbm.visitor.Visitor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainController extends Controller implements Initializable {
	
	// FXML Member
	@FXML private TableView<Visitor> tvVisitor;
	@FXML private TableColumn<Visitor, Integer> tcNo;
	@FXML private TableColumn<Visitor, String> tcGrade;
	@FXML private TableColumn<Visitor, String> tcId;
	@FXML private TableColumn<Visitor, String> tcPw;
	@FXML private TableColumn<Visitor, String> tcLName;
	@FXML private TableColumn<Visitor, String> tcFName;
	@FXML private TableColumn<Visitor, LocalDate> tcBitrh;
	@FXML private TableColumn<Visitor, String> tcEmail;
	@FXML private TableColumn<Visitor, String> tcPhone;
	
	// 
	@FXML private StackPane spPiVisitor;
	@FXML private VBox vBoxVisitorDetail;
	
	// 
	private Visitor user;
	private MainService mainService;
	private Executor executor;
	
	public MainController(Visitor user) {
		this.user = user;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		// set Service Executor
		executor = Executors.newCachedThreadPool(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
		
		// set Service
		this.mainService = new MainService(this, this.executor);

		// set Table Column Value Factory
		this.tcNo.setCellValueFactory(cellData -> cellData.getValue().noProperty().asObject());
		this.tcGrade.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
		this.tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		this.tcPw.setCellValueFactory(cellData -> Binding.pwBinding(cellData.getValue().pwProperty().get())/*cellData.getValue().pwProperty()*/);
		this.tcLName.setCellValueFactory(cellData -> cellData.getValue().lNameProperty());
		this.tcFName.setCellValueFactory(cellData -> cellData.getValue().fNameProperty());
		this.tcBitrh.setCellValueFactory(cellData -> cellData.getValue().birthProperty());
		this.tcEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		this.tcPhone.setCellValueFactory(cellData -> Binding.phoneBinding(cellData.getValue().phoneProperty().get()));
		
		// set Mouse Double Click Listener on Table Row
		this.tvVisitor.setRowFactory(value -> {
			TableRow<Visitor> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 2 && row.getItem() != null) {
					// show VisitorDetail View
					ViewManager.showView(VIEW.VISITOR_DETAIL, this.vBoxVisitorDetail, this, (Visitor) row.getItem());
				}
			});
			return row;
		});
		
	}
	
	
	// 
	public void showAllVisitor(ActionEvent e) {
		// Progress Indicator Visible
		this.showPiVisitor(true);
		// Execute MainService.GetAllVisitor
		this.mainService.execGetAllVisitor();
	}
	
	// 
	public void setVisitorList(ObservableList<Visitor> visitorList) {
		this.tvVisitor.setItems(visitorList);	
	}

	// 
	public void showPiVisitor(boolean show) {
		if(show) this.spPiVisitor.setVisible(true);
		else this.spPiVisitor.setVisible(false);
	}
	
	
	// Getter and Setter
	public TableView<Visitor> getTvVisitor() {
		return this.tvVisitor;
	}
	public VBox getvBoxVisitorDetail() {
		return this.vBoxVisitorDetail;
	}

}
