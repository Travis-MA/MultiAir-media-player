package FrontSide;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import Model.CustomerSet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CustomerSetControl {

	@FXML TextField movieName;
	@FXML TextField movieUrl;
	@FXML TextField picUrl;
	@FXML TextField movieCountry;
	@FXML TextField year;
	@FXML TextField type;
	@FXML TextField deleteMovieName;
	@FXML TextField deleteMovieId;
	@FXML TextField setCompanyName;
	@FXML TextField setPicture1Url;
	@FXML TextField setPicture2Url;
	@FXML TextField setCompanyVideoUrl;
	@FXML Button movieUrlSearch;
	@FXML Button picUrlSearch;
	@FXML Button confirmAdd;
	@FXML Button confirmDelete;
	@FXML Button setPic1Search;
	@FXML Button setPic2Search;
	@FXML Button setVideoSearch;
	@FXML Button companyConfirm;
	@FXML ComboBox<String> typeChoose;
	@FXML ImageView picture1Preview;
	@FXML ImageView picture2Preview;
	@FXML Text companyName;
	
	
	private ObservableList<String> typeItems;
	private FileChooser fileChooser = new FileChooser();
	private CustomerSet backSide = new CustomerSet();
	private String[] types = {"全部", "喜剧", "悲剧", "爱情", "动作", "恐怖", "悬疑", "历史",
	    "儿童", "音乐", "冒险", "科幻", "中国", "欧美", "日韩", "其他"};
	
	@FXML
	public void initialize() {
		typeChoose.setItems(typeItems);
		
		//加载Properties配置文件
		Properties userDefault = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream("userDefault.properties");
			userDefault.load(new InputStreamReader(inputStream, "UTF-8"));
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		companyName.setText(userDefault.getProperty("companyName"));
		String companyPic1 = userDefault.getProperty("companyPicture1");
		String companyPic2 = userDefault.getProperty("companyPicture2");

		Image pic1 = new Image(CustomerSetControl.class.getResourceAsStream("frontSidePicture\\"+companyPic1));
		Image pic2 = new Image(CustomerSetControl.class.getResourceAsStream("frontSidePicture\\"+companyPic2));
		picture1Preview.setImage(pic1);
		picture2Preview.setImage(pic2);
		
	}
	
	

	private File applyFileChooser() {
		String fileUrl = null;
		Stage fileChooserStage = new Stage();
		fileChooser.setTitle("选择文件");
	    File file =  fileChooser.showOpenDialog(fileChooserStage); 
	    return file;
	}

	@FXML
	public void clickConfirmAdd() {
		System.out.println("ConfirmAdd");
        String filmUrlType = backSide.checkFile(movieUrl.getText());
        String picUrlType = backSide.checkFile(picUrl.getText());
        
        if("mp4".equals(filmUrlType)) {
        	if("jpg".equals(picUrlType.toLowerCase())) {
        		backSide.copyMovieAndPic(movieUrl.getText(),picUrl.getText(),movieName.getText());	
        		backSide.addNewMovies(movieName.getText(),type.getText(),year.getText());
        	}else{
        		System.out.println("Picture File Is Wrong "+picUrlType);
        	}
        }else {
        	System.out.println("Movie File Is Wrong "+filmUrlType);
        }   
	}
	
	@FXML
	public void clivkConfirmDelete() {
		
	}
	@FXML
	public void clickCompanyConfirm() {
		String name = setCompanyName.getText();
		String url1 =  setPicture1Url.getText();
		String url2 = setPicture2Url.getText();
		String url3 = setCompanyVideoUrl.getText();
		
		if(name!=null && url1!= null && url2!= null && url3!=null) {
			backSide.changeAirCompanyTheme(name,url1,url2,url3);
		}
		
	}
	@FXML
	public void clickMovieUrlSearch() {
		try {
			movieUrl.setText(applyFileChooser().toString());
		}catch(Exception e) {}
	}
	@FXML
	public void clickPicUrlSearch() {
		try {
			picUrl.setText(applyFileChooser().toString());
		}catch(Exception e) {}
	}
	@FXML
	public void clickSetPic1Search() {
		try {
			setPicture1Url.setText(applyFileChooser().toString());
		}catch(Exception e) {}
	}
	@FXML
	public void clickSetPic2Search() {
		try {
			setPicture2Url.setText(applyFileChooser().toString());
		}catch(Exception e) {}
	}
	@FXML
	public void clickSetVideoSearch() {
		try {
			setCompanyVideoUrl.setText(applyFileChooser().toString());
		}catch(Exception e) {}
	}
	
	
}
