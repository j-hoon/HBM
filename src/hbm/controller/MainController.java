package hbm.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hbm.Main;
import hbm.service.MainService;
import hbm.util.Binding;
import hbm.util.Debug;
import hbm.visitor.Visitor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
	
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
	@FXML private VBox vBoxVisitorDetail;
	
	// 
	private Visitor visitor;
	private MainService mainService;
//	private ObservableList<Visitor> visitorList;
	
	public MainController(Visitor visitor) {
		this.visitor = visitor;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mainService = new MainService(this);

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
					Visitor v = row.getItem();
					System.out.println(v);

					// 
					FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/hbm/view/MainVisitorDetail.fxml"));
					Parent root = null;
					fxmlLoader.setControllerFactory(c -> new VisitorDetailController(this, (Visitor) v));
					try {
						root = (Parent) fxmlLoader.load();
						this.vBoxVisitorDetail.getChildren().clear();
						this.vBoxVisitorDetail.setMaxWidth(600.0);
						this.vBoxVisitorDetail.getChildren().add(root);
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			});
			return row;
		});
		
		/*
		// set ImageView
		if(visitor.getImgFileName() != null) {
			Debug.show("visitor.getImgFileName(): " + visitor.getImgFileName());
			
//			Debug.show("visitor.getImgFileCont(): " + visitor.getImgFileCont());
//			Debug.show("visitor.getImgFileCont().length(): " + visitor.getImgFileCont().length());
//			ivVisitorImg.setImage(new Image(visitor.getImgFileCont()));
//			FileChannel fileChannel = ((FileInputStream) visitor.getImgFileCont()).getChannel();
//			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		}
		*/
	}
	
	
	// 
	public void showAllVisitor(ActionEvent e) {
		// 
		this.mainService.execGetAllVisitor();
	}
	
	// 
	public void setVisitorList(ObservableList<Visitor> visitorList) {
		this.tvVisitor.setItems(visitorList);	
	}
	
	
	
	// Getter and Setter
//	public ObservableList<Visitor> getVisitorList() {
//		return this.visitorList;
//	}
	public TableView<Visitor> getTvVisitor() {
		return this.tvVisitor;
	}
	public VBox getvBoxVisitorDetail() {
		return this.vBoxVisitorDetail;
	}
	

}
