package sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Platform;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main extends Application {

    static Label nowtime;
    static Label endtime;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TimeManager");
        Scene scene = new Scene(root, 182, 301);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        primaryStage.show();
        initView(root,primaryStage);
        testJson2();
    }

    private void initView(Parent parent,Stage primaryStage) {

        Button ST = (Button)parent.lookup("#sttimest");
        ST.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                SetTimeZone setTimeZone = new SetTimeZone();
                Stage stage = new Stage();
                try {
                    setTimeZone.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        nowtime = (Label)parent.lookup("#nowTime");
        endtime = (Label)parent.lookup("#endTime");

        Thread thread = new Thread(() -> {
            while (true){
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                int nowHour = c.get(Calendar.HOUR);
                int nowMinute = c.get(Calendar.MINUTE);
                try {
                    Thread.sleep(700);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }

                if(nowHour < 10) {
                    if (nowMinute < 10) {
                        Platform.runLater(() -> nowtime.setText("0" + nowHour + ":0" + nowMinute));
                    } else {
                        Platform.runLater(() -> nowtime.setText("0" + nowHour + ":" + nowMinute));
                    }
                } else {
                    if (nowMinute < 10) {
                        Platform.runLater(() -> nowtime.setText(nowHour + ":0" + nowMinute));
                    } else {
                        Platform.runLater(() -> nowtime.setText(nowHour + ":" + nowMinute));
                    }
                }
                try {
                    Thread.sleep(700);
                    if(nowHour < 10) {
                        if (nowMinute < 10) {
                            Platform.runLater(() -> nowtime.setText("0" + nowHour + " 0" + nowMinute));
                        } else {
                            Platform.runLater(() -> nowtime.setText("0" + nowHour + " " + nowMinute));
                        }
                    } else {
                        if (nowMinute < 10) {
                            Platform.runLater(() -> nowtime.setText(nowHour + " 0" + nowMinute));
                        } else {
                            Platform.runLater(() -> nowtime.setText(nowHour + " " + nowMinute));
                        }
                    }
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }
        });
        thread.start();


    }

    public static void main(String[] args) {
        launch(args);
    }


    public void testJson2() {

        String pathname = "./time.json"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = null; // 建立一个输入流对象reader
        try {
            reader = new InputStreamReader(
                    new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONObject object = JSONObject
                .parseObject(line);
        //string
        String s = object.getString("firstStartTime");
        System.out.println(s);
    }
}
