package sample;

import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    private static Interpreter instance = null;
    private final List<String> validCommands = Arrays.asList("G00", "G01", "G02", "G03", "G28", "M00", "M02", "M03", "M04", "M05");
    private GraphicsContext graphicsContext;

    private Interpreter() {
    }

    public static Interpreter getInstance() {
        if (instance == null) instance = new Interpreter();
        return instance;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    boolean isValidCommand(String command) {
        return validCommands.contains(command.toUpperCase());
    }


    void translateCommand(String lineOfCode) {
        String[] command = lineOfCode.split(" ", 2);
      /*  Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(command[1]);
        String var = matcher.group();*/
        if (isValidCommand(command[0])) {
            switch (command[0].toUpperCase()) {
                case "G01":
                    drawLine(command[1].toUpperCase());
            }
        }
        //else TODO Throw an exception
    }

    private void drawLine(String command) {
        //TODO define the starting position
        Task draw = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                double x1, x2, y1, y2;
                x1 = Double.parseDouble(command.substring(command.indexOf("X") + 1, command.indexOf("Y")));
              //  y1 = Double.parseDouble(command.substring(command.indexOf("Y")+1,);
                x1 = 15;
                y1 = 3;
                x2 = 100;
                y2 = 100;
                double m = (y2 - y1) / (x2 - x1);
                double b = y1 - (m * x1);
                graphicsContext.beginPath();
                graphicsContext.lineTo(x1, y1);
                for (; x1 <= x2; x1++) {
                    y1 = (m * x1) + b;
                    graphicsContext.lineTo(x1, y1);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    graphicsContext.stroke();
                }


                return null;


            }
        };
        new Thread(draw).start();
    }

}


