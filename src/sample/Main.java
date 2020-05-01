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
import javafx.scene.control.TextArea;
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
    static TextArea askTC;
    int overHour = 00;
    int overMin = 00;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TimeManager");
        Scene scene = new Scene(root, 182, 305);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        primaryStage.show();
        initView(root, primaryStage);
        testJson2();
        talkTime();
    }

    private void initView(Parent parent, Stage primaryStage) {

        Button ST = (Button) parent.lookup("#sttimest");
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

        nowtime = (Label) parent.lookup("#nowTime");
        endtime = (Label) parent.lookup("#endTime");
        askTC = (TextArea) parent.lookup("#askTC");

        Thread thread = new Thread(() -> {
            while (true) {
                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                int nowHour = c.get(Calendar.HOUR);
                int nowMinute = c.get(Calendar.MINUTE);
                try {
                    Thread.sleep(700);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }

                if (nowHour < 10) {
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
                    if (nowHour < 10) {
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

        Date n = new Date();//可以对每个时间域单独修改
        SimpleDateFormat f = new SimpleDateFormat("HH");
        SimpleDateFormat fm = new SimpleDateFormat("mm");
        int nowHour = Integer.parseInt(f.format(n));
        int nowMinute = Integer.parseInt(fm.format(n));

        JSONObject object = JSONObject
                .parseObject(line);
        int SFsm = Integer.parseInt(object.getString("firstStartTimeMin"));
        int SFem = Integer.parseInt(object.getString("firstEndTimeMin"));
        int SSsm = Integer.parseInt(object.getString("secondStartTimeMin"));
        int SSem = Integer.parseInt(object.getString("secondEndTimeMin"));
        int STsm = Integer.parseInt(object.getString("thirdStartTimeMin"));
        int STem = Integer.parseInt(object.getString("thirdEndTimeMin"));
        int SFsh = Integer.parseInt(object.getString("firstStartTimeHour"));
        int SFeh = Integer.parseInt(object.getString("firstEndTimeHour"));
        int SSsh = Integer.parseInt(object.getString("secondStartTimeHour"));
        int SSeh = Integer.parseInt(object.getString("secondEndTimeHour"));
        int STsh = Integer.parseInt(object.getString("thirdStartTimeHour"));
        int STeh = Integer.parseInt(object.getString("thirdEndTimeHour"));



        Thread thread = new Thread(() -> {
            while (true) {
                if (SFsh == nowHour || SFeh == nowHour) {
                    if (SFeh >= nowHour || SFem <= nowMinute) {
                        // 计算出已经进行多久的晚自习了 分
                        if (SFsh - nowHour == 0) {
                            overHour = 00;
                            overMin = nowMinute - SFsm;
                        } else {
                            overHour = SFeh - SFsh;
                            overMin = nowMinute;
                        }
                        Platform.runLater(() -> endtime.setText(FontNumber.FontNumber(overHour) + ":" + FontNumber.FontNumber(overMin)));
                        try {
                            Thread.sleep(700);
                            Platform.runLater(() -> endtime.setText(FontNumber.FontNumber(overHour) + " " + FontNumber.FontNumber(overMin)));
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }
    String[] talkT;
    public void talkTime() {
        // 获取Json文件
        String pathname = "./talk.json"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
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

        // 解析 Json
        JSONObject jsonObject = JSONObject.parseObject(line);
        int howNumber = Integer.parseInt(jsonObject.getString("hownumber"));

        // 建立数组，存储数据
        talkT = new String[howNumber];
        // 将数据放入数组
        for (int i = 0; i < howNumber; i++) {
            int i1 = i+1;
            talkT[i] = jsonObject.getString( i1 + "t");
        }

        // 数据进入

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    for (int i = 0; i < howNumber; i++) {
                        int i1 = i+1;
                        talkT[i] = jsonObject.getString( i1 + "t");
                        int finalI = i;
                        Platform.runLater(() -> askTC.setText(talkT[finalI]));
                        Thread.sleep(3000);
                    }
                    Platform.runLater(() -> askTC.setText(""));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
}
