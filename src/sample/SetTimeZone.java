package sample;

import com.alibaba.fastjson.JSONObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SetTimeZone extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    static JSONObject object = new JSONObject();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("settimezone.fxml"));
        stage.setTitle("TimeManager");
        Scene scene = new Scene(root, 182, 301);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Main main = new Main();
                Stage stage = new Stage();
                try {
                    main.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        stage.show();
        initBox(root,stage);
    }

    public void initBox(Parent parent,Stage stage) {
        ComboBox fsm = (ComboBox) parent.lookup("#fsm");
        fsm.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

        ComboBox fem = (ComboBox) parent.lookup("#fem");
        fem.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

        ComboBox ssm = (ComboBox) parent.lookup("#ssm");
        ssm.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

        ComboBox sem = (ComboBox) parent.lookup("#sem");
        sem.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

        ComboBox tsm = (ComboBox) parent.lookup("#tsm");
        tsm.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

        ComboBox tem = (ComboBox) parent.lookup("#tem");
        tem.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

        ComboBox fsh = (ComboBox) parent.lookup("#fsh");
        fsh.getItems().addAll("18", "19", "20", "21");

        ComboBox feh = (ComboBox) parent.lookup("#feh");
        feh.getItems().addAll("18", "19", "20", "21");

        ComboBox ssh = (ComboBox) parent.lookup("#ssh");
        ssh.getItems().addAll("18", "19", "20", "21");

        ComboBox seh = (ComboBox) parent.lookup("#seh");
        seh.getItems().addAll("18", "19", "20", "21");

        ComboBox teh = (ComboBox) parent.lookup("#teh");
        teh.getItems().addAll("18", "19", "20", "21");

        ComboBox tsh = (ComboBox) parent.lookup("#tsh");
        tsh.getItems().addAll("18", "19", "20", "21");


        Button button = (Button) parent.lookup("#button12");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String SFsm = fsm.getValue().toString();
                    String SFem = fem.getValue().toString();
                    String SSsm = ssm.getValue().toString();
                    String SSem = sem.getValue().toString();
                    String STsm = tsm.getValue().toString();
                    String STem = tem.getValue().toString();
                    String SFsh = fsh.getValue().toString();
                    String SFeh = feh.getValue().toString();
                    String SSsh = ssh.getValue().toString();
                    String SSeh = seh.getValue().toString();
                    String STsh = tsh.getValue().toString();
                    String STeh = teh.getValue().toString();


                    object.put("firstStartTimeHour", SFsh);
                    object.put("firstStartTimeMin", SFsm);
                    object.put("firstEndTimeHour", SFeh);
                    object.put("firstEndTimeMin", SFem);

                    object.put("secondStartTimeHour", SSsh);
                    object.put("secondStartTimeMin", SSsm);
                    object.put("secondEndTimeHour", SSeh);
                    object.put("secondEndTimeMin", SSem);

                    object.put("thirdStartTimeHour", STsh);
                    object.put("thirdStartTimeMin", STsm);
                    object.put("thirdEndTimeHour", STeh);
                    object.put("thirdEndTimeMin", STem);
                    try {
                        File writename = new File("./time.json"); // 相对路径，如果没有则要建立一个新的output。txt文件
                        writename.delete();
                        writename.createNewFile(); // 创建新文件
                        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
                        out.write(object + "");
                        out.flush(); // 把缓存区内容压入文件
                        out.close(); // 最后记得关闭文件

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("设置成功！");
                        alert.setHeaderText("设置已经完成");
                        alert.setContentText("现在您可以在首页看到第二项更新的变化啦！");
                        alert.showAndWait();

                        stage.close();
                        Main main = new Main();
                        Stage stage = new Stage();
                        main.start(stage);
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("错误！");
                        alert.setHeaderText("在设置时，遇到了一个问题");
                        alert.setContentText("设置时出现了一个未知的问题，错误码：IO Error");
                        alert.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误！");
                    alert.setHeaderText("在设置时，遇到了一个问题");
                    alert.setContentText("您可能忘记设置某一个时间了哦!请检查！");
                    alert.showAndWait();
                }

            }
        });

        Button button1 = (Button) parent.lookup("#button121");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("说明！");
                alert.setHeaderText("设置说明：");
                alert.setContentText("从左向右，第一个框是小时，第二个框是分钟\n\n第一行为第一节晚自习开始时间\n" +
                        "第二行为第一节晚自习结束时间\n" +
                        "第三行为第二节晚自习开始时间\n" +
                        "第四行为第二节晚自习结束时间\n" +
                        "第五行为第三节晚自习开始时间\n" +
                        "第六行为第三节晚自习结束时间\n"
                );
                alert.showAndWait();
            }
        });

    }
}
